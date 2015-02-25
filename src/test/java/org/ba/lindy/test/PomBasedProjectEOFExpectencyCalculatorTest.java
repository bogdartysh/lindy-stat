package org.ba.lindy.test;

import org.ba.lindy.PomBasedProjectEOFExpectencyCalculator;
import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.assertEquals;

public class PomBasedProjectEOFExpectencyCalculatorTest {
	PomBasedProjectEOFExpectencyCalculator pomCalc = new PomBasedProjectEOFExpectencyCalculator();

	@Test
	@Ignore
	public void testSelftPomInitial() {
		assertEquals(3d, pomCalc.outPutStatistics("self.mvn.dependency.tree", "self.mvn.dependency.tree.out"), 0.5d);
	}

}
