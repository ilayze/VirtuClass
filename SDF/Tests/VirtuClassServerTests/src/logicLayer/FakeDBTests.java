package logicLayer;

import static org.junit.Assert.*;

import org.junit.Test;

public class FakeDBTests {

	FakeDB db = FakeDB.getDB();
	@Test
	public void initTests() {
		
		assertEquals(-1, db.signIn("wrongUsr", "1234"));
		assertEquals(true, db.signUp("newUsr1", "12345"));
		assertEquals(0, db.signIn("newUsr1", "12345"));
		assertEquals(true, db.signUp("newUsr2", "3333"));
		assertEquals(1, db.signIn("newUsr2", "3333"));
		assertEquals(-1, db.signIn("newUsr2", "3332"));
		assertEquals(true, db.signUp("newUsr3", "12345"));
		assertEquals(false, db.signUp("newUsr2", "12345"));
		

		assertEquals(true, db.deleteUser("newUsr1"));
		assertEquals(false, db.deleteUser("newUsr1"));
		assertEquals(false, db.deleteUser("newUsr4"));
		assertEquals(true, db.deleteUser("newUsr3"));

	}
	
	
	

	
	
	@Test
	public void deletionTests()
	{

	}
}
