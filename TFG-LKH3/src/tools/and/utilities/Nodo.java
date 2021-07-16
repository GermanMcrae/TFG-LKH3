package tools.and.utilities;

import java.util.ArrayList;
import java.util.List;
import org.openstreetmap.gui.jmapviewer.Coordinate;

import javax.swing.table.DefaultTableModel;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "nodo")
@XmlAccessorType (XmlAccessType.FIELD)
public class Nodo {
	
	private String name;
	private int demand;
	private Coordinate coordinate;
	
	@XmlElement(name = "listRoutes")
	List<Ruta> listRoutes;
	
	public Nodo() {
		name = "";
		demand = 0;
		coordinate = new Coordinate(0.0, 0.0);
		
		listRoutes = new ArrayList<Ruta>();
		
	}
	
	public Nodo(String n, int dem, Coordinate coor, List<Ruta> lR) {
		name = n;
		demand = dem;
		coordinate = coor;
		
		listRoutes = lR;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String n) {
		name = n;
	}
	
	public int getDemand() {
		return demand;
	}
	
	public void setDemand(int d) {
		demand = d;
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
	
	public void deleteRuta(Ruta r) {
		listRoutes.remove(r);
	}
	
	public void deleteRuta(int i) {
		//System.out.println("Delete ruta, listRoutes.size:"+listRoutes.size()+" i:"+i);
		listRoutes.remove(i);
	}
	
	@Override
	public String toString() {
	    return name+" Demand["+demand+"]";
	}
	
	public boolean isEqual(Nodo n) {
		boolean value = true;
		if(!name.equals(n.getName()))
			return false;
		if(demand != n.demand)
			return false;
		if(!coordinate.equals(n.getCoordinate()))
			return false;
		if(listRoutes.size() != n.getListRoutes().size())
			return false;
		else if(listRoutes.size() == n.getListRoutes().size()) {
			for(int i=0;i<listRoutes.size();i++) {
				if(!listRoutes.get(i).equals(n.getListRoutes().get(i)))
					return false;
			}
		}
		
		
		
		return true;
	}
	
	public String getVectorDistanceForMatrix(int n) {
		String text = "";
		for(int i=0; i<listRoutes.size();i++) {
			if(i == n) {
				text += "0 "+listRoutes.get(i).getDistance()+" ";
			}
			else {
				text += listRoutes.get(i).getDistance()+" ";
			}
		}
		//add 0 last vector
		if(n == listRoutes.size())
			text += 0;
		return text;
	}

	public String getVectorDurationForMatrix(int n) {
		String text = "";
		for(int i=0; i<listRoutes.size();i++) {
			if(i == n) {
				text += "0 "+listRoutes.get(i).getDuration()+" ";
			}
			else {
				text += listRoutes.get(i).getDuration()+" ";
			}
		}
		//add 0 last vector
		if(n == listRoutes.size())
			text += 0;
		return text;
	}
	
	public DefaultTableModel GetTableRoutesNodo(){
		
		String[] columnNames = {"Name","Origin", "Destination", "Duration", "Distance",};
		Object[][] datos = new Object[listRoutes.size()][];;
		for(int i = 0; i<listRoutes.size();i++) {
			Object[] d = new Object[]{
				listRoutes.get(i).getNameDestinate(),
				listRoutes.get(i).getNameFrom(),
				listRoutes.get(i).getNameTo(),
				listRoutes.get(i).getDuration(),
				listRoutes.get(i).getDistance(),
			};
			datos[i] = d;
		}
		DefaultTableModel dtm = new DefaultTableModel(datos,columnNames);
		
		
		return dtm;
	}
	
	
}
