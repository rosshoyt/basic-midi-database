package midi.musictools;
import com.sun.media.sound.MidiUtils;
import com.sun.org.apache.bcel.internal.generic.RETURN;

import javax.sound.*;
public class MusicTools {


   public MusicTools(){

   }
   public int toOctave(int key){
      return ((key/12)-1);
   }
   //TODO add more foundational conversion methods
}
