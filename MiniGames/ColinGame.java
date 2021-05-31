import DLibX.*;
import java.util.Random;
import java.lang.Math.*;

public class ColinGame implements MiniGame {


  
  public int playGame(DConsole dc) {
    //We need this to define a main method
    dc.setOrigin(DConsole.ORIGIN_CENTER);

    int X = dc.getWidth() / 2; //Spawn point
    int Y = dc.getHeight() / 2;

    int VelY = 0;
    boolean inAir = true;
    boolean count = false;
    short timesJumped = 0;
    double time = 0;

     dc.clear();
    dc.drawString("JUMP!!", dc.getWidth() / 2, dc.getHeight() / 2);
    dc.redraw();
    dc.pause(1000);

    while(true) 


    {
      time++;

      if(dc.isKeyPressed('a')) { //Move left
        X = X - 5;
      }
      if(dc.isKeyPressed('d')) { //Move Right
        X = X + 5;
      }
      if(dc.isKeyPressed('w') && inAir == false) { //Jump
        VelY = -28; //Add velocity
        inAir = true; //save that the character is in air
      }
      if(Y > dc.getHeight() -  20 ) { //if past flooor
        Y = dc.getHeight() - 21; // return onto floor
        VelY = 0; //Null velocity
        inAir = false; //The player is no longer in air, set bool for future use
        count = true; //Set count to true, so we count this as a jump
      }
   
     
     //if(VelY <= dc.getHeight() - 10) {
       VelY++; //Increase vel down
     //}

     Y = Y + VelY; //Update y
      
      dc.fillEllipse(X, Y, 20, 20); //draw the circle 

      //Wincheck
      if((Y < 50) && count == true) { //if high up in the map
        timesJumped++; //We probably jump
        count = false; //stop the counting
      }
      if(timesJumped >= 6) { //if we jump 6 times, end gfame
        time = time - 310; //remove the min time from score
        
        time = (Math.pow(time, 2) * -0.01 + 30); //Quadratic equation that determines speed
        if(time <= 0) { //if score is less then 0, set to 0
          time = 0;
        }
        int score=(int)time; //cast the double as an int
        return score; //return it
      }
      dc.drawString("Jumps: " + timesJumped, 80, 80); //Info
      dc.drawString("Time: " + time, 80, 100);
      dc.redraw();
      dc.pause(20);
      dc.clear();
    }

  }

}