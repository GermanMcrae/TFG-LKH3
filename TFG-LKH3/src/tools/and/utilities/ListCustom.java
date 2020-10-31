package tools.and.utilities;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

import com.sun.xml.txw2.annotation.XmlElement;

@XmlType
public class ListCustom {
	ArrayList <Integer> listRoute;
	
	public ListCustom() {
		listRoute = new ArrayList<Integer>();
	}
	
	public ListCustom(ArrayList<Integer> l) {
		listRoute = l;
	}
	
	@XmlElement
	public ArrayList<Integer> getListRoute(){
		return listRoute;
	}
	
	public void setListRoute(ArrayList<Integer> l){
		listRoute = l;
	}
}
