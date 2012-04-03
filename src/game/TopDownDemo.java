package game;
import gameObjects.Barrier;

import gameObjects.Enemy;
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
import com.golden.gamedev.util.ImageUtil;


public class TopDownDemo extends Game {
	
	private Player myPlayer;
	private SpriteGroup myPlayerGroup;
	private SpriteGroup myBarrierGroup;
	private SpriteGroup myEnemyGroup;
	private Background myBackground;
	private PlayField myPlayfield;

	@Override
	public void initResources() {	    
	    myBarrierGroup = new SpriteGroup("barrier");
	    myPlayerGroup = new SpriteGroup("player");
	    myEnemyGroup = new SpriteGroup("enemy");
	    //init background
	    myBackground = new ImageBackground(getImage("resources/black_background.jpg"));
	    myPlayerGroup.setBackground(myBackground);
	    myBarrierGroup.setBackground(myBackground);
	    myEnemyGroup.setBackground(myBackground);
	    
	    //init playfield
	    myPlayfield = new PlayField(myBackground);
	    myPlayfield.addGroup(myPlayerGroup);
	    myPlayfield.addGroup(myBarrierGroup);
	    myPlayfield.addGroup(myEnemyGroup);
	    myPlayfield.addCollisionGroup(myPlayerGroup, myBarrierGroup, new PlayerBarrierCollision());
	    
	    //load level
		LevelLoader l = new LevelLoader();
		String player = "Player";
		String barrier = "Barrier";
		String enemy = "Enemy";
		try {
			List<GameObjectFactory> factories = l.load("savedLevel.json");
			for (GameObjectFactory f : factories){
				if (f.isMyObject(player)){
					myPlayer = (Player) f.makeObject();
					myPlayer.setImage(getImage(myPlayer.getImgPath()));
					myPlayerGroup.add(myPlayer);
				}
				if (f.isMyObject(barrier)){
					Barrier b = (Barrier) f.makeObject();
					b.setImage(getImage(b.getImgPath()));
					myBarrierGroup.add(b);
				}
				if (f.isMyObject(enemy)){
					Enemy e = (Enemy) f.makeObject();
					e.setImage(getImage(e.getImgPath()));
					myEnemyGroup.add(e);
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	@Override
	public void render(Graphics2D pen) {
		myPlayfield.render(pen);
	}

	@Override
	public void update(long elapsedTime) {
		playerMovement();
		myPlayfield.update(elapsedTime);
	}
	
	public void playerMovement(){
		if (myPlayer.getX() > 0 && keyDown(java.awt.event.KeyEvent.VK_A)) {
			myPlayer.moveX(-3);
		}
		if (myPlayer.getX() < 800-myPlayer.getWidth()-3 && keyDown(java.awt.event.KeyEvent.VK_D)){
			myPlayer.moveX(3);
		}
		if (myPlayer.getY() > 0 && keyDown(java.awt.event.KeyEvent.VK_W)){
			myPlayer.moveY(-3);
		}
		if (myPlayer.getY() < 600-myPlayer.getHeight()-3 && keyDown(java.awt.event.KeyEvent.VK_S)){
			myPlayer.moveY(3);
		}
	}

}
