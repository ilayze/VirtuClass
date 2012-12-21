package logicLayer;

import static org.junit.Assert.*;

import org.junit.Test;

public class FakeDBTests {

	FakeDB db = FakeDB.getDB();
	@Test
	public void test() {
		
		assertEquals(-1, db.signIn("wrongUsr", "1234"));
		assertEquals(true, db.signUp("newUsr1", "12345",0));
		//assertEquals(0, db.signIn("newUsr1", "12345"));
	}

}
