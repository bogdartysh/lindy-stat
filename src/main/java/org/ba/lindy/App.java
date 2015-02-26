package org.ba.lindy;

import lombok.NoArgsConstructor;
import lombok.val;

@NoArgsConstructor
public class App {
	static PomBasedProjectEOFExpectencyCalculator pomCalculator = new PomBasedProjectEOFExpectencyCalculator();

	public static void main(String[] args) {
		for (String arg : args) {
			System.out.println(arg);
		}

		if (!"tree".equals(args[0].trim())) {
			System.err.print("first arg should be a valid project type " + args[0]);
			System.exit(-1);
		}

		double result = pomCalculator.outPutStatistics(args[1], args[2]);
		System.out.println(result);
		System.exit((int)result);
	}

}
