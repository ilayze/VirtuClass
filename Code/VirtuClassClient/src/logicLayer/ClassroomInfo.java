package logicLayer;

import java.util.concurrent.ArrayBlockingQueue;

public class ClassroomInfo {
	private String nameOfClassroom;
	private ArrayBlockingQueue<String> classroomQueue;
	
	public ClassroomInfo(String name, ArrayBlockingQueue<String> classroomQueue)
	{
		nameOfClassroom = name;
		this.classroomQueue=classroomQueue;
	}
	
	public String getName()
	{
		return nameOfClassroom;
	}
	
	public boolean addToQueue(String newMessage)
	{
		try {
			classroomQueue.put(newMessage);
			return true;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public String getFromQueue()
	{
		String messageToTake="";
		try {
			messageToTake=classroomQueue.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return messageToTake;
	}
	
	
}
