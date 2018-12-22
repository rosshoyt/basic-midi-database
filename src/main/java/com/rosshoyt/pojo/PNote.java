package com.rosshoyt.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.sound.midi.ShortMessage;

@Entity
@Table (name = "Notes")
public class PNote {

   @Id
   @GeneratedValue(strategy=GenerationType.AUTO)
   private long noteID;

   float note_start;
   float note_off;
   int trackNumber;
   int channelNumber;

   int velocity;
   int key;

   //derived attributes
   int octave;
   int dodecaphonic_interval;
   String note_name;

   int onVelocity;
   int offVelocity;

   protected PNote(){}

   public PNote(float note_start, float note_off, int key, int velocity, int trackNumber){
     this.note_start = note_start;
     this.note_off = note_off;
     this.key = key;
     this.velocity = velocity;
     this.trackNumber = trackNumber;
   }
   public PNote(int key, int velocity){
      this.key = key;
      this.velocity = velocity;
   }

   public float getNote_start() {
      return note_start;
   }

   public void setNote_start(float note_start) {
      this.note_start = note_start;
   }

   public float getNote_off() {
      return note_off;
   }

   public void setNote_off(float note_off) {
      this.note_off = note_off;
   }

   public int getTrackNumber() {
      return trackNumber;
   }

   public void setTrackNumber(int trackNumber) {
      this.trackNumber = trackNumber;
   }

   public int getChannelNumber() {
      return channelNumber;
   }

   public void setChannelNumber(int channelNumber) {
      this.channelNumber = channelNumber;
   }

   public int getKey() {
      return key;
   }

   public void setKey(int key) {
      this.key = key;
   }

   public int getOctave() {
      return octave;
   }

   public void setOctave(int octave) {
      this.octave = octave;
   }

   public int getDodecaphonic_interval() {
      return dodecaphonic_interval;
   }

   public void setDodecaphonic_interval(int dodecaphonic_interval) {
      this.dodecaphonic_interval = dodecaphonic_interval;
   }

   public String getNote_name() {
      return note_name;
   }

   public void setNote_name(String note_name) {
      this.note_name = note_name;
   }

   public int getOnVelocity() {
      return onVelocity;
   }

   public void setOnVelocity(int onVelocity) {
      this.onVelocity = onVelocity;
   }


   public int getOffVelocity() {
      return offVelocity;
   }

   public void setOffVelocity(int offVelocity) {
      this.offVelocity = offVelocity;
   }





}
