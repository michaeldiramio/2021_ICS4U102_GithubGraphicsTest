import DLibX.*;

public class Main {

  public static void main(String[] args) {

    DConsole dc = new DConsole(600,400);
    dc.setOrigin(DConsole.ORIGIN_CENTER);
    
    dc.fillEllipse(200, 200, 50, 90);


    dc.redraw();

  }


}