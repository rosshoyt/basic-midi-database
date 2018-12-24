package com.rosshoyt.model;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.Sequence;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

import com.rosshoyt.app.MidiDatabaseDAO;
import com.rosshoyt.app.MissingMIDIDataException;
import com.rosshoyt.pojo.*;
//import com.sun.org.apache.bcel.internal.generic.RETURN;


public class MidiParser {
   //static refs
   private static final int NOTE_ON = 0X90;
   private static final int NOTE_OFF = 0x80;
   private static final String[] NOTE_NAMES = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
   //global objects
   private Sequence sequence;

   //global vars
   //private int currentSequenceID; - will need to get from database, if need to have it here at all
   private int currentTrackNumber;


   /**
    * <Header Chunk> = <chunk type><length><format><ntrks><division>
    */
   protected enum MThd_Header_Key {

   }
   protected enum MTrk_Track_Key {

   }


   public MidiParser(Sequence sequence) {
      this.sequence = sequence;
      currentTrackNumber = 0;
   }

   public void parseMidi() throws MissingMIDIDataException{
      parseHeader();
      parseTracks();
   }

   /**
    * Parses Header Chunk
    * Reference - ALl header chunks follow this format-
    * <Header Chunk> = <chunk type><length><format><ntrks><division>
    */
   public PHeader parseHeader() {
      // TODO parse message against key for division type - should lead to more easy transaction handling
      float divType = sequence.getDivisionType();

      int resolution = sequence.getResolution();
      long microsecond_length = sequence.getMicrosecondLength();
      long tick_length = sequence.getTickLength();
      int numTracks = sequence.getTracks().length; //will always be 1 for Format 0 Midi File
      // TODO Parse actual message and compare against key with header to get midi file 'format'


      //MidiUtils.
      //add entity patchlist to db
      PPatchList patchList = new PPatchList(sequence.getPatchList());

      PSequence pSequence = new PSequence();

      return new PHeader(divType, resolution, microsecond_length, tick_length, numTracks);

   }



   public PTrack[] parseTracks() throws MissingMIDIDataException{
      Track[] tracks = sequence.getTracks();
      PTrack[] persistentTracks = new PTrack[tracks.length];

      System.out.println("Creating PTrack array for [" + tracks.length + "] tracks");
      //TrackEventList[] trackEventListArray = new TrackEventList[tracks.length];

      for (int i = 0; i < tracks.length; i++) {
         persistentTracks[i] = parseOneTrack(tracks[i]);

      }
      //System.out.println(sequence.getPatchList());
      System.out.println("Parse complete - returning track event list array");
      //midiDAO.closeConnection();

      return persistentTracks;
   }

   private PTrack parseOneTrack(Track track) throws MissingMIDIDataException{
      currentTrackNumber++;

      //persistentTracks[currentTrackNumber-1] = new PTrack(currentTrackNumber);
      PTrack pTrack = new PTrack(currentTrackNumber);
      //Created on a per-track basis
      NoteOnWaitingList noteOnWaitingList = new NoteOnWaitingList();

      //legacy debug code
      System.out.println("Track " + currentTrackNumber + ": size = " + track.size());
      System.out.println();

      for (int i=0; i < track.size(); i++) {
         MidiEvent event = track.get(i);

         //PARAM:
         long evStart = event.getTick();


         //PARAM (partial inits for latar)
         String messageType = "Unset";
         int channel = -1;
         String command = "Unset";
         int key = -1;
         int octave = -1;
         int noteNumber = -1;
         String noteName = "Unset";
         int velocity = -1;

         //establish persistent object reference
         //add all 'midi event - specific' params to potential PNote here:
         PNote pNote;
         boolean needToFindNoteOn = false;
         //debug
         System.out.print("@" + evStart + " ");

         MidiMessage message = event.getMessage();
         if (message instanceof MetaMessage) {
            //introduce meta message parsing here
         }
         if (message instanceof ShortMessage) {
            ShortMessage sm = (ShortMessage) message;


            channel = sm.getChannel();
            System.out.print("Channel: " + channel + " ");
            //MIDIStatusByte
            if (sm.getCommand() == NOTE_ON) {


               //add note on SM to waiting list
               noteOnWaitingList.addNoteOn(sm, evStart);

               command = "NOTE_ON";
               key = sm.getData1();

               octave = (key / 12)-1;
               noteNumber = key % 12;
               noteName = NOTE_NAMES[noteNumber];
               velocity = sm.getData2();

               System.out.println("Note on, " + noteName + octave + " key=" + key + " velocity: " + velocity);



            } else if (sm.getCommand() == NOTE_OFF) {
               needToFindNoteOn = true;
               key = sm.getData1();
               octave = (key / 12)-1;
               noteNumber = key % 12;
               noteName = NOTE_NAMES[noteNumber];
               velocity = sm.getData2();
               System.out.println("Note off, " + noteName + octave + " key=" + key + " velocity: " + velocity);

            } else {
               System.out.println("Command:" + sm.getCommand());

            }
            //main logic
            if(needToFindNoteOn) {

               System.out.println("Need a Note On for this NoteOff with pitch " + sm.getData1());
               //try {
               NoteBucket noteOn = noteOnWaitingList.getNoteOnFROMOff(sm);
               pNote = new PNote(noteOn.getShortMessage().getData1(), noteOn.getShortMessage().getData2());
               pNote.setNote_On(noteOn.getEventStart());
               pNote.setNote_Off(evStart);
               pNote.setKey(key);
               //pNote.setChannelNumber(channel);
               //pNote.setNote_name(noteName);
               //pNote.setOnVelocity(sm.getData1());
               ///pNote.setOffVelocity(velocity);
               //pNote.setOctave(sm.getData2());

               pNote.setTrackNumber(currentTrackNumber);

               pTrack.addPNote(pNote);
               System.out.println("PNote added");
//                  } catch (MissingMIDIDataException e) {
//                     e.printStackTrace();
//                  }
            }
         } else {
            System.out.println("Other message: " + message.getClass());
         }
         //midiDAO.addMidiEvent(currentSequenceID, currentTrackNumber, evStart, messageType, channel, command, key, octave, noteNumber, noteName, velocity);
      }

      System.out.println();
      return pTrack;

   }
}
