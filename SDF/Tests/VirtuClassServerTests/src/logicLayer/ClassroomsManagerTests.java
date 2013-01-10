package logicLayer;

import static org.junit.Assert.*;

import org.junit.Test;

public class ClassroomsManagerTests {

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
		assertEquals(0, cm.getClassesNames().size());
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
		assertEquals(2, cm.getClassesNames().size());
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
		assertEquals(1, cm.getClassesNames().size());
	}

}
