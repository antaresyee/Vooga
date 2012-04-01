package levelEditor;

import gameObjects.Barrier;
import gameObjects.GameObjectFactory;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ButtonGrid {
	 
    JFrame frame=new JFrame(); //creates frame
    private static JButton[][] grid; //names the grid of buttons
    Dimension d;
    ActionListener evt;
    List<GameObjectFactory> GOFList;
    BufferedImage image;
    
    public ButtonGrid (int width, int length){ //constructor
            frame.setLayout(new GridLayout(width,length)); //set layout
            d = new Dimension(500,670);
            frame.setPreferredSize(d);
            grid=new JButton[width][length]; //allocate the size of grid
            for(int y=0; y<length; y++){
                    for(int x=0; x<width; x++){
                            grid[x][y]=new JButton(); //creates new button    
                            frame.add(grid[x][y]); //adds button to grid
                            grid[x][y].addActionListener(new ActionListener() {
                           	 
                                public void actionPerformed(ActionEvent e)
                                {
                                	JFrame frame1 = new JFrame();
                            		Object[] options = { "Barrier", "Player" };

                            		String input = (String) JOptionPane.showInputDialog(frame1,
                            				"Place Barrier or Player:", "Vooga",
                            				JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

                            		if (input.equals("Barrier"))
                            		{
                            			Barrier.BarrierFactory temp = new Barrier.BarrierFactory(1,1,image);
                            		}
                            		
                                }
                            });
                    }
            }
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack(); //sets appropriate size for frame
            frame.setVisible(true); //makes frame visible
            
    }
           
    
    
    
    public static void main(String[] args) {
            new ButtonGrid (23,23);//makes new ButtonGrid with 2 parameters
    }
}
