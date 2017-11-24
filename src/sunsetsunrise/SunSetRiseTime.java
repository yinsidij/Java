package sunsetsunrise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/*
 * Input: latitude, longitude in double
 * 
 * Output: sunrise, sunset times (PST)
 * 
 */
public class SunSetRiseTime {
	
	private Double lat;
	private Double lng;
	private static final String URL = "https://api.sunrise-sunset.org/json";
	
	private String response;
	
	private String sunset;
	private String sunrise;
	private String status;
	
	private String sunsetInPST;
	private String sunriseInPST;
	
	public SunSetRiseTime(Double lat, Double lng) {
		
		this.lat=lat;
		this.lng=lng;
		String fullURL = URL+"?lat="+lat+"&lng="+lng+"&date=today&formatted=1";
			
		try {
			
			URL url = new URL(fullURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			if (conn.getResponseCode() != 200) {
				throw new IOException (conn.getResponseMessage());	
			}
			
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        StringBuilder sb = new StringBuilder();
			String inputLine;
			
	        while ((inputLine = rd.readLine()) != null) 
	        	sb.append(inputLine);
	        rd.close();
	        
	        conn.disconnect();
	        
	        response = sb.toString();
	        //System.out.println(response);
	        
		} catch (IOException e) {
			e.printStackTrace();
		}
		//parse response
		try {
			Object obj = new JSONParser().parse(response);
			JSONObject jo = (JSONObject) obj;
			Map results = ((Map) jo.get("results"));
			sunrise = (String) results.get("sunrise");
			sunset = (String) results.get("sunset");
			status = (String) jo.get("status");
			
			if (status.equals("OK")) {	
				sunriseInPST= UTC2PST(this.sunrise);
				sunsetInPST = UTC2PST(this.sunset);				
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		
	}
	
	public String UTC2PST(String timeInUTC) {
		
		
		
		//System.out.println(timeInUTC);		
		//format 1
		DateFormat utcFormat = new SimpleDateFormat("hh:mm:ss a");
		utcFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		
		
		//format 0
		//SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd'T'HH:mm:ss+00:00");
		//formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
		
		Date date = null;
		
		try {
			date = utcFormat.parse(timeInUTC);
			//date = formatter.parse(timeInUTC);
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//format 1
		DateFormat pstFormat = new SimpleDateFormat("hh:mm:ss a");
		pstFormat.setTimeZone(TimeZone.getTimeZone("PST"));
		
		//format 0
		//formatter.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
		//System.out.println(formatter.format(date));
		
		//System.out.println(pstFormat.format(date));
		return pstFormat.format(date);
	}
	
	
	
	public double getLat() {
		return this.lat;
	}
	
	public double getLng() {
		return this.lng;
	}
	
	public String getSunSetTimeInPST() {
		return this.sunsetInPST;
	}
	
	public String getSunRiseTimeInPST() {
		return this.sunriseInPST;
	}
	
	public String getSunSetTimeInUCT() {
		return this.sunset;
	}
	public String getSunRiseTimeInUCT() {
		return this.sunrise;
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		SunSetRiseTime time = new SunSetRiseTime(37.402375, -121.953793);
		
	}
}
