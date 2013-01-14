package logicLayer;

import java.util.concurrent.ArrayBlockingQueue;

public class MsgFromServerReader implements Runnable {
	private EditorInfo ei;
	private ArrayBlockingQueue<String> readFromServerQueue;
	
	public MsgFromServerReader(ArrayBlockingQueue<String> readFromServerQueue,
			EditorInfo ei) {
		this.ei=ei;
		this.readFromServerQueue=readFromServerQueue;
	}

	@Override
	public void run() {
		while(true)
		{
			String newMessage="";
			try {
				newMessage=readFromServerQueue.take();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(newMessage.length()>0)
				ei.setText(newMessage);
		}
	}

}
