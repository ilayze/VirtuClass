package logicLayer;

import java.util.LinkedList;



public class FakeDB {
	private static LinkedList <String> list;
	
	private FakeDB()
	{
		list=null;
	}
	public static void initDB()
	{
		if(list==null)
			list = new LinkedList<String>();
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
