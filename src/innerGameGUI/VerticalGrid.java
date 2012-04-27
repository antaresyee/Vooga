package innerGameGUI;
/**
 * Subclass of Grid.java
 * 
 * @author Kaitlyn
 *
 */
public class VerticalGrid extends Grid {
	public VerticalGrid(){
		super(Grid.VERTICAL);
	}

	public void setGridSize(int n){
		super.setGridSize(n);
		cellWidth = getWidth();
		cellHeight = (double)getHeight()/getSize();
	}
	
	@Override
	public int[] getGridXYCoordinate(int index) {
		int[] xy = {(int) (cellWidth/2), (int) (cellHeight*(index+0.5)), (int) cellWidth, (int) cellHeight};
		return xy;
	}
}
