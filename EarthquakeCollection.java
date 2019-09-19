package com.lynden.gmapsfx;
/* EarthquakeCollection.java stores all earthquake objects in CSV file.
 * When run, a command line will appear.
 * Type in commands to return appropriate data.
 */

// Imports
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import javafx.util.Pair;

import java.text.SimpleDateFormat;

public class EarthquakeCollection {
	
	// Date format
	SimpleDateFormat format = new SimpleDateFormat("MMMM dd yyyy HH:mm:ss");
	
	// Method to format date
	public static Date formatDate(String dateStr) throws Exception{
		Date newDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateStr);
		return newDate;
	}

	
	
	// Method to print out data about the collection
	public static String summary(ArrayList<Earthquake> earthquakes) {
		int size = earthquakes.size();
		String output = "This collection has " + size + " entries.";
		return output;
	}
	
	// Method to print all entries
	public static String printAll(ArrayList<Earthquake> earthquakes) {
		String output = "";
		
		for (int i = 0; i < earthquakes.size(); i++) {
			output += earthquakes.get(i).toString();
		}
		
		return output;
	}
	
	
	// SEARCH BY METHODS
	
	// Search range of dates
	public static String searchByDateRange(ArrayList<Earthquake> earthquakes, String dateStr1, String dateStr2) throws Exception{
		Date date1 = formatDate(dateStr1);
		Date date2 = formatDate(dateStr2);
		String result = "";
		
		for (int i = 0; i < earthquakes.size(); i++) {
			// If date is between specified range, add to output
			if (earthquakes.get(i).time.compareTo(date1) >= 0 && earthquakes.get(i).time.compareTo(date2) <= 0)
				result += "\n" + earthquakes.get(i).toString();
		}
		
		//checking if anything was found
		if (result.equals(""))
			return "No results found";
		else {
			//creating what user will see
			
			return result;
		}	
	}
	
	
	// Search one date
	public static String searchByDate(ArrayList<Earthquake> earthquakes, String dateStr) throws Exception{
		String result = "";
		for (int i = 0; i < earthquakes.size(); i++) {
			
		if (earthquakes.get(i).time.equals(dateStr)) {
			result += "\n" + earthquakes.get(i).toString();
		}
		}
		//checking if anything was found
		if (result.equals(""))
			return "no results found";
		else {
			//creating what user will see
			
			return result;
		}	
	}
	
	// Search one location
	public static String searchByLocation(ArrayList<Earthquake> earthquakes, double latitude, double longitude) {
		String result = "";
		for (int i = 0; i < earthquakes.size(); i++) {
			
		if (earthquakes.get(i).latitude == (latitude) && earthquakes.get(i).longitude == (longitude)) {
			result += "\n" + earthquakes.get(i).toString();
		}
		}
		//checking if anything was found
		if (result.equals(""))
			return "no results found";
		else {
			//creating what user will see
			
			return result;
		}
	}
	
	// Search by location range 
	//deleted this method
	
	// Search one depth
	public static String searchByDepth(ArrayList<Earthquake> earthquakes, double depth) {
		String result = "";
		for (int i = 0; i < earthquakes.size(); i++) {
			
		if (earthquakes.get(i).depth == depth) {
			result += "\n" + earthquakes.get(i).toString();
		}
		}
		//checking if anything was found
		if (result.equals(""))
			return "no results found";
		else {
			//creating what user will see
			
			return result;
		}
	}
	
	// Search range of depths
	public static String searchByDepthRange(ArrayList<Earthquake> earthquakes, double depth1, double depth2) {
		String result = "";
		for (int i = 0; i < earthquakes.size(); i++) {
			
		if (earthquakes.get(i).depth >= depth1 && earthquakes.get(i).depth <= depth2) {
			result += "\n" + earthquakes.get(i).toString();
		}
		}
		//checking if anything was found
		if (result.equals(""))
			return "no results found";
		else {
			//creating what user will see
			
			return result;
		}	
	}
	
	// Search one magnitude
	public static String searchByMag(ArrayList<Earthquake> earthquakes, double mag) {
		String result = "";
		for (int i = 0; i < earthquakes.size(); i++) {
			
		if (earthquakes.get(i).mag == mag) {
			result += "\n" + earthquakes.get(i).toString();
		}
		}
		//checking if anything was found
		if (result.equals(""))
			return "no results found";
		else {
			//creating what user will see
			
			return result;
		}
	}
	
	// Search magnitude range
	public static String searchByMagRange(ArrayList<Earthquake> earthquakes, double mag1, double mag2) {
		String result = "";
		for (int i = 0; i < earthquakes.size(); i++) {
			
		if (earthquakes.get(i).mag >= mag1 && earthquakes.get(i).depth <= mag2) {
			result += "\n" + earthquakes.get(i).toString();
		}
		}
		//checking if anything was found
		if (result.equals(""))
			return "no results found";
		else {
			//creating what user will see
			
			return result;
		}
	}
	
	// Search magnitude type
	public static String searchByMagType(ArrayList<Earthquake> earthquakes, String magType) {
		String result = "";
		for (int i = 0; i < earthquakes.size(); i++) {
			
		if (earthquakes.get(i).magType.equals(magType)) {
			result += "\n" + earthquakes.get(i).toString();
		}
		}
		//checking if anything was found
		if (result.equals(""))
			return "no results found";
		else {
			//creating what user will see
			
			return result;
		}
	}
	
	// Search by place
	public static String searchByPlace(ArrayList<Earthquake> earthquakes, String place) {
		String result = "";
		for (int i = 0; i < earthquakes.size(); i++) {
			
		if (earthquakes.get(i).place.contains((place))) {
			result += "\n" + earthquakes.get(i).toString();
		}
		}
		//checking if anything was found
		if (result.equals(""))
			return "no results found";
		else {
			//creating what user will see
			
			return result;
		}	
	}
	
	// Search by status
	public static String searchByStatus(ArrayList<Earthquake> earthquakes, String status) {
		String result = "";
		for (int i = 0; i < earthquakes.size(); i++) {
			
		if (earthquakes.get(i).status.equals(status)) {
			result += "\n" + earthquakes.get(i).toString();
		}
		}
		//checking if anything was found
		if (result.equals(""))
			return "no results found";
		else {
			//creating what user will see
			
			return result;
		}
	}
	
	// Print by date
	public static String printByDate(ArrayList<Earthquake> earthquakes) {
		String output = "";
		for (int i = 0; i < earthquakes.size(); i++) {
			output += "\n" + earthquakes.get(i).time;
		}
		return output;
	}
	
	// Print by depth
	public static String printByDepth(ArrayList<Earthquake> earthquakes) {
		// Sort depth 
		String output = "";
		for (int i = 0; i < earthquakes.size(); i++) {
			output += "\n" + earthquakes.get(i).depth;
		}
		return output;	
	}
	
	// Print by magnitude
	public static String printByMag(ArrayList<Earthquake> earthquakes) {
		String output = "";
		for (int i = 0; i < earthquakes.size(); i++) {
			output += "\n" + earthquakes.get(i).mag;
		}
		return output;
	}
	
	// Print by place
	public static String printByPlace(ArrayList<Earthquake> earthquakes) {
		String output = "";
		for (int i = 0; i < earthquakes.size(); i++) {
			output += "\n" + earthquakes.get(
					i).place;
		}
		return output;
	}
	//public static Pair<Double,Double> returnLatLong (Pair<Double, Double> PairList, ArrayList<Earthquake> earthquakes) {
		//for (int i = 0; i < earthquakes.size(); i++) {
		//	PairList.(earthquakes.get(i).latitude, earthquakes.get(i).longitude);
		//}
	//}
	
	// Print by status
	public static String printByStatus(ArrayList<Earthquake> earthquakes) {
		
		String output = "";
		for (int i = 0; i < earthquakes.size(); i++) {
			output += "\n" + earthquakes.get(i).status;
		}
		return output;	
	}
	
	public static double[][] printByLatLon(ArrayList<Earthquake> earthquakes) {
		double[][] output = new double[earthquakes.size()][2];
		for (int i = 0; i < earthquakes.size(); i++) {
			output[i][0] = earthquakes.get(i).latitude;
			output[i][1] = earthquakes.get(i).longitude;
		}
		return output;
	}
}