package states;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author James Pagliuca
 * 
 */
public class EnemyDataLoader {

	private ArrayList<String> movementInfo;
	private ArrayList<String> stateInfo;

	public EnemyDataLoader(FileInputStream f) {
		movementInfo = new ArrayList<String>();
		stateInfo = new ArrayList<String>();
		Scanner s;
		s = new Scanner(f);
		s.useDelimiter("\\n");
		while (s.hasNext()) {
			String line = s.next();
			String[] splitLine = line.split(" ");
			movementInfo.add(splitLine[0]);
			stateInfo.add(splitLine[1]);
		}

	}

	public ArrayList<String> getMovementInfo() {
		return movementInfo;
	}

	public ArrayList<String> getStateInfo() {
		return stateInfo;
	}
}
