// License: GPL. For details, see Readme.txt file.
package org.openstreetmap.gui.jmapviewer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.TreeMap;

import javax.json.JsonReader;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openstreetmap.gui.jmapviewer.events.JMVCommandEvent;
import org.openstreetmap.gui.jmapviewer.interfaces.ICoordinate;
import org.openstreetmap.gui.jmapviewer.interfaces.JMapViewerEventListener;
import org.openstreetmap.gui.jmapviewer.interfaces.MapPolygon;
import org.openstreetmap.gui.jmapviewer.interfaces.TileLoader;
import org.openstreetmap.gui.jmapviewer.interfaces.TileSource;
import org.openstreetmap.gui.jmapviewer.tilesources.BingAerialTileSource;
import org.openstreetmap.gui.jmapviewer.tilesources.OsmTileSource;

import com.kitfox.svg.Polyline;
import com.osrm.services.Route;

import tools.and.utilities.EjercicioSolucion;
import tools.and.utilities.GestionNodesRoutes;
import tools.and.utilities.ListCustom;
import tools.and.utilities.Nodo;
import tools.and.utilities.NodosList;
import tools.and.utilities.ParametersFile;
import tools.and.utilities.PointProblem;
import tools.and.utilities.ProblemFile;
import tools.and.utilities.RouteJSON;
import tools.and.utilities.RouteResult;
import tools.and.utilities.Ruta;
import tools.and.utilities.SettingLKH;
import tools.and.utilities.TourResult;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;





/**
 * Plataforma de experimentacion para algoritmo LKH-3 con JOSM
 *
 * @author Germán Valdearenas Jiménez
 *
 */
public class Demo extends JFrame implements JMapViewerEventListener {

    private static final long serialVersionUID = 1L;

    private final JMapViewerTree treeMap;
    
    //private final JList listaPuntos;

    private final JLabel zoomLabel;
    private final JLabel zoomValue;

    private final JLabel mperpLabelName;
    private final JLabel mperpLabelValue;
    
    private boolean pointFlag;
    
    private NodosList ejercicio;
    private SettingLKH pSetting;
    private GestionNodesRoutes pGestionNR;
    private List<TourResult> ListTours;
    //private JComboBox<TourResult> cbRouteSelector;
    
    private boolean errorLKH;
    

    //MatrixPoints rawMatrixPoint;
    
    /**
     * Constructs the {@code Demo}.
     */
    public Demo() {
        super("JMapViewer Demo");
        setSize(400, 400);
        pointFlag = true;
        treeMap = new JMapViewerTree("Vehicles or routes");
        errorLKH = false;
        
        map().addJMVListener(this);
        
        ejercicio = new NodosList();
        try {
			pSetting = new SettingLKH();
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
        pGestionNR = new GestionNodesRoutes();
        ListTours = new ArrayList<TourResult>();
        PointProblem ppNewPoint = new PointProblem(this);
        
        
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        JPanel panelTop = new JPanel(new BorderLayout());
        JPanel panelDown = new JPanel(new BorderLayout());
        JPanel panelTopFirst = new JPanel();
        JPanel panelTopSecond = new JPanel();
        JPanel panelDownFirst = new JPanel();
        JPanel panelDownSecond = new JPanel();
        
        
        JPanel panelMain = new JPanel();
        panelMain.setLayout (new BorderLayout());
        
        
        JTabbedPane jTabbePane = new JTabbedPane();
        JPanel panelBase = new JPanel();
        panelBase.setLayout (new BorderLayout());

        mperpLabelName = new JLabel("Meters/Pixels: ");
        mperpLabelValue = new JLabel(String.format("%s", map().getMeterPerPixel()));

        zoomLabel = new JLabel("Zoom: ");
        zoomValue = new JLabel(String.format("%s", map().getZoom()));

        panelMain.add(panelTop, BorderLayout.NORTH);
        panelMain.add(panelDown, BorderLayout.SOUTH);
        panelTop.add(panelTopFirst, BorderLayout.NORTH);
        panelTop.add(panelTopSecond, BorderLayout.SOUTH);
        JLabel helpLabel = new JLabel("Use left mouse button to create node, right mouse button to move\n "
                + "and mouse wheel to zoom.");
        panelDownSecond.add(helpLabel);
        panelDown.add(panelDownFirst, BorderLayout.NORTH);
        panelDown.add(panelDownSecond, BorderLayout.SOUTH);
        JButton button = new JButton("setDisplayToFitMapMarkers");
        button.addActionListener(e -> map().setDisplayToFitMapMarkers());
        
        JButton btLoad = new JButton("Load");
        btLoad.addActionListener(e -> {
        	clearPoints();
        	JFileChooser fileChooser = new JFileChooser();
        	fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        	int result = fileChooser.showOpenDialog(this);
        	if (result == JFileChooser.APPROVE_OPTION) {
        	    File selectedFile = fileChooser.getSelectedFile();
        	    System.out.println("Selected file: " + selectedFile.getAbsolutePath());
        	    try {
        	    	
					LoadProyect(selectedFile);
				} catch (IOException | JAXBException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        JButton btSave = new JButton("Save");
        btSave.addActionListener(e -> {
        	JFrame parentFrame = new JFrame();
        	 
        	JFileChooser fileChooser = new JFileChooser();
        	fileChooser.setDialogTitle("Specify a file to save");   
        	int userSelection = fileChooser.showSaveDialog(parentFrame);
        	if (userSelection == JFileChooser.APPROVE_OPTION) {
        	    File fileToSave = fileChooser.getSelectedFile();
        	    //if(!fileToSave.exists()) {
        	    	try {
						SaveProyect(fileToSave);
					} catch (IOException | JAXBException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
        	    //}
        	    
        	}
        });
        
        
        JButton btClear = new JButton("Clear");
        btClear.addActionListener(e -> clearPoints());
        
        //JButton btGenerateRoute = new JButton("Generate Route");
        /*btGenerateRoute.addActionListener(e ->{
        	if(clearFiles()) 
        		GenerateRoute();
        	else
        		JOptionPane.showMessageDialog(null, 
	      				  "Files could not be initialized", "Error", JOptionPane.ERROR_MESSAGE);
        		
        });*/
        
        pGestionNR.btDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Item seleccionado: "+pGestionNR.getItemIndexSelection());
				//dar un aviso de confirmarcion
				if (JOptionPane.showConfirmDialog(null, "Are you sure?", "WARNING",
				        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					ejercicio.delete(pGestionNR.getItemIndexSelection());
					refresh();
					pGestionNR.listUpdate(ejercicio);
					drawPointInMap();
					pGestionNR.clearDisplay();
				    // yes option
				} else {
				    // no option
				}
				
			}
		});
        
        pGestionNR.btModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ejercicio.get(pGestionNR.getItemIndexSelection()).setName(pGestionNR.getName());
				ejercicio.get(pGestionNR.getItemIndexSelection()).setDemand(pGestionNR.getDemand());
				refresh();
				pGestionNR.listUpdate(ejercicio);
				drawPointInMap();
				pGestionNR.clearDisplay();
			}
		});

        JComboBox<TileSource> tileSourceSelector = new JComboBox<>(new TileSource[] {
                new OsmTileSource.TransportMap(),
                new OsmTileSource.Transport(),
                new OsmTileSource.OSMFrance(),
                new BingAerialTileSource(),
        });
        //tileSourceSelector.setSelectedIndex(2);
        /*tileSourceSelector.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
            	//Thread.sleep(2000);
				map().setTileSource((TileSource) e.getItem());
            }
        });*/
        //JComboBox<TileLoader> tileLoaderSelector;
        /*tileLoaderSelector = new JComboBox<>(new TileLoader[] {new OsmTileLoader(map())});
        tileLoaderSelector.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                map().setTileLoader((TileLoader) e.getItem());
                
            }
        });*/
        //map().setTileLoader((TileLoader) tileLoaderSelector.getSelectedItem());
        panelTopFirst.add(tileSourceSelector);
        //panelTop.add(tileLoaderSelector);
        final JCheckBox showMapMarker = new JCheckBox("Show Nodes");
        showMapMarker.setSelected(map().getMapMarkersVisible());
        showMapMarker.addActionListener(e -> map().setMapMarkerVisible(showMapMarker.isSelected()));
        panelTopFirst.add(showMapMarker);
        ///
        final JCheckBox showTreeLayers = new JCheckBox("List Route");
        showTreeLayers.setSelected(true);
        treeMap.setTreeVisible(true);
        showTreeLayers.addActionListener(e -> treeMap.setTreeVisible(showTreeLayers.isSelected()));
        panelTopFirst.add(showTreeLayers);
        ///
        final JCheckBox showToolTip = new JCheckBox("ToolTip visible");
        showToolTip.addActionListener(e -> map().setToolTipText(null));
        panelTopFirst.add(showToolTip);
        ///
        final JCheckBox showTileGrid = new JCheckBox("Tile grid visible");
        showTileGrid.setSelected(map().isTileGridVisible());
        showTileGrid.addActionListener(e -> map().setTileGridVisible(showTileGrid.isSelected()));
        panelTopFirst.add(showTileGrid);
        map().setZoomControlsVisible(true);
        
        pSetting.btnGenerateRoutes.addActionListener(e ->{
        	if(clearFiles()) {
        		if(pSetting.getInitialTourAlgorithm().equals("ALL") ) {
        			
        			List<String> tiposAlgoritmos = Arrays.asList("BORUVKA", "GREEDY", "MOORE", "MTSP", "NEAREST-NEIGHBOR", "WALK");
        			for(int i=0;i<tiposAlgoritmos.size();i++) {
        				GenerateRoute(tiposAlgoritmos.get(i));
        			}
        		}
        		else
        			GenerateRoute(pSetting.getInitialTourAlgorithm());
        		
        		JOptionPane.showMessageDialog(null, 
	      				  "Route generated", "", JOptionPane.OK_OPTION);
        	}	
        	else
        		JOptionPane.showMessageDialog(null, 
	      				  "Files could not be initialized", "Error", JOptionPane.ERROR_MESSAGE);
        		
        });
        
        panelTopSecond.add(btLoad);
        panelTopSecond.add(btSave);
        panelTopSecond.add(btClear);
        //panelTopSecond.add(btGenerateRoute);
        
        panelDownFirst.add(zoomLabel);
        panelDownFirst.add(zoomValue);
        panelDownFirst.add(mperpLabelName);
        panelDownFirst.add(mperpLabelValue);

        panelMain.add(treeMap, BorderLayout.CENTER);


        map().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent b) {
                if (b.getButton() == MouseEvent.BUTTON1 && pointFlag) {//left button
                	map().getAttribution().handleAttribution(b.getPoint(), true);
                	ppNewPoint.setVisible(true);

                    ppNewPoint.FillData(map().getPosition(b.getPoint()).getLat(), map().getPosition(b.getPoint()).getLon());
                    
                    ppNewPoint.setModalityType(Dialog.ModalityType.MODELESS);
                    MapMarkerDot myPoint = new MapMarkerDot(map().getPosition(b.getPoint()).getLat(),map().getPosition(b.getPoint()).getLon());
                    myPoint.setBackColor(Color.blue);
    	            ppNewPoint.setName("P"+(map().mapMarkerList.size()+1));
                    
    	            System.out.println("Antes de los botones ");
					ppNewPoint.okButton.addActionListener(e -> {
						ppNewPoint.setVisible(false);
						myPoint.setName(ppNewPoint.getName());
						map().addMapMarker(myPoint);
						ppNewPoint.clearActionListener();
						addNodoEjercicio(ppNewPoint.getName(), ppNewPoint.getDemand(), new Coordinate(ppNewPoint.getLat(), ppNewPoint.getLon()));
						pGestionNR.listUpdate(ejercicio);
						pSetting.updateMaxMTSPSize(ejercicio.size()-1);
    				});
                    
                    ppNewPoint.cancelButton.addActionListener(e -> {
                    	ppNewPoint.setVisible(false);
                    	ppNewPoint.clearActionListener();
					});
                    
                   
                }
                if (b.getButton() == MouseEvent.BUTTON2) {//middle button
                    map().getAttribution().handleAttribution(b.getPoint(), true);
                }
                if (b.getButton() == MouseEvent.BUTTON3) {//right button
                    map().getAttribution().handleAttribution(b.getPoint(), true);
                }
            }
        });
        
        map().addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                Point p = e.getPoint();
                boolean cursorHand = map().getAttribution().handleAttributionCursor(p);
                if (cursorHand) {
                    map().setCursor(new Cursor(Cursor.HAND_CURSOR));
                } else {
                    map().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
                if (showToolTip.isSelected()) map().setToolTipText(map().getPosition(p).toString());
            }
        });
        
        tileSourceSelector.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
            	//Thread.sleep(2000);
				map().setTileSource((TileSource) e.getItem());
            }
        });
        
        jTabbePane.add("Mapa",panelMain);
        jTabbePane.add("Gestion",pGestionNR);
        jTabbePane.add("Configuracion LKH",pSetting);
        
        add(jTabbePane);
    }

    private JMapViewer map() {
        return treeMap.getViewer();
    }

    private static Coordinate c(double lat, double lon) {
        return new Coordinate(lat, lon);
    }

    /**
     * @param args Main program arguments
     */
    public static void main(String[] args) {
        new Demo().setVisible(true);
    }

    private void updateZoomParameters() {
        if (mperpLabelValue != null)
            mperpLabelValue.setText(String.format("%s", map().getMeterPerPixel()));
        if (zoomValue != null)
            zoomValue.setText(String.format("%s", map().getZoom()));
    }
    
   
    private void GenerateRoute(String algoritmo) {
    	if(ejercicio.size() > 1) {
    		//vamos a crear el fichero
    		ProblemFile fileTSP = new ProblemFile();
    		fileTSP.setNAME("TestCVRP");
    		fileTSP.setTYPE(pSetting.getTypeProblem());
    		fileTSP.setDIMENSION(ejercicio.getNodes().size());
    		fileTSP.setEDGE_WEIGHT_TYPE("EXPLICIT");
    		fileTSP.setEDGE_WEIGHT_FORMAT("FULL_MATRIX");
    		if(pSetting.getInitialDistanceOrDuration().equals("DISTANCE"))
    			fileTSP.setEDGE_WEIGHT_SECTION(ejercicio.getMatrixDistance());
    		else if(pSetting.getInitialDistanceOrDuration().equals("DURATION"))
				fileTSP.setEDGE_WEIGHT_SECTION(ejercicio.getMatrixDuration());
    		//fileTSP.setEDGE_WEIGHT_SECTION(ejercicio.getMatrixDistance());
    		System.out.println("Capacidad truck "+pSetting.getCapacityTruck());
    		fileTSP.setCAPACITY(pSetting.getCapacityTruck());
    		fileTSP.setDEMAND_SECTION(ejercicio.getListDemands());
    		fileTSP.setDEPOT_SECTION("1\n-1");
    		if(fileTSP.generateFile(pSetting.getTypeProblem())) {
    			ParametersFile filePAR = new ParametersFile();
    			
    			filePAR.setBoolVEHICLES(true);
    			filePAR.setVEHICLES(pSetting.getNumberVehicle());
    			    			
    			if(pSetting.getBoolMTSPmin()) {
    				filePAR.setBoolMTSP_MIN_SIZE(pSetting.getBoolMTSPmin());
        			filePAR.setMTSP_MIN_SIZE(pSetting.getMTSPmin());
    			}
    			if(pSetting.getBoolMTSPmax()) {
    				filePAR.setBoolMTSP_MAX_SIZE(pSetting.getBoolMTSPmax());
        			filePAR.setMTSP_MAX_SIZE(pSetting.getMTSPmax());
    			}
    			if(pSetting.getBoolPatchingA()) {
    				filePAR.setBoolPATCHING_A(pSetting.getBoolPatchingA());
        			filePAR.setPATCHING_A(pSetting.getPatchingA());
        			filePAR.setPATCHING_A_EXT(pSetting.getPatchingAResExt());
    			}
    			if(pSetting.getBoolPatchingC()) {
    				filePAR.setBoolPATCHING_C(pSetting.getBoolPatchingC());
        			filePAR.setPATCHING_C(pSetting.getPatchingC());
        			filePAR.setPATCHING_C_EXT(pSetting.getPatchingCResExt());
    			}
    			if(pSetting.getBoolRuns()) {
    				filePAR.setBoolRUNS(pSetting.getBoolRuns());
        			filePAR.setRUNS(pSetting.getRuns());
    			}
    			
    			//filePAR.setBoolMOVE_TYPE(value);
    			//filePAR.setBoolOPTIMUM(value);
    			//filePAR.setBoolPATCHING_A(value);
    			
    			//filePAR.setBoolPATCHING_C(value);
    			//filePAR.setBoolRUNS(value);
    			//filePAR.setINITIAL_TOUR_ALGORITHM(pSetting.getInitialTourAlgorithm());
    			filePAR.setINITIAL_TOUR_ALGORITHM(algoritmo);
    			
    			filePAR.setBACKTRACKING(pSetting.getBacktracking());
    			if(filePAR.generateFile()) {
    				executeLKH();
    				if(!errorLKH) {
    					
    					//System.out.println("Cantidad de componentes inicial "+map().getMapPolygonList().size());
    					
    					//map().removeAllMapPolygons();
    					//treeMap.getTree().deleteAllNodesChildren();
	    				
    					RouteResult routeResult = new RouteResult();
	    				routeResult.testLectura();
	    				routeResult.testLecturaTours();
	    				ListTours = routeResult.getListTours();
	    				System.out.println("Tam ListCaminos: "+routeResult.getListCaminos().size());
	    				for(int i=0;i<routeResult.getListCaminos().size();i++) {
	    					System.out.println("Camino "+(i+1));
	    					for(int j=0;j<routeResult.getListCaminos().get(i).size();j++) {
	    						System.out.println(routeResult.getListCaminos().get(i).get(j));
	    					}
	    				}
	    				EjercicioSolucion nuevoEjercicioSolucion = new EjercicioSolucion();
	    						nuevoEjercicioSolucion.setProblema(pSetting.getTypeProblem());
	    						nuevoEjercicioSolucion.setAlgoritmo(algoritmo);
	    						nuevoEjercicioSolucion.setNdeVehiculos(pSetting.getNumberVehicle());
	    						nuevoEjercicioSolucion.setDistanciaDuracion(pSetting.getInitialDistanceOrDuration());
	    						nuevoEjercicioSolucion.setCapacidad(pSetting.getCapacityTruck());
	    						nuevoEjercicioSolucion.setCostTotal(routeResult.GetCostAllTours(pSetting.getInitialDistanceOrDuration()));
	    						nuevoEjercicioSolucion.setListCaminosMod(routeResult.getListCaminos());
	    						nuevoEjercicioSolucion.setCost(routeResult.getCost());
	    						
	    				
	    				/*public EjercicioSolucion(String p, String a, int n, String d, Integer Cap, Double Cos, List<ListCustom> lc, List<Double> cst) {
		Problema = p;
		Algoritmo = a;
		NdeVehiculos = n;
		DistanciaDuracion = d;
		Capacidad = Cap;
		CostTotal = Cos;
		listCaminos = lc;
		cost = cst;
	}*/
	    				
	    				ejercicio.addEjercicioSolucion(nuevoEjercicioSolucion);
	    				//private String Problema; //Tipo de problema
	    				//private String Algoritmo; //algoritmo inicial
	    				//private Integer NdeVehiculos; //numero de vehiculos
	    				//private String DistanciaDuracion; //Distancia o duracion
	    				//private Integer Capacidad; //Capacidad
	    				//private double CostTotal; //Coste total de la solucion
	    				//private List<List<Integer>> listCaminos;
	    				//private List<Double> cost;
	    				//RouteResult;
	    				/*
    <ejercicios>
        <algoritmo>WALK</algoritmo>
        <capacidad>20</capacidad>
        <cost>3560.0</cost>
        <cost>3652.0</cost>
        <cost>2872.0</cost>
        <costTotal>0.0</costTotal>
        <distanciaDuracion>DISTANCE</distanciaDuracion>
        <ndeVehiculos>1</ndeVehiculos>
        <problema>CVRP</problema>
    </ejercicios>*/
	    				//public EjercicioSolucion(String p, String a, Integer n, String d, Integer Cap, double Cos, List<List<Integer>> lc, List<Double> cst) {
	    				//
	    				for(int i=0;i<routeResult.getListTours().size();i++) {
	    					routeResult.getListTours().get(i).setTrackMPLI(ejercicio.getTourMPLI(routeResult.getListTours().get(i).getCamino(), selectColorRand(i)));
	    					routeResult.getListTours().get(i).setDistance(ejercicio.getListDistanceTour(routeResult.getListTours().get(i).getCamino()));
	    					routeResult.getListTours().get(i).setDuration(ejercicio.getListDurationTour(routeResult.getListTours().get(i).getCamino()));
	    					routeResult.getListTours().get(i).setColor(selectColorRand(i));
	    					
	    				}
	    				
	    				//LayerGroup layerAlgoritm = new LayerGroup("Problema: "+pSetting.getTypeProblem()+" Algoritmo "+pSetting.getInitialTourAlgorithm());
	    				
	    				LayerGroup layerAlgoritm = new LayerGroup("Problema: "+pSetting.getTypeProblem()+" Algoritmo "+algoritmo+" Cost Total("+pSetting.getInitialDistanceOrDuration()+"): "+routeResult.GetCostAllTours(pSetting.getInitialDistanceOrDuration()));
	    				for(int i=0;i<routeResult.getListTours().size();i++) {
	    					//JMapViewerTree tempTM = new JMapViewerTree("algoritmo "+i);
	    					//"<html><center>Select route or stage<br />to display it on the map</center></html>"
	    					
	    					
	    					LayerGroup testGroup = new LayerGroup("Route "+(i+1)+" "
	    									+routeResult.getListTours().get(i).getTextCamino()
	    									//+" Cost:"+routeResult.getListTours().get(i).getCost());
	    									+" Cost:"+routeResult.getListTours().get(i).CostTotalDistance());
	    					layerAlgoritm.add(testGroup);
	    					for(int j=0;j<routeResult.getListTours().get(i).getTrackMPLI().size();j++) {
	    						Layer tempLayer = testGroup.addLayer("Stage "+(j+1)+" Distance:"+routeResult.getListTours().get(i).getDistance().get(j)+
	    								" Duration:"+routeResult.getListTours().get(i).getDuration().get(j));
	    						MapPolylineImpl mpli = routeResult.getListTours().get(i).getTrackMPLI().get(j);
	    						mpli.setLayer(tempLayer);
	    						map().addMapPolygon(mpli);
	    						treeMap.addLayer(tempLayer);
	    						//tempTM.addLayer(tempLayer);
	    						
	    					}
	    					//tempTM.setVisible(true);
	    					//layerAlgoritm.add(testGroup);
	    					
	    				}
	    				//treeMap.add(layerAlgoritm);
	    				
    				}
    			}
    			else{
    				JOptionPane.showMessageDialog(null, 
    	      				  "The parameter file could not be generated", "Error", JOptionPane.ERROR_MESSAGE);
    				System.out.println("No se pudo generar el fichero de parametros");
    			}
    		}
    		else{
    			JOptionPane.showMessageDialog(null, 
      				  "The problem file could not be generated", "Error", JOptionPane.ERROR_MESSAGE);
    			System.out.println("No se pudo generar el fichero de problemas");
    		}
    	}
    	else {
    		JOptionPane.showMessageDialog(null, 
    				  "Insufficient number of nodes", "Error", JOptionPane.ERROR_MESSAGE);
    		System.out.println("Fallo");
    	}
    }
    
    private Color selectColorRand(int i) {
    	Color color = Color.black;//por defecto
    	int value = i%5;
    	if(value == 0) {
    		color = Color.black;
        }
    	else if(value == 1) {
    		color = Color.blue;
        }
    	else if(value == 2) {
    		color = Color.cyan;
        }
    	else if(value == 3) {
    		color = Color.green;
        }
    	else if(value == 4) {
    		color = Color.pink;
        }
    	else if(value == 5) {
    		color = Color.red;
        }
    	return color;
    }
        
    private void clearPoints() {
    	System.out.println("Test clear ok");
    	map().removeAllMapMarkers();
    	map().removeAllMapPolygons();
    	map().removeAll();
    	ejercicio.clear();
    	pGestionNR.clearList();
    	ListTours.clear();
        treeMap.getTree().deleteAllNodesChildren();
        treeMap.addLayer("");
        map().setZoomControlsVisible(true);
        System.out.println("Clear Points");
    }
    
    private void refresh() {
    	System.out.println("Test clear ok");
    	map().removeAllMapMarkers();
    	map().removeAllMapPolygons();
    	map().removeAll();
    	pGestionNR.clearList();
    	ListTours.clear();
		treeMap.getTree().deleteAllNodesChildren();
        pGestionNR.clearDisplay();
        treeMap.addLayer("");
        map().setZoomControlsVisible(true);
        System.out.println("Refresh");
    }
        
    /*void SaveProyect2(File fileToSave) throws IOException, JAXBException{
    	
    	JAXBContext context = JAXBContext.newInstance(NodosList.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		
		List<Ruta> routes = new ArrayList<Ruta>();
		Ruta ruta = new Ruta();
		ruta.setDistance(20.1);
		ruta.setCoordinateFrom(new Coordinate(20, 30));
		
		routes.add(ruta);
			
		Nodo n1 = new Nodo();
		n1.setName("Nodo 1");
		n1.setDemand(100);
		n1.setCoordinate(new Coordinate(10, 10));
			
		Nodo n2 = new Nodo();
		n2.setName("Nodo 2");
		n2.setDemand(200);
		n2.setCoordinate(new Coordinate(20, 10));
		
		//n2.setListRoutes(routes);
		
		Nodo n3 = new Nodo();
		n3.setName("Nodo 0");
		n3.setDemand(300);
		n3.setCoordinate(new Coordinate(10, 30));
		
		List<Nodo> myList = new ArrayList<Nodo>();
		myList.add(n1);
		myList.add(n2);
		myList.add(n3);
		
		NodosList nodes = new NodosList();
		nodes.setNodes(myList);*/
		
		/*Ruta ruta = new Ruta();
		ruta.setDistance(20.1);
		ruta.setCoordinateFrom(new Coordinate(20, 30));*/
		
		/*FileOutputStream fos = new FileOutputStream(fileToSave);
		marshaller.marshal(nodes, fos);
		fos.close();
    	
    }*/
    
    /*void LoadProyect2(File fileToLoad) throws IOException, JAXBException{
    	
    	JAXBContext context2 = JAXBContext.newInstance(NodosList.class);
		System.out.println("ruta del ejercicio "+fileToLoad.getAbsolutePath());

		
		Unmarshaller unmarshaller = context2.createUnmarshaller();
		var ejer = (NodosList) unmarshaller.unmarshal(fileToLoad);
    }*/
    
    void LoadProyect(File fileToLoad) throws IOException, JAXBException{
    	
    	JAXBContext context2 = JAXBContext.newInstance(NodosList.class);
		//Marshaller marshaller = context.createMarshaller();
		//marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		//System.out.println("tamaño de ejercicio "+ejercicio.size());
		System.out.println("ruta del ejercicio "+fileToLoad.getAbsolutePath());

		
		Unmarshaller unmarshaller = context2.createUnmarshaller();
		ejercicio = (NodosList) unmarshaller.unmarshal(fileToLoad);
		pSetting.updateMaxMTSPSize(ejercicio.size()-1);
		drawPointInMap();
		System.out.println("Tam ejercicio: "+ejercicio.getNodes().size());
		pGestionNR.listUpdate(ejercicio);
		System.out.println(ejercicio.getMatrixDistance());
		System.out.println(ejercicio.getMatrixDuration());
		System.out.println(ejercicio.getListCoordenades());
		System.out.println(ejercicio.getListDemands());
    }
    
    void SaveProyect(File fileToSave) throws IOException, JAXBException{
    	
    	JAXBContext context = JAXBContext.newInstance(NodosList.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		System.out.println("tamaño de ejercicio "+ejercicio.size());
		FileOutputStream fos = new FileOutputStream(fileToSave);
		marshaller.marshal(ejercicio, fos);
		fos.close();
    	
    }
    
    private void addNodoEjercicio(String name, int demand, Coordinate coor) {
    	System.out.println("Nombre: " + name);
    	System.out.println("Capacidad " + demand);
    	System.out.println("Coordenadas" + coor);
    	Nodo newNodo = new Nodo();
    	newNodo.setName(name);
		newNodo.setDemand(demand);
		newNodo.setCoordinate(coor);
    	if(ejercicio.size() == 0) {
    		ejercicio.add(newNodo);
    		
    	}
    	else {
    		//listar los anteriores y crear las rutas pertinentes
    		
    		//listar de nuevo punto a nodos previos
    		for(int i = 0; i < ejercicio.size(); i++) {
    			ejercicio.get(i).getCoordinate();
    			Ruta newRuta = new Ruta(ejercicio.get(i).getCoordinate(),newNodo.getCoordinate());
    			newRuta.setNameDestinate(name);
    			ejercicio.get(i).addRuta(newRuta);
    		}
    		
    		//listar de nodos previos a nuevo punto
    		for(int i = 0; i < ejercicio.size(); i++) {
    			ejercicio.get(i).getCoordinate();
    			Ruta newRuta = new Ruta(newNodo.getCoordinate(), ejercicio.get(i).getCoordinate());
    			newRuta.setNameDestinate(ejercicio.get(i).getName());
    			newNodo.addRuta(newRuta);
    		}
    		ejercicio.add(newNodo);
    		testRutas();
    	}
    }
    
    
    /*private void testExport() throws IOException, JAXBException{
    	
		JAXBContext context = JAXBContext.newInstance(NodosList.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		
		List<Ruta> routes = new ArrayList<Ruta>();
		Ruta ruta = new Ruta();
		ruta.setDistance(20.1);
		ruta.setCoordinateFrom(new Coordinate(20, 30));
		
		routes.add(ruta);
			
		Nodo n1 = new Nodo();
		n1.setName("Nodo 1");
		n1.setDemand(100);
		//n1.setCoordinate(new Coordinate(10, 10));
			
		Nodo n2 = new Nodo();
		n2.setName("Nodo 2");
		n2.setDemand(200);
		
		Nodo n3 = new Nodo();
		n3.setName("Nodo 0");
		n3.setDemand(300);
		//n3.setCoordinate(new Coordinate(10, 30));
		
		List<Nodo> myList = new ArrayList<Nodo>();
		myList.add(n1);
		myList.add(n2);
		myList.add(n3);
		
		NodosList nodes = new NodosList();
		nodes.setNodes(myList);
		
		
		FileOutputStream fos = new FileOutputStream("DATOS_NODOS.xml");
		marshaller.marshal(nodes, fos);
		fos.close();
    }*/
    
    void executeLKH() {
    	//Runtime runtime = Runtime.getRuntime();
    	
    	
    	File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        
    	try {//~/Documents/tfg/lkh-3/LKH-3.0.6/
    		
			//Process pr = runtime.exec("cd ~/Documents/tfg/lkh-3/LKH-3.0.6/ | ./LKH test.par");
    		//String[] cmd = {"/bin/sh", "./Documents/tfg/lkh-3/LKH-3.0.6/LKH /Documents/tfg/lkh-3/LKH-3.0.6/test.par"};
    		//String[] cmd = {"/bin/sh", "cd ~/Documentos", "mkdir aaaa"};
    		String[] cmd = {"/bin/sh", "-c", "./LKH parFile.par"};
    		//String[] cmd = {"/bin/sh", "/Documents/tfg/lkh-3/LKH-3.0.6/", "./LKH test.par"};
    		//Runtime.getRuntime().exec(cmd);
    		String line;
    		//Process p = Runtime.getRuntime().exec("ping -c 3 google.com;");
    		Process p = Runtime.getRuntime().exec(cmd);
    		
    		BufferedReader bri = new BufferedReader
    		        (new InputStreamReader(p.getInputStream()));
    		BufferedReader bre = new BufferedReader
    		        (new InputStreamReader(p.getErrorStream()));
    		while ((line = bri.readLine()) != null) {
    			System.out.println(line);
    		}
    		bri.close();
    		
    		errorLKH = false;
    		String msgError = "";
    		while ((line = bre.readLine()) != null) {
    			if(errorLKH) {
    				msgError += line;
    			}
    			if(line.equals("*** Error ***")) {
    				errorLKH = true;
    			}
				System.out.println(line);
    		}
    		
    		if(errorLKH)
    			JOptionPane.showMessageDialog(null, msgError, "Error",JOptionPane.ERROR_MESSAGE);
    		
    		bre.close();
    		try {
    			if(!errorLKH)
    				p.waitFor();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
           // En el finally cerramos el fichero, para asegurarnos
           // que se cierra tanto si todo va bien como si salta 
           // una excepcion.
           try{                    
              if( null != fr ){   
              //   fr.close();     
              }                  
           }catch (Exception e2){ 
              //e2.printStackTrace();
           }
        }
    }
    
    void drawPointInMap() {
    	
    	for(int i = 0 ; i < ejercicio.size(); i++) {
    		MapMarkerDot myPoint = new MapMarkerDot(ejercicio.get(i).getCoordinate().getLat(),ejercicio.get(i).getCoordinate().getLon());
            myPoint.setName(ejercicio.get(i).getName());
            myPoint.setBackColor(Color.blue);
			map().addMapMarker(myPoint);
    	}
    }
    
    void testRutas() {
    	for(int i=0; i<ejercicio.size();i++) {
    		System.out.println("nodo "+ejercicio.get(i).getName()+" "+ejercicio.get(i).getDemand()+" "+ejercicio.get(i).getCoordinate()  );
    		for(int j=0; j<ejercicio.get(i).getListRoutes().size();j++) {
    			System.out.println(ejercicio.get(i).getListRoutes().get(j).getNameFrom()+" "+
    					ejercicio.get(i).getListRoutes().get(j).getNameTo()+" "+
    					ejercicio.get(i).getListRoutes().get(j).getDistance()+" "+
    					ejercicio.get(i).getListRoutes().get(j).getDuration());
    		}
    	}
    }
    
    boolean clearFiles() {
    	boolean flag = true;
    	//flag = clearFile("parFile.par");
    	if(flag)
    		//flag = clearFile("problemFile.tsp");
    	if(flag)
    		flag = clearFile("solucionTest.txt");
    	if(flag)
    		flag = clearFile("solucionMTSP.txt");
    	return flag;
    }
    
    boolean clearFile(String file) {
    	boolean res = false;
    	FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
        	fichero = new FileWriter(file);
        	pw = new PrintWriter(fichero);
        	pw.print("");
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
    

    @Override
    public void processCommand(JMVCommandEvent command) {
        if (command.getCommand().equals(JMVCommandEvent.COMMAND.ZOOM) ||
                command.getCommand().equals(JMVCommandEvent.COMMAND.MOVE)) {
        	
            updateZoomParameters();
        }
    }
}