package tools.and.utilities;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.DefaultListModel;
import javax.swing.JList;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JRadioButton;
import java.awt.Component;

public class GestionNodesRoutes extends JPanel {

	/**
	 * Create the panel.
	 */
	private JList<Nodo> list;
	DefaultListModel<Nodo> nodoModel;
	
	
	private JTextField tfName;
	private JFormattedTextField tfDemand;
	private JTextField tfLatitude;
	private JTextField tfLongitude;
	public JButton btModify;
	public JButton btDelete;
	
	private JTable tableRoute;
	private JTable tableRouteSol;
	public JScrollPane spRutas;
	public JScrollPane spSoluciones;
	
	public GestionNodesRoutes() {
		setLayout(null);
		nodoModel = new DefaultListModel<>();
		
		list = new JList<>();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(new ListSelectionListener() {
			@Override
		    public void valueChanged(ListSelectionEvent e) {
		        //System.out.println("Hello you selected me!  "
		        //    + list.getSelectedValue());
				tfName.setEditable(true);
				tfDemand.setEditable(true);
				fillNodo(list.getSelectedValue());
				
		    }
		});
		list.setBounds(65, 96, 256, 258);
		JScrollPane listScroller = new JScrollPane(list);
	    //listScroller.setViewportView(list);
		listScroller.setBounds(65, 96, 256, 258);
		//add(new JScrollPane(list));
		add(listScroller);
		
		
		
		
		NumberFormat format = NumberFormat.getInstance();
	    NumberFormatter formatter = new NumberFormatter(format);
	    formatter.setValueClass(Integer.class);
	    formatter.setMinimum(0); //valor mínimo
	    formatter.setMaximum(Integer.MAX_VALUE); //valor máximo
	    formatter.setAllowsInvalid(false);
		
		btModify = new JButton("Modify");
		btModify.setEnabled(false);
		btModify.setBounds(362, 325, 117, 29);
		add(btModify);
		
		btDelete = new JButton("Delete");
		btDelete.setEnabled(false);
		btDelete.setBounds(560, 325, 117, 29);
		add(btDelete);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(362, 101, 61, 16);
		add(lblNewLabel);
		
		tfName = new JTextField();
		tfName.setColumns(10);
		tfName.setBounds(445, 96, 266, 26);
		tfName.setEditable(false);
		add(tfName);
		
		JLabel lblNewLabel_1 = new JLabel("Demand");
		lblNewLabel_1.setBounds(362, 145, 61, 16);
		add(lblNewLabel_1);
		
		tfDemand = new JFormattedTextField(formatter);
		tfDemand.setColumns(10);
		tfDemand.setBounds(445, 140, 266, 26);
		tfDemand.setEditable(false);
		add(tfDemand);
		
		JLabel lblNewLabel_2 = new JLabel("Latitude");
		lblNewLabel_2.setBounds(362, 184, 61, 16);
		add(lblNewLabel_2);
		
		tfLatitude = new JTextField();
		tfLatitude.setColumns(10);
		tfLatitude.setBounds(445, 178, 266, 26);
		tfLatitude.setEditable(false);
		add(tfLatitude);
		
		JLabel lblNewLabel_2_1 = new JLabel("Longitude");
		lblNewLabel_2_1.setBounds(362, 222, 71, 16);
		add(lblNewLabel_2_1);
		
		tfLongitude = new JTextField();
		tfLongitude.setColumns(10);
		tfLongitude.setBounds(445, 217, 266, 26);
		tfLongitude.setEditable(false);
		add(tfLongitude);
		tableRoute = new JTable();
		//JScrollPane scrollPane = new JScrollPane(tableRoute);
		spRutas = new JScrollPane(tableRoute);
		spRutas.setBounds(65, 413, 712, 151);
		add(spRutas);
		
		JLabel lblNewLabel_3 = new JLabel("Nodes list");
		lblNewLabel_3.setBounds(65, 68, 90, 16);
		add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("All the routes of the selected node");
		lblNewLabel_4.setBounds(65, 385, 712, 16);
		add(lblNewLabel_4);
		
		
		
		
		
		//JScrollPane spSoluciones = new JScrollPane((Component) null);
		//tableRouteSol = new JTable();
		//spSoluciones = new JScrollPane(tableRouteSol);
		//spSoluciones.setBounds(65, 604, 712, 151);
		//add(spSoluciones);
		


	}
	
	public void listUpdate(NodosList ejercicio) {
		nodoModel.clear();
		nodoModel.addAll(ejercicio.getNodes());
		list.setModel(nodoModel);
		
	}
	
	public void clearList() {
		nodoModel.removeAllElements();
		list.setModel(nodoModel);
		//tableRoute.removeAll();
		tableRoute.setModel(new DefaultTableModel());
		//tableRouteSol.setModel(new DefaultTableModel());
		clearDisplay();
	}
	
	private void fillNodo(Nodo value) {
		if(value != null) {
			tfName.setText(value.getName());
			tfDemand.setText(String.valueOf(value.getDemand()));
			tfLatitude.setText(String.valueOf(value.getCoordinate().getLat()));
			tfLongitude.setText(String.valueOf(value.getCoordinate().getLon()));
			btModify.setEnabled(true);
			btDelete.setEnabled(true);
			
			tableRoute.setModel(value.GetTableRoutesNodo());
			//tableRoute.setModel(value.get);
		}
	}
	
	public void clearDisplay() {
		tfName.setText("");
		tfDemand.setText("0");
		tfLatitude.setText("");
		tfLongitude.setText("");
		tfName.setEditable(false);
		tfDemand.setEditable(false);
		btModify.setEnabled(false);
		btDelete.setEnabled(false);
		
		//tableRoute.removeAll();
	}
	
	public Nodo getItemSelection() {
		return list.getSelectedValue();
	}
	
	public int getItemIndexSelection() {
		return list.getSelectedIndex();
	}
	
	
	
	public String getName() {
		return tfName.getText();
	}
	public int getDemand() {
		return Integer.parseInt(tfDemand.getText());
	}
}