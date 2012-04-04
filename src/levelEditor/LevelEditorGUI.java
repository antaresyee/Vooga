package levelEditor;

import gameObjects.GameObjectData;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.golden.gamedev.Game;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;

public class LevelEditorGUI extends Game {
	// private SpriteGroup ENEMIES;
	private List<Sprite> list;
	private SpriteGroup ALL;
	private Sprite current = null;
	private Sprite enemy;
	private Sprite barrier;
	private Sprite powerup;
	private Sprite player;
	private int count = 0;
	private int time = 0;
	private int playerX = 30;
	private int barrierX = 130;
	private int enemyX = 230;
	private int powerupX = 330;

	@Override
	public void initResources() {
		// TODO Auto-generated method stub
		enemy = new Sprite(getImage("resources/enemy.png"), enemyX,
				700 - getImage("resources/enemy.png").getHeight() - 40);
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
		player.setLocation(playerX, 700 - getImage("resources/ship.png")
				.getHeight() - 40);
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
		ALL.render(pen);
		// ENEMIES.render(pen);
		// barrier.render(pen);
		// powerup.render(pen);
		// player.render(pen);
		pen.setColor(Color.BLACK);
		pen.draw(new Line2D.Double(0.0, 600.0, 400.0, 600.0));
	}

	@Override
	public void update(long elapsedTime) {
		// TODO Auto-generated method stub
		ALL.update(elapsedTime);

		if (clicked() != null && current == null) {
			current = clicked();
			time = count;
		}
		if (current != null) {
			current.setLocation(getMouseX(), getMouseY());
			current.moveX(-current.getWidth() / 2);
			current.moveY(-current.getHeight() / 2);
			if (click() && time != count) {
				String input = yesOrNo();
				
				ArrayList<Sprites.Factory> factory = new ArrayList<Sprites.Factory>();
				factory.add(new EnemySprite.Factory());
				factory.add(new BarrierSprite.Factory());
				factory.add(new PowerUpSprite.Factory());

				for (Sprites.Factory check : factory) {
					if (check.isType(current.getID())) {
						Sprites newSpr = check.makeSprite();

						if (input.equals("Yes")) {
							Sprite new1 = new Sprite(
									getImage(newSpr.getPath()),
									newSpr.getStartX(), newSpr.getStartY());
							new1.setID(newSpr.newID());
							ALL.add(new1);
							list.add(new1);
						} else {
							current.setLocation(newSpr.getStartX(),
									newSpr.getStartY());
						}
					}
				}

				current = null;

			}
		}
		count++;

	}

	private String yesOrNo() {
		Object[] options = { "Yes", "No" };

		String input = (String) JOptionPane.showInputDialog(
				new JFrame(),
				"Would you like to place the Game Object here?",
				"Level Editor'", JOptionPane.PLAIN_MESSAGE, null,
				options, options[0]);
		return input;
	}
//	private GameObjectData makeGameObject(Sprite spr){
//		GameObjectData temp = new GameObjectData("hey");
//		temp.setX(spr.getX());
//		temp.setY(spr.getY());
//		temp.setImgPath("resources/duke.png");
//
//	}
	private Sprite clicked() {
		Sprite temp = null;

		for (Sprite elem : list) {
			if (click() && (checkPosMouse(elem, true))) {
				temp = elem;
				break;
			}
		}
		return temp;
	}

	public static void main(String[] args) throws MalformedURLException,
			URISyntaxException {
		GameLoader game = new GameLoader();
		game.setup(new LevelEditorGUI(), new Dimension(400, 700), false);
		game.start();
	}
}
