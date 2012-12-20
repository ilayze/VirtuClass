package logicLayer;

import guiLayer.LoginFrame;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import connectionLayer.Synchronizer;

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
