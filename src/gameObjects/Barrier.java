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
    	
    	public BarrierFactory(){
    	    myName = "Barrier";
    	}
    	
    	public void setFactory(double x, double y, String path){
    		super.myName = "Barrier";
            myX = x;
            myY = y;
            super.path = path;
    	}
    	
        public BarrierFactory(double x, double y, String path){
            setFactory(x,y,path);
        }

        @Override
        public GameObject makeObject() {
            return new Barrier(myX, myY, path);
        }

        @Override
        public boolean isMyObject(String name) {
            return myName.equals(name);
        }

    }
}