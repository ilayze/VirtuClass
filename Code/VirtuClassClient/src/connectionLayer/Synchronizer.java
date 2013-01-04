package connectionLayer;



//The client code Client.java:


import java.net.*;
import java.io.*;

public class Synchronizer {

	public static class SynchronizerData {
		public ObjectInputStream Sinput;
		public ObjectOutputStream Soutput;
		public Socket socket;

		public SynchronizerData() {
		}
	}

	private SynchronizerData data = new SynchronizerData();
	private static Synchronizer sync=null;

	// Constructor connection receiving a socket number
	private Synchronizer()
	{
		//sync=null;
	}
	public static Synchronizer getSync()
	{
		if(sync==null)
		{
			sync=new Synchronizer();
		}
		return sync;

	}

	public boolean connect(int port) {
		// we use "localhost" as host name, the server is on the same machine
		// but you can put the "real" server name or IP address
		//SyncInitializer initializer = new SyncInitializer();
		if(initializeSocket(port)==false)
			return false;

		/* Creating both Data Stream */
		if(initializeStreams()==false)
			return false;
		return true;

	}

	public boolean sendData(String s)
	{
		// now that I have my connection
		//String test = "aBcDeFgHiJkLmNoPqRsTuVwXyZ";
		// send the string to the server
		System.out.println("Client sending \"" + s + "\" to server");
		try {
			data.Soutput.writeObject(s);
			data.Soutput.flush();
		}
		catch(IOException e) {
			System.out.println("Error writting to the socket: " + e);
			return false;
		}
		return true;
	}

	public String getData()
	{
		// read back the answer from the server
		String response;
		try {
			response = (String) data.Sinput.readObject();
			return response;
			//System.out.println("Read back from server: " + response);
		}
		catch(Exception e) {
			System.out.println("Problem reading back from server: " + e);
			return null;
		}
		//	return true;
	}

	public void disconnect() throws IOException
	{
		data.Sinput.close();
		data.Soutput.close();
		data.socket.close();

	}   


	private boolean initializeStreams() {
		try
		{
			data.Sinput  = new ObjectInputStream(data.socket.getInputStream());
			data.Soutput = new ObjectOutputStream(data.socket.getOutputStream());
		}
		catch (IOException e) {
			System.out.println("Exception creating new Input/output Streams: " + e);
			return false;
		}
		return true;
	}

	private boolean initializeSocket(int port) {
		try {
			data.socket = new Socket("localhost", port);
		}
		catch(Exception e) {
			System.out.println("Error connectiong to server: " + e);
			return false;
		}
		System.out.println("Connection accepted " +
				data.socket.getInetAddress() + ":" +
				data.socket.getPort());
		return true;
	}




}
