import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 
 * Parses JSON files
 *
 */


public class JSONParser {
	
	Scanner console; 
	String file; 
	LinkedHashSet<String> attributes;
	Set<Restaurant> restaurants; 
	
	
	public JSONParser(String f) throws FileNotFoundException {
		file = f; 
		console = new Scanner(new File(file));
		attributes = new LinkedHashSet<String>();
		restaurants = new HashSet<Restaurant>();
		
		
	}
	
	/**
	 * 
	 * Creates set of attributes
	 * @throws FileNotFoundException 
	 * @throws JSONException 
	 */
	public void createAttributes() throws FileNotFoundException, JSONException {
		Scanner input = new Scanner(new File(file));
		JSONObject js = new JSONObject(input.nextLine());
		for (String s : JSONObject.getNames(js)) {
			JSONObject rest = js.getJSONObject(s);
			Iterator itr = rest.keys();
			while (itr.hasNext()) {
				String a = (String) itr.next();
				attributes.add(a);
			}
		}
		
	}
	
	/**
	 * Returns a set of Restaurants 
	 */
	
	public void getRestaurants () throws JSONException {
		int debug = 0; 
		JSONObject js = new JSONObject(console.nextLine());
		for (String name : JSONObject.getNames(js)) {
			JSONObject rest = js.getJSONObject(name);
			Restaurant r = new Restaurant(name);
			for (String s : attributes) {
				if (rest.has(s)) {
					String v = rest.getString(s);
					if (v.contains("&amp;")) {
						v =v.replace("&amp;", "and");
					}
					if (v.contains("'")) {
						v = v.replace("'","");
					}
					r.add(s,v);
				} else {
					r.add(s,"");
				}
			}
			restaurants.add(r);
			
		}
	
		
	}
	
	public void printRestaurants() {
		int debug = 0;
		for (Restaurant r : restaurants) {
			if (debug < 3) {
				System.out.println(r.name);
				debug++;
				for (String s : attributes) {
					System.out.print (s + ": ");
					System.out.println(r.getValue(s));
				}
				System.out.println();

			}
		}
		
	}

}
