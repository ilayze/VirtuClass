package logicLayer;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class ClassroomsManager {
	LinkedList<Classroom> classes;
	Semaphore sem = new Semaphore(1);
	
	public ClassroomsManager()
	{
		classes = new LinkedList<Classroom>();
	}
	
	public String getClassesNames()
	{
		String classesNames="";
		try {
			sem.acquire();
			System.out.println("In get classes names sem");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for(Classroom classroom:classes)
			classesNames+=(classroom.getName()+":");
		if(sem.availablePermits()==0)
			sem.release();
		System.out.println("released sem in get classes names");
		return classesNames;
	}
	
	public boolean joinClassroom(String classroomName,User usr)
	{
		Classroom classroom = findRelevantClassroom(classroomName);
		if(classroom==null)
			return false;
		return classroom.addUser(usr);
	}

	public boolean leaveClassrooms(User usr)
	{
		boolean leftSuccessfully=true;
		try {
			sem.acquire();
			System.out.println("In get leave classrooms sem");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Iterator<Classroom> i = classes.iterator();
		while (i.hasNext()) {
			System.out.println("aaa");
			Classroom classroom = i.next();
			System.out.println("bbb. class name is: "+ classroom.getName());

			removeUsrFromClassroom(classroom, usr,i);
			System.out.println("ccc");

		}
		if(sem.availablePermits()==0)
			sem.release();
		System.out.println("released sem in leave classrooms");
		try {
			usr.quitChat();
		} catch (IOException e) {
			e.printStackTrace();
			leftSuccessfully=false;
		}
		return leftSuccessfully;
	}
	
	public boolean leaveClassroom(String classroomName, User usr) throws ClassroomNotDeletedSuccessfullyException
	{
		Classroom classroom = findRelevantClassroom(classroomName);
		if(classroom==null)
			return false;
		return removeUsrFromClassroom(classroom,usr);

	}
	
	private boolean removeUsrFromClassroom(Classroom classroom, User usr) throws ClassroomNotDeletedSuccessfullyException {
		System.out.println("ccc3");
		if(!tryToRemoveUsrFromClassroom(classroom, usr))
			return false;
		System.out.println("ccc4");
		if(classroom.getNumberOfUsers()==0)
			removeClassroom(classroom);
		System.out.println("ccc5");
		return true;
	}
	
	private boolean removeUsrFromClassroom(Classroom classroom, User usr,Iterator<Classroom> i){
		System.out.println("ccc3");
		if(!tryToRemoveUsrFromClassroom(classroom, usr))
			return false;
		System.out.println("ccc4");
		if(classroom.getNumberOfUsers()==0)
			i.remove();
		System.out.println("ccc5");
		return true;
	}

	public int getNumberOfClassrooms()
	{
		try {
			sem.acquire();
			System.out.println("In get num of classes sem");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		int size = classes.size();
		if(sem.availablePermits()==0)
			sem.release();
		System.out.println("released sem in get get num of classrooms");
		return size;
	}

	public boolean createClassroom(String classroomName, User usr) {
		if(usr==null)
			return false;
		Classroom newClassroom = new Classroom(usr, classroomName);
		return addClassroom(newClassroom);
	}
	
	public LinkedList<DataOutputStream> getClientsOutputStreams(String classroomName)
	{
		Classroom classroom = findRelevantClassroom(classroomName);
		if(classroom==null)
			return null;
		return classroom.getUsersOutputStreams();
	}
	
	
	private boolean addClassroom(Classroom c)
	{
		try {
			sem.acquire();
			System.out.println("In add classroom sem");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(c!=null && !classes.contains(c))
		{
			classes.add(c);
			if(sem.availablePermits()==0)
				sem.release();
			System.out.println("released sem in add classroom");
			return true;
		}
		if(sem.availablePermits()==0)
			sem.release();
		System.out.println("released sem in add classroom");
		return false;
	}
	
	private void removeClassroom(Classroom c) throws ClassroomNotDeletedSuccessfullyException
	{
		ClassroomNotDeletedSuccessfullyException e=null;
//		try {
//			sem.acquire();
//			System.out.println("In remove classroom sem");
//		} catch (InterruptedException e2) {
//			e2.printStackTrace();
//		}
		if(!classes.remove(c))
			e = new ClassroomNotDeletedSuccessfullyException();
//		if(sem.availablePermits()==0)
//			sem.release();
//		System.out.println("released sem in remove classroom");
		if(e!=null)
			throw e;		
	}
	
	private Classroom findRelevantClassroom(String classroomName)
	{
		User fakeUser = new User("Some name", null, null, null);
		Classroom fakeClassroom = new Classroom(fakeUser, classroomName);
		try {
			sem.acquire();
			System.out.println("In findRelevant sem");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for(Classroom classroom : classes)
			if(classroom.equals(fakeClassroom))
			{
				if(sem.availablePermits()==0)
					sem.release();
				System.out.println("released sem in find relevant classroom");
				return classroom;
			}
		if(sem.availablePermits()==0)
			sem.release();
		System.out.println("released sem in find relevant classroom");
		return null;
	}
	
	private boolean tryToRemoveUsrFromClassroom(Classroom classroom, User usr)
	{
//		try {
			if(!classroom.removeUser(usr))
				return false;
//		} catch (IOException e) {
//			return false;
//		}
		return true;
	}
}
