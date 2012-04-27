package innerGameGUI;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.golden.gamedev.Game;
import com.golden.gamedev.GameLoader;
/*
 * Example of combining all KComponents
 */
public class StartGUI{
	private KPanel myPanel;
	private Game myGame;
	private String filepath;
	
	
	public StartGUI(Game game) {
		myGame = game;
		myPanel = new KPanel(null, game);
		myPanel.setGrid(new VerticalGrid());
//		myPanel.setOrdering(KPanel.VERTICAL);
		KButton load = new KButton(myPanel, game, "Load Level");
		KButton help = new KButton(myPanel, game, "Help");
		KButton button = new KButton(myPanel, game, "Quit");
		
		//Define action to be listened by the developer. 
		//If want to change button's behavior, no need to go to the original framework
		load.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev) {
				JFileChooser fc = new JFileChooser("./");
		        int returnVal = fc.showOpenDialog(null);
		        if (returnVal == JFileChooser.APPROVE_OPTION)
		        {
		        	filepath = fc.getSelectedFile().getAbsolutePath();
		        }
			}
		});

		help.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev) {
				JOptionPane.showMessageDialog(new JFrame(), "Simply click on Load and choose a level file from the window opened.");
			}
		});
		
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev) {
				myGame.finish();
			}
		});
		myPanel.add(load);
		myPanel.add(help);
		myPanel.add(button);
		
		
	}
	
	public String getLoadPath(){
		return filepath;
	}

	public void render(Graphics2D pen) {
		
		myPanel.render(pen);
	}
	
	public void update(long elapsedTime) {
		// TODO Auto-generated method stub
		myPanel.update(elapsedTime);
	}
	
//	public static void main(String[] arg0)
//	{
//		GameLoader game = new GameLoader();
//		game.setup(new StartGUI(), new Dimension(800, 600), false);
//		game.start();
//	}
}
