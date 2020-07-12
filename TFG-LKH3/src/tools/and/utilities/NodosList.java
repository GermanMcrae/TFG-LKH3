package tools.and.utilities;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "nodes")
@XmlAccessorType (XmlAccessType.FIELD)

public class NodosList {
	
	@XmlElement(name = "nodo")
    private List<Nodo> nodes = null;
	
	public List<Nodo> getNodes() {
        return nodes;
    }
 
    public void setNodes(List<Nodo> collection) {
        this.nodes = collection;
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
 