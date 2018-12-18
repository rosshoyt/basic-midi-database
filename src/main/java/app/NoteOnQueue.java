package app;

import javax.sound.midi.ShortMessage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Hashtable;
public class NoteOnQueue {
   private final static int SIZE = 128; //number of midi notes

   //private Hashtable<<Integer>, <ArrayList<ShortMessage>> noteOnQueue;
   private Hashtable<Integer, ShortMessage> hangingNotes;
   private Hash

   public NoteOnQueue(){
      hangingNotes = new Hashtable<>();
   }
   public void addNoteOn(ShortMessage sm){
      hangingNotes.put(sm.getData1(), sm);
   }
   public ShortMessage getNoteOff(int pitch) throws MidiException {

      if(!hangingNotes.contains(pitch)) throw new MidiException();
      //pointer for to-be-selected shortmessage
      ShortMessage tmp = hangingNotes.get(pitch);
      hangingNotes.remove(pitch);
      return
   }

}
