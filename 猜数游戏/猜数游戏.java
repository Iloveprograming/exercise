package com.cwy.guessNum;

import java.util.Scanner;

public class guessNum {
	int score=0;     //���ĿǰΪֹ�ĵ÷�
	int num;       //��ұ��ֲ²������
	public static void main(String[] args) {
		System.out.printf("���������������");
		Scanner input=new Scanner(System.in);
		int len=input.nextInt();                        //���������������ı���
		System.out.printf("��������Ϸ�ִΣ� ");
		int turn=input.nextInt();						//������Ϸ�ִεı���
		guessNum[] s=new guessNum[len];
		for(int i=0;i<len;i++)
			s[i]=new guessNum();                        //��ʼ�������
		for(int i=0;i<turn;i++)                        //ѭ����Ϸ�ִ�
		{
			int number=1;//(int)(Math.random()*200);       //���ֲ�����һ��0��199֮��������
			for(int j=0;j<len;j++)                         //��ÿ����Ҳ²�����ַŵ�����������
			{
				System.out.println("play"+(j+1)+" ������0��199֮���һ������������0��199) ");
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