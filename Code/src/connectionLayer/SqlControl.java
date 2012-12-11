package connectionLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.management.Query;

//******************************************************************
public class SqlControl {
	Connection con = null;
	Statement st = null;
	Logger lgr;

	public SqlControl(String ip, String port, String database, String user1,
			String password1) {

		String url = "jdbc:mysql://" + ip + ":" + port + "/" + database;
		String user = user1;
		String password = password1;
	}
		
}
