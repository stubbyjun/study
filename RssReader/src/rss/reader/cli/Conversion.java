package rss.reader.cli;

public enum Conversion {

	CONVERT("convert"), CUT("cut");
	
	private String code;
	
	Conversion(String code) {
		
		this.code = code;
	}

	public String getCode() {

		return this.code;
	}

}
