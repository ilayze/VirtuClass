package guiLayer;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import logicLayer.EditorInfo;
import logicLayer.LoginInfo;


public class EditorFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2395745371001166481L;
	private JPanel contentPane;
	private JEditorPane chatEditorPane;
	public EditorInfo ei;
	
	private static EditorFrame editorFrame=null;
	/**
	 * Launch the application.
	 */

	public static EditorFrame getEditor()
	{
		if(editorFrame==null)
		{
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						editorFrame = new EditorFrame();
						editorFrame.initializeEditor();
						editorFrame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
			});
			System.out.println("lalala");
		}
		return editorFrame;
		
	}
	
	/**
	 * Create the frame.
	 */
	private EditorFrame() {
	}



	private void initializeEditor()
	{
		editorFrame.setResizable(false);
		editorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		editorFrame.setBounds(100, 100, 564, 577);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(32, 178, 170));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		editorFrame.setContentPane(contentPane);
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
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(32, 178, 170));
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(0, 433, 558, 126);
		contentPane.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblWriteSomethingHere = new JLabel("Write something here:");
		panel_1.add(lblWriteSomethingHere, BorderLayout.NORTH);
		
		JEditorPane editorPane_1 = new JEditorPane();
		panel_1.add(editorPane_1, BorderLayout.CENTER);
		
		JLabel label = new JLabel(" ");
		panel_1.add(label, BorderLayout.WEST);
		
		JLabel label_1 = new JLabel(" ");
		panel_1.add(label_1, BorderLayout.SOUTH);
		
		JButton btnSend = new JButton("   Send   ");
		btnSend.setBackground(new Color(32, 178, 170));
		panel_1.add(btnSend, BorderLayout.EAST);
		
		JButton btnNewButton = new JButton("Calc");
		btnNewButton.setBounds(10, 32, 74, 50);
		contentPane.add(btnNewButton);
		
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

		
		
		editorFrame.ei=new EditorInfo() {

			@Override
			public String getText() {
				return null;
			}

			@Override
			public void setText(String s) {
				
			}	//function pointer. we don't want the textfields to be passed, only their current values.
			
		};
	}
	

	
}





/*
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */
//
//public class EditorFrame extends JFrame {
//
//	private static EditorFrame editorFrame;
//	private JPanel contentPane;
//	private final Action exitAction = new ExitAction();
//	private final Action aboutAction = new AboutAction();
//	private JEditorPane editorPane;
//	/**
//	 * Create the frame.
//	 */
//	private void initializeEditor()
//	{
//		editorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		editorFrame.setBounds(100, 100, 742, 749);
//		
//		JMenuBar menuBar = new JMenuBar();
//		editorFrame.setJMenuBar(menuBar);
//		
//		JMenu mnFile = new JMenu("  File  ");
//		menuBar.add(mnFile);
//		
//		JMenuItem mntmAbout = new JMenuItem("About");
//		mntmAbout.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//			}
//		});
//		mntmAbout.setAction(aboutAction);
//		mnFile.add(mntmAbout);
//		
//		JMenuItem mntmHelp = new JMenuItem("Help");
//		mnFile.add(mntmHelp);
//		
//		JMenuItem mntmExit = new JMenuItem("Exit");
//
//		mntmExit.setAction(exitAction);
//		mnFile.add(mntmExit);
//		contentPane = new JPanel();
//		contentPane.setBackground(new Color(153, 204, 255));
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		editorFrame.setContentPane(contentPane);
//		
//		editorPane = new JEditorPane();
//		
//		JSeparator separator = new JSeparator();
//		separator.setForeground(new Color(0, 0, 51));
//		
//		JButton CalcButton = new JButton("Calc");
//		GroupLayout gl_contentPane = new GroupLayout(contentPane);
//		gl_contentPane.setHorizontalGroup(
//			gl_contentPane.createParallelGroup(Alignment.TRAILING)
//				.addGroup(gl_contentPane.createSequentialGroup()
//					.addContainerGap()
//					.addComponent(editorPane, GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
//					.addContainerGap())
//				.addComponent(separator, GroupLayout.DEFAULT_SIZE, 716, Short.MAX_VALUE)
//				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
//					.addContainerGap()
//					.addComponent(CalcButton, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
//					.addContainerGap(633, Short.MAX_VALUE))
//		);
//		gl_contentPane.setVerticalGroup(
//			gl_contentPane.createParallelGroup(Alignment.TRAILING)
//				.addGroup(gl_contentPane.createSequentialGroup()
//					.addContainerGap()
//					.addComponent(CalcButton, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
//					.addPreferredGap(ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
//					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
//					.addPreferredGap(ComponentPlacement.RELATED)
//					.addComponent(editorPane, GroupLayout.PREFERRED_SIZE, 569, GroupLayout.PREFERRED_SIZE)
//					.addContainerGap())
//		);
//		contentPane.setLayout(gl_contentPane);
//		editorFrame.setVisible(true);
//
//	}
//	
//	private EditorFrame() {
//			}
//	
//	public static EditorFrame getEditor()
//	{
//		if(editorFrame==null)
//		{
//			editorFrame = new EditorFrame();
//			editorFrame.initializeEditor();
//		}
//		return editorFrame;
//	}
//	
//	
//	public String getText()
//	{
//		return editorPane.getText();
//	}
//	
//	public void setText(String s)
//	{
//		if(s!=null)
//			editorPane.setText(editorPane.getText()+ s+"\n");
//	}
//	
//	private class ExitAction extends AbstractAction {
//		public ExitAction() {
//			putValue(NAME, "Exit");
//			putValue(SHORT_DESCRIPTION, "Exit VirtuClass Editor");
//		}
//		public void actionPerformed(ActionEvent e) {
//			System.exit(0);
//		}
//	}
//	
//	private class AboutAction extends AbstractAction {
//		public AboutAction() {
//			putValue(NAME, "About");
//			putValue(SHORT_DESCRIPTION, "Details about VirtuClass");
//		}
//		public void actionPerformed(ActionEvent e) {
//			JOptionPane.showMessageDialog(null, "VirtuClass is an open source network made for students\naround the globe to help in homework assignments and more.", "About Virtuclass",JOptionPane.INFORMATION_MESSAGE );
//		}
//	}
//}
