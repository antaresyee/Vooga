package game;
import gameObjects.Barrier;

import gameObjects.GameObject;
import gameObjects.GameObjectFactory;
import gameObjects.Player;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.util.List;

import levelLoadSave.LevelEditorLoader;
import levelLoadSave.LevelLoader;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.CollisionManager;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ImageBackground;


public class TopDownDemo extends Game {
	
	private Player myPlayer;
	private SpriteGroup myPlayerGroup;
	private SpriteGroup myBarrierGroup;
	private Background myBackground;
	private PlayField myPlayfield;

	@Override
	public void initResources() {
		LevelEditorLoader l = new LevelEditorLoader();
		try {
			List<GameObject> objs = l.load("savedLevel.json");
			for(GameObject o : objs)
			{
				o.setImage(getImage(o.getPath()));

			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		LevelLoader l = new LevelLoader();
//		String player = "player";
//		String barrier = "barrier";
//		try {
//			List<GameObjectFactory> factories = l.load("savedLevel.json");
//			for (GameObjectFactory f : factories){
//				if (f.isMyObject(player)){
//					myPlayer = (Player) f.makeObject();
//					playerGroup.add(myPlayer);
//				}
//				if (f.isMyObject(barrier)){
//					Barrier b = (Barrier) f.makeObject();
//					barrierGroup.add(b);
//				}
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
		
	}

	@Override
	public void render(Graphics2D pen) {
		myPlayfield.render(pen);
		
	}

	@Override
	public void update(long elapsedTime) {
		myPlayfield.update(elapsedTime);
		playerMovement();
	}
	
	public void playerMovement(){
		if (myPlayer.getX() > 0 && keyDown(java.awt.event.KeyEvent.VK_A)) {
			myPlayer.moveX(-3);
		}
		if (myPlayer.getX() < 800 && keyDown(java.awt.event.KeyEvent.VK_D)){
			myPlayer.moveX(3);
		}
		if (myPlayer.getY() > 0 && keyDown(java.awt.event.KeyEvent.VK_W)){
			myPlayer.moveY(-3);
		}
		if (myPlayer.getY() < 600 && keyDown(java.awt.event.KeyEvent.VK_S)){
			myPlayer.moveY(3);
		}
	}

}
