package com.cwy.guessNum;

import java.util.Scanner;

public class guessNum {
	int score=0;     //玩家目前为止的得分
	int num;       //玩家本轮猜测的数字
	public static void main(String[] args) {
		System.out.printf("请输入玩家人数：");
		Scanner input=new Scanner(System.in);
		int len=input.nextInt();                        //保存玩家人数输入的变量
		System.out.printf("请输入游戏轮次： ");
		int turn=input.nextInt();						//保存游戏轮次的变量
		guessNum[] s=new guessNum[len];
		for(int i=0;i<len;i++)
			s[i]=new guessNum();                        //初始化类对象
		for(int i=0;i<turn;i++)                        //循环游戏轮次
		{
			int number=1;//(int)(Math.random()*200);       //本轮产生的一个0到199之间的随机数
			for(int j=0;j<len;j++)                         //将每个玩家猜测的数字放到对象属性中
			{
				System.out.println("play"+(j+1)+" 请输入0到199之间的一个整数（包括0和199) ");
				s[j].num=input.nextInt();
				if(s[j].num==number) {
					++s[j].score;
					System.out.println("Congraduation!That's right.");
				}
				else 
					System.out.println("Sorry,but Don't be sad!");
			}
			System.out.println("This turn ,the true anwser is "+number);
			System.out.println();
		}
		System.out.println("now,let'publish the final result");
		for(int i=0;i<len;i++)
			System.out.println("the final result of play"+(i+1)+" is "+s[i].score);
	}

}