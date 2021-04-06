package rss.reader.converter;

public class FileInputConverter extends InputConverter {

	public FileInputConverter(String[] option) {
		super(option);
	}

	@Override
	public String process(Object source) {
		
		String contents = (String) source;

		if (this.hasCutOption()) {

			contents = cutTitleLength(contents);

			contents = cutBodyLength(contents);
		}

		if (this.hasConvertOption()) {

			contents = convertWord(contents);
		}

		return contents;
	}

	@Override
	protected String convertWord(String contents) {

		return contents.replaceAll("ユーザベース", "UZABASE");
	}

	@Override
	protected String cutTitleLength(String contents) {

		return contents.replaceAll("(?<=title:\\s.{10})(.)*", "");
	}

	@Override
	protected String cutBodyLength(String contents) {

		return contents.replaceAll("(?<=body:\\s.{30})(.)*", "");
	}
}
