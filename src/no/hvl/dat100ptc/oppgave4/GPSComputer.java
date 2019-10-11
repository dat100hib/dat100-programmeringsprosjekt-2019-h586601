package no.hvl.dat100ptc.oppgave4;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;

public class GPSComputer {
	
	private GPSPoint[] gpspoints;
	
	public GPSComputer(String filename) {

		GPSData gpsdata = GPSDataFileReader.readGPSFile(filename);
		gpspoints = gpsdata.getGPSPoints();

	}

	public GPSComputer(GPSPoint[] gpspoints) {
		this.gpspoints = gpspoints;
	}
	
	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
	// beregn total distances (i meter)
	public double totalDistance() {

		double distance = 0;

		// TODO - START

		for (int pos = 0; pos < gpspoints.length-1; pos++) {
			distance += GPSUtils.distance(gpspoints[pos], gpspoints[pos+1]);
		}
		
		return distance;

		// TODO - SLUTT

	}

	// beregn totale høydemeter (i meter)
	public double totalElevation() {

		double elevation = 0;
		
		// TODO - START

		for (int pos = 0; pos < gpspoints.length-1; pos++) {
			
			double posElevation = gpspoints[pos].getElevation();
			double pos1Elevation = gpspoints[pos+1].getElevation();
			
			if (posElevation < pos1Elevation)
			elevation += pos1Elevation - posElevation;
		}
		
		return elevation;
		

		// TODO - SLUTT

	}

	// beregn total tiden for hele turen (i sekunder)
	public int totalTime() {
		
		int sistePos = gpspoints.length-1;
		int forstePos = 0;
		
		int totalTid = gpspoints[sistePos].getTime() - gpspoints[forstePos].getTime();
		
		return totalTid;
	}
		
	// beregn gjennomsnitshastighets mellom hver av gps punktene
	public double[] speeds() {
		
		// TODO - START
		
		double[] gjennomsnitt = new double[gpspoints.length-1];
		
		for (int pos = 0; pos < gpspoints.length-1; pos++) {
			gjennomsnitt[pos] = GPSUtils.speed(gpspoints[pos], gpspoints[pos+1]);
		}
		
		return gjennomsnitt;

		// TODO - SLUTT

	}
	
	public double maxSpeed() {
		
		double maxspeed = 0;
		
		// TODO - START
		
		maxspeed = GPSUtils.findMax(speeds());
		
		return maxspeed;
		
		// TODO - SLUTT
		
	}

	public double averageSpeed() {

		double average = 0;
		
		// TODO - START
		
		average = (totalDistance()/totalTime())*60*60/1000;
		
		return average;
		
		// TODO - SLUTT
		
	}

	/*
	 * bicycling, <10 mph, leisure, to work or for pleasure 4.0 bicycling,
	 * general 8.0 bicycling, 10-11.9 mph, leisure, slow, light effort 6.0
	 * bicycling, 12-13.9 mph, leisure, moderate effort 8.0 bicycling, 14-15.9
	 * mph, racing or leisure, fast, vigorous effort 10.0 bicycling, 16-19 mph,
	 * racing/not drafting or >19 mph drafting, very fast, racing general 12.0
	 * bicycling, >20 mph, racing, not drafting 16.0
	 */

	// conversion factor m/s to miles per hour
	public static double MS = 2.236936;

	// beregn kcal gitt weight og tid der kjøres med en gitt hastighet
	public double kcal(double weight, int secs, double speed) {

		double kcal;

		// MET: Metabolic equivalent of task angir (kcal / kg-1 x h-1)
		double met = 0;		
		double speedmph = speed * MS;

		// TODO - START
		// Kan også skrives med tabeller.
		
		if (speedmph < 10)
			met = 4.0;
		else if (speedmph >= 10 && speedmph < 12)
			met = 6.0;
		else if (speedmph >= 12 && speedmph < 14)
			met = 8.0;
		else if (speedmph >= 14 && speedmph < 16)
			met = 10.0;
		else if (speedmph >= 16 && speedmph < 20)
			met = 12.0;
		else
			met = 16.0;
		
		double hr = secs/3600.0;
		
		kcal = met * weight * hr;
		
		return kcal;

		// TODO - SLUTT
		
	}

	public double totalKcal(double weight) {

		double totalkcal = 0;

		// TODO - START
		// Denne vil ikke gå gjennom. Forstår ikke hvorfor. Skriver ut 0.0 istedenfor 28.44
		// #whocaresaboutcalories
		
		double[] Hastighet = speeds();
		int[] Tider = GPSUtils.getTimes(gpspoints);
		
		for (int pos = 0; pos < Hastighet.length; pos++) {
			totalkcal += kcal(weight, (Tider[pos+1] - Tider[pos]), Hastighet[pos]);
		}
		
		return totalkcal;

		// TODO - SLUTT
		
	}
	
	private static double WEIGHT = 80.0;
	
	public void displayStatistics() {

		System.out.println("==============================================");

		// TODO - START

		System.out.println("Total time     : " + GPSUtils.formatTime(totalTime()));
		System.out.println("Total distance : " + GPSUtils.formatDouble(totalDistance()/1000.0) + " km");
		System.out.println("Total elevation: " + GPSUtils.formatDouble(totalElevation()) + " m");
		System.out.println("Max speed      : " + GPSUtils.formatDouble(maxSpeed()) + " km/t");
		System.out.println("Average speed  : " + GPSUtils.formatDouble(averageSpeed()) + " km/t");
		System.out.println("Energy         : " + GPSUtils.formatDouble(totalKcal(WEIGHT)) + " kcal");
		
		// TODO - SLUTT
		
		System.out.println("==============================================");
		
	}

}
