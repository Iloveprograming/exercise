package com.cwy.Karaoke;

import java.util.Scanner;

public class SongDrive{
	public static Master master;			//������ʵ��
	public static Player player;           //����ʵ��
	public static void main(String[] args) {
		SongDrive songdrive=new SongDrive();
		Scanner a=new Scanner(System.in);
		System.out.println("��1������ѡ������Ϣ�������ˣ���0ֹͣ��� �������˰�3�����б��׸���");
		System.out.println("����ѡ��׶ΰ�1ѡ�裬��0ֹͣ��裺 ");
		int b=a.nextInt();                            //�û�ѡ��ť
		if(b==0)System.out.println("��ֹͣ���");
		while(b==1)                               //���ֵ��
		{
			player.addSong();
			master.listSong.add(player.d);
			System.out.println("�����������밴1�������밴0");
			b=a.nextInt();
		}
        System.out.println("�����˽׶ΰ�3���Ÿ���: ");      //�����˲��Ÿ���
        b=a.nextInt();
        while(b==3) { 
		if(master.play())
		{
			System.out.println("���벥����һ���������˰�3�������밴0");
			b=a.nextInt();
		}
		else {
			System.out.println("�б������޸���������ּ������!");
			break;
		}
		   
        }
}
	SongDrive(){                       //�����ʼ��
		master=new Master();
		player=new Player();
	}
}