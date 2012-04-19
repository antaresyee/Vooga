package levelEditor;

import java.awt.Point;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.golden.gamedev.Game;

public class Question {
	public int enemies = 1;
	private String movement=null;
	public void enemyQuestion(Game g){
		ArrayList<Point> coordinates = new ArrayList<Point>();
		Object[] options = {"Back and Forth","Targeted Movement", "No Movement"};

		String input = (String) JOptionPane.showInputDialog(new JFrame(),
				"Pick Your Enemy Movement for default state:", "Top Down Demo'",
				JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		if (input.equals("Back and Forth")) movement ="BF,100,200,.2 ";
		if (input.equals("Targeted Movement")) movement = "T ";
	
	}
	
	public void writeEnemy(){
		 try{
			  // Create file 
			  FileWriter fstream = new FileWriter("StateInfo" + enemies +".txt");
			  BufferedWriter out = new BufferedWriter(fstream);	  
			  out.write(movement);
			  out.write("FH,1 ");
			  //Close the output stream
			  out.close();
			  }catch (Exception e){//Catch exception if any
			  System.err.println("Error: " + e.getMessage());
			  }
			  enemies++;
	}
	
	// asks user if he is happy with his location
	public int yesOrNo(String type) {
		String[] options = { "yes", "no" };
		int option = JOptionPane.showOptionDialog(new JFrame(),
				"Would you like to place the " + type + " here?",
				"Level Editor", JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

		return option;
	}
	
	
}

	
	
	

