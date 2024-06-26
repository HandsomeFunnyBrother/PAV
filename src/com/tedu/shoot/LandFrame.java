package com.tedu.shoot;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.tedu.Dao.Dao;



public class LandFrame extends JFrame {

	private JPasswordField passwordField;// �����
	private JTextField username;    //�û����ı���
	private JLabel name;
	private JLabel password;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			UIManager.setLookAndFeel(UIManager
					.getSystemLookAndFeelClassName());
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		try {
			LandFrame frame = new LandFrame();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public LandFrame(){
		// �������ô��ڵ������Ϣ
		super();// ���ø���Ĺ��췽��
		setTitle("�ɻ���ս");// ���ô��ڵı���
		setResizable(false);// ���ô��ڲ����Ըı��С
		//setAlwaysOnTop(true);// ���ô���������ǰ��
		setBounds(100, 100, 500, 285);// ���ô��ڵĴ�С
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// ���õ��رմ���ʱִ�еĶ���
		setUndecorated(true);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();  
	    setLocation((dim.width - this.getWidth()) / 2, (dim.height - this.getHeight()) / 2);
		// ���潫����һ����������ӵ����ڵ�������
		final MPanel panel = new MPanel(this.getClass().getResource(
			"/imgs/bg.jpg"));// ����һ��������
			panel.setLayout(new GridBagLayout());// �������Ĳ��ֹ�����Ϊ�����鲼��
			this.add(panel);// �������ӵ�������

		

		final JPanel leftPanel = new JPanel();
		leftPanel.setOpaque(false);// �������ı���Ϊ͸��
		final GridBagConstraints gridBagConstraints_2 = new GridBagConstraints();
		gridBagConstraints_2.gridwidth= 1;
		gridBagConstraints_2.gridheight = 5;
		gridBagConstraints_2.gridy = 0;
		gridBagConstraints_2.gridx = 0;
		panel.add(leftPanel, gridBagConstraints_2);	
		
		final JPanel rightPanel = new JPanel();
		rightPanel.setOpaque(false);// �������ı���Ϊ͸��
		final GridBagConstraints gridBagConstraints_3 = new GridBagConstraints();
		gridBagConstraints_3.ipadx = 450;
		gridBagConstraints_3.gridwidth = 1;
		gridBagConstraints_3.gridheight = 1;
		gridBagConstraints_3.gridy = 0;
		gridBagConstraints_3.gridx = 1;
		panel.add(rightPanel, gridBagConstraints_3);
		
		Box boxU,boxP,boxud;
		boxU = Box.createHorizontalBox();
		boxP = Box.createHorizontalBox();
		boxP.add(Box.createHorizontalStrut(120));
        boxU.add(Box.createHorizontalStrut(120));
        boxud = Box.createVerticalBox();
		final JPanel userPanel = new JPanel();
		userPanel.setOpaque(false);
		userPanel.setBackground(Color.black);
		userPanel.setLayout(new BoxLayout(userPanel,BoxLayout.X_AXIS));
		
		name = new JLabel();
		password = new JLabel();
		name.setText("�û�����");
		password.setText("��  �룺");
		username = new JTextField(20);
		final GridBagConstraints gridBagConstraints_4 = new GridBagConstraints();// ���������鲼�ֹ���������
		gridBagConstraints_4.gridwidth = 1;
		gridBagConstraints_4.gridheight = 1;
		gridBagConstraints_4.gridy = 1;// ����������Ϊ2
		gridBagConstraints_4.gridx = 1;// ����������Ϊ2
		gridBagConstraints_4.anchor = GridBagConstraints.EAST;// ����Ϊ�������ʾ
		boxud.add(username);
		boxud.add(Box.createVerticalStrut(10));
		userPanel.add(name);
		userPanel.add(boxud);
		userPanel.add(boxU);
		
		panel.add(userPanel, gridBagConstraints_4);// �������ָ���Ĳ��ֹ�������ӵ������
		
		// ���������������
		final JPanel passPanel = new JPanel();
		passPanel.setOpaque(false);
		passPanel.setLayout(new BoxLayout(passPanel,BoxLayout.X_AXIS));
		passwordField = new JPasswordField();// ����������������
		passwordField.setColumns(20);// ������������ʾ���ַ���
		//passwordField.addFocusListener(new PasswordFieldFocusListener());// Ϊ�������ӽ��������
		final GridBagConstraints gridBagConstraints_5 = new GridBagConstraints();// ���������鲼�ֹ���������
		gridBagConstraints_5.gridwidth = 1;
		gridBagConstraints_5.gridheight = 1;
		gridBagConstraints_5.gridy = 2;// ����������Ϊ2
		gridBagConstraints_5.gridx = 1;// ����������Ϊ2
		gridBagConstraints_5.anchor = GridBagConstraints.EAST;// ����Ϊ�������ʾ
		passPanel.add(password);
		passPanel.add(passwordField);
		passPanel.add(boxP);
		panel.add(passPanel, gridBagConstraints_5);// �������ָ���Ĳ��ֹ�������ӵ������
		
		//��ѡ�����
		final JPanel buttonPane2 = new JPanel();// ����һ��������Ӱ�ť�����
		buttonPane2.setOpaque(false);// �������ı���Ϊ͸��
		buttonPane2.setLayout(new BoxLayout(buttonPane2, BoxLayout.X_AXIS));// ����������ˮƽ�䲼��
		final GridBagConstraints gridBagConstraints_6 = new GridBagConstraints();// ���������鲼�ֹ���������
		gridBagConstraints_6.gridheight = 1;
		gridBagConstraints_6.gridwidth = 1;
		gridBagConstraints_6.gridy = 3;// ����������Ϊ2
		gridBagConstraints_6.gridx = 1;// ����������Ϊ2
		gridBagConstraints_6.anchor = GridBagConstraints.WEST;// ����Ϊ�������ʾ
		panel.add(buttonPane2, gridBagConstraints_6);// �������ָ���Ĳ��ֹ�������ӵ������
		Box boxV1,boxV2;
        boxV1 = Box.createHorizontalBox();
        boxV2 = Box.createHorizontalBox();
        boxV2.add(Box.createHorizontalStrut(150));
        boxV1.add(Box.createHorizontalStrut(150));
        JLabel l = new JLabel();
        boxV1.add(l);
        buttonPane2.add(boxV1);
		ButtonGroup user = new ButtonGroup();
		
		// ����������һ���������������ť�����
		final JPanel buttonPanel = new JPanel();// ����һ��������Ӱ�ť�����
		buttonPanel.setOpaque(false);// �������ı���Ϊ͸��
		buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.X_AXIS));// ����������ˮƽ�䲼��
		final GridBagConstraints gridBagConstraints_7 = new GridBagConstraints();// ���������鲼�ֹ���������
		gridBagConstraints_7.gridwidth = 1;
		gridBagConstraints_7.gridheight = 1;
		gridBagConstraints_7.gridy = 4;// ����������Ϊ3
		gridBagConstraints_7.gridx = 1;// ����������Ϊ1
		gridBagConstraints_7.anchor = GridBagConstraints.WEST;// ����Ϊ�������ʾ
		panel.add(buttonPanel, gridBagConstraints_7);// �������ָ���Ĳ��ֹ�������ӵ������
		
		
        buttonPanel.add(boxV2);
		// ����������һ����¼��ť����������ӵ�������Ӱ�ť�������
		final JButton landButton = new JButton();// ������¼��ť�������
		landButton.setMargin(new Insets(0, 0, 0, 0));// ���ð�ť�߿�ͱ�ǩ֮��ļ��
		landButton.setContentAreaFilled(false);// ���ò����ư�ť����������
		landButton.setBorderPainted(false);// ���ò����ư�ť�ı߿�
		URL landUrl = this.getClass().getResource("/imgs/land_submit.png");// ���Ĭ������µ�¼��ť��ʾͼƬ��URL
		landButton.setIcon(new ImageIcon(landUrl));// ����Ĭ������µ�¼��ť��ʾ��ͼƬ
		URL landOverUrl = this.getClass().getResource(
						"/imgs/land_submit_over.png");// ��õ���꾭����¼��ťʱ��ʾͼƬ��URL
		landButton.setRolloverIcon(new ImageIcon(landOverUrl));// ���õ���꾭����¼��ťʱ��ʾ��ͼƬ
		URL landPressedUrl = this.getClass().getResource(
						"/imgs/land_submit_pressed.png");// ��õ���¼��ť������ʱ��ʾͼƬ��URL
		landButton.setPressedIcon(new ImageIcon(landPressedUrl));// ���õ���¼��ť������ʱ��ʾ��ͼƬ
		landButton.addActionListener(new LandButtonActionListener());// Ϊ��¼��ť����¼�������
		buttonPanel.add(landButton);// ����¼��ť��ӵ�������Ӱ�ť�������
		
		final JButton registerButton = new JButton();
		registerButton.setMargin(new Insets(0, 0, 0, 0));
		registerButton.setContentAreaFilled(false);
		registerButton.setBorderPainted(false);
		URL registerUrl = this.getClass().getResource("/imgs/register_exit.png");
		registerButton.setIcon(new ImageIcon(registerUrl));
		URL registerOverUrl = this.getClass()
				.getResource("/imgs/register_exit_over.png");
		registerButton.setRolloverIcon(new ImageIcon(registerOverUrl));
		URL registerPressedUrl = this.getClass().getResource(
				"/imgs/register_exit_pressed.png");
		registerButton.setPressedIcon(new ImageIcon(registerPressedUrl));
		registerButton.addActionListener(new registerButtonActionListener());
		buttonPanel.add(registerButton);
        
		final JButton resetButton = new JButton();
		resetButton.setMargin(new Insets(0, 0, 0, 0));
		resetButton.setContentAreaFilled(false);
		resetButton.setBorderPainted(false);
		URL resetUrl = this.getClass().getResource("/imgs/land_reset.png");
		resetButton.setIcon(new ImageIcon(resetUrl));
		URL resetOverUrl = this.getClass().getResource(
				"/imgs/land_reset_over.png");
		resetButton.setRolloverIcon(new ImageIcon(resetOverUrl));
		URL resetPressedUrl = this.getClass().getResource(
				"/imgs/land_reset_pressed.png");
		resetButton.setPressedIcon(new ImageIcon(resetPressedUrl));
		resetButton.addActionListener(new ResetButtonActionListener());
		buttonPanel.add(resetButton);

		final JButton exitButton = new JButton();
		exitButton.setMargin(new Insets(0, 0, 0, 0));
		exitButton.setContentAreaFilled(false);
		exitButton.setBorderPainted(false);
		URL exitUrl = this.getClass().getResource("/imgs/land_exit.png");
		exitButton.setIcon(new ImageIcon(exitUrl));
		URL exitOverUrl = this.getClass()
				.getResource("/imgs/land_exit_over.png");
		exitButton.setRolloverIcon(new ImageIcon(exitOverUrl));
		URL exitPressedUrl = this.getClass().getResource(
				"/imgs/land_exit_pressed.png");
		exitButton.setPressedIcon(new ImageIcon(exitPressedUrl));
		exitButton.addActionListener(new ExitButtonActionListener());
		buttonPanel.add(exitButton);
	}
	
	class LandButtonActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String user = username.getText().trim();
			if (user.equals("")) {// �鿴�Ƿ�ѡ���˵�¼�û�
				JOptionPane.showMessageDialog(null, "�û�������Ϊ�գ�", "������ʾ",
						JOptionPane.INFORMATION_MESSAGE);// ������ʾ
			}
			else{
				char[] passwords = passwordField.getPassword();// ��õ�¼�û�������
				String password = String.valueOf(passwords);
					String dbpass = (String) Dao.getInstance().sPlayerPassword(user);
					dbpass = dbpass.trim();
					System.out.println(dbpass);
					System.out.println(password);
					boolean player = false;
					if(dbpass.equals(password)){
						Vector list = Dao.getInstance().sPlyerName(); 
						for(int i = 0; i < list.size(); i++){
							String n = (String) list.get(i);
							n.trim();
							if(n.equals(user)){
								player = true;
								break;
							}
						}
						if(!player){
							Dao.getInstance().iScoreList(user,0);
						}
						System.out.println(list);
						GamePane.name = user;
						System.out.println(GamePane.name);
						//GamePane.mainFrame(user);
						//game.setVisible(true);
						MenuPane.plyer = user;
						setVisible(false);
					}
					else{
						JOptionPane.showMessageDialog(null, "������û�������", "������ʾ",
								JOptionPane.ERROR_MESSAGE);// ������ʾ
					}
			}
			
		}
	}

	class ExitButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			setVisible(false);
		}
		
	}

	class ResetButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			username.setText("");
			passwordField.setText("");
		}
		
	}
	class registerButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Register rs = new Register();
			rs.setVisible(true);
		}
		
	}
}

