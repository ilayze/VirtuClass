package connectionLayer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.ArrayBlockingQueue;

import logicLayer.ClassroomsManager;
import logicLayer.User;

public class ServerChat extends Thread{
	private ClassroomsManager cm = new ClassroomsManager();
	private int portNumber = 8888;
	private ServerSocket serverSocket;
	private ArrayBlockingQueue<String> waitingUsers;
	private static final int maxClientsCount = 30;
	private LinkedList<ClientChatThread> threads = new LinkedList<ClientChatThread>();
	
	
	public ServerChat(ArrayBlockingQueue<String> waitingUsers)
	{
		this.waitingUsers=waitingUsers;
		try {
			serverSocket = new ServerSocket(portNumber,20);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	@Override
	public void run()
	{
		while(true)
		{
			String username=null;
			try {
				username = waitingUsers.take();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Iterator<ClientChatThread> i = threads.iterator();
			while (i.hasNext()) {
				ClientChatThread thread = i.next();
				if(!thread.isAlive())
					i.remove();
			}
			if(username!=null && threads.size()<maxClientsCount)
			{
				newUserLoggedIn(username);
			}
		}
	}
	
	private void newUserLoggedIn(String username)
	{
		System.out.println("got a new user: " + username);
		try {
			Socket clientSocket = serverSocket.accept();
			DataOutputStream os = new DataOutputStream(clientSocket.getOutputStream());
			DataInputStream is = new DataInputStream(clientSocket.getInputStream());
			ClientChatThread newThread =new ClientChatThread(username,clientSocket,os,is,cm);
			threads.add(newThread);
			newThread.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
