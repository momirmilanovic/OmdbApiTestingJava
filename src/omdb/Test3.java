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

import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class Test3 {

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
	public void test() throws IOException, ParseException {
		
		final Logger LOGGER = Logger.getLogger( Test.class.getName() );

		
		InputStream input = null;
		Properties prop = new Properties();
		input = new FileInputStream("C:/Users/momir/eclipse-workspace/StemTest/src/omdb/config.properties");
		prop.load(input);
	    String title = prop.getProperty("Test3Title");
	    String expectedInPlot = prop.getProperty("Test3ExpectedInPlot");
	    String expectedRuntime = prop.getProperty("Test3xpectedRuntime");
	    	    
	    omdbApiClient oac = new omdbApiClient();
		
	    LOGGER.log(Level.INFO, "Running TEST 3");

	    HashMap<String, String> hmTest3 = new HashMap<String, String>();
	    hmTest3 = oac.getByTitle(title);
	    //System.out.println("hmTest3: " + hmTest3);
	    assertTrue("Plot for movie " + title + " does not contain " + expectedInPlot, hmTest3.get("plot").contains(expectedInPlot));
	    assertEquals("Runtime of movie"  + title + " is not expectedRuntime" , hmTest3.get("runtime"), expectedRuntime);
	}

}
