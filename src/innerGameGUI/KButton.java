package innerGameGUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.font.SystemFont;
/**
 * A subclass of KComponent
 * A button that can be clicked on.
 * 
 * @author Kaitlyn
 *
 */
public class KButton extends KComponent{
	private String myText;
	private final static int MY_TAB_WIDTH = 100;
	private final static int MY_TAB_HEIGHT = 30;
	private SystemFont myFont;
	private ActionListener myListener;
	
	public KButton(KComponent parent, Game game, String text, Color color) {
		super(parent, game);
		myText=text;
		setColor(color);
		myFont = new SystemFont(new Font("Comic Sans MS", Font.PLAIN, 18),
				Color.ORANGE);
		setWidthHeight(MY_TAB_WIDTH, MY_TAB_HEIGHT);
		
	}
	
	public KButton(KComponent parent, Game game, String text)
	{
		this(parent, game, text, parent != null? parent.getColor():Color.MAGENTA);
	}
	
	/*
	 * Define action to be listened by the developer. 
	 * If want to change button's behavior, no need to go to the original framework
	 */
	public void addActionListener(ActionListener al){
		myListener = al;
	}
	
	public void render(Graphics2D pen) {
//		pen.fillRect(0, 0, myWindowWidth, myWindowHeight);
		pen.setColor(myColor);
		pen.fillRoundRect((int)getX(), (int)getY(), MY_TAB_WIDTH, MY_TAB_HEIGHT, 10, 10);
		myFont.drawString(pen, myText, SystemFont.CENTER, (int)getX(), (int)getY(),
				MY_TAB_WIDTH);
	}
	
	public void update(long elapsedTime){
		if(myGame.click() && myGame.getMouseX() > getX()
				&& myGame.getMouseX() < getX() + MY_TAB_WIDTH
				&& myGame.getMouseY() > getY()
				&& myGame.getMouseY() < getY() + MY_TAB_HEIGHT)
				{
					clickEvent();
				}
	}
	
	/*
	 * On click of the button, do something.
	 * TODO can generalize this action
	 */
	public void clickEvent(){
		if(myListener != null) myListener.actionPerformed(null);
	}
	
	
}
