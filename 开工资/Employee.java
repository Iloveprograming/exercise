package com.cwy.salary;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Employee {
	public String name;
	public double salary;
	public int birthdaymonth;
	public static List <Employee> employee=new ArrayList<>();//普通员工信息
	public static List <Integer> record=new ArrayList<>();
	public Employee() {}
	public Employee(String n,double s,int b) {
		name=n;
		salary=s;
		birthdaymonth=b;
	}
	public void salary(int month) {//month月员工发放工资
		for(Employee s:employee)
		{
			if(s.birthdaymonth==month)
			System.out.println("员工  "+s.name+"  "+"生日月份:"+s.birthdaymonth+"  这个月是你生日耶，公司特批300块，拿去疯吧"
			+"    "+month+"月工资："+(s.salary+300));
			else System.out.println("员工  "+s.name+"  "
	                  +"生日月份:"+s.birthdaymonth+"    "+month+"月工资："+s.salary+"  ");
			
		}
	}
	public void addEmployee()          //键盘输入员工信息
	{
		Scanner input = new Scanner(System.in);
		System.out.println("请输入员工的姓名，每月工资，生日月份: ");
		String name=input.nextLine();
		double salary=input.nextDouble();
		int birthdaymonth=input.nextInt();
		employee.add(new Employee(name,salary,birthdaymonth));
		
	}
	public void addEmployee(String n,double s,int b)//直接添加小员工基本信息
	{
		employee.add(new Employee(n,s,b));
	}
}
