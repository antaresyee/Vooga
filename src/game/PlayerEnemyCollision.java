package game;

import gameObjects.Player;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionGroup;

public class PlayerEnemyCollision extends CollisionGroup{
	
	private int damage = 3;

	public void collided(Sprite player, Sprite enemy) {
		((Player) player).reduceHealth(damage);
		enemy.setActive(false); 
	}

}
