package tools.and.utilities;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.MapPolylineImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapPolygon;

public class RouteJSON {
	
	JSONArray routes;
	String codeString;//code":"Ok",
	boolean code;
	double duration;
	double distance;
	String weight_name;
	MapPolygon poly;
	
	List<Coordinate> coordinates;
	
	Coordinate coordinateFrom;
	Coordinate coordinateTo;
	
	String nameFrom;
	String nameTo;
	
	JSONArray waypoints;

	
	
	public RouteJSON() {
		routes = new JSONArray();
		codeString = "";//code":"Ok",
		code = false;
		duration = 0.0;
		distance = 0.0;
		weight_name = "";
		poly = null;
		List<Coordinate> coordinates = new ArrayList<Coordinate>();;
		
		
		coordinateFrom = null;
		coordinateTo = null;
		
		nameFrom = "";
		nameTo = "";
	}

	public RouteJSON(JSONObject json) {
		try {
			System.out.println(json);
			if(json != null) {
				
			
				routes = json.getJSONArray("routes");
				codeString = json.getString("code");
				waypoints = json.getJSONArray("waypoints");
			
				if(codeString.equals("Ok")) {
					//System.out.println("codeString.equals(\"Ok\")");
					code = true;
				}
				else {
					code = false;
				}
				if(code) {
					var tempRoutes = routes.getJSONObject(0);
					duration = tempRoutes.getDouble("duration");
					distance = tempRoutes.getDouble("distance");
					weight_name = tempRoutes.getString("weight_name");
					var tempGeometry = tempRoutes.getJSONObject("geometry");
					var tempCoordinates = tempGeometry.getJSONArray("coordinates");
				
					coordinates = new ArrayList<Coordinate>();
					for(int i=0; i<tempCoordinates.length();i++) {
	        			JSONArray dato = tempCoordinates.getJSONArray(i);
	        			coordinates.add(new Coordinate((double)dato.get(1), (double)dato.get(0)));
	        		}
					poly = new MapPolylineImpl(coordinates);
							
				
					var tempWaypoints = json.getJSONArray("waypoints");
					if(tempWaypoints.length() == 2) {
						var tp1 = (JSONObject)tempWaypoints.get(0);
						nameFrom = tp1.getString("name");
						var tp11 = tp1.getJSONArray("location");
						coordinateFrom = new Coordinate(tp11.getLong(0), tp11.getLong(1));
						var tp2 = (JSONObject)tempWaypoints.get(0);
						nameTo = tp2.getString("name");
						var tp22 = tp2.getJSONArray("location");
						coordinateTo = new Coordinate(tp22.getLong(0), tp22.getLong(1));
					}
				
				}
			}						
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public double getDuration(){
		return duration;
	}
	
	public double getDistance() {
		return distance;
	}
	
	public String getNameFrom() {
		return nameFrom;
	}
	
	public String getNameTo() {
		return nameTo;
	}
	
	public Coordinate getCoordinateFrom() {
		return coordinateFrom;
	}
	
	public Coordinate getCoordinateTo() {
		return coordinateTo;
	}
	
	public List<Coordinate> getCoordinates(){
		return coordinates;
	}
	
	public boolean getCode() {
		return code;
	}
	
	public MapPolygon getPolyline() {
		return poly;
	}
	
}
