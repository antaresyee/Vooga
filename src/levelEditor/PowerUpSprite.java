package levelEditor;

import gameObjects.GameObjectData;

import com.golden.gamedev.object.Sprite;
/**
 * 
 * @author Leo Rofe
 *
 */
public class PowerUpSprite extends Sprites {
	int powerUpCount=2001;
	
	public PowerUpSprite(String pngPath, int x, int y) {
		super(pngPath,x,y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void askQuestions() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int newID() {
		powerUpCount++;
		return powerUpCount;
	}
	
	public static class Factory extends Sprites.Factory{
        
		private final String imagePath = "resources/powerup.png";
		private final int startX = 330;
		private final int startY = 2929;
		private final String myType = "PowerUp";

		@Override
		public boolean isType(int id) {
			return 2000<id &&id<3001;
		}

		@Override
		public Sprites makeSprite() {
			// TODO Auto-generated method stub
			return new PowerUpSprite(imagePath, startX, startY);
		}

		@Override
		public GameObjectData makeGameObject(Sprite spr) {
			GameObjectData god = new GameObjectData(myType);
			god.setX(spr.getX());
			god.setY(spr.getY());
			god.setImgPath(imagePath);
			return god;
		}

		@Override
		public String getType() {
			// TODO Auto-generated method stub
			return myType;
		}


		
	}


}
