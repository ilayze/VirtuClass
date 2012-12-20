package logicLayer;

import guiLayer.LoginFrame;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class Mediator{
	
	
	private class LoginMediator implements ActionListener{
	
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand()==LoginFrame.EXIT_CMD)
			{
				System.exit(0);
			}
			else if(e.getActionCommand()==LoginFrame.SIGNUP_CMD)
			{
				
			}
			else if(e.getActionCommand()==LoginFrame.LOGIN_CMD)
			{
				try {
					Login l = new Login();
					l.login("ggg","aaa");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
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
