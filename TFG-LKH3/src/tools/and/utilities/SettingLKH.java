package tools.and.utilities;

import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.BoxLayout;
//import net.miginfocom.swing.MigLayout;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.InputVerifier;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class SettingLKH extends JPanel {
	private JFormattedTextField tfMTSPmin;
	private JFormattedTextField tfMTSPmax;
	private JFormattedTextField tfRuns;
	private JFormattedTextField tfPatchingA;
	private JFormattedTextField tfPatchingC;
	
	private JSpinner spVehicles;
	private JSpinner spTruck;
	
	private JCheckBox chckbxMTSPmin;
	private JCheckBox chckbxMTSPmax;
	private JCheckBox chckbxRuns;
	private JCheckBox chckbxPatchingA;
	private JCheckBox chckbxPatchingC;
	
	private JComboBox cbPatchingA;
	private JComboBox cbPatchingC;
	private JComboBox cbBacktracking;
	private JComboBox cbTypeProblem;
	private JComboBox cbAlgoritmo;
	private JComboBox cbDistanceDuration;
	
	public JButton btnGenerateRoutes;
	
	private int maxMTSPSize;

	/**
	 * Create the panel.
	 * @throws ParseException 
	 */
	public SettingLKH() throws ParseException {
		setLayout(null);
		
		maxMTSPSize = 0;
		
		NumberFormat format = NumberFormat.getInstance();
	    NumberFormatter formatter = new NumberFormatter(format);
	    formatter.setValueClass(Integer.class);
	    formatter.setMinimum(0); //valor mínimo
	    formatter.setMaximum(Integer.MAX_VALUE); //valor máximo
	    formatter.setAllowsInvalid(false);
				

	    chckbxMTSPmin = new JCheckBox("");
		chckbxMTSPmin.setBounds(20, 226, 28, 23);
		chckbxMTSPmin.setVisible(false);
		add(chckbxMTSPmin);
		
		JLabel lblNewLabel = new JLabel("Min number of nodes to visit");
		lblNewLabel.setBounds(20, 66, 221, 16);
		lblNewLabel.setVisible(true);
		add(lblNewLabel);
		
		tfMTSPmin = new JFormattedTextField(formatter);
		tfMTSPmin.setText("1");
		tfMTSPmin.setBounds(253, 61, 104, 26);
		tfMTSPmin.setVisible(true);
		add(tfMTSPmin);
		tfMTSPmin.setColumns(10);
		
		chckbxMTSPmax = new JCheckBox("");
		chckbxMTSPmax.setBounds(20, 259, 28, 23);
		chckbxMTSPmax.setVisible(false);
		add(chckbxMTSPmax);
		
		JLabel lblNewLabel1 = new JLabel("Max number of nodes to visit");
		lblNewLabel1.setBounds(20, 100, 221, 16);
		lblNewLabel1.setVisible(true);
		add(lblNewLabel1);
		
		tfMTSPmax = new JFormattedTextField(formatter);
		//tfMTSPmax.setText("0");
		tfMTSPmax.setBounds(253, 95, 104, 26);
		tfMTSPmax.setVisible(true);
		add(tfMTSPmax);
		tfMTSPmax.setColumns(10);
		
		chckbxRuns = new JCheckBox("");
		chckbxRuns.setBounds(60, 259, 28, 23);
		chckbxRuns.setVisible(false);
		add(chckbxRuns);
		
		JLabel lbRuns = new JLabel("Runs (default:10)");
		lbRuns.setBounds(20, 132, 116, 16);
		lbRuns.setVisible(true);
		add(lbRuns);
		
		tfRuns = new JFormattedTextField(formatter);
		tfRuns.setText("10");
		tfRuns.setBounds(227, 127, 130, 26);
		tfRuns.setVisible(true);
		add(tfRuns);
		tfRuns.setColumns(10);

		chckbxPatchingA = new JCheckBox("");
		chckbxPatchingA.setBounds(20, 157, 28, 23);
		chckbxPatchingA.setVisible(false);
		add(chckbxPatchingA);
		
		JLabel lblNewLabel3 = new JLabel("PATCHING_A");
		lblNewLabel3.setBounds(53, 161, 104, 16);
		lblNewLabel3.setVisible(false);
		add(lblNewLabel3);
		
		tfPatchingA = new JFormattedTextField(formatter);
		tfPatchingA.setText("0");
		tfPatchingA.setBounds(174, 154, 130, 26);
		tfPatchingA.setVisible(false);
		add(tfPatchingA);
		tfPatchingA.setColumns(10);
		
		chckbxPatchingC = new JCheckBox("");
		chckbxPatchingC.setBounds(20, 188, 28, 23);
		chckbxPatchingC.setVisible(false);
		add(chckbxPatchingC);
		
		JLabel lblNewLabel4 = new JLabel("PATCHING_C");
		lblNewLabel4.setBounds(53, 192, 104, 16);
		lblNewLabel4.setVisible(false);
		add(lblNewLabel4);
		
		tfPatchingC = new JFormattedTextField(formatter);
		tfPatchingC.setText("0");
		tfPatchingC.setBounds(174, 185, 130, 26);
		tfPatchingC.setVisible(false);
		add(tfPatchingC);
		tfPatchingC.setColumns(10);
		
		JLabel lblBacktracking = new JLabel("Backtracking");
		lblBacktracking.setBounds(412, 34, 87, 16);
		lblBacktracking.setVisible(false);
		add(lblBacktracking);
		
		JLabel lblNewLabel_1 = new JLabel("Type problem");
		lblNewLabel_1.setBounds(20, 6, 149, 16);
		add(lblNewLabel_1);
		
		cbTypeProblem = new JComboBox();
		cbTypeProblem.setModel(new DefaultComboBoxModel(new String[] {"ATSP", "CVRP"}));
		//cbTypeProblem.setModel(new DefaultComboBoxModel(new String[] {"ACVRP","BWTSP","CCVRP","CTSP","CVRP","CVRPTW","DCVRP","1-PDTSP","m-PDTSP","m1-PDTSP","MLP","MTRP","MTRPD","mTSP","OCMTSP","OVRP","PDPTW","PDTSP","PDTSPF","PDTSPL","RCTVRP","RCTVRPTW","SOP","STTSP","TRP","TSPDL","TSPPD","TSPTW","VRPB","VRPBTW","VRPMPD","VRPMPDTW","VRPSPD","VRPSPDTW"}));
		cbTypeProblem.setSelectedIndex(1);
		cbTypeProblem.setBounds(260, 2, 97, 27);
		cbTypeProblem.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	EnableOptions();
		    }
		});
		add(cbTypeProblem);
		
		JLabel lblNewLabel_2 = new JLabel("Number of vehicles");
		lblNewLabel_2.setBounds(20, 34, 149, 16);
		add(lblNewLabel_2);
		
		SpinnerModel sm = new SpinnerNumberModel(1, 1, null, 1);
		spVehicles = new JSpinner(sm);
		spVehicles.setValue(1);
		
		spVehicles.setBounds(260, 29, 97, 26);
		add(spVehicles);
		
		cbPatchingA = new JComboBox();
		cbPatchingA.setModel(new DefaultComboBoxModel(new String[] {"RESTRICTED", "EXTENDED"}));
		cbPatchingA.setBounds(450, 157, 132, 27);
		cbPatchingA.setVisible(false);
		add(cbPatchingA);
		
		cbPatchingC = new JComboBox();
		cbPatchingC.setModel(new DefaultComboBoxModel(new String[] {"RESTRICTED", "EXTENDED"}));
		cbPatchingC.setBounds(450, 188, 132, 27);
		cbPatchingC.setVisible(false);
		add(cbPatchingC);
		
		JLabel lblCapacitytruck = new JLabel("Capacity (truck)");
		lblCapacitytruck.setBounds(412, 100, 109, 16);
		add(lblCapacitytruck);
		
		cbBacktracking = new JComboBox();
		cbBacktracking.setModel(new DefaultComboBoxModel(new String[] {"YES", "NO"}));
		cbBacktracking.setSelectedIndex(1);
		cbBacktracking.setBounds(606, 30, 128, 27);
		cbBacktracking.setVisible(false);
		add(cbBacktracking);
		
		SpinnerModel sm2 = new SpinnerNumberModel(1, 1, null, 1);
		spTruck = new JSpinner(sm2);
		spTruck.setBounds(611, 95, 123, 26);
		add(spTruck);
		
		JLabel lblAlgoritmo = new JLabel("Initial algorithm");
		lblAlgoritmo.setBounds(412, 6, 109, 16);
		add(lblAlgoritmo);
		
		cbAlgoritmo = new JComboBox();
		//cbAlgoritmo.setModel(new DefaultComboBoxModel(new String[] {"ALL","BORUVKA", "CVRP", "GREEDY", "MOORE", "MTSP", "NEAREST-NEIGHBOR", "QUICK-BORUVKA", "SIERPINSKI", "WALK"}));
		cbAlgoritmo.setModel(new DefaultComboBoxModel(new String[] {"ALL","BORUVKA", "GREEDY", "MOORE", "MTSP", "NEAREST-NEIGHBOR", "WALK"}));
		cbAlgoritmo.setSelectedIndex(1);
		cbAlgoritmo.setBounds(533, 2, 201, 27);
		add(cbAlgoritmo);
		
		JLabel lblDistanceOrDuration = new JLabel("DIstance or Duration");
		lblDistanceOrDuration.setBounds(412, 66, 179, 16);
		add(lblDistanceOrDuration);
		
		cbDistanceDuration = new JComboBox();
		cbDistanceDuration.setModel(new DefaultComboBoxModel(new String[] {"DISTANCE", "DURATION"}));
		cbDistanceDuration.setSelectedIndex(0);
		cbDistanceDuration.setBounds(606, 62, 128, 27);
		add(cbDistanceDuration);
		
		btnGenerateRoutes = new JButton("Generate Route");
		btnGenerateRoutes.setBounds(606, 127, 128, 29);
		add(btnGenerateRoutes);
		
		JButton btLoad = new JButton("Load");
		btLoad.setBounds(316, 220, 117, 29);
		btLoad.setVisible(false);
		add(btLoad);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.setBounds(316, 253, 117, 29);
		btnNewButton.setVisible(false);
		add(btnNewButton);
	}
	
	public int getNumberVehicle() {
		return Integer.parseInt(spVehicles.getValue().toString());
	}
	
	public int getCapacityTruck() {
		return Integer.parseInt(spTruck.getValue().toString());
	}
	
	public boolean getBoolMTSPmin() {
		return chckbxMTSPmin.isSelected();
	}
	
	public int getMTSPmin() {
		return Integer.parseInt(tfMTSPmin.getText().replaceAll(",", ""));
	}
	
	public boolean getBoolMTSPmax() {
		return chckbxMTSPmax.isSelected();
	}
	
	public int getMTSPmax() {
		return Integer.parseInt(tfMTSPmax.getText().replaceAll(",", ""));
	}
	
	public boolean getBoolPatchingA() {
		return chckbxPatchingA.isSelected();
	}
	
	public int getPatchingA() {
		return (int) tfPatchingA.getValue();
	}
	
	public boolean getBoolRuns() {
		return chckbxRuns.isSelected();
	}
	
	public int getRuns() {
		return Integer.parseInt(tfRuns.getText().replaceAll(",", ""));
	}
	
	public String getPatchingAResExt() {
		return cbPatchingA.getSelectedItem().toString();
	}
	
	public boolean getBoolPatchingC() {
		return chckbxPatchingC.isSelected();
	}
	
	public int getPatchingC() {
		return (int) tfPatchingC.getValue();
	}
	
	public String getPatchingCResExt() {
		return cbPatchingC.getSelectedItem().toString();
	}
	
	public String getBacktracking() {
		return cbBacktracking.getSelectedItem().toString();
	}
	//cbTypeProblem
	public String getTypeProblem() {
		return cbTypeProblem.getSelectedItem().toString();
	}
	
	public String getInitialTourAlgorithm() {
		return cbAlgoritmo.getSelectedItem().toString();
	}
	
	public String getInitialDistanceOrDuration() {
		return cbDistanceDuration.getSelectedItem().toString();
	}
	
	public int getMaxMTSPSize() {
		return maxMTSPSize;
	}
	
	private void EnableOptions() {
		if(cbTypeProblem.getSelectedItem().toString().equals("CVRP")) {
			tfMTSPmin.setEnabled(true);
			tfMTSPmax.setEnabled(true);
			//textField2.setEnabled(false);
			tfPatchingA.setEnabled(true);
			tfPatchingC.setEnabled(true);
			spVehicles.setEnabled(true);
			tfRuns.setEnabled(true);
			
			//cbAlgoritmo.setEnabled(true);
			spTruck.setEnabled(true);
			cbPatchingA.setEnabled(true);
			cbPatchingC.setEnabled(true);
			//spVehicles.setEnabled(true);
		}
		else if(cbTypeProblem.getSelectedItem().toString().equals("ATSP")||cbTypeProblem.getSelectedItem().toString().equals("TSP")) {
			tfMTSPmin.setEnabled(false);
			tfMTSPmax.setEnabled(false);
			//textField2.setEnabled(false);
			tfPatchingA.setEnabled(false);
			tfPatchingC.setEnabled(false);
			spVehicles.setEnabled(false);
			tfRuns.setEnabled(false);
			
			//cbAlgoritmo.setEnabled(false);
			spTruck.setEnabled(false);
			cbPatchingA.setEnabled(false);
			cbPatchingC.setEnabled(false);
			//spVehicles.setEnabled(false);
		}
	}
	
	public void updateMaxMTSPSize(int value) {
		maxMTSPSize = value;
		tfMTSPmax.setText(Integer.toString(maxMTSPSize));
		
	}
}


