import DLibX.*;

public class MilanGame implements MiniGame {

  public int playGame(DConsole dc) {
    
    dc.setOrigin(DConsole.ORIGIN_CENTER);

    int score = 0;
    int lives = 1;
    boolean spacebarPressed = false;

    int ballX = -20;
    int ballY = dc.getHeight() / 2;

    int timedAreaX = dc.getWidth() / 2;
    int timedAreaY = dc.getHeight() / 2;
    
    dc.clear();
    dc.drawString("TIMED SPACEBAR", dc.getWidth() / 2, dc.getHeight() / 2);
    dc.redraw();
    dc.pause(1000);

    while(lives > 0) {
      dc.clear();

      // timing check
      if(dc.isKeyPressed(' ') && spacebarPressed == false) {
        if(ballX >= timedAreaX - 50 && ballX <= timedAreaX + 50) {
          spacebarPressed = true;
          score++;
        } else {
          lives--;
        }
      }

      // ball movement
      ballX += 5;

      if(ballX >= dc.getWidth()) {
        if(spacebarPressed == false) {
          lives--;
        }
        ballX = -20;
        spacebarPressed = false;
      }
      
      // drawing
      dc.fillEllipse(ballX, ballY, 20, 20); // ball
      dc.drawRect(timedAreaX, timedAreaY, 100, 20); // timing area
      dc.drawString(score, 20, 20);  // current score


      dc.redraw();
      dc.pause(20);

    }
    return score;





  }


}