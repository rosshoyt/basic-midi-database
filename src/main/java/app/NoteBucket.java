package app;


import javax.sound.midi.ShortMessage;

public class NoteBucket{
   /**
    * The ShortMessage Entry
    */
   private ShortMessage note_on_message;

   /**
    * Assignable Reference to next ShortMessage -
    * indicates list end if null
    */
   public ShortMessage next;
   /**
    * Assignable Reference to previous ShortMessage -
    * indicates list Start if is set to null
    */
   public ShortMessage prev;
   /**
    * No-Args NoteBucket Constructor
    */
   public NoteBucket(){
      note_on_message = null;
      next = null;
      prev = null;
   }

   /**
    * NoteBucket Constructor which accepts shortmessage
    * @param sm
    */
   public NoteBucket(ShortMessage sm){
      this.note_on_message = sm;
      next = null;
      prev = null;
   }

   /**
    * Sets the shortmessage in the note bucket
    * @param sm ShortMessage to set in Bucket
    */
   public void setShortMessage(ShortMessage sm){
      this.note_on_message = sm;
   }

   /**
    * @return ShortMessage in the Bucket
    */
   public ShortMessage getShortMessage(){
      return this.note_on_message;
   }


}
