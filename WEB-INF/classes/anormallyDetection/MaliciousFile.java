package anormallyDetection;

import java.util.ArrayList;

public class MaliciousFile {
	private String fileName;
	private ArrayList<Anormally> anormallyList;
	
	public MaliciousFile (String fileName) {
		this.fileName = fileName;
		anormallyList = new ArrayList<Anormally>();
	}
	
	public void addAnormally(int position, double distance, double exceedingRatio) {
		Anormally a = new Anormally (position, distance, exceedingRatio);
		anormallyList.add(a);
	}
	
	public String getFileName(){
		return fileName;
	}
	
	public ArrayList<Anormally> getAnormallyList(){
		return anormallyList;
	}
}
