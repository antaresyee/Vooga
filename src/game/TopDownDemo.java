package game;
import gameObjects.Barrier;


import gameObjects.Enemy;
import gameObjects.GameObjectData;
import gameObjects.GameObjectFactory;
import gameObjects.Player;

import java.awt.Graphics2D;
import java.awt.Point;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import levelLoadSave.LevelLoader;
import movement.TargetedMovement;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ImageBackground;


public class TopDownDemo extends Game {
	
	private Player myPlayer;
	private Enemy myEnemy;
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
	    
	    loadLevelData();
	    
//this is for testing enemy movement
		Enemy e = new Enemy (400, 400, "resources/enemy.png", new TargetedMovement(new Point(800, 600)));
		e.setImage(getImage(e.getImgPath()));
		myEnemy = e;
		myEnemyGroup.add(myEnemy);
	}

	@Override
	public void render(Graphics2D pen) {
		myPlayfield.render(pen);
//		this is for testing enemy movement
		myEnemy.render(pen);
	}

	@Override
	public void update(long elapsedTime) {
		playerMovement();
		myPlayfield.update(elapsedTime);
// this is for testing enemy movement
		myEnemy.update();
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
	
	public void loadLevelData() {
//	    //THIS IS IDEAL, BUT CAN'T BECAUSE MUST SET myPlayer, setImage(), etc.
//	    List<GameObjectFactory> allFactories = new ArrayList<GameObjectFactory>();
//	    allFactories.add(Player.getFactory());
//	    allFactories.add(Barrier.getFactory());
//	    allFactories.add(Enemy.getFactory());
//	    
//        //load level
//        LevelLoader l = new LevelLoader();
//        try {
//            List<GameObjectData> gameObjectDatas = l.load("savedLevel.json");
//            for (GameObjectData god : gameObjectDatas) {
//                for (GameObjectFactory factory : allFactories) {
//                    if (factory.isMyObject(god)) {
//                        factory.makeGameObject(god);
//                        break;
//                    }
//                }
//            }
//            
//        }
//        catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
	    
      //load level
      LevelLoader l = new LevelLoader();
      try {
          List<GameObjectData> gameObjectDatas = l.load("savedLevel.json");
          for (GameObjectData god : gameObjectDatas) {
              
              GameObjectFactory playerFactory = Player.getFactory();
              GameObjectFactory barrierFactory = Barrier.getFactory();
              GameObjectFactory enemyFactory = Barrier.getFactory();
              
              if (playerFactory.isMyObject(god)) {
                  myPlayer = (Player) playerFactory.makeGameObject(god);
                  myPlayer.setImage(getImage(myPlayer.getImgPath()));
                  myPlayerGroup.add(myPlayer);
              }
              if (barrierFactory.isMyObject(god)) {
                  Barrier b = (Barrier) barrierFactory.makeGameObject(god);
                  b.setImage(getImage(b.getImgPath()));
                  myBarrierGroup.add(b);
              }
              if (enemyFactory.isMyObject(god)) {
                  Enemy e = (Enemy) enemyFactory.makeGameObject(god);
                  e.setImage(getImage(e.getImgPath()));
                  myEnemyGroup.add(e);
              }
          }
          
      }
      catch (FileNotFoundException e) {
          e.printStackTrace();
      }
	}

}
