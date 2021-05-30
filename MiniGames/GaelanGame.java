import DLibX.*;
import java.awt.Color;
import java.util.Random;

public class GaelanGame implements MiniGame{
  public int playGame(DConsole dc){
    dc.setOrigin(DConsole.ORIGIN_CENTER);
    Random ran = new Random();
    int playerX = dc.getWidth() / 2;
    int playerY = dc.getHeight() - 50;
    int lives = 1;
    int score = 0;
    int block1x = 600;
    int block1y = dc.getHeight() - 35;
    int block2x = 900;
    int block2y = 10;

    dc.clear();
    dc.drawString("PRESS SPACE TO DODGE!!", dc.getWidth() / 2, dc.getHeight() / 2);
    dc.redraw();
    dc.pause(1000);

    while (lives > 0){
      dc.clear();

      playerY = dc.getHeight() - 50;
      if(dc.isKeyPressed(' ')) {
        playerY = 50;
      }

      block1x -= 5;
      block2x -= 5;

      if(block1x <= -50){
        block1x = 600 + ran.nextInt(201);
        score++;
      }

      if(block2x <= -50){
        block2x = 1100 + ran.nextInt(201);
        score++;
      }

      int xDiff = playerX - block1x;
      int yDiff = playerY - block1y;
      double dist = Math.sqrt(xDiff * xDiff + yDiff * yDiff);

      if(dist < 30) {
        lives--;
      }

      int xDiff2 = playerX - block2x;
      int yDiff2 = playerY - block2y;
      double dist2 = Math.sqrt(xDiff2 * xDiff2 + yDiff2 * yDiff2);

      if(dist < 50) {
        lives--;
      }

      dc.fillEllipse(playerX, playerY, 20, 20); // player
      dc.fillRect(block1x, block1y, 50, 200);
      dc.fillRect(block2x, block2y, 50, 200); // thing
      dc.drawString(score, 20, 20)
      dc.redraw();
      dc.pause(20);
    }
    return score;
  }
}