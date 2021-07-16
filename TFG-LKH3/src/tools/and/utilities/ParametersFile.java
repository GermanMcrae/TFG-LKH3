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
	int MTSP_MIN_SIZE;
	int MTSP_MAX_SIZE;
	int VEHICLES;
	String MTSP_SOLUTION_FILE;
	String TOUR_FILE;// = solucion.txt
	String INITIAL_TOUR_ALGORITHM;
	String BACKTRACKING;
	String PATCHING_C_EXT;
	String PATCHING_A_EXT;
	boolean boolOPTIMUM;
	boolean boolMOVE_TYPE;
	boolean boolPATCHING_C;
	boolean boolPATCHING_A;
	boolean boolRUNS;
	boolean boolMTSP_MIN_SIZE;
	boolean boolMTSP_MAX_SIZE;
	boolean boolVEHICLES;
	boolean boolINITIAL_TOUR_ALGORITHM;
	//INITIAL_TOUR_ALGORITHM ={ BORUVKA | CVRP | GREEDY | MOORE | MTSP | NEAREST-NEIGHBOR | QUICK-BORUVKA | SIERPINSKI | WALK 
	
	//PROBLEM_FILE = eil22.vrp
	//INITIAL_TOUR_ALGORITHM = CVRP
	//VEHICLES = 5
	//MTSP_SOLUTION_FILE = solucionMTSP.txt
	//TOUR_FILE = solucion.txt
	
	
	public ParametersFile() {
		PROBLEM_FILE = "problemFile.tsp";
		OPTIMUM = 378032;
		MOVE_TYPE = 2;
		PATCHING_C = 3;
		PATCHING_A = 2;
		RUNS = 1;
		MTSP_MIN_SIZE = 1;
		MTSP_MAX_SIZE = 1;
		VEHICLES = 1;
		MTSP_SOLUTION_FILE = "solucionMTSP.txt";
		TOUR_FILE = "solucionTest.txt";
		INITIAL_TOUR_ALGORITHM = "";
		BACKTRACKING = "NO";
		PATCHING_C_EXT = "";
		PATCHING_A_EXT = "";
		
		boolOPTIMUM = false;
		boolMOVE_TYPE = false;
		boolPATCHING_C = false;
		boolPATCHING_A = false;
		boolRUNS = false;
		boolMTSP_MIN_SIZE = false;
		boolMTSP_MAX_SIZE = false;
		boolVEHICLES = false;
		boolINITIAL_TOUR_ALGORITHM = false;
	}
	
	public String getPROBLEM_FILE() {
		return PROBLEM_FILE;
	}
	
	public int getOPTIMUM() {
		return OPTIMUM;
	}
	
	public int getMOVE_TYPE() {
		return MOVE_TYPE;
	}
	
	public int getPATCHING_A() {
		return PATCHING_A;
	}
	
	public int getPATCHING_C() {
		return PATCHING_C;
	}
	
	public String getPATCHING_A_EXT() {
		return PATCHING_A_EXT;
	}
	
	public String getPATCHING_C_EXT() {
		return PATCHING_C_EXT;
	}
	
	public int getRUNS() {
		return RUNS;
	}
	
	public int getMTSP_MIN_SIZE() {
		return MTSP_MIN_SIZE;
	}
	
	public int getMTSP_MAX_SIZE() {
		return MTSP_MAX_SIZE;
	}
	
	public int getVEHICLES() {
		return VEHICLES;
	}
	
	public String getBACKTRACKING() {
		return BACKTRACKING;
	}
	
	public String getMTSP_SOLUTION_FILE() {
		return MTSP_SOLUTION_FILE;
	}
	
	public String getTOUR_FILE() {
		return TOUR_FILE;
	}
	
	public String getINITIAL_TOUR_ALGORITHM() {
		return INITIAL_TOUR_ALGORITHM;
	}
		
	public void setPROBLEM_FILE(String value) {
		PROBLEM_FILE = value;
	}
	
	public void setOPTIMUM(int value) {
		OPTIMUM = value;
	}
	
	public void setMOVE_TYPE(int value) {
		MOVE_TYPE = value;
	}
	
	public void setPATCHING_A(int value) {
		PATCHING_A = value;
	}
	
	public void setPATCHING_C(int value) {
		PATCHING_C = value;
	}
	
	public void setPATCHING_A_EXT(String value) {
		PATCHING_A_EXT = value;
	}
	
	public void setPATCHING_C_EXT(String value) {
		PATCHING_C_EXT = value;
	}
	
	public void setRUNS(int value) {
		RUNS = value;
	}
	
	public void setMTSP_MIN_SIZE(int value) {
		MTSP_MIN_SIZE = value;
	}
	
	public void setMTSP_MAX_SIZE(int value) {
		MTSP_MAX_SIZE = value;
	}
	
	public void setVEHICLES(int value) {
		VEHICLES = value;
	}
	
	public void setBACKTRACKING(String value) {
		BACKTRACKING = value;
	}
	
	public void setMTSP_SOLUTION_FILE(String value) {
		MTSP_SOLUTION_FILE = value;
	}
	
	public void setTOUR_FILE(String value) {
		TOUR_FILE = value;
	}
	
	public void setINITIAL_TOUR_ALGORITHM(String value) {
		INITIAL_TOUR_ALGORITHM = value;
	}
	
	public boolean getBoolOPTIMUM() {
		return boolOPTIMUM;
	}
	
	public boolean getBoolMOVE_TYPE() {
		return boolMOVE_TYPE;
	}
	
	public boolean getBoolPATCHING_C() {
		return boolPATCHING_C;
	}
	
	public boolean getBoolPATCHING_A() {
		return boolPATCHING_A;
	}
	
	public boolean getBoolRUNS() {
		return boolRUNS;
	}
	
	public boolean getBoolMTSP_MIN_SIZE() {
		return boolMTSP_MIN_SIZE;
	}
	
	public boolean getBoolMTSP_MAX_SIZE() {
		return boolMTSP_MAX_SIZE;
	}
	
	public boolean getBoolVEHICLES() {
		return boolVEHICLES;
	}
	
	public boolean getBoolINITIAL_TOUR_ALGORITHM() {
		return boolINITIAL_TOUR_ALGORITHM;
	}
	
	public void setBoolOPTIMUM(boolean value) {
		boolOPTIMUM = value;
	}
	
	public void setBoolMOVE_TYPE(boolean value) {
		boolMOVE_TYPE = value;
	}
	
	public void setBoolPATCHING_C(boolean value) {
		boolPATCHING_C = value;
	}
	
	public void setBoolPATCHING_A(boolean value) {
		boolPATCHING_A = value;
	}
	
	public void setBoolRUNS(boolean value) {
		boolRUNS = value;
	}
	
	public void setBoolMTSP_MIN_SIZE(boolean value) {
		boolMTSP_MIN_SIZE = value;
	}
	
	public void setBoolMTSP_MAX_SIZE(boolean value) {
		boolMTSP_MAX_SIZE = value;
	}
	
	public void setBoolVEHICLES(boolean value) {
		boolVEHICLES = value;
	}
	
	public void setBoolINITIAL_TOUR_ALGORITHM(boolean value) {
		boolINITIAL_TOUR_ALGORITHM = value;
	}
	
	//public void setNAME(String value) {
	//	NAME = value;
	//}
	
	public boolean generateFile() {
		boolean res = false;
		String cad = "";
		
		cad += "PROBLEM_FILE= " + PROBLEM_FILE + "\n";
		
		cad += "TOUR_FILE= " + TOUR_FILE + "\n";
		cad += "MTSP_SOLUTION_FILE= " + MTSP_SOLUTION_FILE + "\n";
		cad += "BACKTRACKING= " + BACKTRACKING + "\n";
		cad += "MTSP_MIN_SIZE= " + MTSP_MIN_SIZE + "\n";
		cad += "MTSP_MAX_SIZE= " + MTSP_MAX_SIZE + "\n";
		cad += "RUNS= " + RUNS + "\n";
		
		/*if(boolOPTIMUM) {
			cad += "OPTIMUM= " + OPTIMUM + "\n";
		}
		if(boolMOVE_TYPE) {
			cad += "MOVE_TYPE= " + MOVE_TYPE + "\n";
		}
		if(boolPATCHING_C) {
			cad += "PATCHING_C= " + PATCHING_C + " " + PATCHING_A_EXT + "\n";
		}
		if(boolPATCHING_A) {
			cad += "PATCHING_A= " + PATCHING_A + " " + PATCHING_A_EXT + "\n";
		}
		if(boolRUNS) {
			cad += "RUNS= " + RUNS + "\n";
		}
		if(boolMTSP_MIN_SIZE) {
			cad += "MTSP_MIN_SIZE= " + MTSP_MIN_SIZE + "\n";
		}
		if(boolMTSP_MAX_SIZE) {
			cad += "MTSP_MAX_SIZE= " + MTSP_MAX_SIZE + "\n";
		}
		if(boolVEHICLES) {
			cad += "VEHICLES= " + VEHICLES + "\n";
		}*/
		//if(boolINITIAL_TOUR_ALGORITHM) {
		cad += "INITIAL_TOUR_ALGORITHM= " + INITIAL_TOUR_ALGORITHM + "\n";
		//}
		
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
	
	
	public boolean generateFile2() {
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
