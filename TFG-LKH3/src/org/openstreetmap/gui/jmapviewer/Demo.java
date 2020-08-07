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

import tools.and.utilities.GestionNodesRoutes;
import tools.and.utilities.MatrixPoints;
import tools.and.utilities.NewProyectRoute;
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
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;





/**
 * Demonstrates the usage of {@link JMapViewer}
 *
 * @author Jan Peter Stotz
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
    private String proyectName;
    private String pathFile;
    
    private NodosList ejercicio;
    private SettingLKH pSetting;
    private GestionNodesRoutes pGestionNR;
    private List<TourResult> ListTours;
    private JComboBox<TourResult> cbRouteSelector;
    
    private boolean errorLKH;
    
    //private List<List<RouteJSON>> MatrixOfPoint = new ArrayList<List<RouteJSON>>();

    MatrixPoints rawMatrixPoint;
    
    /**
     * Constructs the {@code Demo}.
     */
    public Demo() {
        super("JMapViewer Demo");
        setSize(400, 400);
        pointFlag = true;
        proyectName = "";
        pathFile = "";
        treeMap = new JMapViewerTree("Zones");
        errorLKH = false;
        //listaPuntos = new JList();
        //listaPuntos.setSize(50, 100);;

        // Listen to the map viewer for user operations so components will
        // receive events and update
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
        NewProyectRoute npRoute = new NewProyectRoute(this);
        PointProblem ppNewPoint = new PointProblem(this);
        
        
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        JPanel panel = new JPanel(new BorderLayout());
        JPanel panelTop = new JPanel();
        JPanel panelBottom = new JPanel();
        JPanel panelBottomNew = new JPanel();
        JPanel helpPanel = new JPanel();
        
        //add tabbedpanel
        JTabbedPane jTabbePane = new JTabbedPane();
        JPanel panel1 = new JPanel();
        panel1.setLayout (new BorderLayout());
        JPanel panel2 = new JPanel();
        
        
        

        mperpLabelName = new JLabel("Meters/Pixels: ");
        mperpLabelValue = new JLabel(String.format("%s", map().getMeterPerPixel()));

        zoomLabel = new JLabel("Zoom: ");
        zoomValue = new JLabel(String.format("%s", map().getZoom()));

        panel1.add(panel, BorderLayout.NORTH);
        panel1.add(helpPanel, BorderLayout.SOUTH);
        panel.add(panelTop, BorderLayout.NORTH);
        panel.add(panelBottom, BorderLayout.CENTER);
        panel.add(panelBottomNew, BorderLayout.SOUTH);
        JLabel helpLabel = new JLabel("Use right mouse button to move,\n "
                + "left double click or mouse wheel to zoom.");
        helpPanel.add(helpLabel);
        JButton button = new JButton("setDisplayToFitMapMarkers");
        button.addActionListener(e -> map().setDisplayToFitMapMarkers());
        
        JButton btNew = new JButton("New");
        btNew.addActionListener(e -> {
        	
        	npRoute.setVisible(true);
        	npRoute.setModalityType(Dialog.ModalityType.MODELESS);
        	this.setEnabled(false);
        });
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
        
        JButton btExport = new JButton("Export");
        btExport.addActionListener(e -> {
			try {
				testExport();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (JAXBException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
        
        JButton btClear = new JButton("Clear");
        btClear.addActionListener(e -> clearPoints());
        
        JButton btGenerateRoute = new JButton("Generate Route");
        btGenerateRoute.addActionListener(e -> GenerateRoute());
        
        cbRouteSelector = new JComboBox<TourResult>();
        
        cbRouteSelector.setPreferredSize(new Dimension(190, 25));
        cbRouteSelector.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				map().removeAllMapPolygons();
				TourResult tr = (TourResult)e.getItem();
				
				for(int i=0; i<tr.getTrack().size();i++) {
					MapPolylineImpl mpl = new MapPolylineImpl(tr.getTrack().get(i));
					mpl.setColor(tr.getColor());
					map().addMapPolygon(mpl);
				}
				
			}
		});
        
        //JButton btTestXML = new JButton("Leer XML");
        //btTestXML.addActionListener(e -> ());
        
        JComboBox<TileSource> tileSourceSelector = new JComboBox<>(new TileSource[] {
                //new OsmTileSource.Mapnik(),
                new OsmTileSource.TransportMap(),
                new OsmTileSource.Transport(),
                new OsmTileSource.OSMFrance(),
                new BingAerialTileSource(),
        });
        tileSourceSelector.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                map().setTileSource((TileSource) e.getItem());
            }
        });
        JComboBox<TileLoader> tileLoaderSelector;
        tileLoaderSelector = new JComboBox<>(new TileLoader[] {new OsmTileLoader(map())});
        tileLoaderSelector.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                map().setTileLoader((TileLoader) e.getItem());
                
            }
        });
        map().setTileLoader((TileLoader) tileLoaderSelector.getSelectedItem());
        panelTop.add(tileSourceSelector);
        panelTop.add(tileLoaderSelector);
        final JCheckBox showMapMarker = new JCheckBox("Map markers visible");
        showMapMarker.setSelected(map().getMapMarkersVisible());
        showMapMarker.addActionListener(e -> map().setMapMarkerVisible(showMapMarker.isSelected()));
        panelBottom.add(showMapMarker);
        ///
        final JCheckBox showTreeLayers = new JCheckBox("Tree Layers visible");
        showTreeLayers.addActionListener(e -> treeMap.setTreeVisible(showTreeLayers.isSelected()));
        panelBottom.add(showTreeLayers);
        ///
        final JCheckBox showToolTip = new JCheckBox("ToolTip visible");
        showToolTip.addActionListener(e -> map().setToolTipText(null));
        panelBottom.add(showToolTip);
        ///
        final JCheckBox showTileGrid = new JCheckBox("Tile grid visible");
        showTileGrid.setSelected(map().isTileGridVisible());
        showTileGrid.addActionListener(e -> map().setTileGridVisible(showTileGrid.isSelected()));
        panelBottom.add(showTileGrid);
        final JCheckBox showZoomControls = new JCheckBox("Show zoom controls");
        showZoomControls.setSelected(map().getZoomControlsVisible());
        showZoomControls.addActionListener(e -> map().setZoomControlsVisible(showZoomControls.isSelected()));
        panelBottom.add(showZoomControls);
        final JCheckBox scrollWrapEnabled = new JCheckBox("Scrollwrap enabled");
        scrollWrapEnabled.addActionListener(e -> map().setScrollWrapEnabled(scrollWrapEnabled.isSelected()));
        panelBottom.add(scrollWrapEnabled);
        //panelBottom.add(button);
        
        panelBottomNew.add(btNew);
        panelBottomNew.add(btLoad);
        panelBottomNew.add(btSave);
        panelBottomNew.add(btExport);
        panelBottomNew.add(btClear);
        panelBottomNew.add(btGenerateRoute);
        panelBottomNew.add(cbRouteSelector);
        
        panelTop.add(zoomLabel);
        panelTop.add(zoomValue);
        panelTop.add(mperpLabelName);
        panelTop.add(mperpLabelValue);

        panel1.add(treeMap, BorderLayout.CENTER);

        //
        LayerGroup germanyGroup = new LayerGroup("Germany");
        Layer germanyWestLayer = germanyGroup.addLayer("Germany West");
        Layer germanyEastLayer = germanyGroup.addLayer("Germany East");
        //MapMarkerDot eberstadt = new MapMarkerDot(germanyEastLayer, "Eberstadt", 49.814284999, 8.642065999);
        //MapMarkerDot ebersheim = new MapMarkerDot(germanyWestLayer, "Ebersheim", 49.91, 8.24);
        //MapMarkerDot empty = new MapMarkerDot(germanyEastLayer, 49.71, 8.64);
        //MapMarkerDot darmstadt = new MapMarkerDot(germanyEastLayer, "Darmstadt", 49.8588, 8.643);
        //map().addMapMarker(eberstadt);
        //map().addMapMarker(ebersheim);
        //map().addMapMarker(empty);
        //Layer franceLayer = treeMap.addLayer("France");
        //map().addMapMarker(new MapMarkerDot(franceLayer, "La Gallerie", 48.71, -1));
        //map().addMapMarker(new MapMarkerDot(43.604, 1.444));
        //map().addMapMarker(new MapMarkerCircle(53.343, -6.267, 0.666));
        //map().addMapRectangle(new MapRectangleImpl(new Coordinate(53.343, -6.267), new Coordinate(43.604, 1.444)));
        //map().addMapMarker(darmstadt);
        treeMap.addLayer(germanyWestLayer);
        treeMap.addLayer(germanyEastLayer);

        //MapPolygon bermudas = new MapPolygonImpl(c(49, 1), c(45, 10), c(40, 5));
        //map().addMapPolygon(bermudas);
        //map().addMapPolygon(new MapPolygonImpl(germanyEastLayer, "Riedstadt", ebersheim, darmstadt, eberstadt, empty));

        //map().addMapMarker(new MapMarkerCircle(germanyWestLayer, "North of Suisse", new Coordinate(48, 7), .5));
        //Layer spain = treeMap.addLayer("Spain");
        //map().addMapMarker(new MapMarkerCircle(spain, "La Garena", new Coordinate(40.4838, -3.39), .002));
        //spain.setVisible(Boolean.FALSE);

        //Layer wales = treeMap.addLayer("UK");
        //map().addMapRectangle(new MapRectangleImpl(wales, "Wales", c(53.35, -4.57), c(51.64, -2.63)));

        // map.setDisplayPosition(new Coordinate(49.807, 8.6), 11);
        // map.setTileGridVisible(true);

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
                    //if(ppNewPoint.getValidate())
                    
    	            System.out.println("Antes de los botones ");
					ppNewPoint.okButton.addActionListener(e -> {
						ppNewPoint.setVisible(false);
						myPoint.setName(ppNewPoint.getName());
						map().addMapMarker(myPoint);
						//ppNewPoint.clearOkActionListener();
						ppNewPoint.clearActionListener();
						addNodoEjercicio(ppNewPoint.getName(), ppNewPoint.getDemand(), new Coordinate(ppNewPoint.getLat(), ppNewPoint.getLon()));
						pGestionNR.listUpdate(ejercicio);
						//System.out.println(ejercicio.getMatrixDistance());
						//System.out.println(ejercicio.getMatrixDuration());
						//System.out.println(ejercicio.getListCoordenades());
						//System.out.println(ejercicio.getListDemands());
    				});
                    
                    ppNewPoint.cancelButton.addActionListener(e -> {
                    	ppNewPoint.setVisible(false);
                    	//ppNewPoint.clearCancelActionListener();
                    	ppNewPoint.clearActionListener();
					});
                    
                   
                }
                if (b.getButton() == MouseEvent.BUTTON2) {//middle button
                    map().getAttribution().handleAttribution(b.getPoint(), true);
                    System.out.println("Button2a "+map().getPosition(b.getPoint()));
                    System.out.println("Button2b "+b.getPoint());
                }
                if (b.getButton() == MouseEvent.BUTTON3) {//right button
                    map().getAttribution().handleAttribution(b.getPoint(), true);
                    System.out.println("Button3a "+map().getPosition(b.getPoint()));
                    System.out.println("Button3b "+b.getPoint());
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
        
        jTabbePane.add("Mapa",panel1);
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
    
    private void GenerateRoute() {
    	if(ejercicio.size() > 1) {
    		//vamos a crear el fichero
    		ProblemFile fileTSP = new ProblemFile();
    		fileTSP.setNAME("TestCVRP");
    		fileTSP.setTYPE(pSetting.getTypeProblem());
    		fileTSP.setDIMENSION(ejercicio.getNodes().size());
    		fileTSP.setEDGE_WEIGHT_TYPE("EXPLICIT");
    		fileTSP.setEDGE_WEIGHT_FORMAT("FULL_MATRIX");
    		fileTSP.setEDGE_WEIGHT_SECTION(ejercicio.getMatrixDistance());
    		System.out.println("Capacidad truck "+pSetting.getCapacityTruck());
    		fileTSP.setCAPACITY(pSetting.getCapacityTruck());
    		fileTSP.setDEMAND_SECTION(ejercicio.getListDemands());
    		fileTSP.setDEPOT_SECTION("1\n-1");
    		if(fileTSP.generateFile("CVRP")) {
    			ParametersFile filePAR = new ParametersFile();
    			filePAR.setBoolVEHICLES(true);
    			filePAR.setVEHICLES(pSetting.getNumberVehicle());
    			//filePAR.setBoolINITIAL_TOUR_ALGORITHM(value);
    			//filePAR.getBoolMTSP_MIN_SIZE();
    			//filePAR.getBoolMTSP_MAX_SIZE();
    			//filePAR.setBoolINITIAL_TOUR_ALGORITHM(value);
    			//filePAR.setBoolMOVE_TYPE(value);
    			//filePAR.setBoolOPTIMUM(value);
    			//filePAR.setBoolPATCHING_A(value);
    			//filePAR.setBoolPATCHING_C(value);
    			//filePAR.setBoolRUNS(value);
    			filePAR.setINITIAL_TOUR_ALGORITHM(pSetting.getInitialTourAlgorithm());
    			filePAR.setBACKTRACKING(pSetting.getBacktracking());
    			if(filePAR.generateFile()) {
    				executeLKH();
    				if(!errorLKH) {
    					
    				
	    				//modificar
	    				RouteResult routeResult = new RouteResult();
	    				System.out.println("Haciendo testLectura");
	    				routeResult.testLectura();
	    				routeResult.testLecturaTours();
	    				ListTours = routeResult.getListTours();
	    				for(int i=0;i<routeResult.getListTours().size();i++) {
	    					routeResult.getListTours().get(i).setTrack(ejercicio.getTour(routeResult.getListTours().get(i).getCamino()));
	    					routeResult.getListTours().get(i).setColor(selectColorRand(i));
	    				}
	    				
	    				cbRouteSelector.removeAllItems();
	    				for(int i=0;i<routeResult.getListTours().size();i++) {
	    					cbRouteSelector.addItem(routeResult.getListTours().get(i));
	    				}
	    				cbRouteSelector.setSelectedIndex(-1);
	    				
	    				/*test*/
	    				for(int i=0;i<routeResult.getListTours().size();i++) {
	    					//System.out.println("Hace esto? routeResult.getListTours().size() "+routeResult.getListTours().size());
	    					for(int j=0;j<routeResult.getListTours().get(i).getTrack().size();j++) {
	    						//System.out.println("Hace esto? routeResult.getListTours().get(i).getTrack().size() "+routeResult.getListTours().get(i).getTrack().size());
	    						MapPolylineImpl mpl = new MapPolylineImpl(routeResult.getListTours().get(i).getTrack().get(j));
	    						mpl.setColor(routeResult.getListTours().get(i).getColor());
	    						map().addMapPolygon(mpl);
	    						
	    					}
	    				}
    				}
    				/*List<Integer> listResult = routeResult.getTour();
    				for(int i = 0; i<listResult.size(); i++) {
    					System.out.println(listResult.get(i));
    				}
    				List<MapPolygon> listPoligonResult = rawMatrixPoint.RouteList(listResult);
    				for(int i=0; i<listPoligonResult.size();i++) {
    					map().addMapPolygon(listPoligonResult.get(i));
    				}*/
    			}
    			else{
    				System.out.println("No se pudo generar el fichero de parametros");
    			}
    		}
    		else{
    			System.out.println("No se pudo generar el fichero de problemas");
    		}
    	}
    	else {
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
    
    private void GenerateRoute2() {
    	System.out.println("Test Generate route ok");
    	if(!map().mapMarkerList.isEmpty()){
    		rawMatrixPoint = new MatrixPoints(map().mapMarkerList);
    		System.out.println(rawMatrixPoint.getMatrixDistance());
    		System.out.println(rawMatrixPoint.getMatrixDuration());
    		
    		List<List<MapPolygon>> ruta = rawMatrixPoint.getAllRoutesMapPolygon();
    		for(int i=0; i< ruta.size(); i++) {
    			
    			
    			
    			List<MapPolygon> poly = ruta.get(i);
    			for(int j=0; j<poly.size(); j++) {
    				if(i!=j)
    					map().addMapPolygon(poly.get(j));
    			}
    		}
    		
    		//vamos a crear el fichero
    		ProblemFile fileTSP = new ProblemFile();
    		fileTSP.setDIMENSION(map().mapMarkerList.size());
    		fileTSP.setEDGE_WEIGHT_SECTION(rawMatrixPoint.getMatrixDistance());
    		fileTSP.setDISPLAY_DATA_SECTION(rawMatrixPoint.getCoordinates());
    		if(fileTSP.generateFile()) {
    			ParametersFile filePAR = new ParametersFile();
    			if(filePAR.generateFile()) {
    				executeLKH();
    				RouteResult routeResult = new RouteResult();
    				System.out.println("Haciendo testLectura");
    				routeResult.testLectura();
    				List<Integer> listResult = routeResult.getTour();
    				for(int i = 0; i<listResult.size(); i++) {
    					System.out.println(listResult.get(i));
    				}
    				List<MapPolygon> listPoligonResult = rawMatrixPoint.RouteList(listResult);
    				for(int i=0; i<listPoligonResult.size();i++) {
    					map().addMapPolygon(listPoligonResult.get(i));
    				}
    			}
    			else{
    				System.out.println("No se pudo generar el fichero de parametros");
    			}
    		}
    		else{
    			System.out.println("No se pudo generar el fichero de problemas");
    		}
    	}
    	else {
    		System.out.println("Fallo");
    	}
    	 
    }
    
    private void clearPoints() {
    	System.out.println("Test clear ok");
    	map().removeAllMapMarkers();
    	map().removeAllMapPolygons();
    	map().removeAll();
    	ejercicio.clear();
    	pGestionNR.clearList();
    	ListTours.clear();
        cbRouteSelector.removeAllItems();
    }
    
    private void newProyect() {
    	
    }
    
    void SaveProyect2(File fileToSave) throws IOException, JAXBException{
    	
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
		nodes.setNodes(myList);
		
		/*Ruta ruta = new Ruta();
		ruta.setDistance(20.1);
		ruta.setCoordinateFrom(new Coordinate(20, 30));*/
		
		FileOutputStream fos = new FileOutputStream(fileToSave);
		marshaller.marshal(nodes, fos);
		fos.close();
    	
    }
    
    void LoadProyect2(File fileToLoad) throws IOException, JAXBException{
    	
    	JAXBContext context2 = JAXBContext.newInstance(NodosList.class);
		//Marshaller marshaller = context.createMarshaller();
		//marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		//System.out.println("tamaño de ejercicio "+ejercicio.size());
		System.out.println("ruta del ejercicio "+fileToLoad.getAbsolutePath());

		
		Unmarshaller unmarshaller = context2.createUnmarshaller();
		var ejer = (NodosList) unmarshaller.unmarshal(fileToLoad);
    }
    
    void LoadProyect(File fileToLoad) throws IOException, JAXBException{
    	
    	JAXBContext context2 = JAXBContext.newInstance(NodosList.class);
		//Marshaller marshaller = context.createMarshaller();
		//marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		//System.out.println("tamaño de ejercicio "+ejercicio.size());
		System.out.println("ruta del ejercicio "+fileToLoad.getAbsolutePath());

		
		Unmarshaller unmarshaller = context2.createUnmarshaller();
		ejercicio = (NodosList) unmarshaller.unmarshal(fileToLoad);
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
    		System.out.println("vacio");
    		ejercicio.add(newNodo);
    		
    	}
    	else {
    		System.out.println("lleno");
    		    		
    		//listar los anteriores y crear las rutas pertinentes
    		
    		//listar de nuevo punto a nodos previos
    		for(int i = 0; i < ejercicio.size(); i++) {
    			ejercicio.get(i).getCoordinate();
    			Ruta newRuta = new Ruta(ejercicio.get(i).getCoordinate(),newNodo.getCoordinate());
    			ejercicio.get(i).addRuta(newRuta);
    		}
    		
    		//listar de nodos previos a nuevo punto
    		for(int i = 0; i < ejercicio.size(); i++) {
    			ejercicio.get(i).getCoordinate();
    			Ruta newRuta = new Ruta(newNodo.getCoordinate(), ejercicio.get(i).getCoordinate());
    			newNodo.addRuta(newRuta);
    		}
    		ejercicio.add(newNodo);
    		testRutas();
    	}
    }
    
    
    private void testExport() throws IOException, JAXBException{
    	
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
		//n2.setCoordinate(new Coordinate(20, 10));
		
		//n2.setListRoutes(routes);
		
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
			
			
		
    	
    	
    	/*JAXBContext context = JAXBContext.newInstance(Provincia.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		
		Provincia provincia = fillProvincia();
		
		//Mostramos el documento XML generado por la salida estandar
		marshaller.marshal(provincia, System.out);
		
		FileOutputStream fos = new FileOutputStream(PROVINCIA_DAT_FILE);
		//guardamos el objeto serializado en un documento XML
		marshaller.marshal(provincia, fos);
		fos.close();
		
		Unmarshaller unmarshaller = context.createUnmarshaller();
		//Deserealizamos a partir de un documento XML
		Provincia provincaAux = (Provincia) unmarshaller.unmarshal(new File(PROVINCIA_DAT_FILE));
		System.out.println("********* Provincia cargado desde fichero XML***************");
		//Mostramos por linea de comandos el objeto Java obtenido 
		//producto de la deserialziacion
		marshaller.marshal(provincaAux, System.out);*/
    }
    
    private void testExport2() {
    	
    	//System.out.println(map().mapMarkerList.get(2));
    	//System.out.println(map().mapMarkerList.get(3));
    	
    	
    	final double FROM_LAT = 36.9010208, FROM_LON = -116.7659466;
        final double TO_LAT = 36.9059371, TO_LON = -116.7618071;
        //Request ghRequest = new Request(
        //        FROM_LAT, FROM_LON,
        //        TO_LAT, TO_LON
        //);
        Route rt = new Route();
        
        //from 37.221539, -3.695808
        //to 36.744934, -3.515645
        //map().addMapMarker(new MapMarkerDot(37.221539,-3.695808));
        //map().addMapMarker(new MapMarkerDot(36.744934,-3.515645));
        
        //JSONObject routingWithProfile = rt.getFastestRoute("37.221539,-3.695808;36.744934,-3.515645", "car");
        JSONObject routingWithProfile = rt.getFastestRoute("-3.695808,37.221539;-3.515645,36.744934", "car");
        RouteJSON obj = new RouteJSON(routingWithProfile);
        //System.out.println(obj.getCode());
        
        	//MapPolygon poly = new MapPolygon(coordinates);
        	//MapPolygon poly = new MapPolylineImpl(coordinates);
        	map().addMapPolygon(obj.getPolyline());
        	
        	
        	
        	Runtime runtime = Runtime.getRuntime();
        	
        	
        	File archivo = null;
            FileReader fr = null;
            BufferedReader br = null;
            
        	try {
        		
				//Process pr = runtime.exec("cd ~/Documents/tfg/lkh-3/LKH-3.0.6/ | ./LKH test.par");
        		//String[] cmd = {"/bin/sh", "./Documents/tfg/lkh-3/LKH-3.0.6/LKH /Documents/tfg/lkh-3/LKH-3.0.6/test.par"};
        		//String[] cmd = {"/bin/sh", "cd ~/Documentos", "mkdir aaaa"};
        		String[] cmd = {"/bin/sh", "-c", "./LKH test.par"};
        		//String[] cmd = {"/bin/sh", "/Documents/tfg/lkh-3/LKH-3.0.6/", "./LKH test.par"};
        		//Runtime.getRuntime().exec(cmd);
        		String line;
        		//Process p = Runtime.getRuntime().exec("ping -c 3 google.com;");
        		Process p = Runtime.getRuntime().exec(cmd);
        		//System.out.println(p.getInputStream());
        		
        		BufferedReader bri = new BufferedReader
        		        (new InputStreamReader(p.getInputStream()));
        		BufferedReader bre = new BufferedReader
        		        (new InputStreamReader(p.getErrorStream()));
        		while ((line = bri.readLine()) != null) {
    				System.out.println(line);
        		}
        		bri.close();
        		while ((line = bre.readLine()) != null) {
    				System.out.println(line);
        		}
        		bre.close();
        		try {
					p.waitFor();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		
        		//Runtime.getRuntime().exec("./LKH test.par");
        		//Process pr = runtime.exec("ls");
				//pr.getInputStream();
			    //BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        		//System.out.println(input);
				
				
				
	            // Apertura del fichero y creacion de BufferedReader para poder
	            // hacer una lectura comoda (disponer del metodo readLine()).
				archivo = new File ("solucion.txt");
				fr = new FileReader (archivo);
				br = new BufferedReader(fr);
				
				// Lectura del fichero
				String linea;
				while((linea=br.readLine())!=null)
					System.out.println(linea);
				
				
				
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
    
    void executeLKH() {
    	Runtime runtime = Runtime.getRuntime();
    	
    	
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
    		//System.out.println(p.getInputStream());
    		
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
    				//System.out.println("Salta el error");
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
    		
    		//Runtime.getRuntime().exec("./LKH test.par");
    		//Process pr = runtime.exec("ls");
			//pr.getInputStream();
		    //BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
    		//System.out.println(input);
			
			
			
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
			//archivo = new File ("solucionTest.txt");
			//fr = new FileReader (archivo);
			//br = new BufferedReader(fr);
			
			// Lectura del fichero
			//String linea;
			//while((linea=br.readLine())!=null)
			//	System.out.println(linea);
			
			
			
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
    
    void nuevasRutas() {
    	
    }
    
    void drawPointInMap() {
    	//ejercicio
    	
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
    
    

    @Override
    public void processCommand(JMVCommandEvent command) {
        if (command.getCommand().equals(JMVCommandEvent.COMMAND.ZOOM) ||
                command.getCommand().equals(JMVCommandEvent.COMMAND.MOVE)) {
        	
            updateZoomParameters();
        }
    }
}