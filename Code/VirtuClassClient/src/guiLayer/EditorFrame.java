package guiLayer;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.ActionMap;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import logicLayer.EditorInfo;
import javax.swing.JScrollBar;

public class EditorFrame extends JFrame implements Runnable{

	private JEditorPane chatInputEditorPane;
	private JEditorPane chatEditorPane;
	private JPanel contentPane;
	public EditorInfo ei;
	public final static String SEND_CMD = "send";
	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public EditorFrame(ActionListener m) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 564, 577);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(32, 178, 170));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(32, 178, 170));
		panel.setForeground(new Color(0, 139, 139));
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Chat", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(0, 83, 558, 353);
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		chatEditorPane = new JEditorPane();
		chatEditorPane.setEditable(false);
		panel.add(chatEditorPane, BorderLayout.CENTER);
		
		JScrollPane scrollBar = new JScrollPane(chatEditorPane);
		panel.add(scrollBar, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(32, 178, 170));
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(0, 433, 558, 126);
		contentPane.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblWriteSomethingHere = new JLabel("Write something here:");
		panel_1.add(lblWriteSomethingHere, BorderLayout.NORTH);
		chatInputEditorPane = new JEditorPane();
		panel_1.add(chatInputEditorPane, BorderLayout.CENTER);
		
		
		JLabel label = new JLabel(" ");
		panel_1.add(label, BorderLayout.WEST);
		
		JLabel label_1 = new JLabel(" ");
		panel_1.add(label_1, BorderLayout.SOUTH);
		
		JButton btnSend = new JButton("   Send   ");
		btnSend.setActionCommand(SEND_CMD);
		btnSend.addActionListener(m);
		btnSend.setBackground(new Color(32, 178, 170));
		panel_1.add(btnSend, BorderLayout.EAST);
		
		JButton calcButton = new JButton("Calc");
		calcButton.setBounds(10, 32, 74, 50);
		contentPane.add(calcButton);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 558, 15);
		contentPane.add(menuBar);
		
		JMenu mnFile = new JMenu("  File  ");
		menuBar.add(mnFile);
		
		JMenuItem mntmAbout = new JMenuItem("About    ");
		mnFile.add(mntmAbout);
		
		JMenuItem mntmHelp = new JMenuItem("Help     ");
		mnFile.add(mntmHelp);
		
		JMenuItem mntmExit = new JMenuItem("Exit     ");
		mnFile.add(mntmExit);
		
		ei = new EditorInfo() {
			
			@Override
			public void setText(String s) {
				if(s!=null)
					chatEditorPane.setText(chatEditorPane.getText()+"\n"+s);
			}
			
			@Override
			public String getText() {
				if(chatInputEditorPane.getText()==null)
					return "";
				String toSend=chatInputEditorPane.getText();
				chatInputEditorPane.setText("");
				return toSend;
			}
		};
	}


	@Override
	public void run() {		
	}
}
