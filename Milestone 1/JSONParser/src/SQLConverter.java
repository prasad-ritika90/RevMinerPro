import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 
 * Converts to SQL format 
 *
 */
public class SQLConverter {
	
	Set<Restaurant> restaurants; //contains all restaurants
	PrintStream output;
	LinkedHashSet<String> attributes;
	
	SQLConverter(Set<Restaurant> r, LinkedHashSet<String> a) throws FileNotFoundException {
		restaurants = r;
		output = new PrintStream(new File("sql2.data"));
		attributes = a; 
	}
	
	/**
	 * Outputs create table command 
	 */
	public void createTable() {
		String[] s = attributes.toArray(new String[0]);
		output.print("CREATE TABLE restaurants(" + "Name" + " VARCHAR(100)");
		for (int i=0; i<s.length; i++) {
			s[i] = formatString(s[i]);
			output.print (", " + s[i] + " VARCHAR(100)");
		}
		output.println(");\n");
	}
	
	/**
	 * Outputs commands to insert into SQL database
	 */
	public void insert() {
		int debug = 0;
		for (Restaurant r : restaurants) {
			if (debug != -1) {
				output.print("INSERT INTO restaurants VALUES('" +r.name+"'");
				debug++;
				for (String s : attributes) {
					output.print (",'" + r.getValue(s) + "'");
				}
				output.println(");");

			}
		}
	}
	
	public String formatString(String s) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == ' ' || s.charAt(i) == '-') {
				sb.append("_");
			} else {
				sb.append(s.charAt(i));
			}
		}
		return sb.toString();
		
	}

}
