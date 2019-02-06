package omdb;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

//import org.json.*;
import org.junit.Test;


import org.json.simple.JSONObject;
import org.json.simple.*;
import org.json.JSONException;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class omdbApiClient {
	
	private final static Logger LOGGER = Logger.getLogger(Test.class.getName());
	private static String apiKey = "";
	private static String omdb = "";
	
	public omdbApiClient() throws IOException {
		InputStream input = null;
		Properties prop = new Properties();
		try {
			input = new FileInputStream("C:/Users/momir/eclipse-workspace/StemTest/src/omdb/config.properties");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.log( Level.SEVERE, "Cannot find config.properties file!");
		}
		prop.load(input);
		apiKey=prop.getProperty("API_KEY");
		omdb=prop.getProperty("OMDB");
		LOGGER.log( Level.INFO, "OMDB API CLIENT\napiKey:" + apiKey);
	}
	
	protected ArrayList<Film> search(String s) throws JSONException, ParseException, IOException {
			
		ArrayList<Film> allFilms = new ArrayList<Film>();
		String omdbQuery = omdb + "s=" + URLEncoder.encode(s, "UTF-8") + "&apikey=" + apiKey;
		
		LOGGER.log(Level.INFO, "omdbApiClient SEARCH funtion");
			
		int pages = pagination(omdbQuery);
		for(int i=1;i<=pages;i++) {
			String omdbQueryPage  = omdbQuery + "&page=" + i;
			//System.out.println("omdbQueryPage: " + omdbQueryPage);
			JSONObject json = omdbQuery(omdbQueryPage);
			JSONArray search = (JSONArray) json.get("Search");
			//System.out.println("search " + i + ": " + search); DELETE
			  for(int j=0;j<search.size();j++)
			  {
				  JSONObject movie = (JSONObject) search.get(j);
				  String title = (String) movie.get("Title");
				  String year = (String) movie.get("Year");
				  String type = (String) movie.get("Type");
				  String imdbId = (String) movie.get("imdbID");
				  Film film = new Film(title, year, imdbId, type);
				  //film.printFilm();
				  allFilms.add(film);			  
			  }
			
		}
					
		return allFilms;
	}
	
	
	protected HashMap<String, String> getById(String id) throws IOException, ParseException {
		
		/*
		* Creates omdbQuery and parse results to get released and director values, and set this values as values in hashmap which is return value
		*/
		String omdbIdQuery = omdb + "i=" + URLEncoder.encode(id, "UTF-8") + "&apikey=" + apiKey;
		HashMap<String, String> hmId = new HashMap<String, String>();
		JSONObject json = omdbQuery(omdbIdQuery);
		String released = (String) json.get("Released");
		String director = (String) json.get("Director");
		//System.out.println("omdbIdQuery in  getById" + omdbIdQuery + "; released: " + released + ", director: " + director);
			
		hmId.put("released", released);
		hmId.put("director", director);
		
		LOGGER.log(Level.INFO, "omdbApiClient getById funtion; film id: " + id + ", hmId: " + hmId);
		
		return hmId;
		
	}

	protected HashMap<String, String> getByTitle(String title) throws IOException, ParseException {
		
		/*
		* Creates omdbQuery and parse results to get plot and director runtime values, and set this values as values in hashmap which is return value
		*/
		String omdbTitleQuery = omdb + "t=" + URLEncoder.encode(title, "UTF-8") + "&apikey=" + apiKey;
		HashMap<String, String> hmTitle = new HashMap<String, String>();
		
		JSONObject json = omdbQuery(omdbTitleQuery);
		String plot = (String) json.get("Plot");
		String runtime = (String) json.get("Runtime");
		
		hmTitle.put("plot", plot);
		hmTitle.put("runtime", runtime);
		
		LOGGER.log(Level.INFO, "omdbApiClient getByTitle funtion; film title: " + title + ", hmTitle: " + hmTitle);
		
		return hmTitle;
		
	}
	
		
	private int pagination(String omdbQuery) throws IOException, ParseException {
			
		JSONObject json = omdbQuery(omdbQuery);
		String totalResults = (String) json.get("totalResults");	
		int results  = (int)Integer.parseInt(totalResults);
		int pages = 0;
		if (results % 10 == 0) {
			pages = results/10;
		} else {
			pages = results/10 + 1;
		}
		//System.out.println("totalResults: " + totalResults + ", pages: " + pages);
					
		return pages;
	}
	
	private JSONObject omdbQuery(String omdbQuery) throws IOException, ParseException {
		
		LOGGER.log(Level.INFO, "omdbQuery f-ntion omdbQuery: " + omdbQuery);
		URL omdbUrl = new URL(omdbQuery);
		
		HttpURLConnection omdbConnection = (HttpURLConnection) omdbUrl.openConnection();
		omdbConnection.setRequestMethod("GET");
		omdbConnection.setRequestProperty("Accept", "*/*");	
		omdbConnection.setRequestProperty("Content-type", "application/json; charset=UTF-8");	
		InputStream stream = omdbConnection.getInputStream();
		InputStreamReader reader = new InputStreamReader(stream);
		BufferedReader buffer = new BufferedReader(reader);
		String line;
		StringBuffer response = new StringBuffer();
		while ((line = buffer.readLine()) != null) {
			response.append(line);
		}
	    //System.out.println("response.toString is: " + response.toString());		      
	    JSONParser parser = new JSONParser();
	    JSONObject json = (JSONObject) parser.parse(response.toString());	
	    
	    buffer.close();
	    omdbConnection.disconnect();
	    
	    return json;
		
		
		
	}
	
	
	
}
