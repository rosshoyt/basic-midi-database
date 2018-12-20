
package com.rosshoyt.app;



//import com.fasterxml.classmate.AnnotationConfiguration;
import com.rosshoyt.pw_utils.PasswordField;
import com.rosshoyt.model.SequenceParser;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.persistence.*;

public class Main {
   //midi file info
   private static final String RESOURCE_PATH = "src/main/resources/";
   private static String[] midiSrcFiles = new String[]{"pianocon.mid", "la_mer_1.mid",
         "helloWorld.mid"};
   //connection info
   private static final String DRIVER = "com.mysql.jdbc.Driver";
   private static final String CONFIG = "hibernate.cfg.xml";

   public static void main(String[] args) {
      if (args.length > 0) {
         //launch gui
      }
      //else launch
      commandLineAppHibernate();
   }

   public static void commandLineAppHibernate() {
      System.out.println("Welcome to the basic midi database V2.0 - \n HIBERNATE EDITION!!!.");


      //get connection properties (may need to prompt for password before putting properties)

      Map<String, String> properties = new HashMap<>();
      properties.put("javax.persistence.jdbc.user", "root");
      properties.put("javax.persistence.jdbc.password", promptForPassword_EraseDisplay());
      EntityManagerFactory emf = Persistence.createEntityManagerFactory(
            "Basic-Midi-Database-Persistence-Unit", properties);

      //ask for what midi file user would like to test (list available midi files or allow
      //to add their own path


      //pass midi file to MidiParser

   }
   /* SAMPLE TRANSACTION
      em.getTransaction().begin();
      Employee employee = new Employee();
      employee.setName("Chandan");
      System.out.println("COMIITING");
      em.persist(employee);
      em.getTransaction().commit();
*/


 /*  private static final SessionFactory sessionFactory;
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

   /**
    * Static method which runs simple DAO interation of MIDI DB command line app.
    */
   public static void commandLineAppJDBC() {
      System.out.println("Welcome to the app midi database.");
      String sequenceName = getMidiFileResourcePath(2).toString();

      //object to pass to MidiParser
      MidiDatabaseDAO dao = new BasicMidiDatabaseDAO();
      Sequence sequence;
      String password = promptForPassword_EraseDisplay();
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
      SequenceParser midiParser = new SequenceParser(sequence);

      System.out.println("Parsing midi file " + sequenceName);
      midiParser.parseMidi();

   }

   /*
    * Method which masks and reads in password on the command line.
    * DO NOT USE INSIDE IDE - BREAKS PROGRAM
    * @return password
    */

   private static String promptForPassword_EraseDisplay() {
      String password = PasswordField.readPassword("Enter password: ");
      //System.out.println("Password entered was:" + password);
      return password;
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
   private static StringBuilder getMidiFileResourcePath(int index){
      return new StringBuilder(midiSrcFiles[index] + RESOURCE_PATH);
   }
}
