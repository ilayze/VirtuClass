package connectionLayer;

import java.io.DataInputStream;
import java.io.PrintStream;
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
	          .println("Usage: java MultiThreadChatServer <portNumber>\n"
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


	}
	class clientThread extends Thread {

		  private DataInputStream is = null;
		  private PrintStream os = null;
		  private Socket clientSocket = null;
		  private final clientThread[] threads;
		  private int maxClientsCount;
		  
		  public clientThread(Socket clientSocket, clientThread[] threads) {
			    this.clientSocket = clientSocket;
			    this.threads = threads;
			    maxClientsCount = threads.length;
			  }

	}
}
