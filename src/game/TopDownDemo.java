package game;
import gameObjects.Barrier;
import gameObjects.GameObjectFactory;
import gameObjects.Player;

import java.awt.Graphics2D;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import levelLoadSave.LevelLoader;

import com.golden.gamedev.Game;
import com.golden.gamedev.GameObject;
import com.golden.gamedev.object.SpriteGroup;


public class TopDownDemo extends Game {
	
	private Player myPlayer;
	private SpriteGroup playerGroup;
	private SpriteGroup barrierGroup;

	@Override
	public void initResources() {
		LevelLoader l = new LevelLoader();
		String player = "player";
		String barrier = "barrier";
		try {
			List<GameObjectFactory> factories = l.load("savedLevel.json");
			for (GameObjectFactory f : factories){
				if (f.isMyObject(player)){
					myPlayer = (Player) f.makeObject();
					playerGroup.add(myPlayer);
				}
				if (f.isMyObject(barrier)){
					Barrier b = (Barrier) f.makeObject();
					barrierGroup.add(b);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void render(Graphics2D arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(long arg0) {
		// TODO Auto-generated method stub
		
	}

}
