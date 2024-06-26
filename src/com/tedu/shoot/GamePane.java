package com.tedu.shoot;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.tedu.Dao.Dao;

public class GamePane extends JPanel {
	public static BufferedImage hero0;
	public static BufferedImage hero1;
	public static BufferedImage bg1;
	public static BufferedImage bg2;
	public static BufferedImage airplane;
	public static BufferedImage bee;
	public static BufferedImage bullet;
	public static BufferedImage bigairplane;
	public static BufferedImage start;
	public static BufferedImage pause;
	public static BufferedImage gameover;
	public static BufferedImage bang;
	
	public static int index;
	public static boolean restart = false;
	
	private static final int UP = KeyEvent.VK_UP;
	private static final int DOWN = KeyEvent.VK_DOWN;
	private static final int LEFT = KeyEvent.VK_LEFT;
	private static final int RIGHT = KeyEvent.VK_RIGHT;
	private static final int SPACE = KeyEvent.VK_SPACE;
	private static final int P = KeyEvent.VK_P;
	private static final int Q = KeyEvent.VK_Q;
	private static final int B = KeyEvent.VK_B;
	private static final int L = KeyEvent.VK_L;
	
	private static int x;
	private static int y;
	private static Robot ro;
	
	public static final int CANSTART = 1;
	public static final int RUNNING = 0;
	public static final int PAUSE = 2;
	public static final int GAMEOVER = 3;
	public static int state = CANSTART;
	public int runFire = 4;     //多发计数
	public boolean mood = false;   //射击模式
	public int singleFire = 0;     //单发计数
	public String fire = "单发";     //显示单发还是连发
	public AudioClip aau = null;    //运行时的背景音乐
	public int statecopy = RUNNING;
	
	public int move = 1;
	public static int bg1y;
	public static int bg2y;
	
    private FlyingObject [] flyings = {};
	private Bullet [] bullets = {};
	private int score = 0;
	private FlyingObject bangFlying;
	private int levelcopy;
	private boolean big = false;

	public static String name;
	
	public GamePane(){}
	public GamePane(String name){
		
		this.name = name;
		boolean player = false;
		Vector list = Dao.getInstance().sPlyerName(); 
		//list.add(Dao.getInstance().sPlyerName());
		for(int i = 0; i < list.size(); i++){
			String n = (String) list.get(i);
			n.trim();
			if(n.equals(name)){
				player = true;
				break;
			}
		}
		
		if(!player){
			Dao.getInstance().iScoreList(name, score);
		}
		System.out.println(list);
	}
	static {
		try {
			hero0 = ImageIO.read(GamePane.class.getResource("/imgs/hero0.png"));
			hero1 = ImageIO.read(GamePane.class.getResource("/imgs/hero1.png"));
			airplane = ImageIO.read(GamePane.class.getResource("/imgs/airplane.png"));
			bee = ImageIO.read(GamePane.class.getResource("/imgs/bee.png"));
			bullet = ImageIO.read(GamePane.class.getResource("/imgs/bullet.png"));
			bigairplane = ImageIO.read(GamePane.class.getResource("/imgs/enemy2.png"));
			start = ImageIO.read(GamePane.class.getResource("/imgs/start.png"));
			gameover = ImageIO.read(GamePane.class.getResource("/imgs/gameover.png"));
			pause = ImageIO.read(GamePane.class.getResource("/imgs/pause.png"));
			bang = ImageIO.read(GamePane.class.getResource("/imgs/flame.png"));
			bg1 = ImageIO.read(GamePane.class.getResource("/imgs/bg1.png"));
			bg2 = ImageIO.read(GamePane.class.getResource("/imgs/bg1.png"));
			
			index = 0;
			bg1y = 0;
			bg2y = -bg2.getHeight();
			
		} catch (IOException e) {
			System.out.println("没有找到相应的图片");
		}
	}
	
	public void sorceList(){
		
	}
	public void levelAction(){
		if(score >= 100 && score < 300){
			hero.setLevel(2);
		}else if(score >= 300 && score < 600){
			hero.setLevel(3);
		}else if(score >= 600){
			hero.setLevel(4);
		}
		levelManage();
	}
	int count = 0;
	public void levelManage(){
		if(count < 1 && hero.getLevel()==1){
			try {
				bg1 = ImageIO.read(GamePane.class.getResource("/imgs/bg1.png"));
				bg2 = ImageIO.read(GamePane.class.getResource("/imgs/bg1.png"));
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			count++;
			for(int i = 0; i < flyings.length; i++){
				flyings[i].setLifeadd(0);
				flyings[i].setDeepx(9);
				flyings[i].setDeepx(9);
			}
		}
		if(levelcopy != hero.getLevel()){
			levelcopy = hero.getLevel();
			switch(levelcopy){
			case 2:
				try {
					bg1 = ImageIO.read(GamePane.class.getResource("/imgs/bg2.png"));
					bg2 = ImageIO.read(GamePane.class.getResource("/imgs/bg2.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				for(int i = 0; i < flyings.length; i++){
					flyings[i].setLifeadd(1);
					flyings[i].setXadd(2);
					flyings[i].setYadd(2);
					
				}
				big = false;
				break;
			case 3:
				try {
					bg1 = ImageIO.read(GamePane.class.getResource("/imgs/bg3.png"));
					bg2 = ImageIO.read(GamePane.class.getResource("/imgs/bg3.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for(int i = 0; i < flyings.length; i++){
					flyings[i].setLifeadd(2);
					flyings[i].setXadd(4);
					flyings[i].setYadd(4);	
				}
				big = true;
				break;
			case 4:
				try {
					bg1 = ImageIO.read(GamePane.class.getResource("/imgs/bg4.png"));
					bg2 = ImageIO.read(GamePane.class.getResource("/imgs/bg4.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for(int i = 0; i < flyings.length; i++){
					flyings[i].setLifeadd(3);
					flyings[i].setXadd(6);
					flyings[i].setYadd(6);	
				}
				big = true;
				break;
		    }
		
		}
	}
	public void paint(Graphics g) {    //画页面,自动调用
		if(bg1y == bg1.getHeight()){
			bg1y = -bg1.getHeight();
		}
		if(bg2y == bg2.getHeight()){
			bg2y = -bg2.getHeight();
		}
		if(state == RUNNING){
			bg1y += move;
			bg2y += move;
		}
		g.drawImage(bg1, 0, bg1y, null);
		g.drawImage(bg2, 0, bg2y, null);
		paintSocre(g);    //成绩
		paintHero(g);     //英雄机
		paintState(g);    //状态
		paintFlyings(g);  //画敌方飞机
		paintBullets(g);  //画子弹 
	}
	private void paintBullets(Graphics g) {
		FlyingObject bullet;
		for(int i = 0; i < bullets.length; i++){
			bullet = bullets[i];
			g.drawImage(bullet.img, bullet.getX(), bullet.getY(), null);
		}
	}

	private void paintFlyings(Graphics g) {
		// TODO Auto-generated method stub
		FlyingObject fly;
		for(int i = 0; i < flyings.length; i++){
			fly= flyings[i];
			g.drawImage(fly.img, fly.getX(), fly.getY(), null);
		}
	}

	private void paintState(Graphics g) {
		switch(state){
		case CANSTART:
			g.drawImage(start, 50, 0, null);
			break;
		
		case PAUSE:
			g.drawImage(pause, 0, 0, null);
			break;
		
		case GAMEOVER:
			g.drawImage(gameover, 0, 0, null);
			break;
		
		}
	}

	Hero hero = new Hero();
	private void paintHero(Graphics g) {
	    g.drawImage(hero.getImg(), hero.getX(), hero.getY(), null);
	    
	}

	
	private void paintSocre(Graphics g) {
		g.setColor(getForeground().RED);
//		g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,24)); 
		g.drawString("SCORE：" + score, 20, 20);
		g.drawString("Life：" + hero.getLife(), 20, 40);
		g.drawString("level：" + hero.getLevel(), 20, 60);
		g.drawString("当前状态："+ fire, 20, 80);
		g.drawString("发射子弹：”：“空格”",20,100);
		g.drawString("切换“单发/连发”：“B”",20,120);
		g.drawString("QUIT：“Q/q”", 20, 140);
		
	}
	
	public void startGame(){
		//创建键盘监听，并添加到当前的面板上
		KeyAdapter kad = new KeyAdapter(){
			public void keyReleased(KeyEvent e){
				if(e.getKeyCode() == SPACE){
					singleFire = 0;
					runFire = 4;
				}
			}
			public void keyPressed(KeyEvent e){
				int key;
				
				key = e.getKeyCode();
				if(key == Q){
					System.exit(0);
				}
				if(state != GAMEOVER){
					switch(key){
					case L:
						SorceList sl = new SorceList();
						sl.setVisible(true);
						sl.setAlwaysOnTop(true);
						break;
					case P:
						if(state == RUNNING){
							state = PAUSE;
						}else if(state == PAUSE){
							state = RUNNING;
						}
						break;
					case B:
						if(mood == false){
							mood = true;
							runFire = 4;
							fire = "连发";
						}else if(mood == true){
							mood = false;
							singleFire = 0;
							fire = "单发";
						}
							
						break;
					case SPACE:
						if(state == RUNNING){
							runFire++;
							if(runFire%5 == 0 && mood == true){
								bulletMusic();
								Bullet[] bs = hero.shoot(); //获取子弹对象
								bullets = Arrays.copyOf(bullets,bullets.length+bs.length); //扩容(bs有几个元素就扩大几个容量)
								System.arraycopy(bs,0,bullets,bullets.length-bs.length,bs.length); //数组的追加
							}else if(mood == false && singleFire < 1){
								bulletMusic();
								singleFire++;
								Bullet[] bs = hero.shoot(); //获取子弹对象
								bullets = Arrays.copyOf(bullets,bullets.length+bs.length); //扩容(bs有几个元素就扩大几个容量)
								System.arraycopy(bs,0,bullets,bullets.length-bs.length,bs.length); //数组的追加
							}
						}
						break;
					case UP:
						if(!hero.outofBounds()){
							hero.moveTo(hero.getX(), hero.getY()-10);
							//ro.mouseMove(x+hero.getX(),y+hero.getY());
							state = RUNNING;
						}else if(hero.getY()+hero.getHeight() > GamePane.HEIGHT){
							state = RUNNING;
							hero.moveTo(hero.getX(), hero.getY()-10);
						}else if(hero.outofBounds()){
							state = PAUSE;
						}
						break;
					case DOWN:
						if(!hero.outofBounds()){
							hero.moveTo(hero.getX(), hero.getY()+10);
							//ro.mouseMove(x+hero.getX(),y+hero.getY()); 
							state = RUNNING;
						}else if(hero.getY() < (-hero.getHeight())/2){
							state = RUNNING;
							hero.moveTo(hero.getX(), hero.getY()+10);
						}else if(hero.outofBounds()){
							state = PAUSE;
						}
						break;
					case LEFT:
						if(!hero.outofBounds()){
							hero.moveTo(hero.getX()-10, hero.getY());
							//ro.mouseMove(x+hero.getX(),y+hero.getY());
							state = RUNNING;
						}else if(hero.getX() + hero.getWidth()/2>GamePane.WIDTH){
							state = RUNNING;
							hero.moveTo(hero.getX()-10, hero.getY());
						}else if(hero.outofBounds()){
							state = PAUSE;
						}
						break;
					case RIGHT:
					    if(!hero.outofBounds()){
					    	hero.moveTo(hero.getX()+10, hero.getY());
					    	//ro.mouseMove(x+hero.getX(),y+hero.getY());
					    	state = RUNNING;
					    }else if(hero.getX() < (-hero.getWidth())/2){
							state = RUNNING;
							hero.moveTo(hero.getX()+10, hero.getY());
						}else if(hero.outofBounds()){
							state = PAUSE;
						}
					    break;
					}
				}
			}
		};
		//添加到当前面板
		this.addKeyListener(kad);
		//让当前的面板能够获取焦点
		this.setFocusable(true);
		this.requestFocus();
		
		MouseAdapter moad = new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if(state == PAUSE){
					state = RUNNING;
				}else if(state == CANSTART){
					state = RUNNING;
				} else if(state == GAMEOVER){
					score = 0;   //清理现场
					hero = new Hero();
					flyings = new FlyingObject[0];
					bullets = new Bullet[0];
					singleFire = 0;
					runFire = 4;
					hero.setLevel(1);
					FlyingObject.xadd = 0;
					FlyingObject.yadd = 0;
					FlyingObject.lifeadd = 0;
					count = 0;
					big = false;
					mood = false;
					state=CANSTART; //修改为启动状态
				} else if(state == RUNNING){
					state = PAUSE;
				}
			}
			
			public void mouseMoved(MouseEvent e){
				if(state == RUNNING){
					hero.moveTo(e.getX() - hero.getWidth()/2, 
							e.getY() - hero.getHeight()/2);
					
				}
				
			}
			public void mouseEntered(MouseEvent e) {   //当鼠标进入的时候
				reFouce();
				if(state == PAUSE){
					state = RUNNING;
				}
			}
			
			public void mouseExited(MouseEvent e) {    //当鼠标退出的时候
				if(state == RUNNING){
					state = PAUSE;
				}
			}
		};
		
		this.addMouseListener(moad);
		this.addMouseMotionListener(moad);
		timeer();
	}
	public void reFouce(){
		this.requestFocus();
	}
	public static int loop = 10;
	public void timeer() {
		Timer time = new Timer();
		time.schedule(new TimerTask(){
			
			@Override
			public void run() {
				levelAction();
				if(state == RUNNING ){
					index++;
					enterAction();
					checkGameOver();
					outOfBoundsAction();
					bangAction();
					hitAction();
					stepAction();
				}
				if(state != statecopy && state != PAUSE){
					statecopy = state;
					musicAction();
				}
				if(restart){
					reatartGame();
				}
				repaint();
			}
			
		}, loop, loop);
	}
	
	private void reatartGame() {
		// TODO Auto-generated method stub
		restart = false;
		score = 0;   //清理现场
		hero = new Hero();
		flyings = new FlyingObject[0];
		bullets = new Bullet[0];
		singleFire = 0;
		runFire = 4;
		hero.setLevel(1);
		FlyingObject.xadd = 0;
		FlyingObject.yadd = 0;
		FlyingObject.lifeadd = 0;
		count = 0;
		big = false;
		mood = false;
		state=RUNNING; //修改为启动状态
	}
	
	public void outOfBoundsAction(){ //10毫秒走一次
		int index = 0; //1)不越界敌人数组下标  2)不越界敌人个数
		FlyingObject[] flyingLives = new FlyingObject[flyings.length]; //不越界敌人数组
		for(int i=0;i<flyings.length;i++){ //遍历所有敌人
			FlyingObject f = flyings[i]; //获取每一个敌人
			if(!f.outofBounds()){ //不越界
				flyingLives[index] = f; //将不越界敌人对象添加到不越界敌人数组中
				index++; //1)不越界敌人数组下标增一  2)不越界敌人个数增一
			}
		}
		flyings = Arrays.copyOf(flyingLives,index); //将不越界敌人数组复制到flyings中，flyings的长度为index(不越界敌人个数)
		
		index = 0; //1)不越界子弹数组下标归零  2)不越界子弹个数归零
		Bullet[] bulletLives = new Bullet[bullets.length]; //不越界子弹数组
		for(int i=0; i<bullets.length; i++){ //遍历所有子弹
			Bullet b = bullets[i]; //获取每一个子弹
			if(!b.outofBounds()){ //不越界
				bulletLives[index] = b; //将不越界子弹对象添加到不越界子弹数组中
				index++; //1)不越界子弹数组下标增一  2)不越界子弹个数增一
			}
		}
		bullets = Arrays.copyOf(bulletLives,index); //将不越界子弹数组复制到bullets中，bullets的长度为index(不越界子弹个数)
		
	}
	public void enterAction(){
		if(index%50 == 0){
			FlyingObject fly = nextOne();
			flyings = Arrays.copyOf(flyings,flyings.length+1);
			flyings[flyings.length-1] = fly;
		}
		
	}
	
	private FlyingObject nextOne() {
		Random ran = new Random();
		int i = ran.nextInt(130);
		if(i<=80){
			return new AirPlan();
		}else if(80<i && i<120 && big) {
			return new BigAirPlane();
		}else {
			return new Bee();
		}
	}
	public void stepAction(){
		hero.step();
		//if(index % 20 == 0){
			for(int i = 0; i < flyings.length; i++){
				flyings[i].step();
			}
			for(int i = 0; i < bullets.length; i++){
				bullets[i].step();
			}
		//}
		
	}
	public void bangAction(){ //10毫秒走一次
		for(int i=0;i<bullets.length;i++){ //遍历所有子弹
			Bullet b = bullets[i]; //获取每一个子弹
			int fly = bang(b); //实现一个子弹与所有敌人(敌机+小蜜蜂)的碰撞
			if(fly != -1){
				Bullet tempB = b;
				bullets[i] = bullets[bullets.length-1];
				bullets[bullets.length - 1] = tempB;
				bullets = Arrays.copyOf(bullets, bullets.length - 1);
			}
		}
		
	}
	public int bang(Bullet b){
		int fly = -1; //被撞敌人的下标
		for(int i=0;i<flyings.length;i++){ //遍历所有敌人
			FlyingObject f = flyings[i]; //获取每一个敌人
			if(f.shootBy(b)){ //撞上了
				fly = i; //记录被撞敌人的下标
				break; //其余敌人不再参与比较了
			}
		}
		if(fly!=-1){ //撞上了
			bangMusic();
			flyings[fly].subLife();
			FlyingObject one = flyings[fly]; //被撞的敌人
			if(one.getLife() == 0){
				if((one instanceof Enemy)){  //若是敌人
					Enemy e = (Enemy)one;  //强转为敌人
					score += e.getScore(); //玩家得分
				}
				if((one instanceof Award) && one.getLife() == 0){   //若是奖励
					Award a = (Award)one;   //强转为奖励
					hero.getAward(a.getAward());
				}
				
				//交换被撞的敌人与数组中的最后一个元素
				bangFlying = flyings[fly];
				flyings[fly] = flyings[flyings.length-1];
				flyings[flyings.length-1] = bangFlying ;
				//缩容(去掉最后一个元素，即被撞的敌人对象)
				flyings = Arrays.copyOf(flyings,flyings.length-1);
			}
			
		}
		
		return fly;
	}
	
	public void hitAction(){
		FlyingObject fo;
		for(int i = 0; i < flyings.length; i++){
			fo = flyings[i];
			if(hero.hit(fo)){
				bangMusic();
				hero.subLife();
				hero.clearnDoubleFire();
				//交换被撞的敌人与数组中的最后一个元素
				bangFlying = flyings[i];
				flyings[i] = flyings[flyings.length-1];
				flyings[flyings.length-1] = bangFlying;
				//缩容(去掉最后一个元素，即被撞的敌人对象)
				flyings = Arrays.copyOf(flyings,flyings.length-1);
			}
		}
	}
	
	public void checkGameOver(){
		if(hero.life <= 0){
			state = GAMEOVER;
			int s = (int) Dao.getInstance().sScore(name);
			if(s < score){
			Dao.getInstance().uScore(name, score);
			}
		}
	}
	public void bulletMusic(){
//		File f;
//		URI uri;
//		URL url;
//		AudioClip aau;
//		try {
//			f = new File("src/music/bullet.wav");
//			uri = f.toURI();
//			url = uri.toURL();  //解析地址
//			aau = Applet.newAudioClip(url);
//			aau.play();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	public void bangMusic(){
//		File f;
//		URI uri;
//		URL url;
//		AudioClip aau;
//		try {
//			f = new File("src/music/Bang.wav");
//			uri = f.toURI();
//			url = uri.toURL();  //解析地址
//			aau = Applet.newAudioClip(url);
//			aau.play();  //循环播
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	
	public void musicAction(){
		int type = -1;
		if(state == CANSTART || state == GAMEOVER){
			type = 0;
		}else{
			type = 1;
		}
		if(aau != null){ 
			aau.stop();
		}
			bgmusic(type);
			aau.loop();
		
	}
   
	public void bgmusic(int type){
		File f;
		URI uri;
		URL url;
		if(type == 1){
			try {
				//aau.stop();
				f = new File("src/music/music.wav");
				uri = f.toURI();
				url = uri.toURL();  //解析地址 
				aau = Applet.newAudioClip(url);
			} catch (Exception e) { 
				e.printStackTrace();
			}
		}
		if(type == 0){
			try {
				//aau.stop();
				f = new File("src/music/bgm_juqingduihua.wav");
				uri = f.toURI();
				url = uri.toURL();  //解析地址 
				aau = Applet.newAudioClip(url);
			} catch (Exception e) { 
				e.printStackTrace();
			}
		}
	}
	public static final int WIDTH = bg1.getWidth();
	public static final int HEIGHT = bg1.getHeight();
//	public void mainFrame(String user) {
//		JFrame win = new JFrame("飞机大战");
//		GamePane pane = new GamePane(user);
//		pane.startGame();
//		win.add(pane);
//		win.setSize(WIDTH, HEIGHT);
//		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize(); 
//		win.setLocation((dim.width - win.getWidth()) / 2, (dim.height - win.getHeight()) / 2);
//		x = win.getX();
//		x = win.getY();
//		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);;
//		win.setVisible(true);
//		//win.setAlwaysOnTop(true);
//	}
	
	
}
