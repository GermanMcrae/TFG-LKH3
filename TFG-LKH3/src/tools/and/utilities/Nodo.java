package tools.and.utilities;

import java.util.ArrayList;
import java.util.List;
import org.openstreetmap.gui.jmapviewer.Coordinate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "nodo")
@XmlAccessorType (XmlAccessType.FIELD)
public class Nodo {
	
	private String name;
	private double capacity;
	private Coordinate coordinate;
	
	@XmlElement(name = "listRoutes")
	List<Ruta> listRoutes;
	
	public Nodo() {
		name = "";
		capacity = 0.0;
		coordinate = new Coordinate(0.0, 0.0);
		
		listRoutes = new ArrayList<Ruta>();
		
	}
	
	public Nodo(String n, double cap, Coordinate coor, List<Ruta> lR) {
		name = n;
		capacity = cap;
		coordinate = coor;
		
		listRoutes = lR;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String n) {
		name = n;
	}
	
	public double getCapacity() {
		return capacity;
	}
	
	public void setCapacity(double c) {
		capacity = c;
	}
	
	public Coordinate getCoordinate() {
		return coordinate;
	}
	
	public void setCoordinate(Coordinate c) {
		coordinate = c;
	}
	
	public List<Ruta> getListRoutes() {
		return listRoutes;
	}
	
	public void setListRoutes(List<Ruta> c) {
		listRoutes = c;
	}
	
	public void addRuta(Ruta r) {
		listRoutes.add(r);
	}

}
