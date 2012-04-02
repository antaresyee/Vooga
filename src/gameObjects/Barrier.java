package gameObjects;
import java.awt.image.BufferedImage;



public class Barrier extends GameObject {

	public Barrier(){}
	
    public Barrier(double x, double y, String path, BufferedImage image){
        makeObj(x,y,path,image);
    }
    
    public String getPath()
    {
    	return pathToImage;
    }
    

    public static class BarrierFactory extends GameObjectFactory{
    	
    	public BarrierFactory(){myName = "Barrier";}
    	
    	public void setFactory(double x, double y, String path, BufferedImage image){
    		super.myName = "Barrier";
            myX = x;
            myY = y;
            super.path = path;
            myImage = image;
    	}
    	
        public BarrierFactory(double x, double y, String path, BufferedImage image){
            setFactory(x,y,path, image);
        }

        @Override
        public GameObject makeObject() {
            return new Barrier(myX, myY, path, myImage);
        }

        @Override
        public boolean isMyObject(String name) {
            return myName.equals(name);
        }

    }

	@Override
	public void makeObj(double x, double y, String path, BufferedImage image) {
		// TODO Auto-generated method stub
		myX = x;
        myY = y;
        myImage = image;
        pathToImage = path;
        myName = "Barrier";
    }

	@Override
	public void setImage(BufferedImage image) {
		// TODO Auto-generated method stub
		myImage = image;
	}

}