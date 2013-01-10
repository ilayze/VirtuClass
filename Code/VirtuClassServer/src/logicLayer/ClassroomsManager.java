package logicLayer;

import java.util.LinkedList;

public class ClassroomsManager {
	LinkedList<Classroom> classes;
	
	public ClassroomsManager()
	{
		classes = new LinkedList<Classroom>();
	}
	
	public LinkedList<String> getClassesNames()
	{
		LinkedList<String> names = new LinkedList<String>();
		for(Classroom classroom:classes)
			names.add(classroom.getName());
		return names;
	}
	
	public boolean joinClassroom(String classroomName,User usr)
	{
		Classroom classroom = findRelevantClassroom(classroomName);
		if(classroom==null)
			return false;
		return classroom.addUser(usr);
	}
	
	public int getNumberOfClassrooms()
	{
		return classes.size();
	}

	public boolean createClassroom(String classroomName, User usr) {
		Classroom newClassroom = new Classroom(usr, classroomName);
		return addClassroom(newClassroom);
	}
	
	private boolean addClassroom(Classroom c)
	{
		if(c!=null && !classes.contains(c))
		{
			classes.add(c);
			return true;
		}
		return false;
	}
	
	private boolean removeClassroom(Classroom c)
	{
		
		if(classes.remove(c))
		{
			c.deleteData();
			return true;
		}
		return false;
	}
	
	private Classroom findRelevantClassroom(String classroomName)
	{
		User fakeUser = new User("Some name", null, null, null);
		Classroom fakeClassroom = new Classroom(fakeUser, classroomName);
		for(Classroom classroom : classes)
			if(classroom.equals(fakeClassroom))
				return classroom;
		return null;
	}
}
