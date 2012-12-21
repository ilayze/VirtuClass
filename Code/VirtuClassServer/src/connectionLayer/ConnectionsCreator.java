//The server code Server.java:
 
package connectionLayer;

import java.io.*;
import java.net.*;
 
/**
* This is to help people to write Client server application
*  I tried to make it as simple as possible... the client connect to the server
*  the client send a String to the server the server returns it in UPPERCASE thats all
*/
public class ConnectionsCreator {
 
        // the socket used by the server
        private ServerSocket serverSocket;
        // server constructor
        ConnectionsCreator(int port) {
               
                /* create socket server and wait for connection requests */
                try
                {
                        serverSocket = new ServerSocket(port);
                        System.out.println("Server waiting for client on port " + serverSocket.getLocalPort());
 
                        while(true)
                        {
                                Socket socket = serverSocket.accept();  // accept connection
                                System.out.println("New client asked for a connection");
                                TcpThread t = new TcpThread(socket);    // make a thread of it
                                System.out.println("Starting a thread for a new Client");
                                t.start();
                        }
                }
                catch (IOException e) {
                        System.out.println("Exception on new ServerSocket: " + e);
                }
        }               
 
//        you must "run" server to have the server run as a console application
        public static void main(String[] arg) {
                // start server on port 1500
                new ConnectionsCreator(8500);
        }
       
        /** One instance of this thread will run for each client */
        class TcpThread extends Thread {
                // the socket where to listen/talk
                Socket socket;
                ObjectInputStream Sinput;
                ObjectOutputStream Soutput;
               
                TcpThread(Socket socket) {
                        this.socket = socket;
                }
                public void run() {
                        /* Creating both Data Stream */
                        System.out.println("Thread trying to create Object Input/Output Streams");
                        try
                        {
                                // create output first
                                Soutput = new ObjectOutputStream(socket.getOutputStream());
                                Soutput.flush();
                                Sinput  = new ObjectInputStream(socket.getInputStream());
                        }
                        catch (IOException e) {
                                System.out.println("Exception creating new Input/output Streams: " + e);
                                return;
                        }
                        System.out.println("Thread waiting for a String from the Client");
                        // read a String (which is an object)
                        try {
                                String username = (String) Sinput.readObject();
                                String password = (String) Sinput.readObject();
                                //str = str.toUpperCase();
                                Soutput.writeObject(username + " OK");
                                Soutput.writeObject(password + " OK");
                                Soutput.flush();
                        }
                        catch (IOException e) {
                                System.out.println("Exception reading/writing  Streams: " + e);
                                return;                               
                        }
                        // will surely not happen with a String
                        catch (ClassNotFoundException o) {                               
                        }
                        finally {
                                try {
                                        Soutput.close();
                                        Sinput.close();
                                }
                                catch (Exception e) {                                       
                                }
                        }
                }
        }
}
 
 