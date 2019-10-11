package no.hvl.dat100ptc.oppgave3;


import no.hvl.dat100ptc.oppgave1.GPSPoint;


public class GPSUtils {

	public static double findMax(double[] da) {

		double max; 
		
		max = da[0];
		
		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}
		
		return max;
	}

	public static double findMin(double[] da) {

		double min;

		// TODO - START

		min = da[0];
		
		for (double d : da) {
			if (d < min) {
				min = d;
			}
		}
		return min;

		// TODO - SLUT

	}

	public static double[] getLatitudes(GPSPoint[] gpspoints) {

		// TODO - START
		
		double[] latitudeTab = new double[gpspoints.length];
		
		
		for (int pos = 0; pos < gpspoints.length; pos++) {
			latitudeTab[pos] = gpspoints[pos].getLatitude();
		}
		
		return latitudeTab;
		
		// TODO - SLUTT
	}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {

		// TODO - START

		double[] longitudeTab = new double[gpspoints.length];
		
		for (int pos = 0; pos < gpspoints.length; pos++) {
			longitudeTab[pos] = gpspoints[pos].getLongitude();
		}
		
		return longitudeTab;
		
		// TODO - SLUTT

	}
	
	public static int[] getTimes(GPSPoint[] gpspoints) {

		// TODO - START

		int[] timeTab = new int[gpspoints.length];
		
		for (int pos = 0; pos < gpspoints.length; pos++) {
			timeTab[pos] = gpspoints[pos].getTime();
		}
		
		return timeTab;
		
		// TODO - SLUTT

	}

	private static int R = 6371000; // jordens radius

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		double d;
		double latitude1, latitude2;

		// TODO - START


			latitude1 = Math.toRadians(gpspoint1.getLatitude());

			latitude2 = Math.toRadians(gpspoint2.getLatitude());

			double theta = Math.toRadians(gpspoint2.getLatitude() - gpspoint1.getLatitude());
			double lambda = Math.toRadians(gpspoint2.getLongitude() - gpspoint1.getLongitude());

			double a = (Math.pow(Math.sin(theta / 2), 2))
					+ (Math.cos(latitude1) * Math.cos(latitude2) * Math.pow(Math.sin(lambda) / 2, 2));
			
			double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
			
			d = c * R;
			
			return d;
		

			// TODO - SLUTT

	}

	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		int secs;
		double speed;

		// TODO - START

		double distance = distance(gpspoint1, gpspoint2);
		secs = gpspoint2.getTime() - gpspoint1.getTime();
		
		speed = ((distance/secs)*60*60/1000);
		
		return speed;

		// TODO - SLUTT

	}

	public static String formatTime(int secs) {

		String timestr;
		String TIMESEP = ":";

		// TODO - START
		
		int hr = secs/3600;
		int rest = secs%3600;
		int min = rest/60;
		int sek = rest%60;
		
		
		timestr = "  ";
				if (hr < 10)
					timestr += "0";
				timestr += hr + TIMESEP;
				
				if (min < 10)
					timestr += "0";
				timestr += min + TIMESEP;
				
				if (sek < 10)
					timestr += "0";
				timestr += sek;
				
		return timestr;
		
		// TODO - SLUTT

	}
	private static int TEXTWIDTH = 10;

	public static String formatDouble(double d) {

		String str;

		// TODO - START

		double rounded = Math.round(d*100)/100.0;
		String round = Double.toString(rounded);
		
		str = String.format("%1$" + TEXTWIDTH + "s", round);
		
		return str;

		// TODO - SLUTT
		
	}
}
