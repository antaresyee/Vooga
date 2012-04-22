package levelEditor;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

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
	public static int enemyCount=1;
	public EnemySprite(String pngPath, int x, int y) {
		super(pngPath,x,y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String askQuestions(Question q, Game g) {
		// TODO Auto-generated method stub
		String str =q.enemyQuestion(g);
		if (!str.equals("Path")) q.writeEnemy(q.getFileData());
		enemyCount++;

		
		return str;
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
