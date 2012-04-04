package levelEditor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;



import com.golden.gamedev.Game;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

public class LevelEditorAttempt extends Game {
//	private SpriteGroup ENEMIES;
	private List<Sprite> list;
	private SpriteGroup ALL;
	private Sprite current=null;
	private Sprite enemy;
	private Sprite barrier;
	private Sprite powerup;
	private Sprite player;
	private int total=5;
	private int count=0;
	private int click=0;
	private int time=0;
	private boolean bool = false;
	private int playerX = 30;
	private int barrierX = 130;
	private int enemyX = 230;
	private int powerupX = 330;

	@Override
	public void initResources() {
		// TODO Auto-generated method stub
		enemy = new Sprite(getImage("resources/enemy.png"),enemyX, 700 - getImage("resources/enemy.png")
				.getHeight() - 40);
		enemy.setID(1);
		
		barrier = new Sprite(getImage("resources/black.png"));
		barrier.setLocation(barrierX, 700 - getImage("resources/black.png")
				.getHeight() - 40);
		barrier.setID(1001);

		powerup = new Sprite(getImage("resources/powerup.png"));
		powerup.setLocation(powerupX, 700 - getImage("resources/powerup.png")
				.getHeight() - 40);
		powerup.setID(2001);
		
		player = new Sprite(getImage("resources/ship.png"));
		player.setLocation(playerX,
				700 - getImage("resources/ship.png").getHeight() - 40);
		System.out.println(700 - getImage("resources/ship.png").getHeight() - 40);
		player.setID(3001);
		
		ALL = new SpriteGroup("All");
		ALL.add(enemy);
		ALL.add(barrier);
		ALL.add(player);
		ALL.add(powerup);
		list = new ArrayList<Sprite>();
		list.add(enemy);
		list.add(barrier);
		list.add(player);
		list.add(powerup);


	}

	@Override
	public void render(Graphics2D pen) {
		// TODO Auto-generated method stub
		pen.setColor(Color.WHITE);
		pen.fillRect(0, 0, getWidth(), getHeight());
//		for (Sprite elem: enemies){
//			elem.render(pen);
//		}
		ALL.render(pen);
//		ENEMIES.render(pen);
//		barrier.render(pen);
//		powerup.render(pen);
//		player.render(pen);
		pen.setColor(Color.BLACK);
		pen.draw(new Line2D.Double(0.0, 600.0, 400.0, 600.0));
	}

	@Override
	public void update(long elapsedTime) {
		// TODO Auto-generated method stub
//		dragAndDrop();
//		for (Sprite elem: enemies){
//			elem.update(elapsedTime);		
//			}
		ALL.update(elapsedTime);
//		ENEMIES.update(elapsedTime);
//		barrier.update(elapsedTime);
//		powerup.update(elapsedTime);
//		player.update(elapsedTime);
		
		
		if (clicked()!=null&&current==null){
			current = clicked();
			time=count;
		}
		if (current!=null) {
			current.setLocation(getMouseX(), getMouseY());
			current.moveX(-current.getWidth() / 2);
			current.moveY(-current.getHeight() / 2);
			if (click()&&time!=count) 
				{
//				Object[] options = { "Yes", "No" };
//				
//								String input = (String) JOptionPane.showInputDialog(new JFrame(),
//										"Would you like to place the Game Object here?", "Level Editor'",
//										JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
//								if (input == "Yes") ;
//								else current.setLocation(230, 700 - getImage("resources/enemy.png")
//										.getHeight() - 40);
								
				ArrayList<Sprites.Factory> factory = new ArrayList<Sprites.Factory>();
				factory.add(new EnemySprite.Factory());
				factory.add(new PlayerSprite.Factory());
				factory.add(new BarrierSprite.Factory());
				factory.add(new PowerUpSprite.Factory());
				
				for (Sprites.Factory check : factory)
				{
					System.out.println(current.getID());
					if(check.isType(current.getID()))
					{
						
						Sprites newSpr = check.makeSprite();
						Sprite new1 = new Sprite(getImage(newSpr.getPath()),newSpr.getStartX(),newSpr.getStartY());
						new1.setID(newSpr.newID());
						System.out.println(newSpr.newID());
						ALL.add(new1);
						list.add(new1);
					}
				}
		
				current=null;
				
				}
		}
		count++;

	}
	private Sprite clicked() {
		Sprite temp = null;

		for (Sprite elem: list)
		{
			if (click()&& (checkPosMouse(elem, true))){
				temp =elem;
				break;
			}
		}
		return temp;
	}

//	private void dragAndDrop() {
//	
//		if (click>0){
//			if (click()) click=-1;
//			enemy.setLocation(getMouseX(), getMouseY());
//			enemy.moveX(-enemy.getWidth() / 2);
//			enemy.moveY(-enemy.getHeight() / 2);
//		
//		}
//		if (click()&& (checkPosMouse(enemy, true)) &&click==0){
//		
//		click++;
//		}
//		if (click==-1) click=0;
//		
//	
//		for (Sprite elem : array) {
//			if (bsInput.isMousePressed(java.awt.event.MouseEvent.BUTTON1)
//					&& checkPosMouse(elem, true)) {
//				// elem.setLocation(getMouseX()-elem.getWidth()/2,
//				// getMouseY()-elem.getHeight()/2);
//				elem.setLocation(getMouseX(), getMouseY());
//				elem.moveX(-elem.getWidth() / 2);
//				elem.moveY(-elem.getHeight() / 2);
//				Object[] options = { "Yes", "No" };
//
//				String input = (String) JOptionPane.showInputDialog(new JFrame(),
//						"Would you like to place the Game Object here?", "Level Editor'",
//						JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
//				if (input == "Yes") ;
//				else elem.setLocation(230, 700 - getImage("resources/enemy.png")
//						.getHeight() - 40);
//				
//			}
//		}
//	}
//
//	private Sprite[] spriteMaker(int total, String imagePath) {
//		Sprite[] spriteArr = new Sprite[total]; 
//		for (int x = 0; x < total; x++) {	
//			Sprite temp = new Sprite(getImage(imagePath));
//			spriteArr[x]=temp;
//		}
//		return spriteArr;
//	}

	public static void main(String[] args) throws MalformedURLException,
			URISyntaxException {
		GameLoader game = new GameLoader();
		game.setup(new LevelEditorAttempt(), new Dimension(400, 700), false);
		game.start();
	}
}
