package rss.reader.parsing;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import rss.reader.converter.InputConverter;
import rss.reader.converter.XmlInputConverter;
import rss.reader.util.XmlUtil;

public class XmlParser implements InputParser<Document> {

	private String url;
	private InputConverter converter;

	public XmlParser(String url) {

		super();
		this.url = url;
	}

	@Override
	public Document parse() {

		Document document = null;
		InputStream is;
		DocumentBuilder builder;

		try {
			is = XmlUtil.getInputStream(new URL(this.url));

			builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

			document = builder.parse(is);

			document.getDocumentElement().normalize();
						
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		}

		return document;
	}

	@Override
	public InputConverter getConverter() {

		return converter;
	}

	@Override
	public void setConverter(String[] options) {
		
		converter = new XmlInputConverter(options);		
	}

}
