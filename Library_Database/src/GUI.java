import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class GUI implements ActionListener {
	
	private static String AdminName="Tom";
	private static String AdminPassword ="123";
	private static JLabel userlabel;
	private static JTextField userText;
	private static JLabel passwordLabel;
	private static JPasswordField passwordText;
	private static JButton button;
	private static JLabel success;
	private static JFrame frame;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JPanel panel = new JPanel();
		frame = new JFrame();
		frame.setSize(350,200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		
		panel.setLayout(null);
		
		userlabel = new JLabel("User");
		userlabel.setBounds(10,20,80,25);
		panel.add(userlabel);
		
		userText = new JTextField(20);
		userText.setBounds(100,20,165,25);
		panel.add(userText);
		
		passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(10,50,80,25);
		panel.add(passwordLabel);
		
		passwordText = new JPasswordField();
		passwordText.setBounds(100,50,165,25);
		panel.add(passwordText);
		
		success = new JLabel("");
		button = new JButton("Login");
		button.setBounds(100,80,80,25);
		button.addActionListener(new GUI());
		panel.add(button);
		
		success.setBounds(10,110,300,25);
		panel.add(success);
		
		frame.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String user = userText.getText();
		String password = passwordText.getText();
		System.out.println(user +", "+ password);
		
		if (user.equals(AdminName)&& password.equals(AdminPassword)) {
			success.setText("Succesful Login");
			//create object to second box
			try {
				MainUI mainMenu= new MainUI();
				frame.setVisible(false); //you can't see me!
				frame.dispose(); //Destroy the JFrame object
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//mainMenu.setVisible(true);
			//SecondBox second = new SecondBox();
			//second.setVisible(true);
		}
		else {
			success.setText("Invalid username or password");
		}
	}

}
