package LogicLayer;

public abstract class AbstractUser {
	private String _username;
	private boolean _isConnected;
	
	public String getUsername() {
		return _username;
	}
	public void setUsername(String username) {
		this._username = username;
	}
	public boolean isConnected() {
		return _isConnected;
	}
	public void setConnected(boolean isConnected) {
		this._isConnected = isConnected;
	}
	
	
}
