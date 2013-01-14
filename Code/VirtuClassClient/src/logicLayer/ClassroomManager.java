package logicLayer;

import java.util.LinkedList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Semaphore;

public class ClassroomManager {
	LinkedList<ClassroomInfo> l = new LinkedList<ClassroomInfo>();
	Semaphore sem = new Semaphore(1);
	ArrayBlockingQueue<String> writeQueue;
	
	public ClassroomManager(ArrayBlockingQueue<String> writeQueue)
	{
		this.writeQueue=writeQueue;
	}
	
	public int getNumberOfOpenClassrooms()
	{
		return l.size();
	}
	
	
	public void openClassroom(String classroomName, String username)
	{
		try {
			sem.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
			return;
		}
		if(classroomNotOpened(classroomName))
		{
			addNewClassroom(classroomName, username);
		}
		sem.release();
	}
	
	private void addNewClassroom(String classroomName,String username) {
		ArrayBlockingQueue<String> a = new ArrayBlockingQueue<String>(20);
		l.add(new ClassroomInfo(classroomName, a));
		new Thread(new OpenClassMediator(username, classroomName, a,writeQueue)).start();
	}

	private boolean classroomNotOpened(String classroomName)
	{
		for(ClassroomInfo i : l)
		{
			if(i.getName().equals(classroomName))
				return false;
		}
		return true;
	}
	
	public void updateTheRightReadQueue(String messageGiven)
	{
		int classroomGivenNameLength =messageGiven.indexOf(':');
		if(classroomGivenNameLength>12 || classroomGivenNameLength<0)
			classroomGivenNameLength=12;
		String classroomNameGiven = messageGiven.substring(0, classroomGivenNameLength);
		try {
			sem.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
			return;
		}
		for(ClassroomInfo i : l)
		{
			if(i.getName().equals(classroomNameGiven))//found the class the message belongs to
			{
				i.addToQueue(messageGiven.substring(12));
				break;
			}
		}
		sem.release();
	}
}
