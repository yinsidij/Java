package sunsetsunrise;


import java.util.regex.Matcher;
import java.util.regex.Pattern;
/*	Input: string
 * 
 *  Output: address, city, state, zipcode in string after parsing input
 *          isValid is boolean
 */
public class AddressChecker {
	
	private String addr;
	private String city;
	private String state;
	private String zipCode;
	private boolean flag;
	
	
	public AddressChecker (String address) {

	    this.addr = "";
	    this.city = "";
	    this.state= "";
	    this.zipCode = "";	    
	    this.flag=false;
		
	    Pattern p = Pattern.compile("(.*)\\s*,\\s*(.*)\\s*,\\s*(.*)\\s*,\\s*(\\d+)");
	    Matcher m = p.matcher(address);		
	    if (m.find())
	    {
	     
	      this.addr = m.group(1);
	      this.city = m.group(2);
	      this.state= m.group(3);
	      this.zipCode = m.group(4);
	      
	      // 
	      //System.out.format("'%s', '%s', '%s', '%s'\n", addr, city, state, zipCode);
	      this.flag=true;
	    }
	    

	    
		
	}
	
	public String getAddr() {
		return this.addr;
	}
	
	public String getCity() {
		return this.city;
	}
	
	public String getState() {
		return this.state;
	}
	
	public String getZipCode() {
		return this.zipCode;
	}
	
	public boolean isValid() {
		return this.flag;
	}
}
