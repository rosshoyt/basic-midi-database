package com.rosshoyt.model;

//import com.sun.tools.javac.util.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sound.midi.InvalidMidiDataException;

class ModelTest {
   MidiParser midiParser;
   TrackEventList[] trackEventLists;


   NoteOnQueue noteOnQueue;
   NoteStitcher noteStitcher;

   String midiSrc = "src/main/resources/helloWorld.mid";
   @BeforeEach
   void setUp() throws InvalidMidiDataException {


      //init vars TODO init as fields? && Setup test properly w/ annotations\ etc
      noteOnQueue = new NoteOnQueue();
      noteStitcher = new NoteStitcher();




      System.out.println("Testing parse tracks");
      trackEventLists = midiParser.parseTracks();

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