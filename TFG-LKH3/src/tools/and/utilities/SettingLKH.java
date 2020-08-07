package tools.and.utilities;

import javax.swing.JPanel;
import java.awt.GridLayout;
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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.BoxLayout;
import net.miginfocom.swing.MigLayout;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.InputVerifier;
import javax.swing.JSpinner;

public class SettingLKH extends JPanel {
	private JFormattedTextField tfMTSPmin;
	private JFormattedTextField tfMTSPmax;
	private JTextField textField2;
	private JFormattedTextField tfPatchingA;
	private JFormattedTextField tfPatchingC;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_5;
	private JTextField textField_6;
	
	private JSpinner spVehicles;
	private JSpinner spTruck;
	
	private JCheckBox chckbxMTSPmin;
	private JCheckBox chckbxMTSPmax;
	
	private JCheckBox chckbxPatchingA;
	private JCheckBox chckbxPatchingC;
	
	private JComboBox cbPatchingA;
	private JComboBox cbPatchingC;
	private JComboBox cbBacktracking;
	private JComboBox cbTypeProblem;
	private JComboBox cbAlgoritmo;

	/**
	 * Create the panel.
	 * @throws ParseException 
	 */
	public SettingLKH() throws ParseException {
		setLayout(null);
		
		NumberFormat format = NumberFormat.getInstance();
	    NumberFormatter formatter = new NumberFormatter(format);
	    formatter.setValueClass(Integer.class);
	    formatter.setMinimum(0); //valor mínimo
	    formatter.setMaximum(Integer.MAX_VALUE); //valor máximo
	    formatter.setAllowsInvalid(false);
				

	    chckbxMTSPmin = new JCheckBox("");
		chckbxMTSPmin.setBounds(20, 65, 28, 23);
		add(chckbxMTSPmin);
		
		JLabel lblNewLabel = new JLabel("MTSP Min size");
		lblNewLabel.setBounds(53, 69, 104, 16);
		add(lblNewLabel);
		
		tfMTSPmin = new JFormattedTextField(formatter);
		tfMTSPmin.setBounds(174, 62, 130, 26);
		add(tfMTSPmin);
		tfMTSPmin.setColumns(10);
		
		chckbxMTSPmax = new JCheckBox("");
		chckbxMTSPmax.setBounds(20, 98, 28, 23);
		add(chckbxMTSPmax);
		
		JLabel lblNewLabel1 = new JLabel("MTSP Max size");
		lblNewLabel1.setBounds(53, 102, 104, 16);
		add(lblNewLabel1);
		
		tfMTSPmax = new JFormattedTextField(formatter);
		tfMTSPmax.setBounds(174, 95, 130, 26);
		add(tfMTSPmax);
		tfMTSPmax.setColumns(10);
		
		JCheckBox chckbxNewCheckBox2 = new JCheckBox("");
		chckbxNewCheckBox2.setBounds(20, 130, 28, 23);
		add(chckbxNewCheckBox2);
		
		JLabel lblNewLabel2 = new JLabel("New label");
		lblNewLabel2.setBounds(53, 134, 104, 16);
		add(lblNewLabel2);
		
		textField2 = new JTextField();
		textField2.setBounds(174, 127, 130, 26);
		add(textField2);
		textField2.setColumns(10);

		chckbxPatchingA = new JCheckBox("");
		chckbxPatchingA.setBounds(20, 157, 28, 23);
		add(chckbxPatchingA);
		
		JLabel lblNewLabel3 = new JLabel("PATCHING_A");
		lblNewLabel3.setBounds(53, 161, 104, 16);
		add(lblNewLabel3);
		
		tfPatchingA = new JFormattedTextField(formatter);
		tfPatchingA.setBounds(174, 154, 130, 26);
		add(tfPatchingA);
		tfPatchingA.setColumns(10);
		
		chckbxPatchingC = new JCheckBox("");
		chckbxPatchingC.setBounds(20, 188, 28, 23);
		add(chckbxPatchingC);
		
		JLabel lblNewLabel4 = new JLabel("PATCHING_C");
		lblNewLabel4.setBounds(53, 192, 104, 16);
		add(lblNewLabel4);
		
		tfPatchingC = new JFormattedTextField(formatter);
		tfPatchingC.setBounds(174, 185, 130, 26);
		add(tfPatchingC);
		tfPatchingC.setColumns(10);
		
		JLabel lblBacktracking = new JLabel("Backtracking");
		lblBacktracking.setBounds(349, 34, 87, 16);
		add(lblBacktracking);
		
		JLabel lblNewLabel_1 = new JLabel("Tipo de problema");
		lblNewLabel_1.setBounds(20, 6, 149, 16);
		add(lblNewLabel_1);
		
		cbTypeProblem = new JComboBox();
		cbTypeProblem.setModel(new DefaultComboBoxModel(new String[] {"TSP", "MTSP", "CVRP"}));
		cbTypeProblem.setSelectedIndex(2);
		cbTypeProblem.setBounds(174, 2, 123, 27);
		add(cbTypeProblem);
		
		JLabel lblNewLabel_2 = new JLabel("N. de vehiculos");
		lblNewLabel_2.setBounds(20, 34, 149, 16);
		add(lblNewLabel_2);
		
		SpinnerModel sm = new SpinnerNumberModel(1, 1, null, 1);
		spVehicles = new JSpinner(sm);
		spVehicles.setValue(1);
		
		spVehicles.setBounds(174, 29, 123, 26);
		add(spVehicles);
		
		cbPatchingA = new JComboBox();
		cbPatchingA.setModel(new DefaultComboBoxModel(new String[] {"RESTRICTED", "EXTENDED"}));
		cbPatchingA.setBounds(316, 153, 132, 27);
		add(cbPatchingA);
		
		cbPatchingC = new JComboBox();
		cbPatchingC.setModel(new DefaultComboBoxModel(new String[] {"RESTRICTED", "EXTENDED"}));
		cbPatchingC.setBounds(316, 184, 132, 27);
		add(cbPatchingC);
		
		JCheckBox chckbxNewCheckBox5_1 = new JCheckBox("");
		chckbxNewCheckBox5_1.setBounds(20, 224, 28, 23);
		add(chckbxNewCheckBox5_1);
		
		JLabel lblNewLabel5_1 = new JLabel("New label");
		lblNewLabel5_1.setBounds(53, 228, 61, 16);
		add(lblNewLabel5_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(119, 223, 130, 26);
		add(textField_1);
		
		JCheckBox chckbxNewCheckBox5_2 = new JCheckBox("");
		chckbxNewCheckBox5_2.setBounds(20, 251, 28, 23);
		add(chckbxNewCheckBox5_2);
		
		JLabel lblNewLabel5_2 = new JLabel("New label");
		lblNewLabel5_2.setBounds(53, 255, 61, 16);
		add(lblNewLabel5_2);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(119, 250, 130, 26);
		add(textField_2);
		
		JCheckBox chckbxNewCheckBox5_3 = new JCheckBox("");
		chckbxNewCheckBox5_3.setBounds(20, 278, 28, 23);
		add(chckbxNewCheckBox5_3);
		
		JLabel lblNewLabel5_3 = new JLabel("New label");
		lblNewLabel5_3.setBounds(53, 282, 61, 16);
		add(lblNewLabel5_3);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(119, 277, 130, 26);
		add(textField_3);
		
		JCheckBox chckbxNewCheckBox5_4 = new JCheckBox("");
		chckbxNewCheckBox5_4.setBounds(261, 224, 28, 23);
		add(chckbxNewCheckBox5_4);
		
		JLabel lblCapacitytruck = new JLabel("Capacity (truck)");
		lblCapacitytruck.setBounds(349, 70, 109, 16);
		add(lblCapacitytruck);
		
		JCheckBox chckbxNewCheckBox5_5 = new JCheckBox("");
		chckbxNewCheckBox5_5.setBounds(261, 252, 28, 23);
		add(chckbxNewCheckBox5_5);
		
		JLabel lblNewLabel5_5 = new JLabel("New label");
		lblNewLabel5_5.setBounds(294, 256, 61, 16);
		add(lblNewLabel5_5);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(360, 251, 130, 26);
		add(textField_5);
		
		JCheckBox chckbxNewCheckBox5_6 = new JCheckBox("");
		chckbxNewCheckBox5_6.setBounds(261, 279, 28, 23);
		add(chckbxNewCheckBox5_6);
		
		JLabel lblNewLabel5_6 = new JLabel("New label");
		lblNewLabel5_6.setBounds(294, 283, 61, 16);
		add(lblNewLabel5_6);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(360, 278, 130, 26);
		add(textField_6);
		
		cbBacktracking = new JComboBox();
		cbBacktracking.setModel(new DefaultComboBoxModel(new String[] {"YES", "NO"}));
		cbBacktracking.setSelectedIndex(1);
		cbBacktracking.setBounds(530, 30, 128, 27);
		add(cbBacktracking);
		
		SpinnerModel sm2 = new SpinnerNumberModel(1, 1, null, 1);
		spTruck = new JSpinner(sm2);
		spTruck.setBounds(530, 65, 123, 26);
		add(spTruck);
		
		JLabel lblAlgoritmo = new JLabel("Tour Algorithm");
		lblAlgoritmo.setBounds(349, 6, 99, 16);
		add(lblAlgoritmo);
		
		cbAlgoritmo = new JComboBox();
		cbAlgoritmo.setModel(new DefaultComboBoxModel(new String[] {"BORUVKA", "CVRP", "GREEDY", "MOORE", "MTSP", "NEAREST-NEIGHBOR", "QUICK-BORUVKA", "SIERPINSKI", "WALK"}));
		cbAlgoritmo.setSelectedIndex(1);
		cbAlgoritmo.setBounds(448, 2, 210, 27);
		add(cbAlgoritmo);
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
		return (int) tfMTSPmin.getValue();
	}
	
	public boolean getBoolMTSPmax() {
		return chckbxMTSPmax.isSelected();
	}
	
	public int getMTSPmax() {
		return (int) tfMTSPmax.getValue();
	}
	
	public boolean getBoolPatchingA() {
		return chckbxPatchingA.isSelected();
	}
	
	public int getPatchingA() {
		return (int) tfPatchingA.getValue();
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
}


