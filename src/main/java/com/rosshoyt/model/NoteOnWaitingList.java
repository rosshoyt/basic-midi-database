package com.rosshoyt.model;

import com.rosshoyt.app.MissingMIDIDataException;

import javax.sound.midi.ShortMessage;
import java.util.*;

import com.rosshoyt.app.MissingMIDIDataException;

import javax.sound.midi.ShortMessage;
import java.util.LinkedList;
import java.util.Hashtable;

/**
 * Class which works with TrackEventLists
 */
public class NoteOnWaitingList {

   private Deque<ShortMessage>[] hangingNotes;
   private static final int NUMBER_OF_MIDINOTE_PITCHES = 128;
   public NoteOnWaitingList() {
      hangingNotes = new ArrayDeque[NUMBER_OF_MIDINOTE_PITCHES];
   }

   public void addNoteOn(ShortMessage sm) {
      int pitch = sm.getData1();

      //if note has not yet been added, creates linked list at hashtable key of that pitch
      if (hangingNotes[pitch] == null) {
         hangingNotes[pitch] = new ArrayDeque();
         hangingNotes[pitch].add(sm);

      } else { //A Waiting Note On has already Been added for this note.
         // so add to end of list
         hangingNotes[pitch].add(sm);

      }

   }

   public ShortMessage getNoteOnFROMOff(ShortMessage sm) throws MissingMIDIDataException {
      int pitch = sm.getData1();
      System.out.println("Getting Note On from Note off value " + pitch);
      //test if hashmap contains pitch key
      if (hangingNotes[pitch].isEmpty() || hangingNotes[pitch] == null) throw new MissingMIDIDataException();

      return hangingNotes[pitch].pop();



   }
}
