package logicLayer;

import static org.junit.Assert.*;

import org.junit.Test;

public class ClassroomsManagerTests {

	
	@Test
	public void getNumberOfClassrooms_ThreeClassroomsOpened_3() throws ClassroomNotDeletedSuccessfullyException
	{
		//Arrange
		ClassroomsManager cm = new ClassroomsManager();
		User usr1 = new User("Ben",null,null,null);
		User usr2 = new User("Guy",null,null,null);
		
		//Act
		cm.createClassroom("Algebra", usr1);
		cm.createClassroom("Geometry", usr2);
		cm.createClassroom("Trigonometry", usr1);
		
		//Assert
		assertEquals(3,cm.getNumberOfClassrooms());
	}
	
	@Test
	public void getNumberOfClassrooms_AllUsersLeftTheClassroom_0() throws ClassroomNotDeletedSuccessfullyException
	{
		//Arrange
		ClassroomsManager cm = new ClassroomsManager();
		User usr1 = new User("Ben",null,null,null);
		User usr2 = new User("Guy",null,null,null);
		
		//Act
		cm.createClassroom("Algebra", usr1);
		cm.joinClassroom("Algebra", usr2);
		cm.leaveClassroom("Algebra", usr1);
		cm.leaveClassroom("Algebra", usr2);
		
		//Assert
		assertEquals(0,cm.getNumberOfClassrooms());
	}
	
	@Test
	public void leaveClassroom_UserIsInClassroom_true() throws ClassroomNotDeletedSuccessfullyException
	{
		//Arrange
		ClassroomsManager cm = new ClassroomsManager();
		User usr1 = new User("Ben",null,null,null);
		User usr2 = new User("Guy",null,null,null);
		
		//Act
		cm.createClassroom("Algebra", usr1);
		cm.joinClassroom("Algebra", usr2);
		
		//Assert
		assertEquals(true, cm.leaveClassroom("Algebra", usr2));
	}
	
	@Test
	public void leaveClassroom_CreatorIsLeavingClassroom_true() throws ClassroomNotDeletedSuccessfullyException
	{
		//Arrange
		ClassroomsManager cm = new ClassroomsManager();
		User usr1 = new User("Ben",null,null,null);
		
		//Act
		cm.createClassroom("Algebra", usr1);
		
		//Assert
		assertEquals(true, cm.leaveClassroom("Algebra", usr1));
	}
	
	@Test
	public void leaveClassroom_UserIsntInClassroom_false() throws ClassroomNotDeletedSuccessfullyException
	{
		//Arrange
		ClassroomsManager cm = new ClassroomsManager();
		User usr1 = new User("Ben",null,null,null);
		User usr2 = new User("Guy",null,null,null);
		
		//Act
		cm.createClassroom("Algebra", usr1);
		cm.createClassroom("Geometry", usr2);
		
		//Assert
		assertEquals(false, cm.leaveClassroom("Algebra", usr2));
	}
	
	@Test
	public void leaveClassroom_NoSuchOpenClassroomName_false() throws ClassroomNotDeletedSuccessfullyException
	{
		//Arrange
		ClassroomsManager cm = new ClassroomsManager();
		User usr1 = new User(null,null,null,null);
		
		//Assert
		assertEquals(false, cm.leaveClassroom("Algebra", usr1));
	}
	
	@Test
	public void leaveClassroom_UserIsNullAndClassroomExists_false() throws ClassroomNotDeletedSuccessfullyException
	{
		//Arrange
		ClassroomsManager cm = new ClassroomsManager();
		User usr1 = new User(null,null,null,null);
		
		//Act
		cm.createClassroom("Algebra", usr1);
		
		//Assert
		assertEquals(false, cm.leaveClassroom("Algebra", null));
	}
	
	@Test
	public void leaveClassroom_UserIsNull_false() throws ClassroomNotDeletedSuccessfullyException
	{
		//Arrange
		ClassroomsManager cm = new ClassroomsManager();
		
		//Act
		cm.createClassroom("Algebra", null);
		
		//Assert
		assertEquals(false, cm.leaveClassroom("Algebra", null));
	}
	
	
	@Test
	public void joinClassroom_UserIsNull_false()
	{
		//Arrange
		ClassroomsManager cm = new ClassroomsManager();
		User usr1 = new User("Ben",null,null,null);
		
		//Act
		cm.createClassroom("Algebra", usr1);
		
		//Assert
		assertEquals(false, cm.joinClassroom("Algebra", null));
	}
	
	@Test
	public void joinClassroom_ThereIsAnOpenClassroomWithSuchNameButThisUserIsInTheClassroom_false()
	{
		//Arrange
		ClassroomsManager cm = new ClassroomsManager();
		User usr1 = new User("Ben", null, null, null);
		
		//Act
		cm.createClassroom("Algebra", usr1);
		cm.createClassroom("Geometry", usr1);
		
		
		//Assert
		assertEquals(false, cm.joinClassroom("Algebra", usr1));
	}
	
	@Test
	public void joinClassroom_ThereIsAnOpenClassroomWithSuchNameAndThisUserIsntInTheClassroom_true()
	{
		//Arrange
		ClassroomsManager cm = new ClassroomsManager();
		User usr1 = new User("Ben", null, null, null);
		User usr2 = new User("Guy",null,null,null);
		
		//Act
		cm.createClassroom("Algebra", usr1);
		cm.createClassroom("Geometry", usr1);
		
		//Assert
		assertEquals(true, cm.joinClassroom("Algebra", usr2));
	}
	
	
	@Test
	public void joinClassroom_NoOpenClassroomsWithSuchName_false()
	{
		//Arrange
		ClassroomsManager cm = new ClassroomsManager();
		User usr1 = new User("Ben", null, null, null);
		
		//Act
		cm.createClassroom("Algebra", usr1);
		cm.createClassroom("Geometry", usr1);
		
		//Assert
		assertEquals(false, cm.joinClassroom("Trigonometry", usr1));
	}
	
	@Test
	public void joinClassroom_NoOpenClassrooms_false()
	{
		//Arrange
		ClassroomsManager cm = new ClassroomsManager();
		User usr1 = new User("Ben", null, null, null);
		
			
		//Assert
		assertEquals(false, cm.joinClassroom("Algebra", usr1));
	}
	
	@Test
	public void createClassroom_UserIsNull_false()
	{
		//Arrange
		ClassroomsManager cm = new ClassroomsManager();
		
		//Assert
		assertEquals(false, cm.createClassroom("Algebra", null));
	}
	
	@Test
	public void createClassroom_NoOpenClassroomsWithSuchName_true()
	{
		//Arrange
		ClassroomsManager cm = new ClassroomsManager();
		User usr1 = new User("Ben", null, null, null);
		boolean firstCreationSucceed=false, secondCreationSucceed=false, thirdCreationSucceed=false;
		
		//Act
		firstCreationSucceed = cm.createClassroom("Algebra", usr1);
		secondCreationSucceed = cm.createClassroom("Geometry", usr1);
		thirdCreationSucceed = cm.createClassroom("Calculus", usr1);
		
		//Assert
		assertEquals(true, firstCreationSucceed);
		assertEquals(true, secondCreationSucceed);
		assertEquals(true, thirdCreationSucceed);
	}
	
	@Test
	public void createClassroom_ClassroomNameExists_false()
	{
		//Arrange
		ClassroomsManager cm = new ClassroomsManager();
		User usr1 = new User("Ben", null, null, null);
		
		//Act
		cm.createClassroom("Algebra", usr1);
		
		//Assert
		assertEquals(false, cm.createClassroom("Algebra", usr1));
	}
	
	@Test
	public void getClassesNames_NoOpenClassrooms_ListOfSize0()
	{
		//Arrange
		ClassroomsManager cm = new ClassroomsManager();
		
		//Assert
		assertEquals(0, cm.getNumberOfClassrooms());
	}
	
	@Test
	public void getClassesNames_TwoOpenClassrooms_ListOfSize2()
	{
		//Arrange
		ClassroomsManager cm = new ClassroomsManager();
		User usr1 = new User("Einstein", null, null, null);
		
		//Act
		cm.createClassroom("Algebra",usr1);
		cm.createClassroom("Geometry",usr1);
		
		//Assert
		assertEquals(2, cm.getNumberOfClassrooms());
	}
	
	@Test
	public void getClassesNames_CreateTwoClassroomsWithSameNames_ListOfSize1()
	{
		//Arrange
		ClassroomsManager cm = new ClassroomsManager();
		User usr1 = new User("Einstein", null, null, null);
		
		//Act
		cm.createClassroom("Algebra",usr1);
		cm.createClassroom("Algebra",usr1);
		
		//Assert
		assertEquals(1, cm.getNumberOfClassrooms());
	}

}
