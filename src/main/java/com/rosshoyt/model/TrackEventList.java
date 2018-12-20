package com.rosshoyt.model;

import javax.sound.midi.Track;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.ShortMessage;
import java.util.ArrayList;

public class TrackEventList {
   private ArrayList     <MidiEvent> eventlist;
   private int tracknumber;
   private int size;


   public TrackEventList(int tracknumber){
      tracknumber = tracknumber;
      eventlist = new ArrayList<>();
      size = eventlist.size();
   }

   public void addEvent(MidiEvent m){
      eventlist.add(m);
   }
   public MidiEvent getEvent(int eventNumber){

      return eventlist.get(eventNumber);
   }

}

