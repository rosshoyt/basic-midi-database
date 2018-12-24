package com.rosshoyt.app;
import com.rosshoyt.model.MidiParser;
import com.rosshoyt.model.ModelRunner;
import com.rosshoyt.pojo.PTrack;
import com.rosshoyt.pw_utils.PasswordField;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.persistence.*;
import javafx.application.*;
import javafx.stage.Stage;

public class Main extends Application {
   //midi file info
   private static final String RESOURCE_PATH = "src/main/resources/";
   private static String[] midiSrcFiles = new String[]{"pianocon.mid", "la_mer_1.mid",
         "helloWorld.mid"};
   //connection info
   private static final String DRIVER = "com.mysql.jdbc.Driver";
   private static final String CONFIG = "hibernate.cfg.xml";
   private static Scanner scanner;

   public static void main(String[] args) {
      if (args.length > 0) {
         launch(args);
      }
      //else launch commandline
      commandLineApp();
   }

   @Override
   public void start(Stage primaryStage) {

   }

   public static void commandLineApp() {
      scanner = new Scanner(System.in);
      ModelRunner modelRunner = new ModelRunner();
      String midiSrc = "/Users/RossHoyt/IdeaProjects/basicmididatabase-MVN/src/main/resources/helloWorld.mid";

      //connection params
      String dbURL = "jdbc:mysql://localhost/";
      String welcomeMsg = "Welcome to the basic midi database V2.0 - \nHIBERNATE EDITION!!!\n"
            + "The midi file being used: " + midiSrc;
      String dbNamePrompt = "Enter the name of the database: ";
      String dbUsernamePrompt = "Enter your database username: ";
      String dbPasswordPrompt = "Enter your database password: ";


      //START {COMMAND LINE APP}
      System.out.println(welcomeMsg);
      //Set file -
      System.out.println("Setting file");
      //could ask for file name/path in above- hardcoded for now
      try {
         File file = new File(midiSrc);
         modelRunner.setFile(file);
      } catch (Exception e) {
         e.printStackTrace();
         return;
      }

      /*
      Connection Data Prompts
       */
      System.out.println(dbNamePrompt);
      String dbName = scanner.next();
      System.out.println("\n" + dbUsernamePrompt);
      String dbUsername = scanner.next();
      String pw = promptForPassword_EraseDisplay("\n" + dbPasswordPrompt);


      System.out.println("\nSetting JPA connection properties...");
      /*
      set JPA connection properties
       */
      Map<String, String> properties = new HashMap<>();
      properties.put("javax.persistence.jdbc.url", dbURL + dbName);
      properties.put("javax.persistence.jdbc.user", dbUsername);
      properties.put("javax.persistence.jdbc.password", pw);

      //JPA Code
      System.out.println("Creating Entity Manager Factory...");
      EntityManagerFactory emf = Persistence.createEntityManagerFactory(
            "Basic-Midi-Database-Persistence-Unit", properties);

      System.out.print("Creating Entity Manager...");
      EntityManager em = emf.createEntityManager();
      System.out.print("EM Status = " + (em.isOpen() ? "Is Open\n":"Is Closed\n"));

      PTrack[] pTracks;
      System.out.println();

      System.out.println("Beginning Transaction");
      em.getTransaction().begin();
      try {
         System.out.println("Parsing midi file");
         pTracks = modelRunner.parseTracks();
         displayResults(pTracks);
         System.out.println("Persisting the Notes");

         for(int i= 0; i < pTracks.length; i++) {
            PTrack pt = pTracks[i];
            for (int j = 0; j < pt.getNumberPNotes(); j++) {
               em.persist(pt.getPNote(j));
            }
         }
      } catch(Exception e){
         e.printStackTrace();
      }
      System.out.println("Committing Transaction");
      em.getTransaction().commit();


      /* SAMPLE TRANSACTION
      em.getTransaction().begin();
      Employee employee = new Employee();
      employee.setName("Chandan");
      System.out.println("COMIITING");
      em.persist(employee);
      em.getTransaction().commit();
*/







   }


   private static void displayResults(PTrack[] pTracks) {
      System.out.println("Number of Tracks parsed = " + pTracks.length
            + "\nNow Displaying Each Track's Results:\n");

      for (int i = 0; i < pTracks.length; i++) {
         PTrack pt = pTracks[i];
         System.out.println("PTrack #" + pt.getTrackNumber() + ":");
         for (int j = 0; j < pt.getNumberPNotes(); j++) {
            System.out.println(
                  "         PNote #" + (j + 1) + " " + pt.getPNote(j).toString());
         }
         System.out.println();
      }
   }

   /*
    * Method which masks and reads in password on the command line.
    * DO NOT USE INSIDE IDE - BREAKS PROGRAM
    * @return password
    */

   private static String promptForPassword_EraseDisplay(String prompt) {
      return PasswordField.readPassword(prompt);
      //System.out.println("Password entered was:" + password);

   }


   /*
    * Method which takes in password - NO MASKING.
    * FOR USE INSIDE IDE FOR TESTING
    * @return password
    */

   private static String promptForPassword_noMask() {
      //get password info
      Scanner scanner = new Scanner(System.in);
      String password = "";
      System.out.print("Enter password: ");
      password = scanner.next();
      System.out.println();
      return password;
   }

   private static StringBuilder getMidiFileResourcePath(int index) {
      return new StringBuilder(midiSrcFiles[index] + RESOURCE_PATH);
   }
}
   /* HIBERnATE STUFF
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

*/

