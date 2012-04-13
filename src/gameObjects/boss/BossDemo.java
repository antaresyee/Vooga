package gameObjects.boss;

import gameObjects.Player;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.golden.gamedev.Game;
import com.golden.gamedev.GameLoader;
import com.golden.gamedev.object.CollisionManager;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.Timer;
import com.golden.gamedev.object.font.SystemFont;

public class BossDemo extends Game {

	private SpriteGroup bossG, player, MY_FIRE_GROUP;
	private final String FOLDER = "resources/";
	private final String PNG = ".png";
	private final String FIRE = FOLDER + "fire" + PNG;
	private Player myPlane;
	private CollisionManager cm;
	private Boss boss;
	private Timer fireRate;
	private SystemFont sf;

	@Override
	public void initResources() {
		// TODO Auto-generated method stub
		boss = new Boss();
		fireRate = new Timer(100);
		boss.load(this);
		bossG = boss.getSpriteGroup();
		sf = new SystemFont(new Font("Casual", Font.BOLD, 24), Color.CYAN);
		// System.out.println(bossG);
		// System.out.println(boss.getCurrent().getX() +
		// ","+boss.getCurrent().getY()+
		// ","+boss.getCurrent().getWidth()+","+boss.getCurrent().getHeight());
		MY_FIRE_GROUP = new SpriteGroup("my fire");
		player = new SpriteGroup("my player");
		myPlane = new Player(0, 0, "");
		myPlane.setImage(getImage("resources/ship.png"));
		player.add(myPlane);
		cm = new FireBossCollision();
		cm.setCollisionGroup(bossG, MY_FIRE_GROUP);
	}

	@Override
	public void render(Graphics2D pen) {
		// TODO Auto-generated method stub
		pen.setColor(Color.BLACK);
		pen.fillRect(0, 0, getWidth(), getHeight());
		boss.render(pen);
		myPlane.render(pen);
		MY_FIRE_GROUP.render(pen);
	}

	@Override
	public void update(long elapsedTime) {
		// TODO Auto-generated method stub
		int right = (keyDown(KeyEvent.VK_RIGHT) && (myPlane.getX()
				+ myPlane.getWidth() < getWidth())) ? 5 : 0;
		int left = (keyDown(KeyEvent.VK_LEFT) && (myPlane.getX() > 0)) ? 5 : 0;
		int up = (keyDown(KeyEvent.VK_UP) && (myPlane.getY() > 0)) ? 5 : 0;
		int down = (keyDown(KeyEvent.VK_DOWN) && (myPlane.getY()
				+ myPlane.getHeight() < getHeight())) ? 5 : 0;
		int dx = right - left;
		int dy = up - down;
		myPlane.move(dx, -dy);
		myPlane.update(elapsedTime);
		MY_FIRE_GROUP.update(elapsedTime);
		boss.update(elapsedTime);
		if (keyDown(KeyEvent.VK_SPACE) && fireRate.action(elapsedTime)) {
			Sprite fire = new Sprite(getImage(FIRE));
			addFire(fire, myPlane, true);
			MY_FIRE_GROUP.add(fire);
		}
		for (Sprite fire : MY_FIRE_GROUP.getSprites()) {
			// System.out.println(MY_FIRE_GROUP.getSize());
			if (fire != null && fire.getY() < 0)
				fire.setActive(false);
		}
		cm.checkCollision();
//		System.out.println(bossG);
		if (boss.transformed())
			cm.setCollisionGroup(boss.getSpriteGroup(), MY_FIRE_GROUP);
		if (boss.isDead())
			finish();
	}

	private void addFire(Sprite fire, Sprite plane, boolean isMyPlane) {
		int invert = isMyPlane ? -1 : 1;
		int addHeight = isMyPlane ? 0 : 1;
		fire.setLocation(plane.getX() + plane.getWidth() / 2. - fire.getWidth()
				/ 2., plane.getY() + addHeight * plane.getHeight() + invert
				* fire.getHeight());
		fire.setVerticalSpeed(invert);
	}

	public static void main(String[] agrs) {
		GameLoader game = new GameLoader();
		game.setup(new BossDemo(), new Dimension(800, 600), false);
		game.start();
	}
}
