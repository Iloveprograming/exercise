package com.cwy.trans;

public class Car extends Transport{
	public Car()
	{
		transname="汽车";
		checktime=0;
		tickettime=0;
		speed=70;
		arrtime=10;				
		rettime=10;					
		waittime=20;				//候车时间
	}
	 public void timandpri(double distance) 
	 {
		 double drivetime=distance/speed;
		 price=distance*0.32;
		 double time=waittime+drivetime*60;
		 Double get_double = (double) ((Math.round(time * 100)) / 100.0);
		 System.out.println("坐汽车总时间为 ： "+get_double+"分钟   车费为 ："+price+"元");
	 }
}
