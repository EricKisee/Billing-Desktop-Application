package com.erickisee.app.ui;

import java.awt.Color;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.erickisee.app.db.Database;
import com.erickisee.app.models.User;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class Users extends JPanel {
	
	private Color lightBlue = new Color (239,242,248);
	private Color darkBlue = new Color (106,117,202);
	private Color white = new Color (255,255,255);
	private User user ;
	private JTable table;
	private DefaultTableModel tableModel;
	private Vector <User> users = new Vector<>();

	private JScrollPane scroll_table;
	private JLabel lblNewLabel;
	/**
	 * Create the panel.
	 */
	public Users(User user) {
		this.user = user;
		setBackground(lightBlue);
		setBounds(0, 0, 646, 561);
		setLayout(null);
		
		table = new JTable();
	    scroll_table = new JScrollPane(table);  
	    scroll_table.setBounds(10, 127, 626, 382);
		add(scroll_table);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(106, 117, 202));
		panel.setBounds(456, 520, 180, 30);
		add(panel);
		
		JLabel btnDeleteUser = new JLabel("Delete User");
		btnDeleteUser.setHorizontalAlignment(SwingConstants.CENTER);
		btnDeleteUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				if(i<0) {
					//no item selected
				}else {
					if(user.getRank().equalsIgnoreCase("Admin")) {
						User selectedUser = users.elementAt(i);
						if(user.getId()==selectedUser.getId()) {
							JOptionPane.showMessageDialog(null, "You cant delete your own account");
						}else {
							Database db = new Database();
							if(db.delete(selectedUser))
								JOptionPane.showMessageDialog(null, "Delete Successfull");
						}
					}else {
						JOptionPane.showMessageDialog(null, "Action not allowed");
					}
				}
			}
			
		});
		btnDeleteUser.setForeground(Color.WHITE);
		btnDeleteUser.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		btnDeleteUser.setBounds(0, 0, 180, 30);
		panel.add(btnDeleteUser);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Users.class.getResource("/com/erickisee/app/ui/images/users_b.png")));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 626, 105);
		add(lblNewLabel);
		populateTable();
	}
	
	private void populateTable() {
		tableModel = new DefaultTableModel();
		tableModel.addColumn("Name");
		tableModel.addColumn("Email");
		tableModel.addColumn("Rank");
		
		Database db = new Database();
		users = db.getUsers();
		for(int i =0; i<users.size();i++) {
			User user = users.get(i);
			String name = user.getName();
			String email = user.getEmail();
			String rank = user.getRank();
			tableModel.addRow(new Object[]{name, email, rank});
		}
		table.setModel(tableModel);	
		db.disconnect();
	}

}
