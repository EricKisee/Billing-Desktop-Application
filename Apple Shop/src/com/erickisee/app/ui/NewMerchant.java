package com.erickisee.app.ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.erickisee.app.db.Database;
import com.erickisee.app.models.Merchant;
import com.erickisee.app.models.User;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class NewMerchant extends JPanel {
	
	private Color lightBlue = new Color (239,242,248);
	private Color darkBlue = new Color (106,117,202);
	private Color white = new Color (255,255,255);
	private JTextField tfName;
	private JTextField tfPhone;
	private JTextField tfEmail;
	private User user;

	/**
	 * Create the panel.
	 */
	public NewMerchant(User user) {
		this.user = user;
		setBackground(lightBlue);
		setBounds(0, 0, 646, 561);
		setLayout(null);
		
		JLabel label = new JLabel("Name");
		label.setBounds(150, 200, 100, 30);
		add(label);
		
		tfName = new JTextField();
		tfName.setColumns(10);
		tfName.setBounds(299, 200, 200, 30);
		add(tfName);
		
		JLabel label_1 = new JLabel("Phone");
		label_1.setBounds(150, 241, 100, 30);
		add(label_1);
		
		tfPhone = new JTextField();
		tfPhone.setColumns(10);
		tfPhone.setBounds(299, 241, 200, 30);
		add(tfPhone);
		
		JLabel label_2 = new JLabel("Email");
		label_2.setBounds(150, 282, 100, 30);
		add(label_2);
		
		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBounds(299, 282, 200, 30);
		add(tfEmail);
		
		JPanel btnAdd = new JPanel();
		btnAdd.setLayout(null);
		btnAdd.setBackground(new Color(106, 117, 202));
		btnAdd.setBounds(240, 360, 180, 30);
		add(btnAdd);
		
		JLabel lblAddMerchant = new JLabel("Add Merchant");
		lblAddMerchant.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddMerchant.setForeground(Color.WHITE);
		lblAddMerchant.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		lblAddMerchant.setBounds(0, 0, 180, 30);
		btnAdd.add(lblAddMerchant);
		
		JLabel label_3 = new JLabel("");
		label_3.setIcon(new ImageIcon(NewMerchant.class.getResource("/com/erickisee/app/ui/images/merchants_b.png")));
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(10, 11, 626, 105);
		add(label_3);
		

		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String name = tfName.getText();
				String email = tfEmail.getText();
				String phone = tfPhone.getText();
				if(name.isEmpty()||email.isEmpty()||phone.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Fill all fields.");
				}else {
					Merchant merchant = new Merchant ();
					merchant.setEmail(email);
					merchant.setName(name);
					merchant.setPhoneNumber(phone);
					
					Database db = new Database();
					if(db.saveMerchant(merchant)) {
						JOptionPane.showMessageDialog(null, "Merchant Registration Successfull.");
						tfName.setText("");
						tfEmail.setText("");
						tfPhone.setText("");
					}
					db.disconnect();
				}
			}
		});

	}

}
