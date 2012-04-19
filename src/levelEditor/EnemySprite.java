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
public class EnemySprite extends Sprites {
	private final String myType= "Enemy";
	public EnemySprite(String pngPath, int x, int y) {
		super(pngPath,x,y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void askQuestions(Question q) {
		// TODO Auto-generated method stub
		q.enemyQuestion();
		q.writeEnemy();
	}
	@Override
	public String getType() {
		return myType;
	}
	
	
	public static class Factory extends Sprites.Factory{
        
		private final String imagePath = "resources/enemy.png";
		private final int startX = 230;
		private final int startY = 2929               ;
		private final String myType= "Enemy";

		@Override
		public boolean isType(BufferedImage img, Game g) {
			return img.equals(g.getImage(imagePath));
		}

		@Override
		public Sprites makeSprite() {
			return new EnemySprite(imagePath, startX, startY);
		}

		@Override
		public String getType() {
			// TODO Auto-generated method stub
			return myType;
		}

		
	}
	
	

}
