package logicLayer;

import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JOptionPane;

import connectionLayer.Synchronizer;

public class LoginMaster{


	private UsrInfoHandler handler;
	private LoginScreenTerminator terminator;
	public LoginMaster(LoginInfo li, WindowEvent wev) throws Exception
	{
		handler = new UsrInfoHandler(li);
		terminator = new LoginScreenTerminator(wev);

	}
	
	public String getUserName()
	{
		return handler.li.getUsername();
	}
	
	public boolean sendRequest(int loginType) throws Exception//for now it's almost like login. But later it will change
	{
		LoginSignUpSlave slave = new LoginSignUpSlave(loginType);
		return slave.tryToEnter();

	}


	public void forceExit()
	{
		terminator.closeWindow();
	}

	
	private class LoginSignUpSlave
	{
		private int loginType;

		private LoginSignUpSlave(int loginType)
		{
			this.loginType=loginType;
		}

		private boolean tryToEnter() throws Exception{
			handler.collectInfo();
			
			LoginConnection newConnection = new LoginConnection();
			boolean connectionSucceed=true;
			connectionSucceed=newConnection.startConnection();
			if(!connectionSucceed)
				return false;

			String data = newConnection.sendAndGetData(handler.username,handler.pw,loginType);
			LoginDataAnalyzer analyzer = new LoginDataAnalyzer(data);
			newConnection.close();
			return analyzer.sendOccured(loginType);

		}
	}

	private class LoginConnection
	{
		private Synchronizer _sync;
		private boolean startConnection()
		{
			try
			{
				reconnect();
			}
			catch(Exception e)
			{
				return false;
			}
			return true;
		}


		public void close() throws IOException {
			_sync.disconnect();

		}

		public String sendAndGetData(String usr, String pw, int loginType)
		{
			sendData(usr, pw, loginType);
			return getReceivedData();
		}

		public String getReceivedData() {
			return _sync.getData();	//if 0 = success, 1=failure

		}

		public void sendData(String usr, String pw, int loginType) {

			_sync.sendData(""+loginType+usr);
			_sync.sendData(""+loginType+pw);
		}

		private void reconnect()throws Exception
		{
			_sync = Synchronizer.getSync();
			boolean connected = _sync.connect(8500);
			if(!connected)
				throw new Exception("Connection failed");
		}

	}

	private class UsrInfoHandler
	{
		private String username;
		private String pw;
		private LoginInfo li;

		public UsrInfoHandler(LoginInfo li) {
			this.li=li;
		}

		
		private void collectInfo() throws Exception
		{
			username=li.getUsername();
			char [] cs = li.getPassword();
			pw = String.copyValueOf(cs);
			checkIfBlank();
		}

		private void checkIfBlank() throws Exception
		{	
			if(username==null || username.length()==0)
			{
				JOptionPane.showMessageDialog(null, "Please enter username");
				throw new Exception("invalid username");
			}		
			if(pw==null || pw.length()==0)
			{
				JOptionPane.showMessageDialog(null, "Please enter password");
				throw new Exception("invalid password");
			}
		}
	}

	private class LoginDataAnalyzer
	{
		private String data;
		public LoginDataAnalyzer(String data) {
			this.data=data;
		}

		private boolean analyze() throws Exception {
			if(data==null)
				throw new Exception("Problem reading back from server");

			if (data.equals("0"))
			{
				System.out.println("success");
				return true;
			}
			else// if (data.equals("1"))
			{
				System.out.println("failure");
				return false;
			}
		}

		private boolean sendOccured(int loginType) throws Exception
		{
			if(loginType==1 && data!=null && !data.equals("0") && !data.equals("1"))
				throw new UnknownServerMessageException("Invalid return after call to signUp. Expected 0/1");	
			else if(loginType==0 && data!=null && !data.equals("0") && !data.equals("1"))
				throw new UnknownServerMessageException("Invalid return after call to signUp. Expected 0/1");	
			return analyze();
		}
	}
	
	private class LoginScreenTerminator
	{
		private WindowEvent wev;
		public LoginScreenTerminator(WindowEvent wev) {
			this.wev=wev;
		}
		private void closeWindow()
		{
			Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
		}
	}

}