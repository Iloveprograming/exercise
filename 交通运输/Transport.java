package com.cwy.trans;

public abstract class Transport {
	 public String transname;			//��ͨ��ʽ����
	 public int arrtime;				//����վ��������ʱ��
	 public int rettime;				//�ӳ�վ������������ʱ��
	 public int tickettime;			//ȡƱʱ��
	 public int checktime;				//����ʱ��
	 public int waittime;				//��ʱ��
	 public double speed;					//����
	 public double price;				//����
	 public abstract void timandpri(double distance) ;	//��������ʱ��ͼ۸�
	 public void print(double distance) {
		 timandpri(distance);
	 };			//������󷽷�
}