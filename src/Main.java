import java.awt.Dimension;



import game.TopDownDemo;


import com.golden.gamedev.GameLoader;


public class Main {
    
    public static void main(String[] args) {
        GameLoader game = new GameLoader();
        game.setup(new TopDownDemo(), new Dimension(400,700), false);
        game.start();
    }
    
}
