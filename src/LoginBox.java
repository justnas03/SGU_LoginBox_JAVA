import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

public class LoginBox extends JFrame{
	private JLabel usernameLabel, pwdLabel;
	private JTextField usernameTF;
	private JTextField pwdTF;
	private JButton loginButton, cancelButton;
	private JPanel panel;
	Database db;

	public LoginBox() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(250,150));
		setResizable(false);
		setLocationRelativeTo(null);
		
		buildContent();
		
		pack();
		setVisible(true);
		
		try {
			db = new Database();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void buildContent() {
		
		//Create Components
		panel = new JPanel();
		usernameLabel = new JLabel("Username: ");
		pwdLabel = new JLabel("Password:  ");
		usernameTF = new JTextField(10);
		pwdTF = new JPasswordField(10);
		loginButton = new JButton("Login");
		cancelButton = new JButton("Cancel");
		
		//setup Layout
		GridLayout layout = new GridLayout(3,1);
		layout.setVgap(5);
		layout.setHgap(5);
		
		//setLayout
		panel.setLayout(layout);
		
		
		//inner User Name
		JPanel innerUserName = new JPanel(new BorderLayout());
		innerUserName.add(usernameLabel,BorderLayout.WEST);
		innerUserName.add(usernameTF,BorderLayout.CENTER);
		panel.add(innerUserName);
		
		//inner Password
		JPanel innerPassword = new JPanel(new BorderLayout());
		innerPassword.add(pwdLabel,BorderLayout.WEST);
		innerPassword.add(pwdTF,BorderLayout.CENTER);
		panel.add(innerPassword);
		
		//inner Buttons
		JPanel innerButton = new JPanel();
		innerButton.add(loginButton);
		loginButton.addActionListener(new ButtonListener());
		innerButton.add(cancelButton);
		cancelButton.addActionListener(new ButtonListener());
		panel.add(innerButton);
		//add panel to JFrame
		add(panel);
	}
	
	public class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == loginButton) {
				if (usernameTF.getText().length() == 0 || pwdTF.getText().length() == 0) {
					 JOptionPane.showMessageDialog(null, "Chua nhap Username hoac Password", "ERROR",JOptionPane.ERROR_MESSAGE);
				} else {
					String password = pwdTF.getText();
					String username = usernameTF.getText();
					try {
						ResultSet result = db.stm.executeQuery("SELECT * FROM Account WHERE username = '" + username + "'");
						result.next();
						String check = result.getString("password");
						if (check.equals(password)) { //if password equals to password in database
							 //Show Dialog
			                JFrame dialog = new JFrame();
		                    JOptionPane.showMessageDialog(dialog, "Login successfully.");
		                    }else{
		                    JFrame dialog = new JFrame();
		                    JOptionPane.showMessageDialog(dialog, "Login fail.");
		                    }
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			} else 
				if (e.getSource() == cancelButton) {
					System.exit(0);
				}
		}
	}
}
