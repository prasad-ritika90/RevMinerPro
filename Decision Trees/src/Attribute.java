
/*Class to represent an Attribute
 * 
 */

/**
 * 
 * @specfield id: Integer // representing which attribute, from 1-16
 * @specfield party: char //representing the value of the attribute, either y, n, or ?
 * 
 * 
 */

public class Attribute {
	
	private int id; 
	
	
	/**
	 * 
	 * Assigns ID to attribute
	 */
	public Attribute(int i) {
		id = i;
	}
	
	public int getID() {
		return id;
	}
	
	public boolean equals(Object o) {
		Attribute a = (Attribute) o;
		if (id == a.id) {
			return true; 
		} else {
			return false; 
		}
	}
	
	public int hashCode() {
		return id; 
	}
	

}
