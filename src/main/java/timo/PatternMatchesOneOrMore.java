package timo;

public class PatternMatchesOneOrMore extends Pattern {
	
	protected PatternMatchesOneOrMore(String patternString) {
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
			if ("".equals(endOfString)) {
				return false;
			} else {
				return true;
			}
		}
		
		for (int i = 1; i < endOfString.length(); i++) {
			if (Pattern.getPattern(endOfPattern).matches(endOfString.substring(i))) {
				return true;
			}
		}
		
		return false;
	}
}
