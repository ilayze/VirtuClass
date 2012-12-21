package logicLayer;

import connectionLayer.Synchronizer;

public class Login{
	Synchronizer _sync;
	LoginInfo li;
	
	public Login(LoginInfo li) throws Exception
	{
		this.li=li;
		_sync = Synchronizer.getSync();
		boolean connected = _sync.connect(8500);
		if(!connected)
			throw new Exception("Connection failed");
	}
	
	public void login(String username,char[] cs){
		_sync.sendData(username);
		String pw = String.copyValueOf(cs);
		_sync.sendData(pw);
		_sync.getData();
		_sync.getData();
	}
	
	String getUsername()
	{
		return li.getUsername();
	}
	
	char[] getPassword()
	{
		return li.getPassword();
	}
	
}