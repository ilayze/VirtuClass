package logicLayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import  connectionLayer.VirtuClassClientChat;
public class OpenClassroom implements Runnable ,ActionListener{
	
	public EditorInfo ei;
	public WindowClose wc;
	public String newInput="";
	
	public OpenClassroom(EditorInfo ei, WindowClose wc)
	{
		this.ei=ei;
		this.wc=wc;
	}

	@Override
	public void run() {
		System.out.println("Im chatting !!");
		new Thread(new VirtuClassClientChat(this));		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
	public void ReciveM(String massage)
	{
		ei.setText(massage);
	}
	
	public void send()
	{
		
	}
}
