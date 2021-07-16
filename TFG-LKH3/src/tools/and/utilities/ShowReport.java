package tools.and.utilities;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ShowReport extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public JButton okButton;

	private JTextArea taTextReport;
	private JScrollPane scroll;
	
	public ShowReport(JFrame jf) {
		setBounds(100, 100, 800, 850);
		taTextReport = new JTextArea();
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		taTextReport.setBounds(6, 6, 788, 777);
		taTextReport.setEditable(false);
		scroll = new JScrollPane(taTextReport, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		scroll.setBounds(6, 6, 788, 777);
		contentPanel.add(scroll);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
	
	public void getText(String text) {
		
		taTextReport.setText(text);
	}
}
