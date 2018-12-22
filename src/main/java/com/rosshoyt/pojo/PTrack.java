package com.rosshoyt.pojo;

import javax.persistence.Entity;
import java.util.ArrayList;

@Entity
public class PTrack {
   private ArrayList<PNote> pNotes;
   private int trackNumber;
   public PTrack(int trackNumber){
      this.trackNumber = trackNumber;
      pNotes = new ArrayList<>();
   }
   protected PTrack(){}
   public void addPNote(PNote pNote){
      pNotes.add(pNote);
   }
   public int getNumberPNotes() {
      return pNotes.size();
   }
   public PNote getPNote(int index){ return pNotes.get(index);}
   public void setTrackNumber(int trackNumber){
      this.trackNumber = trackNumber;
   }
   public int getTrackNumber(){
      return trackNumber;
   }
}
