package com.cwy.trans;

public class Airplane extends Transport{
	public Airplane()
	{
		transname="�ɻ�";
		speed=800;
		arrtime=30;				//����վ��������ʱ��
		rettime=29;				//�ӳ�վ������������ʱ��
		tickettime=7;			//ȡƱʱ��
		checktime=6;				//����ʱ��
		waittime=7;
	}
	 public void timandpri(double distance) 
	 {
		 price=distance*0.75;
		 double drivetime=distance/speed;
		 double time=waittime+drivetime*60+arrtime+rettime+tickettime+checktime;
		 Double get_double = (double) ((Math.round(time * 100)) / 100.0);
		 System.out.println("���ɻ���ʱ��Ϊ �� "+get_double+"����   ����Ϊ ��"+price+"Ԫ");
	 }
}
