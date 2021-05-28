import DLibX.*;
import MiniGames.*;
import java.util.ArrayList;
import java.util.Random;

public class Main {

  public static void main(String[] args) {

    DConsole dc = new DConsole(600,400);
    dc.setOrigin(DConsole.ORIGIN_CENTER);
    Random r = new Random();

    ArrayList<MiniGame> games = new ArrayList<>();
    games.add(new DiRamioGame());
    games.add(new MilanGame());
    games.add(new MasonGame());
    games.add(new JoeGame());

    // play games FOREVER!
    while(true) {

      // pre-game screen
      dc.clear();
      dc.drawString("Press Space to Play Next Game", dc.getWidth() / 2, dc.getHeight() / 2);
      dc.redraw();

      while(!dc.isKeyPressed(' ')) { // wait until they press space
        dc.pause(20);
      }

      MiniGame toPlay = games.get(r.nextInt(games.size())); // randomly choose a game
      int score = toPlay.playGame(dc); // play the game

      // display how they did in the current game
      dc.clear();
      dc.drawString("You got " + score, dc.getWidth() / 2, dc.getHeight() / 2);
      dc.redraw();

      dc.pause(2000);
    }


  }


}