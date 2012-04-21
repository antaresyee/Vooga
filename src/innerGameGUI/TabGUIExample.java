package innerGameGUI;

import gameObjects.GameObject;
import gameObjects.Player;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import com.golden.gamedev.Game;
import com.golden.gamedev.GameLoader;

public class TabGUIExample extends Game {
	private TabGUI tab1, tab2;
	
	/*
	 * logically contain all the tabs
	 * 
	 * 
	 * (non-Javadoc)
	 * @see com.golden.gamedev.Game#initResources()
	 */
	
	@Override
	public void initResources() {
		// TODO Auto-generated method stub
		List<TabGUI> tg = new ArrayList<TabGUI>();
		List<GameObject> gob = new ArrayList<GameObject>();
		for(int i = 1; i < 9; i++)
		{
			Player p = new Player(0, 0, "");
			p.setImage(getImage("resources/ship" + i + ".png"));
			gob.add(p);
		}
		tab1 = new TabGUI(this, gob, "ships", tg, 0, 0);
		
		List<GameObject> gob2 = new ArrayList<GameObject>();
		Player p2 = new Player(0, 0, "");
		p2.setImage(getImage("resources/Black.png"));
		gob2.add(p2);
		
		Player p3 = new Player(0, 0, "");
		p3.setImage(getImage("resources/shieldbubble.jpg"));
		gob2.add(p3);
		
		tab2 = new TabGUI(this, gob2, "weapons", tg, tab1.getX() + tab1.getWidth(), tab1.getY());
		
		tg.add(tab1);
		tg.add(tab2);
	}

	@Override
	public void render(Graphics2D pen) {
		// TODO Auto-generated method stub
		
		tab1.render(pen);
		tab2.render(pen);

		
	}

	@Override
	public void update(long elapsedTime) {
		// TODO Auto-generated method stub
		tab1.update(elapsedTime);
		tab2.update(elapsedTime);
	}
	
	public static void main(String[] arg0)
	{
		GameLoader game = new GameLoader();
		game.setup(new TabGUIExample(), new Dimension(800, 600), false);
		game.start();
	}
	
}
