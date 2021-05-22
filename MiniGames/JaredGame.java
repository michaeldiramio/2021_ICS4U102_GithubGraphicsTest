import DLibX.*;
import java.util.Random;
import java.util.ArrayList;
import java.awt.Color;

public class JaredGame implements MiniGame {

  private ArrayList<Pipe> pipes = new ArrayList<>();//list of pipes

  public int playGame(DConsole dc){

    Random r = new Random();

    int score = 0;
    int lives = 1;
    int timer = 0;

    int playerX = 100;
    int playerY= dc.getHeight() / 2;
    int playerV = 0;

    while(lives > 0){
      dc.clear();

      if(timer > 50){
        Pipe newPipe = new Pipe(dc.getWidth(), r.nextInt(dc.getHeight() - 120) + 60);
        this.pipes.add(newPipe);
        timer = 0;
      }
      //checking inputs
      if(dc.isKeyPressed(' ')){
        playerV = -7;
      }

      //movements
      playerY = playerY + playerV;
      playerV = playerV + 1;
    
      for(int i = 0; i < this.pipes.size(); i++){
        Pipe temp = this.pipes.get(i);
        temp.move();
      }
   
      


      //collisions
      if((playerY + 15) > dc.getHeight()){
        lives--;
      }
      for(int i = 0; i < this.pipes.size(); i++){
        Pipe temp = this.pipes.get(i);
        if(playerX + 15 > temp.getPositionX() & playerX -15 < temp.getPositionX() + 40){
          if(playerY - 15 < temp.getHole() - 50 || playerY + 15 > temp.getHole() + 50){
            lives--;
          }
          
        }
        if(temp.getPositionX() + 100 < playerX){
          score++;
          pipes.remove(temp);
        }
      }

      //drawing
      dc.setPaint(Color.YELLOW);
      dc.setOrigin(DConsole.ORIGIN_CENTER);
      dc.fillEllipse(playerX,playerY,30,30);
      dc.setPaint(Color.GREEN);
      dc.setOrigin(DConsole.ORIGIN_TOP_LEFT);
      for(int i = 0; i < this.pipes.size(); i++){
        Pipe temp = this.pipes.get(i);
        dc.fillRect(temp.getPositionX(), 0, 40, (temp.getHole() - 50));
        dc.fillRect(temp.getPositionX(), temp.getHole() + 50, 40, dc.getHeight()-(temp.getHole() + 50));
      }
      dc.setOrigin(DConsole.ORIGIN_CENTER);
      dc.setPaint(Color.BLACK);
      dc.drawString(score, dc.getWidth() / 2, 30);

      dc.redraw();
      timer++;
      dc.pause(20);
    }
    pipes.clear();
    return score;
  }
  private class Pipe{
   private int positionX;
   private int opening;

   public Pipe(int width,int hole){
   this.positionX = width;
      this.opening = hole;
    }

    public int getPositionX(){
      return this.positionX;
    }

    public int getHole(){
      return this.opening;
    }

    public void move(){
      this.positionX = this.positionX - 7;
    }
      
  }


}