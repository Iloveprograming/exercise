package com.cwy.Karaoke;

import java.util.Scanner;

public class SongDrive{
	public static Master master;			//主持人实例
	public static Player player;           //歌手实例
	public static void main(String[] args) {
		SongDrive songdrive=new SongDrive();
		Scanner a=new Scanner(System.in);
		System.out.println("按1输入所选歌曲信息给主持人，按0停止点歌 ，主持人按3播放列表首歌曲");
		System.out.println("歌手选歌阶段按1选歌，按0停止点歌： ");
		int b=a.nextInt();                            //用户选择按钮
		if(b==0)System.out.println("已停止点歌");
		while(b==1)                               //歌手点歌
		{
			player.addSong();
			master.listSong.add(player.d);
			System.out.println("若想继续点歌请按1，否则请按0");
			b=a.nextInt();
		}
        System.out.println("主持人阶段按3播放歌曲: ");      //主持人播放歌曲
        b=a.nextInt();
        while(b==3) { 
		if(master.play())
		{
			System.out.println("若想播放下一首请主持人按3，否则请按0");
			b=a.nextInt();
		}
		else {
			System.out.println("列表中已无歌曲，请歌手继续点歌!");
			break;
		}
		   
        }
}
	SongDrive(){                       //对象初始化
		master=new Master();
		player=new Player();
	}
}