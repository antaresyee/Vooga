package game;

import gameObjects.Ship;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionGroup;

public class PlayerEnemyCollision extends CollisionGroup{
	
	private int damage = 3;

	public void collided(Sprite player, Sprite enemy) {
		((Ship) player).reduceHealth(damage);
		enemy.setActive(false); 
	}

}
