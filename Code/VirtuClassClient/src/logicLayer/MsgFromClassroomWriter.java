package logicLayer;

import java.util.concurrent.ArrayBlockingQueue;

public class MsgFromClassroomWriter implements Runnable {
	private String nameOfClassroom;
	private ArrayBlockingQueue<String> writeToServerQueue;
	private String msgToWrite;
	private String username;
	
	public MsgFromClassroomWriter(String name, String toWrite,
			ArrayBlockingQueue<String> writeToServerQueue, String username) {
		this.nameOfClassroom=name;
		this.writeToServerQueue=writeToServerQueue;
		msgToWrite=toWrite;
		this.username=username;
	}

	@Override
	public void run() {
		try {
			writeToServerQueue.put("3"+nameOfClassroom+"<"+username+"@VirtuClass>: " +msgToWrite);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	

}
