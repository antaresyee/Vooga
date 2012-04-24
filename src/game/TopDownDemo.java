package game;

import gameObjects.Enemy;
import gameObjects.Player;
import gameObjects.Ship;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import levelLoadSave.EnemyLoadObserver;
import levelLoadSave.HorizontalShipLoadObserver;
import levelLoadSave.LevelLoader;
import levelLoadSave.LoadObserver;
import levelLoadSave.PlayerLoadObserver;
import levelLoadSave.SimpleLoadObserver;
import maps.Map;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

import decorator.CompanionDecorator;
import decorator.HorizontalDecorator;
import decorator.PowerUpDecorator;
import decorator.SimpleShip;
import decorator.SpaceShip;
import decorator.VerticalDecorator;

public class TopDownDemo extends Game {

	private Player myPlayer;
	private Ship myShip;
	private SpaceShip decoratedShip;
	private PowerUpDecorator myPowerUpDecorator;

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
	private int count = 0;
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

		HorizontalDecorator myV = new HorizontalDecorator(new SimpleShip(),
				myShip);
		decoratedShip = new VerticalDecorator(myV, myShip);

		myPowerUpDecorator = new CompanionDecorator(new SimpleShip(), myShip);

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
		myLoadObservers
				.add(new HorizontalShipLoadObserver(myPlayerGroup, this));
		myLoadObservers.add(new PlayerLoadObserver(myPlayerGroup, this));
		myLoadObservers.add(new SimpleLoadObserver(myBarrierGroup));
		myLoadObservers.add(new EnemyLoadObserver(myEnemyGroup));

		LevelLoader l = new LevelLoader(myLoadObservers);
		l.loadLevelData("serializeTest.ser");

		enemySize = myEnemyGroup.getSize();

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

		playerMovement();
		myPlayfield.update(elapsedTime); 
		decoratedShip.action(this, myShip);
		
		myPowerUpDecorator.powerUp(this, myShip);
		
		
		if (!((CompanionDecorator) myPowerUpDecorator).beenCreated())
		{
			((CompanionDecorator) myPowerUpDecorator).getCompanion().setBackground(myBackground);
			myPlayerGroup.add(((CompanionDecorator) myPowerUpDecorator).getCompanion()); 
			((CompanionDecorator) myPowerUpDecorator).setCreated(); 
		}

		
		// this is for testing enemy movement
		count =0;
		for (Sprite elem:myEnemyGroup.getSprites()){
			if (count>=enemySize) break;
			Enemy e = (Enemy) elem;
			e.updateEnemy();
			count++;
		}
		playerInfo.updatePlayerPosition(myPlayer.getX(), myPlayer.getY());

	}

	public void playerMovement() {
		if (myPlayer.getX() > 0 && keyDown(java.awt.event.KeyEvent.VK_A)) {
			myPlayer.moveX(-3);
		}
		if (myPlayer.getX() < getWidth() - myPlayer.getWidth() - 3
				&& keyDown(java.awt.event.KeyEvent.VK_D)) {
			myPlayer.moveX(3);
		}
		if (myPlayer.getY() > myMap.getFrameHeight()
				&& keyDown(java.awt.event.KeyEvent.VK_W)) {
			myPlayer.moveY(-3);
		}
		if (myPlayer.getY() < getHeight() - myPlayer.getHeight() - 3
				+ myMap.getFrameHeight()
				&& keyDown(java.awt.event.KeyEvent.VK_S)) {
			myPlayer.moveY(3);
		}

		// Used for movements or states that need access to info about player's
		// movement
		playerInfo.setUpwardMovement(keyDown(java.awt.event.KeyEvent.VK_W));
		playerInfo.setDownwardMovement(keyDown(java.awt.event.KeyEvent.VK_S));
		playerInfo.setLeftwardMovement(keyDown(java.awt.event.KeyEvent.VK_A));
		playerInfo.setRightwardMovement(keyDown(java.awt.event.KeyEvent.VK_D));

	}

	public void setPlayer(Player g) {
		myPlayer = g;
	}

}
