package omdb;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;

public class Film {
	
	private String title;
	private String year;
	private String imdbId;
	private String type;
	private final static Logger LOGGER = Logger.getLogger(Test.class.getName());

		
	public Film(String title, String year, String imdbId, String type) {
		this.title = title;
		this.year = year;
		this.imdbId = imdbId;
		this.type = type;

	}
	
	String getTitle() {
		return this.title;
	}
	
	String getYear() {
		return this.year;
	}
	
	String getImdbId() {
		return this.imdbId;
	}
	
	String getType() {
		return this.type;
	}
	
	
	public void printFilm() {
		
		//System.out.println("FILM title: " + this.title + ", year: " + this.year + ", imbdId: " + this.imdbId + ", type: " + this.type);
		LOGGER.log(Level.INFO, "FILM title: " + this.title + ", year: " + this.year + ", imbdId: " + this.imdbId + ", type: " + this.type);
		
	}
	 
	

}
