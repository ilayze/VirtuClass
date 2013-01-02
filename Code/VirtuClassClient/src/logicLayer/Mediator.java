package logicLayer;

//import guiLayer.EditorFrame;
import guiLayer.EditorFrame2;
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
			
			Semaphore loginKey = new Semaphore(1);
			
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


			@Override
			public void run() {
				System.out.println("got here");
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					// need to open a new thread here..
					boolean isOk = l.login();
					if(!isOk)
					{
						JOptionPane.showMessageDialog(null, "Wrong username / password", "Invalid user", JOptionPane.ERROR_MESSAGE);
						//loginKey.release();
						return;
					}
					loginSucceed();
				} catch (Exception e1) {
					System.out.println(e1.getMessage());
				}
				finally
				{
					loginKey.release();
				}
				
			}
			
		}
		
		private class SignUpMediator
		{
			public void signUpPressed()
			{
				try {
					
					boolean isOk = l.signUp();
					if(!isOk)
					{
						JOptionPane.showMessageDialog(null, "Username already exist.", "Choose a different username", JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception e1) {
					System.out.println(e1.getMessage());
				}
			}
		}
		
		
		
	}
	
	private class OpenClassMediator implements ActionListener, Runnable
	{
		OpenClass op;
		private OpenClassMediator thisClass;

		public OpenClassMediator()
		{
			thisClass=this;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand()==EditorFrame2.SEND_CMD)
			{
				synchronized (op) { 
					op.notifyAll();
				}
				
			}
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
			EditorFrame2 frame = new EditorFrame2(thisClass);
			frame.setVisible(true);
			op = new OpenClass(frame.ei);
			Thread t1 = new Thread(op);
			t1.start();
			
		}
		
	}
	/**
	 * @param args
	 */
	public Mediator()
	{
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginScreenMediator m = new LoginScreenMediator();
					LoginFrame frame = new LoginFrame(m);
					WindowEvent wev = new WindowEvent(frame, WindowEvent.WINDOW_CLOSING);
					m.l = new LoginMaster(frame.li,wev);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});	
	}
	
	
	
	public static void main(String[] args) {//init Mediator
		
		new Thread(new Mediator());//Mediator m = new Mediator();
	}

}
