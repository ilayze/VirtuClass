package logicLayer;

import guiLayer.EditorFrame;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ArrayBlockingQueue;


public class OpenClassMediator implements Runnable//http://eclipse-metrics.sourceforge.net/descriptions/pages/cohesion/PairwiseFieldIrrelation.html
{
	private String username;
	private String classroomName;
	private ArrayBlockingQueue<String> readFromServerQueue;
	private ArrayBlockingQueue<String> writeToServerQueue;
	private class OpenClassMessagesMediator implements ActionListener
		{
			private EditorInfo ei = new NullEditorInfo();
			@Override
			public void actionPerformed(ActionEvent e) {
				String toWrite = ei.getText();
				if(toWrite.length()!=0)
				{
					new Thread(new MsgFromClassroomWriter(classroomName,toWrite, writeToServerQueue, username)).start();
				}
			}
			
			public void setEditorInfo(EditorInfo ei) {
				this.ei=ei;
			}
		}
	
		public OpenClassMediator(String username, String classroomName, ArrayBlockingQueue<String> a, ArrayBlockingQueue<String> writeQueue)
		{
			this.username=username;
			this.classroomName=classroomName;
			this.readFromServerQueue=a;
			this.writeToServerQueue=writeQueue;
		}

		@Override
		public void run() {
			EventQueue.invokeLater(new Runnable() {
			public void run() {
					try {

						startEditor();						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}});
		}

		public void startEditor()
		{
			
			OpenClassMessagesMediator msgMed = new OpenClassMessagesMediator();
			EditorFrame frame = new EditorFrame(msgMed,classroomName);
			addLength();
			msgMed.setEditorInfo(frame.data.ei);
			frame.setVisible(true);
			new Thread(new MsgFromServerReader(readFromServerQueue,frame.data.ei)).start();
			
		}
		
		private void addLength()
		{
			for(int i=classroomName.length(); i<12;i++)
				classroomName+=":";
		}

}
