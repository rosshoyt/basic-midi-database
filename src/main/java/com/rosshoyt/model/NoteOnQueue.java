package com.rosshoyt.model;

import com.rosshoyt.app.MissingMIDIDataException;

import javax.sound.midi.ShortMessage;
import java.util.LinkedList;
import java.util.Hashtable;
public class NoteOnQueue {
   private final static int SIZE = 128; //number of midi notes

   //private Hashtable<<Integer>, <ArrayList<ShortMessage>> noteOnQueue;
   private Hashtable<Integer, LinkedList<NoteBucket>> hangingNotes;


   public NoteOnQueue()
   {
      hangingNotes = new Hashtable<>();
   }
   public void addNoteOn(int pitch, ShortMessage sm)
   {
      LinkedList<NoteBucket> tempListBucket;
      //if note has not yet been added, creates linked list at hashtable key of that pitch
      if(!hangingNotes.contains(pitch)) {
         tempListBucket = new LinkedList<>();
         tempListBucket.add(new NoteBucket(sm));
         hangingNotes.put(pitch, tempListBucket);
      } else{ //A Waiting Note On has already Been added for this note.
             // so add to end of list
         tempListBucket = hangingNotes.get(pitch);
         tempListBucket.add(new NoteBucket(sm));

      }

   }
   public ShortMessage getNoteOnFROMOff(int pitch_of_note_on) throws MissingMIDIDataException
   {
      //test if hashmap contains pitch key
      if(!hangingNotes.contains(pitch_of_note_on)) throw new MissingMIDIDataException();

      LinkedList<NoteBucket> tempListBucket = hangingNotes.get(pitch_of_note_on);
      if(tempListBucket !=null ) {
         ShortMessage temp = tempListBucket.getFirst().getShortMessage();
         //clear note on event
         tempListBucket.remove(0);
         return temp;
      }
      //list was empty
      throw new MissingMIDIDataException();
   }

}
