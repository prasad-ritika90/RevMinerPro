
/*Represents a value of a given attribute
 * 
 * 
 */

/**
 * 
 * @specfield c: value //can be y, n, or ?
 */


public class Value {
	
	private char val; 
	private Attribute parent; 
	
	public Value(char v, Attribute p) {
		val = v;
		parent = p;
	}
	
	public char getValue() {
		return val;
	}

	public Attribute getParent() {
		return parent;
	}
	
	@Override
	public boolean equals(Object o) {
		Value v = (Value) o;
		if (val == v.val) {
			return true; 
		}
		return false; 
	}
	
	@Override
	public int hashCode() {
		return val*(parent.getID()-1*23); 
	} 
	
}
