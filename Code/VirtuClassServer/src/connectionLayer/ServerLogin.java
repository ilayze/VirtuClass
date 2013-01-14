package connectionLayer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;

import logicLayer.FakeDB;

public class ServerLogin extends Thread {
	
	private ArrayBlockingQueue<String> waitingUsers;
	private ServerSocket serverSocket;
	
	public ServerLogin(ArrayBlockingQueue<String> waitingUsers)
	{
		try {
			serverSocket = new ServerSocket(8500,20);
		} catch (IOException e) {
			
			e.printStackTrace();
			System.exit(1);
		}
		this.waitingUsers=waitingUsers;
	}
	
	@Override
	public void run()
	{
		while(true)
		{
			Socket socket;
			try {
				socket = serverSocket.accept();
//				System.out.println("New client asked for a connection");
				TcpThread t = new TcpThread(socket);    // make a thread of it
//				System.out.println("Starting a thread for a new Client");
				t.start();
			} catch (IOException e) {	
				e.printStackTrace();
			}  // accept connection
		}
	}
	
	class TcpThread extends Thread {
		Socket socket;
		ObjectInputStream Sinput;
		ObjectOutputStream Soutput;

		TcpThread(Socket socket) {
			this.socket = socket;
		}
		
		public void run() {
			/* Creating both Data Streams */
			int loginStatus=0;
			boolean signUpStatus=true;
//			System.out.println("Thread trying to create Object Input/Output Streams");
			try {
				initStreams();
			} catch (IOException e1) {
				e1.printStackTrace();
				return;
			}

//			System.out.println("Thread waiting for a String from the Client");
			// read a String (which is an object)
			try {
				String username = (String) Sinput.readObject();
				String password = (String) Sinput.readObject();
				//str = str.toUpperCase();
				String toWrite="";
				int state=-1;
				if(username.charAt(0)=='0' && password.charAt(0)=='0')
				{
					state=0;
					loginStatus=login(username, password);
					if(loginStatus>=0)
						toWrite+="0";//"username: " + username.substring(1) + " OK";
					else
						toWrite+="1";//"username: " + username.substring(1) + " Bad";		
				}
				else if(username.charAt(0)=='1' && password.charAt(0)=='1')
				{
					state=1;
					signUpStatus = signup(username, password);
					if(signUpStatus==true)
						toWrite+="0";//"username: " + username.substring(1) + " OK";
					else
						toWrite+="1";//"username: " + username.substring(1) + " Bad";
				}

				Soutput.writeObject(toWrite);
				Soutput.flush();
				if(state!=0 || loginStatus<0)
					return;
				
				waitingUsers.put(username.substring(1));
			}
			catch (IOException e) {
				System.out.println("Lost connection from socket");
		//		System.out.println("Exception reading/writing  Streams: ");
		//		e.printStackTrace();
				return;                               
			}
			// will surely not happen with a String
			catch (ClassNotFoundException o) {                               
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			finally {
				try {Soutput.close();}
				catch (Exception e){}
				try{Sinput.close();}
				catch(IOException e){}
				try{socket.close();}
				catch(IOException e){}
			}
			
		}

		private void initStreams() throws IOException {
				Soutput = new ObjectOutputStream(socket.getOutputStream());
				Soutput.flush();
				Sinput  = new ObjectInputStream(socket.getInputStream());
		}
		
		private int login(String username, String password)
		{
			int loginStatus=0;
			FakeDB fakeDB = FakeDB.getDB();
			loginStatus=fakeDB.signIn(username.substring(1), password.substring(1));
			System.out.println("username: " + username + ". login status: "+loginStatus);
			return loginStatus;
		}
		
		private boolean signup(String username, String password)
		{
			boolean signUpStatus= true;
			FakeDB fakeDB = FakeDB.getDB();
			signUpStatus=fakeDB.signUp(username.substring(1), password.substring(1));
			return signUpStatus;
		}
	}
	
	
}
