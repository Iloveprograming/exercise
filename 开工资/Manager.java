package com.cwy.salary;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Manager extends Employee{
	double bonus;
	public static List <Manager> manager=new ArrayList<>();//������Ϣ���
	public Manager() {};
	public Manager(String n,double s,int b,double bo) {
		super(n,s,b);
		bonus=bo;
	}
	public void addManager(String n,double s,int b,double bo)//ֱ����Ӿ��������Ϣ
	{
		manager.add(new Manager(n,s,b,bo));
	}
	
	public void addManager()          //�������뾭����Ϣ
	{
		Scanner input = new Scanner(System.in);
		System.out.println("�����뾭���������ÿ�¹��ʣ������·ݺ�ÿ�½���: ");
		String name=input.nextLine();
		double salary=input.nextDouble();
		int birthdaymonth=input.nextInt();
		double bonus=input.nextDouble();
		manager.add(new Manager(name,salary,birthdaymonth,bonus));
		
	}
	
	
	public void salary(int month) {      //������Ϣ�������
		for(Manager s:manager)
		{
			if(s.birthdaymonth==month)
				System.out.println("����  "+s.name+"  "+"�����·�:"+s.birthdaymonth+"  �������������Ү����˾����300�飬��ȥ���"
						+"  "+month+"�¹��ʣ�"+(s.salary+300)+"  ����:"+s.bonus);
			else 
				System.out.println("����  "+s.name+"  "+"�����·�:"+s.birthdaymonth
						+"    "+month+"�¹��ʣ�"+(s.salary+300)+"  ����:"+s.bonus);
			
		}
	};

}
