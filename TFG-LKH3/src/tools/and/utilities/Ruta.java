package tools.and.utilities;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.MapPolylineImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;

import com.osrm.services.Route;

public class Ruta {
	
	double duration;
	double distance;
	String weight_name;
	
	List<Coordinate> coordinates;
	
	Coordinate coordinateFrom;
	Coordinate coordinateTo;
	
	String nameFrom;
	String nameTo;
	
	public Ruta() {
		duration = 0.0;
		distance = 0.0;
		weight_name = "";
		
		coordinates = new ArrayList<Coordinate>();
		
		coordinateFrom = new Coordinate(0,0);
		coordinateTo = new Coordinate(0,0);
		
		nameFrom = "";
		nameTo = "";
	}
	
	public Ruta(Coordinate from, Coordinate to) {
		coordinateFrom = from;
		coordinateTo = to;
		
		boolean flag = true;
		RouteJSON json;
		Route rt = new Route();
		while(flag) {
			//JSONObject routingWithProfile = rt.getFastestRoute("-3.695808,37.221539;-3.515645,36.744934", "car");
			String msg = String.valueOf(coordinateFrom.getLon())+","+
					String.valueOf(coordinateFrom.getLat())+";"+
					String.valueOf(coordinateTo.getLon())+","+
					String.valueOf(coordinateTo.getLat());
	
	
			JSONObject routingWithProfile = rt.getFastestRoute(msg, "car");
			if(routingWithProfile != null) {
				
				json = new RouteJSON(routingWithProfile);
				//fil.add(temp);
				duration = json.getDuration();
				distance = json.getDistance();
				weight_name = "";
				coordinates = json.getCoordinates();
				nameFrom = json.getNameFrom();
				nameTo = json.getNameTo();
				flag = false;
			}
			else {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				
		}
		//RouteJSON rjson = new RouteJSON(json);
		
		
		
		
	}
	
	public double getDuration() {
		return duration;
	}
	

	/*public void RouteJSON(JSONObject json) {
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
					JSONObject tempRoutes = routes.getJSONObject(0);
					duration = tempRoutes.getDouble("duration");
					distance = tempRoutes.getDouble("distance");
					weight_name = tempRoutes.getString("weight_name");
					JSONObject tempGeometry = tempRoutes.getJSONObject("geometry");
					JSONArray tempCoordinates = tempGeometry.getJSONArray("coordinates");
				
					coordinates = new ArrayList<Coordinate>();
					for(int i=0; i<tempCoordinates.length();i++) {
	        			JSONArray dato = tempCoordinates.getJSONArray(i);
	        			coordinates.add(new Coordinate((double)dato.get(1), (double)dato.get(0)));
	        		}
					poly = new MapPolylineImpl(coordinates);
							
				
					JSONArray tempWaypoints = json.getJSONArray("waypoints");
					if(tempWaypoints.length() == 2) {
						JSONObject tp1 = (JSONObject)tempWaypoints.get(0);
						nameFrom = tp1.getString("name");
						JSONArray tp11 = tp1.getJSONArray("location");
						coordinateFrom = new Coordinate(tp11.getLong(0), tp11.getLong(1));
						JSONObject tp2 = (JSONObject)tempWaypoints.get(0);
						nameTo = tp2.getString("name");
						JSONArray tp22 = tp2.getJSONArray("location");
						coordinateTo = new Coordinate(tp22.getLong(0), tp22.getLong(1));
					}
				
				}
			}						
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}*/
	
	public void setDuration(double d) {
		duration = d;
	}
	
	public double getDistance() {
		return distance;
	}
	
	public void setDistance(double d) {
		distance = d;
	}
	
	public String getWeightName() {
		return weight_name;
	}
	
	public void setWeightName(String n) {
		weight_name = n;
	}
	
	public List<Coordinate> getCoordinates() {
		return coordinates;
	}
	
	public void setCoordinates(List<Coordinate> c) {
		coordinates = c;
	}
	
	public Coordinate getCoordinateFrom() {
		return coordinateFrom;
	}
	
	public void setCoordinateFrom(Coordinate c) {
		coordinateFrom = c;
	}
	
	public Coordinate getCoordinateTo() {
		return coordinateTo;
	}
	
	public void setCoordinateTo(Coordinate c) {
		coordinateTo = c;
	}
	
	public String getNameFrom() {
		return nameFrom;
	}
	
	public void setNameFrom(String n) {
		nameFrom = n;
	}
	public String getNameTo() {
		return nameTo;
	}
	
	public void setNameTo(String n) {
		nameTo = n;
	}
	
	
	
}
