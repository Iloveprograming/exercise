package com.cwy.Karaoke;
import java.util.*;



public class Master{            //主持人类
	static List<Song> listSong;
	public Master() {
		listSong=new ArrayList<Song>();
	};//构造函数
	public boolean play() {
		if(!listSong.isEmpty()) {
			System.out.println(listSong.get(0).songName+"---"+listSong.get(0).singer+"---"+listSong.get(0).time);
			listSong.remove(0);            //输出listSong表中第一个元素，然后将其删除
			return true;
	}
		else return false;

}
}
