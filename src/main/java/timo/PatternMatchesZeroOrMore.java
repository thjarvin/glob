package timo;

public class PatternMatchesZeroOrMore extends Pattern {
	
	protected PatternMatchesZeroOrMore(String patternString) {
		super(patternString);
	}
	
	@Override
	public boolean matches(String someString) {
		if (!super.matches(someString)) {
			return false;
		}
		
		String endOfPattern = getEndOfPattern();
		String endOfString = getEndOfString(someString);
		
		if ("".equals(endOfPattern)) {
			return true;
		}
		
		for (int i = 0; i < endOfString.length(); i++) {
			if (Pattern.getPattern(endOfPattern).matches(endOfString.substring(i))) {
				return true;
			}
		}
		
		return false;
	}
}
