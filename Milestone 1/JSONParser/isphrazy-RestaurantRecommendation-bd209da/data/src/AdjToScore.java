import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 
 * @author Pingyang He
 * This class generates a json file that maps from adj to the score of this adj.
 * The better the word is, the higher the score is
 */
public class AdjToScore {
	
	private static final String ADJ_SOURCE_FILE_NAME = "all.polarityData";
	private static final String TARGET_FILE_NAME = "adjScore.data";
	
	private static JSONObject adjs;
	private static JSONObject adjScoreJSON;
	
	/**
	 * main method for the class
	 * @param args
	 * @throws JSONException
	 * @throws IOException
	 */
	public static void main(String[] args) throws JSONException, IOException{
		
		readJSONFiles();
		
		buildInfoJSONObject();

		outputFile();
		
	}

	/*
	 * read json files into memory;
	 */
	private static void readJSONFiles() throws FileNotFoundException, JSONException {
		Scanner adjScn = new Scanner(new File(ADJ_SOURCE_FILE_NAME));
		StringBuilder sb = new StringBuilder();
		while(adjScn.hasNext()){
			sb.append(adjScn.nextLine());
		}
		adjs = new JSONObject(sb.toString());
		adjScoreJSON = new JSONObject();
	}

	/*
	 * build the json mapping from adj to score.
	 */
	private static void buildInfoJSONObject() throws JSONException {
		for(String adj : JSONObject.getNames(adjs)){
			JSONArray scores = adjs.getJSONArray(adj);
			int score = 0;
			int total = 0;
			int offset = 2;
			for(int i = 0; i < scores.length(); i++){
				int partScore = scores.getInt(i);
				total += partScore;
				score += (i - offset) * partScore;
			}
			double finalScore = 1.0 * score / (offset * total);
			adjScoreJSON.put(adj, finalScore);
		}
		
	}
	
	/*
	 * output the result json onto disk
	 */
	private static void outputFile() throws IOException {
		FileWriter out = new FileWriter(TARGET_FILE_NAME);
		out.write(adjScoreJSON.toString());
		out.close();
	}
}
