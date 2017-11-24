package sunsetsunrise;

import java.io.IOException;
/*
 * main: run program
 * 
 * output sunrise and sunset times in PST
 *        error out if inputs are not valid or geocode is not found
 * 
 */
public class AddressToSunRiseSetTime {
	
	public AddressToSunRiseSetTime () {
		
	}
	
	public void run() {
		UserInput ui = new UserInput();
		AddressChecker ac = new AddressChecker(ui.getUserInput());
		if (!ac.isValid()) {
			System.out.println("Please enter in 'address, city, state, zipcode' format");
			return;
		}
		GeoCode gc = new GeoCode(ac.getAddr(), ac.getCity(), ac.getState(), ac.getZipCode());
		if (!gc.isValid()) {
			System.out.println("Uable to locate your address");
			return;
		}

		SunSetRiseTime s = new SunSetRiseTime(gc.getLat(), gc.getLng());
		System.out.println("Sunrise time (PST): "+s.getSunRiseTimeInPST());
		System.out.println("Sunset  time (PST): " +s.getSunSetTimeInPST());
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		AddressToSunRiseSetTime a = new AddressToSunRiseSetTime();
		a.run();
		
	}

}
