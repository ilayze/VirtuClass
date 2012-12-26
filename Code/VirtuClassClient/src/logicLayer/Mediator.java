package logicLayer;

//import guiLayer.EditorFrame;
import guiLayer.EditorFrame;
import guiLayer.EditorFrame2;
import guiLayer.LoginFrame;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JOptionPane;



public class Mediator{
	
	
	private class LoginMediator implements ActionListener{
	
		Login l;
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand()==LoginFrame.EXIT_CMD)
			{
				System.exit(0);
			}
			else if(e.getActionCommand()==LoginFrame.SIGNUP_CMD)
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
			else if(e.getActionCommand()==LoginFrame.LOGIN_CMD)
			{
				try {
		
					boolean isOk = l.login();
					if(!isOk)
					{
						JOptionPane.showMessageDialog(null, "Wrong username / password", "Invalid user", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
// need to return a signal, this signal will make the mediator to open OpenClassMediator
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								try {
									l.forceExit();
									OpenClassMediator op = new OpenClassMediator();
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});
						
					}

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
					System.out.println(e1.getMessage());
				}
				
				 
				
			}
		}
		
	}
	
	private class OpenClassMediator implements ActionListener
	{
		OpenClass op;
		private OpenClassMediator thisClass;

		public OpenClassMediator() throws InvocationTargetException, InterruptedException
		{

			thisClass=this;
			System.out.println("in open class mediator");
			
				System.out.println("alala");

				
				EventQueue.invokeLater(new Runnable() {
				public void run() {
						try {
							EditorFrame2 frame = new EditorFrame2(thisClass);
							frame.setVisible(true);
							op = new OpenClass(frame.ei);
							Thread t1 = new Thread(op);
							t1.start();
							
								
							
						} catch (Exception e) {
							e.printStackTrace();
						}
					}});
			

			//need to open 2 threads here for read/write
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
		
	}
	/**
	 * @param args
	 */
	public Mediator()
	{
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginMediator m = new LoginMediator();
					LoginFrame frame = new LoginFrame(m);
					WindowEvent wev = new WindowEvent(frame, WindowEvent.WINDOW_CLOSING);
					m.l = new Login(frame.li,wev);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});	
	}
	
	
	
	public static void main(String[] args) {//init Mediator
		Mediator m = new Mediator();
	}



}
