/*
 * author: Yumeng Fang and Hao Sun
 * Level Editor Class, developer should extend this class and override initResources() method to include their own images, and names
 * And once in the GUI, users click on grid to choose what to put down. And once done with all configurations, developer can simply press ctrl+s to save and quit.
 * Then, developer can head to their own game class and load the saved data from saved json file.
 */


package bossEditor;


import gameObjects.Barrier;
import gameObjects.GameObject;
import gameObjects.GameObjectFactory;
import gameObjects.Player;

import java.awt.Container;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import levelLoadSave.LevelSaver;

import com.golden.gamedev.Game;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.background.TileBackground;
import com.golden.gamedev.util.ImageUtil;

public abstract class ObjectEditor extends Game{
	
//	private final String BLACK = "resource/Black.png";
	private List<GameObjectFactory> myFactories;
	private TileBackground tilesBack;
	private BufferedImage[] tileImages;
	private String[] imageNames;
	private int[][] tiles;
	private int black;
	private int row;
	private int col;
	private List<GameObjectFactory> myObjects;
	private Map<BufferedImage,String> myImagePathMap;
	
//	public LevelGUI(){super();}
	
	public void setInfo(BufferedImage[] tileImages, BufferedImage blackImage, String[] imageNames, int row, int col)
	{
//		myFactories = new ArrayList<GameObjectFactory>();
//		myFactories.add(new Barrier.BarrierFactory());
//		myFactories.add(new Player.PlayerFactory());
//		myObjects = new ArrayList<GameObjectFactory>();
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
		this.tileImages[black]=ImageUtil.resize(blackImage,getWidth()/col, getHeight()/row);
		tilesBack = new TileBackground(this.tileImages,tiles);
	}
	
//	public LevelGUI(BufferedImage[] tileImages, String[] imageNames, int row, int col)
//	{
//		setInfo(tileImages, imageNames, row, col);
//	}


//	@Override
//	public void initResources() {
//		// TODO Auto-generated method stub
//		myImagePathMap = new HashMap<BufferedImage, String>();
//		int width = getWidth();
//		int height = getHeight();
//		int row = 6;
//		int col = 8;
//		int imageWidth = width/col;
//		int imageHeight = height/row;
//		BufferedImage[] bimg = new BufferedImage[2];
//		String path1 = "./resources/ship.png";
//		String path2 = "./resources/enemy.png";
//		bimg[0]=ImageUtil.resize(getImage(path1),imageWidth, imageHeight);
//		bimg[1]=ImageUtil.resize(getImage(path2),imageWidth, imageHeight);
//		myImagePathMap.put(bimg[0],path1);
//		myImagePathMap.put(bimg[1], path2);
//		String[] names = new String[2];
//		names[0]="Player";
//		names[1]="Barrier";
//		setInfo(bimg, getImage("resources/Black.png"),names, row, col);	
//	}


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
			clickEvent();
//			int r = getMouseY()/(getHeight()/row);
//			int c = getMouseX()/(getWidth()/col);
//			int option = JOptionPane.showOptionDialog(new JFrame(), "Place Object on Grid "+r+","+c, "Options", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE, null, imageNames, imageNames[0]);
////			System.out.println(r + "," + c);
//			tiles[c][r]=option;
//			tilesBack.setTiles(tiles);
		}
		
		if(keyDown(KeyEvent.VK_CONTROL) && keyPressed(KeyEvent.VK_S))
		{
			saveEvent();
//			for(int i = 0; i<col; i++)
//			{
//				for(int j = 0; j<row; j++)
//				{
//					GameObject go;
//						int tile = tiles[i][j];
//						if(tile<black)
//						{
//							
////							Class c = Class.forName("gameObjects."+imageNames[tile]);
////							Class f = c.getEnclosingClass();
////							go = (GameObjectFactory) f.newInstance();
////							go.setFactory(i*getWidth()/col,j*getHeight()/row,tileImages[tile]);
////							try {
////								go = (GameObject) Class.forName("gameObjects."+imageNames[tile]).newInstance();
////								go.makeObj(i*getWidth()/col,j*getHeight()/row,myImagePathMap.get(tileImages[tile]),null);
////								myObjects.add(go);
////							} catch (InstantiationException e) {
////								// TODO Auto-generated catch block
////								e.printStackTrace();
////							} catch (IllegalAccessException e) {
////								// TODO Auto-generated catch block
////								e.printStackTrace();
////							} catch (ClassNotFoundException e) {
////								// TODO Auto-generated catch block
////								e.printStackTrace();
////							}
////						}
////							System.out.println(i+","+j);
//							String name = imageNames[tile];
//							for(GameObjectFactory f:myFactories)
//							{
//								if(f.isMyObject(name)){
//									f.setFactory(i*getWidth()/col,j*getHeight()/row,myImagePathMap.get(tileImages[tile]));
//									myObjects.add(f);
//									myFactories = new ArrayList<GameObjectFactory>();
//									myFactories.add(new Barrier.BarrierFactory());
//									myFactories.add(new Player.PlayerFactory());
//								}
//							}
//						}
//				}
//			}
//			try {
//				LevelSaver.save(myObjects);
//				finish();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
		tilesBack.update(elapsedTime);
	}
	
	public abstract void clickEvent();
	public abstract void saveEvent();
	
//	public static void main(String[] args) throws MalformedURLException, URISyntaxException
//	{
//		GameLoader game = new GameLoader();
//		game.setup(new ObjectEditor(), new Dimension(800, 600), false);
//		game.start();
//	}
}
