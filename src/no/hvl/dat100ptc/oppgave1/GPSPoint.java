package no.hvl.dat100ptc.oppgave1;

public class GPSPoint {

	// TODO - Objektvariable
	
	private int time;
	private double latitude;
	private double longitude;
	private double elevation;
	
		
	public GPSPoint(int time, double latitude, double longitude, double elevation) {

		// TODO - Konstruktur

		this.time = time;
		this.latitude = latitude;
		this.longitude = longitude;
		this.elevation = elevation;

	}

	// TODO - get/set metoder
	public int getTime() {
		
		return time;
		
	}

	public void setTime(int time) {
				
		this.time = time;

	}

	public double getLatitude() {
		
		return latitude;
		
	}

	public void setLatitude(double latitude) {
		
		this.latitude = latitude;
		
	}

	public double getLongitude() {
		
		return longitude;
		
	}

	public void setLongitude(double longitude) {
		
		this.longitude = longitude;
		
	}

	public double getElevation() {
		
		return elevation;
		
	}

	public void setElevation(double elevation) {
		
		this.elevation = elevation;
		
	}
	
//	Metode som returnerer en tekstlig versjon av et objekt
//	I dette tilfelle skal det vise 1 (2.0,3.0) 5.0\n
//	1 dag, 2.0 breddegrad, 3.0 lengdegrad og 5.0 er høyden
	
	public String toString() { 
		
		String str;
		
		// TODO - start
		
		str = time + " (" + latitude + "," + longitude + ") " + elevation + "\n";
		return str;

		// TODO - slutt
		
	}
}
