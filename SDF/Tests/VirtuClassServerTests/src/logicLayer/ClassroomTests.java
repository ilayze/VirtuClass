package logicLayer;

import static org.junit.Assert.*;

import org.junit.Test;

public class ClassroomTests {

	@Test
	public void getCreator_ClassCreatorIsTom_Tom() {
		
		//Arrange
		User usr = new User("Tom",null,null,null);
		Classroom c = new Classroom(usr,null);
		
		//Assert
		assertEquals(usr,c.getCreator());
	
	}
	
	@Test
	public void getNumberOfUsers_NoUsersInClassroom_0() {
		
		//Arrange
		User usr = new User("Tom",null,null,null);
		Classroom c = new Classroom(usr,null);
		
		//Act
		c.removeUser(usr);
		
		//Assert
		assertEquals(0,c.getNumberOfUsers());
	
	}
	
	@Test
	public void getNumberOfUsers_FiveUsersEntered_5()
	{
		
		//Arrange
		User usr = new User("Tom",null,null,null);
		User usr2 = new User("Mushon",null,null,null);
		User usr3 = new User("Rami",null,null,null);
		User usr4 = new User("Shimshon",null,null,null);
		User usr5 = new User("Gabi",null,null,null);
		Classroom c = new Classroom(usr,null);
		
		//Act
		c.addUser(usr2);
		c.addUser(usr3);
		c.addUser(usr4);
		c.addUser(usr5);
		
		//Assert
		assertEquals(5,c.getNumberOfUsers());
		
	}
	
	@Test
	public void getNumberOfUsers_ThreeSameUsers_1()
	{
		
		//Arrange
		User usr = new User("Tom",null,null,null);
		User usr2 = new User("Tom",null,null,null);
		User usr3 = new User("Tom",null,null,null);
		Classroom c = new Classroom(usr,null);
		
		//Act
		c.addUser(usr2);
		c.addUser(usr3);
		
		//Assert
		assertEquals(1,c.getNumberOfUsers());
		
	}
	 
	@Test
	public void getNumberOfUsers_NullUsernames_1()
	{
		
		//Arrange
		User usr = new User(null,null,null,null);
		User usr2 = new User(null,null,null,null);
		User usr3 = new User(null,null,null,null);
		Classroom c = new Classroom(usr,null);
		
		//Act
		c.addUser(usr2);
		c.addUser(usr3);
		
		//Assert
		assertEquals(1,c.getNumberOfUsers());
		
	}
	 	
	@Test
	public void getNumberOfUsers_NullUsername_1()
	{
		
		//Arrange
		User usr = new User(null,null,null,null);
		Classroom c = new Classroom(usr,null);
		
		//Assert
		assertEquals(1,c.getNumberOfUsers());
		
	}
	
	@Test
	public void getNumberOfUsers_NullUsersExceptCreator_1()
	{
		
		//Arrange
		User usr = new User(null,null,null,null);
		Classroom c = new Classroom(usr,null);
		
		//Act
		c.addUser(null);
		c.addUser(null);
				
		//Assert
		assertEquals(1,c.getNumberOfUsers());
		
	}

	@Test
	public void equals_ClassroomsWithSameNames_true()
	{
		
		//Arrange
		User usr = new User(null,null,null,null);
		Classroom c = new Classroom(usr,"Geometry");
		User usr2 = new User("Mushon",null,null,null);
		Classroom c2 = new Classroom(usr2,"Geometry");
				
		//Assert
		assertEquals(true,c.equals(c2));
		
	}

}
