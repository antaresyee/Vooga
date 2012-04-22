package maps;
import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.Timer;
import com.golden.gamedev.object.background.ImageBackground;
import com.golden.gamedev.object.background.TileBackground;


/*
 * written by: Gideon Rosenthal
 * 
 * The Maps class makes it easier for the designer to implement 
 * a scrolling background either as a TileBackground
 * or one with a single Image. 
 */

public class Map {

	private Background myBack; 
	private int frameHeight;
	private int frameWidth; 
	private int backWidth; 
	private int backHeight; 
	private Timer timer; 
	private int speed; 
	private int counter;
	private int counterHold; 
	private int frameSpeed = 5; 
	

	public Map(BufferedImage image,int width, int height){
		myBack = new ImageBackground(image);
		counter = image.getHeight() - height; 
		counterHold = counter; 
		init(width, height); 
	}
	
	public Map(BufferedImage image, int numTiles, int width, int height){
		BufferedImage[] backPics = new BufferedImage[1]; 
		backPics[0] = image; 
		myBack = new TileBackground(backPics, 1, numTiles);
		init(width, height); 
	}
	
	public void place(Sprite sprite, int x, int y){
		int alterY = backHeight - y; 
		sprite.setLocation(x, alterY); 
		
	}
	
	public void init(int width, int height){
		frameHeight = height; 
		frameWidth = width; 
		 
		
		backWidth = myBack.getWidth();
		backHeight = myBack.getHeight(); 
		
		myBack.setClip(0,0,frameWidth,frameHeight);
		myBack.move(backWidth - frameWidth, backHeight - frameHeight);
		
		
		speed = 10; 
		timer = new Timer(speed); 
	}

	
	public Background getMyBack(){
		return myBack; 
	}

	public void moveMap(long elapsedTime) {
		// TODO Auto-generated method stub
		if (timer.action(elapsedTime)){
			myBack.move(0, -1); 
			if (counter>0)
				counter--;
		}
		
	}
	
	public void guiMoveUp(){
		myBack.move(0, -frameSpeed); 
		if (counter>0)	counter=counter-frameSpeed;
	}
	
	public void guiMoveDown(){
		myBack.move(0, frameSpeed); 
		if (counter< 2300) counter=counter+frameSpeed;
	}
	
	public void movePlayer(long elapsedTime, Sprite player){
		if(timer.action(elapsedTime))
			if(counter > 0)
				player.move(0, -1); 
	}
	
	public void setSpeed(int speed){
		timer.setDelay(speed);  
	}
	
	
	public int getFrameHeight(){
		return counter; 
	}
	
	public int getWidth(){
		return myBack.getWidth(); 
	}
	
	public void setFrameSpeed(int fS){
		frameSpeed = fS; 
	}
	
	public void moveToBottom(){
		myBack.move(backWidth - frameWidth, backHeight - frameHeight);
		counter = counterHold; 
	}
	
}
