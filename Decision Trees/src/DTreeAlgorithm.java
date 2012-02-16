import java.io.PrintStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;

/**
 * 
 * @specfield examples: Set //current set of examples
 * @specfield examples: Set //set of previous examples
 * @specfield attributes: Set //set of attributes
 * @specfield pruner: Map //maps a tree to all examples associated with it
 * @specfield criteria Boolean //true for IG, false for accuracy
 * @specfield demoMissing: int[] // array for democrats to handle misisng values
 * @specifield repMisisng: int[] //array for republicans to handle missing values
 *
 */


public class DTreeAlgorithm {
	
	Set<Example> examples;
	Set<Example> parentExamples;
	Set<Attribute> attributes;
	HashMap<DTree,Set<Example>> pruner; 
	boolean criteria; //true ig, false misclassification impurity
	boolean missing; 
	int[] demoMissing;
	int[] repMissing;
	
	/**
	 * Creates a new DTAAlgorithm object
	 * with given sent of examples, attributes, and critera
	 */
	public DTreeAlgorithm(LinkedHashSet<Example> e, HashSet<Attribute> a, boolean c, boolean m) {
		examples = e;
		parentExamples = e;
		attributes = a;
		criteria = c;
		pruner = new HashMap<DTree,Set<Example>>();
		missing = m; 
		if (m) {
		  demoMissing = new int[17];
		  repMissing = new int[17];
		  missingValues();
		}
	}
	
	/**
	 * 
	 * @return DTree after running the learning algorithm
	 */
	public DTree DTAlgorithm() {
		return DTAlgorithm(examples,attributes,parentExamples);
	}
	
	/**
	 * The Decision Tree Learning Algorithm
	 */
	private DTree DTAlgorithm(Set<Example> eSet, Set<Attribute> aSet, Set<Example> pSet) {

		if (eSet.isEmpty()) {
	//		System.out.println("this happens too");
			return new DTree (pluralityV(pSet));
			
		} else if (goalState(eSet)) {
	//		System.out.println("This happens also");
			Iterator<Example> itr = eSet.iterator();
			Example one = itr.next();
			return new DTree(one.getResult());
		} else if (aSet.isEmpty()) {
			System.out.println("This happens as well");
			return new DTree(pluralityV(eSet));
		} else {
			Attribute a = informationGain(eSet,aSet);
			DTree tree = new DTree(a,eSet.size());
			aSet.remove(a);
			ArrayList<Character> values = new ArrayList<Character>();
			if (missing) {
			  values.add('y');
			  values.add('n');
			} else {
			  values.add('y');
			  values.add('n');
			  values.add('?');
			}
			
			
			for (char c : values) {
				LinkedHashSet<Example> newExamples = new LinkedHashSet<Example>();
				for (Example x : eSet) {
					Value v2 = x.get(a);
					char val = v2.getValue();
					if (missing && val == '?') {
						val = handleMissing(x,a);	
					} 
					if (c == val) {
						newExamples.add(x);
					}
				}
				DTree subTree = DTAlgorithm(newExamples,aSet,eSet);
				Value v = new Value(c,a);
				tree.add(v,subTree);
			} 
			aSet.add(a);
			pruner.put(tree, eSet);
			return tree; 
		} 
		 
	}
	
	/**
	 * Figures out which attribute we can gain the most 
	 * information from 
	 * 
	 * @return Attribute in which the information 
	 * gain is the largest 
	 */
	public Attribute informationGain(Set<Example> e, Set<Attribute> aSet) {
		Attribute split = null; 
		double orig = goal(e);
		double maxGain = -10000; 
		for (Attribute a : aSet) {
			double d = orig - remainder(a,e);
			if (d > maxGain) {
				split = a; 
				maxGain = d; 
			}
		}
		return split;
	}
	
	/**Calculates entropy with respect to the goal
	 * 
	 * @return value of the entropy of the entire set 
	 * of examples 
	 */
	public double goal(Set<Example> eSet) {
		int p =0; 
		int n = 0;
		for (Example e : eSet) {
			if (e.getResult()) {
				p++;
			} else {
				n++;
			}
		}
		double x = ((double) p) / ((double) p +n); 
		double ans;
		if (criteria) {
			ans = entropy(x);
		} else {
			ans = mP(x);
		}
		return ans;
	}
	
	/**Calculates entropy
	 * 
	 * @returns entropy value of a given boolean variable that
	 * is true with probability x
	 */
	private double entropy(double x) {
		if (x==1 || x==0) {
			return 0; 
		}
		double a = x * (Math.log(x)/Math.log(2)); 
		double b = (1-x) * (Math.log(1-x)/Math.log(2));
		return -1*(a+b);
	}
	
	/**
	 * Takes in a probability and returns the missclassification impurity 
	 */
	private double mP(double x) {
		if (x > 0.5) {
			return 1-x;
		} else {
			return x;
		}
	}
	
	/** Returns the entropy 
	 * if we split on a particular
	 * 
	 * @requires set examples to represent all active examples 
	 * @returns entropy or impurtiy if we split on
	 * attribute a
	 */
	public double remainder(Attribute a, Set<Example> eSet) {
		int total = eSet.size();
		double answer = 0; 
		ArrayList<Character> values = new ArrayList<Character>();
		if (missing) {
		  values.add('y');
		  values.add('n');
		} else {
		  values.add('y');
		  values.add('n');
		  values.add('?');
		}
		for (char v : values) {
			double belong = 0;
			double result = 0;
			for (Example e : eSet) {
				Value v2 = e.get(a);
				char val = v2.getValue();
				if (missing && val == '?') {
					val = handleMissing(e,a);
				} 
				if (v == val) {
					belong++;
					if (e.getResult()) {
						result++;
					}
				}
			}
			double weight = belong/(double) total;
			double x = 0; 
			if (belong != 0) {
				if (criteria) {
					x = entropy(result/belong);
				} else {
					x = mP(result/belong);
				}
			}
			answer= answer + (weight*x);
		}
		return answer; 
	}
	
	/**Returns the most common classification from 
	 * the set of examples
	 * 
	 * @returns the most common output among the set of examples
	 */
	public boolean pluralityV(Set<Example> set) {
		int t = 0; 
		int f = 0; 
		for (Example e : set) {
			if (e.getResult()) {
				t++;
			} else {
				f++;
			}
		}
		if (t >= f) {
			return true;
		} else {
			return false; 
		}
	}
	
	/**Checks if examples have the same classification
	 * 
	 * @return true iff all the examples have the same end result
	 */
	public boolean goalState(Set<Example> eSet) {
		boolean same; 
		Iterator<Example> itr = eSet.iterator();
		Example one = itr.next();
		same = one.getResult(); 
		for (Example e : eSet) {
			if (!(e.getResult() == same)) {
				return false; 
			}
		}
		return true; 
	}
	
	/**
	 * Fills in demoMissing and repMissing
	 * Where each index represents an attribute 
	 * and index value represents which value was more common
	 * 
	 */
	public void missingValues() {
		for (Example e : examples) {
			boolean r = e.getResult();
			for (Attribute a : attributes) {
				int id = a.getID();
				char c = e.get(a).getValue();
				if (r) {
					if (c == 'y') {
						demoMissing[id]++;
					} else if (c == 'n') {
						demoMissing[id]--;
					}
				} else {
					if (c == 'y') {
						repMissing[id]++;
					} else if (c == 'n') {
						repMissing[id]--;
					}
				}
			}
		
		}
	}
	
	
	/**
	 * 
	 * Takes a given example and attribute with misisng value
	 * and returns the most common value with given classification
	 */
	public char handleMissing(Example x, Attribute a) {
		boolean r = x.getResult();
		if (r) {
			if (demoMissing[a.getID()] >= 0) {
				return 'y';
			} else {
				return 'n';
			}
		} else {
			if (repMissing[a.getID()] >= 0) {
				return 'y';
			} else {
				return 'n';
			}
		}
	}
	
	/**
	 * 
	 * Checks accuracy of a tree given a set of examples
	 */
	public double check(DTree t, Set<Example> eSet) {
		double percent;
		double right = 0.0;
		double wrong= 0.0;
		for (Example e : eSet) {
			if (t.classify(e,missing) == e.getResult()) {
				right++;
			} else {
				wrong++;
			}
		}
		percent = (right/(right+wrong)) * 100;
	//	System.out.println("You scored " + percent +"%");
		return percent; 
	}
	
	/**
	 * 
	 * Prunes a tree given a set of examples 
	 */
	public void prune(DTree tree, LinkedHashSet<Example> eSet) {
		for (DTree t : pruner.keySet()) {
				double before = check(tree,eSet);
				if (t.classification == null) 
				   t.classification = pluralityV(pruner.get(t));
				double after = check(tree,eSet);
				if (before > after || (before == after && t.size < 10)) { //leave it pruned if tie 
				   t.classification = null;
				}
		}
	}
	
	
}

