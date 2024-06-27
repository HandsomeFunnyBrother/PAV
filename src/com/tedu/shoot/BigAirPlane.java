package com.tedu.shoot;

import java.util.Random;

/**
 * 大飞机类，继承自 FlyingObject 并实现 Enemy 接口
 */
public class BigAirPlane extends FlyingObject implements Enemy {

	/**
	 * 大飞机的得分
	 */
	public static final int score = 5;

	/**
	 * 大飞机的纵向移动速度
	 */
	public int deepy = 2 + yadd;

	/**
	 * 大飞机的横向移动速度
	 */
	public int deepx;

	/**
	 * 大飞机的构造函数
	 */
	public BigAirPlane() {
		// 设置生命值
		life = 1 + lifeadd;
		// 设置图片
		img = GamePane.bigairplane;
		// 随机生成横坐标
		Random ra = new Random();
		x = 20 + ra.nextInt(GamePane.WIDTH - 20 - img.getWidth());
		// 设置纵坐标为屏幕上方
		y = -img.getHeight();
		// 设置宽度和高度
		width = img.getWidth();
		height = img.getHeight();
		// 随机生成横向移动方向
		Random rd = new Random();
		int i = rd.nextInt(2);
		if (i == 1) {
			deepx = 2 + xadd;
		} else if (i == 0) {
			deepx = -2 - xadd;
		}
	}

	/**
	 * 获取纵向移动速度
	 *
	 * @return 纵向移动速度
	 */
	public int getDeepy() {
		return deepy;
	}

	/**
	 * 设置纵向移动速度
	 *
	 * @param deepy 纵向移动速度
	 */
	public void setDeepy(int deepy) {
		this.deepy = deepy;
	}

	/**
	 * 获取横向移动速度
	 *
	 * @return 横向移动速度
	 */
	public int getDeepx() {
		return deepx;
	}

	/**
	 * 设置横向移动速度
	 *
	 * @param deepx 横向移动速度
	 */
	public void setDeepx(int deepx) {
		this.deepx = deepx;
	}

	/**
	 * 获取得分
	 *
	 * @return 得分
	 */
	@Override
	public int getScore() {
		// TODO Auto-generated method stub
		return 10;
	}

	/**
	 * 移动大飞机
	 */
	public void step() {
		// 如果大飞机到达屏幕边缘，则改变横向移动方向
		if (x + img.getWidth() >= GamePane.WIDTH) {
			deepx = -deepx;
		} else if (x <= 0) {
			deepx = -deepx;
		}
		// 更新横坐标和纵坐标
		x = x + deepx;
		y = y + deepy;
	}

	/**
	 * 判断大飞机是否超出屏幕
	 *
	 * @return true 如果超出屏幕，否则返回 false
	 */
	@Override
	public boolean outofBounds() {
		// TODO Auto-generated method stub
		return y >= GamePane.HEIGHT;
	}
}
