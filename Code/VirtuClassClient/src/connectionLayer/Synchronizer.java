package connectionLayer;



//The client code Client.java:


import java.net.*;
import java.io.*;

public class Synchronizer {

	ObjectInputStream Sinput;                // to read the socker
	ObjectOutputStream Soutput;        // towrite on the socket
	Socket socket;
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
		try {
			socket = new Socket("localhost", port);
		}
		catch(Exception e) {
			System.out.println("Error connectiong to server: " + e);
			return false;
		}
		System.out.println("Connection accepted " +
				socket.getInetAddress() + ":" +
				socket.getPort());

		/* Creating both Data Stream */
		try
		{
			Sinput  = new ObjectInputStream(socket.getInputStream());
			Soutput = new ObjectOutputStream(socket.getOutputStream());
		}
		catch (IOException e) {
			System.out.println("Exception creating new Input/output Streams: " + e);
			return false;
		}
		return true;

	}

	public boolean sendData(String s)
	{
		// now that I have my connection
		//String test = "aBcDeFgHiJkLmNoPqRsTuVwXyZ";
		// send the string to the server
		System.out.println("Client sending \"" + s + "\" to server");
		try {
			Soutput.writeObject(s);
			Soutput.flush();
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
			response = (String) Sinput.readObject();
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
		Sinput.close();
		Soutput.close();
		socket.close();

	}    
	/*while(true)
              {
            	 try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
              }
      } 
	 */
}
