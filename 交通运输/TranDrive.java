package com.cwy.trans;

import java.util.Scanner;

public class TranDrive {
	public static void main(String []args)
	{
		System.out.println("请输入出发地，目的地和距离: ");
		Scanner input=new Scanner(System.in);
		String sour=input.nextLine();
		String des=input.nextLine();
		double distance=input.nextDouble();
		Transport T=new Car();
		T.print(distance);
		T=new Train();
		T.print(distance);
		T=new Airplane();
		T.print(distance);
	}
}
