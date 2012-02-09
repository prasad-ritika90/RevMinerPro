import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 
 * @author Pingyang He
 * this class generates a light database the will be used for searching restaurant. 
 */
public class SearchDatabaseGenerator {
	
	private static final String SOURCE_FILE_NAME = "Restaurants.data";
	private static final String TARGET_FILE_NAME = "SearchDatabase.data";
	private static final String BUSINESS_NAME = "Business Name";
	private static final String ADDRESS = "Address";
	
	private static JSONObject restaurants;
	
	public static void main(String[] args) throws JSONException, IOException{
		
		readJSONFiles();
		
		JSONObject resultJSON = buildInfoJSONObject();

		outputFile(resultJSON);
		
	}

	//read file from disk then add to memory
	private static void readJSONFiles() throws FileNotFoundException, JSONException {
		Scanner scn = new Scanner(new File(SOURCE_FILE_NAME));
		StringBuilder sb2 = new StringBuilder();
		while(scn.hasNextLine()){
			sb2.append(scn.nextLine());
		}
		
		restaurants = new JSONObject(sb2.toString());
	}

	//build search database
	private static JSONObject buildInfoJSONObject() throws JSONException {
		
		JSONObject resultJSON = new JSONObject();
		for(String name : JSONObject.getNames(restaurants)){
			JSONObject detail = new JSONObject();
			JSONObject restaurant = restaurants.getJSONObject(name);
			detail.put(BUSINESS_NAME, (String)restaurant.get(BUSINESS_NAME));
			detail.put(ADDRESS, (String)restaurant.get(ADDRESS));
			
			resultJSON.put(name, detail);
		}
		return resultJSON;
	}

	//output the search database to file
	private static void outputFile(JSONObject resultJSON) throws IOException {
		FileWriter out = new FileWriter(TARGET_FILE_NAME);
		out.write(resultJSON.toString());
		out.close();
	}
}
