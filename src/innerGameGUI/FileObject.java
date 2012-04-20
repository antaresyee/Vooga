package innerGameGUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import com.golden.gamedev.object.font.SystemFont;

import gameObjects.GameObject;
import gameObjects.GameObjectData;
/**
 * Convert the saved JSON game to a GameObject for display in the TabGUI should the developer choose to
 * 
 * @author Kaitlyn 
 * 
 */
public class FileObject extends GameObject {
	private String myFilePath;
	private SystemFont myFont;
	
	public FileObject(String path)
	{
		myFilePath = path;
		myImgPath = "resources/file.png";
		myFont = new SystemFont(new Font("Comic Sans MS", Font.PLAIN, 18),
				Color.BLUE);
	}
	
	public String getFilePath()
	{
		return myFilePath;
	}
	@Override
	public String getImgPath() {
		// TODO Auto-generated method stub
		return myImgPath;
	}
	
	@Override
	public void render(Graphics2D pen)
	{
		super.render(pen);
		myFont.drawString(pen, myFilePath, SystemFont.CENTER, (int) getX(), (int)(getY()+getHeight()*10/9),
				getWidth());
	}

	@Override
	public GameObject makeGameObject(GameObjectData god) {
		// TODO Auto-generated method stub
		return null;
	}

}
