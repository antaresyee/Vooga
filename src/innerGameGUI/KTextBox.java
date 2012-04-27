package innerGameGUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.font.SystemFont;
/**
 * Designed as flexible input textbox able to be combined with all other components
 * 
 * @author Kaitlyn
 *
 */
public class KTextBox extends KComponent {
	private String myInfo, myMsg;
	private SystemFont myFont;
	private boolean displayTextOn, enabled;
	private Game myGame;
	
	public KTextBox(KComponent parent, Game game, String info){
		super(parent, game);
		myInfo = info;
		myMsg = "";
		myGame = game;
		displayTextOn = true;
		myFont = new SystemFont(new Font("Comic Sans MS", Font.PLAIN, 18),
				Color.BLUE);
		this.setWidthHeight(myFont.getWidth(info), myFont.getHeight());
	}
	
	public KTextBox(KComponent parent, Game game){
		this(parent, game, "");
		displayTextOn = false;
	}
	
	/*
	 * 1st type of dialogue input
	 */
	public void popDialogueBox(String msg)
	{
		myMsg = msg;
		String backup = "";
		if (myInfo != null && myInfo.length()>0) backup = myInfo;
		myInfo = JOptionPane.showInputDialog(msg, "");
		if (myInfo == null) myInfo = backup;
		if (myInfo.length() > 0) displayTextOn = true;
	}
	
	/*
	 * TODO 2nd type of dialogue input
	 */
	public void nonPopBox(){
		
	}
	
	
	/*
	 * TODO If want to put in a different location, call KTextBox.update and pass in new location as x and y
	 * But alternative: Create a grid class, then can just choose grids.
	 */
	public void update(long elapsedTime, double x, double y){
		super.update(elapsedTime);
//		if(myGame.keyPressed(KeyEvent.VK_ENTER)) enabled= true;
		if(myGame.click()) enabled=true;
		if(enabled){
			popDialogueBox(myMsg);
			enabled = false;
		}
		setLocation(x, y);
	}
	
	
	public void render(Graphics2D pen){
		super.render(pen);
		if(displayTextOn && myInfo != null) myFont.drawString(pen, myInfo, (int)getX(), (int)getY());
	}
	
	/*
	 * example of using: print to console
	 */
	public void print()
	{
		System.out.println(myInfo);
	}
	
	/*
	 * test:
	 */
	public static void main(String[] arg)
	{
//		KTextBox ktb = new KTextBox("Your name:");
//		ktb.popDialogueBox();
//		ktb.print();
	}
	
	/*
	 * TODO access the string
	 */
	public String getInfo(){
		return myInfo;
	}
}
