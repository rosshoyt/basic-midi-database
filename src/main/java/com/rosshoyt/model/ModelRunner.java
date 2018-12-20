package com.rosshoyt.model;
import javax.sound.midi.MidiSystem;
import java.io.File;
import java.io.FileNotFoundException;

public class ModelRunner {

   TrackEventList[] trackEventLists;


   //NoteOnQueue noteOnQueue;
   NoteStitcher noteStitcher;
   String midiSrc;

   public ModelRunner(File file) throws FileNotFoundException {

      //set sequence
      try {
         MidiSystem.getSequence(file);
      }
      catch(Exception e){
         if (!file.exists())
            throw new FileNotFoundException(e.toString());
         //catch other issues between FIle and java MidiSystem and throw excep
      }
      //instantiate vars
      noteStitcher = new NoteStitcher();
      midiParser
      midiSrc = "src/main/resources/helloWorld.mid";

   }


   public static void main(String[] args) {
      MidiParser midiParser;

      System.out.println("Test Starting");
      //create sequence parser with sequence
      try{
         midiParser = new MidiParser(MidiSystem.getSequence(new File(midiSrc)));

      } catch(Exception e){
         e.printStackTrace();


      }
      System.out.println("Testing parse tracks");
     // trackEventLists = midiParser.parseTracks();

   }

}
