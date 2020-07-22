package tools.and.utilities;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


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
 