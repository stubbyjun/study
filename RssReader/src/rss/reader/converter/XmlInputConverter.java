package rss.reader.converter;

import java.util.stream.Stream;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import rss.reader.util.PatternMatcher;
import rss.reader.util.XmlUtil;

public class XmlInputConverter extends InputConverter {

	public XmlInputConverter(String[] option) {
		super(option);
	}

	@Override
	public String process(Object source) {
		
		Document document = (Document) source;
		
		NodeList itemList = document.getElementsByTagName("item");

		for (int i = 0; i < itemList.getLength(); i++) {

			Node node = itemList.item(i);

			if (node.getNodeType() == Node.ELEMENT_NODE) {

				if (this.hasCutOption()) {

					Element element = (Element) node;

					Node titleNode = element.getElementsByTagName("title").item(0);

					titleNode.setTextContent(cutTitleLength(titleNode.getTextContent()));

					Node bodyNode = element.getElementsByTagName("description").item(0);

					bodyNode.setTextContent(cutBodyLength(bodyNode.getTextContent()));
				}

				if (this.hasConvertOption()) {

					NodeList childrenList = node.getChildNodes();

					Stream.iterate(0, k -> k + 1)
					.limit(childrenList.getLength())
					.map(childrenList::item)	
					.forEach(child -> {
								child.setTextContent(convertWord(child.getTextContent()));
							});
				}
			}
		}

		return XmlUtil.makeString(document);
	}

	@Override
	protected String convertWord(String contents) {

		return PatternMatcher.replaceAll("ユーザベース", "UZABASE", contents);
	}

	@Override
	protected String cutTitleLength(String contents) {

		return PatternMatcher.replaceAll("(?<=.{10})(.)*", "", contents);
	}

	@Override
	protected String cutBodyLength(String contents) {

		return PatternMatcher.replaceAll("(?<=.{30})(.*)", "", contents);
	}
}
