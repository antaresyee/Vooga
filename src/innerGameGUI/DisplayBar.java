package innerGameGUI;

import gameObjects.GameObject;

/**
 * Decorator class: position depends on the decorate objects
 * Can be implemented as bloodbar, Name of the ship (which can also be combined with KTextBox)
 * 
 * @author Kaitlyn
 *
 */
public abstract class DisplayBar extends GameObject{
	//data from gameobject
	protected double myFull;
	protected double myCurrent;
	//name
	protected String myString;
	 
	
	

}
