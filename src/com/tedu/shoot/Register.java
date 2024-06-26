package com.tedu.shoot;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.tedu.Dao.Dao;
import com.tedu.Dao.JDBC;

public class Register extends JDialog{
	private JTextField oldPasswordField;

	private JPasswordField repeatPasswordField;

	private JPasswordField newPasswordField;

	private final Dao dao = Dao.getInstance();

	/**
	 * Launch the application
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			UIManager.setLookAndFeel(UIManager
					.getSystemLookAndFeelClassName());
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		try {
			Register dialog = new Register();
			dialog.setVisible(true);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog
	 */
	public Register() {
		super();
		setModal(true);
		setTitle("用户注册");
		this.setBounds(0, 0, 300,200);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();  
	    setLocation((dim.width - this.getWidth()) / 2, (dim.height - this.getHeight()) / 2);
	    setResizable(false);// 设置窗口不可以改变大小
	    
		final JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		Box box,boxV1,boxV2,boxV3;
		boxV1 = Box.createVerticalBox();
		boxV2 = Box.createVerticalBox();
		boxV3 = Box.createHorizontalBox();
		box = Box.createHorizontalBox();
		box.add(boxV1);
		box.add(boxV2);
		box.add(Box.createHorizontalStrut(50));
		
		final JLabel oldPasswordLabel = new JLabel();
		oldPasswordLabel.setText("用 户 名：");
		final JLabel newPasswordLabel = new JLabel();
		newPasswordLabel.setText("新 密 码：");
		final JLabel repeatPasswordLabel = new JLabel();
		repeatPasswordLabel.setText("重新输入：");
		
		boxV1.add(Box.createVerticalStrut(14));
		boxV1.add(oldPasswordLabel);
		boxV1.add(Box.createVerticalStrut(20));
		boxV1.add(newPasswordLabel);
		boxV1.add(Box.createVerticalStrut(20));
		
		boxV1.add(repeatPasswordLabel);
		boxV1.add(Box.createVerticalStrut(12));
		//boxV1.add(Box.createHorizontalStrut(40));
		
		oldPasswordField = new JTextField();
		oldPasswordField.setColumns(25);
		oldPasswordField.setMaximumSize(new Dimension(150,30));
		
		newPasswordField = new JPasswordField();
		newPasswordField.setColumns(25);
		newPasswordField.setMaximumSize(new Dimension(150,30));

		repeatPasswordField = new JPasswordField();
		repeatPasswordField.setColumns(25);
		repeatPasswordField.setMaximumSize(new Dimension(150,30));
		boxV2.add(Box.createVerticalStrut(8));
		boxV2.add(oldPasswordField);
		boxV2.add(Box.createVerticalStrut(8));
		boxV2.add(newPasswordField);
		boxV2.add(Box.createVerticalStrut(8));
		boxV2.add(repeatPasswordField);
		boxV2.add(Box.createVerticalStrut(8));
		
		final JButton submitButton = new JButton();
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String oldPassword = oldPasswordField.getText().trim();
				char[] newPasswords = newPasswordField.getPassword();
				String newPassword = turnCharsToString(newPasswords).trim();
				char[] repeatPasswords = repeatPasswordField.getPassword();
				String repeatPassword = turnCharsToString(repeatPasswords).trim();
				if (oldPassword.length() == 0 || newPassword.length() == 0
						|| repeatPassword.length() == 0) {
					JOptionPane.showMessageDialog(null, "信息不全！", "友情提示",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
					if (newPassword.equals(repeatPassword)) {
						Dao.getInstance().iPlayer(oldPassword, newPassword);
						JOptionPane.showMessageDialog(null, "注册成功！", "友情提示",
								JOptionPane.INFORMATION_MESSAGE);
						dispose(); //释放资源
					} else {
						JOptionPane.showMessageDialog(null,
								"您两次输入的新密码不一致，请确认后重新输入！", "友情提示",
								JOptionPane.INFORMATION_MESSAGE);
					}
			}
		});
		
		submitButton.setText("确定");
		boxV3.add(submitButton);

		final JButton exitButton = new JButton();
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		exitButton.setText("退出");
		boxV3.add(exitButton);
		panel.add(box);
		panel.add(boxV3);
		add(panel);
	}

	private String turnCharsToString(char[] chars) {
		StringBuffer strBuf = new StringBuffer();
		for (int i = 0; i < chars.length; i++) {
			strBuf.append(chars[i]);
		}
		return strBuf.toString().trim();
	}
	
}
