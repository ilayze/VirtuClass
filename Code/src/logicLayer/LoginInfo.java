package logicLayer;

public final class LoginInfo {
	private String userName;
	private String password;
	
	public LoginInfo(String userName,String password)
	{
		this.userName=userName;
		this.password=password;
	}
	
	public String getUserName()
	{
		return userName;
	}
	
	public String getPassword()
	{
		return password;
	}
	

}
