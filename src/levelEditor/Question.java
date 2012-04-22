package levelEditor;

import java.awt.Point;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.golden.gamedev.Game;

public class Question {
	public static int enemies = 1;
	private String movement=null;
	public static ArrayList<String>fileData;
	int count =1;
	public boolean done;
	
	public String enemyQuestion(Game g){
		done =false;
		if(count==1) fileData = new ArrayList<String>();
		count++;
		Object[] options = {"Back and Forth","Targeted", "Path"};

		String input = (String) JOptionPane.showInputDialog(new JFrame(),
				"Pick Your Enemy Movement:", "Top Down Demo'",
				JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		if (input.equals("Back and Forth")) movement ="BF,100,200,.2 ";
		if (input.equals("Targeted")) movement = "T ";
		if (input.equals("Path")){

			movement = "Path";
			count=1;
			JOptionPane
			.showMessageDialog(new JFrame(),
					"Click on the coordinates for your Enemy's Path Movement. Press 'D' when done.");
			return movement;
		}
		String a = addState(movement,g);
		fileData.add(a);
		System.out.println(a);
		int ans =addAnotherMovement(g);
		if (ans ==1) 
			count=1;
		
		return movement;
	}

	public int addAnotherMovement(Game g) {
		String[] yon = { "yes", "no" };
		int ans = JOptionPane.showOptionDialog(new JFrame(),
				"Do you want to add another Movement?","Level Editor", JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, yon, yon[0]);
		if (ans==0) enemyQuestion(g);
		return ans;
	}

	public String addState(String move,Game g) {
		String state ="";
		Object[] options1 = {"Full Health","Half Health", "Low Health", "Proximity"};
		String input1 = (String) JOptionPane.showInputDialog(new JFrame(),
				"Pick State", "Enemy Movement",
				JOptionPane.PLAIN_MESSAGE, null, options1, options1[0]);
				if (input1.equals("Full Health")) state="FH,";
				if (input1.equals("Half Health")) state="HH,";
				if (input1.equals("Low Health")) state="LH,";
				if (input1.equals("Proximity")) state="P,";
		String prior = JOptionPane.showInputDialog(null, "Enter Priority Level number: (the lower the number, the higher the priority)", 
						"Enemy Movement", 1);
		state=state+Integer.parseInt(prior) + " ";
		
		return move +state;
	}
	
	public void writeEnemy(ArrayList<String> data){
		
		try{
			  // Create file 
			 FileWriter fstream = new FileWriter("StateInfo" + enemies +".txt");
			  BufferedWriter out = new BufferedWriter(fstream);	  
			  for (String str:data){
			  out.write(str);
			  out.write("\n");
			  }
			  //Close the output stream
			  out.close();
			  }catch (Exception e){//Catch exception if any
			  System.err.println("Error: " + e.getMessage());
			  }
			  enemies++;
		}
	
	public ArrayList<String> getFileData() {
		return fileData;
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

	
	
	

