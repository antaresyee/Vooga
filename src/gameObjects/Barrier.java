package gameObjects;



public class Barrier extends GameObject {
	
    public Barrier(double x, double y, String imgPath){
        myX = x;
        myY = y;
        myImgPath = imgPath;
        myName = "Barrier";
        setLocation(myX,myY);
    }
    
    public String getImgPath()
    {
    	return myImgPath;
    }
    

    public static class BarrierFactory extends GameObjectFactory{
    	
    	public BarrierFactory(double x, double y, String imgPath){
    	    myName = "Barrier";
    	    myX = x;
            myY = y;
            super.myImgPath = imgPath;
    	}

        @Override
        public GameObject makeObject() {
            return new Barrier(myX, myY, myImgPath);
        }


    }


    @Override
    public GameObject makeGameObject(GameObjectData god) {
        Double x = god.getX();
        Double y = god.getY();
        String imgPath = god.getImgPath();
        return new Barrier(x, y, imgPath);
    }
}