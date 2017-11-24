package sunsetsunrise;

import java.io.*;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

/*Input: address, city, state, zipcode in string
 * 
 *Output: latitude, longitude in double
 *		  isValid in boolean
 */

public class GeoCode {

	private static final String URL = "http://www.mapquestapi.com/geocoding/v1/address";
	private static final String KEY = "YTS7K6QDGGpPtCIAySVlkU8dKR0ya5AI";
	private double latitude;
	private double longitude;
	private boolean flag;
	
	public GeoCode(String addr, String city, String state, String zipCode) {
		

		String fullURL = URL+"?key="+KEY+"&location="+addr.replace(' ', '+')+","+city.replace(' ','+')+","+state+","+zipCode+"&outFormat=csv";
		
		String responseStr = "";
		try {
			
			URL url = new URL(fullURL);
			URLConnection conn = url.openConnection();
			
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        String inputLine;
	        while ((inputLine = in.readLine()) != null) 
	        	responseStr+=inputLine;
	        in.close();
	        
			String[] response_arr = responseStr.split(",");
			int length = response_arr.length;
			String geoCodeQualityCode = response_arr[length-5].replace("\"", "");
			String displayLat = response_arr[length-2].replace("\"","");
			String displayLng = response_arr[length-1].replace("\"","");
			if (geoCodeQualityCode.charAt(2)=='A' && geoCodeQualityCode.charAt(3)=='A' && geoCodeQualityCode.charAt(4)=='A') {
				this.flag=true;
			} else {
				this.flag=false;
			}
			this.latitude=Double.parseDouble(displayLat);
			this.longitude=Double.parseDouble(displayLng);	        
	        
	        
		} catch (IOException e) {
			System.out.println("Fatal: address is not found");
		}



	}
	
	public double getLat() {
		return this.latitude;
	}
	
	public double getLng() {
		return this.longitude;
	}
	
	public boolean isValid() {
		return this.flag;
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String input = "1500 Vista Club Cir, Santa Clara, CA, 95054";
		AddressChecker ac = new AddressChecker(input);
		if (ac.isValid()){
			GeoCode gGC = new GeoCode(ac.getAddr(), ac.getCity(), ac.getState(), ac.getZipCode());
			if (gGC.flag) {
				System.out.println(gGC.latitude);
				System.out.println(gGC.longitude);
			} else {
				System.out.println("Error: Unable to found this address");
				
			}
		}
	}

}
