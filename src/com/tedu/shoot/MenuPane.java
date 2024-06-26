package com.tedu.shoot;

import java.awt.Insets;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class MenuPane extends Panel {
	public static String plyer;
	public static String Password;
	
	public JButton startGame = new JButton();
	public JButton restart = new JButton();
	public JButton conGame = new JButton();
	public JButton loginGame = new JButton();
	public JButton sorceList = new JButton();
	public JButton exitGame = new JButton();
	
	public static final int CANSTART = 1;
	public static final int RUNNING = 0;
	public static final int PAUSE = 2;
	public static final int GAMEOVER = 3;
	
	public boolean start = false;
	
	public MenuPane(){
		startGame.setMargin(new Insets(0, 0, 0, 0));// 设置按钮边框和标签之间的间隔
		startGame.setContentAreaFilled(false);// 设置不绘制按钮的内容区域
		startGame.setBorderPainted(false);// 设置不绘制按钮的边框
		URL landUrl = this.getClass().getResource("/imgs/startGame.png");// 获得默认情况下登录按钮显示图片的URL
		startGame.setIcon(new ImageIcon(landUrl));// 设置默认情况下登录按钮显示的图片
		startGame.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(GamePane.name != null){
					GamePane.state = RUNNING;
					start = true;
				}else{
					JOptionPane.showMessageDialog(null, "请先登录/注册", "友情提示",
							JOptionPane.ERROR_MESSAGE);// 弹出提示
					try {
						UIManager.setLookAndFeel(UIManager
								.getSystemLookAndFeelClassName());
					} catch (Exception exception) {
						exception.printStackTrace();
					}			
						LandFrame frame = new LandFrame();
						frame.setVisible(true);
						
				}
			}
			
		});// 为登录按钮添加事件监听器
		
	
		restart.setMargin(new Insets(0, 0, 0, 0));// 设置按钮边框和标签之间的间隔
		restart.setContentAreaFilled(false);// 设置不绘制按钮的内容区域
		restart.setBorderPainted(false);// 设置不绘制按钮的边框
		URL restartURL = this.getClass().getResource("/imgs/restart.png");// 获得默认情况下登录按钮显示图片的URL
		restart.setIcon(new ImageIcon(restartURL));// 设置默认情况下登录按钮显示的图片
		restart.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(GamePane.name != null){
					GamePane.restart = true;
					start = true;
				}
			}
			
		});// 为登录按钮添加事件监听器
		
		
		loginGame.setMargin(new Insets(0, 0, 0, 0));// 设置按钮边框和标签之间的间隔
		loginGame.setContentAreaFilled(false);// 设置不绘制按钮的内容区域
		loginGame.setBorderPainted(false);// 设置不绘制按钮的边框
		URL loginURL = this.getClass().getResource("/imgs/loginGame.png");// 获得默认情况下登录按钮显示图片的URL
		loginGame.setIcon(new ImageIcon(loginURL));// 设置默认情况下登录按钮显示的图片
		loginGame.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					UIManager.setLookAndFeel(UIManager
							.getSystemLookAndFeelClassName());
				} catch (Exception exception) {
					exception.printStackTrace();
				}			
					LandFrame frame = new LandFrame();
					frame.setVisible(true);
					
			}
			
		});// 为登录按钮添加事件监听器
		
		
		exitGame.setMargin(new Insets(0, 0, 0, 0));// 设置按钮边框和标签之间的间隔
		exitGame.setContentAreaFilled(false);// 设置不绘制按钮的内容区域
		exitGame.setBorderPainted(false);// 设置不绘制按钮的边框
		URL exitURL = this.getClass().getResource("/imgs/exitGame.png");// 获得默认情况下登录按钮显示图片的URL
		exitGame.setIcon(new ImageIcon(exitURL));// 设置默认情况下登录按钮显示的图片
		exitGame.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
			
		});// 为登录按钮添加事件监听器
		
		
		sorceList.setMargin(new Insets(0, 0, 0, 0));// 设置按钮边框和标签之间的间隔
		sorceList.setContentAreaFilled(false);// 设置不绘制按钮的内容区域
		sorceList.setBorderPainted(false);// 设置不绘制按钮的边框
		URL listURL = this.getClass().getResource("/imgs/sorceList.png");// 获得默认情况下登录按钮显示图片的URL
		sorceList.setIcon(new ImageIcon(listURL));// 设置默认情况下登录按钮显示的图片
		sorceList.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SorceList sl = new SorceList();
				sl.setVisible(true);
				sl.setAlwaysOnTop(true);
			}
			
		});// 为登录按钮添加事件监听器
		
		
		conGame.setMargin(new Insets(0, 0, 0, 0));// 设置按钮边框和标签之间的间隔
		conGame.setContentAreaFilled(false);// 设置不绘制按钮的内容区域
		conGame.setBorderPainted(false);// 设置不绘制按钮的边框
		URL conURL = this.getClass().getResource("/imgs/conGame.png");// 获得默认情况下登录按钮显示图片的URL
		conGame.setIcon(new ImageIcon(conURL));// 设置默认情况下登录按钮显示的图片
		conGame.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(start){
					GamePane.state = RUNNING;
				}
			}
			
		});// 为登录按钮添加事件监听器
		this.add(startGame);
		this.add(conGame);
		this.add(restart);
		this.add(loginGame);
		this.add(sorceList);
		this.add(exitGame);
	}
}
