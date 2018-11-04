package com.cwy.trans;

public abstract class Transport {
	 public String transname;			//交通方式名称
	 public int arrtime;				//到车站（机场）时间
	 public int rettime;				//从车站（机场）返回时间
	 public int tickettime;			//取票时间
	 public int checktime;				//安检时间
	 public int waittime;				//候车时间
	 public double speed;					//车速
	 public double price;				//单价
	 public abstract void timandpri(double distance) ;	//计算消耗时间和价格
	 public void print(double distance) {
		 timandpri(distance);
	 };			//输出抽象方法
}