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
	while(i!=0)                    //���빫˾����ְԱ��ϢΪ�˷���ֱ�ӵ���input
		{
			System.out.println("����0�˳�������1������Ա����Ϣ������2�����뾭����Ϣ������3������ɶ���Ϣ��");
			i=input.nextInt();
			if(i==1)
			{
				System.out.println("�������Ա����Ϣ: ");
				emp.addEmployee();
			}
			else if (i==2) {
				System.out.println("������þ�����Ϣ: ");
				man.addEmployee();
			}
			else if (i==3) {
				System.out.println("������ùɶ���Ϣ: ");
				sha.addEmployee();
			}
			else i=0;
		}
		a.input();
		i=1;
		System.out.println("������0�˳�����1~12ѡ�񷢷Ź����·�")	;
		while (i!=0)
		{
			System.out.println("�����뷢�����·�: " );
			i=input.nextInt();
			emp.salary(i);
			man.salary(i);
			sha.salary(i);
		}
	}
	public void input()
	{
		emp.addEmployee("����ΰ", 3000, 6);
		emp.addEmployee("������",3500, 3);
		emp.addEmployee("����",4000,9);
		emp.addEmployee("������",4000,12);
		emp.addEmployee("��ʫʫ",4000,3);
		man.addManager("����", 20000,10,10000);
		man.addManager("Steve Jobs",20000,2, 10001);
		sha.addShareholder("Warren Buffett", 0, 8, 0.4);
		sha.addShareholder("������", 0, 12, 0.6);
	}
	/*public void record()
	{
		int a=Employee.employee.size();
		int b=Manager.manager.size();
		int c=Shareholder.sholder.size();
		
	}
	public int getERecord(String na)     //��ѯ��Ա�����б��еڼ���
	{
		int n;
		for(n=0;n<Employee.employee.size();n++)
			if(Employee.employee.get(n).name==na)return n+1;
		return -1;
	}
	public int getMRecord(String na)         //��ѯ�þ������б��еڼ���
	{
		int n;
		for(n=0;n<Manager.manager.size();n++)
			if(Manager.manager.get(n).name==na)return n+1;
		return -1;
	}
	public int getSRecord(String na)      //��ѯ�ùɶ����б��еڼ���
	{
		int n;
		for(n=0;n<Shareholder.sholder.size();n++)
			if(Shareholder.sholder.get(n).name==na)return n+1;
		return -1;
	}*/
}
