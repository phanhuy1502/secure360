package injectionDetection;

import injectionDetection.Config.InjectionType;

public class Injection {
	private InjectionType type;
	private String line;
	private String url;
	private String header;
	private String[] value;
	
	public Injection(InjectionType t, String line, String url, String header, String[] value)  {
		this.type = t;
		this.line = line;
		this.url = url;
		this.header = header;
		this.value = value;
	}
	
	public String toString() {
		String returnString;
		returnString = "Injection found at line " + line + "\ntype: " + type.toString() 
		+ "\nurl: " + url + "\nheader: " + header 
		+ "\nvalue before: " + value[0]
		+ "\nvalue: " + value[1]
		+ "\nvalue after: " + value[2];
		return returnString;
	}

	public String getType() {
		switch (type) {
			case WindowsCmdInjection: 
				return "Windows Injection";
			case SQLInjection: 
				return "SQL Injection";
			case UnixCmdInjection: 
				return "Unix Injection";
			default: 
				return "";
		}
	}

	public String getLineNumber() {
		return line;
	}

	public String getURL() {
		return url;
	}

	public String getHeader(){
		return header;
	}

	public String[] getValue() {
		return value;
	}
}