//package com.rosshoyt.musictools;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Hashtable;
//import java.util.LinkedList;
//import java.util.Map;
//
//public class Chord {
//
//public int[] plus7mod12 = new int[] { 0, 7,  2,  9, 4, 11, 6,  1, 8,
//												  4, 11, 6,  1, 8, 3,  10, 5, 0,
//												  8, 3,  10, 5, 0, 7  ,2,  9, 4,
//												  0, 7,  2,  9, 4, 11, 6,  1, 8,
//												  4, 11, 6,  1, 8, 3,  10, 5, 0 };
//	/* E  B  F# C# G# D# A# E# B#
//	   C  G  D  A  E  B  F# C# G#
//	   Ab Eb Bb F  C  G  D  A  E
//	   2 more lines*/
//
//
//
//	private static final String[] NOTE_NAMES = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
//	/**
//	 * The notes making up this chord
//	 */
//	private ArrayList<Note> notes;
//
//
//
//
//	/*
//	 * THE HARMONIC ALGORITHM
//The problem for which our harmonic program is designed is to determine the
//key of a fugue from its subject, and, if there are any notes not belonging to
//the key, to determine their relation to those which do, and to the keynote in
//particular. Once this problem has been solved, it is a trivial matter to transcribe
//the solution into standard musical notation, so we shall eschew
//notational technicalities as far as we can in the following paragraphs.
//Figure 1 depicts a short section of the keyboard, with each note lettered or
//numbered according to its position in the octave. The key of c major
//comprises the 'white notes' 0, 2, 4, 5, 7, 9, and 11, and contains the three
//'major triads' (C E G), (G B D), and (F A C), that is, (0 4 7), (7 11 2), and
//(5 9 0). In each triad the first and third notes are separated by a 'perfect
//fifth', which is one of the three app intervals of music. The other two app
//intervals are the octave — but we are treating two notes an octave apart as
//equivalent— and the `major third', which is the interval between the first and second notes of each major triad.These facts enable us to represent the harmonic relations within C major by the two-dimensional array shown in figure2,whereeachnoteisa perfectfifthbelowthenoteon itsrightand a majorthirdbelowthenotewrittenaboveit.A majortriadthenformsan L-shaped cluster of three notes. One may set up a corresponding array of numbers,andextenditindefinitelyinbothdimensions(seefigure3)wherea move to the right adds 7 modulo 12 and a move upwards adds 4.
//AEB FCGD
//Figure2
//9 4 11
//5 0 7 2
//0 major
//Figure3
//4 11 6 1 8 3 10 5 0 0 7 2 9 4 11 6 1 8 8 3 10 5 0 7 2 9 4 4 11 6 1 8 3 10 5 0 0 7 2 9 4 11 6 1 8
//5 0 7 2
//T8 37 0 minor
//The key of 0 major then comprises the seven notes shown on the left. Notes other than these are conventionally denoted by `sharps'(1) and
//'flats'(17),asinfigure4.
//E B F#C#G#DOAPE#B#
//AEB CGDAE BF#C#G# BL
//FC0DI AbEl,BIPFCGD AEIFCGD Cmajor F Cl' D17Al'E FC
//Figure:4
//Di+ BR,FL' Cl' Al' Cminor
//
//
//
//
//
//
//
//
//
//	"Frequency data shall be defined in [units] which are fractions of a semitone.
//	The frequency range starts at MIDI note 0, C = 8.1758 Hz, and extends above MIDI note 127,
//	G = 12543.854 Hz. The first byte of the frequency data word specifies the highest equal-tempered
//	semitone not exceeding the frequency. The next two bytes (14 bits) specify the fraction of 100 cents
//	above the semitone at which the frequency lies. Effective resolution = 100 cents / 214 = .0061 cents."[1]
//
//	This higher resolution allows a logarithmic representation of pitch in which the semitone is divided
//	into 1282 = 214 = 16384 parts, which means the octave is divided into 196608 (logarithmically) equal parts.
//	These parts are exactly 100/16384 cents (approximately 0.0061 cents) in size, which is far below the threshold
//	of human pitch perception and which therefore allows a very accurate representation of pitch.
//	*/
//
//
//
//	private ArrayList<ArrayList<Boolean>> lydianParentScales = new ArrayList<ArrayList<Boolean>>();
//
//
//
//
//
//
//
//	private HashMap<Integer, LinkedList<Note>> noteBuckets = new HashMap<>(12);
//
//	//private boolean[] chord = new boolean[12];
//
//	protected final int FIFTH_SIZE = 7;
//
//	protected final int DIATONIC_SCALE_SIZE = 12;
//
//	protected final int NOTES_PER_OCTAVE = 12;
//
//	protected final int HIGHEST_MIDI_NOTE = 127;
//
//
//	private void setFixedModes() {
//
//
//		for(int i = 0; i < 12; i++) {
//			for(int j = 0; j < 12; j++){
//
//			}
//		}
//	}
//
//
//
//	/**
//	 * Chord Constructor which accepts an arraylist of notes.
//	 * @param notes All the notes making up the chord.
//	 */
//	public Chord(ArrayList<Note> notes){
//		this.notes = notes;
//		setHarmonicOccurances();
//	}
//
//
//
//	/**
//	 * Boolean representation of the lydian parent scale
//	 */
//	protected final boolean[] lydianParentMode = new boolean[] { true, false, true, false,
//			true, false, true, true, false, true, false, true};
//
//	private void setHarmonicOccurances() {
//
//
//		//boolean[] _lydian = new boolean[12];
//
//
//		boolean[] _mixolydian = new boolean[12];
//		boolean[] _aeolian = new boolean[12];
//		boolean[] _locrian = new boolean[12];
//		boolean[] _ionian = new boolean[12];
//		boolean[] _dorian = new boolean[12];
//		boolean[] _phrygian_ = new boolean[12];
//
//
//
//
//		/*
//		int count = 0;
//		for(int j = 0; j < HIGHEST_MIDI_NOTE; j+= FIFTH_SIZE) {
//			if(count > DIATONIC_SCALE_SIZE) j-=2; //offsets to invert 5th increase to 4th, ensuring diatonic compliance. (adds 5 steps which == a 'fourth'
//			_lydian[j] = true;
//			count++;
//		}
//		*/
//
//
//	}
////	boolean[] mode0 = new boolean[128];
////	boolean[] mode1 = new boolean[128];
////	boolean[] mode2 = new boolean[128];
////	boolean[] mode3 = new boolean[128];
////	boolean[] mode4 = new boolean[128];
////	boolean[] mode5 = new boolean[128];
////	boolean[] mode6 = new boolean[128];
////	boolean[] mode7 = new boolean[128];
////	boolean[] mode8 = new boolean[128];
////	boolean[] mode9 = new boolean[128];
////	boolean[] modeA = new boolean[128];
////	boolean[] modeB = new boolean[128];
//
//
//
//		/*  INDIVIDUAL APPROACH - DO ONCE PER BOOLEAN ARRAY
//		int index = 0;
//
//		for(int j = index; j < HIGHEST_MIDI_NOTE; j+= FIFTH_SIZE) {
//			mode
//		}
//		*/
//
//	}
//
//
//
//
//}
