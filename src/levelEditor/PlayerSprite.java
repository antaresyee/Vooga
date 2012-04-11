package levelEditor;

import java.awt.Dimension;

import innerGameGUI.TabGUIExample;
import gameObjects.GameObjectData;

import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.Sprite;

/**
 * 
 * @author Leo Rofe
 *
 */

public class PlayerSprite extends Sprites {
	int playerCount=3001;
	public PlayerSprite(String pngPath, int x, int y) {
		super(pngPath,x,y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void askQuestions() {
//		TabGUIExample s=new TabGUIExample();
//		GameLoader game = new GameLoader();
//		game.setup(s, new Dimension(800, 600), false);
//		game.start();
		
	}
	@Override
	public int newID() {
		playerCount++;
		return playerCount;
	}
	
	public static class Factory extends Sprites.Factory{
        
		private final String imagePath = "resources/ship.png";
		private final int startX = 30;
		private final int startY = 2929;	
		private final String myType = "Player";
		
		@Override
		public boolean isType(int id) {
			return id>3000;
		}

		@Override
		public Sprites makeSprite() {			
			return new PlayerSprite(imagePath, startX, startY);			
		}	

		public GameObjectData makeGameObject(Sprite spr) {
			GameObjectData god = new GameObjectData(myType);
			god.setX(spr.getX());
			god.setY(spr.getY());
			god.setImgPath(imagePath);
			return god;
		}

		@Override
		public String getType() {
			return myType;
		}


		
	}


}

