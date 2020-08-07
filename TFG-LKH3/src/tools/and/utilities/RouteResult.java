package tools.and.utilities;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
	List<TourResult> ListTours;
	//List<int> routeSol;
	
	public RouteResult() {
		routeSol = new ArrayList<Integer>();
		file = readFile();
		ListTours = readFileTours();
		//fileTours = readFileTours();
		//tour();
	}
	
	String readFile() {
		String text = "";
		File archivo = new File ("solucionTest.txt");
		FileReader fr;
		try {
			fr = new FileReader (archivo);
			BufferedReader br = new BufferedReader(fr);
			String linea;
			try {
				
				boolean read = false;
				while((linea=br.readLine())!=null) {
					
				
					text += linea+ "\n";
					
					if(linea.equals("-1")) {
						read = false;
					}
					
					if(read){
						//System.out.println(linea);
						//String temp = ;
						//System.out.println("'"+temp+"'");
						//int temp2 = Integer.valueOf(temp);
						//System.out.println("'"+temp2+"'");
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
	
	List<TourResult> readFileTours() {
		String text = "";
		List<TourResult> result = new ArrayList<TourResult>();
		File archivo = new File ("solucionMTSP.txt");
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
									//System.out.println("el texto de mierda'"+tempText+"'");
									
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
									tr.setCost(Integer.parseInt(tempText));
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
	
	public List<Integer> getTour(){
		return routeSol;
	}
	
	/*
	 * // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
			archivo = new File ("solucionTest.txt");
			fr = new FileReader (archivo);
			br = new BufferedReader(fr);
			
			// Lectura del fichero
			String linea;
			while((linea=br.readLine())!=null)
				System.out.println(linea);
	 * */
}
