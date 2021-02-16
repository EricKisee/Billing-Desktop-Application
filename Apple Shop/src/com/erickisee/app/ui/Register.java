package com.erickisee.app.ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.erickisee.app.db.Database;
import com.erickisee.app.models.User;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;

public class Register extends JPanel {
	
	private Color lightBlue = new Color (239,242,248);
	private Color darkBlue = new Color (106,117,202);
	private Color white = new Color (255,255,255);
	private JTextField tfEmail;
	private JPasswordField tfPassword;
	private JTextField tfName;
	private User user;
	
	/**
	 * Create the panel.
	 */
	public Register(User user) {
		this.user = user;
		setBackground(lightBlue);
		setBounds(0, 0, 646, 561);
		setLayout(null);
		
		JLabel label = new JLabel("Email");
		label.setBounds(150, 282, 100, 30);
		add(label);
		
		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBounds(299, 282, 200, 30);
		add(tfEmail);
		
		JLabel label_1 = new JLabel("Password");
		label_1.setBounds(150, 323, 100, 30);
		add(label_1);
		
		tfPassword = new JPasswordField();
		tfPassword.setBounds(299, 323, 200, 30);
		add(tfPassword);

		JPanel btnRegister = new JPanel();
		btnRegister.setLayout(null);
		btnRegister.setBackground(new Color(106, 117, 202));
		btnRegister.setBounds(244, 392, 180, 30);
		add(btnRegister);
		
		JLabel lblRegister = new JLabel("Register");
		lblRegister.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegister.setForeground(Color.WHITE);
		lblRegister.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		lblRegister.setBounds(0, 0, 180, 30);
		btnRegister.add(lblRegister);
		
		JLabel lblPhone = new JLabel("Rank");
		lblPhone.setBounds(150, 241, 100, 30);
		add(lblPhone);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(150, 200, 100, 30);
		add(lblName);
		
		tfName = new JTextField();
		tfName.setColumns(10);
		tfName.setBounds(299, 200, 200, 30);
		add(tfName);
		
		String ranks [] = {"Admin","Staff"};
		JComboBox comboBoxRank = new JComboBox(ranks);
		comboBoxRank.setBounds(299, 241, 200, 30);
		add(comboBoxRank);
		

		btnRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(user.getRank().equalsIgnoreCase("Admin")) {
				
					String name = tfName.getText();
					String email = tfEmail.getText();
					String password = tfPassword.getText();
					String rank = comboBoxRank.getSelectedItem().toString();
					
					if(name.isEmpty()||email.isEmpty()||
							password.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Fill all fields.");
					}else {
						User user = new User();
						user.setEmail(email);
						user.setName(name);
						user.setPassword(password);
						user.setRank(rank);
						
						Database db = new Database();
						if(db.saveUser(user)) {
							JOptionPane.showMessageDialog(null, "Registration Successfull. User can now login.");
							tfName.setText("");
							tfEmail.setText("");
							tfPassword.setText("");
							
						}
						db.disconnect();
							
					}
				}else {
				JOptionPane.showMessageDialog(null, "Only Administators can register other users");
			}
			}
		});

	}
}
