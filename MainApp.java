package com.lynden.gmapsfx;

import com.lynden.gmapsfx.service.elevation.ElevationResult;
import com.lynden.gmapsfx.service.elevation.ElevationService;
import com.lynden.gmapsfx.service.elevation.ElevationServiceCallback;
import com.lynden.gmapsfx.service.elevation.ElevationStatus;
import com.lynden.gmapsfx.service.elevation.LocationElevationRequest;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.Animation;
import com.lynden.gmapsfx.javascript.object.DirectionsPane;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.InfoWindow;
import com.lynden.gmapsfx.javascript.object.InfoWindowOptions;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.LatLongBounds;
import com.lynden.gmapsfx.javascript.object.MVCArray;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.service.directions.DirectionStatus;
import com.lynden.gmapsfx.service.directions.DirectionsRenderer;
import com.lynden.gmapsfx.service.directions.DirectionsRequest;
import com.lynden.gmapsfx.service.directions.DirectionsResult;
import com.lynden.gmapsfx.service.directions.DirectionsService;
import com.lynden.gmapsfx.service.directions.DirectionsServiceCallback;
import com.lynden.gmapsfx.service.directions.DirectionsWaypoint;
import com.lynden.gmapsfx.service.directions.TravelModes;
import com.lynden.gmapsfx.service.geocoding.GeocoderStatus;
import com.lynden.gmapsfx.service.geocoding.GeocodingResult;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import com.lynden.gmapsfx.service.geocoding.GeocodingServiceCallback;
import com.lynden.gmapsfx.shapes.ArcBuilder;
import com.lynden.gmapsfx.shapes.Circle;
import com.lynden.gmapsfx.shapes.CircleOptions;
import com.lynden.gmapsfx.shapes.Polygon;
import com.lynden.gmapsfx.shapes.PolygonOptions;
import com.lynden.gmapsfx.shapes.Polyline;
import com.lynden.gmapsfx.shapes.PolylineOptions;
import com.lynden.gmapsfx.shapes.Rectangle;
import com.lynden.gmapsfx.shapes.RectangleOptions;
import com.lynden.gmapsfx.util.MarkerImageFactory;

import java.util.ArrayList;
import java.util.Locale;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import netscape.javascript.JSObject;
import static javafx.application.Application.launch;
import javafx.concurrent.Worker;
import javafx.scene.web.WebEngine;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 * Example Application for creating and loading a GoogleMap into a JavaFX
 * application
 *
 * @author Rob Terpilowski
 */
//This code is not mine it is Rob Terpilowski's
public class MainApp extends Application implements MapComponentInitializedListener, 
        ElevationServiceCallback, GeocodingServiceCallback, DirectionsServiceCallback{

    protected GoogleMapView mapComponent;
    protected GoogleMap map;
    protected DirectionsPane directions;
    //instantiated buttons and items in the window
    private Button btnZoomIn;
    private Button btnZoomOut;
    private Label lblZoom;
    private Label lblCenter;
    private Label lblClick;
    private ComboBox<MapTypeIdEnum> mapTypeCombo;
	
	private MarkerOptions markerOptions2;
	private Marker myMarker2;
	private Button btnHideMarker;
	private Button btnDeleteMarker;
	
        
    @Override
    public void start(final Stage stage) {
        System.out.println("Java version: " + System.getProperty("java.home"));
        //set api key to open my version of google maps
        //if this api key expires you can easily make one and replace in the code
        mapComponent = new GoogleMapView(Locale.getDefault().getLanguage(), "AIzaSyCdifICfDROaglC7JApjXH-6ecoB33Er4o");
        mapComponent.addMapInitializedListener(this);
                
        BorderPane bp = new BorderPane();
        ToolBar tb = new ToolBar();

        btnZoomIn = new Button("Zoom In");
        btnZoomIn.setOnAction(e -> {
            map.zoomProperty().set(map.getZoom() + 1);
        });
        btnZoomIn.setDisable(true);

        btnZoomOut = new Button("Zoom Out");
        btnZoomOut.setOnAction(e -> {
            map.zoomProperty().set(map.getZoom() - 1);
        });
        btnZoomOut.setDisable(true);

        lblZoom = new Label();
        lblCenter = new Label();
        lblClick = new Label();
        
        mapTypeCombo = new ComboBox<>();
        mapTypeCombo.setOnAction( e -> {
           map.setMapType(mapTypeCombo.getSelectionModel().getSelectedItem() );
        });
        mapTypeCombo.setDisable(true);
        
        Button btnType = new Button("Map type");
        btnType.setOnAction(e -> {
            map.setMapType(MapTypeIdEnum.SATELLITE);
        });
		
		btnHideMarker = new Button("Hide Marker");
		btnHideMarker.setOnAction(e -> {hideMarker();});
		
		btnDeleteMarker = new Button("Delete Marker");
		btnDeleteMarker.setOnAction(e -> {deleteMarker();});
		
        tb.getItems().addAll(btnZoomIn, btnZoomOut, mapTypeCombo,
                new Label("Zoom: "), lblZoom,
                new Label("Center: "), lblCenter,
                new Label("Click: "), lblClick,
				btnHideMarker, btnDeleteMarker);

        bp.setTop(tb);
        
        bp.setCenter(mapComponent);

        Scene scene = new Scene(bp);
        stage.setScene(scene);
        stage.show();
    }

    DirectionsRenderer renderer;
    
    @Override
    public void mapInitialized() {
        
        //System.out.println("MainApp.mapInitialised....");
        
        //Once the map has been loaded by the Webview, initialize the map details.
    		
		
		//had to comment out a lot of code that was irrelevant to this project. I kept them in in case we need them in the future.
//		Test data file

		

		
//		MAIN FILE
			//each lat/long type
    	/*
			LatLong newLatLong = new LatLong(Main.doubles[0][0], Main.doubles[0][1]);
			mapComponent.addMapReadyListener(() -> {
	    			checkCenter(newLatLong);
			});
			MapOptions option1 = new MapOptions();
		    option1.center(newLatLong)
		           .mapMarker(true)
		           .zoom(9)
		           .overviewMapControl(false)
		           .panControl(false)
		           .rotateControl(false)
		           .scaleControl(false)
		           .streetViewControl(false)
		           .zoomControl(false)
		           .mapType(MapTypeIdEnum.TERRAIN)
		           .clickableIcons(false)
		           .disableDefaultUI(true)
		           .disableDoubleClickZoom(true)
		           .keyboardShortcuts(false)
		           .styleString("[{'featureType':'landscape','stylers':[{'saturation':-100},{'lightness':65},{'visibility':'on'}]},{'featureType':'poi','stylers':[{'saturation':-100},{'lightness':51},{'visibility':'simplified'}]},{'featureType':'road.highway','stylers':[{'saturation':-100},{'visibility':'simplified'}]},{\"featureType\":\"road.arterial\",\"stylers\":[{\"saturation\":-100},{\"lightness\":30},{\"visibility\":\"on\"}]},{\"featureType\":\"road.local\",\"stylers\":[{\"saturation\":-100},{\"lightness\":40},{\"visibility\":\"on\"}]},{\"featureType\":\"transit\",\"stylers\":[{\"saturation\":-100},{\"visibility\":\"simplified\"}]},{\"featureType\":\"administrative.province\",\"stylers\":[{\"visibility\":\"off\"}]},{\"featureType\":\"water\",\"elementType\":\"labels\",\"stylers\":[{\"visibility\":\"on\"},{\"lightness\":-25},{\"saturation\":-100}]},{\"featureType\":\"water\",\"elementType\":\"geometry\",\"stylers\":[{\"hue\":\"#ffff00\"},{\"lightness\":-25},{\"saturation\":-97}]}]");
		    map = mapComponent.createMap(option1,false);
		    
		
		    LatLong newLatLong2 = new LatLong(Main.doubles[1][0], Main.doubles[1][1]);
			mapComponent.addMapReadyListener(() -> {
	    			checkCenter(newLatLong2);
			});
			MapOptions option2 = new MapOptions();
		    option2.center(newLatLong2)
		           .mapMarker(true)
		           .zoom(9)
		           .overviewMapControl(false)
		           .panControl(false)
		           .rotateControl(false)
		           .scaleControl(false)
		           .streetViewControl(false)
		           .zoomControl(false)
		           .mapType(MapTypeIdEnum.TERRAIN)
		           .clickableIcons(false)
		           .disableDefaultUI(true)
		           .disableDoubleClickZoom(true)
		           .keyboardShortcuts(false)
		           .styleString("[{'featureType':'landscape','stylers':[{'saturation':-100},{'lightness':65},{'visibility':'on'}]},{'featureType':'poi','stylers':[{'saturation':-100},{'lightness':51},{'visibility':'simplified'}]},{'featureType':'road.highway','stylers':[{'saturation':-100},{'visibility':'simplified'}]},{\"featureType\":\"road.arterial\",\"stylers\":[{\"saturation\":-100},{\"lightness\":30},{\"visibility\":\"on\"}]},{\"featureType\":\"road.local\",\"stylers\":[{\"saturation\":-100},{\"lightness\":40},{\"visibility\":\"on\"}]},{\"featureType\":\"transit\",\"stylers\":[{\"saturation\":-100},{\"visibility\":\"simplified\"}]},{\"featureType\":\"administrative.province\",\"stylers\":[{\"visibility\":\"off\"}]},{\"featureType\":\"water\",\"elementType\":\"labels\",\"stylers\":[{\"visibility\":\"on\"},{\"lightness\":-25},{\"saturation\":-100}]},{\"featureType\":\"water\",\"elementType\":\"geometry\",\"stylers\":[{\"hue\":\"#ffff00\"},{\"lightness\":-25},{\"saturation\":-97}]}]");
		    map = mapComponent.createMap(option2,false);
		    
		    LatLong newLatLong3 = new LatLong(Main.doubles[2][0], Main.doubles[2][1]);
			mapComponent.addMapReadyListener(() -> {
	    			checkCenter(newLatLong);
			});
			MapOptions option3 = new MapOptions();
		    option3.center(newLatLong3)
		           .mapMarker(true)
		           .zoom(9)
		           .overviewMapControl(false)
		           .panControl(false)
		           .rotateControl(false)
		           .scaleControl(false)
		           .streetViewControl(false)
		           .zoomControl(false)
		           .mapType(MapTypeIdEnum.TERRAIN)
		           .clickableIcons(false)
		           .disableDefaultUI(true)
		           .disableDoubleClickZoom(true)
		           .keyboardShortcuts(false)
		           .styleString("[{'featureType':'landscape','stylers':[{'saturation':-100},{'lightness':65},{'visibility':'on'}]},{'featureType':'poi','stylers':[{'saturation':-100},{'lightness':51},{'visibility':'simplified'}]},{'featureType':'road.highway','stylers':[{'saturation':-100},{'visibility':'simplified'}]},{\"featureType\":\"road.arterial\",\"stylers\":[{\"saturation\":-100},{\"lightness\":30},{\"visibility\":\"on\"}]},{\"featureType\":\"road.local\",\"stylers\":[{\"saturation\":-100},{\"lightness\":40},{\"visibility\":\"on\"}]},{\"featureType\":\"transit\",\"stylers\":[{\"saturation\":-100},{\"visibility\":\"simplified\"}]},{\"featureType\":\"administrative.province\",\"stylers\":[{\"visibility\":\"off\"}]},{\"featureType\":\"water\",\"elementType\":\"labels\",\"stylers\":[{\"visibility\":\"on\"},{\"lightness\":-25},{\"saturation\":-100}]},{\"featureType\":\"water\",\"elementType\":\"geometry\",\"stylers\":[{\"hue\":\"#ffff00\"},{\"lightness\":-25},{\"saturation\":-97}]}]");
		    map = mapComponent.createMap(option3,false);
		    
		    LatLong newLatLong4 = new LatLong(Main.doubles[0][0], Main.doubles[0][1]);
			mapComponent.addMapReadyListener(() -> {
	    			checkCenter(newLatLong);
			});
			MapOptions option4 = new MapOptions();
		    option4.center(newLatLong4)
		           .mapMarker(true)
		           .zoom(9)
		           .overviewMapControl(false)
		           .panControl(false)
		           .rotateControl(false)
		           .scaleControl(false)
		           .streetViewControl(false)
		           .zoomControl(false)
		           .mapType(MapTypeIdEnum.TERRAIN)
		           .clickableIcons(false)
		           .disableDefaultUI(true)
		           .disableDoubleClickZoom(true)
		           .keyboardShortcuts(false)
		           .styleString("[{'featureType':'landscape','stylers':[{'saturation':-100},{'lightness':65},{'visibility':'on'}]},{'featureType':'poi','stylers':[{'saturation':-100},{'lightness':51},{'visibility':'simplified'}]},{'featureType':'road.highway','stylers':[{'saturation':-100},{'visibility':'simplified'}]},{\"featureType\":\"road.arterial\",\"stylers\":[{\"saturation\":-100},{\"lightness\":30},{\"visibility\":\"on\"}]},{\"featureType\":\"road.local\",\"stylers\":[{\"saturation\":-100},{\"lightness\":40},{\"visibility\":\"on\"}]},{\"featureType\":\"transit\",\"stylers\":[{\"saturation\":-100},{\"visibility\":\"simplified\"}]},{\"featureType\":\"administrative.province\",\"stylers\":[{\"visibility\":\"off\"}]},{\"featureType\":\"water\",\"elementType\":\"labels\",\"stylers\":[{\"visibility\":\"on\"},{\"lightness\":-25},{\"saturation\":-100}]},{\"featureType\":\"water\",\"elementType\":\"geometry\",\"stylers\":[{\"hue\":\"#ffff00\"},{\"lightness\":-25},{\"saturation\":-97}]}]");
		    map = mapComponent.createMap(option4,false);
		    
		    
		    
		*/    
		    
        LatLong center = new LatLong(47.606189, -122.335842);
        mapComponent.addMapReadyListener(() -> {
            // This call will fail unless the map is completely ready.
            checkCenter(center);
            
            
        });
        
        
        MapOptions options = new MapOptions();
        options.center(center)
                .mapMarker(true)
                .zoom(9)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .mapType(MapTypeIdEnum.TERRAIN)
                .clickableIcons(false)
                .disableDefaultUI(true)
                .disableDoubleClickZoom(true)
                .keyboardShortcuts(false)
                .styleString("[{'featureType':'landscape','stylers':[{'saturation':-100},{'lightness':65},{'visibility':'on'}]},{'featureType':'poi','stylers':[{'saturation':-100},{'lightness':51},{'visibility':'simplified'}]},{'featureType':'road.highway','stylers':[{'saturation':-100},{'visibility':'simplified'}]},{\"featureType\":\"road.arterial\",\"stylers\":[{\"saturation\":-100},{\"lightness\":30},{\"visibility\":\"on\"}]},{\"featureType\":\"road.local\",\"stylers\":[{\"saturation\":-100},{\"lightness\":40},{\"visibility\":\"on\"}]},{\"featureType\":\"transit\",\"stylers\":[{\"saturation\":-100},{\"visibility\":\"simplified\"}]},{\"featureType\":\"administrative.province\",\"stylers\":[{\"visibility\":\"off\"}]},{\"featureType\":\"water\",\"elementType\":\"labels\",\"stylers\":[{\"visibility\":\"on\"},{\"lightness\":-25},{\"saturation\":-100}]},{\"featureType\":\"water\",\"elementType\":\"geometry\",\"stylers\":[{\"hue\":\"#ffff00\"},{\"lightness\":-25},{\"saturation\":-97}]}]");
        
        //[{\"featureType\":\"landscape\",\"stylers\":[{\"saturation\":-100},{\"lightness\":65},{\"visibility\":\"on\"}]},{\"featureType\":\"poi\",\"stylers\":[{\"saturation\":-100},{\"lightness\":51},{\"visibility\":\"simplified\"}]},{\"featureType\":\"road.highway\",\"stylers\":[{\"saturation\":-100},{\"visibility\":\"simplified\"}]},{\"featureType\":\"road.arterial\",\"stylers\":[{\"saturation\":-100},{\"lightness\":30},{\"visibility\":\"on\"}]},{\"featureType\":\"road.local\",\"stylers\":[{\"saturation\":-100},{\"lightness\":40},{\"visibility\":\"on\"}]},{\"featureType\":\"transit\",\"stylers\":[{\"saturation\":-100},{\"visibility\":\"simplified\"}]},{\"featureType\":\"administrative.province\",\"stylers\":[{\"visibility\":\"off\"}]},{\"featureType\":\"water\",\"elementType\":\"labels\",\"stylers\":[{\"visibility\":\"on\"},{\"lightness\":-25},{\"saturation\":-100}]},{\"featureType\":\"water\",\"elementType\":\"geometry\",\"stylers\":[{\"hue\":\"#ffff00\"},{\"lightness\":-25},{\"saturation\":-97}]}]
        map = mapComponent.createMap(options,false);
        directions = mapComponent.getDirec();
        
        map.setHeading(123.2);
//        System.out.println("Heading is: " + map.getHeading() );
        //checking in EARTHQUAKEDATAGUICONTROLLER if datebool then try to create a marker with a window that comes up on screen with the number  of which earthquake it is.
        if(EarthquakeDataGUIController.datebool) {
	        	for (int i = 0; i < EarthquakeDataGUIController.datedoubles.length; i++) {
	        		if(!(EarthquakeDataGUIController.datedoubles[i][0] == 0)){
		        		MarkerOptions markerOptions = new MarkerOptions();
			        LatLong markerLatLong = new LatLong(EarthquakeDataGUIController.datedoubles[i][0], EarthquakeDataGUIController.datedoubles[i][1]);
			        markerOptions.position(markerLatLong)
			                .title("Earthquake")
			                .icon("mymarker.png")
			                .animation(Animation.DROP)
			                .visible(true);
			
			        final Marker myMarker = new Marker(markerOptions);
			        map.addMarker(myMarker);
			        InfoWindowOptions infoOptions = new InfoWindowOptions();
			        infoOptions.content("<h2>Eearthquake " + i + "</h2>")
			                .position(center);
		
			        InfoWindow window = new InfoWindow(infoOptions);
			        window.open(map, myMarker);
	        		}
	        	}
        }else if(EarthquakeDataGUIController.depthbool) {
        	for (int i = 0; i < EarthquakeDataGUIController.depthdoubles.length; i++) {
        		if(!(EarthquakeDataGUIController.depthdoubles[i][0] == 0)){
	        		MarkerOptions markerOptions = new MarkerOptions();
		        LatLong markerLatLong = new LatLong(EarthquakeDataGUIController.depthdoubles[i][0], EarthquakeDataGUIController.depthdoubles[i][1]);
		        markerOptions.position(markerLatLong)
		                .title("Earthquake")
		                .icon("mymarker.png")
		                .animation(Animation.DROP)
		                .visible(true);
		
		        final Marker myMarker = new Marker(markerOptions);
		        map.addMarker(myMarker);
		        InfoWindowOptions infoOptions = new InfoWindowOptions();
		        infoOptions.content("<h2>Eearthquake " + i + "</h2>")
		                .position(center);
	
		        InfoWindow window = new InfoWindow(infoOptions);
		        window.open(map, myMarker);
        		}
        	}
    } else {
    		//only method that works for opening the google maps view
    		//since every earthquake is instantiated here, it takes much to long too load the data in, this is why we tried implementing a system to narrow down the results however the array will always return null
        for (int i = 0; i < EarthquakeDataGUIController.doubles.length; i++) {
        		
        		if(!(EarthquakeDataGUIController.doubles[i][0] == 0)){
	        		MarkerOptions markerOptions = new MarkerOptions();
		        LatLong markerLatLong = new LatLong(EarthquakeDataGUIController.doubles[i][0], EarthquakeDataGUIController.doubles[i][1]);
		        markerOptions.position(markerLatLong)
		                .title("Earthquake")
		                .icon("mymarker.png")
		                .animation(Animation.DROP)
		                .visible(true);
		
		        final Marker myMarker = new Marker(markerOptions);
		        map.addMarker(myMarker);
		        InfoWindowOptions infoOptions = new InfoWindowOptions();
		        infoOptions.content("<h2>Eearthquake " + i + "</h2>")
		                .position(center);
	
		        InfoWindow window = new InfoWindow(infoOptions);
		        window.open(map, myMarker);
        		}
        }
    }
        //MarkerOptions markerOptions2 = new MarkerOptions();
        //LatLong markerLatLong2 = new LatLong(Main.doubles[0][0], Main.doubles[0][1]);
        //markerOptions2.position(markerLatLong2)
        //        .title("earthquake 1")
        //        .icon("mymarker.png")
        //        .animation(Animation.DROP)
        //        .visible(true);

        //final Marker myMarker2 = new Marker(markerOptions2);

        //markerOptions2 = new MarkerOptions();
        

        
        //map.addMarker(myMarker2);

        
        
        
        map.fitBounds(new LatLongBounds(new LatLong(30, 120), center));
        //map.fitBounds(new LatLongBounds(new LatLong(30, 120), newLatLong));
        //map.fitBounds(new LatLongBounds(new LatLong(30, 120), newLatLong2));
        //map.fitBounds(new LatLongBounds(new LatLong(30, 120), newLatLong3));
        //map.fitBounds(new LatLongBounds(new LatLong(30, 120), newLatLong4));
//        System.out.println("Bounds : " + map.getBounds());

        lblCenter.setText(map.getCenter().toString());
        map.centerProperty().addListener((ObservableValue<? extends LatLong> obs, LatLong o, LatLong n) -> {
            lblCenter.setText(n.toString());
        });

        lblZoom.setText(Integer.toString(map.getZoom()));
        map.zoomProperty().addListener((ObservableValue<? extends Number> obs, Number o, Number n) -> {
            lblZoom.setText(n.toString());
        });

//      map.addStateEventHandler(MapStateEventType.center_changed, () -> {
//			System.out.println("center_changed: " + map.getCenter());
//		});
//        map.addStateEventHandler(MapStateEventType.tilesloaded, () -> {
//			System.out.println("We got a tilesloaded event on the map");
//		});

  
        map.addUIEventHandler(UIEventType.click, (JSObject obj) -> {
            LatLong ll = new LatLong((JSObject) obj.getMember("latLng"));
            //System.out.println("LatLong: lat: " + ll.getLatitude() + " lng: " + ll.getLongitude());
            lblClick.setText(ll.toString());
        });

        btnZoomIn.setDisable(false);
        btnZoomOut.setDisable(false);
        mapTypeCombo.setDisable(false);
        
        mapTypeCombo.getItems().addAll( MapTypeIdEnum.ALL );

        //LatLong[] ary = new LatLong[]{markerLatLong};
        //MVCArray mvc = new MVCArray(ary);

        //PolylineOptions polyOpts = new PolylineOptions()
                //.path(mvc)
                //.strokeColor("red")
                //.strokeWeight(2);

        //Polyline poly = new Polyline(polyOpts);
        //map.addMapShape(poly);
        //map.addUIEventHandler(poly, UIEventType.click, (JSObject obj) -> {
            //LatLong ll = new LatLong((JSObject) obj.getMember("latLng"));
//            System.out.println("You clicked the line at LatLong: lat: " + ll.getLatitude() + " lng: " + ll.getLongitude());
        //});
        
        LatLong poly1 = new LatLong(47.429945, -122.84363);
        LatLong poly2 = new LatLong(47.361153, -123.03040);
        //LatLong poly3 = new LatLong(47.387193, -123.11554);
        //LatLong poly4 = new LatLong(47.585789, -122.96722);
        LatLong[] pAry = new LatLong[]{poly1, poly2};
        MVCArray pmvc = new MVCArray(pAry);

        PolygonOptions polygOpts = new PolygonOptions()
                .paths(pmvc)
                .strokeColor("blue")
                .strokeWeight(2)
                .editable(false)
                .fillColor("lightBlue")
                .fillOpacity(0.5);

        Polygon pg = new Polygon(polygOpts);
        map.addMapShape(pg);
        map.addUIEventHandler(pg, UIEventType.click, (JSObject obj) -> {
            //polygOpts.editable(true);
            pg.setEditable(!pg.getEditable());
        });
        /*for (int i = 0; i < Main.doubles.length; i++) {
        		LatLong earthquakeData = new LatLong(Main.doubles[i][0], Main.doubles[i][1]);
        		
                CircleOptions earthquakeCircleOptions = new CircleOptions()
                        .center(earthquakeData)
                        .radius(5000)
                        .strokeColor("green")
                        .strokeWeight(2)
                        .fillColor("orange")
                        .fillOpacity(0.3);

                Circle newCircle = new Circle(earthquakeCircleOptions);
                map.addMapShape(newCircle);
                map.addUIEventHandler(newCircle, UIEventType.click, (JSObject obj) -> {
                    newCircle.setEditable(!newCircle.getEditable());
                });
        }
        */
        /*
        LatLong centreC = new LatLong(47.545481, -121.87384);
        CircleOptions cOpts = new CircleOptions()
                .center(centreC)
                .radius(5000)
                .strokeColor("green")
                .strokeWeight(2)
                .fillColor("orange")
                .fillOpacity(0.3);

        Circle c = new Circle(cOpts);
        map.addMapShape(c);
        map.addUIEventHandler(c, UIEventType.click, (JSObject obj) -> {
            c.setEditable(!c.getEditable());
        });
        */

        LatLongBounds llb = new LatLongBounds(new LatLong(47.533893, -122.89856), new LatLong(47.580694, -122.80312));
        RectangleOptions rOpts = new RectangleOptions()
                .bounds(llb)
                .strokeColor("black")
                .strokeWeight(2)
                .fillColor("null");

        Rectangle rt = new Rectangle(rOpts);
        map.addMapShape(rt);

        LatLong arcC = new LatLong(47.227029, -121.81641);
        double startBearing = 0;
        double endBearing = 30;
        double radius = 30000;

        MVCArray path = ArcBuilder.buildArcPoints(arcC, startBearing, endBearing, radius);
        path.push(arcC);

        Polygon arc = new Polygon(new PolygonOptions()
                .paths(path)
                .strokeColor("blue")
                .fillColor("lightBlue")
                .fillOpacity(0.3)
                .strokeWeight(2)
                .editable(false));

        map.addMapShape(arc);
        map.addUIEventHandler(arc, UIEventType.click, (JSObject obj) -> {
            arc.setEditable(!arc.getEditable());
        });
        
        GeocodingService gs = new GeocodingService();
        
        DirectionsService ds = new DirectionsService();
        renderer = new DirectionsRenderer(true, map, directions);
        
        DirectionsWaypoint[] dw = new DirectionsWaypoint[2];
        dw[0] = new DirectionsWaypoint("São Paulo - SP");
        dw[1] = new DirectionsWaypoint("Juiz de Fora - MG");
        
        DirectionsRequest dr = new DirectionsRequest(
                "Belo Horizonte - MG",
                "Rio de Janeiro - RJ",
                TravelModes.DRIVING,
                dw, false);
        ds.getRoute(dr, this, renderer);
        
        //LatLong[] location = new LatLong[1];
        //location[0] = new LatLong(-19.744056, -43.958699);
        //LocationElevationRequest loc = new LocationElevationRequest(location);
        //ElevationService es = new ElevationService();
        //es.getElevationForLocations(loc, this);
        
    }
    
    

	
	
	private void hideMarker() {
//		System.out.println("deleteMarker");
		
		//boolean visible = myMarker2.getVisible();
		
		//System.out.println("Marker was visible? " + visible);
		
		//myMarker2.setVisible(! visible);

//				markerOptions2.visible(Boolean.FALSE);
//				myMarker2.setOptions(markerOptions2);
//		System.out.println("deleteMarker - made invisible?");
	}
	
	private void deleteMarker() {
		//System.out.println("Marker was removed?");
		//map.removeMarker(myMarker2);
	}
	
    private void checkCenter(LatLong center) {
//        System.out.println("Testing fromLatLngToPoint using: " + center);
//        Point2D p = map.fromLatLngToPoint(center);
//        System.out.println("Testing fromLatLngToPoint result: " + p);
//        System.out.println("Testing fromLatLngToPoint expected: " + mapComponent.getWidth()/2 + ", " + mapComponent.getHeight()/2);
    }
    
    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.setProperty("java.net.useSystemProxies", "true");
        launch(args);
        
    }

    @Override
    public void elevationsReceived(ElevationResult[] results, ElevationStatus status) {
        if(status.equals(ElevationStatus.OK)){
            for(ElevationResult e : results){
                System.out.println(" Elevation on "+ e.getLocation().toString() + " is " + e.getElevation());
            }
        }
    }

    @Override
    public void geocodedResultsReceived(GeocodingResult[] results, GeocoderStatus status) {
        if(status.equals(GeocoderStatus.OK)){
            for(GeocodingResult e : results){
                System.out.println(e.getVariableName());
                System.out.println("GEOCODE: " + e.getFormattedAddress() + "\n" + e.toString());
            }
            
        }
        
    }

    @Override
    public void directionsReceived(DirectionsResult results, DirectionStatus status) {
        if(status.equals(DirectionStatus.OK)){
            
            System.out.println("OK");
            
            DirectionsResult e = results;
            GeocodingService gs = new GeocodingService();
            
            System.out.println("SIZE ROUTES: " + e.getRoutes().size() + "\n" + "ORIGIN: " + e.getRoutes().get(0).getLegs().get(0).getStartLocation());
            //gs.reverseGeocode(e.getRoutes().get(0).getLegs().get(0).getStartLocation().getLatitude(), e.getRoutes().get(0).getLegs().get(0).getStartLocation().getLongitude(), this);
            System.out.println("LEGS SIZE: " + e.getRoutes().get(0).getLegs().size());
            System.out.println("WAYPOINTS " +e.getGeocodedWaypoints().size());
            /*double d = 0;
            for(DirectionsLeg g : e.getRoutes().get(0).getLegs()){
                d += g.getDistance().getValue();
                System.out.println("DISTANCE " + g.getDistance().getValue());
            }*/
            try{
                System.out.println("Distancia total = " + e.getRoutes().get(0).getLegs().get(0).getDistance().getText());
            } catch(Exception ex){
                System.out.println("ERRO: " + ex.getMessage());
            }
            System.out.println("LEG(0)");
            System.out.println(e.getRoutes().get(0).getLegs().get(0).getSteps().size());
            /*for(DirectionsSteps ds : e.getRoutes().get(0).getLegs().get(0).getSteps()){
                System.out.println(ds.getStartLocation().toString() + " x " + ds.getEndLocation().toString());
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(ds.getStartLocation())
                        .title(ds.getInstructions())
                        .animation(Animation.DROP)
                        .visible(true);
                Marker myMarker = new Marker(markerOptions);
                map.addMarker(myMarker);
            }
                    */
            System.out.println(renderer.toString());
        }
    }
}
