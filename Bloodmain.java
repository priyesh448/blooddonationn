import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTabbedPane;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.beans.Statement;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Bloodmain extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JTextField bgroup;
	private JTextField address;
	private JTextField phon;
	private JTextField ref;
	private JTextField willing;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bloodmain frame = new Bloodmain();
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
	Connection c=null;
	private JTextField blodgroup;
	private JTable table;
	private JTextField na;
	private JTextField bg;
	private JTextField ad;
	private JTextField ph;
	private JTextField re;
	private JTextField wi;
	private JTable updatetable;
	
	public Bloodmain() throws SQLException {
		setResizable(false);
		
		c=DriverManager.getConnection("JDBC:sqlite:C:\\Users\\priyesh\\Desktop\\eclipse\\BLOODDONATION\\Blood.sqlite");
		JOptionPane.showMessageDialog(null,"Connect Sucessfully");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1050, 700);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenu mnNew = new JMenu("New");
		mnFile.add(mnNew);
		
		JMenuItem mntmDelete = new JMenuItem("Delete");
		mnFile.add(mntmDelete);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBloodDonation = new JLabel("BLOOD DONATION");
		lblBloodDonation.setHorizontalAlignment(SwingConstants.CENTER);
		lblBloodDonation.setFont(new Font("Times New Roman", Font.BOLD, 31));
		lblBloodDonation.setBounds(12, 13, 1008, 100);
		contentPane.add(lblBloodDonation);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane.setFont(new Font("Times New Roman", Font.BOLD, 25));
		tabbedPane.setBounds(12, 136, 1008, 454);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("ADD DONER", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblAddDonationPerson = new JLabel("ADD DONATION PERSON INFORMATION");
		lblAddDonationPerson.setBackground(Color.CYAN);
		lblAddDonationPerson.setForeground(Color.BLUE);
		lblAddDonationPerson.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddDonationPerson.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblAddDonationPerson.setBounds(0, 44, 825, 72);
		panel.add(lblAddDonationPerson);
		
		JLabel lblNewLabel = new JLabel("NAME");
		lblNewLabel.setBackground(Color.GRAY);
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel.setBounds(57, 167, 164, 54);
		panel.add(lblNewLabel);
		
		name = new JTextField();
		name.setBounds(233, 167, 175, 54);
		panel.add(name);
		name.setColumns(10);
		
		JLabel lblBloodGroup = new JLabel("BLOOD GROUP");
		lblBloodGroup.setBackground(Color.PINK);
		lblBloodGroup.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblBloodGroup.setBounds(67, 234, 164, 54);
		panel.add(lblBloodGroup);
		
		bgroup = new JTextField();
		bgroup.setColumns(10);
		bgroup.setBounds(233, 234, 175, 54);
		panel.add(bgroup);
		
		JLabel lblAddress = new JLabel("ADDRESS");
		lblAddress.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblAddress.setBounds(57, 303, 164, 54);
		panel.add(lblAddress);
		
		address = new JTextField();
		address.setColumns(10);
		address.setBounds(233, 303, 175, 54);
		panel.add(address);
		
		JLabel phone = new JLabel("PHONE");
		phone.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		phone.setBounds(57, 365, 164, 54);
		panel.add(phone);
		
		phon = new JTextField();
		phon.setColumns(10);
		phon.setBounds(233, 370, 175, 54);
		panel.add(phon);
		
		JLabel lblReference = new JLabel("REFERENCE");
		lblReference.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblReference.setBounds(422, 167, 164, 54);
		panel.add(lblReference);
		
		ref = new JTextField();
		ref.setColumns(10);
		ref.setBounds(598, 167, 175, 54);
		panel.add(ref);
		
		JLabel lblWilling = new JLabel("WILLING");
		lblWilling.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblWilling.setBounds(422, 228, 164, 54);
		panel.add(lblWilling);
		
		willing = new JTextField();
		willing.setColumns(10);
		willing.setBounds(598, 228, 175, 54);
		panel.add(willing);
		
		JButton AddDoner = new JButton("ADD DONER");
		AddDoner.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nam=name.getText();
				String bloodgrp=bgroup.getText();
				String addres=address.getText();
				String phone=phon.getText();
				String refrence=ref.getText();
				String willin=willing.getText();
			
				String query="insert into Blooddoner (Name,Bgroup,Address,Phone,Refrence,Willing)"
						+ " values('"+nam+"','"+bloodgrp+"','"+addres+"','"+phone+"','"+refrence+"','"+willin+"')";
		
		try {
			java.sql.Statement st=c.createStatement();
			st.execute(query);
			JOptionPane.showMessageDialog(null, "DONER ADDED SUCSSFULLY");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
				
			}
		});
		AddDoner.setBackground(Color.BLUE);
		AddDoner.setForeground(Color.WHITE);
		AddDoner.setBounds(661, 388, 126, 25);
		panel.add(AddDoner);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(240, 240, 240));
		tabbedPane.addTab("SEARCH", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel label = new JLabel("BLOOD GROUP");
		label.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		label.setBackground(Color.PINK);
		label.setBounds(70, 33, 190, 54);
		panel_1.add(label);
		
		blodgroup = new JTextField();
		blodgroup.setColumns(10);
		blodgroup.setBounds(285, 35, 175, 54);
		panel_1.add(blodgroup);
		
		JButton btnSearch = new JButton("search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			
				String bloodgrp=blodgroup.getText();
				
				
				String query1="select * from Blooddoner where Bgroup='"+bloodgrp+"'";
		
				
				try {
			java.sql.Statement st1=c.createStatement();
			
			ResultSet rs=st1.executeQuery(query1);
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
				
				
				
				
			}
		});
		btnSearch.setBackground(Color.BLACK);
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnSearch.setForeground(Color.RED);
		btnSearch.setBounds(579, 39, 97, 42);
		panel_1.add(btnSearch);
		
		JScrollPane searchtable = new JScrollPane();
		searchtable.setBounds(34, 127, 737, 334);
		panel_1.add(searchtable);
		
		table = new JTable();
		searchtable.setViewportView(table);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("UPDATE", null, panel_2, null);
		panel_2.setLayout(null);
		
		na = new JTextField();
		na.setEditable(false);
		na.setColumns(10);
		na.setBounds(208, 13, 175, 54);
		panel_2.add(na);
		
		JLabel label_1 = new JLabel("NAME");
		label_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		label_1.setBackground(Color.GRAY);
		label_1.setBounds(32, 13, 164, 54);
		panel_2.add(label_1);
		
		JLabel label_2 = new JLabel("BLOOD GROUP");
		label_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		label_2.setBackground(Color.PINK);
		label_2.setBounds(32, 78, 164, 54);
		panel_2.add(label_2);
		
		bg = new JTextField();
		bg.setColumns(10);
		bg.setBounds(208, 80, 175, 54);
		panel_2.add(bg);
		
		JLabel label_3 = new JLabel("ADDRESS");
		label_3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		label_3.setBounds(32, 149, 164, 54);
		panel_2.add(label_3);
		
		ad = new JTextField();
		ad.setColumns(10);
		ad.setBounds(208, 149, 175, 54);
		panel_2.add(ad);
		
		JLabel label_4 = new JLabel("PHONE");
		label_4.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		label_4.setBounds(32, 211, 164, 54);
		panel_2.add(label_4);
		
		ph = new JTextField();
		ph.setColumns(10);
		ph.setBounds(208, 216, 175, 54);
		panel_2.add(ph);
		
		JLabel label_5 = new JLabel("REFERENCE");
		label_5.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		label_5.setBounds(397, 13, 164, 54);
		panel_2.add(label_5);
		
		re = new JTextField();
		re.setColumns(10);
		re.setBounds(573, 13, 175, 54);
		panel_2.add(re);
		
		JLabel label_6 = new JLabel("WILLING");
		label_6.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		label_6.setBounds(397, 74, 164, 54);
		panel_2.add(label_6);
		
		wi = new JTextField();
		wi.setColumns(10);
		wi.setBounds(573, 74, 175, 54);
		panel_2.add(wi);
		
		JButton btnUpdate = new JButton("UPDATE DONER");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String nam1=na.getText();
				String bloodgrp1=bg.getText();
				String addres1=ad.getText();
				String phone1=ph.getText();
				String refrence1=re.getText();
				String willin1=wi.getText();
			
				String query5="Update Blooddoner set Name='"+nam1+"',Bgroup='"+bloodgrp1+"',Address='"+addres1+"',Phone='"+phone1+"',Refrence='"+refrence1+"',Willing='"+willin1+"' where Name='"+nam1+"'";
		
		try {
			java.sql.Statement st5=c.createStatement();
			st5.execute(query5);
			JOptionPane.showMessageDialog(null, "DONER UPDATED SUCSSFULLY");
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
			
				
				
				
				
			}
		});
		btnUpdate.setForeground(Color.BLACK);
		btnUpdate.setBackground(Color.BLUE);
		btnUpdate.setBounds(622, 166, 126, 25);
		panel_2.add(btnUpdate);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(42, 278, 783, 158);
		panel_2.add(scrollPane);
		
		updatetable = new JTable();
		updatetable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				int row=updatetable.getSelectedRow();
				String n=updatetable.getModel().getValueAt(row, 0).toString();
				String query4="select * from Blooddoner where Name='"+n+"'";
				
				try {
					java.sql.Statement st4=c.createStatement();
					ResultSet rs4=st4.executeQuery(query4);
					while(rs4.next()){
						na.setText(rs4.getString("Name"));
						bg.setText(rs4.getString("Bgroup"));
						ad.setText(rs4.getString("Address"));
						ph.setText(rs4.getString("Phone"));
						re.setText(rs4.getString("Refrence"));
						wi.setText(rs4.getString("Willing"));
					
					
					
					}
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
			}
		});
		scrollPane.setViewportView(updatetable);
		
		JButton btnShow = new JButton("SHOW");
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				
				
		
				
				try {
					String query2="select * from Blooddoner";
			java.sql.Statement st2=c.createStatement();
			
			ResultSet rs2=st2.executeQuery(query2);
			updatetable.setModel(DbUtils.resultSetToTableModel(rs2));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
				
				
				
				
			}
		});
		btnShow.setForeground(Color.BLACK);
		btnShow.setBackground(Color.BLUE);
		btnShow.setBounds(622, 228, 126, 25);
		panel_2.add(btnShow);
	}
}
