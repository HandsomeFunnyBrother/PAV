package com.tedu.shoot;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

// �������࣬���𴴽���Ϸ���ںͲ˵�����
public class MainFrame extends JFrame {
	//�ָ���壬���ڷָ���Ϸ���Ͳ˵����
	public static JSplitPane split;
	public static final int WIDTH = 800;
	public static final int HEIGHT = 800;
	public static void main(String[] args) {
		// ������Ϸ���
		GamePane lgp = new GamePane();
		// �����˵����
		MenuPane rmp = new MenuPane();
		// �����ָ���壬����Ϸ���Ͳ˵����ˮƽ�ָ�
		split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, lgp, rmp);
		// ����������
		MainFrame win = new MainFrame();
		// ���ô��ڴ�С
		win.setSize(WIDTH, HEIGHT);
		// ��ȡ��Ļ�ߴ�
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		// ���ô���λ��Ϊ��Ļ����
		win.setLocation((dim.width - win.getWidth()) / 2, (dim.height - win.getHeight()) / 2);
		// ���ô��ڹرղ���Ϊ�˳�����
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// ���ָ������ӵ�������
		win.add(split);
		// ���ô��ڿɼ�
		win.setVisible(true);
		// ���ô��ڲ��ɵ�����С
		win.setResizable(false);
		// ���÷ָ����ķָ���λ��
		split.setDividerLocation(512);
		// ���÷ָ����ķָ����϶�
		split.setEnabled(false);
		// ������Ϸ
		lgp.startGame();
	}
}
