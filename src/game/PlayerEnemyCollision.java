package game;

import gameObjects.Ship;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionGroup;

public class PlayerEnemyCollision extends CollisionGroup{

	public void collided(Sprite player, Sprite enemy) {
		// TODO Auto-generated method stub
		((Ship) player).reduceHealth();
		enemy.setActive(false); 
		
		
	}

}
