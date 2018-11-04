package com.cwy.salary;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class Shareholder extends Employee{
	Manager m;
	Employee e;              
	double share;           //�ɶ��ɷ�
	int month;              
	static double sales;      //��˾Ӫҵ��
	public static List <Shareholder> sholder=new ArrayList<>();//�ɶ���Ϣ���
	public Shareholder() {};
	public Shareholder(String n,double s,int b,double sh)
	{
		super(n,s,b);
		share=sh;
	}
	public void salary(int month) {    //�������������
		sales=(double)Math.random()*10000000+100000000;  //��˾Ӫҵ���һ�ڸ���1ǧ��
		for(Shareholder s:sholder)
		{
			if(month==12)
			{
				double dow=down();
				
				System.out.println("�ɶ�  "+s.name+"  "+"�����·�:"+s.birthdaymonth+
						"    "+month+"�·ֺ죺"+(sales-dow)*s.share);
			}	
			else //else�޹���
				System.out.println("�ɶ�"+s.name+"  "+"�����·�:"+s.birthdaymonth
						+"    "+month+"�¹��ʣ��ɶ����ˣ���ȵ�ʮ���������÷ֺ��");
			
		}
	};
	public void addShareholder(String n,double s,int b,double sh)//��ӹɶ�������Ϣ
	{
		sholder.add(new Shareholder(n,s,b,sh));
	}
	public void addShareholder()          //�������뾭����Ϣ
	{
		Scanner input = new Scanner(System.in);
		System.out.println("������ɶ���������ÿ�¹��ʣ������·ݺ͹ɷ�: ");
		String name=input.nextLine();
		double salary=input.nextDouble();
		int birthdaymonth=input.nextInt();
		double share=input.nextDouble();
		sholder.add(new Shareholder(name,salary,birthdaymonth,share));
		
	}
	
	public double down()      //������귢�ŵ����й����ܶ�
	{
		double sum=0;
		for(int i=0;i<Employee.employee.size();i++)//��������Ա������������
		{
			sum=sum+Employee.employee.get(i).salary*12+300;
		}
		for(int i=0;i<Manager.manager.size();i++)//�������о������������
		{
			sum=sum+(Manager.manager.get(i).salary+Manager.manager.get(i).bonus)*12+300;
		}
		return sum;
	}
}
