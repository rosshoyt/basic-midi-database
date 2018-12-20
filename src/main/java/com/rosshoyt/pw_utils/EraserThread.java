package com.rosshoyt.pw_utils;

public class EraserThread implements Runnable {
   private boolean stop;

   public EraserThread(String prompt) {
      System.out.print(prompt);
   }

   public void run () {
      while (!stop){
         System.out.print("\010*");
         try {
            Thread.currentThread().sleep(1);
         } catch(InterruptedException ie) {
            ie.printStackTrace();
         }
      }
   }

   public void stopMasking() {
      this.stop = true;
   }
}