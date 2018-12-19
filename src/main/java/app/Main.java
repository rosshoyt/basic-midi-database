
package app;



//import com.fasterxml.classmate.AnnotationConfiguration;
import com.fasterxml.classmate.AnnotationConfiguration;
import commandline.pw_utils.PasswordField;
import org.hibernate.*;
import org.hibernate.cfg.*;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import java.io.File;
import java.sql.SQLException;
import java.util.Scanner;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.internal.SessionFactoryImpl;
import javax.persistence.*;
import org.hibernate.cfg.*;
import org.hibernate.service.ServiceRegistry;

public class Main {
   private final String RESOURCES = "src/main/resources/";
   private final String DRIVER = "com.mysql.jdbc.Driver";
   private final String CONFIG = "hibernate.cfg.xml";
   private String[] midiSrcFiles = new String[]{ RESOURCES + "pianocon.mid", RESOURCES + "la_mer_1.mid",
         RESOURCES + "helloWorld.mid"};

   public static void main(String[] args) {
      if(args.length > 0) {
         //launch gui
      }
      //else launch
      commandLineApp2();
   }
   public static void commandLineApp2(){
      System.out.println("Welcome to the app midi database V2.0 - \n HIBERNATE EDITION!!!.");
      //create connnection


      SessionFactory sessionFactory;

      sessionFactory = new AnnotationConfiguration()

   }

   private static final SessionFactory sessionFactory;
   static {
      try {
         sessionFactory = new AnnotationConfiguration()
               .configure().buildSessionFactory();
               .addPackage("test.animals") //the fully qualified package name
               .addAnnotatedClass(Flight.class)
               .addAnnotatedClass(Sky.class)
               .addAnnotatedClass(Person.class)
               .addAnnotatedClass(Dog.class)
               .addResource("test/animals/orm.xml")
               .configure()
               .buildSessionFactory();

      } catch (Throwable ex) {
         // Log exception!
         throw new ExceptionInInitializerError(ex);
      }
   }

   public static Session getSession()
         throws HibernateException {
      return sessionFactory.openSession();
   }
}
   public Configuration getConfiguration(){
      // Creating Configuration Instance & Passing Hibernate Configuration File
      Configuration configObj = new Configuration();
      configObj.configure(RESOURCES + CONFIG);

   }
   // Method Used To Create The Hibernate's SessionFactory Object
   public static SessionFactory getSessionFactory() {

      // Since Hibernate Version 4.x, Service Registry Is Being Used
      ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings(configObj.getProperties()).build();

      // Creating Hibernate Session Factory Instance
      SessionFactory factoryObj = configObj.buildSessionFactory(serviceRegistryObj);
      return factoryObj;
   }


   /**
    * Static method which runs simple DAO interation of MIDI DB command line app.
    */
   public static void commandLineApp1() {
      System.out.println("Welcome to the app midi database.");
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
/*
    * Method which masks and reads in password on the command line.
    * DO NOT USE INSIDE IDE - BREAKS PROGRAM
    * @return password
    */

   private static String passwordPrompt_EraseDisplay() {
      String password = PasswordField.readPassword("Enter password: ");
      //System.out.println("Password entered was:" + password);
      return password;
   }


   /*
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
