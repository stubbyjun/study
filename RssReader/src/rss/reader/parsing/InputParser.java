package rss.reader.parsing;

import rss.reader.converter.InputConverter;

public interface InputParser<T> {

	public T parse();
	
	public void setConverter(String[] options);
	
	public InputConverter getConverter();
}
