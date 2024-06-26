package com.tedu.shoot;

import java.awt.image.BufferedImage;


public abstract class FlyingObject {
	
	public int x;
	public int y;
	public BufferedImage img;
	public int height;
	public int width;
	public int life;
	public int deepx;
	public int deepy;
	public static int xadd;
	public static int yadd;
	public static int lifeadd = 0;
	//�۷������˶����ӵ������˶�
	public abstract void step();    //�ɻ��ƶ�
	public abstract boolean outofBounds(); // �ж�Խ��
	
	public boolean shootBy(Bullet bullet){
		int x1 = this.x;             //x1:���˵�x
		int x2 = this.x+this.width;  //x2:���˵�x+���˵Ŀ�
		int y1 = this.y;             //y1:���˵�y
		int y2 = this.y+this.height; //y2:���˵�y+���˵ĸ�
		int x = bullet.x;            //x:�ӵ���x
		int y = bullet.y;            //y:�ӵ���y
		
		return x>=x1 && x<=x2
			   &&
			   y>=y1 && y<=y2; //x��x1��x2֮�䣬���ң�y��y1��y2֮�䣬��Ϊײ����
	}
	
	public static int getLifeadd() {
		return lifeadd;
	}
	public static void setLifeadd(int lifeadd) {
		FlyingObject.lifeadd = lifeadd;
	}
	public static int getXadd() {
		return xadd;
	}
	public static void setXadd(int xadd) {
		FlyingObject.xadd = xadd;
	}
	public static int getYadd() {
		return yadd;
	}
	public static void setYadd(int yadd) {
		FlyingObject.yadd = yadd;
	}
	public int getDeepx() {
		return deepx;
	}
	public void setDeepx(int deepx) {
		this.deepx = deepx;
	}
	public int getDeepy() {
		return deepy;
	}
	public void setDeepy(int deepy) {
		this.deepy = deepy;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getLife() {
		return life;
	}
	public void setLife(int life) {
		this.life = life;
	}
	public BufferedImage getImg() {
		return img;
	}
	public void setImg(BufferedImage img) {
		this.img = img;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	
	public void subLife(){
		life = life - 1;
	}
	
}
