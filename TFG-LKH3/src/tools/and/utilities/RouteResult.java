package tools.and.utilities;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.openstreetmap.gui.jmapviewer.LayerGroup;

public class RouteResult {

	/* File example
	 * 
	 * 	NAME : Test.24106.tour
		COMMENT : Length = 24106
		COMMENT : Found by LKH [Keld Helsgaun] Sun Jun 21 00:21:44 2020
		TYPE : TOUR
		DIMENSION : 6
		TOUR_SECTION
		1
		3
		5
		2
		4
		6
		-1
		EOF

	 * 
	 * */
	
	//File f;
	String file;
	//String fileTours;
	List<Integer> routeSol;// = new ArrayList<>();
	List<Integer> routeDistance;
	List<Integer> routeDuration;
	List<TourResult> ListTours;
	List<LayerGroup> groupVehicles;
	
	List<ArrayList<Integer>> listCaminos;
	List<Double> cost;
	//List<int> routeSol;
	
	
	
	public RouteResult() {
		file = "";
		routeSol = new ArrayList<Integer>();
		routeDistance = new ArrayList<Integer>();
		routeDuration = new ArrayList<Integer>();
		ListTours = new ArrayList<TourResult>();
		groupVehicles = new ArrayList<LayerGroup>();
		
		listCaminos = new ArrayList<ArrayList<Integer>>();
		cost = new ArrayList<Double>();
	}
	
	
	public void loadWithList(List<ArrayList<Integer>> listSol) {
		listCaminos = listSol;
		for(int i=0;i<listSol.size();i++) {
			TourResult tr = new TourResult();
			tr.setCamino(listSol.get(i));
			ListTours.add(tr);
		}
		
	}
	
	public void loadFile() {
		routeSol = new ArrayList<Integer>();
		file = readFile();
		System.out.println("loadFile() file: "+file);
		ListTours = readFileTours();
		System.out.println("loadFile() ListTours.size() == 0: "+ListTours.size());
		if(ListTours.size() == 0) {
			ListTours = readFileOneTour();
			System.out.println("loadFile() ListTours.size() == 0: "+ListTours.size());
			if(ListTours.size() == 0) {
				JOptionPane.showMessageDialog(null, 
	    				  "Route could not be generated", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	
	String readFile() {
		String text = "";
		File archivo = new File ("solucionTest.txt");
		FileReader fr;
		try {
			fr = new FileReader (archivo);
			BufferedReader br = new BufferedReader(fr);
			String linea;
			TourResult tr = new TourResult();
			tr.setNombre("Optimal route all");
			try {
				
				boolean read = false;
				while((linea=br.readLine())!=null) {
					
				
					text += linea+ "\n";
					
					if(linea.equals("-1")) {
						read = false;
					}
					
					if(read){
						routeSol.add(Integer.valueOf(linea.replaceFirst("\n", "")));
					}
					
					if(linea.equals("TOUR_SECTION")) {
						read = true;
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return text;
	}
	
	List<TourResult> readFileOneTour() {
		String text = "";
		List<TourResult> result = new ArrayList<TourResult>();
		ArrayList<Integer> resultNumber = new ArrayList<Integer>();
		File archivo = new File ("solucionTest.txt");
		FileReader fr;
		try {
			fr = new FileReader (archivo);
			BufferedReader br = new BufferedReader(fr);
			String linea;
			try {
				TourResult tr = new TourResult();
				boolean read = false;
				while((linea=br.readLine())!=null) {
					
					text += linea+ "\n";
					
					if(linea.equals("-1")) {
						read = false;
					}
					
					if(read){
						tr.addCamino(Integer.valueOf(linea.replaceFirst("\n", "")));
						resultNumber.add(Integer.valueOf(linea.replaceFirst("\n", "")));
					}
					
					if(linea.equals("TOUR_SECTION")) {
						read = true;
					}
				}
				tr.addCamino(1);
				result.add(tr);
				resultNumber.add(1);
				listCaminos.add(resultNumber);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	/*List<TourResult> readFileTours() {
		String text = "";
		List<TourResult> result = new ArrayList<TourResult>();
		List<List<Integer>> listCaminos = new ArrayList<List<Integer>>();
		File archivo = new File ("solucionMTSP.txt");
		System.out.println("archivo");
		System.out.println(archivo);
		FileReader fr;
		try {
			fr = new FileReader (archivo);
			BufferedReader br = new BufferedReader(fr);
			String linea;
			try {
				int i = 0;
				while((linea=br.readLine())!=null) {
					if(i > 1) {
						TourResult tr = new TourResult();
						
						int j=0;
						tr.setNombre("Coche"+(i-1));
						//tr.setGroupName(new LayerGroup("Coche"+(i-1)));
						String tempText = "";
						boolean fase2 = true;
						while(j<linea.length()) {
							//System.out.println("Antes del if('"+linea.charAt(j)+"'");
							if(linea.charAt(j) != '(' && fase2) {
								//System.out.println("Despues del if('"+linea.charAt(j)+"'");
								if(linea.charAt(j) != ' ') {
									//System.out.println("Despues2 del if y graba('"+linea.charAt(j)+"'");
									tempText += linea.charAt(j);
									
								}
								else if(linea.charAt(j) == ' ') {
									
									
									
									tr.addCamino(Integer.parseInt(tempText));
									
									tempText = "";
								}
							}
							else {
								fase2 = false;
							}
							if(!fase2) {
								
								//System.out.println("Entra en fase2 '"+linea.charAt(j)+"'");
								if(List.of('0','1','2','3','4','5','6','7','8','9').contains(linea.charAt(j))) {
									tempText += linea.charAt(j);
									//System.out.println("Como va fase2 tempText '"+tempText+"'");
								}
								
								if(linea.charAt(j) == ')') {
									tempText = "";
								}
								
								if(j == linea.length()-1) {
									//System.out.println("Momento final '"+(linea.length()-1)+"' coste:"+tempText);
									tr.setCost(Double.parseDouble(tempText));
								}
							}
							
							j++;
						}
						
						result.add(tr);
						//System.out.println(linea + " tam:"+linea.getBytes().length);
					}
					i++;
				}
				//System.out.println("Resultado tamaño "+result.size());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
		
	}*/
	
	List<TourResult> readFileTours() {
		String text = "";
		List<TourResult> result = new ArrayList<TourResult>();
		listCaminos = new ArrayList<ArrayList<Integer>>();
		cost = new ArrayList<Double>();
		File archivo = new File ("solucionMTSP.txt");
		System.out.println("archivo");
		System.out.println(archivo);
		FileReader fr;
		try {
			fr = new FileReader (archivo);
			BufferedReader br = new BufferedReader(fr);
			String linea;
			try {
				int i = 0;
				while((linea=br.readLine())!=null) {
					if(i > 1) {
						TourResult tr = new TourResult();
						ArrayList<Integer> caminito = new ArrayList<Integer>();
						int j=0;
						tr.setNombre("Coche"+(i-1));
						//tr.setGroupName(new LayerGroup("Coche"+(i-1)));
						String tempText = "";
						boolean fase2 = true;
						while(j<linea.length()) {
							//System.out.println("Antes del if('"+linea.charAt(j)+"'");
							if(linea.charAt(j) != '(' && fase2) {
								//System.out.println("Despues del if('"+linea.charAt(j)+"'");
								if(linea.charAt(j) != ' ') {
									//System.out.println("Despues2 del if y graba('"+linea.charAt(j)+"'");
									tempText += linea.charAt(j);
									
								}
								else if(linea.charAt(j) == ' ') {
									
									
									Integer intTemp = Integer.parseInt(tempText); 
									tr.addCamino(intTemp);
									caminito.add(intTemp);
									tempText = "";
								}
							}
							else {
								fase2 = false;
							}
							if(!fase2) {
								
								//System.out.println("Entra en fase2 '"+linea.charAt(j)+"'");
								if(List.of('0','1','2','3','4','5','6','7','8','9').contains(linea.charAt(j))) {
									tempText += linea.charAt(j);
									//System.out.println("Como va fase2 tempText '"+tempText+"'");
								}
								
								if(linea.charAt(j) == ')') {
									tempText = "";
								}
								
								if(j == linea.length()-1) {
									//System.out.println("Momento final '"+(linea.length()-1)+"' coste:"+tempText);
									Double tempDouble = Double.parseDouble(tempText);
									tr.setCost(tempDouble);
									cost.add(tempDouble);
								}
							}
							
							j++;
						}
						
						result.add(tr);
						listCaminos.add(caminito);
						//System.out.println(linea + " tam:"+linea.getBytes().length);
					}
					i++;
				}
				//System.out.println("Resultado tamaño "+result.size());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	List<TourResult> GenerateTourResult() {
		List<TourResult> result = new ArrayList<TourResult>();
		for(int i=0; i<listCaminos.size();i++) {
			TourResult tr = new TourResult();
			tr.setNombre("Coche"+(i));
			for(int j=0;j<listCaminos.get(i).size();j++) {
				tr.addCamino(listCaminos.get(i).get(j));
			}
			if(i > cost.size())
				tr.setCost(cost.get(i));
			result.add(tr);
		}
		return result;
		
	}
	
	/*TestCVRP, Cost: 1540_210160
	The tours traveled by the 5 salesmen are:
	1 2 1 (#1)  Cost: 6708
	1 7 1 (#1)  Cost: 10024
	1 4 5 8 1 (#3)  Cost: 117440
	1 6 1 (#1)  Cost: 70588
	1 3 1 (#1)  Cost: 5400*/
	
	public List<Integer> getListRutas(){
		return routeSol;
	}
	
	public List<TourResult> getListTours(){
		return ListTours;
	}
	
	
	public void testLectura() {
		System.out.println(file);
	}
	
	public void testLecturaTours() {
		String text = "";
		for(int i=0;i<ListTours.size();i++) {
			text += ListTours.get(i).getTextCamino()+"\n";
		}
		System.out.println(text);
	}
	
	private void tour() {
		String linea = "";
		boolean read = false;
		//boolean finishRead = false;
		try {
			while((linea=((BufferedReader) routeSol).readLine())!=null) {
				if(linea.equals("TOUR_SECTION")) {
					read = true;
				}
				
				if(linea.equals("-1")) {
					read = false;
				}
				
				if(read){
					System.out.println(linea);
				}
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	
	public double GetCostAllTours(String type) {
		double value = 0.0;
		for(int i=0;i<ListTours.size();i++) {
			value += ListTours.get(i).getCosteTotal(type);
		}
		return Math.round(value * 100.0) / 100.0;
	}
	
	public List<Integer> getTour(){
		return routeSol;
	}
	//List<List<Integer>> listCaminos;
	//List<Double> cost;
	public List<ArrayList<Integer>> getListCaminos(){
		return listCaminos;
	}
	
	public void setListCaminos(List<ArrayList<Integer>> lc) {
		listCaminos = lc;
	}
	
	public List<Double> getCost(){
		return cost;
	}
	
	public void setRouteSol(List<Double> cst) {
		cost = cst;
	}
}
