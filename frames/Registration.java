package frames;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import connection.RegisterConnection;
import entity.User;

public class Registration {
	private JFrame registerFrame = new JFrame("Registration");
	private JTextField loginNameField = new JTextField();
	private JTextField passwordField = new JTextField();
	private JTextField firstNameField = new JTextField();
	private JTextField surnameField = new JTextField();
	private JTextField emailField = new JTextField();
	private JTextField phoneField = new JTextField();
	private JTextField companyName = new JTextField();
	private JTextField specialization = new JTextField();
	private JButton cansel = new JButton("Cansel");
	private JButton register = new JButton("Register");

	public void createRegister() {
		registerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		registerFrame.setSize(400, 250);
		registerFrame.setVisible(true);
		registerFrame.setLocationRelativeTo(null);
		registerFrame.setResizable(false);

		JPanel regPanel = new JPanel();

		regPanel.setLayout(new GridLayout(9, 2));
		registerFrame.add(regPanel);
		regPanel.add(new JLabel("Login Name"));
		regPanel.add(loginNameField);
		regPanel.add(new JLabel("Password"));
		regPanel.add(passwordField);
		regPanel.add(new JLabel("First Name"));
		regPanel.add(firstNameField);
		regPanel.add(new JLabel("Surname"));
		regPanel.add(surnameField);
		regPanel.add(new JLabel("eMail"));
		regPanel.add(emailField);
		regPanel.add(new JLabel("Phone"));
		regPanel.add(phoneField);
		regPanel.add(new JLabel("Company name"));
		regPanel.add(companyName);
		regPanel.add(new JLabel("Specialization"));
		regPanel.add(specialization);
		regPanel.add(cansel);
		regPanel.add(register);

		cansel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Login lg = new Login();
				lg.createLogin();
				registerFrame.dispose();
			}
		});
		register.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				User user = new User();
				user.setLogin(loginNameField.getText());
				user.setPassword(passwordField.getText());
				user.setCompanyName(companyName.getText());
				user.setEmail(emailField.getText());
				user.setFirstName(firstNameField.getText());
				user.setPhone(Integer.parseInt(phoneField.getText()));
				user.setSpecialization(specialization.getText());
				user.setSurName(surnameField.getText());

				RegisterConnection con = new RegisterConnection();
				con.RegCon(user);
				Login lg = new Login();
				lg.createLogin();
				registerFrame.dispose();
			}
		});
	}
}
