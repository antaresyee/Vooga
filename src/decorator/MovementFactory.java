package decorator;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;


/*
 * Allows the developer insert a number of Decorators and 
 * combines them into one. Also allows them to remove as well.
 */
public class MovementFactory {
	
	private HashMap<String,Class<?>> myDecorators; 
	private MovementDecorator toReturn;
	
	public MovementFactory() throws ClassNotFoundException{
		myDecorators = new HashMap<String, Class<?>>(); 
		myDecorators.put("HorizontalDecorator", Class.forName("decorator.HorizontalDecorator")); 
		myDecorators.put("VerticalDecorator", Class.forName("decorator.VerticalDecorator")); 
		
	}
	
	
	public MovementDecorator getDecorators(){
		return toReturn; 
	}
	
	public void addDecorators(ArrayList<String> toDecorate) throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassNotFoundException{
	
		Class<?> firstFilter = myDecorators.get(toDecorate.get(0)); 
		// constructor.newinstance()
		Constructor<?> construct = getCorrectConstructor(firstFilter); 
		MovementDecorator decorated = (MovementDecorator) construct.newInstance(new SimpleShip()); 
		
		for (int i=1; i< toDecorate.size(); i++){
			Class<?> hold = myDecorators.get(toDecorate.get(i));
			Constructor<?> construct2 = getCorrectConstructor(hold); 
			MovementDecorator decorated2 = (MovementDecorator) construct2.newInstance(decorated);
			decorated = decorated2; 
		}
		toReturn = decorated; 
	}
	

	
	public Constructor<?> getCorrectConstructor(Class<?> c) throws ClassNotFoundException{
		Constructor[] myConstructors= c.getDeclaredConstructors(); 
		
		for (int i =0; i <myConstructors.length; i++){
			Constructor<?> trueConstructor = myConstructors[i];
			if(trueConstructor.getParameterTypes().length == 1 && trueConstructor.getParameterTypes()[0].equals(Class.forName("decorator.DecoratedShip")))
			{
				return trueConstructor;
			}
		}
		return null;
	}

}
