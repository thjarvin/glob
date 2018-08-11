package timo;

public class PatternMatchesExactlyOne extends Pattern {
	
	protected PatternMatchesExactlyOne(String patternString) {
		super(patternString);
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
		
		return Pattern.getPattern(getEndOfPattern()).matches(getEndOfString(someString));
	}
}
