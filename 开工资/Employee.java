package com.cwy.salary;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Employee {
	public String name;
	public double salary;
	public int birthdaymonth;
	public static List <Employee> employee=new ArrayList<>();//��ͨԱ����Ϣ
	public static List <Integer> record=new ArrayList<>();
	public Employee() {}
	public Employee(String n,double s,int b) {
		name=n;
		salary=s;
		birthdaymonth=b;
	}
	public void salary(int month) {//month��Ա�����Ź���
		for(Employee s:employee)
		{
			if(s.birthdaymonth==month)
			System.out.println("Ա��  "+s.name+"  "+"�����·�:"+s.birthdaymonth+"  �������������Ү����˾����300�飬��ȥ���"
			+"    "+month+"�¹��ʣ�"+(s.salary+300));
			else System.out.println("Ա��  "+s.name+"  "
	                  +"�����·�:"+s.birthdaymonth+"    "+month+"�¹��ʣ�"+s.salary+"  ");
			
		}
	}
	public void addEmployee()          //��������Ա����Ϣ
	{
		Scanner input = new Scanner(System.in);
		System.out.println("������Ա����������ÿ�¹��ʣ������·�: ");
		String name=input.nextLine();
		double salary=input.nextDouble();
		int birthdaymonth=input.nextInt();
		employee.add(new Employee(name,salary,birthdaymonth));
		
	}
	public void addEmployee(String n,double s,int b)//ֱ�����СԱ��������Ϣ
	{
		employee.add(new Employee(n,s,b));
	}
}
