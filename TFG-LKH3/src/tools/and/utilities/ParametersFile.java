package tools.and.utilities;

import java.io.FileWriter;
import java.io.PrintWriter;

public class ParametersFile {
	
	/*
	 * PROBLEM_FILE = eil30.vrp
OPTIMUM = 378032
MOVE_TYPE = 2
PATCHING_C = 3
PATCHING_A = 2
RUNS = 1
TOUR_FILE = solucion.txt
	 * */
	
	String PROBLEM_FILE;// = eil30.vrp
	int OPTIMUM;// = 378032
	int MOVE_TYPE;// = 2
	int PATCHING_C;// = 3
	int PATCHING_A;// = 2
	int RUNS;// = 1
	String TOUR_FILE;// = solucion.txt

	public ParametersFile() {
		PROBLEM_FILE = "problemFile.tsp";
		OPTIMUM = 378032;
		MOVE_TYPE = 2;
		PATCHING_C = 3;
		PATCHING_A = 2;
		RUNS = 1;
		TOUR_FILE = "solucionTest.txt";
	}
	
	public boolean generateFile() {
		boolean res = false;
		String cad = "";
		
		cad += "PROBLEM_FILE= " + PROBLEM_FILE + "\n";
		cad += "OPTIMUM= " + OPTIMUM + "\n";
		cad += "MOVE_TYPE= " + MOVE_TYPE + "\n";
		cad += "PATCHING_C= " + PATCHING_C + "\n";
		cad += "PATCHING_A= " + PATCHING_A + "\n";
		cad += "RUNS= " + RUNS + "\n";
		cad += "TOUR_FILE= " + TOUR_FILE + "\n";
		cad += "EOF";
		
		//cad += "DIMENSION: " + + "/n";
		
		System.out.println(cad);
		
		FileWriter fichero = null;
	    PrintWriter pw = null;
	    try
	    {
	    	fichero = new FileWriter("parFile.par");
	    	pw = new PrintWriter(fichero);
	    	pw.print(cad);
	    } catch (Exception e) {
            e.printStackTrace();
	    } finally {
	    try {
	    	// Nuevamente aprovechamos el finally para 
	        // asegurarnos que se cierra el fichero.
	    	if (null != fichero) {
	    		fichero.close();
	    		res = true;
	    	}
	        	
	    	
	        } catch (Exception e2) {
	        	e2.printStackTrace();
	        }
	    }
		
		return res;
	}
	
}
