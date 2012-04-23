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
import playerObjects.SmallShip;

import states.FullHealthState;
import states.LowHealthState;
import states.State;

import levelLoadSave.EnemyLoadObserver;
import levelLoadSave.HorizontalShipLoadObserver;
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

public class TopDownDemo extends Game {

	//private CompanionShip myShip;

	//private SmallShip comp;
	private Player myPlayer;
	private Enemy myEnemy;
	// private HealthBar myBar; 
	
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
		myLoadObservers.add(new HorizontalShipLoadObserver(myPlayerGroup, this));
		myLoadObservers.add(new PlayerLoadObserver(myPlayerGroup, this));
		myLoadObservers.add(new SimpleLoadObserver(myBarrierGroup));
		myLoadObservers.add(new EnemyLoadObserver(myEnemyGroup));
		
		
		LevelLoader l = new LevelLoader(myLoadObservers);
		l.loadLevelData("serializeTest.ser");
		
		// this is for testing enemy movement

		//Enemy e = new Enemy(100, 2400, "resources/enemy.png", "stateInfo.txt");
		//e.setImage(getImage(e.getImgPath()));
//		myEnemy = e;
//		myEnemyGroup.add(myEnemy);
		enemySize=myEnemyGroup.getSize();
		// testing ship movement
//		CompanionShip s = new CompanionShip(200, 2950, "resources/ship.png");
//		s.setImage(getImage(s.getImgPath()));
//		myShip = s;
//		//myShip.setHozSpeed(5);
//		myPlayerGroup.add(myShip);


		// companion
//		SmallShip c = myShip.getComp();
//		c.setImage(getImage(c.getImgPath()));; 
//		comp = c; 
//		myPlayerGroup.add(comp);
//		
		// initializing PlayerInfo
		playerInfo = new PlayerInfo();
		

//		myBar = new HealthBar(myShip); 

		

	}

	@Override
	public void render(Graphics2D pen) {
		myPlayfield.render(pen);

//		myBar.render(pen); 

		
		// this is for testing enemy movement
		// myEnemy.render(pen);
		// myShip.render(pen);
	}

	@Override
	public void update(long elapsedTime) {
		myMap.moveMap(elapsedTime);
		//playerMovement();
		myPlayfield.update(elapsedTime);
		// updating playerInfo
		
		HorizontalShip hz = (HorizontalShip) myPlayer;
		hz.move(this, 400, 3000);

		//myMap.movePlayer(elapsedTime, myShip);
		// myShip.move(this, myMap.getWidth());
		//myShip.move(this, myMap.getWidth(), myMap.getFrameHeight());


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
