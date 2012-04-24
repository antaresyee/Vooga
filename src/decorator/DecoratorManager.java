package decorator;

import java.util.ArrayList;
import java.util.HashMap;


/*
 * Allows the developer insert a number of Decorators and 
 * combines them into one. Also allows them to remove as well.
 */
public class DecoratorManager {
	
	private HashMap<String,Class<?>> myDecorators; 
	private Class<?> toReturn;
	
	DecoratorManager() throws ClassNotFoundException{
		myDecorators = new HashMap<String, Class<?>>(); 
		myDecorators.put("HorizontalDecorator", Class.forName("HorizontalDecorator")); 
		myDecorators.put("VerticalDecorator", Class.forName("VerticalDecorator")); 
		
	}
	
	
	public Class<?> getDecorators(){
		return toReturn; 
	}
	
	public void addDecorators(ArrayList<String> toDecorate){
		Class<?> firstFilter = myDecorators.get(toDecorate.get(0)); 
		toReturn = firstFilter; 
		
//		for (int i=1; i< toDecorate.size(); i++){
//			Class<?>hold = myDecorators.get(toDecorate.get(i));  
//			toReturn = new  
//		}
	}
	

}
