package com.rosshoyt.pojo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.sound.midi.ShortMessage;

@Entity
@Table (name = "PNotes")
public class PNote {


   private int noteID;

   private float note_on;
   private float note_off;
   private int trackNumber;
//   private int channelNumber;

   private int velocity;
   private int key;

   //derived attributes
   /*
   private int octave;
   private int dodecaphonic_interval;
   private String note_name;

   private int onVelocity;
   private int offVelocity;
*/
   public PNote(){}

   public PNote(float note_on, float note_off, int key, int velocity, int trackNumber){
     this.note_on = note_on;
     this.note_off = note_off;
     this.key = key;
     this.velocity = velocity;
     this.trackNumber = trackNumber;
   }
   public PNote(int key, int velocity){
      this.key = key;
      this.velocity = velocity;
   }

   public void setNoteID(){
      this.noteID = noteID;
   }
   /*@GeneratedValue(generator="increment")
   @GenericGenerator(name="increment", strategy = "increment")*/

   @Id
   @GeneratedValue(strategy=GenerationType.AUTO)
   public int getNoteID(){
      return this.noteID;
   }

   @Column(name = "NOTE_ON")
   public float getNote_On() {
      return note_on;
   }

   public void setNote_On(float note_on) {
      this.note_on = note_on;
   }
   @Column(name = "NOTE_OFF")
   public float getNote_Off() {
      return note_off;
   }

   public void setNote_Off(float note_off) {
      this.note_off = note_off;
   }
   @Column (name = "TRACK_NUMBER")
   public int getTrackNumber() {
      return trackNumber;
   }

   public void setTrackNumber(int trackNumber) {
      this.trackNumber = trackNumber;
   }

   public int getKey() {
      return key;
   }
   @Column(name = "KEY")
   public void setKey(int key) {
      this.key = key;
   }
   /*
   public int getChannelNumber() {
      return channelNumber;
   }

   public void setChannelNumber(int channelNumber) {
      this.channelNumber = channelNumber;
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
   @Override
   public String toString(){
      String p1 = (note_name != null) ? note_name + octave: "";

      String p2 = (note_on != 0.0f && note_off != 0.0f) ? "On/off:" + note_on + "/" + note_off : "";
      return  p1 + " " + p2;
   }
*/
}
