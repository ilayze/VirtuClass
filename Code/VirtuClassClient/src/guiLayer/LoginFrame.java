package guiLayer;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;

import logicLayer.LoginInfo;


public class LoginFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final static String EXIT_CMD = "exit";
	public final static String LOGIN_CMD = "login";
	public final static String SIGNUP_CMD = "sign up";
	public LoginFrameData data = new LoginFrameData(new LoginFrameInnerData());

	/**
	 * Create the frame.
	 */
	public LoginFrame(ActionListener m) {
		initPanel();
		
		addPasswordField();
		addUsernameField();	
		addLoginButton(m);	
		addExitButton(m);
		addRememberMeCheckBox();
		addSignUpButton(m);
		addFuncPointers();
		
	}

	public static class LoginFrameData {
		private LoginFrameInnerData innerData;
		public LoginInfo li;

		private LoginFrameData(LoginFrameInnerData innerData) {
			this.innerData = innerData;
		}
	}

	private static class LoginFrameInnerData {
		private JPanel contentPane;
		private JPasswordField passwordField;
		private JLabel lblUsername;
		private JTextField usernameField;

		private LoginFrameInnerData() {
		}
	}

	
	private void addFuncPointers() {
		data.li=new LoginInfo() {	//function pointer. we don't want the textfields to be passed, only their current values.		
			
			@Override
			public String getUsername()
			{
				return getUsrFromUsrField();
			}
			@Override
			public char[] getPassword()
			{
				return getPwFromPwField();
			}
		};
	}
	
	private char[] getPwFromPwField()
	{
		char pw[]={};
		if(data.innerData.passwordField.getPassword()==null)
			return pw;
		return data.innerData.passwordField.getPassword();
	}
	
	private String getUsrFromUsrField()
	{
		if(data.innerData.usernameField.getText()==null)
			return "";
		return data.innerData.usernameField.getText();
	}

	/**
	 * @param m
	 */
	private void addSignUpButton(ActionListener m) {
		JButton signUpButton = new JButton("Sign Up");
		signUpButton.setActionCommand(SIGNUP_CMD);
		signUpButton.addActionListener(m);
		signUpButton.setBounds(26, 316, 289, 35);
		data.innerData.contentPane.add(signUpButton);
	}

	/**
	 * 
	 */
	private void addRememberMeCheckBox() {
		JCheckBox chckbxRememberMe = new JCheckBox("Remember me");
		chckbxRememberMe.setBounds(97, 245, 111, 18);
		data.innerData.contentPane.add(chckbxRememberMe);
	}

	/**
	 * @param m
	 */
	private void addExitButton(ActionListener m) {
		JButton btnExit = new JButton("Exit");
		btnExit.setActionCommand(EXIT_CMD);
		btnExit.addActionListener(m);
		btnExit.setBounds(26, 363, 289, 30);
		data.innerData.contentPane.add(btnExit);
	}

	/**
	 * @param m
	 */
	private void addLoginButton(ActionListener m) {
		JButton btnLogin = new JButton("Login");
		btnLogin.setActionCommand(LOGIN_CMD);
		btnLogin.addActionListener(m);
		btnLogin.setBounds(26, 275, 289, 30);
		data.innerData.contentPane.add(btnLogin);
	}

	/**
	 * 
	 */
	private void addUsernameField() {
		data.innerData.lblUsername = new JLabel("Username :");
		data.innerData.lblUsername.setBounds(26, 172, 67, 18);
		data.innerData.contentPane.add(data.innerData.lblUsername);
		
		data.innerData.usernameField = new JTextField();
		data.innerData.usernameField.setBounds(100, 171, 215, 25);
		data.innerData.contentPane.add(data.innerData.usernameField);
		data.innerData.usernameField.setColumns(10);
	}

	/**
	 * 
	 */
	private void addPasswordField() {
		data.innerData.passwordField = new JPasswordField();
		data.innerData.passwordField.setBounds(100, 208, 215, 25);
		data.innerData.contentPane.add(data.innerData.passwordField);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setBounds(26, 212, 87, 18);
		data.innerData.contentPane.add(lblPassword);
	}

	/**
	 * 
	 */
	private void initPanel() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 338, 440);
		data.innerData.contentPane = new JPanel();
		data.innerData.contentPane.setBackground(new Color(153, 204, 255));
		data.innerData.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(data.innerData.contentPane);
		data.innerData.contentPane.setLayout(null);
	}
	
	

}

