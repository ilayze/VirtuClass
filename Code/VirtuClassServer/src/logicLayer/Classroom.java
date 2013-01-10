package logicLayer;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

public class Classroom {
	private LinkedList<User> users;
	private String name = "Default Name";// also id of the class
	private User creator;

	public Classroom(User creator, String name) {
		if (creator == null)
			return;
		this.creator = creator;
		if (name != null)
			this.name = name;
		users = new LinkedList<User>();
		users.add(creator);
	}

	public User getCreator() {
		return creator;
	}
	
	public String getName()
	{
		return name;
	}

	public LinkedList<User> getUsers() {
		return users;
	}

	public int getNumberOfUsers() {
		return users.size();
	}

	public boolean deleteData() {
		boolean allUsersSuccessfullyQuit = true;
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
		return allUsersSuccessfullyQuit;
	}

	public boolean removeUser(User usr) throws IOException {
		if(usr==null)
			return false;
		if(users.size()>1 && creator.equals(usr))
			changeCreator();
		if (users.remove(usr)) {
			usr.quitChat();
			return true;
		}
		return false;
	}

	public boolean addUser(User usr) {
		if (usr != null && !users.contains(usr))
		{
			users.add(usr);
			return true;
		}
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
