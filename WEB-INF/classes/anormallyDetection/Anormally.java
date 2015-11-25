package anormallyDetection;

public class Anormally {
	private int position;
	private double distance;
	private double exceedingRatio;
	
	public Anormally (int position, double distance, double exceedingRatio) {
		this.position = position;
		this.distance = distance;
		this.exceedingRatio = exceedingRatio;
	}
	
	public int getPosition() {
		return position;
	}
	
	public double getDistance() {
		return distance;
	}
	
	public double getExceedingRatio() {
		return exceedingRatio;
	}
}
