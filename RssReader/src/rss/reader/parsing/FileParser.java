package rss.reader.parsing;

import java.io.IOException;

import rss.reader.converter.FileInputConverter;
import rss.reader.converter.InputConverter;
import rss.reader.util.FileUtil;

public class FileParser implements InputParser<String> {

	private String fileName;
	private InputConverter converter;

	public FileParser(String fileName) {
		super();
		this.fileName = fileName;
	}

	@Override
	public String parse() {

		String contents = null;

		try {
			contents = FileUtil.read(this.fileName);

		} catch (IOException e) {

			e.printStackTrace();
		}

		return contents;
	}

	@Override
	public InputConverter getConverter() {

		return converter;
	}

	@Override
	public void setConverter(String[] options) {

		converter = new FileInputConverter(options);
	}

}
