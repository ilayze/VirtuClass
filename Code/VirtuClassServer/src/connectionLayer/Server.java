package connectionLayer;


import java.util.concurrent.ArrayBlockingQueue;




public class Server extends Thread{
	public static void main(String[] args)
	{
		new Thread(new Server()).start();
	}
	
	@Override
	public void run()
	{
		ArrayBlockingQueue<String> waitingUsers = new ArrayBlockingQueue<String>(10);
		new Thread(new ServerChat(waitingUsers)).start();
		new Thread(new ServerLogin(waitingUsers)).start();
		

	}


}
