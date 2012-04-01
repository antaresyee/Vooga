package game;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionGroup;

public class PlayerBarrierCollision extends CollisionGroup {

	@Override
	public void collided(Sprite player, Sprite barrier) {
		if (collisionSide == BOTTOM_TOP_COLLISION || collisionSide == TOP_BOTTOM_COLLISION){
			player.setVerticalSpeed(0);
		}
		if (collisionSide == LEFT_RIGHT_COLLISION || collisionSide == RIGHT_LEFT_COLLISION){
			player.setHorizontalSpeed(0);
		}
	}

}
