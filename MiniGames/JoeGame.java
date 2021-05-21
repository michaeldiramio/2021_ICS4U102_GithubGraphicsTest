import DLibX.*;
import java.util.Random;
import java.awt.Color;
import java.lang.Math;
import java.awt.Font;

public class JoeGame implements MiniGame {
  public int playGame(DConsole dc) {

    Random rand = new Random();

    dc.setOrigin(DConsole.ORIGIN_CENTER);

    int score = 0;
    int lives = 1;

    int playerX = dc.getWidth() / 2;
    int playerY = dc.getHeight() / 2;

    int targetX = rand.nextInt((dc.getWidth() - 10) + 1) + 10;
    int targetY = rand.nextInt((dc.getHeight() - 10) + 1) + 10;

    double timer = 0;

    dc.clear();
    dc.drawString("HIT THE TARGETS BEFORE 3!!!", dc.getWidth() / 2, dc.getHeight() / 2);
    dc.redraw();
    dc.pause(1000);

    Font backup = dc.getFont();

    while(lives >= 0) {
      dc.clear();


      //logic

      // collision check
      int xDiff = playerX - targetX;
      int yDiff = playerY - targetY;
      double dist = Math.sqrt(xDiff * xDiff + yDiff * yDiff);

      if(dist < 30) { // touching
        targetX = rand.nextInt((dc.getWidth() - 10) + 1) + 10;
        targetY = rand.nextInt((dc.getHeight() - 10) + 1) + 10;
        score++;
        timer = 1;
      }
      //timer check
      if(timer >= 2.6) {
        lives--;
      }
      

      //movement

      if(dc.isKeyPressed('w')) {
        playerY -= 5;
      }
      if(dc.isKeyPressed('s')) {
        playerY += 5;
      }
      if(dc.isKeyPressed('a')) {
        playerX -= 5;
      }
      if(dc.isKeyPressed('d')) {
        playerX += 5;
      }
      //walls
      if(playerX <= -5){
        playerX = dc.getWidth() -5;
      }
      if(playerX >= dc.getWidth() + 5) {
        playerX = 5;
      }
      if(playerY <= -5) {
        playerY = dc.getHeight() - 5;
      }
      if(playerY >= dc.getHeight() + 5) {
        playerY = 5;
      }
      //drawing 
      dc.setPaint(Color.RED);
      dc.fillEllipse(targetX, targetY, 40, 40);
      dc.setPaint(Color.BLACK);
      dc.fillEllipse(playerX, playerY, 10, 10);
      dc.setPaint(new Color(0,0,0,50));
      dc.setFont(new Font("Arial", Font.PLAIN, 60));
      dc.drawString(Math.round(timer), dc.getWidth() / 2, dc.getHeight() / 2);
      dc.setPaint(Color.BLACK);
      dc.setFont(backup);
      dc.drawString(score, 20, 20);
      

      //timer... each cycle is 20ms

      timer = timer + 0.02;

      dc.redraw();
      dc.pause(20);
    }
    return score;
  }
}