package game;

import gameObjects.Barrier;

import gameObjects.Enemy;
import gameObjects.GameObjectData;
import gameObjects.GameObjectFactory;
import gameObjects.HorizontalShip;
import gameObjects.Player;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import playerObjects.CompanionShip;
import playerObjects.Ship;
import playerObjects.SmallShip;

import states.FullHealthState;
import states.LowHealthState;
import states.State;

import levelLoadSave.EnemyLoadObserver;
import levelLoadSave.HorizontalPlayerLoadObserver;
import levelLoadSave.LevelLoader;
import levelLoadSave.LoadObserver;
import levelLoadSave.PlayerLoadObserver;
import levelLoadSave.SimpleLoadObserver;
import maps.Map;
import movement.BackForthMovement;
import movement.Movement;
import movement.TargetedMovement;

import bars.Bar;
import bars.HealthBar;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ImageBackground;

import decorator.HorizontalDecorator;
import decorator.MovementDecorator;
import decorator.SimpleShip;
import decorator.SpaceShip;
import decorator.VerticalDecorator;

public class TopDownDemo extends Game {


	private Player myPlayer;
	private Enemy myEnemy;	
	private Ship myShip; 
	private SpaceShip decoratedShip; 	
	private SpriteGroup myPlayerGroup;
	private SpriteGroup myBarrierGroup;
	private SpriteGroup myEnemyGroup;
	private SpriteGroup myProjectileGroup;
	private Background myBackground;
	private PlayField myPlayfield;
	private PlayerInfo playerInfo;
	private int enemySize;

	private BufferedImage myBackImage;
	private Map myMap;
	private int count=0;
	private List<LoadObserver> myLoadObservers;

	@Override
	public void initResources() {
		myBarrierGroup = new SpriteGroup("barrier");
		myPlayerGroup = new SpriteGroup("player");
		myEnemyGroup = new SpriteGroup("enemy");
		myProjectileGroup = new SpriteGroup("projectile");

		// init background using the new Map class
		myBackImage = getImage("resources/BackFinal.png");
		myMap = new Map(myBackImage, getWidth(), getHeight());
		

		myMap.setSpeed(10);
		myBackground = myMap.getMyBack();
		
		myPlayerGroup.setBackground(myBackground);
		myBarrierGroup.setBackground(myBackground);
		myEnemyGroup.setBackground(myBackground);
		myProjectileGroup.setBackground(myBackground);

		
		myShip = new Ship(200, 2700, "resources/ship.png"); 
		myShip.setImage(getImage("resources/ship.png")); 
		
		VerticalDecorator myV = new VerticalDecorator(new SimpleShip(), myShip);
		decoratedShip = new HorizontalDecorator(myV, myShip); 
		
		myShip.setBackground(myBackground);
		myPlayerGroup.add(myShip); 
	
		
		// init playfield
		myPlayfield = new PlayField(myBackground);
		myPlayfield.addGroup(myPlayerGroup);
		myPlayfield.addGroup(myBarrierGroup);
		myPlayfield.addGroup(myEnemyGroup);
		myPlayfield.addGroup(myProjectileGroup);
		myPlayfield.addCollisionGroup(myPlayerGroup, myBarrierGroup,
				new PlayerBarrierCollision());

		// load level data
		myLoadObservers = new ArrayList<LoadObserver>();
		myLoadObservers.add(new PlayerLoadObserver(myPlayerGroup, this));
		myLoadObservers.add(new SimpleLoadObserver(myBarrierGroup));
		myLoadObservers.add(new EnemyLoadObserver(myEnemyGroup));
		myLoadObservers.add(new HorizontalPlayerLoadObserver(myPlayerGroup, this));
		
		LevelLoader l = new LevelLoader(myLoadObservers);
		l.loadLevelData("serializeTest.ser");
		
		enemySize=myEnemyGroup.getSize();
		
		// initializing PlayerInfo
		playerInfo = new PlayerInfo();
		

	}

	@Override
	public void render(Graphics2D pen) {
		myPlayfield.render(pen);
	}

	@Override
	public void update(long elapsedTime) {
		myMap.moveMap(elapsedTime);
		//playerMovement();
		myPlayfield.update(elapsedTime); 
		decoratedShip.move(this, myShip);
		

		// this is for testing enemy movement
		count =0;
		for (Sprite elem:myEnemyGroup.getSprites()){
			if (count>=enemySize) break;
			Enemy e = (Enemy) elem;
			e.updateEnemy();
			count++;
		}
	}

	public void setPlayer(Player g) {
		myPlayer = g;
	}

}
