package guiLayer;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JEditorPane;
import javax.swing.JSeparator;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.ActionListener;




public class EditorFrame extends JFrame {

	private static EditorFrame editorFrame;
	private JPanel contentPane;
	private final Action exitAction = new ExitAction();
	private final Action aboutAction = new AboutAction();
	private JEditorPane editorPane;
	/**
	 * Create the frame.
	 */
	private void initializeEditor()
	{
		editorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		editorFrame.setBounds(100, 100, 742, 749);
		
		JMenuBar menuBar = new JMenuBar();
		editorFrame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("  File  ");
		menuBar.add(mnFile);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		mntmAbout.setAction(aboutAction);
		mnFile.add(mntmAbout);
		
		JMenuItem mntmHelp = new JMenuItem("Help");
		mnFile.add(mntmHelp);
		
		JMenuItem mntmExit = new JMenuItem("Exit");

		mntmExit.setAction(exitAction);
		mnFile.add(mntmExit);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		editorFrame.setContentPane(contentPane);
		
		editorPane = new JEditorPane();
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(0, 0, 51));
		
		JButton CalcButton = new JButton("Calc");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(editorPane, GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
					.addContainerGap())
				.addComponent(separator, GroupLayout.DEFAULT_SIZE, 716, Short.MAX_VALUE)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(CalcButton, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(633, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(CalcButton, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(editorPane, GroupLayout.PREFERRED_SIZE, 569, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
		editorFrame.setVisible(true);

	}
	
	private EditorFrame() {
			}
	
	public static EditorFrame getEditor()
	{
		if(editorFrame==null)
		{
			editorFrame = new EditorFrame();
			editorFrame.initializeEditor();
		}
		return editorFrame;
	}
	
	
	public String getText()
	{
		return editorPane.getText();
	}
	
	public void setText(String s)
	{
		if(s!=null)
			editorPane.setText(editorPane.getText()+ s+"\n");
	}
	
	private class ExitAction extends AbstractAction {
		public ExitAction() {
			putValue(NAME, "Exit");
			putValue(SHORT_DESCRIPTION, "Exit VirtuClass Editor");
		}
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
	
	private class AboutAction extends AbstractAction {
		public AboutAction() {
			putValue(NAME, "About");
			putValue(SHORT_DESCRIPTION, "Details about VirtuClass");
		}
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "VirtuClass is an open source network made for students\naround the globe to help in homework assignments and more.", "About Virtuclass",JOptionPane.INFORMATION_MESSAGE );
		}
	}
}
