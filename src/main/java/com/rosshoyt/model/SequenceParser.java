package com.rosshoyt.model;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.Sequence;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

import com.rosshoyt.app.MidiDatabaseDAO;
import com.rosshoyt.pojo.PPatchList;
import com.rosshoyt.pojo.PSequence;
//import com.sun.org.apache.bcel.internal.generic.RETURN;


public class SequenceParser {
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


	public SequenceParser(Sequence sequence) {
		this.sequence = sequence;
      currentTrackNumber = 0;
	}

   public void parseMidi(){
	   parseHeader();
	   parseTracks();
   }

   /**
    * Parses Header Chunk
    * Reference - ALl header chunks follow this format-
    * <Header Chunk> = <chunk type><length><format><ntrks><division>
    */
   public void parseHeader() {
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


   }
	public TrackEventList[] parseTracks() {
      Track[] tracks = sequence.getTracks();

      System.out.println("Creating trackEventList array with space for " + tracks.length + "tracks");
      TrackEventList[] trackEventListArray = new TrackEventList[tracks.length];

		for (Track track :  tracks) {
		   currentTrackNumber++;
         //legacy debug code
		   System.out.println("Track " + currentTrackNumber + ": size = " + track.size());
         System.out.println();

         //create local track event list for curr track
         trackEventListArray[currentTrackNumber-1] = new TrackEventList(currentTrackNumber);



         for (int i=0; i < track.size(); i++) {
            MidiEvent event = track.get(i);

            //PARAM:
            long evStart = event.getTick();
            System.out.print("@" + evStart + " ");

            //PARAM (partial inits for latar)
            String messageType = "Unset";
            int channel = -1;
            String command = "Unset";
            int key = -1;
            int octave = -1;
            int noteNumber = -1;
            String noteName = "Unset";
            int velocity = -1;


            MidiMessage message = event.getMessage();
            if (message instanceof MetaMessage) {
               messageType =  "META";


               //MetaMessage mm = (MetaMessage)message;
            }
            if (message instanceof ShortMessage) {
               messageType = "SHORT";
					//String msgByte = new String(message.getMessage());

               ShortMessage sm = (ShortMessage) message;


               channel = sm.getChannel();
               System.out.print("Channel: " + channel + " ");
               //MIDIStatusByte
               if (sm.getCommand() == NOTE_ON) {
                  command = "NOTE_ON";
                  key = sm.getData1();

                  octave = (key / 12)-1;
                  noteNumber = key % 12;
                  noteName = NOTE_NAMES[noteNumber];
                  velocity = sm.getData2();

                  System.out.println("Note on, " + noteName + octave + " key=" + key + " velocity: " + velocity);


                  //NoteOn n = new NoteOn();
					} else if (sm.getCommand() == NOTE_OFF) {
						key = sm.getData1();
						octave = (key / 12)-1;
						noteNumber = key % 12;
						noteName = NOTE_NAMES[noteNumber];
						velocity = sm.getData2();
						System.out.println("Note off, " + noteName + octave + " key=" + key + " velocity: " + velocity);
					} else {
						System.out.println("Command:" + sm.getCommand());

					}
				} else {
					System.out.println("Other message: " + message.getClass());
				}
				//midiDAO.addMidiEvent(currentSequenceID, currentTrackNumber, evStart, messageType, channel, command, key, octave, noteNumber, noteName, velocity);
         }

			System.out.println();
		}
		//System.out.println(sequence.getPatchList());
		System.out.println("Parse complete - returning track event list array");
		//midiDAO.closeConnection();

      return trackEventListArray;
	}


}