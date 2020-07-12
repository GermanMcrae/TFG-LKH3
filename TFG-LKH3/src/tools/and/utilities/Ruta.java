package tools.and.utilities;

import java.util.List;

import org.openstreetmap.gui.jmapviewer.Coordinate;

public class Ruta {
	
	double duration;
	double distance;
	String weight_name;
	
	List<Coordinate> coordinates;
	
	Coordinate coordinateFrom;
	Coordinate coordinateTo;
	
	String nameFrom;
	String nameTo;
	
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
