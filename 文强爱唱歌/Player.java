package com.cwy.Karaoke;

import java.util.Scanner;

public class Player{           //������
	Song d;
	public boolean addSong() {           //���������Ϣ������
		Scanner input=new Scanner(System.in);
		System.out.println("����: ");
		String a=input.nextLine();
		System.out.println("������: ");
		String b=input.nextLine();
		System.out.println("����ʱ��: ");
		double c=input.nextDouble();
		d=new Song(a,b,c);
		return true;
	};
}
