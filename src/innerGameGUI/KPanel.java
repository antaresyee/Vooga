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
	private static final int SPACING = 10;
	public final static int VERTICAL = 1;
	public final static int HORIZONTAL = 0;
	private int ordering;
	private List<KComponent> myComponents;
	private Grid myGrid;
	
	public KPanel(KComponent parent, Game game, Color color, Grid grid, int order, int x, int y, int width, int height){
		super(parent, game);
		ordering = order;
		setLocation(x,y);
		setColor(color);
		setGrid(grid);
		setWidthHeight(width,height);
		myComponents = new ArrayList<KComponent>();
	}
	
	public KPanel (KComponent parent, Game game){
		this(parent, game, Color.BLACK);
	}
	
	public KPanel (KComponent parent, Game game, Color color)
	{
		this(parent, game, color, 0, 0, game.getWidth(), game.getHeight());
	}
	
	public KPanel (KComponent parent, Game game, Color color, int x, int y, int width, int height){
		this(parent, game, color, null, 0, x, y, width, height);
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
	
	public void setGrid(Grid grid){
		myGrid = grid;
		if(myGrid != null) myGrid.setComponent(this);
	}
	
	public void setOrdering(int o){
		ordering = o;
	}
	
	public void updatePanel(KComponent kc){
		if(myGrid == null){
			if(ordering == VERTICAL) defaultVerticalUpdatePanel(kc);
			else if(ordering == HORIZONTAL) defaultHorizontalUpdatePanel(kc);
			return;
		}
		myGrid.setGridSize(myComponents.size());
		for(int i = 0; i < myComponents.size(); i++){
			int[] xy = myGrid.getGridXYCoordinate(i);
			KComponent current = myComponents.get(i);
//			System.out.println(getX() +","+getY()+","+xy[0]+","+xy[1]+","+current.getWidth()/2. + ","+current.getHeight()/2.);
			current.setLocation(getX()+xy[0]-current.getWidth()/2., getY()+xy[1]-current.getHeight()/2.);
		}
	}
	
	public void defaultVerticalUpdatePanel(KComponent kc)
	{
		int spacing = SPACING;
		double x = getX()+spacing;
		double y = getY()+spacing;
		if(myComponents.size() > 1){
			KComponent lastComp = myComponents.get(myComponents.size()-2);
			x = lastComp.getX();
			y = lastComp.getY() + lastComp.getHeight();
		}
		y+=spacing;
		if(myParent == null && (y+kc.getHeight())> myGame.getHeight()){
			x = getX() + getWidth() + spacing;
			y = getY()+spacing;
			setWidthHeight(getWidth()+spacing+kc.getWidth(), Math.max(getHeight(), kc.getHeight()));
		}else{
			setWidthHeight(Math.max(getWidth(), kc.getWidth()), getHeight()+spacing+kc.getHeight());
		}
		kc.setLocation(x, y);
	}
	
	public void defaultHorizontalUpdatePanel(KComponent kc){
		int spacing = SPACING;
		double x = getX()+spacing;
		double y = getY()+spacing;
		if(myComponents.size() > 1){
			KComponent lastComp = myComponents.get(myComponents.size()-2);
			x = lastComp.getX() + lastComp.getWidth();
			y = lastComp.getY();
		}
		x+=spacing;
//		System.out.println(x+","+y);
		if(myParent == null && (x+kc.getWidth())> myGame.getWidth()){
			x = getX()+spacing;
			y = getY() + getHeight() + spacing;
			setWidthHeight(Math.max(getWidth(), kc.getWidth()), getHeight()+spacing+kc.getHeight());
		}else{
			setWidthHeight(getWidth()+spacing+kc.getWidth(), Math.max(getHeight(), kc.getHeight()));
		}
//		System.out.println(x+","+y);
		kc.setLocation(x, y);
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
