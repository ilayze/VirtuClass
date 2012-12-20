package logicLayer;

import connectionLayer.Synchronizer;

public class Login {
	Synchronizer _sync;
	
	public Login() throws Exception
	{
		_sync = Synchronizer.getSync();
		boolean connected = _sync.connect(8500);
		if(!connected)
			throw new Exception("Connection failed");
	}
	
	public void login(String username,String Password){
		_sync.sendData("fsdfsd");
		
	}
	
}