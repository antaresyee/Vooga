package levelEditor;

import java.io.BufferedWriter;
import java.io.FileWriter;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Question {
	public int enemies = 1;
	private String movement;
	public void enemyQuestion(){
		Object[] options = {"Back and Forth"};

		String input = (String) JOptionPane.showInputDialog(new JFrame(),
				"Pick Your Enemy Movement for default state:", "Top Down Demo'",
				JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		if (input.equals("Back and Forth")) movement ="BF,100,200,.2 ";
		System.out.print(enemies);
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
	
	
}

	
	
	

