package movement;

/**
 * 
 * @author James Pagliuca
 *
 */
public abstract class MovementFactory {
	
	protected String myName;

	public boolean isMyMovement(String s){
		return (s.equals(myName));
	}
	
	public abstract Movement makeMyMovement(String[] parameters);
}
