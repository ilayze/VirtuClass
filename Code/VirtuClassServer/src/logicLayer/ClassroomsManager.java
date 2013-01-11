package logicLayer;

import java.io.IOException;
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
	
	public boolean leaveClassroom(String classroomName, User usr) throws ClassroomNotDeletedSuccessfullyException
	{
		Classroom classroom = findRelevantClassroom(classroomName);
		if(classroom==null)
			return false;
		if(!tryToRemoveUsrFromClassroom(classroom, usr))
			return false;
		if(classroom.getNumberOfUsers()==0)
			removeClassroom(classroom);
		return true;
	}
	
	public int getNumberOfClassrooms()
	{
		return classes.size();
	}

	public boolean createClassroom(String classroomName, User usr) {
		if(usr==null)
			return false;
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
	
	private void removeClassroom(Classroom c) throws ClassroomNotDeletedSuccessfullyException
	{
		
		if(!classes.remove(c))
			throw new ClassroomNotDeletedSuccessfullyException();
			
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
	
	private boolean tryToRemoveUsrFromClassroom(Classroom classroom, User usr)
	{
		try {
			if(!classroom.removeUser(usr))
				return false;
		} catch (IOException e) {
			return false;
		}
		return true;
	}
}
