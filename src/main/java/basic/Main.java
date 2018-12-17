package basic;



import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import java.io.File;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

   private static String[] midiSrcFiles = new String[]{"src/main/resources/pianocon.mid", "src/main/resources/la_mer_1.mid", "src/main/resources/helloWorld.mid"};

   public static void main(String[] args) {
      System.out.println("Welcome to the basic midi database.");
      String sequenceName = midiSrcFiles[2];
      //object to pass to MidiParser
      MidiDatabaseDAO dao = new BasicMidiDatabaseDAO();
      Sequence sequence;
      String password;

      //password prompts
      password = passwordPrompt_EraseDisplay();
      //password = passwordPrompt_noMask();

      //connect dao
      try {
         dao.createConnection("jdbc:mysql://localhost:3306/basicmididatabase", "root", password);
      } catch (SQLException e) {
         System.out.println("Failed to connect to DB");
         e.printStackTrace();
         return;
      }

      try {
         sequence = MidiSystem.getSequence(new File(sequenceName));


      } catch (Exception e) {
         System.out.println("Failed to get sequence from specified file");
         e.printStackTrace();
         return;
      }
      System.out.println("Creating MidiParser");
      MIDI_Parser midiParser = new MIDI_Parser(sequence, sequenceName, dao);

      System.out.println("Parsing midi file " + sequenceName);
      midiParser.parseMidi();

   }

   /**
    * Method which masks and reads in password on the command line.
    * DO NOT USE INSIDE IDE - BREAKS PROGRAM
    * @return password
    */
   private static String passwordPrompt_EraseDisplay() {
      String password = PasswordField.readPassword("Enter password: ");
      //System.out.println("Password entered was:" + password);
      return password;
   }

   /**
    * Method which takes in password - NO MASKING.
    * FOR USE INSIDE IDE FOR TESTING
    * @return password
    */
   private static String passwordPrompt_noMask() {
      //get password info
      Scanner scanner = new Scanner(System.in);
      String password = "";
      System.out.print("Enter password: ");
      password = scanner.next();
      System.out.println();
      return password;
   }
}