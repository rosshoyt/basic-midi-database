package com.rosshoyt.app;



import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.*;

public class BasicMidiDatabaseDAO implements MidiDatabaseDAO {

   private Connection connection;
   private boolean connectionStatus;

   public BasicMidiDatabaseDAO(){
      connectionStatus = false;

   }

   public boolean createConnection(String url, String user, String password) throws SQLException {
      boolean connected = false;
      connection = DriverManager.getConnection(url, user, password);
      if(!connection.isClosed()) {
         connectionStatus = true;
         connected = true;
      }
      return connected;
   }
   public boolean closeConnection(){
      boolean closed;
      try {
         connection.close();
         closed = true;
      } catch (Exception e) {
         e.printStackTrace();
         closed = false;
      }
      return closed;

   }
   public boolean getConnectionStatus(){
      return connectionStatus;
   }
   public int addSequence(String sequenceName){
      System.out.println("Trying to add sequence" + sequenceName);

      String sql = "INSERT INTO sequence (songname) VALUES (?)";

      try {
         PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
         statement.setString(1, sequenceName);

         statement.executeUpdate();

         ResultSet resultSet = statement.getGeneratedKeys();
         if (resultSet.next()) {
            int sequenceID = resultSet.getInt(1);
            System.out.println("DEBUG - added sequence to DB with seqID = " + sequenceID);
            return sequenceID;
         }
      } catch(Exception e){
         e.printStackTrace();
      }
      return -1;
   }
   public int addMidiEvent(int idSequence, int trackNumber, long evStart, String messageType, int channel, String command,
               int key, int octave, int noteNumber, String noteName, int velocity){


      String sql = "INSERT INTO midi_event " +
            "(id_sequence, track_num, event_start, message_type, channel_num, command, key, octave, note_num, note_name, velocity)"
            //"(trackNumber, evStart, messageType, channel, command, key, octave, noteNumber,"
            + " VALUES (?,?,?,?,?,?,?,?,?,?,?)";
      // sql query
      //String sql = "INSERT INTO PLAYER (firstName, lastName, dateOfBirth, sex, email) VALUES (?,?,?,?,?)";

      // add parameters to query
      try {
         PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
         statement.setInt(1, idSequence);
         statement.setInt(2, trackNumber);
         statement.setLong(3, evStart);
         statement.setString(4, messageType);
         statement.setInt(5, channel);
         statement.setString(6, command);
         statement.setInt(7, key);
         statement.setInt(8, octave);
         statement.setInt(9, noteNumber);
         statement.setString(10, noteName);
         statement.setInt(11, velocity);


         // execute query
         statement.executeUpdate();

         // return id of inserted row
         /*
         ResultSet resultSet = statement.getGeneratedKeys();
         if (resultSet.next()) {
            return resultSet.getInt(1);
         }
         */
         return 1;
      } catch(Exception e){
         //e.printStackTrace();
         System.out.println(e.getMessage());
      }
      return -1;
   }
}
