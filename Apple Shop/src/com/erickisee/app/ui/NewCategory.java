package com.erickisee.app.ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.erickisee.app.db.Database;
import com.erickisee.app.models.Category;
import com.erickisee.app.models.User;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class NewCategory extends JPanel {
	
	private Color lightBlue = new Color (239,242,248);
	private Color darkBlue = new Color (106,117,202);
	private Color white = new Color (255,255,255);
	private JTextField tfName;
	private User user;

	/**
	 * Create the panel.
	 */
	public NewCategory(User user) {
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
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(150, 241, 100, 30);
		add(lblDescription);
		
		JTextArea tfDescription = new JTextArea();
		tfDescription.setLineWrap(true);
		tfDescription.setBounds(299, 244, 200, 140);
		add(tfDescription);
		
		JPanel btnAdd = new JPanel();
		
		btnAdd.setLayout(null);
		btnAdd.setBackground(new Color(106, 117, 202));
		btnAdd.setBounds(209, 407, 180, 30);
		add(btnAdd);
		
		JLabel lblAddCategory = new JLabel("Add Category");
		lblAddCategory.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddCategory.setForeground(Color.WHITE);
		lblAddCategory.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		lblAddCategory.setBounds(0, 0, 180, 30);
		btnAdd.add(lblAddCategory);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(NewCategory.class.getResource("/com/erickisee/app/ui/images/categories_b.png")));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(10, 11, 626, 105);
		add(label_1);

		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String name = tfName.getText();
				String description = tfDescription.getText();
				if(name.isEmpty()||description.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Fill all fields");
				}else {
					Category category = new Category();
					category.setDescription(description);
					category.setName(name);
					Database db = new Database();
					if(db.saveCategory(category)) {
						JOptionPane.showMessageDialog(null, "Category added Successfull.");
						tfName.setText("");
						tfDescription.setText("");
					}
					db.disconnect();
				}
			}
		});
	}
}
