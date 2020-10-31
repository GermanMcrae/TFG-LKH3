package tools.and.utilities;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "EjercicioSolucion")
@XmlAccessorType (XmlAccessType.FIELD)
public class EjercicioSolucion {
	private String Problema; //Tipo de problema
	private String Algoritmo; //algoritmo inicial
	private int NdeVehiculos; //numero de vehiculos
	private String DistanciaDuracion; //Distancia o duracion
	private int Capacidad; //Capacidad
	private Double CostTotal; //Coste total de la solucion
	@XmlElement(name = "listCaminos")
	//private List<ArrayList<Integer>> listCaminos;ListCustom
	private List<ListCustom> listCaminos;
	@XmlElement(name = "listCostes")
	private List<Double> cost;
	//RouteResult;
	
	public EjercicioSolucion() {
		Problema = "";
		Algoritmo = "";
		NdeVehiculos = 0;
		DistanciaDuracion = "";
		Capacidad = 0;
		CostTotal = 0.0;
		listCaminos = new ArrayList<ListCustom>();
		cost = new ArrayList<Double>();
	}
	
	
	public EjercicioSolucion(String p, String a, int n, String d, Integer Cap, Double Cos, List<ListCustom> lc, List<Double> cst) {
		Problema = p;
		Algoritmo = a;
		NdeVehiculos = n;
		DistanciaDuracion = d;
		Capacidad = Cap;
		CostTotal = Cos;
		listCaminos = lc;
		cost = cst;
	}
	
	/*public EjercicioSolucion(String p, String a, int n, String d, Integer Cap, Double Cos, List<ArrayList<Integer>> lc, List<Double> cst) {
		Problema = p;
		Algoritmo = a;
		NdeVehiculos = n;
		DistanciaDuracion = d;
		Capacidad = Cap;
		CostTotal = Cos;
		setListCaminosMod(lc);
		cost = cst;
	}*/
	
	public String getProblema() {
		return Problema;
	}
	
	public void setProblema(String p) {
		Problema = p;
	}
	
	public String getAlgoritmo() {
		return Algoritmo;
	}
	
	public void setAlgoritmo(String a) {
		Algoritmo = a;
	}
	
	public Integer getNdeVehiculos() {
		return NdeVehiculos;
	}
	
	public void setNdeVehiculos(Integer n) {
		NdeVehiculos = n;
	}
	
	public String getDistanciaDuracion() {
		return DistanciaDuracion;
	}
	
	public void setDistanciaDuracion(String dd) {
		DistanciaDuracion = dd;
	}
	
	public Integer getCapacidad() {
		return Capacidad;
	}
	
	public void setCapacidad(Integer c) {
		Capacidad = c;
	}
	
	public double getCostTotal() {
		return CostTotal;
	}
	
	public void setCostTotal(double ct) {
		CostTotal = ct;
	}
	
	public List<ListCustom> getListCaminos() {
		return listCaminos;
	}
	
	public void setListCaminos(List<ListCustom> lc) {
		listCaminos = lc;
	}
	
	public List<ArrayList<Integer>> getListCaminosMod() {
		List<ArrayList<Integer>> temp = new ArrayList<ArrayList<Integer>>();
		for(int i=0;i<listCaminos.size();i++) {
			temp.add(listCaminos.get(i).getListRoute());
		}
		
		return temp;
	}
	
	public void setListCaminosMod(List<ArrayList<Integer>> lc) {
		List<ListCustom> temp = new ArrayList<ListCustom>();
		for(int i=0;i<lc.size();i++) {
			ListCustom tp = new ListCustom(lc.get(i));
			temp.add(tp);
		}
		listCaminos = temp;
	}
	
	//ListCustom
	
	public List<Double> getCost() {
		return cost;
	}
	
	public void setCost(List<Double> cst) {
		cost = cst;
	}
}

