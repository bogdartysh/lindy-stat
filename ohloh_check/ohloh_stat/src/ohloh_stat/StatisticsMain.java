package ohloh_stat;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class StatisticsMain {

	//private static final String[] list = {"3562", "29936"};
	private static final String key = "APSEp7Gmx4metTn4uR0zwg";

	public static void main(String[] args) {
		try (PrintWriter pw = new PrintWriter("outdated.tmp.csv")) {
			JAXBContext jaxbContext = JAXBContext.newInstance(ohloh_stat.entity.jaxb.Response.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			List<String> list = Files.readAllLines(FileSystems.getDefault().getPath("projects.list"), StandardCharsets.UTF_8 ); 
			for (String projectId : list) {
				StringBuilder strBuilder = new StringBuilder();
				try {

					HttpURLConnection conn =
							(HttpURLConnection) new URL("https://www.openhub.net/projects/" + projectId + ".xml?api_key=" + key)
									.openConnection();
					conn.setRequestMethod("GET");

					try (InputStream source = conn.getInputStream()) {

						ohloh_stat.entity.jaxb.Response response = (ohloh_stat.entity.jaxb.Response) jaxbUnmarshaller.unmarshal(source);
						if (response.getResult().getProject().getProjectActivityIndex().getValue() == 0
								|| response.getResult().getProject().getProjectActivityIndex().getValue() == 20) {

							if (response.getResult().getProject().getAnalysis() != null
									&& response.getResult().getProject().getAnalysis().getMaxMonth().getYear() > 2014) {
								continue;
							}

							strBuilder.append(projectId + ",");
							strBuilder.append(response.getResult().getProject().getName() + ",");
							strBuilder.append(response.getResult().getProject().getProjectActivityIndex().getValue() + ",");
							strBuilder.append(response.getResult().getProject().getCreatedAt().getYear() + ",");
							strBuilder.append(response.getResult().getProject().getUpdatedAt().getYear() + ",");

							if (response.getResult().getProject().getAnalysis() != null) {
								strBuilder.append(response.getResult().getProject().getAnalysis().getTwelveMonthCommitCount() + ",");
								strBuilder.append(response.getResult().getProject().getAnalysis().getMinMonth() + ",");
								strBuilder.append(response.getResult().getProject().getAnalysis().getMaxMonth() + ",");
							}
						}
					}

					pw.println(strBuilder.toString());
				}
				catch (Exception e1) {
					System.out.println(projectId + ",ERROR," + e1.getMessage());
				}
			}
		}
		catch (JAXBException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
