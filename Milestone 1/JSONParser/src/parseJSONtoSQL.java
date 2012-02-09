import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.json.JSONException;


/**
 * 
 * parses JSON to SQL
 *
 */



public class parseJSONtoSQL {
	
	static final String INPUT_FILE = "Restaurants3.data";
	
	public static void main(String[] args) throws JSONException, FileNotFoundException {
		
		JSONParser jp = new JSONParser(INPUT_FILE);
		jp.createAttributes();
		System.out.println("Done with this");
		jp.getRestaurants();
		//jp2.printRestaurants();
		SQLConverter sql = new SQLConverter(jp.restaurants,jp.attributes);
		System.out.println(jp.restaurants.size());
		sql.createTable();
		sql.insert();
		
	}
	

}
