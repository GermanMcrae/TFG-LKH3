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

public class PointProblem extends JOptionPane {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfName;
	private JTextField tfWeight;
	private JTextField tfLatitude;
	private JTextField tfLongitude;

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
		super("Add Point");
		validate = false;
		setBounds(100, 100, 420, 300);
		//getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		//getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(45, 26, 61, 16);
		contentPanel.add(lblNewLabel);
		
		tfName = new JTextField();
		tfName.setBounds(128, 21, 266, 26);
		contentPanel.add(tfName);
		tfName.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Weight");
		lblNewLabel_1.setBounds(45, 70, 61, 16);
		contentPanel.add(lblNewLabel_1);
		
		tfWeight = new JTextField();
		tfWeight.setBounds(128, 65, 266, 26);
		contentPanel.add(tfWeight);
		tfWeight.setColumns(10);
		
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
		
		JLabel lblNewLabel_2_1 = new JLabel("Longitude");
		lblNewLabel_2_1.setBounds(45, 147, 71, 16);
		contentPanel.add(lblNewLabel_2_1);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			//getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//dispose();
					}
				});
				buttonPane.add(okButton);
				//getRootPane().setDefaultButton(okButton);
				validate = true;
				//dispose();
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				validate = false;
				//dispose();
			}
		}
	}
	
	public void FillData(double lat, double lon) {
		tfLatitude.setText(String.valueOf(lat));
		tfLongitude.setText(String.valueOf(lon));
	}
	
	public boolean getValidate() {
		return validate;
	}
}
