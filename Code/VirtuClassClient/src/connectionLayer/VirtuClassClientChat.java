package connectionLayer;


import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ArrayBlockingQueue;

import logicLayer.OpenClassroom;

public class VirtuClassClientChat extends Thread {


	private static boolean closed = false;
	private VirtuClassClientChatData data = new VirtuClassClientChatData(null,
			null, null);


	public VirtuClassClientChat(OpenClassroom op) {
		this.data.op=op;
		int portNumber = 8888;	// The default port.
		// The default host.
		String host = "localhost";// can be ip address
		/*
		 * Open a socket on a given host and port. Open input and output streams.
		 */
		initializeSocketAndStreams(portNumber, host);
		 /* If everything has been initialized then we want to write some data to the
		 * socket we have opened a connection to on the port portNumber.
		 */
		startChat(op);
	}
	
	public VirtuClassClientChat(ArrayBlockingQueue<String> waitingMessagesToBeRead, ArrayBlockingQueue<String> waitingMessagesToBeWritten)
	{
		
		initializeSocketAndStreams(8888, "localhost");
		this.data.waitingMessagesToBeRead=waitingMessagesToBeRead;
		this.data.waitingMessagesToBeWritten=waitingMessagesToBeWritten;
		
	}
	private VirtuClassClientChat(VirtuClassClientChatData data)
	{
		this.data=data;

	}
	
	/*
	 * Create a thread to read from the server. (non-Javadoc)
	 */
	
	private class ChatReader implements Runnable
	{
		private VirtuClassClientChatData data;
		public ChatReader(VirtuClassClientChatData data) {
			this.data=data;
		}

		@Override
		public void run() {
			try {
				readChat();
				closed = true;
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}
		
		/**
		 * @throws IOException
		 */
		private void readChat() throws IOException {
			String responseLine;
			while ((responseLine = data.is.readUTF()) != null && !responseLine.equals("/quit")) {
				
				try {
					data.waitingMessagesToBeRead.put(responseLine);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//data.op.ei.setText(responseLine);
			}
		}
	}
	
	public void run() {
		startChat();
	}
	
	private void startChat() {
		if (data.clientSocket != null && data.os != null && data.is != null) {
//			try {
				/* Create a thread to read from the server. */
				new Thread(new ChatReader(data)).start();
				while(true)
				{
					try {
						String messageToWrite = data.waitingMessagesToBeWritten.take();
						System.out.println("message i got: " + messageToWrite);
						try {
							data.os.writeUTF(messageToWrite);
						} catch (IOException e) {
							e.printStackTrace();
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			//	writeChat(op);
				/*
				 * Close the output stream, close the input stream, close the socket.
				 */
				//closeChat();
//			} catch (IOException e) {
//				System.err.println("IOException:  " + e);
//			}
		}
	}
	
	
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

	private void writeChat(OpenClassroom op) throws IOException {
		while (!closed) {
			synchronized(op){
				try {
					op.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if(closed)
				break;
			String a = op.ei.getText();
			data.os.writeUTF(a);
		}
	}

	private void initializeSocketAndStreams(int portNumber, String host) {
		try {
			data.clientSocket = new Socket(host, portNumber);
			data.os = new DataOutputStream(data.clientSocket.getOutputStream());
			data.is = new DataInputStream(data.clientSocket.getInputStream());
			//catchAbnormalClose();
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host " + host);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to the host "
					+ host);
		}

	}


	private void catchAbnormalClose(){
		data.op.wc.catchClosingWindow(new WindowAdapter() {	 
	        @Override
	        public void windowClosing(WindowEvent e) {
	        	try {
					closeChat();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
	        	System.out.println("closed everything");
	        	System.exit(1);
	        }
	    });
	}


	
	private static class VirtuClassClientChatData {
		private Socket clientSocket;
		private DataOutputStream os;
		private DataInputStream is;
		private OpenClassroom op;
		private ArrayBlockingQueue<String> waitingMessagesToBeRead;
		private ArrayBlockingQueue<String> waitingMessagesToBeWritten;
		
		private VirtuClassClientChatData(Socket clientSocket,
				DataOutputStream os, DataInputStream is) {
			this.clientSocket = clientSocket;
			this.os = os;
			this.is = is;
		}
	}

}



