package sunsetsunrise;

import java.io.IOException;
import java.util.Scanner;

/*
 * Input: user's input
 * 
 * Output: string
 * 
 */
public class UserInput {
	
	
	private String userInput;
	
	public UserInput () {
		
		System.out.println("Please enter your full address: ");
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		userInput = reader.nextLine();
		reader.close(); 
		
	}
	
	public String getUserInput() {
		return userInput;
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		UserInput ui = new UserInput();
		//System.out.println(ui.getUserInput());
	}
}
