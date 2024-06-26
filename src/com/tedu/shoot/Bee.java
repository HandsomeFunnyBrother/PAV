package com.tedu.shoot;

import java.util.Random;

public class Bee extends FlyingObject implements Award {

	public int deepy;
	public int deepx;
	public int awardType;
	
	public Bee(){
		life = 6 + lifeadd;
		//���۷�ͼƬ�������
		img = GamePane.bee;
		Random ra = new Random();
		// �����۷��x���꣬�����������Ϸ����ȷ�Χ��
		// x�������پ������Ե20��λ���������۷�ͼƬ�Ŀ�Ƚ��е���
		x = 20 + ra.nextInt(GamePane.WIDTH -20 -img.getWidth());
		y = -img.getHeight();
		width = img.getWidth();
		height = img.getHeight();
		//����һ�������
		Random rd = new Random();
		// ����һ��0��1�����������ȷ��ˮƽ������ƶ�
		int i = rd.nextInt(2);
		if(i ==1 ){
			// ���iΪ1����deepx����Ϊ��ֵ�������ƶ�����������һ�������ֵxadd
			deepx = 2 + xadd;
		}else if(i == 0){
			// ���iΪ0����deepx����Ϊ��ֵ�������ƶ�����������һ�������ֵxadd
			deepx = -2-xadd;
		}
		// ���ô�ֱ�ƶ��ٶȣ�������һ�������ֵyadd
		deepy = 2 + yadd;
		awardType = ra.nextInt(2);   //0 ������1�ӻ���
	}
	public int getScore() {
		// TODO Auto-generated method stub
		return 5;
	}
	
	
	@Override
	// �۷����step���������ڸ����۷��λ��
	public void step() {
		// TODO Auto-generated method stub
		// ����۷��x�������ͼƬ��ȴ��ڵ�����Ϸ���Ŀ�ȣ�˵���������ұ߽�
		if(x + img.getWidth() >= GamePane.WIDTH){
			// ��תˮƽ������ƶ�����
			deepx = -deepx;
		}else if(x <= 0){
			// ����۷��x����С�ڵ���0��˵����������߽�
			// ��תˮƽ������ƶ�����
			deepx = -deepx;
		}
		// �����۷��x�����y����
		x = x + deepx;
		y = y + deepy;
	}

	@Override
	// ����۷��Ƿ񳬳��߽�ķ���
	public boolean outofBounds() {
		// TODO Auto-generated method stub
		// ����۷��y������ڵ�����Ϸ���ĸ߶ȣ�����true����ʾ�����߽�
		return y >= GamePane.HEIGHT;
	}

	// ��ȡ�������͵ķ���
	public int getAward(){
		// ���ؽ������ͣ�0Ϊ������1Ϊ�ӻ�����
		return awardType;
	}
}
