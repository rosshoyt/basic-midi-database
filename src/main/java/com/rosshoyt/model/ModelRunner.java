package com.rosshoyt.model;
import javax.sound.midi.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import com.rosshoyt.app.MissingMIDIDataException;
import com.rosshoyt.pojo.*;
public class ModelRunner {

   TrackEventList[] trackEventLists;


   //NoteOnQueue noteOnQueue;


   MidiParser midiParser;

   File file;
   boolean isValid;

   public ModelRunner(File file) throws FileNotFoundException {
      this.file = file;
      isValid = false;

      //setup sequence
      Sequence seq;
      try {
         seq = MidiSystem.getSequence(file);
         //instantiate vars

         midiParser = new MidiParser(seq);
         isValid = true;
      }
      catch(Exception e){
         if (!file.exists()) {
            throw new FileNotFoundException(e.toString());
         }
         //catch other issues between FIle and java MidiSystem and throw excep
      }
   }

   public PHeader parseHeader(){

      return midiParser.parseHeader();
   }
   //should really just use regular java tracks for this stage
   public PTrack[] parseTracks() throws MissingMIDIDataException {

      return midiParser.parseTracks();
   }


   public static void main(String[] args) {
      MidiParser midiParser;
      String midiSrc = "src/main/resources/pianocon.mid";
      System.out.println("Test Starting");
      //create sequence parser with sequence
      try{
         midiParser = new MidiParser(MidiSystem.getSequence(new File(midiSrc)));

      } catch(Exception e){
         e.printStackTrace();
         System.out.println("Failure, aborting");
         return;

      }
      System.out.println("Testing parse tracks");
      try {
         PTrack[] pTracks = midiParser.parseTracks();

         System.out.println("Number of Tracks parsed = " + pTracks.length
               + "\nNow Displaying Each Track's Results:\n");

         for (int i = 0; i < pTracks.length; i++){
            PTrack pt = pTracks[i];
            System.out.println("PTrack #" + pt.getTrackNumber() +":");
            for(int j = 0; j < pt.getNumberPNotes(); j++){
               System.out.println(
                     "         PNote #" + (j+1) +
                           " Pitch = "+pt.getPNote(j).getKey());
            }
            System.out.println();
         }
      } catch(MissingMIDIDataException e){
         e.printStackTrace();
      }
      // trackEventLists = midiParser.parseTracks();

   }

}
