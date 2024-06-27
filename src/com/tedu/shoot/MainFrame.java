package com.tedu.shoot;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

// 主窗口类，负责创建游戏窗口和菜单窗口
public class MainFrame extends JFrame {
	//分割面板，用于分割游戏面板和菜单面板
	public static JSplitPane split;
	public static final int WIDTH = 800;
	public static final int HEIGHT = 800;
	public static void main(String[] args) {
		// 创建游戏面板
		GamePane lgp = new GamePane();
		// 创建菜单面板
		MenuPane rmp = new MenuPane();
		// 创建分割面板，将游戏面板和菜单面板水平分割
		split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, lgp, rmp);
		// 创建主窗口
		MainFrame win = new MainFrame();
		// 设置窗口大小
		win.setSize(WIDTH, HEIGHT);
		// 获取屏幕尺寸
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		// 设置窗口位置为屏幕中央
		win.setLocation((dim.width - win.getWidth()) / 2, (dim.height - win.getHeight()) / 2);
		// 设置窗口关闭操作为退出程序
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 将分割面板添加到主窗口
		win.add(split);
		// 设置窗口可见
		win.setVisible(true);
		// 设置窗口不可调整大小
		win.setResizable(false);
		// 设置分割面板的分隔线位置
		split.setDividerLocation(512);
		// 禁用分割面板的分隔线拖动
		split.setEnabled(false);
		// 启动游戏
		lgp.startGame();
	}
}
