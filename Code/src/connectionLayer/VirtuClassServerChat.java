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
		// TODO Auto-generated method stub

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
