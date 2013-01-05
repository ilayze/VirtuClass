package guiLayer;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import logicLayer.EditorInfo;

public class EditorFrame extends JFrame implements Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5595679210482278126L;


	private static class EditorFrameData {
		private JEditorPane chatInputEditorPane;
		private JEditorPane chatEditorPane;
		private JPanel contentPane;

		private EditorFrameData() {
		}
	}


	private EditorFrameData editorData = new EditorFrameData();
	public EditorInfo ei;
	public final static String SEND_CMD = "send";
	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public EditorFrame(ActionListener m) {
		initFrame();
		initFramePanel();
		
		initChatEditorPanel();
		
		initChatInputPart(m);
		
		addCalcButton();
		
		addMenuBar();
		
		ei = new EditorInfo() {
			
			@Override
			public void setText(String s) {
				if(s!=null)
					editorData.chatEditorPane.setText(editorData.chatEditorPane.getText()+"\n"+s);
			}
			
			@Override
			public String getText() {
				if(editorData.chatInputEditorPane.getText()==null)
					return "";
				String toSend=editorData.chatInputEditorPane.getText();
				editorData.chatInputEditorPane.setText("");
				return toSend;
			}
		};
	}




	private void addMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 558, 15);
		editorData.contentPane.add(menuBar);

		JMenu mnFile = new JMenu("  File  ");
		menuBar.add(mnFile);
		
		addFileMenuItems(mnFile);
	}




	/**
	 * @param mnFile
	 */
	private void addFileMenuItems(JMenu mnFile) {
		JMenuItem mntmAbout = new JMenuItem("About    ");
		mnFile.add(mntmAbout);
		
		JMenuItem mntmHelp = new JMenuItem("Help     ");
		mnFile.add(mntmHelp);
		
		JMenuItem mntmExit = new JMenuItem("Exit     ");
		mnFile.add(mntmExit);
	}



	/**
	 * 
	 */
	private void addCalcButton() {
		JButton calcButton = new JButton("Calc");
		calcButton.setBounds(10, 32, 74, 50);
		editorData.contentPane.add(calcButton);
	}



	/**
	 * @param m
	 */
	private void initChatInputPart(ActionListener m) {
		JPanel panel_1 = initInputEditor();
		
		addSendButton(m, panel_1);
	}



	/**
	 * @param m
	 * @param panel_1
	 */
	private void addSendButton(ActionListener m, JPanel panel_1) {
		JButton btnSend = new JButton("   Send   ");
		btnSend.setActionCommand(SEND_CMD);
		btnSend.addActionListener(m);
		btnSend.setBackground(new Color(32, 178, 170));
		panel_1.add(btnSend, BorderLayout.EAST);
	}



	private JPanel initInputEditor() {
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(32, 178, 170));
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(0, 433, 558, 126);
		editorData.contentPane.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblWriteSomethingHere = new JLabel("Write something here:");
		panel_1.add(lblWriteSomethingHere, BorderLayout.NORTH);
		editorData.chatInputEditorPane = new JEditorPane();
		panel_1.add(editorData.chatInputEditorPane, BorderLayout.CENTER);
			
		return addEmptyLabelsForView(panel_1);
	}



	/**
	 * @param panel_1
	 * @return
	 */
	private JPanel addEmptyLabelsForView(JPanel panel_1) {
		JLabel label = new JLabel(" ");
		panel_1.add(label, BorderLayout.WEST);
		
		JLabel label_1 = new JLabel(" ");
		panel_1.add(label_1, BorderLayout.SOUTH);
		return panel_1;
	}



	private void initChatEditorPanel() {
		JPanel panel = new JPanel();
		panel.setBackground(new Color(32, 178, 170));
		panel.setForeground(new Color(0, 139, 139));
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Chat", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(0, 83, 558, 353);
		editorData.contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		editorData.chatEditorPane = new JEditorPane();
		editorData.chatEditorPane.setEditable(false);
		panel.add(editorData.chatEditorPane, BorderLayout.CENTER);		
		JScrollPane scrollBar = new JScrollPane(editorData.chatEditorPane);
		panel.add(scrollBar, BorderLayout.CENTER);
	}


	/**
	 * 
	 */
	private void initFramePanel() {
		editorData.contentPane = new JPanel();
		editorData.contentPane.setBackground(new Color(32, 178, 170));
		editorData.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(editorData.contentPane);
		editorData.contentPane.setLayout(null);
	}


	/**
	 * 
	 */
	private void initFrame() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 564, 577);
	}


	@Override
	public void run() {		
	}
}
