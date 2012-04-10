package levelEditor;

import gameObjects.GameObjectData;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import levelLoadSave.LevelSaver;
import maps.Map;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

/**
 * 
 * @author Leo Rofe
 * 
 */
public class LevelEditorGUI extends Game {
	private List<Sprite> list;
	private SpriteGroup ALL;
	private Sprite current = null;
	private Sprite enemy;
	private Sprite barrier;
	private Sprite powerup;
	private Sprite player;
	private Sprite line;
	private int count = 0;
	private int time = 0;
	private int counter = 0;
	private int totalSprites = 4;
	public List<GameObjectData> level;
	private int backHeight;
	private String myPlayer = "Player";

	// initialize player position and image path
	private int playerX = 30;
	private String playerImgPath = "resources/ship.png";

	// initialize barrier position and image path
	private int barrierX = 130;
	private String barrierImgPath = "resources/black.png";

	// initialize enemy position and image path
	private int enemyX = 230;
	private String enemyImgPath = "resources/enemy.png";

	// initialize powerup position and image path
	private int powerupX = 330;
	private String powerupImgPath = "resources/powerup.png";

	// initialize line and background image path
	private String lineImgPath = "resources/line.png";
	private String backImgPath = "resources/Back2.png";
	
	// initializes the Map to scroll in the background
	private Map myMap;
	private BufferedImage myBackImage;
	private Background myBackground;

	@Override
	public void initResources() {

		// create Map
		myBackImage = getImage(backImgPath);
		myMap = new Map(myBackImage, getWidth(), getHeight());
		myBackground = myMap.getMyBack();
		backHeight = myBackImage.getHeight();

		// create enemy sprite
		enemy = new Sprite(getImage(enemyImgPath), enemyX, backHeight
				- getImage(enemyImgPath).getHeight() - 40);
		enemy.setBackground(myBackground);
		enemy.setID(1);

		// create barrier sprite
		barrier = new Sprite(getImage(barrierImgPath), barrierX, backHeight
				- getImage(barrierImgPath).getHeight() - 40);
		barrier.setBackground(myBackground);
		barrier.setID(1001);

		// create powerup sprite
		powerup = new Sprite(getImage(powerupImgPath), powerupX, backHeight
				- getImage(powerupImgPath).getHeight() - 40);
		powerup.setBackground(myBackground);
		powerup.setID(2001);

		// create player sprite
		player = new Sprite(getImage(playerImgPath), playerX, backHeight
				- getImage(playerImgPath).getHeight() - 40);
		player.setBackground(myBackground);
		player.setID(3001);

		// create line sprite
		line = new Sprite(getImage(lineImgPath), 0, backHeight - 100);
		line.setBackground(myBackground);

		// add all sprites to ALL SpriteGroup
		ALL = new SpriteGroup("All");
		ALL.add(enemy);
		ALL.add(barrier);
		ALL.add(player);
		ALL.add(powerup);
		ALL.add(line);
		ALL.setBackground(myBackground);

		// add Sprites to ArrayList of Sprites
		list = new ArrayList<Sprite>();
		list.add(enemy);
		list.add(barrier);
		list.add(player);
		list.add(powerup);

	}

	@Override
	public void render(Graphics2D pen) {
		// render
		myBackground.render(pen);
		ALL.render(pen);
	}

	@Override
	public void update(long elapsedTime) {

		// update
		myBackground.update(elapsedTime);
		ALL.update(elapsedTime);

		// Press Spacebar to move to the bottom of screen
		if (keyDown(java.awt.event.KeyEvent.VK_SPACE))
			myMap.moveToBottom();

		// press "t" to scroll screen up the background
		// press "g" to scroll screen down the background
		if (keyDown(java.awt.event.KeyEvent.VK_T))
			myMap.guiMoveUp();
		if (keyDown(java.awt.event.KeyEvent.VK_G))
			myMap.guiMoveDown();

		// if user clicks on a gameobject and no other gameobject is currently being dragged
		// set the clicked on gameobject as current (sticks to the mouse location)
		if (clicked() != null && current == null) {
			current = clicked();
			time = count;
		}
		// set current gameobject to mouse location
		if (current != null) {
			followMouse();
			
			// if user clicks, place gameobject and create the correct new sprite
			if (click() && time != count) {

				// create list of Sprite Factories
				ArrayList<Sprites.Factory> factory = new ArrayList<Sprites.Factory>();
				factory.add(new EnemySprite.Factory());
				factory.add(new BarrierSprite.Factory());
				factory.add(new PowerUpSprite.Factory());
				factory.add(new PlayerSprite.Factory());

				for (Sprites.Factory check : factory) {
					if (check.isType(current.getID())) {
						int input = yesOrNo(check.getType());
				
						Sprites newSpr = check.makeSprite();
	
						if (check.getType().equals(myPlayer) && input == 0) {
							newSpr.askQuestions();
							break;
						}
						//create and place new Sprite on background
						createNewSprite(input, newSpr);
					}
				}
				current = null;
			}
		}
		count++;

		// when user presses control and s at same time, the game will create a
		// list of GameObject Data
		if ((keyDown(KeyEvent.VK_CONTROL) && keyPressed(KeyEvent.VK_S))) {
			saveFile();
		}
	}

	private void createNewSprite(int input, Sprites newSpr) {
		// if user is happy with location make new sprite
		if (input == 0) {
			Sprite new1 = new Sprite(getImage(newSpr.getPath()), newSpr.getStartX(), newSpr.getStartY());
			new1.setID(newSpr.newID());
			new1.setBackground(myBackground);
			newSpr.askQuestions();
			ALL.add(new1);
			totalSprites++;
			list.add(new1);

		} else {
			// else send him back to original location
			current.setLocation(newSpr.getStartX(),
					newSpr.getStartY());
		}
	}

	// saves the level
	private void saveFile() {
		if (player.getY() < backHeight - 100) {
			Sprite[] allSprite = new Sprite[ALL.getSize()];
			allSprite = ALL.getSprites();
			level = makeGODList(allSprite);

			try {
				LevelSaver.jsonSave(level, "savedLevel");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finish();
		} else {
			JOptionPane
					.showMessageDialog(new JFrame(),
							"You must place a 'Player' game object before you can save the level!");
		}
		// for (GameObjectData f : level) {
		// System.out.print(f.getImgPath());
		// System.out.print(" ");
		// System.out.print(f.getX());
		// System.out.print(" ");
		// System.out.println(f.getY());
		// }
	}

	// sets current game object to follow the mouse
	private void followMouse() {
		current.setLocation(getMouseX(), getMouseY() + myMap.getFrameHeight());
		current.moveX(-current.getWidth() / 2);
		current.moveY(-current.getHeight() / 2);
	}

	// asks user if he is happy with his location
	private int yesOrNo(String type) {
		String[] options = { "yes", "no" };
		int option = JOptionPane.showOptionDialog(new JFrame(),
				"Would you like to place the " + type + " here?",
				"Level Editor", JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

		return option;
	}

	// returns the Sprite that is clicked on
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

	// makes a list of GameObjectData after the user presses "control" and "s"
	private List<GameObjectData> makeGODList(Sprite[] sprites) {
		ArrayList<GameObjectData> temp = new ArrayList<GameObjectData>();
		ArrayList<Sprites.Factory> factory = new ArrayList<Sprites.Factory>();
		factory.add(new EnemySprite.Factory());
		factory.add(new BarrierSprite.Factory());
		factory.add(new PowerUpSprite.Factory());
		factory.add(new PlayerSprite.Factory());
		for (Sprite elem : sprites) {
			if (counter < totalSprites) {
				for (Sprites.Factory check : factory) {

					if (check.isType(elem.getID())
							&& elem.getY() < backHeight - 100) {

						GameObjectData god = new GameObjectData(check.getType());
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
}
