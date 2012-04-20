package levelEditor;

import java.awt.image.BufferedImage;

import gameObjects.GameObjectData;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;
/**
 * 
 * @author Leo Rofe
 *
 */
public class PowerUpSprite extends Sprites {
	private final String myType = "PowerUp";
	
	public PowerUpSprite(String pngPath, int x, int y) {
		super(pngPath,x,y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void askQuestions(Question q, Game g) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return myType;
	}
	
	public static class Factory extends Sprites.Factory{
        
		private final String imagePath = "resources/powerup.png";
		private final int startX = 330;
		private final int startY = 2929;
		private final String myType = "PowerUp";

		@Override
		public boolean isType(BufferedImage img, Game g) {
			return img.equals(g.getImage(imagePath));
		}

		@Override
		public Sprites makeSprite() {
			// TODO Auto-generated method stub
			return new PowerUpSprite(imagePath, startX, startY);
		}

		@Override
		public String getType() {
			// TODO Auto-generated method stub
			return myType;
		}

		


		
	}


}
