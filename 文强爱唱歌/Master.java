package com.cwy.Karaoke;
import java.util.*;



public class Master{            //��������
	static List<Song> listSong;
	public Master() {
		listSong=new ArrayList<Song>();
	};//���캯��
	public boolean play() {
		if(!listSong.isEmpty()) {
			System.out.println(listSong.get(0).songName+"---"+listSong.get(0).singer+"---"+listSong.get(0).time);
			listSong.remove(0);            //���listSong���е�һ��Ԫ�أ�Ȼ����ɾ��
			return true;
	}
		else return false;

}
}
