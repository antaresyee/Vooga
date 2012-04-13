package levelEditor;

import gameObjects.GameObjectData;

import com.golden.gamedev.object.Sprite;


/**
 * 
 * @author Leo Rofe
 *
 */
public class EnemySprite extends Sprites {
	 int enemyCount=1;
	 Question q;
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
	public int newID() {
		enemyCount++;
		return enemyCount;
	}
	
	
	public static class Factory extends Sprites.Factory{
        
		private final String imagePath = "resources/enemy.png";
		private final int startX = 230;
		private final int startY = 2929               ;
		private final String myType= "Enemy";

		@Override
		public boolean isType(int id) {
			return id<101;
		}

		@Override
		public Sprites makeSprite() {
			return new EnemySprite(imagePath, startX, startY);
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
