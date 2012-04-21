package innerGameGUI;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import com.golden.gamedev.Game;
import com.golden.gamedev.util.ImageUtil;
/**
 * Provides the background to add other KComponents
 * This class is implemented with the help of Grid.java, which defines how and where to display each of the KComponents
 * in the KPanel
 * Can add KComponent to the panel or remove KComponent from the panel.
 * 
 * @author Kaitlyn
 *
 */
public class KPanel extends KComponent{
	private List<KComponent> myComponents;
	private Color myColor;
	private Grid myGrid;
	
	public KPanel(Game game, Color color, Grid grid, int x, int y, int width, int height){
		super(game);
		setLocation(x,y);
		setColor(color);
		setGrid(grid);
		setWidthHeight(width,height);
		myComponents = new ArrayList<KComponent>();
	}
	
	public KPanel (Game game){
		this(game, Color.BLACK);
	}
	
	public KPanel (Game game, Color color)
	{
		this(game, color, 0, 0, game.getWidth(), game.getHeight());
	}
	
	public KPanel (Game game, Color color, int x, int y, int width, int height){
		this(game, color, new VerticalGrid(), x, y, width, height);
	}
	
	/*
	 * All position using pixels now replaced by Grid.java for simplicity
	 * TODO still need to provide the flexibility of choosing between Grid and Pixels? Need to be this detailed?
	 */
	public void add(KComponent kc){
		myComponents.add(kc);
		kc.setParent(this);
		updatePanel(kc);
		//TODO: set coordinates
	}
	
	public void remove(KComponent kc){
		if(myComponents.contains(kc)) myComponents.remove(kc);
		updatePanel(kc);
	}
	
	public void setColor(Color color)
	{
		myColor = color;
	}
	
	public void setGrid(Grid grid){
		myGrid = grid;
		myGrid.setComponent(this);
	}
	
	public void updatePanel(KComponent kc){
		myGrid.setGridSize(myComponents.size());
		for(int i = 0; i < myComponents.size(); i++){
			int[] xy = myGrid.getGridXYCoordinate(i);
			myComponents.get(i).setLocation(getX()+xy[0], getY()+xy[1]);
		}
	}
	
	
	@Override
	public void render(Graphics2D pen){
		pen.setColor(myColor);
		pen.fillRect((int)getX(), (int)getY(), getWidth(), getHeight());
		super.render(pen);
		for(KComponent kc : myComponents){
			kc.render(pen);
		}
	}
	
	@Override
	public void setImage(BufferedImage img){
		super.setImage(ImageUtil.resize(img, getWidth(), getHeight()));
	}
	
	@Override
	public void update(long elapsedTime){
		super.update(elapsedTime);
		for(KComponent kc : myComponents){
			kc.update(elapsedTime);
		}
	}
}
