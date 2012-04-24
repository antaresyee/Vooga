package game;

import gameObjects.Player;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionGroup;

public class PlayerEnemyCollision extends CollisionGroup{

	public void collided(Sprite player, Sprite enemy) {
		// TODO Auto-generated method stub
		((Player) player).reduceHealth();
		enemy.setActive(false); 
		
		
	}

}
