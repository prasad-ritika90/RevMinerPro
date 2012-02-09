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
 * Extract place from all.metaData where the place is a restaurant, put those place into json object then onto disk
 */
public class RestaurantsJSONGenerator {
	
	private static final String SOURCE_FILE_NAME = "all.metaData";
	private static final String TARGET_FILE_NAME = "Restaurants.data";
	private static final String RESTAURANTS_REVIEW_FILE_NAME = "all.placeData";
	private static final String CATEGORY = "Category";
	private static final String KEY_WORD = "Restaurants";
	private static final String BUSINESS_TYPE = "Business Type";
	private static final String FOOD = "Food";
	
	private static JSONObject allData;
	private static JSONObject restaurantsReviews;

	public static void main(String[] args) throws JSONException, IOException{
		
		readJSONFiles();
		
		buildInfoJSONObject();

		outputFile();
		
	}

	//read the file from disk then put into memory
	private static void readJSONFiles() throws FileNotFoundException, JSONException {
		Scanner scn = new Scanner(new File(SOURCE_FILE_NAME));
		
		allData = new JSONObject(scn.nextLine());
		
		Scanner rReviewScn = new Scanner(new File(RESTAURANTS_REVIEW_FILE_NAME));
		StringBuilder sb2 = new StringBuilder();
		while(rReviewScn.hasNextLine()){
			sb2.append(rReviewScn.nextLine());
		}
		restaurantsReviews = new JSONObject(sb2.toString());
	}

	//build restaurants json
	private static void buildInfoJSONObject() throws JSONException {
		for(String name : JSONObject.getNames(allData)){
			JSONObject restaurant = allData.getJSONObject(name);
			String category = null;
			String businessType = null;
			try{
				//if the place do not have category, remove it
				category = restaurant.getString(CATEGORY);
				//if no reviews found, remove it
				if(restaurantsReviews.getJSONObject(name).length() < 1){
					allData.remove(name);
				}
			}catch (org.json.JSONException e){
				allData.remove(name);
			}
			try{
				businessType = restaurant.getString(BUSINESS_TYPE);
			}catch (org.json.JSONException e){
				
			}
			if(category != null && !category.contains(KEY_WORD) 
								&& !category.contains(FOOD)
								&& (businessType == null ? true : !businessType.equals(KEY_WORD)))
					allData.remove(name);
			
		}
	}

	//output restaurant to file
	private static void outputFile() throws JSONException, IOException {
		String s = allData.toString();
		FileWriter out = new FileWriter(TARGET_FILE_NAME);
		out.write(s);
		out.close();
	}
	
}
