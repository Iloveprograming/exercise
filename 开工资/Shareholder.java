package com.cwy.salary;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class Shareholder extends Employee{
	Manager m;
	Employee e;              
	double share;           //股东股份
	int month;              
	static double sales;      //公司营业额
	public static List <Shareholder> sholder=new ArrayList<>();//股东信息存放
	public Shareholder() {};
	public Shareholder(String n,double s,int b,double sh)
	{
		super(n,s,b);
		share=sh;
	}
	public void salary(int month) {    //发工资输出函数
		sales=(double)Math.random()*10000000+100000000;  //公司营业额保底一亿浮动1千万
		for(Shareholder s:sholder)
		{
			if(month==12)
			{
				double dow=down();
				
				System.out.println("股东  "+s.name+"  "+"生日月份:"+s.birthdaymonth+
						"    "+month+"月分红："+(sales-dow)*s.share);
			}	
			else //else无工资
				System.out.println("股东"+s.name+"  "+"生日月份:"+s.birthdaymonth
						+"    "+month+"月工资：股东大人，请等到十二月再来拿分红吧");
			
		}
	};
	public void addShareholder(String n,double s,int b,double sh)//添加股东基本信息
	{
		sholder.add(new Shareholder(n,s,b,sh));
	}
	public void addShareholder()          //键盘输入经理信息
	{
		Scanner input = new Scanner(System.in);
		System.out.println("请输入股东的姓名，每月工资，生日月份和股份: ");
		String name=input.nextLine();
		double salary=input.nextDouble();
		int birthdaymonth=input.nextInt();
		double share=input.nextDouble();
		sholder.add(new Shareholder(name,salary,birthdaymonth,share));
		
	}
	
	public double down()      //计算今年发放的所有工资总额
	{
		double sum=0;
		for(int i=0;i<Employee.employee.size();i++)//计算所有员工今年总收入
		{
			sum=sum+Employee.employee.get(i).salary*12+300;
		}
		for(int i=0;i<Manager.manager.size();i++)//计算所有经理今年总收入
		{
			sum=sum+(Manager.manager.get(i).salary+Manager.manager.get(i).bonus)*12+300;
		}
		return sum;
	}
}
