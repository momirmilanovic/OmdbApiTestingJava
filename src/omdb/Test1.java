/**
 * 
 */
package omdb;

import static org.junit.Assert.*;

import java.util.logging.*;

import org.json.JSONException;
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import java.util.logging.*;

/**
 * @author momir
 *
 */
public class Test1 {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void runTest1() throws JSONException, ParseException, IOException {
		
		Logger LOGGER = Logger.getLogger( Test.class.getName() );
		
		InputStream input = null;
		Properties prop = new Properties();
		input = new FileInputStream("C:/Users/momir/eclipse-workspace/StemTest/src/omdb/config.properties");
		prop.load(input);
		int expected = Integer.parseInt(prop.getProperty("Test1Expected"));
		String movie = prop.getProperty("Test1Movie");
		String searchedTitle1 = prop.getProperty("Test1SearchedTitle1");
		String searchedTitle2 = prop.getProperty("Test1SearchedTitle2");
		
		ArrayList<Film> searchResults = new ArrayList<Film>();
	    omdbApiClient oac = new omdbApiClient();
	    
		// TEST 1	
	    LOGGER.log( Level.INFO, "Running TEST 1");
	    searchResults = oac.search(movie);
	    boolean containStems = false, contain1 = false, contain2 = false;
	    for (Film item : searchResults) {
	    	item.printFilm();
	    	if (item.getTitle().equals(searchedTitle1)) {
	    		contain1 = true;
	    	}
	    	if (item.getTitle().equals(searchedTitle2)) {
	    		contain2 = true;
	    	}
	    }
	    containStems = contain1 & contain2;
	    
		assertTrue("Result has less then 30 items", searchResults.size() >= expected);
		assertTrue("No titles " + searchedTitle1 + " and " + searchedTitle2 + "in results", containStems);
		
		
	}

}
