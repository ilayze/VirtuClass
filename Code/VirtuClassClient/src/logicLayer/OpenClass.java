package logicLayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import  connectionLayer.VirtuClassClientChat;
public class OpenClass implements Runnable ,ActionListener{
	
	public EditorInfo ei;
	public String newInput="";
	
	public OpenClass(EditorInfo ei)
	{
		this.ei=ei;
		
	}

	@Override
	public void run() {
		System.out.println("Im chatting !!");
		VirtuClassClientChat vc = new VirtuClassClientChat(this);		
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
