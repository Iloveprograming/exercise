package com.cwy.trans;

public class Train extends Transport{
	public Train()
	{
		transname="高铁";
		speed=150;
		arrtime=20;				//到车站（机场）时间
		rettime=19;				//从车站（机场）返回时间
		tickettime=5;			//取票时间
		checktime=5;				//安检时间
		waittime=5;
	}
	 public void timandpri(double distance) 
	 {
		 price=distance*0.45;
		 double drivetime=distance/speed;
		 double time=waittime+drivetime*60+arrtime+rettime+tickettime+checktime;
		 Double get_double = (double) ((Math.round(time * 100)) / 100.0);
		 System.out.println("坐动车总时间为 ： "+get_double+"分钟   车费为 ："+price+"元");
	 }
}
