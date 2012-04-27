package levelEditor;

import gameObjects.GameObjectData;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import weapons.UnlimitedGun;

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
	private double currentX;
	private double currentY;
	private double[] toRemove=null;
	
	private Sprite player;
	
	private int count = 0;
	private int time = 0;
	
	private int totalSprites = 1;
	private int backHeight;
	
	private int adjust = 40;
	private int playerX = 30;
	
	public List<GameObjectData> level;
	private String playerStr = "Player";
	private String enemyStr = "Enemy";
	private String pathStr = "Path";
	
	private Map myMap;
	private BufferedImage myBackImage;
	private Background myBackground;
	public Question q;
	private ArrayList<Sprites.Factory> factory;
	public HashMap<double[],Sprites> mapData;
	private String askQ=" ";
	public ArrayList<Point> pathCoord;
	public ArrayList<String> finalFile;
	
	
	@Override
	public void initResources() {
		ALL = new SpriteGroup("All");
		list = new ArrayList<Sprite>();
		
		Properties prop = configFile();
		
		factory = new ArrayList<Sprites.Factory>();
		factory.add(new EnemySprite.Factory());
		factory.add(new BarrierSprite.Factory());
		factory.add(new PowerUpSprite.Factory());
		factory.add(new PlayerSprite.Factory());
		for (Sprites.Factory check : factory){
			if (!check.getType().equals(playerStr)) createNewSprite(check.makeSprite(),true);			
		}
		
		
		// create Map
		myBackImage = getImage("resources/BackFinal.png");
		myMap = new Map(myBackImage, getWidth(), getHeight());
		myBackground = myMap.getMyBack();
		backHeight = myBackImage.getHeight();

		// create player sprite
		player = new Sprite(getImage(prop.getProperty("playerImgPath")), playerX, backHeight
				- getImage(prop.getProperty("playerImgPath")).getHeight() - adjust);

		ALL.add(player);
		ALL.setBackground(myBackground);

		list.add(player);
		q= new Question();
		
		mapData= new HashMap<double[],Sprites>();
		pathCoord = new ArrayList<Point>();
		finalFile = new ArrayList<String>();


	}

	private Properties configFile() {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("resources/config.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	@Override
	public void render(Graphics2D pen) {
		// render
		myBackground.render(pen);
		ALL.render(pen);
	}

	@Override
	public void update(long elapsedTime) {
		
		// Press Spacebar to move to the bottom of screen
		if (keyDown(java.awt.event.KeyEvent.VK_SPACE))
			myMap.moveToBottom();

		// press "t" to scroll screen up the background
		// press "g" to scroll screen down the background
		if (keyDown(java.awt.event.KeyEvent.VK_T))
			myMap.guiMoveUp();
		if (keyDown(java.awt.event.KeyEvent.VK_G))
			myMap.guiMoveDown();
		// update
		myBackground.update(elapsedTime);
		
	
	if(askQ.equals(pathStr)){
			 setPath();
		}
	else{
		ALL.update(elapsedTime);
		// if user clicks on a gameobject and no other gameobject is currently being dragged
		// set the clicked on gameobject as current (sticks to the mouse location)
		if (clicked() != null && current == null) {
			current = clicked();
			currentX = current.getX();
			currentY = current.getY();
			time = count;
		}
		
		// set current gameobject to mouse location
		if (current != null) {
			followMouse();
			
			// if user clicks, place gameobject and create the correct new sprite
			if (click() && time != count) {
				askQ=placeAndCreateGameObject();
			}
		}
		
		count++;
		
		// when user presses control and s at same time, the game will create a
		// list of GameObject Data
		if ((keyDown(KeyEvent.VK_CONTROL) && keyPressed(KeyEvent.VK_S))) {
			saveFile();
		}
	}	
	}

	private void setPath() {
		String writeFile = "P,";
		if (click()){
			Point p = new Point();
			p.setLocation(getMouseX(), getMouseY());
			pathCoord.add(p);
			JOptionPane
			.showMessageDialog(new JFrame(),
					"Added coordinate for Enemy Path. Remember press 'D' when done.");
		}
		if (keyDown(java.awt.event.KeyEvent.VK_D)){
			
			
			for (Point pt:pathCoord){
				writeFile = writeFile +pt.getX()+","+(pt.getY()+myMap.getFrameHeight())+",";
			}
			
			pathCoord.clear();
			writeFile= writeFile + " ";
			finalFile=q.getFileData();
			String path=q.addState(writeFile);
			System.out.println(path);
			finalFile.add(path);
			q.writeEnemy(finalFile);
			askQ = " ";
			JOptionPane
			.showMessageDialog(new JFrame(),
					"Finished Enemy Path. Back to LevelEditor.");
//			q.enemyWeapon();
		}
	}

	private String placeAndCreateGameObject() {
		String str = " ";
		for (Sprites.Factory check : factory) {
			if (check.isType(current.getImage(),this)) {
				int input = q.yesOrNo(check.getType());
				Sprites newSpr = check.makeSprite();
				if (input==1) {
					current.setLocation(newSpr.getStartX(),
						newSpr.getStartY());
					break;
				}
				double []loc = new double[3];
				loc = findPointToRemove(newSpr);
				if (toRemove!=null) mapData.remove(toRemove);
				
				mapData.put(loc,newSpr);
				
				if (check.getType().equals(playerStr)) {
					newSpr.askQuestions(q);
					break;
				}
				//create and place new Sprite on background
				str=createNewSprite(newSpr, false);
			}
		}
		
		current = null;
		return str;
	}

	private double[] findPointToRemove(Sprites spr) {
		double[] loc = new double[3];
		loc[0]=current.getX();
		loc[1]=current.getY();
		if (spr.getType().equals(enemyStr)) {
			loc[2]=EnemySprite.enemyCount;
			System.out.println(EnemySprite.enemyCount);
		}
		else loc[2]=0;
		for (double[] arr: mapData.keySet()) {
			if (arr[0]==currentX &&arr[1]==currentY){
				toRemove=arr;
			}
		}
		return loc;
	}

	private String createNewSprite(Sprites newSpr, boolean initial) {
		// if user is happy with location make new sprite
			String str = " ";
			Sprite new1 = new Sprite(getImage(newSpr.getPath()), newSpr.getStartX(), newSpr.getStartY());
			new1.setBackground(myBackground);
			if (!initial) {
				str = newSpr.askQuestions(q);
			}
			ALL.add(new1);
			list.add(new1);
			totalSprites++;	
			return str;
	}

	// saves the level
	private void saveFile() {
		if (player.getY() < backHeight - 100) {
			level = makeGODList();
			LevelSaver.serializeSave(level, "serializeTest");	
			 for (GameObjectData f : level) {
				 System.out.print(f.getImgPath());
				 System.out.print(" ");
				 System.out.print(f.getX());
				 System.out.print(" ");
				 System.out.println(f.getY());
				 }
			 for (Point a: pathCoord){
				 System.out.println(a.getX() +" " +a.getY());
			 }
			finish();
		} else {
			JOptionPane
					.showMessageDialog(new JFrame(),
							"You must place a 'Player' game object before you can save the level!");
		}
		
	}

	// sets current game object to follow the mouse
	private void followMouse() {
		current.setLocation(getMouseX(), getMouseY() + myMap.getFrameHeight());
		current.moveX(-current.getWidth() / 2);
		current.moveY(-current.getHeight() / 2);
	}


	// returns the Sprite that is clicked on
	private Sprite clicked() {
		Sprite temp = null;

		for (Sprite elem : list) {
			if (click() && (checkPosMouse(elem, true)))
			{
				temp = elem;
				break;
			}
		}
		return temp;
	}

	// makes a list of GameObjectData after the user presses "control" and "s"
	private List<GameObjectData> makeGODList() {
		ArrayList<GameObjectData> temp = new ArrayList<GameObjectData>();
		ArrayList<UnlimitedGun> guns = new ArrayList<UnlimitedGun>();
		guns = q.getWeapons();
		for (double[] pt: mapData.keySet()){
			GameObjectData god = new GameObjectData(mapData.get(pt).getType());
			god.setX(pt[0]);
			god.setY(pt[1]);
			god.setImgPath(mapData.get(pt).getPath());			
			if (mapData.get(pt).getType().equals(enemyStr)) {
				int enemyNum = (int) pt[2];
				System.out.println(enemyNum);
				god.setEnemyConfigFile("StateInfo"+enemyNum+".txt");
				god.setWeapon(guns.get(enemyNum-1));
			}
			if (mapData.get(pt).getType().equals(playerStr)){
				PlayerSprite ps = (PlayerSprite)mapData.get(pt);
				System.out.println(ps.getDecor().size());
				god.setDecorations(ps.getDecor());
			}
			temp.add(god);
		}
		return temp;

	}
}
