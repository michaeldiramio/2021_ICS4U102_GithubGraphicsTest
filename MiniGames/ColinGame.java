import DLibX.*;
import java.util.Random;

public class ColinGame implements MiniGame {


  
  public int playGame(DConsole dc) {
    //We need this to define a main method
    dc.setOrigin(DConsole.ORIGIN_CENTER);

    int X = dc.getWidth() / 2;
    int Y = dc.getHeight() / 2;

    int VelY = 0;
    while(true) 


    {

      if(dc.isKeyPressed('a')) {
        X = X - 5;
      }
      if(dc.isKeyPressed('d')) {
        X = X + 5;
      }
      if(dc.isKeyPressed('w')) {
        VelY = -15;
      }
      if(Y > dc.getHeight() -  20 ) {
        Y = dc.getHeight() - 20;
        VelY = 0;
      }
   
     
     if(VelY <= dc.getHeight() - 10) {
       VelY++;
     }

     Y = Y + VelY;
      
      dc.fillEllipse(X, Y, 20, 20);
      
      dc.redraw();
      dc.pause(20);
      dc.clear();
    }

  }

}