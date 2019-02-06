/**
 * 
 */
package omdb;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

/**
 * @author momir
 *
 */
public class Check {

	/**
	 * @param args
	 * @throws ParseException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException, ParseException {
		// TODO Auto-generated method stub
		
		
		System.out.println("CHECK");
		String oq = "jebaga";
		System.out.println("strana: " + pagination(oq));
		
	}
	
	
	
	
	
	
	private static int pagination(String omdbQuery) throws IOException, ParseException {
		
		//JSONObject json = omdbQuery(omdbQuery);
		//String totalResults = (String) json.get("totalResults");
		String totalResults = "11";
		int results  = (int)Integer.parseInt(totalResults);
		int pages = 0;
		if (results % 10 == 0) {
			pages = results/10;
		} else {
			pages = results/10 + 1;
		}
		System.out.println("totalResults: " + totalResults + ", pages: " + pages);
					
		return pages;
	}

}
