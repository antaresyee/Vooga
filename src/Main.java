import java.awt.Dimension;

import levelEditor.LevelEditorGUI;

import game.TopDownDemo;

import com.golden.gamedev.GameLoader;


public class Main {
    
    public static void main(String[] args) {
        GameLoader game = new GameLoader();
        game.setup(new TopDownDemo(), new Dimension(400,600), false);
        game.start();
    }
    
}
