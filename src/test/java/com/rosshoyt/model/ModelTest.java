package com.rosshoyt.model;
import java.io.*;

//import com.sun.tools.javac.util.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;

import static org.junit.jupiter.api.Assertions.*;

class ModelTest {
   SequenceParser sequenceParser;
   TrackEventList[] trackEventLists;


   NoteOnQueue noteOnQueue;
   NoteStitcher noteStitcher;

   String midiSrc = "src/main/resources/helloWorld.mid";
   @BeforeEach
   void setUp() throws InvalidMidiDataException {
      System.out.println("Test Starting");
      noteOnQueue = new NoteOnQueue();
      noteStitcher = new NoteStitcher();
      //create sequence parser with sequence
      try{
         sequenceParser = new SequenceParser(MidiSystem.getSequence(new File(midiSrc)));

      } catch(Exception e){
         e.printStackTrace();

      }
      //System.out.println("Sequence Parser is initialized");

      System.out.println("Testing parse tracks");
      trackEventLists = sequenceParser.parseTracks();


   }

/*
   @AfterEach
   void tearDown() {
   }

*/
/*
   @Test
   void parseHeader() {
   }
*/

   @Test
   void parseTracks() {


   }
}