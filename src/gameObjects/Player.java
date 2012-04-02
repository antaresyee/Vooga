package gameObjects;
import java.awt.image.BufferedImage;


public class Player extends GameObject {

    public Player(double x, double y, String path, BufferedImage image){
        makeObj(x,y,path, image);
    }
    
    public Player(){}
    
    public String getPath()
    {
    	return pathToImage;
    }

    public static class PlayerFactory extends GameObjectFactory{

        public PlayerFactory(double x, double y, String path, BufferedImage image){
            setFactory(x,y,path, image);
        }
        
        public PlayerFactory(){myName="Player";}

        @Override
        public GameObject makeObject() {
            return new Player(myX, myY, path, myImage);
        }

        @Override
        public boolean isMyObject(String name) {
            return myName.equals(name);
        }

		@Override
		public void setFactory(double x, double y, String path, BufferedImage image) {
			// TODO Auto-generated method stub
			super.myName = "Player";
			super.path = path;
            myX = x;
            myY = y;
            myImage = image;
		}

    }


	@Override
	public void makeObj(double x, double y, String path, BufferedImage image) {
		// TODO Auto-generated method stub
		myX = x;
        myY = y;
        this.pathToImage = path;
        myImage = image;
        myName = "Player";
	}

	@Override
	public void setImage(BufferedImage image) {
		// TODO Auto-generated method stub
		myImage = image;
	}

}