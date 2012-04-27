package levelEditor;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

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
	public ArrayList<String> decorations;
	public PlayerSprite(String pngPath, int x, int y) {
		super(pngPath,x,y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String askQuestions(Question q) {
		String str = " ";
		decorations = q.playerMovement();
		return str;
		
	}
	@Override
	public String getType() {
		return myType;
	}
	
	public ArrayList<String> getDecor(){
		return decorations;
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

