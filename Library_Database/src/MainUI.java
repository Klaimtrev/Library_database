import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.io.*; 
import java.sql.*;


public class MainUI implements ActionListener {
	private int count = 0;
	private JLabel label;
	private JLabel label2;
	private JLabel label3;
	private JButton button;
	private JButton secondB;
	private JButton updateR;
	private JButton exitB;
	//private JFrame frame;
	private JPanel panel;
	private static JFrame frame;
	//private String DBText;
	private JTable jt;
	private JScrollPane jps;
	private JTable jtt;
	private JScrollPane jpss;
	
	public String[][] ReadAuthors() throws SQLException {
		String DBText [][];
		String url = "jdbc:mysql://localhost/zion"; 
		Connection conn = DriverManager.getConnection(url, "root", "");
		String sql= "CALL 	Author_Report()";
		PreparedStatement stmt2 = conn.prepareStatement(sql);
	    ResultSet rset = stmt2.executeQuery();
	    Vector<String> vec_tor = new Vector<String>();
	    while (rset.next ()) {
	    	vec_tor.add(rset.getString ("Author_ID"));
	    	vec_tor.add(rset.getString ("First_Name"));
	    	vec_tor.add(rset.getString("Last_Name"));
	    	vec_tor.add(rset.getString("Number_of_Books"));
	    }
	    DBText=new String[Integer.parseInt(vec_tor.get(vec_tor.size()-4))][4];
	    int count = 0;
	    for (int row = 0; row < Integer.parseInt(vec_tor.get(vec_tor.size()-4)); row++)
	    {
	        for (int col = 0; col < 4; col++)
	        {
	             DBText[row][col]= vec_tor.get(count);
	             count++;
	             
	        }
	     }
	    System.out.println("Vector: " + vec_tor);
		conn.close();
		return DBText;
	}
	public String[][] ReadBooks() throws SQLException {
		String DBText [][];
		String url = "jdbc:mysql://localhost/zion"; 
		Connection conn = DriverManager.getConnection(url, "root", "");
		String sql= "CALL 	Book_Report()";
		PreparedStatement stmt2 = conn.prepareStatement(sql);
	    ResultSet rset = stmt2.executeQuery();
	    Vector<String> vec_tor = new Vector<String>();
	    while (rset.next ()) {
	    	vec_tor.add(rset.getString ("Book_ID"));
	    	vec_tor.add(rset.getString ("Book_Title"));
	    	vec_tor.add(rset.getString("Quantity"));
	    	vec_tor.add(rset.getString("Total_Sales"));
	    }
	    DBText=new String[6][4];
	    int count = 0;
	    for (int row = 0; row < 6; row++)
	    {
	        for (int col = 0; col < 4; col++)
	        {
	             DBText[row][col]= vec_tor.get(count);
	             count++;
	             
	        }
	     }
		conn.close();
		return DBText;
	}
	public void SetUpTableAuthor() throws SQLException {
		String [] columns = {"Author_ID", "First_Name", "Last_Name","Number_of_Books" };
		String [][] data = ReadAuthors();
		
		jt = new JTable(data, columns);
		jt.setPreferredScrollableViewportSize(new Dimension(450,63));
		jt.setFillsViewportHeight(true);
		
		jps= new JScrollPane(jt);
	}
	public void SetUpTableBook() throws SQLException {
		String [] columns2 = {"Book_ID", "Book_Title", "Quantity","Total_Sales" };
		String [][] data2 = ReadBooks();
		
		jtt = new JTable(data2, columns2);
		jtt.setPreferredScrollableViewportSize(new Dimension(450,63));
		jtt.setFillsViewportHeight(true);
		
		jpss= new JScrollPane(jtt);
	}

	public MainUI() throws SQLException {
		frame = new JFrame();
		
		SetUpTableAuthor();
		SetUpTableBook();
		
		
		button = new JButton("Update Address");
		button.addActionListener(this);
		label = new JLabel("");
		secondB = new JButton("Add New Author");
		secondB.addActionListener(this);
		label2 = new JLabel("");
		updateR = new JButton("Update Tables");
		updateR.addActionListener(this);
		label3 = new JLabel("");
		exitB= new JButton("EXIT");
		exitB.addActionListener(this);
		
		
		
		panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(100,100,10,10));
		panel.setLayout(new GridLayout(0,1));
		panel.add(button);
		panel.add(label);
		panel.add(secondB);
		panel.add(label2);
		panel.add(updateR);
		panel.add(jps);
		panel.add(jpss);
		panel.add(label3);
		panel.add(exitB);
		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Our GUI");
		frame.pack();
		frame.setVisible(true);
		
		System.out.println(ReadAuthors());

	}

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		new MainUI();

	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == button) {
			try {
				UpdateAddress UpdateA= new UpdateAddress();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (e.getSource() == secondB) {
			try {
				AddAuthor AddA= new AddAuthor();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (e.getSource() == updateR) {
			//label2.setText(DBText);
			try {
				SetUpTableAuthor();
				SetUpTableBook();
				
				panel.removeAll();
				panel.revalidate();
				panel.repaint();
				button = new JButton("Update Address");
				button.addActionListener(this);
				label = new JLabel("");
				secondB = new JButton("Show Author Report");
				secondB.addActionListener(this);
				label2 = new JLabel("");
				updateR = new JButton("Update Tables");
				updateR.addActionListener(this);
				label3 = new JLabel("");
				exitB= new JButton("EXIT");
				exitB.addActionListener(this);
				
				
				
				panel = new JPanel();
				panel.setBorder(BorderFactory.createEmptyBorder(100,100,10,10));
				panel.setLayout(new GridLayout(0,1));
				panel.add(button);
				panel.add(label);
				panel.add(secondB);
				panel.add(label2);
				panel.add(updateR);
				panel.add(jps);
				panel.add(jpss);
				panel.add(label3);
				panel.add(exitB);
				frame.add(panel, BorderLayout.CENTER);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setTitle("Our GUI");
				frame.pack();
				frame.setVisible(true);
				/*
				MainUI UIN= new MainUI();
				frame.setVisible(false); //you can't see me!
				frame.dispose(); //Destroy the JFrame object
				*/
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (e.getSource() == exitB) {
			frame.setVisible(false); //you can't see me!
			frame.dispose(); //Destroy the JFrame object
		}
		
	}

}
