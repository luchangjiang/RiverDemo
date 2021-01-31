package com.river.design.AdapterPattern.mediaplayer.advanced;

public interface AdvancedMediaPlayer {
   void playVlc(String fileName);
   void playMp4(String fileName);

   interface MediaPlayer {
      public void play(String audioType, String fileName);
   }
}