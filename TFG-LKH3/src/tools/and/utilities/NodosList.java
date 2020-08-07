package tools.and.utilities;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.openstreetmap.gui.jmapviewer.Coordinate;


@XmlRootElement(name = "nodes")
@XmlAccessorType (XmlAccessType.FIELD)

public class NodosList {
	
	@XmlElement(name = "nodo")
    private List<Nodo> nodes;
	
	public NodosList() {
		nodes = new ArrayList<Nodo>();
	}
	
	public NodosList(List<Nodo> n) {
		nodes = n;
	}
	
	public List<Nodo> getNodes() {
        return nodes;
    }
 
    public void setNodes(List<Nodo> collection) {
        this.nodes = collection;
    }
    
    public void clear() {
    	nodes.clear();
    }
    
    public int size() {
    	return nodes.size();
    }
    
    public Nodo get(int i) {
    	return nodes.get(i);
    }
    
    public void add(Nodo n) {
    	nodes.add(n);
    }
	
    public String getMatrixDistance() {
    	String matrix = "";
    	for(int i=0; i<nodes.size();i++) {
    		matrix += nodes.get(i).getVectorDistanceForMatrix(i)+"\n";
    	}
    	return matrix;
    }
    
    public String getMatrixDuration() {
    	String matrix = "";
    	for(int i=0; i<nodes.size();i++) {
    		matrix += nodes.get(i).getVectorDurationForMatrix(i)+"\n";
    	}
    	return matrix;
    }
    
    public String getListCoordenades() {
    	String text = "";
    	for(int i=0;i<nodes.size();i++) {
    		text += i+1+"\t"+nodes.get(i).getCoordinate().getLat()+" "+nodes.get(i).getCoordinate().getLon()+"\n";
    	}
    	return text;
    }

    public String getListDemands() {
    	String text = "";
    	for(int i=0;i<nodes.size();i++) {
    		text += i+1+"\t"+nodes.get(i).getDemand()+"\n";
    	}
    	return text;
    }
    
    public List<List<Coordinate>> getTour(List<Integer> camino){
    	List<List<Coordinate>> value = new ArrayList<List<Coordinate>>();
    	//System.out.println("tam de nodes: "+nodes.size()+" Tam de camino:"+camino.size());
    	for(int i=1;i<camino.size();i++) {
    		//System.out.println("Elemento "+camino.get(i-1)+" "+nodes.get(i-1).getListRoutes().size());
    		
    		
    		
    		//List<Coordinate> data = new ArrayList<Coordinate>();
    		if(camino.get(i-1)<camino.get(i)) {
    			value.add(nodes.get(camino.get(i-1)-1).getListRoutes().get(camino.get(i)-2).getCoordinates());
    		//System.out.println("Numero de nodes: "+nodes.get(camino.get(i-1))+" tamListRoutes: "+nodes.get(camino.get(i-1)).getListRoutes().size());
    			//System.out.println("< Camino "+i+" de fila "+(camino.get(i-1)-1)+" a col "+(camino.get(i)-2));
    		}
    			
    			
    		if(camino.get(i-1)>camino.get(i)) {
    			value.add(nodes.get(camino.get(i-1)-1).getListRoutes().get(camino.get(i)-1).getCoordinates());
    			//System.out.println("> Camino "+i+" de fila "+(camino.get(i-1)-1)+" a col "+(camino.get(i)-1));
    		}
    			
    		
    	}
    		
    		
    		//value.add(e)
    	return value;
    }
}


/*
 * @XmlRootElement(name = "employees")
@XmlAccessorType (XmlAccessType.FIELD)
public class Employees 
{
    @XmlElement(name = "employee")
    private List<Employee> employees = null;
 
    public List<Employee> getEmployees() {
        return employees;
    }
 
    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}*/
 