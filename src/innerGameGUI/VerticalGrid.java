package innerGameGUI;

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
		int[] xy = {0, (int) (cellHeight*index), (int) cellWidth, (int) cellHeight};
		return xy;
	}
}
