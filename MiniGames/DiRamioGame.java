import DLibX.*;
import java.util.Random;

public class DiRamioGame implements MiniGame {

  public int playGame(DConsole dc) {
    
    Random r = new Random();
    dc.setOrigin(DConsole.ORIGIN_CENTER);

    int score = 0;
    int lives = 1;

    int playerX = dc.getWidth() / 2; // starting position
    int playerY = dc.getHeight() - 30;

    int thingX = r.nextInt(dc.getWidth() - 20) + 10;
    int thingY = -10;

    while(lives > 0) {
      dc.clear();

      // player movement
      if(dc.isKeyPressed('a')) {
        playerX -= 5;
      }
      if(dc.isKeyPressed('d')) {
        playerX += 5;
      }

      // thing movement
      thingY += 5;

      if(thingY >= dc.getHeight()) {
        thingY = -10;
        thingX = r.nextInt(dc.getWidth() - 20) + 10;
        score++;
      }

      // collision check
      int xDiff = playerX - thingX;
      int yDiff = playerY - thingY;
      double dist = Math.sqrt(xDiff * xDiff + yDiff * yDiff);

      if(dist < 30) { // touching
        lives--;
      }
      

      // drawing
      dc.fillEllipse(playerX, playerY, 20, 20); // player
      dc.fillEllipse(thingX, thingY, 10, 10); // thing
      dc.drawString(score, 20, 20);  // current score


      dc.redraw();
      dc.pause(20);

    }
    return score;





  }


}