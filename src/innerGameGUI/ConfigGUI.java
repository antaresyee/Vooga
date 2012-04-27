package innerGameGUI;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.golden.gamedev.Game;
import com.golden.gamedev.util.ImageUtil;

public class ConfigGUI extends InGameGUI{
	private List<KPanel> displays;
	private List<List<KButton>> myList;
	private List<Integer> current;
	private KButton currentButton;
	private int index;
	
	private static final int BUTTON_WIDTH = 30;
	private static final int BUTTON_HEIGHT = 30;
	private static final int LARGE_BUTTON_WIDTH = 200;
	private static final int LARGE_BUTTON_HEIGHT = 200;
	private static final int SPACING = 10;
	
	public ConfigGUI(Game game){
		setGame(game);
		setPanel(new KPanel(null, game));
		KButton shipTab = new KButton(myPanel, game, "Ship", Color.CYAN);
		KButton weaponTab = new KButton(myPanel, game, "Weapon", Color.CYAN);
		myPanel.add(shipTab);
		myPanel.add(weaponTab);
		displays = new ArrayList<KPanel>();
		myList = new ArrayList<List<KButton>>();
		current = new ArrayList<Integer>();
		index = 0;
		Color[] colors = {Color.orange, Color.pink};
		for(int i = 0; i < 2; i++){
			displays.add(new KPanel(null, game, colors[i], (int)myPanel.getX(), myPanel.getHeight(), myGame.getWidth() - (int)myPanel.getX(), myGame.getHeight() - myPanel.getHeight()));
			myList.add(new ArrayList<KButton>());
			current.add(0);
		}
		String filename = "resources/ship";
		String ext = ".png";
		File file = new File(filename+ext);
		String name  = filename+1;
		int i = 2;
		KPanel kp = displays.get(0);
		while(file.exists())
		{
			KButton kb = new KButton(kp, myGame, "");
			kb.setImage(myGame.getImage(name+ext));
			kb.setWidthHeight(BUTTON_WIDTH, BUTTON_HEIGHT);
			kb.setImagePath(name+ext);
			if(myList.get(0).size()==0){
				kb.setLocation(kp.getX()+kp.getWidth()/2.-kb.getWidth()/2., kp.getY()+kp.getHeight()*3/4.);
			}else{
				KButton last = myList.get(0).get(myList.get(0).size()-1);
				kb.setLocation(last.getX() + last.getWidth() + SPACING, last.getY());
			}
//			System.out.println(kb.getX()+","+kb.getY()+","+kb.getWidth()+","+kb.getHeight());
			myList.get(0).add(kb);
			name = filename+i;
			i++;
			file = new File(name+ext);
		}
		KButton kb = new KButton(displays.get(1), myGame, "");
		myList.get(1).add(kb);
		currentButton = new KButton(null, myGame, "");
		currentButton.setLocation(kp.getX() + kp.getWidth()/2. - LARGE_BUTTON_WIDTH/2., SPACING + kp.getY() + kp.getHeight()/4.);
		updateCurrent(displays.get(index), myList.get(index).get(current.get(index)));
		shipTab.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev) {
				index = 0;
				updateCurrent(displays.get(index), myList.get(index).get(current.get(index)));
			}
		});

		weaponTab.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev) {
				index = 1;
				updateCurrent(displays.get(index), myList.get(index).get(current.get(index)));
			}
		});
		
		currentButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev) {
				selectionEvent();
			}

		});
	}

	private void selectionEvent() {
		setImagePath(currentButton.getImagePath());
		JOptionPane.showMessageDialog(new JFrame(), "You Have Selected this ship!");
	}
	
	public void updateCurrent(KComponent parent, KButton currentObj){
		currentButton.setColor(parent.getColor());
//		System.out.println(currentButton.getX());
//		currentButton.setLocation(parent.getX() + parent.getWidth()/2. - LARGE_BUTTON_WIDTH/2., SPACING + parent.getY() + parent.getHeight()/4.);
		if(currentObj.getImagePath() == null || currentObj.getImagePath().length()==0){
			currentButton.setImage(null);
//			System.out.println(currentButton.getX());
			return;
		}
		currentButton.setImage(myGame.getImage(currentObj.getImagePath()));
		currentButton.setImagePath(currentObj.getImagePath());
		currentButton.setWidthHeight(LARGE_BUTTON_WIDTH, LARGE_BUTTON_HEIGHT);
	}
	
	public void render(Graphics2D pen){
		super.render(pen);
		displays.get(index).render(pen);
		currentButton.render(pen);
		List<KButton> myButtons = myList.get(index);
		for(KButton kb : myButtons){
			kb.render(pen);
		}
//		System.out.println(displays.get(index).getY());
	}
	
	public void update(long elapsedTime){
		super.update(elapsedTime);
		displays.get(index).update(elapsedTime);
		currentButton.update(elapsedTime);
		List<KButton> myButtons = myList.get(index);
		for(KButton kb : myButtons){
			kb.update(elapsedTime);
		}
		if(myGame.keyPressed(KeyEvent.VK_RIGHT)) shiftRight();
		if(myGame.keyPressed(KeyEvent.VK_LEFT)) shiftLeft();
		if(myGame.keyPressed(KeyEvent.VK_ENTER)) selectionEvent();
	}

	private void shiftLeft() {
		// TODO Auto-generated method stub
		if(current.get(index) <= 0) return;
		for(KButton kb : myList.get(index)){
			kb.moveX(kb.getWidth()+SPACING);
		}
		current.set(index, current.get(index)-1);
		updateCurrent(displays.get(index), myList.get(index).get(current.get(index)));
	}

	private void shiftRight() {
		// TODO Auto-generated method stub
		if(current.get(index) >= myList.get(index).size()-1) return;
		for(KButton kb : myList.get(index)){
			kb.moveX(-(kb.getWidth()+SPACING));
		}
		current.set(index, current.get(index)+1);
		updateCurrent(displays.get(index), myList.get(index).get(current.get(index)));
	}
}
