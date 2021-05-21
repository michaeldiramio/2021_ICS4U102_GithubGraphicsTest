import DLibX.*;
import java.util.Random;
import java.awt.Color;

public class MasonGame implements MiniGame {

  public int playGame(DConsole dc){

    //cretaes random and dconsole
    Random rand = new Random();
    dc.setOrigin(DConsole.ORIGIN_TOP_LEFT);

    //creates the variables that the program will need
    int score = 0;
    int remainingCoins = 15;

    int playerX = 300;
    int playerY = 200;

    int coinX = 0;
    int coinY = 0;

    long t2 = 0;
    long t1 = System.currentTimeMillis();

    boolean coinTouched = true;

    //game loop
    while (remainingCoins > 0){

      //cleares the screen
      dc.clear();

      //displays the score and remaining remaining coins
      dc.drawString("score : " + score, 20, 20);
      dc.drawString("coins left : " + remainingCoins, 500, 20);

      //checks to see if the coin has been touched
      if(playerX >= coinX - 10 && playerX <= coinX + 10 && playerY <= coinY + 10 && playerY >= - 10){
        coinTouched = true;
        score ++;
        t1 = t2;
      }

      //moves the coin after it has been touched
      if(coinTouched == true){

        coinX = rand.nextInt(580) + 10;
        coinY = rand.nextInt(380) + 10;

        coinTouched = false;
        remainingCoins --;
      }
      
      //timer that tracks how long a coin has been active for and deletes the coin if it is not collected in time
      t2 = System.currentTimeMillis();
      if(t2 > t1 + 2500){

        t1 = t2;
        coinTouched = true;
      }
      
      
      
      
      //player movement
      if(dc.isKeyPressed('a')) {
        playerX -= 10;
      }
      if(dc.isKeyPressed('d')) {
        playerX += 10;
      }
      if(dc.isKeyPressed('w')) {
        playerY -= 10;
      }
      if(dc.isKeyPressed('s')) {
        playerY += 10;
      }








      //draws the player
      dc.setPaint(Color.BLACK);
      dc.fillEllipse(playerX, playerY, 20, 20);

      //draws the coin
      dc.setPaint(Color.YELLOW);
      dc.fillEllipse(coinX, coinY, 10, 10);
      dc.setPaint(Color.BLACK);

      //draws the screen and sets the fps
      dc.redraw();
      dc.pause(20);
    }
    return score;
  }
}