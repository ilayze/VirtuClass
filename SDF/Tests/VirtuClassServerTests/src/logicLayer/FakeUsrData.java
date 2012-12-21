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
		if(!(obj instanceof FakeUsrData))
			return false;
		FakeUsrData usr= (FakeUsrData)(obj);
		return username.equals(usr.username);		
	}
}
