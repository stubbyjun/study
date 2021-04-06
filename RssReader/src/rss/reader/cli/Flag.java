package rss.reader.cli;

public enum Flag {

	INPUT("-i"), CONVERSION("-c"), OUTPUT("-o");

	private String code;

	Flag(String code) {

		this.code = code;
	}

	public String getCode() {

		return this.code;
	}

}
