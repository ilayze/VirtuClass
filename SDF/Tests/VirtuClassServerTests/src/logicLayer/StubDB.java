package logicLayer;


public class StubDB {
	
	private static StubDB db=null;
	
	private StubDB()
	{
		
	}
	public static StubDB getDB()
	{
		if(db==null)
		{
			db = new StubDB();
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
