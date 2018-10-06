package com.cwy.Karaoke;

public class Song {
	String songName;                //歌名
	String singer;					//歌手名
	double time;					//歌曲时间（秒）
	public Song(String songName,String singer,double time){
		this.songName=songName;
		this.singer=singer;
		this.time=time;
	}
}
