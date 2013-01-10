package logicLayer;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

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
	public void getNumberOfUsers_NoUsersInClassroom_0() throws IOException{
		
		//Arrange
		User usr = new User("Tom",null,null,null);
		Classroom c = new Classroom(usr,null);
		
		//Act
		c.removeUser(usr);
		
		//Assert
		assertEquals(0,c.getNumberOfUsers());
	
	}
	
	@Test
	public void getNumberOfUsers_FiveDifferentUsersEntered_5()
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
	public void getNumberOfUsers_NullUsername_1()
	{
		
		//Arrange
		User usr = new User(null,null,null,null);
		Classroom c = new Classroom(usr,null);
		
		//Assert
		assertEquals(1,c.getNumberOfUsers());
		
	}
	
	@Test
	public void getNumberOfUsers_SameUsernames_1()
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
	
	@Test
	public void equals_ClassroomsWithDifferentNames_false()
	{
		
		//Arrange
		User usr = new User(null,null,null,null);
		Classroom c = new Classroom(usr,"Geometry");
		User usr2 = new User("Mushon",null,null,null);
		Classroom c2 = new Classroom(usr2,"Algebra");
				
		//Assert
		assertEquals(false,c.equals(c2));
		
	}
	
	@Test
	public void equals_DifferentObject_false()
	{
		
		//Arrange
		User usr = new User(null,null,null,null);
		Classroom c = new Classroom(usr,"Geometry");
				
		//Assert
		assertEquals(false,c.equals(usr));
		
	}
	
	@Test
	public void equals_nullObject_false()
	{
		
		//Arrange
		User usr = new User(null,null,null,null);
		Classroom c = new Classroom(usr,"Geometry");
				
		//Assert
		assertEquals(false,c.equals(null));
		
	}
	
	@Test
	public void removeUser_nullObject_false() throws IOException
	{
		
		//Arrange
		User usr = new User(null,null,null,null);
		Classroom c = new Classroom(usr,"Geometry");
				
		//Assert
		assertEquals(false,c.removeUser(null));
		
	}
	
	@Test
	public void removeUser_UserNotInClassroom_false() throws IOException
	{
		
		//Arrange
		User usr = new User("Guy",null,null,null);
		Classroom c = new Classroom(usr,"Geometry");
		User usr2 = new User("Gadi",null,null,null);
		
		//Assert
		assertEquals(false,c.removeUser(usr2));
		
	}
	
	@Test
	public void removeUser_UserInClassroom_true() throws IOException
	{
		
		//Arrange
		User usr = new User("Guy",null,null,null);
		Classroom c = new Classroom(usr,"Geometry");
		User usr2 = new User("Gadi",null,null,null);
		User usr3 = new User("Yosef",null,null,null);
		User usr4 = new User("Sason",null,null,null);
		
		//Act
		c.addUser(usr2);
		c.addUser(usr3);
		c.addUser(usr4);
		
		//Assert
		assertEquals(true,c.removeUser(usr2));
	}
	
	@Test
	public void deleteData_3UsersInClassroomBeforeDeletion_0UsersLeftInClassroom()
	{
		
		//Arrange
		User usr = new User("Guy",null,null,null);
		Classroom c = new Classroom(usr,"Geometry");
		User usr2 = new User("Gadi",null,null,null);
		User usr3 = new User("Moti",null,null,null);
		
		//Act
		c.addUser(usr2);
		c.addUser(usr3);
		c.deleteData();
		
		//Assert
		assertEquals(0,c.getNumberOfUsers());
	}
	
	@Test
	public void getCreator_GuyIsTheCreatorOfClassroom_CreatorIsGuy()
	{
		
		//Arrange
		User usr = new User("Guy",null,null,null);
		Classroom c = new Classroom(usr,"Geometry");
		User usr2 = new User("Gadi",null,null,null);
		User usr3 = new User("Moti",null,null,null);
		
		//Act
		c.addUser(usr2);
		c.addUser(usr3);
		
		//Assert
		assertEquals(usr,c.getCreator());
	}
	
	@Test
	public void getCreator_CreatorGuyLeftTheClassroomOfGuyAndMoshe_Moshe() throws IOException
	{
		
		//Arrange
		User usr = new User("Guy",null,null,null);
		Classroom c = new Classroom(usr,"Geometry");
		User usr2 = new User("Moshe",null,null,null);
	
		
		//Act
		c.addUser(usr2);
		c.removeUser(usr);
		
		//Assert
		assertEquals(usr2,c.getCreator());
	}


}
