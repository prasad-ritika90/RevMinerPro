import java.io.PrintStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


/*
 * The actual decision tree which also does all the needed computations
 
 */

/**
 * @specfield root: Attribute //Attribute that is at the root of the tree
 * @specifield subTrees: Map //maps a value to another DTree
 * @specifield classification: Boolean // True or False if leaf node null otherwise
 * @specfield size: int //Represents the size of the examples this tree holds inforamiton for
 *                         in the training period 
 */



public class DTree {
	
	private Attribute root; 
	private Map<Value,DTree> subTrees; 
    	public Boolean classification; 
	public int size; 
	
	/**
	 * Iniatlizes tree
	 * 
	 */
	public DTree(Attribute r, int s) {
		root = r;
		subTrees = new HashMap<Value,DTree>();
		classification = null; 
		size = s;
	}
	
	/**
	 * Initalizes tree if leaf node
	 * 
	 */
	public DTree(Boolean c) {
		classification = c;
	}
	
	/**
	 * Returns decision sub-tree with given value
	 */
	public DTree getTree(Value v) {
		return subTrees.get(v);
	}
	
	/**
	 * Adds a value-subtree pair
	 */
	
	public void add(Value v, DTree dt) {
		subTrees.put(v, dt);
	}
	
	/**
	 * 
	 * @return root of the tree
	 */
	public Attribute getRoot() {
		return root;
	}
	
	/**
	 * @return the set of values for a given attribute node
	 */
	public Set<Value> getValues() {
		return subTrees.keySet();
	}
	
	/**
	 * Classifies a given example
	 */
	public boolean classify(Example e,Boolean m) {
		Boolean result = classification; 
		DTree t = this;
		while (result == null) {
			Attribute a = t.root;
			Value v = e.get(a);
			if (m && v.getValue() == '?') {
				v = handleMissing(t,a);
			}
			t = t.subTrees.get(v);
			result = t.classification;
		}
		return result; 
	}
	
	/**
	 * Returns a classification of tree if leaf node
	 */
	public Boolean getResult() {
		return classification;
	}
	
	/**
	 * 
	 * Used to handle missing data. Picks
	 * a value based on which tree had more examples
	 * given information so far
	 */
	private Value handleMissing(DTree t, Attribute a) {
		Value v1 = new Value('y',a);
		Value v2 = new Value('n',a);
		if (t.subTrees.get(v1).size >= t.subTrees.get(v2).size) {
			return v1;
		}
		return v2; 
	}
	

	
	
	

}
