package connectionLayer;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import Mediator.LoginInfo;

public class ControlMessagesConnection {

	private Socket toServer;
	
	public ControlMessagesConnection()
	{
		try {
			toServer = new Socket("localhost", 8888);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendLoginInfo(LoginInfo loginInfo)
	{
		
	}
}
