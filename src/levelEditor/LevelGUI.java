package levelEditor;

import gameObjects.GameObject;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import levelLoadSave.LevelSaver;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.background.TileBackground;

public class LevelGUI extends Game{
	
	private final String BLACK = "resource/Black.png";
	private List<GameObject> myObjects;
	private TileBackground tilesBack;
	private BufferedImage[] tileImages;
	private String[] imageNames;
	private int[][] tiles;
	private int black;
	private int row;
	private int col;
	
	
	public LevelGUI(BufferedImage[] tileImages, String[] imageNames, int row, int col)
	{
		myObjects = new ArrayList<GameObject>();
		this.row=row;
		this.col=col;
		this.imageNames = imageNames;
		black = tileImages.length+1;
		tiles = new int[row][col];
		for(int i=0; i<row;i++)
			for(int j=0; j<col;j++)
				tiles[i][j]=black;
		this.tileImages = new BufferedImage[black];
		for(int i =0; i < black -1; i++)
		{
			this.tileImages[i] = tileImages[i];
		}
		this.tileImages[black-1]=getImage(BLACK);
		tilesBack = new TileBackground(tileImages,tiles);
	}


	@Override
	public void initResources() {
		// TODO Auto-generated method stub
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
			int r = getMouseY()/row;
			int c = getMouseX()/col;
			int option = JOptionPane.showOptionDialog(new JFrame(), "Choose object to place", "Options", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE, null, imageNames, imageNames[0]);
			tiles[r][c]=option;
			tilesBack.setTiles(tiles);
		}
		
		if(keyPressed(KeyEvent.VK_CONTROL) && keyPressed(KeyEvent.VK_S))
		{
			for(int i = 0; i<row; i++)
			{
				for(int j = 0; j<col; j++)
				{
					GameObject go;
					try {
						int tile = tiles[i][j];
						if(tile<black)
						{
							go = (GameObject) Class.forName(imageNames[tile]).newInstance();
							go.makeObj(j*getWidth()/col,i*getHeight()/row,tileImages[tile]);
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
				LevelSaver.save(myObjects);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
