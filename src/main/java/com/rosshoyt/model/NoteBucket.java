package com.rosshoyt.model;


import javax.sound.midi.ShortMessage;

public class NoteBucket{
   /**
    * The ShortMessage Entry
    */
   private ShortMessage noteOnMessage;
   
   private long eventStart;
   /**
    * Assignable Reference to next ShortMessage -
    * indicates list end if null
    */
   //public ShortMessage next;
   /**
    * Assignable Reference to previous ShortMessage -
    * indicates list Start if is set to null
    */
   //public ShortMessage prev;
   /**
    * No-Args NoteBucket Constructor
    */
   public NoteBucket(){
      noteOnMessage = null;
   }

   /**
    * NoteBucket Constructor which accepts shortmessage
    * @param sm
    */
   public NoteBucket(ShortMessage sm, long eventStart){
      this.noteOnMessage = sm;
      this.eventStart = eventStart;
   }

   /**
    * Sets the shortmessage in the note bucket
    * @param sm ShortMessage to set in Bucket
    */
   public void setShortMessage(ShortMessage sm){
      this.noteOnMessage = sm;
   }
   public void setEventStart(long eventStart){
      this.eventStart = eventStart;
   }
   /**
    * @return ShortMessage in the Bucket
    */
   public ShortMessage getShortMessage(){
      return this.noteOnMessage;
   }
   public long getEventStart(){
      return eventStart;
   }


}
