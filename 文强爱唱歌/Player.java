package com.cwy.Karaoke;

import java.util.Scanner;

public class Player{           //歌手类
	Song d;
	public boolean addSong() {           //输入歌曲信息点歌操作
		Scanner input=new Scanner(System.in);
		System.out.println("歌名: ");
		String a=input.nextLine();
		System.out.println("歌手名: ");
		String b=input.nextLine();
		System.out.println("歌曲时间: ");
		double c=input.nextDouble();
		d=new Song(a,b,c);
		return true;
	};
}
