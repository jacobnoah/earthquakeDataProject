package com.lynden.gmapsfx;
/* Earthquake.java creates an object for each row from CSV file.
 * When run, all data will be instantiated in an object.
 * All objects will then be sent to the EarthquakeCollection class.
 */

// Imports
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Earthquake{
	
	// Date format
	SimpleDateFormat format = new SimpleDateFormat("MMMM dd yyyy HH:mm:ss");
		
	//Initialize data fields
	Date time;
	double latitude = 0;
	double longitude = 0;
	double depth = 1;
	double mag = 1.0;
	String magType = "ml";
	int nst = 1;
	double gap = 0;
	double dmin = 0.0;
	double rms = 0.0;
	String net = "us";
	String id = "xxxxxxxxxx";
	Date updated;
	String place = "default location";
	String type = "earthquake";
	double horizontalError = 0.0;
	double depthError = 0.0;
	double magError = 0.0;
	int magNst = 0;
	String status = "default status";
	String locationSource = "us";
	String magSource = "us";
	
	// Earthquake constructors
	Earthquake () {	
	}
	
	// Create earthquake object from FileLoad
	Earthquake (String dateStr, double latitude, double longitude, double depth, double mag, String magType, int nst, double gap, double dmin, double rms, 
			String net, String id, String updatedStr, String place, String type, double horizontalError, double depthError, double magError, int magNst,
			String status, String locationSource, String magSource) throws Exception{

		// Variable declaration
		this.time = formatDate(dateStr);
		this.latitude = latitude;
		this.longitude = longitude;
		this.depth = depth;
		this.mag = mag;
		this.magType = magType;
		this.nst = nst;
		this.gap = gap;
		this.dmin = dmin;
		this.rms = rms;
		this.net = net;
		this.id = id;
		this.updated = formatDate(updatedStr);
		this.place = place;
		this.type = type;
		this.horizontalError = horizontalError;
		this.depthError = depthError;
		this.magError = magError;
		this.magNst = magNst;
		this.status = status;
		this.locationSource = locationSource;
		this.magSource = magSource;
	}
	
	// Format date method
	public Date formatDate(String dateStr) throws Exception{
		Date newDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateStr);
		return newDate;
	}
	
	// Getter methods
	public String getTime() { return format.format(time);}
	public double getLatitude() {return latitude;}
	public double getLongitude() {return longitude;}
	public double getDepth() {	return depth;}
	public double getMag() { return mag;}
	public String getMagType() { return magType;}
	public int getNst() { return nst;}
	public double getGap() { return gap;}
	public double getDmin() { return dmin;}
	public double getRms() { return rms;}
	public String getNet() { return net;}
	public String getID() { return id;}
	public String getUpdated() { return format.format(updated);}
	public String getPlace() { return place;}
	public String getType() { return type;}
	public double getHorizontalError() { return horizontalError;}
	public double getDepthError() { return depthError;}
	public double getMagError() { return magError;}
	public int getMagNst() { return magNst;}
	public String getStatus() { return status;}
	public String getLocationSource() { return locationSource;}
	public String getMagSource() { return magSource;}

	//Setter methods
	public void setTime(String dateStr)throws Exception {
		this.time = formatDate(dateStr);
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public void setDepth(double depth) {
		this.depth = depth;
	}
	public void setMag(double mag) {
		this.mag = mag;
	}
	public void setMagType(String magType) {
		this.magType = magType;
	}
	public void setNst(int nst) {
		this.nst = nst;
	}
	public void setGap(double gap) {
		this.gap = gap;
	}
	public void setDmin(double dmin) {
		this.dmin = dmin;
	}
	public void setRms(double rms) {
		this.rms = rms;
	}
	public void setNet(String net) {
		this.net = net;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setUpdated(String updatedStr)throws Exception {
		this.updated = formatDate(updatedStr);
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setHorizontalError(double horizontalError) {
		this.horizontalError = horizontalError;
	}
	public void setDepthError(double depthError) {
		this.depthError = depthError;
	}
	public void setMagError(double magError) {
		this.magError = magError;
	}
	public void setMagNst(int magNst) {
		this.magNst = magNst;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setLocationSource(String locationSource) {
		this.locationSource = locationSource;
	}
	public void setMagSource(String magSource) {
		this.magSource = magSource;
	}

	// Override for toString();
	@Override
	public String toString(){
		
		// Format output for each earthquake object
		String output = "Earthquake Entry: \n\n";
		output += "Time: " + format.format(time) + "\t\t\tLatitude: " + latitude + "\n";
		output += "Longitude: " + longitude + "\t\t\t\tDepth: " + depth + "\n";
		output += "Magnitude: " + mag + "\t\t\t\t\tMagnitude Type: " + magType + "\n";
		output += "Number of Seismic Stations Used: " + nst + "\t\tGap: " + gap + "\n";
		output += "Distance from Nearest Station: " + dmin + "\t\tRoot-Mean-Square: " + rms + "\n";
		output += "Data Contributer: " + net + "\t\t\t\tId: " + id + "\n";
		output += "Last Updated: " + format.format(updated) + "\t\tPlace: " + place + "\n";
		output += "Type: " + type + "\t\t\t\tHorizontal Error: " + horizontalError + "\n";
		output += "Depth Error: " + depthError + "\t\t\t\tMagnitude Error: " + magError + "\n";
		output += "Magnitude Source: " + magSource + "\t\t\t\tHorizontal Error: " + horizontalError + "\n";
		output += "Seismic Stations Used for Magnitude: " + magNst + "\t\tStatus: " + status + "\n";
		output += "Location Source: " + locationSource + "\t\t\t\tMagnitude Source: " + magSource + "\n-------------------------------------------------------------------\n";
		
		return output;
	}
//	https://medium.com/omarelgabrys-blog/comparing-objects-307400115f88?
//	USED LINK ABOVE TO LEARN HOW TO SORT OBJECTS
	
	// Sort by depth
	static class DepthSort implements Comparator<Earthquake>{

		@Override
		public int compare(Earthquake e1, Earthquake e2) {
			return Double.compare(e1.getDepth(), e2.getDepth());
		}
		
	}
	
	// Sort by magnitude
	static class MagSort implements Comparator<Earthquake>{

		@Override
		public int compare(Earthquake e1, Earthquake e2) {
			return Double.compare(e1.getMag(), e2.getMag());
		}
		
	}
	
	// Sort by status
	static class StatusSort implements Comparator<Earthquake>{

		@Override
		public int compare(Earthquake e1, Earthquake e2) {
			return e1.getStatus().compareTo(e2.getStatus());
		}
	}
	
	// Sort by place
	static class PlaceSort implements Comparator<Earthquake>{

		@Override
		public int compare(Earthquake e1, Earthquake e2) {
			String[] e1_place = e1.getPlace().split(", ");
			String[] e2_place = e2.getPlace().split(", ");
			
			return e1_place[1].compareTo(e2_place[1]);
		}
		
	}
	
	//sorting by date
	static class DateSort implements Comparator<Earthquake>{

		@Override
		public int compare(Earthquake e1, Earthquake e2) {
			// TODO Auto-generated method stub
			return e1.time.compareTo(e2.time);
		}
	}
	
	//sorting by mag type
		static class MagTypeSort implements Comparator<Earthquake>{

			@Override
			public int compare(Earthquake e1, Earthquake e2) {
				// TODO Auto-generated method stub
				return e1.getMagType().compareTo(e2.getMagType());
			}
		}
		
		//sorting by long and alt
		static class LocationSort implements Comparator<Earthquake>{

			@Override
			public int compare(Earthquake e1, Earthquake e2) {
				// TODO Auto-generated method stub
				int latitudeAns = Double.compare(e1.getLatitude(), e2.getLatitude());
				
				//checking if latitudes are different
				if (latitudeAns != 0) {
					return latitudeAns;
				}
				
				//if they are the same, compare longitude
				return Double.compare(e1.getLongitude(), e2.getLongitude());
			}
		}
		
	public static void main(String[] args)throws Exception {
		Earthquake quake1 = new Earthquake();
		Earthquake quake2 = new Earthquake();
		
		quake1.setTime("2019-03-19 12:34:21");
		quake1.setUpdated("2019-03-20 14:32:12");
		
		quake2.setTime("2019-08-19 12:34:21");
		quake2.setUpdated("2019-08-20 14:32:12");
		
		ArrayList<Earthquake> test = new ArrayList<Earthquake>();
		
		test.add(quake1);
		test.add(quake2);
		
		Collections.sort(test, new Earthquake.DateSort());
		
		String output = "";
		
		for(Earthquake e : test) {
			output += e.toString();
		}
		
		System.out.println(output);
	}

	

	
}