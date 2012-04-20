package innerGameGUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.font.SystemFont;

public class KText extends KComponent{

	private String myText;
	private SystemFont myFont;
	
	public KText(Game game, String text, Font font, Color color) {
		super(game);
		myText = text;
		myFont = new SystemFont(font, color);
		// TODO Auto-generated constructor stub
	}
	
	public KText(Game game, String text, Font font){
		this(game, text, font, Color.BLACK);
	}
	
	public KText(Game game, String text){
		this(game, text, new Font("Comic Sans MS", Font.PLAIN, 18));
	}
	
	public void render(Graphics2D pen){
		super.render(pen);
		myFont.drawString(pen, myText, (int)getX(), (int)getY());
	}

}
