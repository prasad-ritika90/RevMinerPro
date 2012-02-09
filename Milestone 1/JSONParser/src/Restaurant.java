import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/*
 * Represents 1 restaurant
 */

/**
 * 
 * @specfield: name- String//name of Restaurant
 * @specfield: map- LinkedHashMap<String,String> //Attribute to Value pairs
 */


public class Restaurant {
	
	public String name; 
	public Map<String,String> map;
	
	
	/**
	 * Creates new Restaurant object
	 */
	public Restaurant(String s) {
		name = s;
		map = new LinkedHashMap<String,String>();
	}
	
	/**
	 * Adds key-value pair into map
	 */
	public void add(String key, String value) {
		map.put(key, value);
		
	}
	
	/**
	 * 
	 * @return all the attributes of the restaurant
	 */
	public Set<String> getAttributes() {
		return map.keySet();
	}
	
	/**
	 * 
	 * 
	 * @return value associated with attribute
	 */
	public String getValue(String s) {
		return map.get(s);
	}
	
	
	
 
}
