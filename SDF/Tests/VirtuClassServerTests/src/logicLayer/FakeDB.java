package logicLayer;

import java.util.LinkedList;



public class FakeDB {
	private static LinkedList <String> list=null;
	private static FakeDB db=null;
	
	private FakeDB()
	{
		
	}
	public static FakeDB getDB()
	{
		if(db==null)
		{
			list = new LinkedList<String>();
			db = new FakeDB();
		}
		return db;
	}
	
	public boolean signUp(String username, String password, int permission)
	{
		
		return false;
	}
	public int signIn(String username)
	{
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
