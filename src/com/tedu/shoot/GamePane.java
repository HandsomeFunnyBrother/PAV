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
	public int runFire = 4;     //�෢����
	public boolean mood = false;   //���ģʽ
	public int singleFire = 0;     //��������
	public String fire = "����";     //��ʾ������������
	public AudioClip aau = null;    //����ʱ�ı�������
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
			System.out.println("û���ҵ���Ӧ��ͼƬ");
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
	public void paint(Graphics g) {    //��ҳ��,�Զ�����
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
		paintSocre(g);    //�ɼ�
		paintHero(g);     //Ӣ�ۻ�
		paintState(g);    //״̬
		paintFlyings(g);  //���з��ɻ�
		paintBullets(g);  //���ӵ� 
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
		g.drawString("SCORE��" + score, 20, 20);
		g.drawString("Life��" + hero.getLife(), 20, 40);
		g.drawString("level��" + hero.getLevel(), 20, 60);
		g.drawString("��ǰ״̬��"+ fire, 20, 80);
		g.drawString("�����ӵ����������ո�",20,100);
		g.drawString("�л�������/����������B��",20,120);
		g.drawString("QUIT����Q/q��", 20, 140);
		
	}
	
	public void startGame(){
		//�������̼���������ӵ���ǰ�������
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
							fire = "����";
						}else if(mood == true){
							mood = false;
							singleFire = 0;
							fire = "����";
						}
							
						break;
					case SPACE:
						if(state == RUNNING){
							runFire++;
							if(runFire%5 == 0 && mood == true){
								bulletMusic();
								Bullet[] bs = hero.shoot(); //��ȡ�ӵ�����
								bullets = Arrays.copyOf(bullets,bullets.length+bs.length); //����(bs�м���Ԫ�ؾ����󼸸�����)
								System.arraycopy(bs,0,bullets,bullets.length-bs.length,bs.length); //�����׷��
							}else if(mood == false && singleFire < 1){
								bulletMusic();
								singleFire++;
								Bullet[] bs = hero.shoot(); //��ȡ�ӵ�����
								bullets = Arrays.copyOf(bullets,bullets.length+bs.length); //����(bs�м���Ԫ�ؾ����󼸸�����)
								System.arraycopy(bs,0,bullets,bullets.length-bs.length,bs.length); //�����׷��
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
		//��ӵ���ǰ���
		this.addKeyListener(kad);
		//�õ�ǰ������ܹ���ȡ����
		this.setFocusable(true);
		this.requestFocus();
		
		MouseAdapter moad = new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if(state == PAUSE){
					state = RUNNING;
				}else if(state == CANSTART){
					state = RUNNING;
				} else if(state == GAMEOVER){
					score = 0;   //�����ֳ�
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
					state=CANSTART; //�޸�Ϊ����״̬
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
			public void mouseEntered(MouseEvent e) {   //���������ʱ��
				reFouce();
				if(state == PAUSE){
					state = RUNNING;
				}
			}
			
			public void mouseExited(MouseEvent e) {    //������˳���ʱ��
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
		score = 0;   //�����ֳ�
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
		state=RUNNING; //�޸�Ϊ����״̬
	}
	
	public void outOfBoundsAction(){ //10������һ��
		int index = 0; //1)��Խ����������±�  2)��Խ����˸���
		FlyingObject[] flyingLives = new FlyingObject[flyings.length]; //��Խ���������
		for(int i=0;i<flyings.length;i++){ //�������е���
			FlyingObject f = flyings[i]; //��ȡÿһ������
			if(!f.outofBounds()){ //��Խ��
				flyingLives[index] = f; //����Խ����˶�����ӵ���Խ�����������
				index++; //1)��Խ����������±���һ  2)��Խ����˸�����һ
			}
		}
		flyings = Arrays.copyOf(flyingLives,index); //����Խ��������鸴�Ƶ�flyings�У�flyings�ĳ���Ϊindex(��Խ����˸���)
		
		index = 0; //1)��Խ���ӵ������±����  2)��Խ���ӵ���������
		Bullet[] bulletLives = new Bullet[bullets.length]; //��Խ���ӵ�����
		for(int i=0; i<bullets.length; i++){ //���������ӵ�
			Bullet b = bullets[i]; //��ȡÿһ���ӵ�
			if(!b.outofBounds()){ //��Խ��
				bulletLives[index] = b; //����Խ���ӵ�������ӵ���Խ���ӵ�������
				index++; //1)��Խ���ӵ������±���һ  2)��Խ���ӵ�������һ
			}
		}
		bullets = Arrays.copyOf(bulletLives,index); //����Խ���ӵ����鸴�Ƶ�bullets�У�bullets�ĳ���Ϊindex(��Խ���ӵ�����)
		
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
	public void bangAction(){ //10������һ��
		for(int i=0;i<bullets.length;i++){ //���������ӵ�
			Bullet b = bullets[i]; //��ȡÿһ���ӵ�
			int fly = bang(b); //ʵ��һ���ӵ������е���(�л�+С�۷�)����ײ
			if(fly != -1){
				Bullet tempB = b;
				bullets[i] = bullets[bullets.length-1];
				bullets[bullets.length - 1] = tempB;
				bullets = Arrays.copyOf(bullets, bullets.length - 1);
			}
		}
		
	}
	public int bang(Bullet b){
		int fly = -1; //��ײ���˵��±�
		for(int i=0;i<flyings.length;i++){ //�������е���
			FlyingObject f = flyings[i]; //��ȡÿһ������
			if(f.shootBy(b)){ //ײ����
				fly = i; //��¼��ײ���˵��±�
				break; //������˲��ٲ���Ƚ���
			}
		}
		if(fly!=-1){ //ײ����
			bangMusic();
			flyings[fly].subLife();
			FlyingObject one = flyings[fly]; //��ײ�ĵ���
			if(one.getLife() == 0){
				if((one instanceof Enemy)){  //���ǵ���
					Enemy e = (Enemy)one;  //ǿתΪ����
					score += e.getScore(); //��ҵ÷�
				}
				if((one instanceof Award) && one.getLife() == 0){   //���ǽ���
					Award a = (Award)one;   //ǿתΪ����
					hero.getAward(a.getAward());
				}
				
				//������ײ�ĵ����������е����һ��Ԫ��
				bangFlying = flyings[fly];
				flyings[fly] = flyings[flyings.length-1];
				flyings[flyings.length-1] = bangFlying ;
				//����(ȥ�����һ��Ԫ�أ�����ײ�ĵ��˶���)
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
				//������ײ�ĵ����������е����һ��Ԫ��
				bangFlying = flyings[i];
				flyings[i] = flyings[flyings.length-1];
				flyings[flyings.length-1] = bangFlying;
				//����(ȥ�����һ��Ԫ�أ�����ײ�ĵ��˶���)
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
//			url = uri.toURL();  //������ַ
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
//			url = uri.toURL();  //������ַ
//			aau = Applet.newAudioClip(url);
//			aau.play();  //ѭ����
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
				url = uri.toURL();  //������ַ 
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
				url = uri.toURL();  //������ַ 
				aau = Applet.newAudioClip(url);
			} catch (Exception e) { 
				e.printStackTrace();
			}
		}
	}
	public static final int WIDTH = bg1.getWidth();
	public static final int HEIGHT = bg1.getHeight();
//	public void mainFrame(String user) {
//		JFrame win = new JFrame("�ɻ���ս");
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
