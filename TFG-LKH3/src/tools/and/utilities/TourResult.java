package tools.and.utilities;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.Layer;
import org.openstreetmap.gui.jmapviewer.LayerGroup;
import org.openstreetmap.gui.jmapviewer.MapPolylineImpl;


public class TourResult {
	
	private String nombre;
	private List<Integer> camino;
	private List<List<Coordinate>> track;
	private List<MapPolylineImpl> trackMPLI;
	
	private LayerGroup groupName;
	private List<Layer> subCaminos;
	private List<Double> distance;
	private List<Double> duration;
	
	private double cost;
	private Color color;
	
	//MapPolygon
	
	public TourResult() {
		nombre = "";
		camino = new ArrayList<Integer>();
		track = new ArrayList<List<Coordinate>>();
		groupName = new LayerGroup("");
		subCaminos = new ArrayList<Layer>();
		cost = 0.0;
		distance = new ArrayList<Double>();
		duration = new ArrayList<Double>();
		color = Color.black;
	}
	
	public TourResult(String n, List<Integer> cam, List<List<Coordinate>> t, LayerGroup gn, List<Layer> sc, double cos,  List<Double> dis, List<Double> dur, Color col) {
		nombre = n;
		camino = cam;
		track = t;
		groupName = gn;
		subCaminos = sc;
		cost = cos;
		distance = dis;
		duration = dur;
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

	public List<MapPolylineImpl> getTrackMPLI(){
		return trackMPLI;
	}
	
	public LayerGroup getGroupName(){
		return groupName;
	}
	
	public List<Layer> getSubCaminos(){
		return subCaminos;
	}
	
	public double getCost(){
		return cost;
	}
	
	public List<Double> getDistance(){
		return distance;
	}
	
	public List<Double> getDuration(){
		return duration;
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
	
	public void setTrackMPLI(List<MapPolylineImpl> m){
		trackMPLI = m;
	}
	
	public void setGroupName(LayerGroup gn){
		groupName = gn;
	}
	
	public void setSubCaminos(List<Layer> sc){
		subCaminos = sc;
	}
	
	public void setCost(double c){
		cost = c;
	}
	
	public void setDistance(List<Double> d){
		distance = d;
	}
	
	public void setDuration(List<Double> d){
		duration = d;
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
	
	public void addListDistance(Double value) {
		distance.add(value);
	}
	
	public void addListDuration(Double value) {
		duration.add(value);
	}
	
	public double CostTotalDistance() {
		double value = 0.0;
		for(int i=0;i<distance.size();i++) {
			value += distance.get(i);
		}
		return value;
	}
	
	public double CostTotalDuration() {
		double value = 0.0;
		for(int i=0;i<duration.size();i++) {
			value += duration.get(i);
		}
		return value;
	}
	
	public String getTextCamino() {
		String text = "";
		for(int i=0;i<camino.size();i++) {
			text+="["+camino.get(i)+"]";
		}
		return text;
	}
	
	public double getCosteTotal(String type) {
		double value = 0.0;
		if(type.equals("DISTANCE"))
			value = CostTotalDistance();
		else if(type.equals("DURATION"))
			value = CostTotalDuration();
		return value;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return nombre+" C:"+cost;
	}
}
