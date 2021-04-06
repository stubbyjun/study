package rss.reader.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

public class XmlUtil {

	public static boolean isValidURL(String source) {

		try {

			URL url = new URL(source);
			url.toURI();
			return true;

		} catch (MalformedURLException e) {
			return false;
		} catch (URISyntaxException e) {
			return false;
		}
	}

	public static InputStream getInputStream(URL url) {

		InputStream stream = null;

		try {

			int redirectedCount = 0;

			while (redirectedCount <= 1) {

				HttpURLConnection.setFollowRedirects(false);

				HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();

				httpConn.setRequestMethod("GET");
				httpConn.setInstanceFollowRedirects(false);

				int resCode = httpConn.getResponseCode();

				if (resCode == HttpsURLConnection.HTTP_OK) {

					return url.openStream();

				} else if (resCode == HttpsURLConnection.HTTP_MOVED_TEMP
						|| resCode == HttpsURLConnection.HTTP_MOVED_PERM) {

					String redirectedUrl = httpConn.getHeaderField("Location");

					stream = new URL(redirectedUrl).openStream();

				} else
					throw new MalformedURLException(
							"cannot connect to the url [" + url.toString() + "] Code: " + resCode);

				++redirectedCount;
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		}

		return stream;
	}

	public static String makeString(Document document) {

		Transformer transformer;
		StringWriter writer = new StringWriter();

		try {
			transformer = TransformerFactory.newInstance().newTransformer();

			transformer.transform(new DOMSource(document), new StreamResult(writer));

		} catch (TransformerConfigurationException | TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}

		return writer.getBuffer().toString();
	}

}
