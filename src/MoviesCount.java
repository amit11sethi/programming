import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class MoviesCount {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		   URL yahoo = new URL("https://jsonmock.hackerrank.com/api/movies/search/?Title=maze");
	        URLConnection yc = yahoo.openConnection();
	        BufferedReader in = new BufferedReader(
	                                new InputStreamReader(
	                                yc.getInputStream()));
	        String inputLine;
	        String json= "";

	        while ((inputLine = in.readLine()) != null) 
	            json = json + inputLine;
	        in.close();
	        
	        JSONParser parser = new JSONParser();
            JSONObject resultObject = (JSONObject)parser.parse(json);
            System.out.println(resultObject.get("total"));
   
	    }

}
