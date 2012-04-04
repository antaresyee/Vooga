package gameObjects;

import movement.Movement;

public class Enemy extends GameObject {
	
	private Movement myMovementType;
	
	public Enemy(double x, double y, String imgPath, Movement m){
		myX = x;
        myY = y;
        myImgPath = imgPath;
        myMovementType = m;
        myType = "Enemy";
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


	@Override
    public GameObject makeGameObject(GameObjectData god) {
        Double x = god.getX();
        Double y = god.getY();
        String imgPath = god.getImgPath();
        Movement movement = god.getMovement();
        return new Enemy(x, y, imgPath, movement);
    }
    
    /**
     * Enemy() and getFactory() must be implemented by each game object; 
     * they are used for the factory system.
     */
    private Enemy() {
        myType = "Enemy";
    }
    
    public static GameObjectFactory getFactory() {
        return new GameObjectFactory(new Enemy());
    }

}
