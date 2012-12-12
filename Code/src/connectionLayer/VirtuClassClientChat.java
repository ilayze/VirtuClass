package connectionLayer;


	import java.io.DataInputStream;
	import java.io.PrintStream;
	import java.io.BufferedReader;
	import java.io.InputStreamReader;
	import java.io.IOException;
	import java.net.Socket;
	import java.net.UnknownHostException;

	public class VirtuClassClientChat implements Runnable {

	  // The client socket
	  private static Socket clientSocket = null;
	  // The output stream
	  private static PrintStream os = null;
	  // The input stream
	  private static DataInputStream is = null;

	  private static BufferedReader inputLine = null;
	  private static boolean closed = false;
	  

	  public static void main(String[] args) {

		    // The default port.
		    int portNumber = 8888;
		    // The default host.
		    String host = "10.8.4.110";

		    if (args.length < 2) {
		      System.out
		          .println("Usage: java MultiThreadChatClient <host> <portNumber>\n"
		              + "Now using host=" + host + ", portNumber=" + portNumber);
		    } else {
		      host = args[0];
		      portNumber = Integer.valueOf(args[1]).intValue();
		    }
	  }
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
