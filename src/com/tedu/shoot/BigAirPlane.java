package com.tedu.shoot;

import java.util.Random;

/**
 * ��ɻ��࣬�̳��� FlyingObject ��ʵ�� Enemy �ӿ�
 */
public class BigAirPlane extends FlyingObject implements Enemy {

	/**
	 * ��ɻ��ĵ÷�
	 */
	public static final int score = 5;

	/**
	 * ��ɻ��������ƶ��ٶ�
	 */
	public int deepy = 2 + yadd;

	/**
	 * ��ɻ��ĺ����ƶ��ٶ�
	 */
	public int deepx;

	/**
	 * ��ɻ��Ĺ��캯��
	 */
	public BigAirPlane() {
		// ��������ֵ
		life = 1 + lifeadd;
		// ����ͼƬ
		img = GamePane.bigairplane;
		// ������ɺ�����
		Random ra = new Random();
		x = 20 + ra.nextInt(GamePane.WIDTH - 20 - img.getWidth());
		// ����������Ϊ��Ļ�Ϸ�
		y = -img.getHeight();
		// ���ÿ�Ⱥ͸߶�
		width = img.getWidth();
		height = img.getHeight();
		// ������ɺ����ƶ�����
		Random rd = new Random();
		int i = rd.nextInt(2);
		if (i == 1) {
			deepx = 2 + xadd;
		} else if (i == 0) {
			deepx = -2 - xadd;
		}
	}

	/**
	 * ��ȡ�����ƶ��ٶ�
	 *
	 * @return �����ƶ��ٶ�
	 */
	public int getDeepy() {
		return deepy;
	}

	/**
	 * ���������ƶ��ٶ�
	 *
	 * @param deepy �����ƶ��ٶ�
	 */
	public void setDeepy(int deepy) {
		this.deepy = deepy;
	}

	/**
	 * ��ȡ�����ƶ��ٶ�
	 *
	 * @return �����ƶ��ٶ�
	 */
	public int getDeepx() {
		return deepx;
	}

	/**
	 * ���ú����ƶ��ٶ�
	 *
	 * @param deepx �����ƶ��ٶ�
	 */
	public void setDeepx(int deepx) {
		this.deepx = deepx;
	}

	/**
	 * ��ȡ�÷�
	 *
	 * @return �÷�
	 */
	@Override
	public int getScore() {
		// TODO Auto-generated method stub
		return 10;
	}

	/**
	 * �ƶ���ɻ�
	 */
	public void step() {
		// �����ɻ�������Ļ��Ե����ı�����ƶ�����
		if (x + img.getWidth() >= GamePane.WIDTH) {
			deepx = -deepx;
		} else if (x <= 0) {
			deepx = -deepx;
		}
		// ���º������������
		x = x + deepx;
		y = y + deepy;
	}

	/**
	 * �жϴ�ɻ��Ƿ񳬳���Ļ
	 *
	 * @return true ���������Ļ�����򷵻� false
	 */
	@Override
	public boolean outofBounds() {
		// TODO Auto-generated method stub
		return y >= GamePane.HEIGHT;
	}
}
