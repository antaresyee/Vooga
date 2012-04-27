package innerGameGUI;

import java.awt.Dimension;
import java.awt.Graphics2D;

import com.golden.gamedev.Game;
import com.golden.gamedev.GameLoader;

public class GUIExampleTwo extends Game{
	StartGUIUpdated start;
	@Override
	public void initResources() {
		// TODO Auto-generated method stub
		start = new StartGUIUpdated(this);
	}

	@Override
	public void render(Graphics2D pen) {
		// TODO Auto-generated method stub
		start.render(pen);
	}

	@Override
	public void update(long elapsedTime) {
		// TODO Auto-generated method stub
		start.update(elapsedTime);
	}
	
	public static void main (String[] args){

		GameLoader game = new GameLoader();
		game.setup(new GUIExampleTwo(), new Dimension(800, 600), false);
		game.start();
	}

}
