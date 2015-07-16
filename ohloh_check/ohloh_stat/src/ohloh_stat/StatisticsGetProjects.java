package ohloh_stat;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.XMLGregorianCalendar;

import ohloh_stat.entity.jaxb.all.Response;
import ohloh_stat.entity.jaxb.all.Response.Result.Project;

public class StatisticsGetProjects {
	private static final String key = "APSEp7Gmx4metTn4uR0zwg";
	private static final int INITIAL_PAGE = 5404;

	public static void main(String[] args) {
		int i = INITIAL_PAGE;
		try {

			//https://www.openhub.net/projects.xml?api_key=APSEp7Gmx4metTn4uR0zwg&sort=updated_at
			JAXBContext jaxbContext = JAXBContext.newInstance(Response.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			try (PrintWriter pw = new PrintWriter("projects.overall.list")) {
				pw.println("id, name, activity, createdAt, updatedAt");
				for (; i < INITIAL_PAGE + 1000; i++) {
					System.out.println(i);
					try {
						HttpURLConnection conn =
								(HttpURLConnection) new URL("https://www.openhub.net/projects.xml?api_key=" + key + "&sort=updated_at&page=" + i)
										.openConnection();
						conn.setRequestMethod("GET");
						try (InputStream source = conn.getInputStream()) {
							Response response = (Response) jaxbUnmarshaller.unmarshal(source);

							for (Project project : response.getResult().getProject()) {
								pw.println(project.getId() + "," + project.getName().replace("\'", " ").replace(",", " ").replace("\"", " ") + "," + project.getProjectActivityIndex().getDescription()
										+ "," + getISO8601(project.getCreatedAt()) + "," + getISO8601(project.getUpdatedAt()));
							}
							pw.flush();
						}
					}
					catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.err.println("on i=" + i);
						break;
					}
				}
				pw.println("--------------------");
				pw.println("--------------------");
				pw.println("overall page is " + i);
			}
			catch (FileNotFoundException e) {
				e.printStackTrace();
				System.err.println("at i = " + i);
			}

		}
		catch (JAXBException e) {
			e.printStackTrace();
			System.err.println("at i = " + i);
		}

	}

	private static String getISO8601(XMLGregorianCalendar date) {
		if (date == null) {
			return null;
		}

		return date.getYear() + "-" + date.getMonth() + "-" + date.getDay();
	}
}
