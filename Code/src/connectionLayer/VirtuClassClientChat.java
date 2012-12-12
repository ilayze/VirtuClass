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
		// TODO Auto-generated method stub

	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
