package timo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PatternMatcher {
	
	private String pattern;
	
	public PatternMatcher(String pattern) {
		this.pattern = pattern;
	}
	
	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public boolean match(String someString) {
		return Pattern.getPattern(pattern).matches(someString);
	}
	
	public static void main(String[] args) {
		System.out.println("\nWELCOME TO GLOB PATTERN MATCHER!");
		System.out.println("\nThe glob pattern syntax is:");
		System.out.println("? matches any one character");
		System.out.println("+ matches one or more characters");
		System.out.println("* matches zero or more characters");
		System.out.println("\\ escapes special characters (i.e. ?, + or *)");
		
		Scanner reader = new Scanner(System.in);
		System.out.println("\nPlease enter a pattern (without spaces please): ");
		String pattern = reader.next();
		PatternMatcher patternMatcher = new PatternMatcher(pattern);
		
		List<String> matchedStrings = new ArrayList<>();
		List<String> notMatchedStrings = new ArrayList<>();
		List<String> errorStrings = new ArrayList<>();
		
		while (true) {
			System.out.println("\nPlease enter a string (without spaces please) or type 'quit' to exit the program: ");
			String someString = reader.next();
			
			if ("quit".equals(someString)) {
				System.out.println("\n+++++ RESULT SUMMARY +++++");
				
				if (matchedStrings.isEmpty() && notMatchedStrings.isEmpty() && errorStrings.isEmpty()) {
					System.out.println("You didn't enter any strings to be matched with pattern '" + pattern + "'.");
				} else {
					if (notMatchedStrings.size() > 0) {
						System.out.println("These strings didn't match pattern '" + pattern + "': " + notMatchedStrings);
					}
					
					if (matchedStrings.size() > 0) {
						System.out.println("These strings matched pattern '" + pattern + "': " + matchedStrings);
					} 
					
					if (errorStrings.size() > 0) {
						System.out.println("These strings caused an error: " + errorStrings 
								+ ". There may be something wrong with the pattern '" + pattern + "' as well.");
					}
				}
				
				System.out.println("\nGoodbye!");
				reader.close();
				System.exit(0);
			}
			
			try {
				boolean returnValue = patternMatcher.match(someString);
				
				if (returnValue) {
					matchedStrings.add(someString);
					System.out.println("\n+++++ RESULT: STRING '" + someString + "' MATCHES PATTERN '" + pattern + "' +++++");
				} else {
					notMatchedStrings.add(someString);
					System.out.println("\n+++++ RESULT: STRING '" + someString + "' DOES NOT MATCH PATTERN '" + pattern + "' +++++");
				}
			} catch (Throwable x) {
				errorStrings.add(someString);
				System.out.println("\nAn error has occured:");
				x.printStackTrace();
			}
		}
	}
}
