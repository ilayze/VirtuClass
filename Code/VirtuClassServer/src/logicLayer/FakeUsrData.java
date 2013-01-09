package logicLayer;

public class FakeUsrData {
	private String username;
	private String password;
	private int permission;
	
	public FakeUsrData(String usr, String pwd, int permission)
	{
		username=usr;
		password=pwd;
		this.permission=permission;
	}
	
	public String getUsername()
	{
		return username;
	}
	public String getPassword()
	{
		return password;
	}
	public int getPermission()
	{
		return permission;
	}
	
	public boolean equals(Object obj)
	{
		if(obj instanceof FakeUsrData)
		{
			FakeUsrData usr= (FakeUsrData)(obj);
			return ((username.equals(usr.username))
					&& (password.equals(usr.password)));
		}
		else if(obj instanceof String)
		{
			String usr=(String)obj;
			return username.equals(usr);
		}
		return false;
	
	}
	
	public String toString()
	{
		return username;
		
	}
}
