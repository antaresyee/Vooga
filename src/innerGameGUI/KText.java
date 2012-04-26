package innerGameGUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.font.SystemFont;
/*
 * All functionality similar to KTextBox only that the user cannot edit the texts.
 * TODO not showing text. Leaving it for later.
 */
public class KText extends KComponent{

	private String myText;
	private SystemFont myFont;
	
	public KText(KComponent parent, Game game, String text, Font font, Color color) {
		super(parent, game);
		myText = text;
		myFont = new SystemFont(font, color);
		this.setWidthHeight(myFont.getWidth(text), myFont.getHeight());
		// TODO Auto-generated constructor stub
	}
	
	public KText(KComponent parent, Game game, String text, Font font){
		this(parent, game, text, font, Color.GREEN);
	}
	
	public KText(KComponent parent, Game game, String text){
		this(parent, game, text, new Font("Comic Sans MS", Font.PLAIN, 18));
	}
	
	public void render(Graphics2D pen){
		super.render(pen);
		myFont.drawString(pen, myText, (int)getX(), (int)getY());
	}

}
