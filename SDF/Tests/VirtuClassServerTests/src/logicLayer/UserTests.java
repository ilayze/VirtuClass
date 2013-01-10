package logicLayer;

import static org.junit.Assert.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


import org.junit.Test;

public class UserTests {

	private static final int OUTPUT_STREAM_CLOSED = 2;
	private static final int INPUT_STREAM_CLOSED = 1;
	
	
	@Test
	public void getName_UsernameIsTom_Tom() {
		//Arrange
		User usr;

		usr = new User("Tom",null,null,null);
		//Assert
		assertEquals("Tom",usr.getName());

	}

	@Test 
	public void getName_UsernameIsNULL_Anonymous()
	{
		//Arrange
		User usr = new User(null,null,null,null);
		
		
		//Assert
		assertEquals("Anonymous",usr.getName());
	}

	@Test
	public void quitChat_socketClosed_true() throws IOException
	{
		//Arrange
		Socket socket = new Socket();
		User usr = new User(null,socket,null,null);
		
		//Act
		usr.quitChat();
		
		//Assert
		assertEquals(true,usr.getSocketDescriptor().isClosed());
	}

	@Test
	public void quitChat__closeOutputStream__OUTPUT_STREAM_CLOSED() throws IOException
	{
		//Arrange
		StubOutputStream s = new StubOutputStream();
		DataOutputStream os = new DataOutputStream(s);
		User usr = new User(null,null,os,null);

		//Act
		int streamsClosed = usr.quitChat();

		//Assert
		assertEquals(OUTPUT_STREAM_CLOSED,streamsClosed);
	}

	@Test
	public void quitChat__closeInputStream__INPUT_STREAM_CLOSED() throws IOException
	{
		//Arrange
		StubInputStream s = new StubInputStream();
		DataInputStream is = new DataInputStream(s);
		User usr = new User(null,null,null,is);

		//Act
		int streamsClosed = usr.quitChat();

		//Assert
		assertEquals(INPUT_STREAM_CLOSED,streamsClosed);
	}


	@Test 
	public void equals_SameUser_true()
	{
		//Arrange
		User usr = new User("abbb",null,null,null);
		User usr2 = new User("abbb",null,null,null);
		//Assert
		assertEquals(true,usr.equals(usr2));
	}
	
	

	@Test 
	public void equals_notSameUser_false()
	{
		//Arrange
		User usr = new User("abbb",null,null,null);
		User usr2 = new User("abbc",null,null,null);
		//Assert
		assertEquals(false,usr.equals(usr2));
	}
	
	@Test 
	public void equals_NotaUser_false()
	{
		//Arrange
		User usr = new User("abbb",null,null,null);
		
		//Assert
		assertEquals(false,usr.equals("abbb"));
	}
	
	@Test
	public void equals_DifferentUserButWithSameUsername_true() throws IOException
	{
		
		//Arrange
		User usr = new User("Guy",null,null,null);
		User usr2 = new User("Guy",null,null,null);
		
		//Assert
		assertEquals(true,usr.equals(usr2));
	}


}
