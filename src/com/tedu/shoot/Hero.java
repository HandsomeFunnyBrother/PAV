package com.tedu.shoot;

import java.awt.image.BufferedImage;
import java.util.Random;


public class Hero extends FlyingObject {
	public BufferedImage [] images;  //�������ͼ���任�ɻ�����״
	public int index = 0;   //ͼƬ���±�
	public int doubleFire = 0;    //����
	public int life;       //����
	public int level;
	public Hero() {
		images = new BufferedImage[]{GamePane.hero0,GamePane.hero1};
		life = 3;
		level = 1;
		this.img = images[0];
		this.width = img.getWidth();
		this.height = img.getHeight();
		Random ran = new Random();
		this.x = ran.nextInt((GamePane.WIDTH-width)/2);
		this.y = 400;
		
	}
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	//�ô˷�����Ӣ�۷ɻ������ӵ����ж�doubleFire == ֵ1���� 2 ˫����
	public Bullet[] shoot() {
		
		int xStep = width/4;
		int yStep = 1;
		if(doubleFire >= 4) { //˫��
			Bullet [] bullets = new Bullet[2];
			bullets[0] = new Bullet(x+xStep,y+yStep);
			bullets[1] = new Bullet(x+xStep*3,y+yStep);
			return bullets;
			
		}else {//����
			Bullet [] bullets = new Bullet[1];
			bullets[0] = new Bullet(x+xStep*2,y+yStep*2);
			return bullets;
		}
	}
	
	public void moveTo(int x, int y) {
		this.x = x; this.y = y;
	}
	
	public void addDoubleFire() {
		doubleFire = doubleFire + 4;
	}
	public void clearnDoubleFire() {
		doubleFire = 0;
	}
	public void addLife() {
		life++;
	}
	
	public void subLife() {
		life--;
	}
	
	public int getLife() {
		return life;
	}

	@Override
	public void step() {
		index++;
		if(images.length > 0 ){
			img = images[index/10%images.length];
		}
	}

	@Override
	public boolean outofBounds() {
		
		return y+height >= GamePane.HEIGHT
				|| y <= -height/2 
				|| x + width/2>=GamePane.WIDTH
				|| x <= -width/2;
	}
	
	public boolean hit(FlyingObject other){
		int x1 = other.x-this.width/2;               //x1:���˵�x-1/2Ӣ�ۻ��Ŀ�
		int x2 = other.x+other.width+this.width/2;   //x2:���˵�x+���˵Ŀ�+1/2Ӣ�ۻ��Ŀ�
		int y1 = other.y-this.height/2; 			 //y1:���˵�y-1/2Ӣ�ۻ��ĸ�
		int y2 = other.y+other.height+this.height/2; //y2:���˵�y+���˵ĸ�+1/2Ӣ�ۻ��ĸ�
		int x = this.x+this.width/2; 				 //x:Ӣ�ۻ���x+1/2Ӣ�ۻ��Ŀ�
		int y = this.y+this.height/2; 				 //y:Ӣ�ۻ���y+1/2Ӣ�ۻ��ĸ�
		
		return x>=x1 && x<=x2
			   &&
			   y>=y1 && y<=y2; //x��x1��x2֮�䣬���ң�y��y1��y2֮�䣬��Ϊײ����
	}
	
	public void getAward(int award){
		if(award == 0){
			addLife();
		}else if(award == 1){
			addDoubleFire();    
		}
	}
}

