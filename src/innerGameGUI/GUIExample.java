package innerGameGUI;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.golden.gamedev.Game;
import com.golden.gamedev.GameLoader;
/*
 * Example of combining all KComponents
 */
public class GUIExample extends Game{
	StartGUI start;
	@Override
	public void initResources() {
//		myPanel = new KPanel(null, this);
//		myPanel.setGrid(new VerticalGrid());
////		myPanel.setOrdering(KPanel.VERTICAL);
//		KButton load = new KButton(myPanel, this, "Load Level");
//		KButton help = new KButton(myPanel, this, "Help");
//		KButton button = new KButton(myPanel, this, "Quit");
//		
//		//Define action to be listened by the developer. 
//		//If want to change button's behavior, no need to go to the original framework
//		load.addActionListener(new ActionListener(){
//			public void actionPerformed(ActionEvent ev) {
//				JOptionPane.showMessageDialog(new JFrame(), "Surprise suprise!!");
//			}
//		});
//
//		help.addActionListener(new ActionListener(){
//			public void actionPerformed(ActionEvent ev) {
//				JOptionPane.showMessageDialog(new JFrame(), "Surprise suprise!!");
//			}
//		});
//		
//		button.addActionListener(new ActionListener(){
//			public void actionPerformed(ActionEvent ev) {
//				finish();
//			}
//		});
//		myPanel.add(load);
//		myPanel.add(help);
//		myPanel.add(button);
		start = new StartGUI(this);
		
	}

	@Override
	public void render(Graphics2D pen) {
		
		start.render(pen);
	}

	@Override
	public void update(long elapsedTime) {
		// TODO Auto-generated method stub
		start.update(elapsedTime);
	}
	
	public static void main(String[] arg0)
	{
		GameLoader game = new GameLoader();
		game.setup(new GUIExample(), new Dimension(800, 600), false);
		game.start();
	}
}
