package levelEditor;

import gameObjects.GameObjectData;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.golden.gamedev.Game;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

public class LevelEditorGUI extends Game {
	// private SpriteGroup ENEMIES;
	private List<Sprite> list;
	private SpriteGroup ALL;
	private Sprite current = null;
	private Sprite enemy;
	private Sprite barrier;
	private Sprite powerup;
	private Sprite player;
	private int count = 0;
	private int time = 0;
	//initialize player position and image path
	private int playerX = 30;
	private String playerImgPath = "resources/ship.png";
	//initialize barrier position and image path
	private int barrierX = 130;
	private String barrierImgPath = "resources/black.png";
	//initialize enemy position and image path
	private int enemyX = 230;
	private String enemyImgPath = "resources/enemy.png";
	//initialize powerup position and image path
	private int powerupX = 330;
	private String powerupImgPath = "resources/powerup.png";
	
	private int counter = 0;
	private int totalSprites=4;
	public List<GameObjectData> level;


	@Override
	public void initResources() {
		// create enemy sprite	
		enemy = new Sprite(getImage(enemyImgPath), enemyX,
				700 - getImage(enemyImgPath).getHeight() - 40);
		enemy.setID(1);
		// create barrier sprite
		barrier = new Sprite(getImage(barrierImgPath));
		barrier.setLocation(barrierX, 700 - getImage(barrierImgPath)
				.getHeight() - 40);
		barrier.setID(1001);
		// create powerup sprite
		powerup = new Sprite(getImage(powerupImgPath));
		powerup.setLocation(powerupX, 700 - getImage(powerupImgPath)
				.getHeight() - 40);
		powerup.setID(2001);
		// create player sprite
		player = new Sprite(getImage(playerImgPath));
		player.setLocation(playerX, 700 - getImage(playerImgPath)
				.getHeight() - 40);
		player.setID(3001);

		ALL = new SpriteGroup("All");
		ALL.add(enemy);
		ALL.add(barrier);
		ALL.add(player);
		ALL.add(powerup);

		list = new ArrayList<Sprite>();
		list.add(enemy);
		list.add(barrier);
		list.add(player);
		list.add(powerup);

	}

	@Override
	public void render(Graphics2D pen) {
		// TODO Auto-generated method stub
		pen.setColor(Color.WHITE);
		pen.fillRect(0, 0, getWidth(), getHeight());
		ALL.render(pen);
		pen.setColor(Color.BLACK);
		pen.draw(new Line2D.Double(0.0, 600.0, 400.0, 600.0));
	}

	@Override
	public void update(long elapsedTime) {
		// TODO Auto-generated method stub
		ALL.update(elapsedTime);

		if (clicked() != null && current == null) {
			current = clicked();
			time = count;
		}
		if (current != null) {
			current.setLocation(getMouseX(), getMouseY());
			current.moveX(-current.getWidth() / 2);
			current.moveY(-current.getHeight() / 2);
			if (click() && time != count) {
				String input = yesOrNo();

				ArrayList<Sprites.Factory> factory = new ArrayList<Sprites.Factory>();
				factory.add(new EnemySprite.Factory());
				factory.add(new BarrierSprite.Factory());
				factory.add(new PowerUpSprite.Factory());

				for (Sprites.Factory check : factory) {
					if (check.isType(current.getID())) {
						Sprites newSpr = check.makeSprite();
						if (input.equals("Yes")) {
							Sprite new1 = new Sprite(
									getImage(newSpr.getPath()),
									newSpr.getStartX(), newSpr.getStartY());
							new1.setID(newSpr.newID());
							ALL.add(new1);
							totalSprites++;
							list.add(new1);
						} else {
							current.setLocation(newSpr.getStartX(),
									newSpr.getStartY());
						}
					}
				}

				current = null;

			}
		}
		count++;
		if (keyDown(KeyEvent.VK_CONTROL) && keyPressed(KeyEvent.VK_S)) {
			Sprite[] allSprite = new Sprite[ALL.getSize()];
			allSprite = ALL.getSprites();
			level = make(allSprite);
			for (GameObjectData f : level) {
				System.out.print(f.getImgPath());
				System.out.print(" ");
				System.out.print(f.getX());
				System.out.print(" ");
				System.out.println(f.getY());
			}
		}

	}

	private String yesOrNo() {
		Object[] options = { "Yes", "No" };

		String input = (String) JOptionPane.showInputDialog(new JFrame(),
				"Would you like to place the Game Object here?",
				"Level Editor'", JOptionPane.PLAIN_MESSAGE, null, options,
				options[0]);
		return input;
	}

	private Sprite clicked() {
		Sprite temp = null;

		for (Sprite elem : list) {
			if (click() && (checkPosMouse(elem, true))) {
				temp = elem;
				break;
			}
		}
		return temp;
	}

	private List<GameObjectData> make(Sprite[] sprites) {
		ArrayList<GameObjectData> temp = new ArrayList<GameObjectData>();
		ArrayList<Sprites.Factory> factory = new ArrayList<Sprites.Factory>();
		factory.add(new EnemySprite.Factory());
		factory.add(new BarrierSprite.Factory());
		factory.add(new PowerUpSprite.Factory());
		factory.add(new PlayerSprite.Factory());
		for (Sprite elem : sprites) {
			if (counter < totalSprites) {
				for (Sprites.Factory check : factory) {

					if (check.isType(elem.getID()) && elem.getY() < 580) {

						GameObjectData god = new GameObjectData(check.getID());
						god = check.makeGameObject(elem);
						temp.add(god);

					}
				}
				counter++;
			} else
				break;
		}
		return temp;

	}

	public static void main(String[] args) throws MalformedURLException,
			URISyntaxException {
		GameLoader game = new GameLoader();
		game.setup(new LevelEditorGUI(), new Dimension(400, 700), false);
		game.start();
	}
}
