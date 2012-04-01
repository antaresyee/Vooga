package gameObjects;
import java.awt.image.BufferedImage;


public class Player extends GameObject {

    public Player(double x, double y, BufferedImage image){
        myX = x;
        myY = y;
        myImage = image;
    }


    public static class PlayerFactory extends GameObjectFactory{

        public PlayerFactory(double x, double y, BufferedImage image){
            super.myName = "player";
            myX = x;
            myY = y;
            myImage = image;
        }

        @Override
        public GameObject makeObject() {
            return new Player(myX, myY, myImage);
        }

        @Override
        public boolean isMyObject(String name) {
            return myName.equals(name);
        }

    }

}