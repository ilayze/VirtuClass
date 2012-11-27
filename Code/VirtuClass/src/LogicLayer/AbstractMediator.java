package LogicLayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author gilad baruchian
 *
 */
public abstract class AbstractMediator implements ActionListener{
	private static final String CONF_FILE_PATH = "\\Data\\UsrConf.dat";
	
	// This method will get an event, check where it came from, and handle it.
	protected abstract void handleEvent(ActionEvent e);
	// Handle the response from the connect() from the Sync.
	protected abstract void handleResponse(boolean response);
	// returns the password from CONF_FILE_PATH (this is a data file and not a string file).
	protected abstract String getUserPassword();
}
