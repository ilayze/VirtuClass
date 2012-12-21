package logicLayer;

import static org.junit.Assert.*;

import org.junit.Test;

public class FakeDBTests {

	FakeDB db = FakeDB.getDB();
	@Test
	public void test() {
		
		assertEquals(-1, db.signIn("wrongUsr", "1234"));
	}

}
