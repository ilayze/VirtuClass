package guiLayer;
//import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JPasswordField;
//import java.awt.CardLayout;
//import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
//import java.awt.Canvas;
//import java.awt.Component;
//import javax.swing.Box;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @author gilad baruchian
 *
 */
public class LoginFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField passwordField;
	private JLabel lblUsername;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 338, 440);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(100, 208, 215, 25);
		contentPane.add(passwordField);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setBounds(26, 212, 87, 18);
		contentPane.add(lblPassword);
		
		lblUsername = new JLabel("Username :");
		lblUsername.setBounds(26, 172, 67, 18);
		contentPane.add(lblUsername);
		
		textField = new JTextField();
		textField.setBounds(100, 171, 215, 25);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(26, 275, 289, 30);
		contentPane.add(btnLogin);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(26, 363, 289, 30);
		contentPane.add(btnExit);
		
		JCheckBox chckbxRememberMe = new JCheckBox("Remember me");
		chckbxRememberMe.setBounds(97, 245, 111, 18);
		contentPane.add(chckbxRememberMe);
		
		JButton btnNewButton = new JButton("Sing Up");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(26, 316, 289, 35);
		contentPane.add(btnNewButton);
	}
}
