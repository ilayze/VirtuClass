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

		try {
			con = DriverManager.getConnection(url, user, password);
			st = con.createStatement();
		} catch (SQLException ex) {
			lgr = Logger.getLogger(SqlControl.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);

		}
	}
	

	// **************************************************************************8
	public boolean signUp(String username, String password, int permission) {

		try {
			st.executeUpdate("INSERT INTO virtuclass.users (`username`, `userpassword`,`permission`) VALUES ('"
					+ username + "', '" + password + "','" + permission + "');");
			return true;
		} catch (SQLException ex) {
			lgr = Logger.getLogger(SqlControl.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
			return false;
		}
	}
	

	// ************************************************************************************************
	public int signIn(String username) {
		ResultSet rs1 = null;
		try {
			rs1 = st.executeQuery("SELECT * FROM virtuclass.users WHERE username = '"
					+ username + "' ;");
			while (rs1.next()) {
				if (rs1.getString(1).equals(username)) {
					return rs1.getInt(3);
				}
			}
			return -1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}
	

	// *************************************************************************************************
	
	public boolean deleteUser(String username)
	{
		try {
			st.executeUpdate("DELETE FROM virtuclass.users WHERE `username`='"+username+"'");
			return true;
		} catch (SQLException ex) {
			lgr = Logger.getLogger(SqlControl.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
			return false;
		}
	}


}
