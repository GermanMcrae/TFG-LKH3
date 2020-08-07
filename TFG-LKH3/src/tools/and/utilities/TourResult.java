package tools.and.utilities;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.MapPolylineImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapPolygon;

public class TourResult {
	
	private String nombre;
	private List<Integer> camino;
	private List<List<Coordinate>> track;
	private int cost;
	private Color color;
	
	//MapPolygon
	
	public TourResult() {
		nombre = "";
		camino = new ArrayList<Integer>();
		track = new ArrayList<List<Coordinate>>();
		cost = 0;
		color = Color.black;
	}
	
	public TourResult(String n, List<Integer> cam, List<List<Coordinate>> t, int cos, Color col) {
		nombre = n;
		camino = cam;
		track = t;
		cost = cos;
		color = col;
	}
	
	public String getNombre(){
		return nombre;
	}
	
	public List<Integer> getCamino(){
		return camino;
	}

	public List<List<Coordinate>> getTrack(){
		return track;
	}
	
	public int getCost(){
		return cost;
	}
	
	public Color getColor(){
		return color;
	}
	
	public void setNombre(String n){
		nombre = n;
	}
	
	public void setCamino(List<Integer> c){
		camino = c;
	}

	public void setTrack(List<List<Coordinate>> t){
		track = t;
	}
	
	public void setCost(int c){
		cost = c;
	}
	
	public void setColor(Color c){
		color = c;
	}
	
	public void addCamino(int value) {
		camino.add(value);
	}
	public void addTrack(ArrayList<Coordinate> value) {
		track.add(value);
	}
	
	public String getTextCamino() {
		String text = "";
		for(int i=0;i<camino.size();i++) {
			text+="["+camino.get(i)+"]";
		}
		return text;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return nombre+" C:"+cost;
	}
}
