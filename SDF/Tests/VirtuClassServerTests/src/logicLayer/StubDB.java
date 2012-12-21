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

	public boolean signUp(String username, String password)
	{
		if(username!=null && password!=null && username.length()>0 && password.length()>0)
			return true;
		return false;
	}
	public int signIn(String username)
	{
		if(username!=null && username.length()>0)
			return 0;
		return -1;

	}
	public boolean deleteUser(String username)
	{
		if(username!=null && username.length()>0)
			return true;
		return false;

	}

	public boolean addClass(String classname,String classcreator)
	{
		if(classname!=null && classcreator!=null && classname.length()>0 && classcreator.length()>0)
			return true;
		return false;

	}
	public boolean deleteClass(String classname)
	{
		if(classname!=null && classname.length()>0)
			return true;
		return false;

	}
}
