package innerGameGUI;
/**
 * A decorator class to separate screen into grids
 * by calculating x,y based on the grid number chosen by developer 
 * 
 * TODO change all instances of x, y pixels into grid coordinates (amplify)
 * 
 * @author Kaitlyn
 *
 */
public abstract class Grid {
	public final static int UNIFORM = 0;
	public final static int VERTICAL = 1;
	public final static int HORIZONTAL = 2;
	private int myFormat, mySize, myX, myY, myWidth, myHeight;
	protected double cellWidth, cellHeight;
	private KComponent myComponent;
	
	public Grid(int format){
		myFormat = format;
		setGridSize(0);
//		setLocation(0,0);
//		setWidthHeight(0,0);
	}
	
	public void setComponent(KComponent kc){
		myComponent = kc;
//		setLocation((int)myComponent.getX(),(int)myComponent.getY());
//		setWidthHeight(myComponent.getWidth(), myComponent.getHeight());
	}
	
	public void setGridSize(int n){
		mySize = n;
	}
	
//	public void setLocation(int x, int y){
//		myX = x;
//		myY = y;
//	}
	
//	public void setWidthHeight(int w, int h){
//		myWidth = w;
//		myHeight = h;
//	}
	
	public void incrementGrid(){
		mySize++;
	}
	
	public void decrementGrid(){
		mySize--;
	}
	
	public int getWidth(){
		if(myComponent == null) return 0;
		return myComponent.getWidth();
	}
	
	public int getHeight(){
		if(myComponent == null) return 0;
		return myComponent.getHeight();
	}
	
	public int getSize(){
		return mySize;
	}
	
	public abstract int[] getGridXYCoordinate(int index);
}
