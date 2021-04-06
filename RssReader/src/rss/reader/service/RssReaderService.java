package rss.reader.service;

import java.io.IOException;
import java.util.Map;

import rss.reader.cli.Flag;
import rss.reader.parsing.InputParser;
import rss.reader.util.FileUtil;

public class RssReaderService {

	public void run(Map<String, Object> options) {

		InputParser<?> parser = (InputParser<?>) options.get(Flag.INPUT.name());
		
		parser.setConverter((String []) options.get(Flag.CONVERSION.name()));
		
		Object parsedObj = parser.parse();
		
		String contents = parser.getConverter().process(parsedObj);
										
		output(contents, (String) options.get(Flag.OUTPUT.name()));
	}
		
	public void output(String contents, String fileName) {

		//file output
		if (fileName != null) {

			try {
				FileUtil.write(fileName, contents);
				System.out.println("Saved to " + fileName);

			} catch (IOException e) {

				System.err.println("output failed!");
			}
		} else {

			//system print
			System.out.println(contents);
		}
		
	}
}
