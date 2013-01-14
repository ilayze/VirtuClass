package connectionLayer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;

import logicLayer.ClassroomNotDeletedSuccessfullyException;
import logicLayer.ClassroomsManager;
import logicLayer.User;

public class ClientChatThread extends Thread
{
	private User user;
	private ClassroomsManager cm;
	public ClientChatThread(String username, Socket clientSocket, DataOutputStream os, DataInputStream is, ClassroomsManager cm) {
		user = new User(username, clientSocket, os, is);
		this.cm=cm;
	}

	public void run()
	{
		//In Classroom selection screen: showing open classrooms names separated by ':'
		System.out.println("Created a new thread");
		if(!writeOpenClassroomsNames(""))
			return;
		//waiting for a client command. classroom creation = 00 , classroom joining = 01
		//leaving = 02, normal message = 03
		while(true)
		{
			String selection = readSelection();
			if(selection==null)
				return;
			//Act according to the selection given from the client.
			if(!observeClassroomSelection(selection))
				return;
		}
		
	}
	
	private boolean observeClassroomSelection(String selection) {
		boolean status=false;
		if(selection.startsWith("0") && selection.length()>1)
		{
			//create classroom
			if(selection.substring(1).equals(":"))//refresh
			{
				return writeOpenClassroomsNames("");//sendOkMessage();
			}
			else
			{
				status = cm.createClassroom(selection.substring(1), user);
				if(status)
					return writeOpenClassroomsNames(selection.substring(1));//sendOkMessage();
				else
					return sendBadMessage("0");
			}
		}
		else if(selection.startsWith("1"))
		{
			//join classroom
			status = cm.joinClassroom(selection.substring(1), user);
			if(status)
				return writeOpenClassroomsNames(selection.substring(1));//return sendOkMessage("0");
			else
				return sendBadMessage("0");
		}
		else if(selection.startsWith("2"))
		{
			//leave a classroom
			try {
				status = cm.leaveClassroom(selection.substring(2), user);
			} catch (ClassroomNotDeletedSuccessfullyException e) {
				e.printStackTrace();
			}
			return true;
		}
		else if(selection.startsWith("3"))
		{
			sendToAllUsersInClassroom(selection.substring(1));
			return true;
		}
		else if(selection.startsWith("4"))
		{
			return true;
		}
		else//impossible theoretically. -> quit chat
		{
			int closedStatus=0;
			try {
				closedStatus = user.quitChat();
			} catch (IOException e) {
				e.printStackTrace();
			}
			finally
			{
				if(closedStatus!=3)
					System.out.println("Couldn't fully close client streams");
			}
			return false;
		}
	}

	private void sendToAllUsersInClassroom(String selection) {
		String classroomName = getClassroomName(selection);
		LinkedList<DataOutputStream> l = cm.getClientsOutputStreams(classroomName);
		if (l==null)
			return;
		for(DataOutputStream o : l)
		{
			try {
				o.writeUTF("3"+selection);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	private String getClassroomName(String string) {
		int indexOfClassNameEnding = string.indexOf(':');
		if(indexOfClassNameEnding<0 || indexOfClassNameEnding>12)
			indexOfClassNameEnding=12;
		return string.substring(0,indexOfClassNameEnding);
	}

	private boolean sendBadMessage(String location) {
		boolean couldSend=true;
		try {
			user.getOutputStream().writeUTF(location+"1");
		} catch (IOException e) {
			cm.leaveClassrooms(user);// can check if false
			couldSend=false;
		}
		return couldSend;
	}

	private boolean sendOkMessage(String location) {
		boolean couldSend=true;
		try {
			user.getOutputStream().writeUTF(location + "0");
		} catch (IOException e) {
			cm.leaveClassrooms(user);// can check if false
			couldSend=false;
		}
		return couldSend;
	}

	private String readSelection() {
		String clientAnswer=null;
		try {
			clientAnswer = user.getInputStream().readUTF();
		} catch (IOException e) {
			cm.leaveClassrooms(user);
			clientAnswer=null;
		}
		return clientAnswer;
	}

	private boolean writeOpenClassroomsNames(String string)
	{
		String toAdd = "";
		for(int i=string.length();i<12;i++)
		{
			toAdd+=":";
		}
		string+=toAdd;
		boolean operationSucceed=true;
		try {
			user.getOutputStream().writeUTF(/*"0Algebra:Geometry:"*/"00"+string+cm.getClassesNames());
		} catch (IOException e) {
			//user left
			cm.leaveClassrooms(user);
			operationSucceed=false;
		}
		return operationSucceed;
	}
	
}
