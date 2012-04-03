package gameObjects;



public class Barrier extends GameObject {
	
    public Barrier(double x, double y, String path){
        myX = x;
        myY = y;
        myImgPath = path;
        myName = "Barrier";
        setLocation(myX,myY);
    }
    
    public String getImgPath()
    {
    	return myImgPath;
    }
    

    public static class BarrierFactory extends GameObjectFactory{
    	
    	public BarrierFactory(double x, double y, String path){
    	    myName = "Barrier";
    	    myX = x;
            myY = y;
            super.path = path;
    	}

        @Override
        public GameObject makeObject() {
            return new Barrier(myX, myY, path);
        }


    }
}