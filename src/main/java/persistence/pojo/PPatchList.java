package persistence.pojo;
import javax.sound.midi.Patch;
public class PPatchList {
   private Patch[] patchList;
   public PPatchList(){

   }
   public PPatchList(Patch[] patchList){
      this.patchList = patchList;
   }
}
