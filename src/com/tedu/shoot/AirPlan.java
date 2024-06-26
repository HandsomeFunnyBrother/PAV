package com.tedu.shoot;

import java.util.Random;

public class AirPlan extends FlyingObject implements Enemy {

	public static final int score = 5;

	// 定义敌机的得分
	public AirPlan(){
		life = 1 + lifeadd;
		img = GamePane.airplane;
		Random ra = new Random();
		x = 20 + ra.nextInt(GamePane.WIDTH -20 -img.getWidth());
		y = -img.getHeight();
		width = img.getWidth();
		height = img.getHeight();
		Random rd = new Random();
		int i = rd.nextInt(2);
		if(i ==1 ){
			deepx = 2 + xadd;
		}else if(i == 0){
			deepx = -2-xadd;
		}
		deepy = 2 + yadd;
	}
	public int getDeepy() {
		return deepy;
	}
	public void setDeepy(int deepy) {
		this.deepy = deepy;
	}
	public int getDeepx() {
		return deepx;
	}
	public void setDeepx(int deepx) {
		this.deepx = deepx;
	}
	@Override
	public int getScore() {
		// TODO Auto-generated method stub
		return 5;
	}

	@Override
	public void step() {
		if(x + img.getWidth() >= GamePane.WIDTH){
			deepx = -deepx;
		}else if(x <= 0){
			deepx = -deepx;
		}
		x = x + deepx;
		y = y + deepy;
	}

	@Override
	public boolean outofBounds() {
		return y >= GamePane.HEIGHT;
	}

}
