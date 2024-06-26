package com.tedu.shoot;

import java.util.Random;

public class Bee extends FlyingObject implements Award {

	public int deepy;
	public int deepx;
	public int awardType;
	
	public Bee(){
		life = 6 + lifeadd;
		//将蜜蜂图片传入过来
		img = GamePane.bee;
		Random ra = new Random();
		// 设置蜜蜂的x坐标，随机生成在游戏面板宽度范围内
		// x坐标至少距离左边缘20单位，并根据蜜蜂图片的宽度进行调整
		x = 20 + ra.nextInt(GamePane.WIDTH -20 -img.getWidth());
		y = -img.getHeight();
		width = img.getWidth();
		height = img.getHeight();
		//定义一个随机数
		Random rd = new Random();
		// 生成一个0或1的随机数，以确定水平方向的移动
		int i = rd.nextInt(2);
		if(i ==1 ){
			// 如果i为1，则将deepx设置为正值（向右移动），并加上一个额外的值xadd
			deepx = 2 + xadd;
		}else if(i == 0){
			// 如果i为0，则将deepx设置为负值（向左移动），并加上一个额外的值xadd
			deepx = -2-xadd;
		}
		// 设置垂直移动速度，并加上一个额外的值yadd
		deepy = 2 + yadd;
		awardType = ra.nextInt(2);   //0 加命，1加火力
	}
	public int getScore() {
		// TODO Auto-generated method stub
		return 5;
	}
	
	
	@Override
	// 蜜蜂类的step方法，用于更新蜜蜂的位置
	public void step() {
		// TODO Auto-generated method stub
		// 如果蜜蜂的x坐标加上图片宽度大于等于游戏面板的宽度，说明碰到了右边界
		if(x + img.getWidth() >= GamePane.WIDTH){
			// 反转水平方向的移动方向
			deepx = -deepx;
		}else if(x <= 0){
			// 如果蜜蜂的x坐标小于等于0，说明碰到了左边界
			// 反转水平方向的移动方向
			deepx = -deepx;
		}
		// 更新蜜蜂的x坐标和y坐标
		x = x + deepx;
		y = y + deepy;
	}

	@Override
	// 检查蜜蜂是否超出边界的方法
	public boolean outofBounds() {
		// TODO Auto-generated method stub
		// 如果蜜蜂的y坐标大于等于游戏面板的高度，返回true，表示超出边界
		return y >= GamePane.HEIGHT;
	}

	// 获取奖励类型的方法
	public int getAward(){
		// 返回奖励类型（0为加命，1为加火力）
		return awardType;
	}
}
