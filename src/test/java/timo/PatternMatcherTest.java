package timo;

import static org.junit.Assert.*;

import org.junit.Test;

import timo.PatternMatcher;

public class PatternMatcherTest {
	
	@Test
	public void testNullMatchesNull() {
		runTrueTest(null, null);
	}
	
	@Test
	public void testDogDoesNotMatchNull() {
		runFalseTest("Dog", null);
	}
	
	@Test
	public void testNullDoesNotMatchDog() {
		runFalseTest(null, "Dog");
	}
	
	@Test
	public void testEmptyMatchesEmpty() {
		runTrueTest("", "");
	}

	@Test
	public void testDogMatchesDog() {
		runTrueTest("Dog", "Dog");
	}
	
	@Test
	public void testDogDoesNotMatchDogs() {
		runFalseTest("Dog", "Dogs");
	}
	
	@Test
	public void testLawMatchesLawStar() {
		runTrueTest("Law", "Law*");
	}
	
	@Test
	public void testLawyerMatchesLawStar() {
		runTrueTest("Lawyer", "Law*");
	}
	
	@Test
	public void testLoewMatchesLStarW() {
		runTrueTest("Loew", "L*w");
	}
	
	@Test
	public void testGrokLawDoesNotMatchLawStar() {
		runFalseTest("GrokLaw", "Law*");
	}
	
	@Test
	public void testLaDoesNotMatchLawStar() {
		runFalseTest("La", "Law*");
	}
	
	@Test
	public void testCatMatchesQuestionMarkAt() {
		runTrueTest("Cat", "?at");
	}
	
	@Test
	public void testBatMatchesQuestionMarkAt() {
		runTrueTest("bat", "?at");
	}
	
	@Test
	public void testLawDoesNotMatchLawPlus() {
		runFalseTest("Law", "Law+");
	}
	
	@Test
	public void testLawyerMatchesLawPlus() {
		runTrueTest("Lawyer", "Law+");
	}
	
	@Test
	public void testLawQuestionMarkMatchesLawEscapeQuestionMark() {
		runTrueTest("Law?", "Law\\?");
	}
	
	@Test
	public void testLawEscapeQuestionMarkDoesNotMatchLawEscapeQuestionMark() {
		runFalseTest("Law\\?", "Law\\?");
	}
	
	@Test
	public void testCatDoesNotMatchEscapeQuestionMarkAt() {
		runFalseTest("Cat", "\\?at");
	}
	
	@Test
	public void testQuestionMarkAtMatchesEscapeQuestionMarkAt() {
		runTrueTest("?at", "\\?at");
	}
	
	@Test
	public void testLawPlusMatchesLawPlus() {
		runTrueTest("Law+", "Law+");
	}
	
	@Test
	public void testLawPlusMatchesEscapeLawPlus() {
		runTrueTest("Law+", "Law\\+");
	}
	
	@Test
	public void testDiipaDaapaMatchesDiipStarA() {
		runTrueTest("diipadaapa", "diip*a");
	}
	
	@Test
	public void testDiipaDaapaMatchesLawStar() {
		runTrueTest("diipadaapa", "diip*a?a");
	}
	
	@Test
	public void testTmDoesNotMatchTPlusM() {
		runFalseTest("tm", "t+m");
	}
	
	@Test
	public void testTiberDoesNotMatchTiPlusBer() {
		runFalseTest("tiber", "ti+ber");
	}
	
	@Test
	public void testTimnoberBerMatchesTiPlusBer() {
		runTrueTest("timnober", "ti+ber");
	}
	
	@Test
	public void testTimnoberBerMatchesTiStarBer() {
		runTrueTest("timnober", "ti*ber");
	}
	
	@Test
	public void testTiberBerMatchesTiStarBer() {
		runTrueTest("tiber", "ti*ber");
	}
	
	private void runTrueTest(String someString, String pattern) {
		assertTrue(
			getShouldMatchText(someString, pattern), 
			(new PatternMatcher(pattern)).match(someString)
		);
	}
	
	private void runFalseTest(String someString, String pattern) {
		assertFalse(
			getShouldNotMatchText(someString, pattern), 
			(new PatternMatcher(pattern)).match(someString)
		);
	}
	
	private static String getShouldMatchText(String someString, String pattern) {
		return "String '" + someString + "' should match pattern '" + pattern + "'.";
	}
	
	private static String getShouldNotMatchText(String someString, String pattern) {
		return "String '" + someString + "' should not match pattern '" + pattern + "'.";
	}
}
