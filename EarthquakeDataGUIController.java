package com.lynden.gmapsfx;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class EarthquakeDataGUIController {
	@FXML
	private ChoiceBox filterDropDown1;
	
	@FXML
	private Button applyButton;
	@FXML
	private TextField textField;
	@FXML
	private TextField field1;
	@FXML
	private TextField field2;
	@FXML
	private Button gMapsFx;
	//instantiating double arrays to set up lat/long values later
	public ArrayList<Earthquake> earthquakes;
	public static double[][] doubles;
	public static double[][] datedoubles;
	public static double[][] locationdoubles;
	public static double[][] depthdoubles;
	public static double[][] magdoubles;
	public static double[][] magTypedoubles;
	public static double[][] placedoubles;
	public static double[][] statusdoubles;
	public static boolean datebool = false;
	public static boolean locationbool = false;
	public static boolean depthbool = false;
	public static boolean magbool = false;
	public static boolean magTypebool = false;
	public static boolean placebool = false;
	public static boolean statusbool = false;
	public Stage primaryStage;
	public String[] args = new String[1];
	
	@FXML
	//initializing the gui for the main window
	public void initialize() throws NumberFormatException, Exception {
		filterDropDown1.setItems(FXCollections.observableArrayList("help", "summary", "print", "printby", "searchby"));
		FileLoad loader = new FileLoad();
		ArrayList<Earthquake> earthquakes = loader.extractData("/Users/jacobantoine/Desktop/GMapsFX-master 2/GMapsFX/src/main/java/com/lynden/gmapsfx/all_month.csv");
		doubles = new double[earthquakes.size()][2];
		for (int i = 0; i < earthquakes.size(); i++) {
			doubles[i][0] = EarthquakeCollection.printByLatLon(earthquakes)[i][0];
			doubles[i][1] = EarthquakeCollection.printByLatLon(earthquakes)[i][1];
		}
		
//		Test data file

		
		
		
//		MAIN FILE
		
		
	}
	@FXML
	public void googleMapClick (ActionEvent event) throws Exception {
		Stage newStage = new Stage();
		BorderPane bp = new BorderPane();
		Scene newScene = new Scene(bp);
		newStage.setScene(newScene);
		//sets up a new google maps window on the google maps button click
		MainApp googleMap = new MainApp();
		googleMap.start(newStage);
		
		
		
	}
	public static void main(String[] args) throws NumberFormatException, Exception {
		//nothing to see here move along... :)
		
		
		
		
	}
	@FXML
	public void applyButtonClick (ActionEvent event) throws Exception {
		//checker if the apply button was clicked
		FileLoad loader = new FileLoad();
		ArrayList<Earthquake> earthquakes = loader.extractData("/Users/jacobantoine/Desktop/GMapsFX-master 2/GMapsFX/src/main/java/com/lynden/gmapsfx/all_month.csv");
		//checking dropdown value and running appropriate methods
		if (filterDropDown1.getValue().equals("help")) {
			System.out.println(Main.printHelp());
		}
		else if (filterDropDown1.getValue().equals("summary")) {
			System.out.println(EarthquakeCollection.summary(earthquakes));
		}
		else if (filterDropDown1.getValue().equals("print")) {
			System.out.println(EarthquakeCollection.printAll(earthquakes));
		}
		else if (filterDropDown1.getValue().equals("printby")) {
			for (int z = 0; z < doubles.length; z++) {
				//doubles[z][0] = Double.parseDouble(null);
			}
			if (textField.getText().equals("date")) {
				System.out.println(EarthquakeCollection.printByDate(earthquakes));
				
				
			}
			if (textField.getText().equals("depth")) {
				System.out.println(EarthquakeCollection.printByDepth(earthquakes));
				
			}
			if (textField.getText().equals("mag")) {
				System.out.println(EarthquakeCollection.printByMag(earthquakes));
				
			}
			if (textField.getText().equals("place")) {
				System.out.println(EarthquakeCollection.printByPlace(earthquakes));
				
			}
			if (textField.getText().equals("status")) {
				System.out.println(EarthquakeCollection.printByStatus(earthquakes));
				
			}
			if (textField.getText().equals("latlong")) {
				System.out.println(EarthquakeCollection.printByLatLon(earthquakes));
				
			}
			
		}
		//hardest part
		else if (filterDropDown1.getValue().equals("searchby")) {
			if (textField.getText().equals("date")) {
				datedoubles = new double[earthquakes.size()][2];
				datebool = true;
				locationbool = false;
				depthbool = false;
				magbool = false;
				magTypebool = false;
				placebool = false;
				statusbool = false;
				//bunch of booleans to set apart different values. The static part sometimes messes up the values in the lat/long so it will not work
				//this did not end up working as static public variables can not change values. I did not know how to correct this
				String date1 = field1.getText();
				System.out.println(EarthquakeCollection.searchByDate(earthquakes, date1));
				for (int i = 0; i < earthquakes.size(); i++) {
					if (earthquakes.get(i).time.equals(date1)) {
						ArrayList<Earthquake> newList = new ArrayList();
						newList.add(earthquakes.get(i));
						//checking if the date matches, adding that specific earthquakw to a new arraylist and getting the lat long values added to a specific doubles arraylist e.g. datedoubles
						datedoubles[i][0] = EarthquakeCollection.printByLatLon(newList)[0][0];
						datedoubles[i][1] = EarthquakeCollection.printByLatLon(newList)[0][1];
					}else {
						//else set to 0 value
						datedoubles[i][0] = 0;
						datedoubles[i][1] = 0;
					}
				}
			}
			if (textField.getText().equals("location")) {
				
				datebool = false;
				locationbool = true;
				depthbool = false;
				magbool = false;
				magTypebool = false;
				placebool = false;
				statusbool = false;
				String lat1 = field1.getText();
				String long1 = field2.getText();
				System.out.println(EarthquakeCollection.searchByLocation(earthquakes, Double.parseDouble(lat1), Double.parseDouble(long1)));
				for (int i = 0; i < earthquakes.size(); i++) {
					if (earthquakes.get(i).latitude == (Double.parseDouble(lat1)) && earthquakes.get(i).longitude == (Double.parseDouble(long1))) {
						ArrayList<Earthquake> newList = new ArrayList();
						newList.add(earthquakes.get(i));
						locationdoubles[i][0] = EarthquakeCollection.printByLatLon(newList)[0][0];
						locationdoubles[i][1] = EarthquakeCollection.printByLatLon(newList)[0][1];
						
					}else {
						locationdoubles[i][0] = 0;
						locationdoubles[i][1] = 0;
					}
				}
			}
			if (textField.getText().equals("depth")) {
				depthdoubles = new double[earthquakes.size()][2];
				datebool = false;
				locationbool = false;
				depthbool = true;
				magbool = false;
				magTypebool = false;
				placebool = false;
				statusbool = false;
				String depth1 = field1.getText();
				System.out.println(EarthquakeCollection.searchByDepth(earthquakes, Double.parseDouble(depth1)));
				for (int i = 0; i < earthquakes.size(); i++) {
					if (earthquakes.get(i).time.equals(depth1)) {
						ArrayList<Earthquake> newList = new ArrayList();
						newList.add(earthquakes.get(i));
						depthdoubles[i][0] = EarthquakeCollection.printByLatLon(newList)[0][0];
						depthdoubles[i][1] = EarthquakeCollection.printByLatLon(newList)[0][1];
					}else {
						depthdoubles[i][0] = 0;
						depthdoubles[i][1] = 0;
					}
				}
			}
			if (textField.getText().equals("mag")) {
				magdoubles = new double[earthquakes.size()][2];
				datebool = false;
				locationbool = false;
				depthbool = false;
				magbool = true;
				magTypebool = false;
				placebool = false;
				statusbool = false;
				String mag1 = field1.getText();
				System.out.println(field1.getText());
				System.out.println(EarthquakeCollection.searchByMag(earthquakes, Double.parseDouble(mag1)));
				for (int i = 0; i < earthquakes.size(); i++) {
					
					if (earthquakes.get(i).time.equals(mag1)) {
						ArrayList<Earthquake> newList = new ArrayList();
						newList.add(earthquakes.get(i));
						magdoubles[i][0] = EarthquakeCollection.printByLatLon(newList)[0][0];
						magdoubles[i][1] = EarthquakeCollection.printByLatLon(newList)[0][1];
					}else {
						magdoubles[i][0] = 0;
						magdoubles[i][1] = 0;
					}
				}
			}
			if (textField.getText().equals("magtype")) {
				magTypedoubles = new double[earthquakes.size()][2];
				datebool = false;
				locationbool = false;
				depthbool = false;
				magbool = false;
				magTypebool = true;
				placebool = false;
				statusbool = false;
				String magType1 = field1.getText();
				System.out.println(EarthquakeCollection.searchByMagType(earthquakes, magType1));
				for (int i = 0; i < earthquakes.size(); i++) {
					if (earthquakes.get(i).time.equals(magType1)) {
						ArrayList<Earthquake> newList = new ArrayList();
						newList.add(earthquakes.get(i));
						magTypedoubles[i][0] = EarthquakeCollection.printByLatLon(newList)[0][0];
						magTypedoubles[i][1] = EarthquakeCollection.printByLatLon(newList)[0][1];
					}
				}
			}
			if (textField.getText().equals("place")) {
				placedoubles = new double[earthquakes.size()][2];
				datebool = false;
				locationbool = false;
				depthbool = false;
				magbool = false;
				magTypebool = false;
				placebool = true;
				statusbool = false;
				String place1 = field1.getText();
				System.out.println(EarthquakeCollection.searchByPlace(earthquakes, place1));
				for (int i = 0; i < earthquakes.size(); i++) {
					if (earthquakes.get(i).time.equals(place1)) {
						ArrayList<Earthquake> newList = new ArrayList();
						newList.add(earthquakes.get(i));
						placedoubles[i][0] = EarthquakeCollection.printByLatLon(newList)[0][0];
						placedoubles[i][1] = EarthquakeCollection.printByLatLon(newList)[0][1];
					}else {
						placedoubles[i][0] = 0;
						placedoubles[i][1] = 0;
					}
				}
			}
			if (textField.getText().equals("status")) {
				statusdoubles = new double[earthquakes.size()][2];
				datebool = false;
				locationbool = false;
				depthbool = false;
				magbool = false;
				magTypebool = false;
				placebool = false;
				statusbool = true;
				String status1 = field1.getText();
				System.out.println(EarthquakeCollection.searchByStatus(earthquakes, status1));
				
				for (int i = 0; i < earthquakes.size(); i++) {
					if (earthquakes.get(i).time.equals(status1)) {
						ArrayList<Earthquake> newList = new ArrayList();
						newList.add(earthquakes.get(i));
						statusdoubles[i][0] = EarthquakeCollection.printByLatLon(newList)[0][0];
						statusdoubles[i][1] = EarthquakeCollection.printByLatLon(newList)[0][1];
					}else {
						statusdoubles[i][0] = 0;
						statusdoubles[i][1] = 0;
					}
				}
			}
		}
	}
		

}
