package timo;

public class PatternEscapeCharacter extends Pattern {
	
	protected PatternEscapeCharacter(String patternString) {
		super(patternString);
	}
	
	protected String getEndOfPattern() {
		return patternString.substring(firstIndexOfPatternSyntaxCharacter + 2);
	}
	
	@Override
	protected String getEndOfString(String someString) {
		return someString.substring(super.firstIndexOfPatternSyntaxCharacter + 1);
	}
	
	@Override
	public boolean matches(String someString) {
		if (!super.matches(someString)) {
			return false;
		}
		
		if (patternString.charAt(firstIndexOfPatternSyntaxCharacter + 1) 
				!= someString.charAt(firstIndexOfPatternSyntaxCharacter)) {
			return false;
		}
		
		String endOfPattern = getEndOfPattern();
		String endOfString = getEndOfString(someString);
		
		if ("".equals(endOfPattern)) {
			if ("".equals(endOfString)) {
				return true;
			} else {
				return false;
			}
		}
		
		return Pattern.getPattern(endOfPattern).matches(endOfString);
	}
}
