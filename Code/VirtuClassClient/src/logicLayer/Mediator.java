package logicLayer;

import guiLayer.EditorFrame;
import guiLayer.LoginFrame;

import java.awt.EventQueue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.concurrent.Semaphore;
import javax.swing.JOptionPane;






public class Mediator extends Thread{
	
	
	private class LoginScreenMediator implements ActionListener{
	
		LoginMaster l;
		LoginMediator lm;
		SignUpMediator sm;
		Semaphore loginKey = new Semaphore(1);

		public LoginScreenMediator()
		{
			lm = new LoginMediator();
			sm = new SignUpMediator();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand()==LoginFrame.EXIT_CMD)
				System.exit(0);
			else if(e.getActionCommand()==LoginFrame.SIGNUP_CMD)
				sm.signUpPressed();
			else if(e.getActionCommand()==LoginFrame.LOGIN_CMD)
				lm.loginPressed();
		}
		
		private class LoginMediator implements Runnable
		{
			
				public LoginMediator()
				{
					
				}
				private LoginMediator(Semaphore obj)
				{
					loginKey=obj;
				}
				
				public void loginPressed()
				{
					if(loginKey.tryAcquire())
					{
						new Thread(new LoginMediator(loginKey)).start();
					}
					return;
					
	
				}
			
				
				private void loginSucceed()
				{
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								l.forceExit();
								new Thread(new OpenClassMediator()).start();
								//OpenClassMediator op = new OpenClassMediator();
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
	
				}
	
				private void tryToLogin() throws Exception
				{
					boolean isOk = l.sendRequest(0);//1=signUp, 0 = login
					if(!isOk)
					{
						JOptionPane.showMessageDialog(null, "Wrong username / password", "Invalid user", JOptionPane.ERROR_MESSAGE);
						return;
					}
					loginSucceed();
				}
				
				
				@Override
				public void run() {
					try {
						tryToLogin();
					} catch (Exception e1) {
						System.out.println(e1.getMessage());
					}
					finally
					{
						loginKey.release();
					}		
				}	
		}
		
		private class SignUpMediator implements Runnable
		{
				private SignUpMediator(Semaphore obj) {
					loginKey=obj;	
				}
	
				public SignUpMediator()
				{
				}
				
				public void signUpPressed()
				{
					if(loginKey.tryAcquire())
					{
						new Thread(new SignUpMediator(loginKey)).start();
					}
	
				}
	
				private void tryToSignUp() throws Exception
				{
					boolean isOk = l.sendRequest(1);//1 = signup, 0 = login
					if(!isOk)
					{
						JOptionPane.showMessageDialog(null, "Username already exist.", "Choose a different username", JOptionPane.ERROR_MESSAGE);
					}
				}
				
				@Override
				public void run() {
					try {
						tryToSignUp();
					} catch (Exception e1) {
						System.out.println(e1.getMessage());
					}
					finally
					{
						loginKey.release();
					}
				}
		}
		
		
		
	}
	//can separate to different classes
	private class OpenClassMediator implements Runnable//http://eclipse-metrics.sourceforge.net/descriptions/pages/cohesion/PairwiseFieldIrrelation.html
	{
		OpenClassroom op;
		
			private class OpenClassMessagesMediator implements ActionListener
			{
	
				@Override
				public void actionPerformed(ActionEvent e) {
					if(e.getActionCommand()==EditorFrame.SEND_CMD)
					{
						synchronized (op) { 
							op.notifyAll();
						}
						
					}
				}
				
			}
		
			public OpenClassMediator()
			{
				
			}
	
			@Override
			public void run() {
				EventQueue.invokeLater(new Runnable() {
				public void run() {
						try {
	
							startEditor();						
						} catch (Exception e) {
							e.printStackTrace();
						}
					}});
			}

			public void startEditor()
			{
				OpenClassMessagesMediator msgMed = new OpenClassMessagesMediator();
				EditorFrame frame = new EditorFrame(msgMed);
				frame.setVisible(true);
				op = new OpenClassroom(frame.data.ei);
				Thread t1 = new Thread(op);
				t1.start();
	
			}
	}

	public Mediator()
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					startLoginScreen();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	private void startLoginScreen() throws Exception
	{
		LoginScreenMediator m = new LoginScreenMediator();
		LoginFrame frame = new LoginFrame(m);
		WindowEvent wev = new WindowEvent(frame, WindowEvent.WINDOW_CLOSING);
		m.l = new LoginMaster(frame.data.li,wev);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {//init Mediator
		
		new Thread(new Mediator());
	}

}
