package connectionLayer;


import guiLayer.EditorFrame;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.locks.Lock;

import logicLayer.OpenClass;

public class VirtuClassClientChat implements Runnable {

	// The client socket
	private static Socket clientSocket = null;
	// The output stream
	private static PrintStream os = null;
	// The input stream
	private static DataInputStream is = null;

	private static BufferedReader inputLine = null;
	private static boolean closed = false;
	private OpenClass op;
	public String comingChat=""; 
	//private static EditorFrame editor;

	private String lastStringRead="";
	
	private VirtuClassClientChat(OpenClass op, int a)
	{
		this.op=op;
		
	}
	public VirtuClassClientChat(OpenClass op) {

		this.op=op;	
		
		// The default port.
		int portNumber = 8888;
		// The default host.
		String host = "localhost";



		/*
		 * Open a socket on a given host and port. Open input and output streams.
		 */
		try {
			clientSocket = new Socket(host, portNumber);
			inputLine = new BufferedReader(new InputStreamReader(System.in));
			os = new PrintStream(clientSocket.getOutputStream());
			is = new DataInputStream(clientSocket.getInputStream());
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host " + host);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to the host "
					+ host);
		}


		/*
		 * If everything has been initialized then we want to write some data to the
		 * socket we have opened a connection to on the port portNumber.
		 */
		if (clientSocket != null && os != null && is != null) {
			try {

				/* Create a thread to read from the server. */
				new Thread(new VirtuClassClientChat(op, 0)).start();
	
				while (!closed) {
		//			EditorFrame editor=EditorFrame.getEditor();
		//			String inputFromEditor = editor.getText();
					
				//	String input=inputLine.readLine().trim();
					//Thread t = new  Thread(new Runnable() {
						

					synchronized(op){
						try {
							op.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					String a = op.ei.getText();
					os.println(a);
					
				}
				/*
				 * Close the output stream, close the input stream, close the socket.
				 */
				os.close();
				is.close();
				clientSocket.close();
			} catch (IOException e) {
				System.err.println("IOException:  " + e);
			}
		}

	}
	
	private VirtuClassClientChat()
	{
	
	}
	/*
	 * Create a thread to read from the server. (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@SuppressWarnings("deprecation")
	public void run() {
		/*
		 * Keep on reading from the socket till we receive "Bye" from the
		 * server. Once we received that then we want to break.
		 */
		String responseLine;
		try {
			while ((responseLine = is.readLine()) != null) {
				//comingChat=responseLine;

				OpenClass op1=op;
				if(op1==null)
					System.out.println("OP IS NULL2");
				op.ei.setText(responseLine);
	//			lastStringRead+=responseLine+"\n";
//				System.out.println(responseLine);
//				EditorFrame editor = EditorFrame.getEditor();
//				if(editor==null)
//					System.out.println("editor is null!!");
//				else
//					editor.setText(responseLine);
//				if (responseLine.indexOf("*** Bye") != -1)
//					break;
			}
			closed = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}



