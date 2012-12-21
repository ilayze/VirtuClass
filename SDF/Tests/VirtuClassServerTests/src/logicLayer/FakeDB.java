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
	
	public boolean signUp(String username, String password, int permission)
	{
		ListIterator<FakeUsrData> itr;
		
		for(itr = list.listIterator(); itr.hasNext();)
			if(itr.next().equals(username))
				return false;	
		

		FakeUsrData newUsr = new FakeUsrData(username, password, permission);
		list.add(newUsr);
		return true;
	}
	public int signIn(String username, String password)
	{
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
		return false;
		
	}
	
	public boolean addClass(String classname,String classcreator)
	{
		return false;
		
	}
	public boolean deleteClass(String classname)
	{
		return false;
		
	}
}
