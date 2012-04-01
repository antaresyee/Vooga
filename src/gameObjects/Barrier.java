package gameObjects;
import java.awt.image.BufferedImage;



public class Barrier extends GameObject {

    public Barrier(double x, double y, BufferedImage image){
        makeObj(x,y,image);
    }

    public static class BarrierFactory extends GameObjectFactory{

        public BarrierFactory(double x, double y, BufferedImage image){
            super.myName = "barrier";
            myX = x;
            myY = y;
            myImage = image;
        }

        @Override
        public GameObject makeObject() {
            return new Barrier(myX, myY, myImage);
        }

        @Override
        public boolean isMyObject(String name) {
            return myName.equals(name);
        }

    }

	@Override
	public void makeObj(double x, double y, BufferedImage image) {
		// TODO Auto-generated method stub
		myX = x;
        myY = y;
        myImage = image;
	}

}