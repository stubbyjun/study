package rss.reader.cli;

import java.util.*;

import rss.reader.parsing.FileParser;
import rss.reader.parsing.InputParser;
import rss.reader.parsing.XmlParser;
import rss.reader.util.FileUtil;
import rss.reader.util.XmlUtil;

public class CommandLine {

	public Map<String, Object> getValidOptions(String[] args) {

		Map<String, Object> validOptions = new HashMap<>();

		if (args.length == 0 || args.length % 2 != 0)
			throw new IllegalArgumentException("usage: -i <input source> -c <conversion option> -o <output filename>");

		for (int i = 0; i < args.length; i += 2) {

			String option = args[i];
			String value = args[i + 1];

			if (value.charAt(0) == '-')
				throw new IllegalArgumentException("wrong input order!");

			Flag validOption = EnumSet.allOf(Flag.class)
					.stream()
					.filter(item -> item.getCode().equals(option))
					.findFirst().orElseThrow(() -> new IllegalArgumentException("Invalid flag!"));

			Object validValue = getValueObject(validOption.name(), value.trim());

			validOptions.put(validOption.name(), validValue);
		}

		return validOptions;
	}

	private Object getValueObject(String option, String value) {

		Object obj;

		if (option.equals(Flag.INPUT.name())) {

			obj = getInputParser(value);

		} else if (option.equals(Flag.CONVERSION.name())) {

			obj = getConversionOption(value);

		} else {

			obj = value;
		}

		return obj;
	}

	private String[] getConversionOption(String parameter) {

		String[] options = parameter.split(",");

		for (String option : options) {

			if (!(option.equals(Conversion.CUT.getCode()) || option.equals(Conversion.CONVERT.getCode()))) {

				throw new IllegalArgumentException("Invalid option for -c flag: choose cut or convert");
			}
		}

		return options;
	}

	private InputParser<?> getInputParser(String source) {

		InputParser<?> parser = null;

		if (XmlUtil.isValidURL(source)) {

			parser = new XmlParser(source);

		} else if (FileUtil.isValidFile(source)) {

			parser = new FileParser(source);

		} else {

			throw new IllegalArgumentException("Invalid url or file source");
		}

		return parser;
	}

}
