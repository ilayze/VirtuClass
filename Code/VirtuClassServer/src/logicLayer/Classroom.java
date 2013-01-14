package logicLayer;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class Classroom {
	private LinkedList<User> users;
	private String name = "Default Name";// also id of the class
	private User creator;
	private Semaphore sem = new Semaphore(1);

	public Classroom(User creator, String name) {
		if (creator == null)
			return;
		this.creator = creator;
		if (name != null)
			this.name = name;
		users = new LinkedList<User>();
		users.add(creator);
	}

	/**
	 * Get the creator of this classroom.
	 * One user may be the creator of many classrooms.
	 * @return the creator of this classroom
	 */
	public User getCreator() {
		return creator;
	}

	/**
	 * <pre>
	 * get the name of this classroom.
	 * Every classroom has a unique name.
	 * </pre>
	 * @return the creator of this classroom.
	 */
	public String getName()
	{
		return name;
	}

	public LinkedList<DataOutputStream> getUsersOutputStreams() {
		LinkedList<DataOutputStream> outList = new LinkedList<DataOutputStream>();
		try {
			sem.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for(User user : users)
		{
			outList.add(user.getOutputStream());
		}
		if(sem.availablePermits()==0)
			sem.release();
		return outList;
	}

	public int getNumberOfUsers() {
		int size;
		try {
			sem.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		size = users.size();
		if(sem.availablePermits()==0)
			sem.release();
		return size;
	}

	public boolean deleteData() {
		boolean allUsersSuccessfullyQuit = true;
		try {
			sem.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Iterator<User> i = users.iterator();
		while (i.hasNext()) {
			User user = i.next();
			i.remove();
			try {
				user.quitChat();
			} catch (IOException e) {
				allUsersSuccessfullyQuit = false;
			}
		}
		if(sem.availablePermits()==0)
			sem.release();
		return allUsersSuccessfullyQuit;
	}

	public boolean removeUser(User usr){//throws IOException
		if(usr==null)
			return false;
		try {
			sem.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(users.size()>1 && creator.equals(usr))
			changeCreator();
		if (users.remove(usr)) {	//no need to remove completely a user if one only left the classroom!
//			try{
//			usr.quitChat();
//			}
//			catch(IOException e)
//			{
//				throw e;
//			}
//			finally
//			{

//			}
			if(sem.availablePermits()==0)
				sem.release();
			return true;
		}
		if(sem.availablePermits()==0)
			sem.release();
		return false;
	}

	public boolean addUser(User usr) {
		try {
			sem.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (usr != null && !users.contains(usr))
		{
			users.add(usr);
			if(sem.availablePermits()==0)
				sem.release();
			return true;
		}
		if(sem.availablePermits()==0)
			sem.release();
		return false;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Classroom) {
			Classroom otherClassroom = (Classroom) obj;
			if (otherClassroom.name.equals(name))
				return true;
		}
		return false;
	}
	
	private void changeCreator()
	{
		for(User usr1:users)
		{
			if(!usr1.equals(creator))
			{
				creator=usr1;
				break;
			}
		}

	}

}















































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































