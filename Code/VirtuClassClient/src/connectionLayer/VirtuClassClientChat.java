package connectionLayer;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import logicLayer.OpenClassroom;

public class VirtuClassClientChat implements Runnable {


	private static boolean closed = false;
	private VirtuClassClientChatData data = new VirtuClassClientChatData(null,
			null, null);


	public VirtuClassClientChat(OpenClassroom op) {

		this.data.op=op;	

		// The default port.
		int portNumber = 8888;
		// The default host.
		String host = "localhost";// can be ip address

		/*
		 * Open a socket on a given host and port. Open input and output streams.
		 */
		initializeSocketAndStreams(portNumber, host);


		/*
		 * If everything has been initialized then we want to write some data to the
		 * socket we have opened a connection to on the port portNumber.
		 */
		startChat(op);

	}
	
	private VirtuClassClientChat(VirtuClassClientChatData data)
	{
		this.data=data;

	}
	
	/*
	 * Create a thread to read from the server. (non-Javadoc)
	 */
	public void run() {
		/*
		 * Keep on reading from the socket till we receive "Bye" from the
		 * server. Once we received that then we want to break.
		 */
		try {
			readChat();
			closed = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param op
	 */
	private void startChat(OpenClassroom op) {
		if (data.clientSocket != null && data.os != null && data.is != null) {
			try {

				/* Create a thread to read from the server. */
				new Thread(new VirtuClassClientChat(data)).start();

				writeChat(op);
				/*
				 * Close the output stream, close the input stream, close the socket.
				 */
				closeChat();
			} catch (IOException e) {
				System.err.println("IOException:  " + e);
			}
		}
	}
	/**
	 * @throws IOException
	 */
	private void closeChat() throws IOException {
		data.os.close();
		data.is.close();
		data.clientSocket.close();
	}
	/**
	 * @param op
	 * @throws IOException
	 */
	private void writeChat(OpenClassroom op) throws IOException {
		while (!closed) {

			synchronized(op){
				try {
					op.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(closed)
				break;
			String a = op.ei.getText();
			data.os.writeUTF(a);

		}
	}
	/**
	 * @param portNumber
	 * @param host
	 */
	private void initializeSocketAndStreams(int portNumber, String host) {
		try {
			data.clientSocket = new Socket(host, portNumber);
			//			inputLine = new BufferedReader(new InputStreamReader(System.in));
			data.os = new DataOutputStream(data.clientSocket.getOutputStream());
			data.is = new DataInputStream(data.clientSocket.getInputStream());
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host " + host);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to the host "
					+ host);
		}
	}


	/**
	 * @throws IOException
	 */
	private void readChat() throws IOException {
		String responseLine;
		while ((responseLine = data.is.readUTF()) != null && !responseLine.equals("/quit")) {
			
			data.op.ei.setText(responseLine);
		}
	}
	
	private static class VirtuClassClientChatData {
		private Socket clientSocket;
		private DataOutputStream os;
		private DataInputStream is;
		private OpenClassroom op;

		private VirtuClassClientChatData(Socket clientSocket,
				DataOutputStream os, DataInputStream is) {
			this.clientSocket = clientSocket;
			this.os = os;
			this.is = is;
		}
	}

}



