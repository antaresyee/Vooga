
import java.awt.Dimension;

import levelEditor.LevelEditorGUI;

import com.golden.gamedev.GameLoader;


public class LevelEditorMain {
	 public static void main(String[] args) {
		    
	    	GameLoader game = new GameLoader();
	        game.setup(new LevelEditorGUI(), new Dimension(400,700), false);
	        game.start();
	       
	    }
}
