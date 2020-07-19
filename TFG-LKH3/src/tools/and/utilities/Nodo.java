package tools.and.utilities;

import java.util.ArrayList;
import java.util.List;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Nodo {
	
	private String name;
	private double capacity;
	Coordinate coordinate;
	
	List<Ruta> listRoutes;
	
	public Nodo() {
		name = "";
		capacity = 0.0;
		coordinate = new Coordinate(0.0, 0.0);
		
		listRoutes = new ArrayList<Ruta>();
		
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
