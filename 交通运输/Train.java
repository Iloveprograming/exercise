package com.cwy.trans;

public class Train extends Transport{
	public Train()
	{
		transname="����";
		speed=150;
		arrtime=20;				//����վ��������ʱ��
		rettime=19;				//�ӳ�վ������������ʱ��
		tickettime=5;			//ȡƱʱ��
		checktime=5;				//����ʱ��
		waittime=5;
	}
	 public void timandpri(double distance) 
	 {
		 price=distance*0.45;
		 double drivetime=distance/speed;
		 double time=waittime+drivetime*60+arrtime+rettime+tickettime+checktime;
		 Double get_double = (double) ((Math.round(time * 100)) / 100.0);
		 System.out.println("��������ʱ��Ϊ �� "+get_double+"����   ����Ϊ ��"+price+"Ԫ");
	 }
}
