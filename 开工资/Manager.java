package com.cwy.salary;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Manager extends Employee{
	double bonus;
	public static List <Manager> manager=new ArrayList<>();//经理信息存放
	public Manager() {};
	public Manager(String n,double s,int b,double bo) {
		super(n,s,b);
		bonus=bo;
	}
	public void addManager(String n,double s,int b,double bo)//直接添加经理基本信息
	{
		manager.add(new Manager(n,s,b,bo));
	}
	
	public void addManager()          //键盘输入经理信息
	{
		Scanner input = new Scanner(System.in);
		System.out.println("请输入经理的姓名，每月工资，生日月份和每月奖金: ");
		String name=input.nextLine();
		double salary=input.nextDouble();
		int birthdaymonth=input.nextInt();
		double bonus=input.nextDouble();
		manager.add(new Manager(name,salary,birthdaymonth,bonus));
		
	}
	
	
	public void salary(int month) {      //工资信息输出函数
		for(Manager s:manager)
		{
			if(s.birthdaymonth==month)
				System.out.println("经理  "+s.name+"  "+"生日月份:"+s.birthdaymonth+"  这个月是你生日耶，公司特批300块，拿去疯吧"
						+"  "+month+"月工资："+(s.salary+300)+"  奖金:"+s.bonus);
			else 
				System.out.println("经理  "+s.name+"  "+"生日月份:"+s.birthdaymonth
						+"    "+month+"月工资："+(s.salary+300)+"  奖金:"+s.bonus);
			
		}
	};

}
