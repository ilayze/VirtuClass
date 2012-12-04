package connectionLayer;

import java.net.Socket;

import LogicLayer.AbstractClass;

/**
 * @author gilad baruchian
 *
 */
public abstract class AbstractSyncronizer {
	private Socket _socket;
	
	// Connects to the DB software using the user name and pass.
	// returns ture/false if connection was successful.
	public abstract boolean Connect(String userName, String password);
	// Returns an array of open classes.
	public abstract AbstractClass[] getOpenClasses();

}
