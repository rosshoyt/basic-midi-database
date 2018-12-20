package com.rosshoyt.pojo;

import javax.sound.midi.ShortMessage;

public class PNote {
   ShortMessage noteOn;
   ShortMessage noteOff;

   float note_start;
   float note_off;
   int trackNumber;
   int channelNumber;

   int key;
   //derived attributes
   int octave;
   int dodecaphonic_interval;
   String note_name;

   int onVelocity;


   public PNote(){

   }



}
