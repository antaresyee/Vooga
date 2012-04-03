package gameObjects;
import java.awt.image.BufferedImage;


public class Player extends GameObject {

    public Player(double x, double y, String path){
        myX = x;
        myY = y;
        myImgPath = path;
        myName = "Player";
        setLocation(myX, myY);
    }
    
    public String getImgPath()
    {
    	return myImgPath;
    }

    public static class PlayerFactory extends GameObjectFactory{

        public PlayerFactory(double x, double y, String path){
        	super.myName = "Player";
			super.path = path;
            myX = x;
            myY = y;
        }
        
        public PlayerFactory(){myName="Player";}

        @Override
        public GameObject makeObject() {
            return new Player(myX, myY, path);
        }

    }
}