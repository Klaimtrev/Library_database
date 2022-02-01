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
public class AddAuthor implements ActionListener{
	
	
	private static JLabel FirstName;
	private static JTextField FN;
	private static JLabel LastName;
	private static JTextField LN;
	private static JLabel BirthDate;
	private static JTextField BD;
	private static JButton button;
	private static JLabel success;
	private static JFrame frame;
	public AddAuthor() throws SQLException {
		JPanel panel = new JPanel();
		frame = new JFrame();
		frame.setSize(450,200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		
		panel.setLayout(null);
		
		FirstName = new JLabel("First Name");
		FirstName.setBounds(10,20,80,25);
		panel.add(FirstName);
		
		FN = new JTextField(20);
		FN.setBounds(170,20,165,25);
		panel.add(FN);
		
		LastName = new JLabel("Last Name");
		LastName.setBounds(10,50,80,25);
		panel.add(LastName);
		
		LN = new JTextField();
		LN.setBounds(170,50,165,25);
		panel.add(LN);
		
		BirthDate = new JLabel("BirthDate YYYY-MM-DD");
		BirthDate.setBounds(10,80,150,25);
		panel.add(BirthDate);
		
		BD = new JTextField(10);
		BD.setBounds(170,80,165,25);
		panel.add(BD);
		
		success = new JLabel("");
		button = new JButton("Enter");
		button.setBounds(100,120,80,25);
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
		frame.setSize(450,200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		
		panel.setLayout(null);
		
		FirstName = new JLabel("First Name");
		FirstName.setBounds(10,20,80,25);
		panel.add(FirstName);
		
		FN = new JTextField(20);
		FN.setBounds(170,20,165,25);
		panel.add(FN);
		
		LastName = new JLabel("Last Name");
		LastName.setBounds(10,50,80,25);
		panel.add(LastName);
		
		LN = new JTextField();
		LN.setBounds(170,50,165,25);
		panel.add(LN);
		
		BirthDate = new JLabel("BirthDate YYYY-MM-DD");
		BirthDate.setBounds(10,80,150,25);
		panel.add(BirthDate);
		
		BD = new JTextField(10);
		BD.setBounds(170,80,165,25);
		panel.add(BD);
		
		success = new JLabel("");
		button = new JButton("Enter");
		button.setBounds(100,120,80,25);
		button.addActionListener(new AddAuthor());
		panel.add(button);
		
		success.setBounds(10,110,300,25);
		panel.add(success);
		
		frame.setVisible(true);
		*/
		// TODO Auto-generated method stub
		new AddAuthor();

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String FiN = FN.getText();
		String LaN = LN.getText();
		String BiD = BD.getText();
		
		String url = "jdbc:mysql://localhost/zion";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, "root", "");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
      
        
		String sql = "CALL Add_Author(" + "\'" + FiN + "\'" +","+ "\'" + LaN + "\'" +","+"\'"+ BiD +"\')"; // built the sql query
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
		
	}

}
