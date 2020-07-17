package tools.and.utilities;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JTextPane;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.KeyAdapter;

import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class NewProyectRoute extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JButton okButton;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			NewProyectRoute dialog = new NewProyectRoute());
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public NewProyectRoute(JFrame jf) {
		super(jf, "New proyect");
		//jf.setEnabled(false);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JTextPane txtpnCreateNewProblem = new JTextPane();
			txtpnCreateNewProblem.setBounds(65, 38, 239, 16);
			txtpnCreateNewProblem.setText("Create new problem proyect of route");
			contentPanel.add(txtpnCreateNewProblem);
		}
		{
			JLabel lblNewLabel = new JLabel("Name");
			lblNewLabel.setBounds(65, 90, 61, 16);
			contentPanel.add(lblNewLabel);
		}
		okButton = new JButton("OK");
		okButton.setEnabled(false);
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		{
			
			okButton.setActionCommand("OK");
			buttonPane.add(okButton);
			getRootPane().setDefaultButton(okButton);
			okButton.addActionListener(e -> {
				jf.setEnabled(true);
				dispose();
			});
		}
		{
			JButton cancelButton = new JButton("Cancel");
			cancelButton.setActionCommand("Cancel");
			buttonPane.add(cancelButton);
			dispose();
		}
		
		
		textField = new JTextField();
		textField.setBounds(148, 85, 156, 26);
		contentPanel.add(textField);
		textField.setColumns(10);
		textField.getDocument().addDocumentListener(new DocumentListener() {
		     public void changedUpdate(DocumentEvent e) {
		         changed();
		       }
		       public void removeUpdate(DocumentEvent e) {
		         changed();
		       }
		       public void insertUpdate(DocumentEvent e) {
		         changed();
		       }

		       public void changed() {
		          if (textField.getText().equals("")){
		        	  okButton.setEnabled(false);
		          }
		          else {
		        	  okButton.setEnabled(true);
		         }

		       }
		       
		});
		
	}
	
	public String ReturnValue() {
		return textField.getText();
	}
	
}
