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

	private JPasswordField passwordField;// 密码框
	private JTextField username;    //用户名文本框
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
		// 首先设置窗口的相关信息
		super();// 调用父类的构造方法
		setTitle("飞机大战");// 设置窗口的标题
		setResizable(false);// 设置窗口不可以改变大小
		//setAlwaysOnTop(true);// 设置窗口总在最前方
		setBounds(100, 100, 500, 285);// 设置窗口的大小
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 设置当关闭窗口时执行的动作
		setUndecorated(true);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();  
	    setLocation((dim.width - this.getWidth()) / 2, (dim.height - this.getHeight()) / 2);
		// 下面将创建一个面板对象并添加到窗口的容器中
		final MPanel panel = new MPanel(this.getClass().getResource(
			"/imgs/bg.jpg"));// 创建一个面板对象
			panel.setLayout(new GridBagLayout());// 设置面板的布局管理器为网格组布局
			this.add(panel);// 将面板添加到窗体中

		

		final JPanel leftPanel = new JPanel();
		leftPanel.setOpaque(false);// 设置面板的背景为透明
		final GridBagConstraints gridBagConstraints_2 = new GridBagConstraints();
		gridBagConstraints_2.gridwidth= 1;
		gridBagConstraints_2.gridheight = 5;
		gridBagConstraints_2.gridy = 0;
		gridBagConstraints_2.gridx = 0;
		panel.add(leftPanel, gridBagConstraints_2);	
		
		final JPanel rightPanel = new JPanel();
		rightPanel.setOpaque(false);// 设置面板的背景为透明
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
		name.setText("用户名：");
		password.setText("密  码：");
		username = new JTextField(20);
		final GridBagConstraints gridBagConstraints_4 = new GridBagConstraints();// 创建网格组布局管理器对象
		gridBagConstraints_4.gridwidth = 1;
		gridBagConstraints_4.gridheight = 1;
		gridBagConstraints_4.gridy = 1;// 设置行索引为2
		gridBagConstraints_4.gridx = 1;// 设置列索引为2
		gridBagConstraints_4.anchor = GridBagConstraints.EAST;// 设置为靠左侧显示
		boxud.add(username);
		boxud.add(Box.createVerticalStrut(10));
		userPanel.add(name);
		userPanel.add(boxud);
		userPanel.add(boxU);
		
		panel.add(userPanel, gridBagConstraints_4);// 将组件按指定的布局管理器添加到面板中
		
		// 创建并设置密码框
		final JPanel passPanel = new JPanel();
		passPanel.setOpaque(false);
		passPanel.setLayout(new BoxLayout(passPanel,BoxLayout.X_AXIS));
		passwordField = new JPasswordField();// 创建密码框组件对象
		passwordField.setColumns(20);// 设置密码框可显示的字符数
		//passwordField.addFocusListener(new PasswordFieldFocusListener());// 为密码框添加焦点监听器
		final GridBagConstraints gridBagConstraints_5 = new GridBagConstraints();// 创建网格组布局管理器对象
		gridBagConstraints_5.gridwidth = 1;
		gridBagConstraints_5.gridheight = 1;
		gridBagConstraints_5.gridy = 2;// 设置行索引为2
		gridBagConstraints_5.gridx = 1;// 设置列索引为2
		gridBagConstraints_5.anchor = GridBagConstraints.EAST;// 设置为靠左侧显示
		passPanel.add(password);
		passPanel.add(passwordField);
		passPanel.add(boxP);
		panel.add(passPanel, gridBagConstraints_5);// 将组件按指定的布局管理器添加到面板中
		
		//单选框组件
		final JPanel buttonPane2 = new JPanel();// 创建一个用来添加按钮的面板
		buttonPane2.setOpaque(false);// 设置面板的背景为透明
		buttonPane2.setLayout(new BoxLayout(buttonPane2, BoxLayout.X_AXIS));// 设置面板采用水平箱布局
		final GridBagConstraints gridBagConstraints_6 = new GridBagConstraints();// 创建网格组布局管理器对象
		gridBagConstraints_6.gridheight = 1;
		gridBagConstraints_6.gridwidth = 1;
		gridBagConstraints_6.gridy = 3;// 设置行索引为2
		gridBagConstraints_6.gridx = 1;// 设置列索引为2
		gridBagConstraints_6.anchor = GridBagConstraints.WEST;// 设置为靠左侧显示
		panel.add(buttonPane2, gridBagConstraints_6);// 将组件按指定的布局管理器添加到面板中
		Box boxV1,boxV2;
        boxV1 = Box.createHorizontalBox();
        boxV2 = Box.createHorizontalBox();
        boxV2.add(Box.createHorizontalStrut(150));
        boxV1.add(Box.createHorizontalStrut(150));
        JLabel l = new JLabel();
        boxV1.add(l);
        buttonPane2.add(boxV1);
		ButtonGroup user = new ButtonGroup();
		
		// 创建并设置一个用来添加三个按钮的面板
		final JPanel buttonPanel = new JPanel();// 创建一个用来添加按钮的面板
		buttonPanel.setOpaque(false);// 设置面板的背景为透明
		buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.X_AXIS));// 设置面板采用水平箱布局
		final GridBagConstraints gridBagConstraints_7 = new GridBagConstraints();// 创建网格组布局管理器对象
		gridBagConstraints_7.gridwidth = 1;
		gridBagConstraints_7.gridheight = 1;
		gridBagConstraints_7.gridy = 4;// 设置行索引为3
		gridBagConstraints_7.gridx = 1;// 设置列索引为1
		gridBagConstraints_7.anchor = GridBagConstraints.WEST;// 设置为靠左侧显示
		panel.add(buttonPanel, gridBagConstraints_7);// 将组件按指定的布局管理器添加到面板中
		
		
        buttonPanel.add(boxV2);
		// 创建并设置一个登录按钮，并将其添加到用来添加按钮的面板中
		final JButton landButton = new JButton();// 创建登录按钮组件对象
		landButton.setMargin(new Insets(0, 0, 0, 0));// 设置按钮边框和标签之间的间隔
		landButton.setContentAreaFilled(false);// 设置不绘制按钮的内容区域
		landButton.setBorderPainted(false);// 设置不绘制按钮的边框
		URL landUrl = this.getClass().getResource("/imgs/land_submit.png");// 获得默认情况下登录按钮显示图片的URL
		landButton.setIcon(new ImageIcon(landUrl));// 设置默认情况下登录按钮显示的图片
		URL landOverUrl = this.getClass().getResource(
						"/imgs/land_submit_over.png");// 获得当鼠标经过登录按钮时显示图片的URL
		landButton.setRolloverIcon(new ImageIcon(landOverUrl));// 设置当鼠标经过登录按钮时显示的图片
		URL landPressedUrl = this.getClass().getResource(
						"/imgs/land_submit_pressed.png");// 获得当登录按钮被按下时显示图片的URL
		landButton.setPressedIcon(new ImageIcon(landPressedUrl));// 设置当登录按钮被按下时显示的图片
		landButton.addActionListener(new LandButtonActionListener());// 为登录按钮添加事件监听器
		buttonPanel.add(landButton);// 将登录按钮添加到用来添加按钮的面板中
		
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
			if (user.equals("")) {// 查看是否选择了登录用户
				JOptionPane.showMessageDialog(null, "用户名不能为空！", "友情提示",
						JOptionPane.INFORMATION_MESSAGE);// 弹出提示
			}
			else{
				char[] passwords = passwordField.getPassword();// 获得登录用户的密码
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
						JOptionPane.showMessageDialog(null, "密码或用户名错误", "友情提示",
								JOptionPane.ERROR_MESSAGE);// 弹出提示
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

