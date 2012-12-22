package logicLayer;

import java.io.IOException;

import javax.swing.JOptionPane;

import connectionLayer.Synchronizer;

public class Login{
	private Synchronizer _sync;
	private LoginInfo li;
	
	public Login(LoginInfo li) throws Exception
	{
		this.li=li;
//		_sync = Synchronizer.getSync();
//		boolean connected = _sync.connect(8500);
//		if(!connected)
//			throw new Exception("Connection failed");
	}
	
	public void login() throws Exception{
		reconnect();
		String username=li.getUsername();
		char [] cs = li.getPassword();
		if(username==null || username.length()==0)
		{
			JOptionPane.showMessageDialog(null, "Please enter username");
			return;
		}

		String pw = String.copyValueOf(cs);
		if(pw==null || pw.length()==0)
		{
			JOptionPane.showMessageDialog(null, "Please enter password");
			return;
		}
		
		_sync.sendData("0"+username);
		_sync.sendData("0"+pw);
		String data = _sync.getData();	//if 0 = success, 1=failure
		_sync.disconnect();
		if (data.equals("0"))
		{
			//success
		}
		else if (data.equals("1"))
		{
			//failure
		}
		else
		{
			throw new UnknownServerMessageException("Invalid return after call to login. Expected 0/1");
		}
		
	}
	
	
	public void signUp() throws Exception//for now it's almost like login. But later it will change
	{
		reconnect();
		String username=li.getUsername();
		char [] cs = li.getPassword();
		if(username==null || username.length()==0)
		{
			JOptionPane.showMessageDialog(null, "Please enter username");
			return;
		}

		String pw = String.copyValueOf(cs);
		if(pw==null || pw.length()==0)
		{
			JOptionPane.showMessageDialog(null, "Please enter password");
			return;
		}
		
		_sync.sendData("1"+username);
		_sync.sendData("1"+pw);
		String data = _sync.getData();	//if 0 = success, 1=failure
		if(data==null)
			throw new Exception("Problem reading back from server");
		_sync.disconnect();
		if (data.equals("0"))
		{
			//success
		}
		else if (data.equals("1"))
		{
			//failure
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
}