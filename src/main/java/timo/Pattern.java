package timo;

import java.util.Arrays;
import java.util.List;

public class Pattern {
	protected String patternString;
	protected int firstIndexOfPatternSyntaxCharacter;
	
	protected static final char MATCHES_EXACTLY_ONE = '?';
	protected static final char MATCHES_ONE_OR_MORE = '+';
	protected static final char MATCHES_ZERO_OR_MORE = '*';
	protected static final char ESCAPE_CHARACTER = '\\';
	
	private static final List<Character> PATTERN_SYNTAX_CHARACTERS = Arrays.asList(new Character[]{
			MATCHES_EXACTLY_ONE, MATCHES_ONE_OR_MORE, MATCHES_ZERO_OR_MORE, ESCAPE_CHARACTER});
	
	protected static Pattern getPattern(String patternString) {
		Character firstPatternSyntaxCharacter = getFirstPatternSyntaxCharacter(patternString);
		
		if (firstPatternSyntaxCharacter == null) {
			return new Pattern(patternString);
		} else if (firstPatternSyntaxCharacter == MATCHES_EXACTLY_ONE) {
			return new PatternMatchesExactlyOne(patternString);
		} else if (firstPatternSyntaxCharacter == MATCHES_ONE_OR_MORE) {
			return new PatternMatchesOneOrMore(patternString);
		} else if (firstPatternSyntaxCharacter == MATCHES_ZERO_OR_MORE) {
			return new PatternMatchesZeroOrMore(patternString);
		} else if (firstPatternSyntaxCharacter == ESCAPE_CHARACTER) {
			return new PatternEscapeCharacter(patternString);
		}
		
		throw new RuntimeException("Unknown pattern syntax character '" + firstPatternSyntaxCharacter + "'.");
	}
	
	public Pattern(String patternString) {
		this.patternString = patternString;
		this.firstIndexOfPatternSyntaxCharacter = getFirstIndexOfPatternSyntaxCharacter();
	}
	
	public String getPatternString() {
		return patternString;
	}
	
	protected final String getBeginOfPattern() {
		if (firstIndexOfPatternSyntaxCharacter < 0) {
			return patternString;
		}
		return patternString.substring(0, firstIndexOfPatternSyntaxCharacter);
	}
	
	protected String getEndOfPattern() {
		return patternString.substring(firstIndexOfPatternSyntaxCharacter + 1);
	}
	
	protected final String getBeginOfString(String someString) {
		if (firstIndexOfPatternSyntaxCharacter < 0 || someString.length() < firstIndexOfPatternSyntaxCharacter) {
			return someString;
		}
		return someString.substring(0, firstIndexOfPatternSyntaxCharacter);
	}
	
	protected String getEndOfString(String someString) {
		return someString.substring(firstIndexOfPatternSyntaxCharacter);
	}
	
	public boolean matches(String someString) {
		//System.out.println("\n----- pattern=" + patternString);
		//System.out.println("----- someString=" + someString);
		
		if (patternString == null && someString == null) {
			return true;
		} else if (patternString == null || someString == null) {
			return false;
		}
		
		return getBeginOfPattern().equals(getBeginOfString(someString));
	}
	
	private static Character getFirstPatternSyntaxCharacter(String patternString) {
		if (patternString == null || "".equals(patternString)) {
			return null;
		}
		
		int firstIndex = -1;
		Character firstPatternSyntaxCharacter = null;
		
		for (char c : Pattern.PATTERN_SYNTAX_CHARACTERS) {
			int index = patternString.indexOf(c);
			
			if (index >= 0) {
				if (firstIndex < 0 || index < firstIndex) {
					firstIndex = index;
					firstPatternSyntaxCharacter = c;
				}
			}
		}
		
		return firstPatternSyntaxCharacter;
	}
	
	private int getFirstIndexOfPatternSyntaxCharacter() {
		if (patternString == null || "".equals(patternString)) {
			return -1;
		}
		
		int firstIndex = -1;
		
		for (char c : Pattern.PATTERN_SYNTAX_CHARACTERS) {
			int index = patternString.indexOf(c);
			
			if (index >= 0) {
				if (firstIndex < 0 || index < firstIndex) {
					firstIndex = index;
				}
			}
		}
		
		return firstIndex;
	}
}
