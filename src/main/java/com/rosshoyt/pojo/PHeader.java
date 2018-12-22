package com.rosshoyt.pojo;

import javax.persistence.Entity;
@Entity
public class PHeader {
   String title;
   String notes;
   String copyrightInfo;
   float divType;
   int resolution;
   long microsecond_length;
   long tick_length;
   int numTracks;
   public PHeader(float divType, int resolution, long microsecond_length, long tick_length, int numTracks){
      title = "";
      notes = "";
      copyrightInfo = "";

      /*this.divType = divType;
      this.resolution =resolution;
      this.microsecond_length = microsecond_length;
      this.tick_length = tick_length;
      this.numTracks = numTracks;*/
   }
   //TODO Annotations
   public PHeader(){

   }
   @Override
   public String toString(){
      StringBuilder sb = new StringBuilder();

      return "unimplemented";
   }
}
