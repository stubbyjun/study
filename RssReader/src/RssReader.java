import java.util.*;

import rss.reader.cli.CommandLine;
import rss.reader.service.RssReaderService;

public class RssReader {

	public static void main(String[] args) {
	
		Map<String, Object> options;

		try {
			
			options = new CommandLine().getValidOptions(args);
						
		//	new RssReaderService().run(options);

		} catch (IllegalArgumentException e) {

			System.err.println(e.getMessage());
			return;
		}

	}
}
