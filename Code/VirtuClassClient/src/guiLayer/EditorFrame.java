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

	public static class EditorFrameData {
		public EditorFrameInnerData editorInnerData;
		public EditorInfo ei;

		public EditorFrameData(EditorFrameInnerData editorInnerData) {
			this.editorInnerData = editorInnerData;
		}
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = -5595679210482278126L;


	private static class EditorFrameInnerData {
		private JEditorPane chatInputEditorPane;
		private JEditorPane chatEditorPane;
		private JPanel contentPane;

		private EditorFrameInnerData() {
		}
	}


	public EditorFrameData data = new EditorFrameData(new EditorFrameInnerData());
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
		
		defineFuncPointers();
	}


	private void defineFuncPointers() {
		data.ei = new EditorInfo() {

			@Override
			public void setText(String s) {
				if(s!=null && s.length()!=0)
					data.editorInnerData.chatEditorPane.setText(data.editorInnerData.chatEditorPane.getText()+"\n"+s);
			}	
			
			@Override	
			public String getText() {
				return getTextFromInputChatbox();
			}
		};	
	}

	private String getTextFromInputChatbox()
	{
		if(data.editorInnerData.chatInputEditorPane.getText()==null)
			return "";
		String toSend=data.editorInnerData.chatInputEditorPane.getText();
		data.editorInnerData.chatInputEditorPane.setText("");
		return toSend;
	}



	private void addMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 558, 15);
		data.editorInnerData.contentPane.add(menuBar);

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
		data.editorInnerData.contentPane.add(calcButton);
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
		data.editorInnerData.contentPane.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblWriteSomethingHere = new JLabel("Write something here:");
		panel_1.add(lblWriteSomethingHere, BorderLayout.NORTH);
		data.editorInnerData.chatInputEditorPane = new JEditorPane();
		panel_1.add(data.editorInnerData.chatInputEditorPane, BorderLayout.CENTER);
			
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
		data.editorInnerData.contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		data.editorInnerData.chatEditorPane = new JEditorPane();
		data.editorInnerData.chatEditorPane.setEditable(false);
		panel.add(data.editorInnerData.chatEditorPane, BorderLayout.CENTER);		
		JScrollPane scrollBar = new JScrollPane(data.editorInnerData.chatEditorPane);
		panel.add(scrollBar, BorderLayout.CENTER);
	}


	/**
	 * 
	 */
	private void initFramePanel() {
		data.editorInnerData.contentPane = new JPanel();
		data.editorInnerData.contentPane.setBackground(new Color(32, 178, 170));
		data.editorInnerData.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(data.editorInnerData.contentPane);
		data.editorInnerData.contentPane.setLayout(null);
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
