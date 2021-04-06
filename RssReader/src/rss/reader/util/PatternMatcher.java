package rss.reader.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternMatcher {

	
	public static String replaceAll(String regex, String replace, String contents) {
		
		
		Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
		
		Matcher matcher = pattern.matcher(contents);
		
		while (matcher.find()){
			
			contents = matcher.replaceAll(replace);
		} 
		
		return contents;
	}
}
