package guiLayer;
//import java.awt.BorderLayout;


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
	private JTextField usernameField;
	public final static String EXIT_CMD = "exit";
	public final static String LOGIN_CMD = "login";
	public final static String SIGNUP_CMD = "sign up";

	/**
	 * Create the frame.
	 */
	public LoginFrame(ActionListener m) {
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
		
		usernameField = new JTextField();
		usernameField.setBounds(100, 171, 215, 25);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setActionCommand(LOGIN_CMD);
		btnLogin.addActionListener(m);
		btnLogin.setBounds(26, 275, 289, 30);
		contentPane.add(btnLogin);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setActionCommand(EXIT_CMD);
		btnExit.addActionListener(m);
		btnExit.setBounds(26, 363, 289, 30);
		contentPane.add(btnExit);
		
		JCheckBox chckbxRememberMe = new JCheckBox("Remember me");
		chckbxRememberMe.setBounds(97, 245, 111, 18);
		contentPane.add(chckbxRememberMe);
		
		JButton signUpButton = new JButton("Sign Up");
		signUpButton.setActionCommand(SIGNUP_CMD);
		signUpButton.addActionListener(m);
		signUpButton.setBounds(26, 316, 289, 35);
		contentPane.add(signUpButton);
	}
	
	public String getUsername()
	{
		if(usernameField.getText()==null)
			return "";
		return usernameField.getText();
	}
	
	public char[] getPassword()
	{
		char pw[]={};
		if(passwordField.getPassword()==null)
			return pw;
		return passwordField.getPassword();
	}
}

