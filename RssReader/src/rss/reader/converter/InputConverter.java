package rss.reader.converter;

import rss.reader.cli.Conversion;

public abstract class InputConverter {

	private boolean cutOption = false;

	private boolean convertOption = false;

	public InputConverter(String[] options) {

		for (String option : options) {

			if (option.equals(Conversion.CUT.getCode())) {

				cutOption = true;
			}

			if (option.equals(Conversion.CONVERT.getCode())) {

				convertOption = true;
			}
		}
	}
	
	public boolean hasCutOption() {
		return cutOption;
	}

	public boolean hasConvertOption() {
		return convertOption;
	}
	
	public abstract String process(Object source);
	
	protected abstract String cutTitleLength(String contents);

	protected abstract String cutBodyLength(String contents);

	protected abstract String convertWord(String contents);

}
