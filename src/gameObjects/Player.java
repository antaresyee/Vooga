package gameObjects;
import java.awt.image.BufferedImage;


public class Player extends GameObject {

    public Player(double x, double y, String path){
        makeObj(x,y,path);
    }
    
    public Player(){}
    
    public String getPath()
    {
    	return pathToImage;
    }

    public static class PlayerFactory extends GameObjectFactory{

        public PlayerFactory(double x, double y, String path){
            setFactory(x,y,path);
        }
        
        public PlayerFactory(){myName="Player";}

        @Override
        public GameObject makeObject() {
            return new Player(myX, myY, path);
        }

        @Override
        public boolean isMyObject(String name) {
            return myName.equals(name);
        }

		@Override
		public void setFactory(double x, double y, String path) {
			// TODO Auto-generated method stub
			super.myName = "Player";
			super.path = path;
            myX = x;
            myY = y;
		}

    }


	@Override
	public void makeObj(double x, double y, String path) {
		// TODO Auto-generated method stub
		myX = x;
        myY = y;
        pathToImage = path;
        myName = "Player";
        setLocation(myX, myY);
//        super.setImage()
	}

//	@Override
//	public void setImage(BufferedImage image) {
//		// TODO Auto-generated method stub
//		myImage = image;
//	}

}