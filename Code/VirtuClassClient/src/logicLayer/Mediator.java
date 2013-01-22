package logicLayer;

import guiLayer.ClassroomChooserFrame2;
import guiLayer.ListChanger;
import guiLayer.LoginFrame;

import java.awt.EventQueue;
import java.awt.Toolkit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Semaphore;
import javax.swing.JOptionPane;

import connectionLayer.VirtuClassClientChat;






public class Mediator extends Thread{
	private ClassroomManager c;
	private String username;
	
	
	private class LoginScreenMediator implements ActionListener{
	
		LoginMaster l;
		LoginMediator lm;
		SignUpMediator sm;
		Semaphore loginKey = new Semaphore(1);

		public LoginScreenMediator()
		{
			lm = new LoginMediator();
			sm = new SignUpMediator();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand()==LoginFrame.EXIT_CMD)
				System.exit(0);
			else if(e.getActionCommand()==LoginFrame.SIGNUP_CMD)
				sm.signUpPressed();
			else if(e.getActionCommand()==LoginFrame.LOGIN_CMD)
				lm.loginPressed();
		}
		
		private class LoginMediator implements Runnable
		{
			
				public LoginMediator()
				{
					
				}
				private LoginMediator(Semaphore obj)
				{
					loginKey=obj;
				}
				
				public void loginPressed()
				{
					if(loginKey.tryAcquire())
					{
						new Thread(new LoginMediator(loginKey)).start();
					}
					return;
					
	
				}
			
				
				private void loginSucceed()
				{
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								l.forceExit();
								//new Thread(new OpenClassMediator()).start();
								new Thread(new ClassroomsChooserMediator()).start();
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
	
				}
	
				private void tryToLogin() throws Exception
				{
					boolean isOk = l.sendRequest(0);//1=signUp, 0 = login
					if(!isOk)
					{
						JOptionPane.showMessageDialog(null, "Wrong username / password", "Invalid user", JOptionPane.ERROR_MESSAGE);
						return;
					}
					username = l.getUserName();
					loginSucceed();
				}
				
				
				@Override
				public void run() {
					try {
						tryToLogin();
					} catch (Exception e1) {
						System.out.println(e1.getMessage());
					}
					finally
					{
						loginKey.release();
					}		
				}	
		}
		
		private class SignUpMediator implements Runnable
		{
				private SignUpMediator(Semaphore obj) {
					loginKey=obj;	
				}
	
				public SignUpMediator()
				{
				}
				
				public void signUpPressed()
				{
					if(loginKey.tryAcquire())
					{
						new Thread(new SignUpMediator(loginKey)).start();
					}
	
				}
	
				private void tryToSignUp() throws Exception
				{
					boolean isOk = l.sendRequest(1);//1 = signup, 0 = login
					if(!isOk)
					{
						JOptionPane.showMessageDialog(null, "Username already exist.", "Choose a different username", JOptionPane.ERROR_MESSAGE);
					}
				}
				
				@Override
				public void run() {
					try {
						tryToSignUp();
					} catch (Exception e1) {
						System.out.println(e1.getMessage());
					}
					finally
					{
						loginKey.release();
					}
				}
		}
		
		
		
	}
	
	private class ClassroomsChooserMediator implements Runnable
	{
		private VirtuClassClientChat chat;
		
		
		private class ClassroomsListMediator extends Thread implements ActionListener
		{
			private ListChanger lc;
			private ArrayBlockingQueue<String> messagesToRead;
			private ArrayBlockingQueue<String> messagesToWrite;
			private String openClassroomsNames="";
			private Semaphore classroomMsgSem = new Semaphore(1);
			
			private class WriteClassroomsListMediator extends Thread
			{
				private String messageToWriteInQueue;
				public WriteClassroomsListMediator(String name) {
					messageToWriteInQueue=name;
				}

				@Override
				public void run()
				{
					try {
						messagesToWrite.put(messageToWriteInQueue);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			
			public ClassroomsListMediator(ArrayBlockingQueue<String> messagesOfClassroomsChooserFrame, ArrayBlockingQueue<String> arrayBlockingQueue2){
				this.messagesToRead=messagesOfClassroomsChooserFrame;
				this.messagesToWrite=arrayBlockingQueue2;
			
			}
				
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//
				String name = lc.getNewClassroomName();
				if(arg0.getActionCommand().equals(ClassroomChooserFrame2.REFRESH))
					name="0:";
				else if(arg0.getActionCommand().equals(ClassroomChooserFrame2.NEW_CLASSROOM))
				{
					if(name==null || name.equals("") || name.length()>12 || alreadyInList(name) ||name.contains(":") || c.getNumberOfOpenClassrooms()>4)
					{
						invalidClassName();
						return;
					}
					name="0"+name;
				}
				else if(arg0.getActionCommand().equals(ClassroomChooserFrame2.CONNECT))
				{
					name = lc.getSelectedValue();
					name="1"+name;
				}
				WriteClassroomsListMediator writeListMediator = new WriteClassroomsListMediator(name);
				writeListMediator.start();

//				int index = lc.getSelectedIndex(); //get selected index
//				if (index == -1) { //no selection, so insert at beginning
//					index = 0;
//				} else {           //add after the selected item
//					index++;
//				}
//
//				lc.insertElementAt(index);
//				//If we just wanted to add to the end, we'd do this:
//				//listModel.addElement(employeeName.getText());
//
//				//Reset the text field.
//				lc.giveFocusToNewClassroomText();
//				lc.setNewClassroomNameClean();
//
//				//Select the new item and make it visible.
//				lc.setSelectedIndex(index);
//				lc.ensureIndexIsVisible(index);
				//
			}
			private boolean alreadyInList(String name) {
				return lc.listContains(name);
			}
			private void invalidClassName()
			{
				Toolkit.getDefaultToolkit().beep();
				lc.giveFocusToNewClassroomText();
				lc.selectNewClassroomText();
			}
			
			public void setListChanger(ListChanger l) {
				lc=l;
			}


			@Override
			public void run() {
				while(true)
				{
					try {
						openClassroomsNames = messagesToRead.take();
						if(openClassroomsNames.startsWith("0"))
						{
						       successMessage(openClassroomsNames);

						}
						else
						{
							System.out.println(openClassroomsNames);
							JOptionPane.showMessageDialog(null, "Invalid classroom name", "Couldn't refresh classrooms", JOptionPane.ERROR_MESSAGE);
						}				
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

successMessage(String openClassroomsNames)
{
							openClassroomsNames = openClassroomsNames.substring(1);
							lc.setNewClassroomsList(openClassroomsNames.substring(12));
							if(!openClassroomsNames.substring(0, 12).equals("::::::::::::"))
							{

								int index = openClassroomsNames.indexOf(':');
								if(index>12 || index<0)
									index=12;
								c.openClassroom(openClassroomsNames.substring(0,index),username);

								
							}

}
			
			
		}
		
		
		public ClassroomsChooserMediator()
		{
			
		}
		@Override
		public void run() {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
						try {
	
							startClassroomChooser();						
						} catch (Exception e) {
							e.printStackTrace();
						}
					}});
		}
		
		private void startClassroomChooser()
		{
			ArrayBlockingQueue<String> waitingMessagesToBeRead = new ArrayBlockingQueue<String>(30);
			ArrayBlockingQueue<String> waitingMessagesToBeWritten = new ArrayBlockingQueue<String>(30);
			ReadMessagesMediator readMediator = new ReadMessagesMediator(waitingMessagesToBeRead);
			readMediator.start();
			WriteMessagesMediator writeMediator = new WriteMessagesMediator(waitingMessagesToBeWritten);
			writeMediator.start();
			
			c = new ClassroomManager(waitingMessagesToBeWritten);
			
			ClassroomsListMediator m = new ClassroomsListMediator(readMediator.getClassroomsChooserQueue(),writeMediator.getClassroomsChooserQueue());
			chat = new VirtuClassClientChat(waitingMessagesToBeRead,waitingMessagesToBeWritten);
			chat.start();
			ClassroomChooserFrame2 frame = new ClassroomChooserFrame2(m);
			m.setListChanger(frame.l);
			m.start();
			frame.setVisible(true);
			
		}
		
	}
	
		
	private class ReadMessagesMediator extends Thread
	{
		private ArrayBlockingQueue<String> waitingMessagesToBeRead;
		private ArrayBlockingQueue<String> messagesForClassroomsChooserFrame;
		private ReadMessagesMediator(ArrayBlockingQueue<String> waitingMessagesToBeRead) {
			this.waitingMessagesToBeRead=waitingMessagesToBeRead;
			messagesForClassroomsChooserFrame = new ArrayBlockingQueue<String>(5);
			
			
		}
		

		public ArrayBlockingQueue<String> getClassroomsChooserQueue()
		{
			return messagesForClassroomsChooserFrame;
		}
		
		@Override
		public void run()
		{
			while(true)
			{
				try {
					String readMessage = waitingMessagesToBeRead.take();
					if(readMessage.length()>=2 && readMessage.charAt(0)=='0')
					{
						messagesForClassroomsChooserFrame.put(readMessage.substring(1));
					}
					if(readMessage.length()>=2 && readMessage.charAt(0)=='3')
					{
						System.out.println("got read3: " + readMessage);
						c.updateTheRightReadQueue(readMessage.substring(1));
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	private class WriteMessagesMediator extends Thread
	{
		private ArrayBlockingQueue<String> waitingMessagesToBeWritten;
		private ArrayBlockingQueue<String> messagesFromClassroomsChooserFrame;
		
		private WriteMessagesMediator(ArrayBlockingQueue<String> waitingMessagesToBeWritten) {
			this.waitingMessagesToBeWritten=waitingMessagesToBeWritten;
			messagesFromClassroomsChooserFrame = new ArrayBlockingQueue<String>(5);
		}

		public ArrayBlockingQueue<String> getClassroomsChooserQueue()
		{
			return messagesFromClassroomsChooserFrame;
		}
		@Override
		public void run()
		{
			new Thread(new WriteMessagesFromClassroomsList(messagesFromClassroomsChooserFrame)).start();
			//new Thread(new WriteMessagesFromOpenClassroom(messagesFromOpenClassroom)).start();

		}
		
		private class WriteMessagesFromClassroomsList implements Runnable
		{
			private ArrayBlockingQueue<String> messagesFromClassroomsChooserFrame1;
			public WriteMessagesFromClassroomsList(
					ArrayBlockingQueue<String> messagesFromClassroomsChooserFrame2) {
				this.messagesFromClassroomsChooserFrame1=messagesFromClassroomsChooserFrame2;
			}
			@Override
			public void run()
			{
				while(true)
				{
					try {
						String messageToWrite = messagesFromClassroomsChooserFrame1.take();
						waitingMessagesToBeWritten.put(messageToWrite);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public Mediator()
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					startLoginScreen();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	private void startLoginScreen() throws Exception
	{
		LoginScreenMediator m = new LoginScreenMediator();
		LoginFrame frame = new LoginFrame(m);
		WindowEvent wev = new WindowEvent(frame, WindowEvent.WINDOW_CLOSING);
		m.l = new LoginMaster(frame.data.li,wev);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {//init Mediator
		
		new Thread(new Mediator());
	}

}
