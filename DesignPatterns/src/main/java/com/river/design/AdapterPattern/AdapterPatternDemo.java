package com.river.design.AdapterPattern;

import com.river.design.AdapterPattern.mediaplayer.AudioPlayer;

/**
 * @program: RiverDemo
 * @description: 适配器模式
 * @author: River
 * @create: 2021-01-30 00:44
 **/
public class AdapterPatternDemo {
    public static void main(String[] args) {
        AudioPlayer audioPlayer = new AudioPlayer();

        audioPlayer.play("mp3", "beyond the horizon.mp3");
        audioPlayer.play("mp4", "alone.mp4");
        audioPlayer.play("vlc", "far far away.vlc");
        audioPlayer.play("avi", "mind me.avi");
    }
}
