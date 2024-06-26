package com.tedu.shoot;

import java.awt.image.BufferedImage;
import java.util.Random;


public class Hero extends FlyingObject {
	public BufferedImage [] images;  //存放两张图，变换飞机的形状
	public int index = 0;   //图片的下标
	public int doubleFire = 0;    //火力
	public int life;       //生命
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

	//用此方法让英雄飞机发射子弹（判断doubleFire == 值1单发 2 双发）
	public Bullet[] shoot() {
		
		int xStep = width/4;
		int yStep = 1;
		if(doubleFire >= 4) { //双发
			Bullet [] bullets = new Bullet[2];
			bullets[0] = new Bullet(x+xStep,y+yStep);
			bullets[1] = new Bullet(x+xStep*3,y+yStep);
			return bullets;
			
		}else {//单发
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
		int x1 = other.x-this.width/2;               //x1:敌人的x-1/2英雄机的宽
		int x2 = other.x+other.width+this.width/2;   //x2:敌人的x+敌人的宽+1/2英雄机的宽
		int y1 = other.y-this.height/2; 			 //y1:敌人的y-1/2英雄机的高
		int y2 = other.y+other.height+this.height/2; //y2:敌人的y+敌人的高+1/2英雄机的高
		int x = this.x+this.width/2; 				 //x:英雄机的x+1/2英雄机的宽
		int y = this.y+this.height/2; 				 //y:英雄机的y+1/2英雄机的高
		
		return x>=x1 && x<=x2
			   &&
			   y>=y1 && y<=y2; //x在x1与x2之间，并且，y在y1与y2之间，即为撞上了
	}
	
	public void getAward(int award){
		if(award == 0){
			addLife();
		}else if(award == 1){
			addDoubleFire();    
		}
	}
}

