package logicLayer;

import javax.swing.JOptionPane;

import connectionLayer.Synchronizer;

public class Login{
	private Synchronizer _sync;
	private LoginInfo li;
	
	public Login(LoginInfo li) throws Exception
	{
		this.li=li;
		_sync = Synchronizer.getSync();
		boolean connected = _sync.connect(8500);
		if(!connected)
			throw new Exception("Connection failed");
	}
	
	public void login(){
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
		
		_sync.sendData(username);
		_sync.sendData(pw);
		_sync.getData();
		_sync.getData();
	}
	
}