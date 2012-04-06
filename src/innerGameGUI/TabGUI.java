/*
 * This is part one of innerGame GUI, tab for all the innerGameGUI including purchasing screen
 * (selecting ships or weapons) or load save current game, as well as Boss editor where each tab
 * represents different stages.
 * 
 * More features to be implemented. For a sample, run TabGUIExample.java.
 * 
 * Author: Kaitlyn F.
 * 
 */


package innerGameGUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.List;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.font.SystemFont;
import com.golden.gamedev.util.ImageUtil;

import gameObjects.GameObject;

public class TabGUI {
	private Game myGame;
	private String myTabname; // the name of each tab
	private List<GameObject> myObjects;
	private SystemFont myFont;
	private int myX, myY, myWindowWidth, myWindowHeight;
	private final static int MY_TAB_WIDTH = 100;
	private final static int MY_TAB_HEIGHT = 30;
	private final static int CURRENT_WIDTH = 300;
	private final static int CURRENT_HEIGHT = 300;
	private final static int OBJ_WIDTH = 60;
	private final static int OBJ_HEIGHT = 60;
	private final static int GAP = 20;
	
	
	private boolean isClicked;
	private GameObject myCurrentObject;

	public TabGUI(Game game, List<GameObject> gos, String name, int x, int y) {
		myGame = game;
		myTabname = name;

		myX = x;
		myY = y;
		myWindowWidth = game.getWidth();
		myWindowHeight = game.getHeight();
		myFont = new SystemFont(new Font("Comic Sans MS", Font.PLAIN, 18),
				Color.ORANGE);
		isClicked = false;

		myObjects = gos;
		if(gos != null)
		{
			myCurrentObject = gos.get(0);
			myCurrentObject.setImage(ImageUtil.resize(myCurrentObject.getImage(), CURRENT_WIDTH, CURRENT_HEIGHT));
			int gox = myGame.getWidth() / 2 - myCurrentObject.getWidth()/2;
			int goy = myGame.getHeight() / 4;
			for (GameObject go : myObjects) {
				if (!go.equals(myCurrentObject))
					go.setImage(ImageUtil.resize(go.getImage(), OBJ_WIDTH, OBJ_HEIGHT));
				go.setLocation(gox, goy);
				gox += go.getWidth() + GAP;
			}
		}
	}

	public void render(Graphics2D pen) {
//		pen.fillRect(0, 0, myWindowWidth, myWindowHeight);
		pen.setColor(Color.MAGENTA);
		pen.fillRoundRect(myX, myY, MY_TAB_WIDTH, MY_TAB_HEIGHT, 10, 10);
		myFont.drawString(pen, myTabname, SystemFont.CENTER, myX, myY,
				MY_TAB_WIDTH);

		if (isClicked) {
			pen.setColor(Color.ORANGE);
			pen.fillRect(0, MY_TAB_HEIGHT, myWindowWidth, myWindowHeight);
			
			for (GameObject go : myObjects) {
				go.render(pen);
			}
		}

	}

	public void update(long elapsedTime) {
		if (myGame.click() && myGame.getMouseX() > myX
				&& myGame.getMouseX() < myX + MY_TAB_WIDTH
				&& myGame.getMouseY() > myY
				&& myGame.getMouseY() < myY + MY_TAB_HEIGHT) {
			isClicked = true;
		}

		if (myGame.keyPressed(KeyEvent.VK_RIGHT)) {
			shiftRight();
		}
		
		if (myGame.keyPressed(KeyEvent.VK_LEFT)) {
			shiftLeft();
		}

	}

	public void shiftRight() {

		if (myObjects.indexOf(myCurrentObject) < myObjects.size() - 1) {
			myObjects.get(0).moveX(- OBJ_WIDTH - GAP);
			
			for (int i = 1; i < myObjects.size(); i++) {
				myObjects.get(i).moveX(-myObjects.get(i-1).getWidth() - GAP);
			}
			myCurrentObject.setImage(ImageUtil.resize(myCurrentObject.getImage(), OBJ_WIDTH, OBJ_HEIGHT));
			myCurrentObject = myObjects.get(myObjects.indexOf(myCurrentObject) + 1);
			myCurrentObject.setImage(ImageUtil.resize(myCurrentObject.getImage(), CURRENT_WIDTH, CURRENT_HEIGHT));
		}

	}
	
	public void shiftLeft() {

		if (myObjects.indexOf(myCurrentObject) > 0) {
			for (GameObject go : myObjects) {
				go.moveX(go.getWidth() + GAP);
			}
			myCurrentObject.setImage(ImageUtil.resize(myCurrentObject.getImage(),OBJ_WIDTH, OBJ_HEIGHT));
			myCurrentObject = myObjects.get(myObjects.indexOf(myCurrentObject) - 1);
			myCurrentObject.setImage(ImageUtil.resize(myCurrentObject.getImage(), CURRENT_WIDTH, CURRENT_HEIGHT));
		}

	}

	/*
	 * Set up the second Tab
	 */
	public int getX() {
		return myX;
	}

	public int getY() {
		return myY;
	}

	public int getWidth() {
		return MY_TAB_WIDTH;
	}

	public int getHeight() {
		return MY_TAB_HEIGHT;
	}
}