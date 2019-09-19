package com.lynden.gmapsfx;
/* Main.java handles the I/O and user interaction for this project.
 * When run, a command line will appear.
 * Type in commands to return appropriate data.
 * 
 * Project by: Jamieson Allare, Laura Buckles, and Jeff Tran
 */

// Imports
import java.util.Scanner;
import java.util.ArrayList;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class Main extends Application{
	@Override
	public void start(Stage primaryStage) {
		//opens the EARTHQUAKEDATAGUICONTROLLER
		try {
			primaryStage.setTitle("Earthquake Data App");
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/EarthquakeDataGUI.fxml"));
			AnchorPane mainLayout = (AnchorPane)loader.load();
			
			Scene scene = new Scene(mainLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static double a = 0.0;
	public static double b = 0.0;
	public static ArrayList<String> newList;
	public ArrayList<Earthquake> earthquakes;
	public static double[][] doubles;
	// Print help method
	public static String printHelp() {
		String output = "";
		output += "Valid commands: \n";
		output += "'summary': summary: print out a summary of all of the data (# of events, timerange of the events�)\n";
		output += "'print': print out all of the earthquake events\n";
		output += "'print by ___': print out all of the earthquake events, sorted by some field (date, depth, mag, place, status, Latitude/ Longitude (latlong))\n";
		output += "'search by ___': print out all of the earthquake events that meet some criteria (date, location, depth, mag, magType, place, status)\n";
		output += "'search by ___ range': print out all of the earthquake events within a range of criteria (date, depth, mag)\n";
		output += "GMAPS to enter google map view";
		output += "'exit': to end program.";
		return output;
	}
	
	// Main method with switch case to call appropriate methods
	
	public static void main(String[] args) throws Exception{
		launch(args);
		
		// Make a new collection by loading in data from FileLoad class
		FileLoad loader = new FileLoad();
		
		
//		Test data file

		

		
//		MAIN FILE
		ArrayList<Earthquake> earthquakes = loader.extractData("/Users/jacobantoine/Desktop/GMapsFX-master 2/GMapsFX/src/main/java/com/lynden/gmapsfx/all_month.csv");
		doubles = new double[earthquakes.size()][2];
		//instantiating the earthquake data from the csv file.
		// Create Scanner object and Welcome screen
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome to Earthquake Data Phase 2.");

		while(true) {
			// Ask user for input until 'exit'
			
			
			// Begin switch case
			/*
			
				// Help, Summary, and print all cases
				case("help"): System.out.println(printHelp()); break;
				case("summary"): System.out.println(EarthquakeCollection.summary(earthquakes)); break;
				case("print"): System.out.println(EarthquakeCollection.printAll(earthquakes)); break;
				
				// Print by cases
				case("print by date"): System.out.println(EarthquakeCollection.printByDate(earthquakes)); break;
				case("print by depth"): System.out.println(EarthquakeCollection.printByDepth(earthquakes)); break;
				case("print by mag"): System.out.println(EarthquakeCollection.printByMag(earthquakes)); break;
				case("print by place"): System.out.println(EarthquakeCollection.printByPlace(earthquakes)); break;
				case("print by status"):System.out.println(EarthquakeCollection.printByStatus(earthquakes)); break;
				case("print by latlong"):System.out.println(EarthquakeCollection.printByLatLon(earthquakes)); break;
				
				case("gmaps"):
					MainApp googleMap = new MainApp();
					
					
					doubles = new double[EarthquakeCollection.printByLatLon(earthquakes).length][2];
					
					for (int i = 0; i < EarthquakeCollection.printByLatLon(earthquakes).length; i++) {
						a = EarthquakeCollection.printByLatLon(earthquakes)[i][0];
						b = EarthquakeCollection.printByLatLon(earthquakes)[i][1];
						doubles[i][0] = a;
						doubles[i][1] = b;
						
					}
					googleMap.main(args);
					
					break;
				// Search by cases
				case("search by date"): 
					System.out.print("Please enter a date (yyyy-MM-dd HH:mm:ss) ");
					String dateStr = input.nextLine();
					System.out.println(EarthquakeCollection.searchByDate(earthquakes, dateStr)); break;
				case("search by date range"):
					System.out.print("Please enter first date in the following format: (yyyy-MM-dd HH:mm:ss) ");
					String dateStr1 = input.nextLine();
					System.out.print("Please enter second date in the following format: (yyyy-MM-dd HH:mm:ss) ");
					String dateStr2 = input.nextLine();
					System.out.println(EarthquakeCollection.searchByDateRange(earthquakes, dateStr1, dateStr2)); break;
				case("search by location"): 
					System.out.print("Please enter the latitude (in the range of -90 to 90): ");
					double latitude = input.nextDouble(); input.nextLine();
					System.out.print("Please enter the longitude (in the range of -180 to 180): ");
					double longitude = input.nextDouble(); input.nextLine();
					System.out.println(EarthquakeCollection.searchByLocation(earthquakes, latitude, longitude)); break;	
				
				case("search by depth"): 
					System.out.print("Please enter a depth: ");
					double depth = input.nextDouble(); input.nextLine();
					System.out.println(EarthquakeCollection.searchByDepth(earthquakes, depth)); break;	
				
					
				case("search by depth range"):
					System.out.print("Please enter first depth: ");
					double depth1 = input.nextDouble(); input.nextLine();
					System.out.print("Please enter second depth: ");
					double depth2 = input.nextDouble(); input.nextLine();
					System.out.println(EarthquakeCollection.searchByDepthRange(earthquakes, depth1, depth2)); break;
				case("search by mag"): 
					System.out.print("Please enter magnitude: ");
					double mag = input.nextDouble(); input.nextLine();
					System.out.println(EarthquakeCollection.searchByMag(earthquakes, mag)); break;	
				case("search by mag range"):
					System.out.print("Please enter first magnitude: ");
					double mag1 = input.nextDouble(); input.nextLine();
					System.out.print("Please enter second magnitude: ");
					double mag2 = input.nextDouble(); input.nextLine();
					System.out.println(EarthquakeCollection.searchByMagRange(earthquakes, mag1, mag2)); break;
				case("search by magType"): 
					System.out.print("Please enter a magnitude type: ");
					String magType = input.nextLine();
					System.out.println(EarthquakeCollection.searchByMagType(earthquakes, magType)); break;
				case("search by place"): 
					System.out.print("Please enter a place: ");
					String place = input.nextLine();
					System.out.println(EarthquakeCollection.searchByPlace(earthquakes, place)); break;
				case("search by status"): 
					System.out.print("Please enter a status: ");
					String status = input.nextLine();			
					System.out.println(EarthquakeCollection.searchByStatus(earthquakes, status)); break;
				
				// Exit and default cases
				case("exit"): System.out.println("Program terminated."); input.close(); System.exit(0); break;
				default: System.out.println("Command not recognized. Enter 'help' for list of commands."); break;
				*/
			}
		}
	}
