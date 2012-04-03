package gameObjects;

import movement.Movement;

public class Enemy extends GameObject {
	
	private Movement myMovementType;
	
	public Enemy(double x, double y, String imgPath, Movement m){
		myX = x;
        myY = y;
        myImgPath = imgPath;
        myMovementType = m;
        myName = "Enemy";
        setLocation(myX,myY);
	}

	@Override
	public String getImgPath() {
		return myImgPath;
	}
	
	public void move(){
		myMovementType.move(this);
	}
	
	public void update(){
		move();
	}
	
	public static class EnemyFactory extends GameObjectFactory{
		
		Movement myMovementType;

		public EnemyFactory(double x, double y, String imgPath, Movement m){
			myName = "Enemy";
    	    myX = x;
            myY = y;
            myMovementType = m;
            super.myImgPath = imgPath;
		}
		
		@Override
		public GameObject makeObject() {
			return new Enemy(myX, myY, myImgPath, myMovementType);
		}


		
	}

	@Override
    public GameObject makeGameObject(GameObjectData god) {
        Double x = god.getX();
        Double y = god.getY();
        String imgPath = god.getImgPath();
        Movement movement = god.getMovement();
        return new Enemy(x, y, imgPath, movement);
    }

}
