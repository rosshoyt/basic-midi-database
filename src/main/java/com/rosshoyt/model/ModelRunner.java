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


   private MidiParser midiParser;

   private File file;
   private boolean isValid;

   public ModelRunner() {
      isValid = false;
   }
   public void setFile(File file){


      this.file = file;
      //setup sequence
      Sequence seq;
      try {
         seq = MidiSystem.getSequence(file);
         //instantiate vars

         midiParser = new MidiParser(seq);
         isValid = true;
      }
      catch(Exception e){
         e.printStackTrace();
      }
         //catch other issues between FIle and java MidiSystem and throw excep
   }


   public PHeader parseHeader() throws InvalidMidiSequenceException {
      if(!isValid) throw new InvalidMidiSequenceException();
      return midiParser.parseHeader();
   }

   public PTrack[] parseTracks() throws MissingMIDIDataException, InvalidMidiSequenceException {
      if(!isValid) throw new InvalidMidiSequenceException();
      return midiParser.parseTracks();
   }

}
