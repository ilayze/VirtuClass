package logicLayer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class User {

	private String id="Anonymous";
	private Socket socket;
	private DataOutputStream os;
	private DataInputStream is;
	
	public User(String name, Socket socket,DataOutputStream os,DataInputStream is)
	{
		if(name!=null)
			this.id=name;
		this.socket = socket;
		this.os=os;
		this.is=is;
	}

	public String getName() {
		return id;
	}

	public Socket getSocketDescriptor() {
		return socket;
	}

	public DataOutputStream getOutputStream() {
		return os;
	}

	public DataInputStream getInputStream() {
		return is;
	}


	public int quitChat() throws IOException{
		int closedSum=0;//+1 for inputStream close, +2 for outputStream
		if(is!=null)
		{
			is.close();
			closedSum+=1;
		}
		if(os!=null)
		{
			os.close();
			closedSum+=2;
		}
		if(socket!=null && !socket.isClosed())
			socket.close();
		
		return closedSum;
	}
	
	public boolean equals(Object obj)
	{
		if(obj instanceof User)
		{
			User usrToCompare = (User)obj;
			if(usrToCompare.getName().equals(this.getName()))
				return true;
		}
		return false;
		
	}
	
	
	
	
	
}
