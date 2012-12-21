package logicLayer;

import java.util.LinkedList;
import java.util.ListIterator;



public class FakeDB {
	private static LinkedList <FakeUsrData> list=null;
	private static FakeDB db=null;
	
	private FakeDB()
	{
		
	}
	public static FakeDB getDB()
	{
		if(db==null)
		{
			list = new LinkedList<FakeUsrData>();
			db = new FakeDB();
		}
		return db;
	}
	
	public boolean signUp(String username, String password)
	{
		if(username==null || password==null || username.length()==0 || password.length()==0)
			return false;
		ListIterator<FakeUsrData> itr;
		
		for(itr = list.listIterator(); itr.hasNext();)
			if(itr.next().equals(username))
				return false;	
		

		FakeUsrData newUsr = new FakeUsrData(username, password, 0);
		list.add(newUsr);
		return true;
	}
	public int signIn(String username, String password)
	{
		if(username==null || password==null || username.length()==0 || password.length()==0)
			return -1;
		FakeUsrData tmpUsr = new FakeUsrData(username, password, 0);
		ListIterator<FakeUsrData> itr;
		for(itr = list.listIterator(); itr.hasNext();)
		{
			if(itr.next().equals(tmpUsr))
			{
				return (itr.nextIndex()-1);
			}
		}
		return -1;
		
	}
	public boolean deleteUser(String username)
	{
		if(username==null  || username.length()==0)
			return false;
		ListIterator<FakeUsrData> itr;
		for(itr = list.listIterator(); itr.hasNext();)
		{
			if(itr.next().equals(username))
			{
				itr.remove();
				return true;
			}
		}
		return false;
		
	}
	
	public boolean addClass(String classname,String classcreator)
	{
		if(classname==null || classcreator==null || classname.length()==0 || classcreator.length()==0)
			return false;
		return true;
		
	}
	public boolean deleteClass(String classname)
	{
		if(classname==null || classname.length()==0)
			return false;
		return true;
		
	}
}
