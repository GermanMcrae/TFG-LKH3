package tools.and.utilities;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.Layer;
import org.openstreetmap.gui.jmapviewer.MapPolylineImpl;


@XmlRootElement(name = "nodes")
@XmlAccessorType (XmlAccessType.FIELD)

public class NodosList {
	
	@XmlElement(name = "nodo")
    private List<Nodo> nodes;
	@XmlElement(name = "ejercicios")
	private List<EjercicioSolucion> ejercicioSolucion;
	
	public NodosList() {
		nodes = new ArrayList<Nodo>();
		ejercicioSolucion = new ArrayList<EjercicioSolucion>();
	}
	
	public NodosList(List<Nodo> n, List<EjercicioSolucion> es) {
		nodes = n;
		ejercicioSolucion = es;
	}
	
	public List<Nodo> getNodes() {
        return nodes;
    }
	
	public List<EjercicioSolucion> getEjercicioSolucion() {
        return ejercicioSolucion;
    }
 
    public void setNodes(List<Nodo> collection) {
        this.nodes = collection;
    }
    
    public void setEjercicioSolucion(List<EjercicioSolucion> collection) {
        this.ejercicioSolucion = collection;
    }
    
    public void clear() {
    	nodes.clear();
    	ejercicioSolucion.clear();
    }
    
    public int size() {
    	return nodes.size();
    }
    
    public int sizeEjercicioSolucion() {
    	return ejercicioSolucion.size();
    }
    
    public Nodo get(int i) {
    	return nodes.get(i);
    }
    
    public EjercicioSolucion getEjercicioSolucion(int i) {
    	return ejercicioSolucion.get(i);
    }
    
    public void add(Nodo n) {
    	nodes.add(n);
    }
    
    public void addEjercicioSolucion(EjercicioSolucion es) {
    	ejercicioSolucion.add(es);
    }
    
    public void delete(Nodo n) {
    	for(int i=0; i<nodes.size();i++) {
    		if(nodes.get(i)!=n) {
    			for(int j=0;j<n.listRoutes.size();j++) {
    				
    				
    				nodes.get(i).deleteRuta(n.getListRoutes().get(j));
    			}
    		}
    	}
    	
    	
    	nodes.remove(n);
    	
    }
    
    public void delete(int index) {
    	for(int i=0; i<nodes.size();i++) {
    		if(index<i) {
    			//System.out.println("nodes.size: "+nodes.size()+" index:"+index+" <"+" i:"+i);
    			nodes.get(i).deleteRuta(index);
    		}
    		if(index>i) {
    			//System.out.println("nodes.size: "+nodes.size()+" index:"+index+" >"+" i:"+i);
    			nodes.get(i).deleteRuta(index-1);
    		}
    	}
    	nodes.remove(index);
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
    	
    	return value;
    }
    
    public List<MapPolylineImpl> getTourMPLI(List<Integer> camino, Color color){
    	List<MapPolylineImpl> value = new ArrayList<MapPolylineImpl>();
    	
    	for(int i=1;i<camino.size();i++) {
    		if(camino.get(i-1)<camino.get(i)) {
    			//List<Coordinate> temp = nodes.get(camino.get(i-1)-1).getListRoutes().get(camino.get(i)-2).getCoordinates();
    			MapPolylineImpl mpl = new MapPolylineImpl(nodes.get(camino.get(i-1)-1).getListRoutes().get(camino.get(i)-2).getCoordinates());
    			mpl.setColor(color);
				value.add(mpl);
				//value.add(e)
    		//System.out.println("Numero de nodes: "+nodes.get(camino.get(i-1))+" tamListRoutes: "+nodes.get(camino.get(i-1)).getListRoutes().size());
    			//System.out.println("< Camino "+i+" de fila "+(camino.get(i-1)-1)+" a col "+(camino.get(i)-2));
    		}
    			
    			
    		if(camino.get(i-1)>camino.get(i)) {
    			MapPolylineImpl mpl = new MapPolylineImpl(nodes.get(camino.get(i-1)-1).getListRoutes().get(camino.get(i)-1).getCoordinates());
    			mpl.setColor(color);
				value.add(mpl);
    			//value.add(nodes.get(camino.get(i-1)-1).getListRoutes().get(camino.get(i)-1).getCoordinates());
    			//System.out.println("> Camino "+i+" de fila "+(camino.get(i-1)-1)+" a col "+(camino.get(i)-1));
    		}
    	}
    	
    	
    	return value;
    }
    
    public List<Double> getListDistanceTour(List<Integer> camino){
    	List<Double> value = new ArrayList<Double>();
    	
    	for(int i=1;i<camino.size();i++) {
    		if(camino.get(i-1)<camino.get(i)) {
    			//List<Coordinate> temp = nodes.get(camino.get(i-1)-1).getListRoutes().get(camino.get(i)-2).getCoordinates();
    			//MapPolylineImpl mpl = new MapPolylineImpl(nodes.get(camino.get(i-1)-1).getListRoutes().get(camino.get(i)-2).getCoordinates());
    			//mpl.setColor(color);
				value.add(nodes.get(camino.get(i-1)-1).getListRoutes().get(camino.get(i)-2).getDistance());
				//value.add(e)
    		//System.out.println("Numero de nodes: "+nodes.get(camino.get(i-1))+" tamListRoutes: "+nodes.get(camino.get(i-1)).getListRoutes().size());
    			//System.out.println("< Camino "+i+" de fila "+(camino.get(i-1)-1)+" a col "+(camino.get(i)-2));
    		}
    			
    			
    		if(camino.get(i-1)>camino.get(i)) {
    			//MapPolylineImpl mpl = new MapPolylineImpl(nodes.get(camino.get(i-1)-1).getListRoutes().get(camino.get(i)-1).getCoordinates());
    			//mpl.setColor(color);
				value.add(nodes.get(camino.get(i-1)-1).getListRoutes().get(camino.get(i)-1).getDistance());
    			//value.add(nodes.get(camino.get(i-1)-1).getListRoutes().get(camino.get(i)-1).getCoordinates());
    			//System.out.println("> Camino "+i+" de fila "+(camino.get(i-1)-1)+" a col "+(camino.get(i)-1));
    		}
    	}
    	
    	
    	return value;
    }
    
    public List<Double> getListDurationTour(List<Integer> camino){
    	List<Double> value = new ArrayList<Double>();
    	
    	for(int i=1;i<camino.size();i++) {
    		if(camino.get(i-1)<camino.get(i)) {
    			//List<Coordinate> temp = nodes.get(camino.get(i-1)-1).getListRoutes().get(camino.get(i)-2).getCoordinates();
    			//MapPolylineImpl mpl = new MapPolylineImpl(nodes.get(camino.get(i-1)-1).getListRoutes().get(camino.get(i)-2).getCoordinates());
    			//mpl.setColor(color);
				value.add(nodes.get(camino.get(i-1)-1).getListRoutes().get(camino.get(i)-2).getDuration());
				//value.add(e)
    		//System.out.println("Numero de nodes: "+nodes.get(camino.get(i-1))+" tamListRoutes: "+nodes.get(camino.get(i-1)).getListRoutes().size());
    			//System.out.println("< Camino "+i+" de fila "+(camino.get(i-1)-1)+" a col "+(camino.get(i)-2));
    		}
    			
    			
    		if(camino.get(i-1)>camino.get(i)) {
    			//MapPolylineImpl mpl = new MapPolylineImpl(nodes.get(camino.get(i-1)-1).getListRoutes().get(camino.get(i)-1).getCoordinates());
    			//mpl.setColor(color);
				value.add(nodes.get(camino.get(i-1)-1).getListRoutes().get(camino.get(i)-1).getDuration());
    			//value.add(nodes.get(camino.get(i-1)-1).getListRoutes().get(camino.get(i)-1).getCoordinates());
    			//System.out.println("> Camino "+i+" de fila "+(camino.get(i-1)-1)+" a col "+(camino.get(i)-1));
    		}
    	}
    	
    	
    	return value;
    }
}
