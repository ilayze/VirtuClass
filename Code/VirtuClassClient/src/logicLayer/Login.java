package logicLayer;

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
		_sync.sendData(username);
		String pw = String.copyValueOf(cs);
		_sync.sendData(pw);
		_sync.getData();
		_sync.getData();
	}
	
}