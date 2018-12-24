package com.rosshoyt.pojo;

import javax.persistence.Entity;
@Entity
public class PHeader {
   private String title;
   private String notes;
   private String copyrightInfo;
   private float divType;
   private int resolution;
   private long microsecond_length;
   private long tick_length;
   private int numTracks;
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
