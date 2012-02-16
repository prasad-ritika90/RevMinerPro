import java.util.LinkedHashMap;
import java.util.Map;

/*
 * Represents one instance or example in the set
 *
 */

/**
 * @specfield id: int //example #
 * @specfield map: LinkedMap //maps an attribute to its value for a given example
 * @specfield result: boolean //indicates democrat (true) or republican (false)
 * 
 */

public class Example {
	
	private int id; 
	Map<Attribute, Value> map; 
	private boolean result; 
	
	/**
	 * 
	 * Creates new example with given id n and given
	 * classification r
	 */
	public Example(int n, boolean r) {
		id = n;
		result = r;
		map = new LinkedHashMap<Attribute,Value>();
		
	}
	
	/**
	 * 
	 * Records the attribute and its value
	 */
	public void add(Attribute a, Value v) {
		map.put(a,v);
	}
	
	/**
	 * 
	 * 
	 * @return a Value based on a given attribute
	 */
	public Value get(Attribute a) {
		return map.get(a);
	}
	
	/**
	 * 
	 * @return returns classification of example
	 */
	public boolean getResult() {
		return result;
	}
	
	/**
	 * 
	 * @return the ID of the example
	 */
	public int getID() {
		return id; 
	}
	
	/**
	 * 
	 * @return the string result of the example 
	 */
	public String getParty() {
		if (result) {
			return "democrat";
		}
		return "republican";
	}


	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("ID: " +id + "\n");
		if (result) {
			sb.append("This person is a democrat\n");
		} else {
			sb.append("This person is a republican\n");
		}
		for (Attribute a : map.keySet()) {
			sb.append("Attribute " + a.getID() + " is a " + map.get(a).getValue() + "\n");
		}
		return sb.toString();
		
	}
}
