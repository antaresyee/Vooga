package innerGameGUI;

import gameObjects.GameObject;
import gameObjects.GameObjectData;
import gameObjects.Player;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.golden.gamedev.Game;
import com.golden.gamedev.GameLoader;

public class LoadSaveGUIExample extends Game {
	private TabGUI tab1, tab2;
	
	
	@Override
	public void initResources() {
		// TODO Auto-generated method stub
		List<TabGUI> tg = new ArrayList<TabGUI>();
		List<GameObject> gob = new ArrayList<GameObject>();
		String filename = "serializeTest";
		String ext = ".ser";
		String path = filename + ext;
		File file = new File(path);
		int i=2;
		while(file.exists()){
			gob.add(new FileObject(path));
			path = filename + i + ext;
			file = new File(path);
			i++;
		}
//		for(int i = 1; i < 9; i++)
//		{
//			Player p = new Player(0, 0, "");
//			p.setImage(getImage("resources/ship" + i + ".png"));
//			gob.add(p);
//		}
		tab1 = new LoadGUI(this, gob, tg, null, 0, 0);
		
		List<GameObject> gob2 = new ArrayList<GameObject>();

		filename = "serializeTest";
		ext = ".ser";
		path = filename + ext;
		file = new File(path);
		i=2;
		while(file.exists()){
			gob2.add(new FileObject(path));
			path = filename + i + ext;
			file = new File(path);
			i++;
		}
		
		List<GameObjectData> objectsToSave = new ArrayList<GameObjectData>();

		GameObjectData barrierData = new GameObjectData("Barrier");
		barrierData.setX(200.5);
		barrierData.setY(1000.0);
		barrierData.setImgPath("./resources/triangle.png");
		objectsToSave.add(barrierData);

		GameObjectData playerData = new GameObjectData("Player");
		playerData.setX(3.5);
		playerData.setY(4.0);
		playerData.setImgPath("./resources/enemy.png");
		objectsToSave.add(playerData);

		GameObjectData playerData2 = new GameObjectData("Player");
		playerData2.setX(100.0);
		playerData2.setY(500.0);
		playerData2.setImgPath("./resources/enemy.png");
		objectsToSave.add(playerData2);
		
		tab2 = new SaveGUI(this, gob2, objectsToSave, tg, tab1.getX() + tab1.getWidth(), tab1.getY());
		
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
		game.setup(new LoadSaveGUIExample(), new Dimension(800, 600), false);
		game.start();
	}
	
}
