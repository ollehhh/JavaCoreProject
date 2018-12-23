package frames;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Login {
	private JFrame loginFrame = new JFrame("Login");
	private JTextField userNameFld = new JTextField(15);
	private JTextField userPassFld = new JTextField(15);
	private JButton loginBut = new JButton("Login");
	private JButton regBut = new JButton("Register");

	public void createLogin() {
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginFrame.setSize(300, 80);
		loginFrame.setVisible(true);
		loginFrame.setLocationRelativeTo(null);
		loginFrame.setResizable(false);

		JLabel userName = new JLabel("User Name");
		JLabel userPass = new JLabel("Password");
		JPanel loginPanel = new JPanel();

		loginPanel.setLayout(new GridLayout(2, 3));

		loginFrame.add(loginPanel);

		loginPanel.add(userName);
		loginPanel.add(userNameFld);
		loginPanel.add(loginBut);
		loginPanel.add(userPass);
		loginPanel.add(userPassFld);
		loginPanel.add(regBut);
		loginBut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		regBut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Registration reg = new Registration();
				reg.createRegister();
				loginFrame.dispose();
			}
		});
	}

	public String getUserNameFld() {
		return userNameFld.getText();
	}

	public void setUserNameFld(String userNameFld) {
		this.userNameFld.setText(userNameFld);
		;
	}

	public String getUserPassFld() {
		return userPassFld.getText();
	}

	public void setUserPassFld(String userPassFld) {
		this.userPassFld.setText(userPassFld);
		;
	}

}
