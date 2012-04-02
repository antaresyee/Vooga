package levelEditor;


import gameObjects.GameObject;
import gameObjects.GameObjectFactory;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import levelLoadSave.LevelEditorSaver;
import levelLoadSave.LevelSaver;

import com.golden.gamedev.Game;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.background.TileBackground;
import com.golden.gamedev.util.ImageUtil;

public class LevelGUI extends Game{
	
//	private final String BLACK = "resource/Black.png";
	private List<GameObject> myObjects;
	private TileBackground tilesBack;
	private BufferedImage[] tileImages;
	private String[] imageNames;
	private int[][] tiles;
	private int black;
	private int row;
	private int col;
	
//	public LevelGUI(){super();}
	
	public void setInfo(BufferedImage[] tileImages, String[] imageNames, int row, int col)
	{
		myObjects = new ArrayList<GameObject>();
		this.row=row;
		this.col=col;
		this.imageNames = imageNames;
		black = tileImages.length;
		tiles = new int[col][row];
		for(int i=0; i<col;i++)
			for(int j=0; j<row;j++)
				tiles[i][j]=black;
		this.tileImages = new BufferedImage[black+1];
		for(int i =0; i < black; i++)
		{
			this.tileImages[i] = tileImages[i];
		}
//		System.out.println(black);
		this.tileImages[black]=ImageUtil.resize(getImage("resources/Black (2).png"),getWidth()/col, getHeight()/row);
		tilesBack = new TileBackground(this.tileImages,tiles);
	}
	
//	public LevelGUI(BufferedImage[] tileImages, String[] imageNames, int row, int col)
//	{
//		setInfo(tileImages, imageNames, row, col);
//	}


	@Override
	public void initResources() {
		// TODO Auto-generated method stub
		int width = getWidth();
		int height = getHeight();
		int row = 6;
		int col = 8;
		int imageWidth = width/col;
		int imageHeight = height/row;
		BufferedImage[] bimg = new BufferedImage[2];
		bimg[0]=ImageUtil.resize(getImage("./resources/ship.png"),imageWidth, imageHeight);
		bimg[1]=ImageUtil.resize(getImage("./resources/enemy.png"),imageWidth, imageHeight);
		String[] names = new String[2];
		names[0]="Player";
		names[1]="Barrier";
		setInfo(bimg, names, row, col);	
	}


	@Override
	public void render(Graphics2D pen) {
		// TODO Auto-generated method stub
		tilesBack.render(pen);
	}


	@Override
	public void update(long elapsedTime) {
		// TODO Auto-generated method stub
		if(click())
		{
			int r = getMouseY()/(getHeight()/row);
			int c = getMouseX()/(getWidth()/col);
			int option = JOptionPane.showOptionDialog(new JFrame(), "Choose object to place", "Options", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE, null, imageNames, imageNames[0]);
//			System.out.println(r + "," + c);
			tiles[c][r]=option;
			tilesBack.setTiles(tiles);
		}
		
		if(keyDown(KeyEvent.VK_CONTROL) && keyPressed(KeyEvent.VK_S))
		{
			for(int i = 0; i<col; i++)
			{
				for(int j = 0; j<row; j++)
				{
					GameObject go;
					try {
						int tile = tiles[i][j];
						if(tile<black)
						{
//							go = (GameObjectFactory) Class.forName("gameObjects."+imageNames[tile]+"." + imageNames[tile] + "Factory").newInstance();
//							go.setFactory(i*getWidth()/col,j*getHeight()/row,tileImages[tile]);
							go = (GameObject) Class.forName("gameObjects."+imageNames[tile]).newInstance();
							go.makeObj(i*getWidth()/col,j*getHeight()/row,tileImages[tile]);
							myObjects.add(go);
						}
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			try {
				LevelEditorSaver.save(myObjects);
				finish();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		tilesBack.update(elapsedTime);
	}
	
	public static void main(String[] args) throws MalformedURLException, URISyntaxException
	{
		GameLoader game = new GameLoader();
		game.setup(new LevelGUI(), new Dimension(800, 600), false);
		game.start();
	}
}