package tools.and.utilities;

import java.util.List;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Nodo {
	
	private String name;
	private int capacity;
	Coordinate coordinate;
	
	List<Ruta> listRoutes;
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String n) {
		name = n;
	}
	
	public int getCapacity() {
		return capacity;
	}
	
	public void setCapacity(int c) {
		capacity = c;
	}
	
	/*public double getLatitude() {
		return latitude;
	}
	
	public void setLatitude(double l) {
		latitude = l;
	}
	
	public double getLongitude() {
		return longitude;
	}
	
	public void setLongitude(double l) {
		longitude = l;
	}*/

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
	

}


/*	
@XmlType
class Localidad{
	private String nombre;
	private int cp;
 
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCp() {
		return cp;
	}
	public void setCp(int cp) {
		this.cp = cp;
	}
}*/