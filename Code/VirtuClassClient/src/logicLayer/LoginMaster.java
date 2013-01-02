package logicLayer;

import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JOptionPane;

import connectionLayer.Synchronizer;

public class LoginMaster{
	private Synchronizer _sync;
	private LoginInfo li;
	private WindowEvent wev;
	public Login a;
	
	public LoginMaster(LoginInfo li, WindowEvent wev) throws Exception
	{
		this.li=li;
		this.wev=wev;
//		_sync = Synchronizer.getSync();
//		boolean connected = _sync.connect(8500);
//		if(!connected)
//			throw new Exception("Connection failed");
	}
	
	public boolean login() throws Exception{

		try
		{
			reconnect();
		}
		catch(Exception e)
		{
			return false;
		}
		String username=li.getUsername();
		char [] cs = li.getPassword();
		if(username==null || username.length()==0)
		{
			JOptionPane.showMessageDialog(null, "Please enter username");
			throw new Exception("invalid username");
		}

		String pw = String.copyValueOf(cs);
		if(pw==null || pw.length()==0)
		{
			JOptionPane.showMessageDialog(null, "Please enter password");
			throw new Exception("invalid password");
		}
		
		_sync.sendData("0"+username);
		_sync.sendData("0"+pw);
		String data = _sync.getData();	//if 0 = success, 1=failure
		_sync.disconnect();
		System.out.println(data);
		if (data.equals("0"))
		{
			System.out.println("success");

			return true;
		}
		else if (data.equals("1"))
		{
			System.out.println("failure");
			
			return false;
		}
		else
		{
			throw new UnknownServerMessageException("Invalid return after call to login. Expected 0/1");
		}
		
	}
	
	
	public boolean signUp() throws Exception//for now it's almost like login. But later it will change
	{
		try
		{
			reconnect();
		}
		catch(Exception e)
		{
			return false;
		}
		String username=li.getUsername();
		char [] cs = li.getPassword();
		if(username==null || username.length()==0)
		{
			JOptionPane.showMessageDialog(null, "Please enter username");
			throw new Exception("invalid username");
		}

		String pw = String.copyValueOf(cs);
		if(pw==null || pw.length()==0)
		{
			JOptionPane.showMessageDialog(null, "Please enter password");
			throw new Exception("invalid password");
		}
		
		_sync.sendData("1"+username);
		_sync.sendData("1"+pw);
		String data = _sync.getData();	//if 0 = success, 1=failure
		if(data==null)
			throw new Exception("Problem reading back from server");
		_sync.disconnect();
		System.out.println(data);
		if (data.equals("0"))
		{
			System.out.println("success");
			return true;
		}
		else if (data.equals("1"))
		{
			System.out.println("failure");
			return false;
		}
		else
		{
			throw new UnknownServerMessageException("Invalid return after call to signUp. Expected 0/1");
		}
		
	}
	
	private void reconnect()throws Exception
	{
		_sync = Synchronizer.getSync();
		boolean connected = _sync.connect(8500);
		if(!connected)
			throw new Exception("Connection failed");
	}
	
	public void forceExit()
	{
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
	}
	
	class Login implements Runnable
	{
		public void run()
		{
			
		}
	}
	
}