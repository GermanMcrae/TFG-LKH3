package tools.and.utilities;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.openstreetmap.gui.jmapviewer.MapPolylineImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;
import org.openstreetmap.gui.jmapviewer.interfaces.MapPolygon;

import com.osrm.services.Route;

public class MatrixPoints {

	private List<List<RouteJSON>> MatrixOfPoint;
	//private List<RouteJSON> ListRoute;
	List<List<MapPolygon>> MatrixOfPolygon;
	
	String matrixForDistance;
	String matrixForDuration;
	String coordinates;
	
	public MatrixPoints() {
		MatrixOfPoint = new ArrayList<List<RouteJSON>>();
	}
	
	
	//añadir una version para elegir vehiculo
	public MatrixPoints(List<MapMarker> listMapMarker) {
		//ListRoute = new ArrayList<RouteJSON>();
		MatrixOfPoint = new ArrayList<List<RouteJSON>>();
		Route rt = new Route();
		for(int i=0;i<listMapMarker.size();i++) {
			List<RouteJSON> fil = new ArrayList<RouteJSON>();
			for(int j=0;j<listMapMarker.size();j++) {
				System.out.println("i:"+i+";"+"j:"+j);
				if(i!=j) {
					boolean flag = true;
					while(flag) {
						//JSONObject routingWithProfile = rt.getFastestRoute("-3.695808,37.221539;-3.515645,36.744934", "car");
						String msg = String.valueOf(listMapMarker.get(i).getLon())+","+
								String.valueOf(listMapMarker.get(i).getLat())+";"+
								String.valueOf(listMapMarker.get(j).getLon())+","+
								String.valueOf(listMapMarker.get(j).getLat());
				
				
						JSONObject routingWithProfile = rt.getFastestRoute(msg, "car");
						if(routingWithProfile != null) {
							
							RouteJSON temp = new RouteJSON(routingWithProfile);
							fil.add(temp);
							flag = false;
						}
						else {
							try {
								Thread.sleep(2000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
							
					}
				}
				else {
					RouteJSON temp = new RouteJSON();
					fil.add(temp);
				}
				//try {
				//	Thread.sleep(2000);
				//} catch (InterruptedException e) {
					// TODO Auto-generated catch block
				//	e.printStackTrace();
				//}
			}
			MatrixOfPoint.add(fil);
		}
		matrixForDistance = calMatrixDistance();
		matrixForDuration = calMatrixDuration();
		coordinates = "";
		for(int i=0;i<listMapMarker.size();i++) {
			coordinates = coordinates + (i+1) + " " + listMapMarker.get(i).getLat() + " " + listMapMarker.get(i).getLon() + "\n";

		}
		MatrixOfPolygon = AllRoutesMapPolygon();
		
	}
	
	private  String calMatrixDistance() {
		String msg = "";
		for(int i=0; i< MatrixOfPoint.size(); i++) {
			List<RouteJSON> temp = MatrixOfPoint.get(i);
			for(int j=0; j<temp.size(); j++) {
				msg = msg + String.valueOf(temp.get(j).distance)+" ";
			}
			msg = msg + "\n";
		}
		
		return msg;
		
	}
	
	private  String calMatrixDuration() {
		String msg = "";
		for(int i=0; i< MatrixOfPoint.size(); i++) {
			List<RouteJSON> temp = MatrixOfPoint.get(i);
			for(int j=0; j<temp.size(); j++) {
				msg = msg + String.valueOf(temp.get(j).duration)+" ";
			}
			msg = msg + "\n";
		}
		
		return msg;
		
	}
	
	private List<List<MapPolygon>> AllRoutesMapPolygon(){
		List<List<MapPolygon>> obj = new ArrayList<List<MapPolygon>>() ;
		for(int i=0; i< MatrixOfPoint.size(); i++) {
			List<MapPolygon> poly = new ArrayList<MapPolygon>();
			List<RouteJSON> temp = MatrixOfPoint.get(i);
			for(int j=0; j<temp.size(); j++) {
				poly.add(temp.get(j).getPolyline());
			}
			obj.add(poly);
		}
		return obj;
	}
	
	public List<List<MapPolygon>>getAllRoutesMapPolygon(){
		return MatrixOfPolygon;
	}
	
	public String getMatrixDistance() {
		return matrixForDistance;
	}
	
	public String getMatrixDuration() {
		return matrixForDuration;
	}
	
	public String getCoordinates() {
		return coordinates;
	}
	
	public List<MapPolygon> RouteList(List<Integer> list){
		List<MapPolygon> ListRoute = new ArrayList<MapPolygon>();
		
		int i = 0;
		while(i < (list.size()-1)) {
			List<MapPolygon> polyFil = MatrixOfPolygon.get(i);
			MapPolylineImpl polyCol = (MapPolylineImpl)polyFil.get(i+1);
			polyCol.setName("Route "+(i+1)+" from "+list.get(i)+ " to "+list.get(i+1));
			
			polyCol.changeColor(Color.red);
			ListRoute.add(polyCol);
			i++;
		}
		
		return ListRoute;
	}
	
	
}
