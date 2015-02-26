package org.ba.lindy;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.Date;

import lombok.val;
import lombok.extern.java.Log;

import org.apache.commons.math3.stat.descriptive.SummaryStatistics;

/**
 * based on http://www.johndcook.com/blog/2012/12/17/the-lindy-effect/	, https://en.wikipedia.org/wiki/Lindy_Effect
 * @author bartyushenko
 */
@Log
public class PomBasedProjectEOFExpectencyCalculator {
	private final DependencyEOFExpectencyCalculator calculator = new DependencyEOFExpectencyCalculator();
	private FileSystem fs = FileSystems.getDefault();

	public double outPutStatistics(String inputPathUrl, String outPutPathUrl) {
		val currentDate = (new Date()).getTime();
		val inputPath = fs.getPath(inputPathUrl);
		val outoutPath = fs.getPath(outPutPathUrl);
		val minDates = new SummaryStatistics();
		try (val writer = Files.newBufferedWriter(outoutPath, Charset.defaultCharset())) {
			writer.write("dependency data");
			writer.write("dependency,first_version_date,last_version_date\n");
			for (val line : Files.readAllLines(inputPath, Charset.defaultCharset())) {
				val dates = calculator.getExpectedEOFDependency(line);
				if (dates != null && dates[0] != 0 && dates[1] != 0) {
					writer.write(getOutPut(line, dates));
					minDates.addValue(currentDate - dates[0]);
				}
			}
			writer.write("\n");
			writer.write("\n");
			writer.write("\n");
			writer.write("expected EOF in years\n");
			writer.write("min,mean,max,std_dev,number_of_deps\n");
			writer.write(getCommonStat(minDates));
			return  toYears(minDates.getMean()) ;
		}
		catch (IOException e) {
			log.severe(e.getMessage());
		}
		return -1;

	}
	
	public static double toYears(final double jsDate) {
		return  jsDate /  (365*86400000d);
	}

	public static String getCommonStat(final SummaryStatistics summary) {
		return  toYears(summary.getMin()) + "," //
				+ toYears(summary.getMean()) + "," //
				+ toYears(summary.getMax()) + "," //
				+ toYears(summary.getStandardDeviation()) + "," //
				+ summary.getN() + " \n" //
		;
	}

	public static String getOutPut(String dependency, Long[] dates) {
		return "\"" + DependencyEOFExpectencyCalculator.getExtractedFromPom(dependency) + "\"," + new Date(dates[0]) + "," + new Date(dates[1]) + "\n";
	}
}
