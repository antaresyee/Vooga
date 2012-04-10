package levelEditor;

import gameObjects.GameObjectData;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import levelLoadSave.LevelSaver;
import maps.Map;

import com.golden.gamedev.Game;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;


/**
 * 
 * @author Leo Rofe
 *
 */
public class LevelEditorGUI extends Game {
	// private SpriteGroup ENEMIES;
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
	
	private String lineImgPath ="resources/line.png";
	//initializes the Map to scroll in the background
	private Map myMap; 
	private BufferedImage myBackImage;
	private Background myBackground; 
	
	private int counter = 0;
	private int totalSprites=4;
	public List<GameObjectData> level;


	@Override
	public void initResources() {
		
		// create Map
		myBackImage = getImage("resources/Back2.png"); 
	    myMap = new Map(myBackImage, 400, 700); 
	    myBackground = myMap.getMyBack(); 
	
	    // create enemy sprite	
		enemy = new Sprite(getImage(enemyImgPath), enemyX,
				3000 - getImage(enemyImgPath).getHeight() - 40);
		enemy.setBackground(myBackground);
		enemy.setID(1);
		// create barrier sprite
		barrier = new Sprite(getImage(barrierImgPath));
		barrier.setBackground(myBackground);
		barrier.setLocation(barrierX, 3000 - getImage(barrierImgPath)
				.getHeight() - 40);
		barrier.setID(1001);
		

		// create powerup sprite
		powerup = new Sprite(getImage(powerupImgPath));
		powerup.setBackground(myBackground);
		powerup.setLocation(powerupX, 3000 - getImage(powerupImgPath)
				.getHeight() - 40);
		powerup.setID(2001);
		

		// create player sprite
		player = new Sprite(getImage(playerImgPath));
		player.setBackground(myBackground);
		player.setLocation(playerX, 3000 - getImage(playerImgPath)
				.getHeight() - 40);
		player.setID(3001);
		
		line = new Sprite(getImage(lineImgPath));
		line.setBackground(myBackground);
		line.setLocation(0, 2900);
		
		ALL = new SpriteGroup("All");
		ALL.add(enemy);
		ALL.add(barrier);
		ALL.add(player);
		ALL.add(powerup);
		ALL.add(line);
		ALL.setBackground(myBackground);
		
		list = new ArrayList<Sprite>();
		list.add(enemy);
		list.add(barrier);
		list.add(player);
		list.add(powerup);

	}

	@Override
	public void render(Graphics2D pen) {
		// TODO Auto-generated method stub
		
		myBackground.render(pen); 
		ALL.render(pen);
		pen.setColor(Color.BLACK);
		pen.draw(new Line2D.Double(0.0, 2900.0, 400.0, 2900.0));
	}

	@Override
	public void update(long elapsedTime) {
		//Press Space to move to the bottom of screen
		
		if (keyDown(java.awt.event.KeyEvent.VK_SPACE)) {
			myMap.moveToBottom();
		}
		
		if (keyDown(java.awt.event.KeyEvent.VK_T)) {
			myMap.guiMoveUp();
		}
		
		if (keyDown(java.awt.event.KeyEvent.VK_G)) {
			myMap.guiMoveDown();
		}
		// TODO Auto-generated method stub
		
		myBackground.update(elapsedTime); 
		ALL.update(elapsedTime);

		if (clicked() != null && current == null) {
			current = clicked();
			time = count;
		}
		if (current != null) {
			current.setLocation(getMouseX(), getMouseY()+myMap.getFrameHeight()); 
			current.moveX(-current.getWidth() / 2);
			current.moveY(-current.getHeight() / 2);
			if (click() && time != count) {
				
				//create list of Sprite Factories
				ArrayList<Sprites.Factory> factory = new ArrayList<Sprites.Factory>();
				factory.add(new EnemySprite.Factory());
				factory.add(new BarrierSprite.Factory());
				factory.add(new PowerUpSprite.Factory());
				factory.add(new PlayerSprite.Factory());

				for (Sprites.Factory check : factory) {
					if (check.isType(current.getID())) {
						int input = yesOrNo(check.getType());
						
						Sprites newSpr = check.makeSprite();
						if (check.getType().equals("Player")&&input==0) {
							newSpr.askQuestions();
							break;		
						}
						//if user is happy with location make new sprite
						if (input==0) {
							Sprite new1 = new Sprite(
									getImage(newSpr.getPath()));
							new1.setID(newSpr.newID());
							new1.setBackground(myBackground); 
							newSpr.askQuestions();
							new1.setLocation(newSpr.getStartX(), newSpr.getStartY());
							ALL.add(new1);
							totalSprites++;
							list.add(new1);
							
							//set to background
							
							
						} else {
							//else send him back to original location
							current.setLocation(newSpr.getStartX(),
									newSpr.getStartY());
						}
					
					}
				}

				current = null;

			}
		}
		count++;
		
		//when user presses control and s at same time, the game will create a list of GameObject Data
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
			try {
				LevelSaver.save(level, "savedLevel");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finish();
		}

	}

	private int yesOrNo(String type) {
		String[] options = {"yes","no"};
		int option = JOptionPane.showOptionDialog(new JFrame(), "Would you like to place the "+type+ " here?", "Level Editor", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		
//		Object[] options = { "Yes", "No" };
//
//		String input = (String) JOptionPane.showInputDialog(new JFrame(),
//				"Would you like to place the Game Object here?",
//				"Level Editor'", JOptionPane.PLAIN_MESSAGE, null, options,
//				options[0]);
		return option;
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
	
	public List<GameObjectData> getGODList(){
		return level;
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

					if (check.isType(elem.getID()) && elem.getY() < 2900) {

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

	public static void main(String[] args) throws MalformedURLException,
			URISyntaxException, IOException {
		GameLoader game = new GameLoader();
		game.setup(new LevelEditorGUI(), new Dimension(400, 700), false);
		game.start();
	}
}
