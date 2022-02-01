import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*; 
import java.sql.*;

import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
public class UpdateAddress implements ActionListener{
	
	private static JLabel userlabel;
	private static JTextField userText;
	private static JLabel passwordLabel;
	private static JTextField passwordText;
	private static JButton button;
	private static JLabel success;
	private static JFrame frame;
	
	public UpdateAddress() throws SQLException {
		JPanel panel = new JPanel();
		frame = new JFrame();
		frame.setSize(350,200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		
		panel.setLayout(null);
		
		userlabel = new JLabel("Customer First Name");
		userlabel.setBounds(20,20,140,25);
		panel.add(userlabel);
		
		userText = new JTextField(20);
		userText.setBounds(150,20,165,25);
		panel.add(userText);
		
		passwordLabel = new JLabel("New Address");
		passwordLabel.setBounds(20,50,140,25);
		panel.add(passwordLabel);
		
		passwordText = new JTextField();
		passwordText.setBounds(150,50,165,25);
		panel.add(passwordText);
		
		success = new JLabel("");
		button = new JButton("Enter");
		button.setBounds(100,80,80,25);
		button.addActionListener(this);
		panel.add(button);
		
		success.setBounds(10,110,300,25);
		panel.add(success);
		
		frame.setVisible(true);
        
	}

	public static void main(String[] args) throws SQLException {
		/*
		JPanel panel = new JPanel();
		frame = new JFrame();
		frame.setSize(350,200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		
		panel.setLayout(null);
		
		userlabel = new JLabel("customerName");
		userlabel.setBounds(10,20,80,25);
		panel.add(userlabel);
		
		userText = new JTextField(20);
		userText.setBounds(100,20,165,25);
		panel.add(userText);
		
		passwordLabel = new JLabel("newAddress");
		passwordLabel.setBounds(10,50,80,25);
		panel.add(passwordLabel);
		
		passwordText = new JPasswordField();
		passwordText.setBounds(100,50,165,25);
		panel.add(passwordText);
		
		success = new JLabel("");
		button = new JButton("Enter");
		button.setBounds(100,80,80,25);
		button.addActionListener(new UpdateAddress());
		panel.add(button);
		
		success.setBounds(10,110,300,25);
		panel.add(success);
		
		frame.setVisible(true);
		*/
		// TODO Auto-generated method stub
		new UpdateAddress();

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String customerName = userText.getText();
		String newAddress = passwordText.getText();
		
		String url = "jdbc:mysql://localhost/zion";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, "root", "");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
      
        
        String sql = "CALL Update_Address(" + "\'" + customerName + "\'" +","+ "\'" + newAddress + "\')"; // built the sql query
        PreparedStatement stmt2 = null;
		try {
			stmt2 = conn.prepareStatement(sql);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
			ResultSet rset = stmt2.executeQuery();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
			conn.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        frame.setVisible(false); //you can't see me!
		frame.dispose(); //Destroy the JFrame object
        //System.out.println(rset); 101 Main St
		//System.out.println(user +", "+ password);
		
        /*
		if (user.equals(customerName)&& password.equals(newAddress)) {
			success.setText("Succesful Login");
			//create object to second box
			try {
				//MainUI mainMenu= new MainUI();
				frame.setVisible(false); //you can't see me!
				frame.dispose(); //Destroy the JFrame object
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//mainMenu.setVisible(true);
			//SecondBox second = new SecondBox();
			//second.setVisible(true);
			 */
		}

}
