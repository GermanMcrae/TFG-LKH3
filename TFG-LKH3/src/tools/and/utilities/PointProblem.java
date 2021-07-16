package tools.and.utilities;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PointProblem extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfName;
	private JTextField tfDemand;
	private JTextField tfLatitude;
	private JTextField tfLongitude;
	
	public JButton okButton;
	public JButton cancelButton;

	private boolean validate;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			PointProblem dialog = new PointProblem();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public PointProblem(JFrame jf) {
		super(jf,"Add Point");
		validate = false;
		setBounds(100, 100, 420, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(45, 26, 61, 16);
		contentPanel.add(lblNewLabel);
		
		tfName = new JTextField();
		tfName.setBounds(128, 21, 266, 26);
		contentPanel.add(tfName);
		tfName.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Demand");
		lblNewLabel_1.setBounds(45, 70, 61, 16);
		contentPanel.add(lblNewLabel_1);
		
		tfDemand = new JTextField("0");
		tfDemand.setBounds(128, 65, 266, 26);
		contentPanel.add(tfDemand);
		tfDemand.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Latitude");
		lblNewLabel_2.setBounds(45, 109, 61, 16);
		contentPanel.add(lblNewLabel_2);
		
		tfLatitude = new JTextField();
		tfLatitude.setBounds(128, 103, 266, 26);
		contentPanel.add(tfLatitude);
		tfLatitude.setColumns(10);
		//String.valueOf(lon)
		tfLongitude = new JTextField();
		tfLongitude.setColumns(10);
		tfLongitude.setBounds(128, 142, 266, 26);
		contentPanel.add(tfLongitude);
		
		okButton = new JButton("OK");
		cancelButton = new JButton("Cancel");
		
		JLabel lblNewLabel_2_1 = new JLabel("Longitude");
		lblNewLabel_2_1.setBounds(45, 147, 71, 16);
		contentPanel.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_3 = new JLabel("Note: Capacity will only apply for the cvrp problem");
		lblNewLabel_2_3.setBounds(45, 187, 320, 16);
		contentPanel.add(lblNewLabel_2_3);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				
				/*okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});*/
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				validate = true;
				
			}
			{
				
				/*cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});*/
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				validate = false;
				//dispose();
			}
		}
	}
	
	public void FillData(double lat, double lon) {
		tfDemand.setText("0");
		tfLatitude.setText(String.valueOf(lat));
		tfLongitude.setText(String.valueOf(lon));
	}
	
	public boolean getValidate() {
		return validate;
	}
	
	public void clearActionListener() {
		System.out.println(okButton.getActionListeners().length);
		for( ActionListener al : okButton.getActionListeners() ) {
			okButton.removeActionListener( al );
		}
		System.out.println(cancelButton.getActionListeners().length);
		for( ActionListener al : cancelButton.getActionListeners() ) {
			cancelButton.removeActionListener( al );
		}
	}
	
	public void clearOkActionListener() {
		System.out.println(okButton.getActionListeners().length);
		for( ActionListener al : okButton.getActionListeners() ) {
			okButton.removeActionListener( al );
		}
	}
	public void clearCancelActionListener() {
		System.out.println(cancelButton.getActionListeners().length);
		for( ActionListener al : cancelButton.getActionListeners() ) {
			cancelButton.removeActionListener( al );
		}
	}
	
	public String getName() {
		return tfName.getText();
	}
	
	public int getDemand() {
		return Integer.parseInt(tfDemand.getText());
	}
	
	public double getLat() {
		return Double.parseDouble(tfLatitude.getText());
	}
	
	public double getLon() {
		return Double.parseDouble(tfLongitude.getText());
	}
	
	public void setName(String name) {
		tfName.setText(name);
	}
	
}
