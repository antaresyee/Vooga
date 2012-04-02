package gameObjects;
import java.awt.image.BufferedImage;



public class Barrier extends GameObject {

	public Barrier(){}
	
    public Barrier(double x, double y, String path){
        makeObj(x,y,path);
    }
    
    public String getPath()
    {
    	return pathToImage;
    }
    

    public static class BarrierFactory extends GameObjectFactory{
    	
    	public BarrierFactory(){myName = "Barrier";}
    	
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

	@Override
	public void makeObj(double x, double y, String path) {
		// TODO Auto-generated method stub
		myX = x;
        myY = y;
        pathToImage = path;
        myName = "Barrier";
        setLocation(myX,myY);
    }

//	@Override
//	public void setImage(BufferedImage image) {
//		// TODO Auto-generated method stub
//		myImage = image;
//	}

}