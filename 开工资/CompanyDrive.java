package com.cwy.salary;

import java.util.Scanner;

public class CompanyDrive {
	public static Employee emp;
	public static Manager man;
	public static Shareholder sha;
	public CompanyDrive() {
		emp=new Employee();
		man=new Manager();
		sha=new Shareholder();
	}
	public static void main(String[] args) {
		CompanyDrive a=new CompanyDrive();
		int i=1;
		Scanner input=new Scanner(System.in);
	while(i!=0)                    //输入公司所有职员信息为了方便直接调用input
		{
			System.out.println("输入0退出，输入1可输入员工信息，输入2可输入经理信息，输入3请输入股东信息：");
			i=input.nextInt();
			if(i==1)
			{
				System.out.println("请输入该员工信息: ");
				emp.addEmployee();
			}
			else if (i==2) {
				System.out.println("请输入该经理信息: ");
				man.addEmployee();
			}
			else if (i==3) {
				System.out.println("请输入该股东信息: ");
				sha.addEmployee();
			}
			else i=0;
		}
		a.input();
		i=1;
		System.out.println("请输入0退出，按1~12选择发放工资月份")	;
		while (i!=0)
		{
			System.out.println("请输入发工资月份: " );
			i=input.nextInt();
			emp.salary(i);
			man.salary(i);
			sha.salary(i);
		}
	}
	public void input()
	{
		emp.addEmployee("梁朝伟", 3000, 6);
		emp.addEmployee("彭于晏",3500, 3);
		emp.addEmployee("胡歌",4000,9);
		emp.addEmployee("霍建华",4000,12);
		emp.addEmployee("刘诗诗",4000,3);
		man.addManager("马化腾", 20000,10,10000);
		man.addManager("Steve Jobs",20000,2, 10001);
		sha.addShareholder("Warren Buffett", 0, 8, 0.4);
		sha.addShareholder("陈雾阳", 0, 12, 0.6);
	}
	/*public void record()
	{
		int a=Employee.employee.size();
		int b=Manager.manager.size();
		int c=Shareholder.sholder.size();
		
	}
	public int getERecord(String na)     //查询该员工在列表中第几个
	{
		int n;
		for(n=0;n<Employee.employee.size();n++)
			if(Employee.employee.get(n).name==na)return n+1;
		return -1;
	}
	public int getMRecord(String na)         //查询该经理在列表中第几个
	{
		int n;
		for(n=0;n<Manager.manager.size();n++)
			if(Manager.manager.get(n).name==na)return n+1;
		return -1;
	}
	public int getSRecord(String na)      //查询该股东在列表中第几个
	{
		int n;
		for(n=0;n<Shareholder.sholder.size();n++)
			if(Shareholder.sholder.get(n).name==na)return n+1;
		return -1;
	}*/
}
