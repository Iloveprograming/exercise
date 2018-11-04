package com.cwy.trans;

public class Airplane extends Transport{
	public Airplane()
	{
		transname="飞机";
		speed=800;
		arrtime=30;				//到车站（机场）时间
		rettime=29;				//从车站（机场）返回时间
		tickettime=7;			//取票时间
		checktime=6;				//安检时间
		waittime=7;
	}
	 public void timandpri(double distance) 
	 {
		 price=distance*0.75;
		 double drivetime=distance/speed;
		 double time=waittime+drivetime*60+arrtime+rettime+tickettime+checktime;
		 Double get_double = (double) ((Math.round(time * 100)) / 100.0);
		 System.out.println("坐飞机总时间为 ： "+get_double+"分钟   车费为 ："+price+"元");
	 }
}
