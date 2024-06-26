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
		startGame.setMargin(new Insets(0, 0, 0, 0));// ���ð�ť�߿�ͱ�ǩ֮��ļ��
		startGame.setContentAreaFilled(false);// ���ò����ư�ť����������
		startGame.setBorderPainted(false);// ���ò����ư�ť�ı߿�
		URL landUrl = this.getClass().getResource("/imgs/startGame.png");// ���Ĭ������µ�¼��ť��ʾͼƬ��URL
		startGame.setIcon(new ImageIcon(landUrl));// ����Ĭ������µ�¼��ť��ʾ��ͼƬ
		startGame.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(GamePane.name != null){
					GamePane.state = RUNNING;
					start = true;
				}else{
					JOptionPane.showMessageDialog(null, "���ȵ�¼/ע��", "������ʾ",
							JOptionPane.ERROR_MESSAGE);// ������ʾ
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
			
		});// Ϊ��¼��ť����¼�������
		
	
		restart.setMargin(new Insets(0, 0, 0, 0));// ���ð�ť�߿�ͱ�ǩ֮��ļ��
		restart.setContentAreaFilled(false);// ���ò����ư�ť����������
		restart.setBorderPainted(false);// ���ò����ư�ť�ı߿�
		URL restartURL = this.getClass().getResource("/imgs/restart.png");// ���Ĭ������µ�¼��ť��ʾͼƬ��URL
		restart.setIcon(new ImageIcon(restartURL));// ����Ĭ������µ�¼��ť��ʾ��ͼƬ
		restart.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(GamePane.name != null){
					GamePane.restart = true;
					start = true;
				}
			}
			
		});// Ϊ��¼��ť����¼�������
		
		
		loginGame.setMargin(new Insets(0, 0, 0, 0));// ���ð�ť�߿�ͱ�ǩ֮��ļ��
		loginGame.setContentAreaFilled(false);// ���ò����ư�ť����������
		loginGame.setBorderPainted(false);// ���ò����ư�ť�ı߿�
		URL loginURL = this.getClass().getResource("/imgs/loginGame.png");// ���Ĭ������µ�¼��ť��ʾͼƬ��URL
		loginGame.setIcon(new ImageIcon(loginURL));// ����Ĭ������µ�¼��ť��ʾ��ͼƬ
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
			
		});// Ϊ��¼��ť����¼�������
		
		
		exitGame.setMargin(new Insets(0, 0, 0, 0));// ���ð�ť�߿�ͱ�ǩ֮��ļ��
		exitGame.setContentAreaFilled(false);// ���ò����ư�ť����������
		exitGame.setBorderPainted(false);// ���ò����ư�ť�ı߿�
		URL exitURL = this.getClass().getResource("/imgs/exitGame.png");// ���Ĭ������µ�¼��ť��ʾͼƬ��URL
		exitGame.setIcon(new ImageIcon(exitURL));// ����Ĭ������µ�¼��ť��ʾ��ͼƬ
		exitGame.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
			
		});// Ϊ��¼��ť����¼�������
		
		
		sorceList.setMargin(new Insets(0, 0, 0, 0));// ���ð�ť�߿�ͱ�ǩ֮��ļ��
		sorceList.setContentAreaFilled(false);// ���ò����ư�ť����������
		sorceList.setBorderPainted(false);// ���ò����ư�ť�ı߿�
		URL listURL = this.getClass().getResource("/imgs/sorceList.png");// ���Ĭ������µ�¼��ť��ʾͼƬ��URL
		sorceList.setIcon(new ImageIcon(listURL));// ����Ĭ������µ�¼��ť��ʾ��ͼƬ
		sorceList.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SorceList sl = new SorceList();
				sl.setVisible(true);
				sl.setAlwaysOnTop(true);
			}
			
		});// Ϊ��¼��ť����¼�������
		
		
		conGame.setMargin(new Insets(0, 0, 0, 0));// ���ð�ť�߿�ͱ�ǩ֮��ļ��
		conGame.setContentAreaFilled(false);// ���ò����ư�ť����������
		conGame.setBorderPainted(false);// ���ò����ư�ť�ı߿�
		URL conURL = this.getClass().getResource("/imgs/conGame.png");// ���Ĭ������µ�¼��ť��ʾͼƬ��URL
		conGame.setIcon(new ImageIcon(conURL));// ����Ĭ������µ�¼��ť��ʾ��ͼƬ
		conGame.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(start){
					GamePane.state = RUNNING;
				}
			}
			
		});// Ϊ��¼��ť����¼�������
		this.add(startGame);
		this.add(conGame);
		this.add(restart);
		this.add(loginGame);
		this.add(sorceList);
		this.add(exitGame);
	}
}
