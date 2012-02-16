import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 
 * @author Pingyang He
 * This class generates a json file that maps from category to restaurants which contain the category
 */
public class CategoriesJSONGenerator {
	
	private static final String SOURCE_FILE_NAME = "Restaurants.data";
	private static final String TARGET_FILE_NAME = "Category.data";
	private static final String CATEGORY = "Category";
	private static final String KEY_WORD = "Restaurants";
	
	private static JSONObject allData;
	
	public static void main(String[] args) throws JSONException, IOException{
		
		readJSONFiles();
		
		if(allData != null){
			
			Map<String, LinkedList<String>> map = buildInfoJSONObject();
			
			outputFile(map);
			
		}
	}

	//read json files from disk to memory
	private static void readJSONFiles() throws FileNotFoundException {
		Scanner scn = new Scanner(new File(SOURCE_FILE_NAME));
		allData = null;
		StringBuilder sb = new StringBuilder();
		while(scn.hasNext()){
			sb.append(scn.nextLine());
		}
		try{
			allData = new JSONObject(sb.toString());
			
		}catch (Exception e){
			e.printStackTrace();
		}		
	}

	//build json that maps from category to restaurants list, which contains that category
	private static Map<String, LinkedList<String>> buildInfoJSONObject() throws JSONException {
		Map<String, LinkedList<String>> map = new HashMap<String, LinkedList<String>>();
		for(String name : JSONObject.getNames(allData)){
			JSONObject restaurant = allData.getJSONObject(name);
			String[] categoryList = restaurant.getString(CATEGORY).split(", *");
			for(String category : categoryList){
				//we don't want the word "restaurants", since it's in all of them
				if(!category.equals(KEY_WORD)){
					if(map.containsKey(category)){
						((LinkedList<String>) map.get(category)).add(name);
					}else{
						LinkedList<String> list = new LinkedList<String>();
						list.add(name);
						map.put(category, list);
					}
				}
			}
		}
		return map;
	}

	//outprint given map json to disk
	private static void outputFile(Map<String, LinkedList<String>> map) throws IOException {
		JSONObject categoryJSON = new JSONObject(map);
		FileWriter out = new FileWriter(TARGET_FILE_NAME);
		out.write(categoryJSON.toString());
		out.close();
	}
	
}
