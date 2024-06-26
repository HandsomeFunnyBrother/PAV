package com.tedu.shoot;

public class Bullet extends FlyingObject {

	public static int index;
	public int speed = 5;   //每次运动-像素
	
	//构造函数
	public Bullet() {}
	public Bullet(int x, int y) {
		this.x = x;
		this.y = y;
		this.img = GamePane.bullet;
		this.width = img.getWidth();
		this.height = img.getHeight();
		index = 0;
	}
	@Override
	public void step() {
		
		y = y-speed;
	}
	@Override
	public boolean outofBounds() {
		
		return y == -height;
	}
}
