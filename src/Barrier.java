import java.awt.image.BufferedImage;


public class Barrier extends GameObject {
	
	public Barrier(double x, double y, BufferedImage image){
		myX = x;
		myY = y;
		myImage = image;
	}
	
	public class BarrierFactory extends GameObject.GameObjectFactory{
		
		public BarrierFactory(double x, double y, BufferedImage image){
			myX = x;
			myY = y;
			myImage = image;
		}

		@Override
		public GameObject makeObject() {
			return new Barrier(myX, myY, myImage);
		}
		
	}

}
