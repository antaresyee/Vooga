package innerGameGUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import javax.swing.JOptionPane;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.font.SystemFont;

public class KTextBox extends Sprite {
	private String myInfo, myMsg;
	private SystemFont myFont;
	private boolean displayTextOn;
	
	public KTextBox(String msg){
		myMsg = msg; 
		myInfo = "";
		displayTextOn = false;
		myFont = new SystemFont(new Font("Comic Sans MS", Font.PLAIN, 18),
				Color.BLUE);
	}
	
	public void popDialogueBox()
	{
		myInfo = JOptionPane.showInputDialog(myMsg, "");
	}
	
	public void update(long elapsedTime, double x, double y){
		super.update(elapsedTime);
		setLocation(x, y);
	}
	
	public void render(Graphics2D pen){
		super.render(pen);
		if(displayTextOn) myFont.drawString(pen, myInfo, (int)getX(), (int)getY());
	}
	
	public void print()
	{
		System.out.println(myInfo);
	}
	
	public static void main(String[] arg)
	{
		KTextBox ktb = new KTextBox("Your name:");
		ktb.print();
	}
	
}
