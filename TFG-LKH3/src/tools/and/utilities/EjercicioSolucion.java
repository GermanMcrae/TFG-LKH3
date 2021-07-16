package tools.and.utilities;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.openstreetmap.gui.jmapviewer.LayerGroup;

@XmlRootElement(name = "EjercicioSolucion")
@XmlAccessorType (XmlAccessType.FIELD)
public class EjercicioSolucion {
	private String Problema; //Tipo de problema
	private String Algoritmo; //algoritmo inicial
	private int NdeVehiculos; //numero de vehiculos
	private String DistanciaDuracion; //Distancia o duracion
	private int Capacidad; //Capacidad
	private Double CostTotal; //Coste total de la solucion
	private Double mtspmin;
	private Double mtspmax;
	private int runs;
	private String FileProblem;
	private String ProcessLKH;
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
		mtspmin = 0.0;
		mtspmax = 0.0;
		runs = 0;
		FileProblem = "";
		ProcessLKH = "";
		listCaminos = new ArrayList<ListCustom>();
		cost = new ArrayList<Double>();
	}
	
	
	public EjercicioSolucion(String p, String a, int n, String d, Integer Cap, Double Cos, Double mmin, Double mmax, int r, String fp, String plkh, List<ListCustom> lc, List<Double> cst) {
		Problema = p;
		Algoritmo = a;
		NdeVehiculos = n;
		DistanciaDuracion = d;
		Capacidad = Cap;
		CostTotal = Cos;
		mtspmin = mmin;
		mtspmax = mmax;
		runs = r;
		FileProblem = fp;
		ProcessLKH = plkh;
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
	
	public double getMtspmin() {
		return Math.round(mtspmin * 100.0) / 100.0;
	}
	
	public double getMtspmax() {
		return Math.round(mtspmax * 100.0) / 100.0;
	}
	
	public int getRuns() {
		return runs;
	}
	
	public String getFileProblem() {
		return FileProblem;
	}
	
	public void setFileProblem(String fp) {
		FileProblem = fp;
	}
	
	public String getProcessLKH() {
		return ProcessLKH;
	}
	
	public void setProcessLKH(String plkh) {
		ProcessLKH = plkh;
	}
	
	public void setCostTotal(double ct) {
		CostTotal = ct;
	}
	
	public void setMtspmin(double mmin) {
		mtspmin = mmin;
	}

	public void setMtspmax(double mmax) {
		mtspmax = mmax;
	}
	
	public void setRuns(int r) {
		runs = r;
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
	@Override
	public String toString() {
	    return Problema+", Algoritmo "+Algoritmo+", Cost Total:"+Math.round(CostTotal * 100.0) / 100.0+", medida:"+DistanciaDuracion;
	}
	
	public String getReport() {
		String cad = "";
		if(Problema.equals("CVRP")) {
			cad += "Problema: "+Problema+"\n";
			cad += "Algoritmo inicial: "+Algoritmo+"\n";
			cad += "Numero de vehiculos: "+NdeVehiculos+"\n";
			cad += "Matriz construida en referencia a: "+DistanciaDuracion+"\n";
			cad += "Capacidad: "+Capacidad+"\n";
			cad += "MTSP min: "+mtspmin+"\n";
			cad += "MTSP max: "+mtspmax+"\n";
			cad += "Runs: "+runs+"\n";
			cad += "Coste: "+CostTotal+"\n";
			cad += "\nFichero del problema: \n"+FileProblem+"\n";
			cad += "\nInforme de LKH3: \n"+ProcessLKH+"\n";
		}
		if(Problema.equals("ATSP")) {
			cad += "Problema: "+Problema+"\n";
			cad += "Algoritmo inicial: "+Algoritmo+"\n";
			cad += "Matriz construida en referencia a: "+DistanciaDuracion+"\n";
			cad += "Coste: "+CostTotal+"\n";
			cad += "\nFichero del problema: \n"+FileProblem+"\n";
			cad += "\nInforme de LKH3: \n"+ProcessLKH+"\n";
		}
		return cad;
	}
	
	/*public DefaultTableModel GetTableRoutesSolucion(){
		
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
	}*/
}

