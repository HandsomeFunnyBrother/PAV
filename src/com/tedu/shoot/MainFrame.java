package com.tedu.shoot;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

public class MainFrame extends JFrame {
	
	public static JSplitPane split;
	public static final int WIDTH = 800;
	public static final int HEIGHT = 768;
	public static void main(String[] args) {
		GamePane lgp = new GamePane();
		MenuPane rmp = new MenuPane();
		split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, lgp, rmp);
		MainFrame win = new MainFrame();
		win.setSize(WIDTH, HEIGHT);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize(); 
		win.setLocation((dim.width - win.getWidth()) / 2, (dim.height - win.getHeight()) / 2);
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);;
		win.add(split);
		win.setVisible(true);
		win.setResizable(false);
		split.setDividerLocation(512);
		split.setEnabled(false);
		lgp.startGame();
	}
	
	
}
