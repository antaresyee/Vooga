package innerGameGUI;

import java.awt.Graphics2D;

import com.golden.gamedev.Game;

public class InGameGUI {
	protected KPanel myPanel;
	protected Game myGame;
	private String myImgPath;
	
	public void setPanel(KPanel kp){
		myPanel = kp;
	}
	
	public void setGame(Game game){
		myGame = game;
	}

	
	public String getImagePath(){
		return myImgPath;
	}
	
	public void setImagePath(String img){
		myImgPath = img;
	}
	
	public void render(Graphics2D pen){
		myPanel.render(pen);
	}
	public void update(long elapsedTime){
		myPanel.update(elapsedTime);
	}
}
