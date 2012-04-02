package levelLoadSave;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Sprite;


public class GameTestClass extends Game {

    @Override
    public void initResources() {
        // TODO Auto-generated method stub
        Sprite s = new Sprite(getImage("./resources/triangle.png"), 10, 10);
        
    }

    @Override
    public void render(Graphics2D arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void update(long arg0) {
        // TODO Auto-generated method stub
        
    }
    
    public BufferedImage getBarrierImage() {
        return getImage("./resources/triangle.png");
    }
    
    public BufferedImage getShipImage() {
        return getImage("./resources/ship.png");
    }

}
