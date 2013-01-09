package logicLayer;

import java.util.LinkedList;

public class Classroom {
	private LinkedList<User> users;
	private String name="Default Name";// also id of the class
	private User creator;
	
	public Classroom(User creator, String name)
	{
		if(creator==null)
			return;
		this.creator=creator;
		if(name!=null)
			this.name=name;
		users = new LinkedList<User>();
		users.add(creator);
	}

	public User getCreator() {
		return creator;
	}

	public LinkedList<User> getUsers() {
		return users;
	}
	
	public int getNumberOfUsers()
	{
		return users.size();
	}

	public void removeUser(User usr) {
		users.remove(usr);
	}

	public void addUser(User usr) {
		if(usr!=null && !users.contains(usr))
			users.add(usr);
	}
	
	public boolean equals(Object obj)
	{
		if(obj instanceof Classroom)
		{
			Classroom otherClassroom = (Classroom)obj;
			if(otherClassroom.name.equals(name))
				return true;
		}
		return false;
	}
	
}
