package omdb;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONException;
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.logging.*;

public class Test2 {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws JSONException, ParseException, IOException {
		
		final Logger LOGGER = Logger.getLogger( Test.class.getName() );
		
		LOGGER.log( Level.INFO, "Running TEST 2");
		
		InputStream input = null;
		Properties prop = new Properties();
		input = new FileInputStream("C:/Users/momir/eclipse-workspace/StemTest/src/omdb/config.properties");
		prop.load(input);
		String expectedReleased = prop.getProperty("Test2ExpectedReleased");
		String expectedDirector = prop.getProperty("Test2ExpectedDirector");
		String searchedTitle = prop.getProperty("Test2SearchedTitle");
		String search = prop.getProperty("Test1Movie");

		ArrayList<Film> searchResults = new ArrayList<Film>();
	    omdbApiClient oac = new omdbApiClient();		
	    searchResults = oac.search(search);
		String id = "", title = "";
	    for (Film item : searchResults) {
	    	if (item.getTitle().equals(searchedTitle)) {
	    		//item.printFilm();
	    		id = item.getImdbId();
	    		title = item.getTitle();
	    		
	    	}
	    }
	    
	    //System.out.println("id:" + id + " for film " + title);
	    HashMap<String, String> hmTest2 = new HashMap<String, String>();
	    hmTest2 = oac.getById(id);
	    //System.out.println("hmTest2: " + hmTest2);
	    assertEquals("Movie " + title + " not released on " + expectedReleased, hmTest2.get("released"), expectedReleased);
	    assertEquals(expectedDirector + " is not director of " + title, hmTest2.get("director"), expectedDirector);
	    
	}

}
