package gameObjects;
import java.awt.image.BufferedImage;


public class Player extends GameObject {

    public Player(double x, double y, String imgPath){
        myX = x;
        myY = y;
        myImgPath = imgPath;
        myName = "Player";
        setLocation(myX, myY);
    }
    
    public String getImgPath()
    {
    	return myImgPath;
    }

    public static class PlayerFactory extends GameObjectFactory{

        public PlayerFactory(double x, double y, String imgPath){
        	super.myName = "Player";
			super.myImgPath = imgPath;
            myX = x;
            myY = y;
        }
        
        public PlayerFactory(){myName="Player";}

        @Override
        public GameObject makeObject() {
            return new Player(myX, myY, myImgPath);
        }

    }
}