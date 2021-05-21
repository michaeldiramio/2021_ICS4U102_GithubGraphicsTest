import DLibX.*;
import java.awt.Color;
import java.util.Random;

public class GaelanGame implements MiniGame{
  public int playGame(DConsole dc){
    dc.setOrigin(DConsole.ORIGIN_CENTER);
    boolean alive = true;
    int playerX = dc.getWidth() / 2;
    int playerY = dc.getHeight() - 35;
    int score = 0;
    int block1x = 600;
    int block1y = dc.getHeight() - 35;
    int block2x = 0;
    int block2y = 0;

    dc.clear();
    dc.drawString("JUMP!", dc.getWidth() / 2, dc.getHeight() / 2);
    dc.redraw();
    dc.pause(1000);

    while (alive == true){
      dc.clear();

      playerY = dc.getHeight() - 35;
      if(dc.isKeyPressed(' ')) {
        playerY = playerY - 150;
      }

      dc.fillEllipse(playerX, playerY, 20, 20); // player
      dc.fillRect(block1x, block1y, 50, 130); // thing
      dc.redraw();
      dc.pause(20);
    }
    return score;
  }
}