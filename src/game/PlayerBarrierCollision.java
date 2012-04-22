package game;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionGroup;

public class PlayerBarrierCollision extends CollisionGroup {

	
	@Override
	public void collided(Sprite player, Sprite barrier) {
		if (collisionSide == TOP_BOTTOM_COLLISION){
			player.setLocation(player.getX(),barrier.getY()+player.getHeight());
		}
		if (collisionSide == BOTTOM_TOP_COLLISION) player.setLocation(player.getX(),barrier.getY()-barrier.getHeight());
		
		if (collisionSide == RIGHT_LEFT_COLLISION){
			player.setLocation(barrier.getX()-player.getWidth(), player.getY());
		}
		if (collisionSide == LEFT_RIGHT_COLLISION) player.setLocation(barrier.getX()+barrier.getWidth(), player.getY());
	}

}
