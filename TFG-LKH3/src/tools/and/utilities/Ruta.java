package tools.and.utilities;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.json.JSONObject;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import com.osrm.services.Route;

@XmlRootElement(name = "ruta")
@XmlAccessorType (XmlAccessType.FIELD)
public class Ruta {
	
	private double duration;
	private double distance;
	private String weight_name;
	
	@XmlElement(name = "coordinates")
	private List<Coordinate> coordinates;
	
	private Coordinate coordinateFrom;
	private Coordinate coordinateTo;
	
	private String nameFrom;
	private String nameTo;
	
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
	
	public Ruta(double du, double dis, String weight_n, List<Coordinate> cs, Coordinate cF, Coordinate cT, String nF, String nT) {
		duration = du;
		distance = dis;
		weight_name = weight_n;
		
		coordinates = cs;
		
		coordinateFrom = cF;
		coordinateTo = cT;
		
		nameFrom = nF;
		nameTo = nT;
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
