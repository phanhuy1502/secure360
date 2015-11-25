package anormallyDetection;

public class FileType {
	private String fileName;
	private boolean malicious;
	public FileType (String name, boolean anormal) {
		fileName = name;
		this.malicious = anormal;
	}
	
	public boolean isMalicious() {
		return malicious;
	}
	
	public String getName() {
		return fileName;
	}
}
