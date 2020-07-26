package tools.and.utilities;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.BoxLayout;
import net.miginfocom.swing.MigLayout;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;

public class SettingLKH extends JPanel {
	private JTextField textField;
	private JTextField textField1;
	private JTextField textField2;
	private JTextField textField3;
	private JTextField textField4;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_5;
	private JTextField textField_6;

	/**
	 * Create the panel.
	 */
	public SettingLKH() {
		setLayout(null);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("");
		chckbxNewCheckBox.setBounds(20, 65, 28, 23);
		add(chckbxNewCheckBox);
		
		JLabel lblNewLabel = new JLabel("MTSP Min size");
		lblNewLabel.setBounds(53, 69, 104, 16);
		add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(174, 62, 130, 26);
		add(textField);
		textField.setColumns(10);
		
		JCheckBox chckbxNewCheckBox1 = new JCheckBox("");
		chckbxNewCheckBox1.setBounds(20, 98, 28, 23);
		add(chckbxNewCheckBox1);
		
		JLabel lblNewLabel1 = new JLabel("MTSP Max size");
		lblNewLabel1.setBounds(53, 102, 104, 16);
		add(lblNewLabel1);
		
		textField1 = new JTextField();
		textField1.setBounds(174, 95, 130, 26);
		add(textField1);
		textField1.setColumns(10);
		
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

		JCheckBox chckbxNewCheckBox3 = new JCheckBox("");
		chckbxNewCheckBox3.setBounds(20, 157, 28, 23);
		add(chckbxNewCheckBox3);
		
		JLabel lblNewLabel3 = new JLabel("PATCHING_A");
		lblNewLabel3.setBounds(53, 161, 104, 16);
		add(lblNewLabel3);
		
		textField3 = new JTextField();
		textField3.setBounds(174, 154, 130, 26);
		add(textField3);
		textField3.setColumns(10);
		
		JCheckBox chckbxNewCheckBox4 = new JCheckBox("");
		chckbxNewCheckBox4.setBounds(20, 188, 28, 23);
		add(chckbxNewCheckBox4);
		
		JLabel lblNewLabel4 = new JLabel("PATCHING_C");
		lblNewLabel4.setBounds(53, 192, 104, 16);
		add(lblNewLabel4);
		
		textField4 = new JTextField();
		textField4.setBounds(174, 185, 130, 26);
		add(textField4);
		textField4.setColumns(10);
		
		JCheckBox chckbxNewCheckBox5 = new JCheckBox("");
		chckbxNewCheckBox5.setBounds(316, 30, 28, 23);
		add(chckbxNewCheckBox5);
		
		JLabel lblNewLabel5 = new JLabel("Backtracking");
		lblNewLabel5.setBounds(349, 34, 87, 16);
		add(lblNewLabel5);
		
		JLabel lblNewLabel_1 = new JLabel("Tipo de problema");
		lblNewLabel_1.setBounds(20, 6, 149, 16);
		add(lblNewLabel_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"TSP", "MTSP", "CVRP"}));
		comboBox.setBounds(174, 2, 123, 27);
		add(comboBox);
		
		JLabel lblNewLabel_2 = new JLabel("N. de vehiculos");
		lblNewLabel_2.setBounds(20, 34, 149, 16);
		add(lblNewLabel_2);
		
		SpinnerModel sm = new SpinnerNumberModel(1, 1, null, 1);
		JSpinner spinner = new JSpinner(sm);
		spinner.setValue(1);
		
		spinner.setBounds(174, 29, 123, 26);
		add(spinner);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"RESTRICTED", "EXTENDED"}));
		comboBox_1.setBounds(316, 153, 132, 27);
		add(comboBox_1);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"RESTRICTED", "EXTENDED"}));
		comboBox_2.setBounds(316, 184, 132, 27);
		add(comboBox_2);
		
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
		
		JComboBox comboBox_1_1 = new JComboBox();
		comboBox_1_1.setModel(new DefaultComboBoxModel(new String[] {"YES", "NO"}));
		comboBox_1_1.setBounds(448, 30, 132, 27);
		add(comboBox_1_1);
		
		JSpinner spinner_1 = new JSpinner(sm);
		spinner_1.setBounds(448, 65, 123, 26);
		add(spinner_1);
	}
}
