package org.ba.lindy;

import java.net.URL;
import java.util.Date;

import lombok.val;
import lombok.extern.java.Log;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Log
public class DependencyEOFExpectencyCalculator {
	private static final int maxNumberOfVersions = 200000000;
	private static final JsonParser gson = new JsonParser();

	public Long[] getExpectedEOFDependency(final String dependencyRecord) {
		Long min = (new Date()).getTime();
		Long max = 0L;

		try {
			val urlStr = getRestUrl(dependencyRecord);
			if (urlStr == null) {
				return null;
			}
			String resp = Resources.asCharSource(new URL(urlStr), Charsets.UTF_8).read();
			val pageJson = (JsonObject) gson.parse(resp);
			val elements = (((JsonObject) pageJson.get("response")).getAsJsonArray("docs")).iterator();
			while (elements.hasNext()) {
				val element = (JsonObject) elements.next();
				val timeStamp = element.get("timestamp").getAsLong();
				min = (min > timeStamp) ? timeStamp : min;
				max = (max < timeStamp) ? timeStamp : max;
			}
		}
		catch (Exception e) {
			log.severe(e.getMessage());
			return null;
		}
		log.info(dependencyRecord + "   " + min + "  " + max);
		return new Long[] { min, max, getLevel(dependencyRecord) };
	}

	public static String getRestUrl(final String input) {
		val dependencyArtifact = getExtractedFromPom(input);
		if (!input.endsWith("compile")) {
			log.info(input + " is not added at compile stage, skip it");
			return null;
		}

		if (!input.contains(":jar")) {
			log.info(input + "is not added a jar, skip it");
			return null;
		}

		val groupAndArtifact = dependencyArtifact.substring(0, dependencyArtifact.indexOf(":jar"));

		val groupId = groupAndArtifact.substring(0, groupAndArtifact.lastIndexOf(":")).replace(":", ".");
		val artifactId = groupAndArtifact.substring(groupAndArtifact.lastIndexOf(":") + ":".length());
		return "http://search.maven.org/solrsearch/select?q=g:%22" + groupId + "%22+AND+a:%22" + artifactId + "%22&core=gav&rows="
				+ maxNumberOfVersions + "&wt=json";
	}

	public static long getLevel(final String input) {
		return (input.indexOf("-") - "[INFO] ".length()) / 3;
	}

	public static String getExtractedFromPom(final String input) {
		return input.substring(input.indexOf("-") + 1).trim();
	}
}
