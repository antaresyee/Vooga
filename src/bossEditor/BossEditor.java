package bossEditor;

import gameObjects.boss.Boss;
import gameObjects.boss.BossState;
import gameObjects.boss.BossWeakPoint;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


import com.golden.gamedev.GameLoader;

public class BossEditor extends ObjectEditor{

	private Boss boss;
	private BossState bossState;
	
	@Override
	public void saveEvent() {
		// TODO to be implemented
		
	}

	@Override
	public void clickEvent() {
		// TODO Auto-generated method stub
		int x = getMouseX();
		int y = getMouseY();
		List<BossWeakPoint> bwps = bossState.getWeakPoints();
		boolean alreadyExist = false;
		for(BossWeakPoint bwp : bwps)
		{
			if(Math.abs(bwp.getX()-x)<5 && Math.abs(bwp.getY()-y)<5){
//				System.out.println("true");
				alreadyExist = true;
				break;
			}
		}
		if(alreadyExist)
		{
			//TODO: to be implemented
			JOptionPane.showMessageDialog(new JFrame(), "Weakpoint already Exist here! more options to be implemented, e.g. conditions!");
		}else{
			String[] options = {"yes","no"};
			int option = JOptionPane.showOptionDialog(new JFrame(), "You wish to place a weakpoint at (" + x + "," + y + ")", "BossEditor", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			if(option == 0)
			{
				bossState.addWeakPoint(new BossWeakPoint(x,y,bossState));
			}
		}
	}

	@Override
	public void initResources() {
		// TODO Auto-generated method stub
		boss = new Boss();
		bossState = new BossState();
		boss.addState(bossState);
		BufferedImage[] bis = new BufferedImage[1];
		bis[0]=getImage("resources/spaceship.png");
		String[] names = new String[1];
		names[0] = "spaceship";
		setInfo(bis, getImage("resources/spaceship.png"),names, 1, 1);	
	}
	
	public static void main(String[] args)
	{
		GameLoader game = new GameLoader();
		game.setup(new BossEditor(), new Dimension(800, 600), false);
		game.start();
	}
	
}
