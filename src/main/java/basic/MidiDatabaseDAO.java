package basic;

import java.sql.SQLException;

public interface MidiDatabaseDAO {

   public boolean createConnection(String url, String user, String password) throws SQLException;
   public boolean closeConnection();
   public boolean getConnectionStatus();
   public int addSequence(String sequenceName);
   public int addMidiEvent(int idSequence, int trackNumber, long evStart, String messageType, int channelNumber, String command,
                           int key, int octave, int noteNumber, String noteName, int velocity);


}
