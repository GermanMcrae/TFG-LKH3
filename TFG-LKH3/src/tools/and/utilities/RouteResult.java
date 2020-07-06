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
	List<Integer> routeSol;// = new ArrayList<>();
	//List<int> routeSol;
	
	public RouteResult() {
		routeSol = new ArrayList<Integer>();
		file = readFile();
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
	
	public void testLectura() {
		System.out.println(file);
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
