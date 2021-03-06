package connectionLayer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;

/*
 * A chat server that delivers public and private messages.
 */

public class VirtuClassServerChat {


	// The server socket.
	private static ServerSocket serverSocket = null;
	// The client socket.
	private static Socket clientSocket = null;

	// This chat server can accept up to maxClientsCount clients' connections.
	private static final int maxClientsCount = 10;
	private static final clientThread[] threads = new clientThread[maxClientsCount];

	public static void main(String[] args) {

		// The default port number.
		int portNumber = 8888;
		if (args.length < 1) {
			System.out
			.println("Usage: java VirtuClassServerChat <portNumber>\n"
					+ "Now using port number=" + portNumber);
		} else {
			portNumber = Integer.valueOf(args[0]).intValue();
		}

		/*
		 * Open a server socket on the portNumber (default 2222). Note that we can
		 * not choose a port less than 1023 if we are not privileged users (root).
		 */
		try {
			serverSocket = new ServerSocket(portNumber);
		} catch (IOException e) {
			System.out.println(e);
		}


		/*
		 * Create a client socket for each connection and pass it to a new client
		 * thread.
		 */
		while (true) {
			try {
				clientSocket = serverSocket.accept();
				int i = 0;
				for (i = 0; i < maxClientsCount; i++) {
					if (threads[i] == null) {
						(threads[i] = new clientThread(clientSocket, threads)).start();
						break;
					}
				}
				if (i == maxClientsCount) {
					DataOutputStream os = new DataOutputStream(clientSocket.getOutputStream());
					os.writeUTF("Server too busy. Try later.");
					os.close();
					clientSocket.close();
				}
			} catch (IOException e) {
				System.out.println(e);
			}
		}
	}


}


/*
 * The chat client thread. This client thread opens the input and the output
 * streams for a particular client, ask the client's name, informs all the
 * clients connected to the server about the fact that a new client has joined
 * the chat room, and as long as it receive data, echos that data back to all
 * other clients. When a client leaves the chat room this thread informs also
 * all the clients about that and terminates.
 */
class clientThread extends Thread {

	private DataInputStream is = null;
	private DataOutputStream os = null;
	private Socket clientSocket = null;
	private final clientThread[] threads;
	private int maxClientsCount;

	public clientThread(Socket clientSocket, clientThread[] threads) {
		this.clientSocket = clientSocket;
		this.threads = threads;
		maxClientsCount = threads.length;
	}


	public void run() {
		int maxClientsCount = this.maxClientsCount;
		clientThread[] threads = this.threads;
		String name="";
		try {
			/*
			 * Create input and output streams for this client.
			 */
			is = new DataInputStream(clientSocket.getInputStream());
			os = new DataOutputStream(clientSocket.getOutputStream());
			os.writeUTF("Enter your name.");
			name = is.readUTF().trim();
			os.writeUTF("Hello " + name
					+ " to our chat room.\nTo leave enter /quit in a new line");
			for (int i = 0; i < maxClientsCount; i++) {
				if (threads[i] != null && threads[i] != this) {
					threads[i].os.writeUTF("*** A new user " + name
							+ " entered the chat room !!! ***");
				}
			}
			while (true) {
				String line = is.readUTF();
				//System.out.println("line read is: "+line);
				if (line.startsWith("/quit")) {
					break;
				}
				for (int i = 0; i < maxClientsCount; i++) {
					if (threads[i] != null) {
						threads[i].os.writeUTF("<" + name + "&VirtuClass; " + line);
					}
				}
			}
			os.writeUTF("*** Bye " + name + " ***");
			os.writeUTF("/quit");


			
		}catch (IOException e) {
			System.out.println("client exited abnormally");
			//e.printStackTrace();
		}finally{

			/*
			 * Clean up. Set the current thread variable to null so that a new client
			 * could be accepted by the server.
			 */
			for (int i = 0; i < maxClientsCount; i++) {
				if (threads[i] != null && threads[i] != this) {
					try {
						if(name.length()>0)
						threads[i].os.writeUTF("*** The user " + name
								+ " is leaving the chat room !!! ***");
					} catch (IOException e) {
						
						//e.printStackTrace();
						System.out.println("Another user has quitted in the meanwhile\nand therefore can't write to him as well.");
					}
				}
			}
			for (int i = 0; i < maxClientsCount; i++) {
				if (threads[i] == this) {
					threads[i] = null;
				}
			}
			Thread.currentThread().interrupt();
		}
		
		/*
		 * Close the output stream, close the input stream, close the socket.
		 */
		try {
			is.close();
			os.close();
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
	}

}

