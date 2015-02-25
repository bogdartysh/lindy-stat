package org.ba.lindy.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import lombok.val;

import org.ba.lindy.DependencyEOFExpectencyCalculator;
import org.junit.Test;

import org.junit.Ignore;

public class TestDependencyEOFExpectencyCalculator {
	DependencyEOFExpectencyCalculator calculator = new DependencyEOFExpectencyCalculator();

	@Test
	public void getUrlShouldReturnNull() {
		assertNull(DependencyEOFExpectencyCalculator.getRestUrl("[INFO] org.ba.lindy:lindy-stat:jar:1.0-SNAPSHOT"));
		assertNull(DependencyEOFExpectencyCalculator.getRestUrl("[INFO] +- junit:junit:jar:3.8.1:test"));
		assertNull(DependencyEOFExpectencyCalculator.getRestUrl("[INFO] +- junit:junit:war:3.8.1:compile"));
	}

	@Test
	public void getUrlShouldReturnCorrectUrl() {
		val expectedUrl =
				"http://search.maven.org/solrsearch/select?q=g:%22com.google.guava%22+AND+a:%22guava%22&core=gav&rows=200000000&wt=json";
		assertEquals(expectedUrl, DependencyEOFExpectencyCalculator.getRestUrl("[INFO] +- com.google.guava:guava:jar:18.0:compile"));
		assertEquals(expectedUrl, DependencyEOFExpectencyCalculator.getRestUrl("[INFO] |  +- com.google.guava:guava:jar:12318.0-beta:compile"));
		assertEquals(expectedUrl, DependencyEOFExpectencyCalculator.getRestUrl("[INFO] |  |  \\- com.google.guava:guava:jar:1.18.0:compile"));
		assertEquals(expectedUrl, DependencyEOFExpectencyCalculator.getRestUrl("[INFO] |     \\- com.google.guava:guava:jar:23.0:compile"));
	}

	@Test
	@Ignore
	public void getMaxMinDatesShouldBeOfSameOrder() {
		Long[] dates = calculator.getExpectedEOFDependency("[INFO] +- com.google.guava:guava:jar:18.0:compile");
		assertTrue(dates[1] > dates[0]);
	}
}
