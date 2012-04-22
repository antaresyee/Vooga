package levelEditor;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

import gameObjects.GameObjectData;

import com.golden.gamedev.Game;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.Sprite;

/**
 * 
 * @author Leo Rofe
 *
 */

public class PlayerSprite extends Sprites {
	private final String myType = "Player";
	public PlayerSprite(String pngPath, int x, int y) {
		super(pngPath,x,y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void askQuestions(Question q, Game g) {
		
		
	}
	@Override
	public String getType() {
		return myType;
	}
	
	public static class Factory extends Sprites.Factory{
        
		private final String imagePath = "resources/ship.png";
		private final int startX = 30;
		private final int startY = 2929;
		private final String myType = "Player";

		
		
		@Override
		public boolean isType(BufferedImage img, Game g) {
			return img.equals(g.getImage(imagePath));
		}

		@Override
		public Sprites makeSprite() {			
			return new PlayerSprite(imagePath, startX, startY);			
		}

		@Override
		public String getType() {
			// TODO Auto-generated method stub
			return myType;
		}	
		
	}


}

