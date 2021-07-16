package tools.and.utilities;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.DefaultXYDataset;





public class GraficResult extends JPanel {

	/**
	 * Create the panel.
	 */
	
	public JList<EjercicioSolucion> listSol;
	DefaultListModel<EjercicioSolucion> nodoModelSol;
	//private JPanel panelMain;
	//JScrollPane scroll;
	
	public JButton btnShowReport;
	public JButton btnDeleteSol;
	public JLabel lblGrafic1;
	public JLabel lblGrafic2;
	
	public GraficResult() {
		//panelMain = new JPanel();
		//this.setLayout(new BorderLayout());
		this.setLayout(null);
		//JPanel panelMain = new JPanel();
		//panelMain.setSize(new Dimension(300, 600));
		//panelMain.setLayout(null);
		/*JScrollPane scrollPane = new JScrollPane(panelMain, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
			    JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(0, 0, 900, 1200);*/
		//scrollPane.setExtendedState(JPanel. .MAXIMIZED_BOTH);
		
		nodoModelSol = new DefaultListModel<>();
		
		setLayout(null);
		JLabel lblSolutions = new JLabel("All solutions generated in this problem");
		lblSolutions.setBounds(65, 20, 282, 16);
		add(lblSolutions);
		//panelMain.add(lblSolutions);
		
		listSol = new JList<>();
		listSol.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listSol.addListSelectionListener(new ListSelectionListener() {
			@Override
		    public void valueChanged(ListSelectionEvent e) {
		        //tfName.setEditable(true);
				//tfDemand.setEditable(true);
				//fillNodo(list.getSelectedValue());
				btnShowReport.setEnabled(true);
				btnDeleteSol.setEnabled(true);
		    }
		});
		listSol.setBounds(65, 604, 712, 151);
		JScrollPane listScrollerSol = new JScrollPane(listSol);
	    //listScroller.setViewportView(list);
		listScrollerSol.setBounds(65, 40, 712, 151);
		//add(new JScrollPane(list));
		add(listScrollerSol);
		//panelMain.add(listScrollerSol);
		
		btnShowReport = new JButton("Show Report");
		btnShowReport.setBounds(75, 200, 130, 29);
		add(btnShowReport);
		//panelMain.add(btnShowReport);
		
		btnDeleteSol = new JButton("Delete Solution");
		btnDeleteSol.setBounds(217, 200, 130, 29);
		add(btnDeleteSol);
		//panelMain.add(btnDeleteSol);
		
		lblGrafic1 = new JLabel();
		lblGrafic1.setBounds(65, 250, 712, 360);
		//panelMain.add(lblGrafic1);
		add(lblGrafic1);
		
		lblGrafic2 = new JLabel("");
		lblGrafic2.setBounds(65, 622, 712, 408);
		//panelMain.add(lblGrafic2);
		add(lblGrafic2);
		
		//scrollPane = new JScrollPane(panelMain);
		//add(scrollPane, BorderLayout.CENTER);
		//scroll.setBounds(0, 0, 712, 151);
				
		
		//JScrollPane panelPane = new JScrollPane(this);
		//add(panelPane);
		//scroll.add(panelMain);
		//add(scroll);
		
		//testGraficoBarras();
	}
	
	public void listUpdate(NodosList ejercicio) {		
		nodoModelSol.clear();
		nodoModelSol.addAll(ejercicio.getEjercicioSolucion());
		listSol.setModel(nodoModelSol);
	}
	
	public void clearList() {
		nodoModelSol.removeAllElements();
		listSol.setModel(nodoModelSol);
		//clearDisplay();
	}
	
	public void clearDisplay() {
		btnShowReport.setEnabled(false);
		btnDeleteSol.setEnabled(false);
		
		//tableRoute.removeAll();
	}
	
	public int getItemIndexSelectionSol() {
		return listSol.getSelectedIndex();
	}
	
	public void generateGrafic(List<Double> dis, List<Double> dur) {
		List<Double> newDis = change100(dis);
		List<Double> newDur = change100(dur);
		
		final String series1 = "Distance";
        final String series2 = "Duration";
        
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for(int i=0;i<newDis.size();i++) {
        	dataset.addValue(newDis.get(i), series1, "Car"+(i+1));
        	dataset.addValue(newDur.get(i), series2, "Car"+(i+1));
        }
        
        //JFreeChart grafico_barras = ChartFactory.createBarChart3D("Calificaciones Java","Estudiantes", "Promedios",dataset, PlotOrientation.VERTICAL, true, true, false);
        JFreeChart grafico_barras = ChartFactory.createBarChart("Cost comparison between distance and duration","Costs in percentages (%)", "Cars used for the problem",dataset, PlotOrientation.VERTICAL, true, true, false);
        //((NumberAxis)grafico_barras.getXYPlot().getRangeAxis()).setNumberFormatOverride(new DecimalFormat("#'%'"));
        
        //XYPlot plot = (XYPlot)grafico_barras.getXYPlot();
        //NumberAxis rangeAxis = new NumberAxis();
        //rangeAxis.setNumberFormatOverride(new DecimalFormat("#'%'"));
        //plot.setDomainAxis(rangeAxis);
        
        CategoryPlot plot = (CategoryPlot) grafico_barras.getPlot(); 
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        //DecimalFormat pctFormat = new DecimalFormat("#.##%");
        //DecimalFormat pctFormat = new DecimalFormat("%.1f %%");
        //pctFormat.;
        
        DecimalFormat pctFormat = new DecimalFormat("##.##");
        //pctFormat.setMultiplier(100);
        plot.getRenderer().setSeriesItemLabelGenerator(0, new StandardCategoryItemLabelGenerator("{2}", pctFormat));
        plot.getRenderer().setSeriesItemLabelsVisible(0, true);
        plot.getRenderer().setSeriesItemLabelGenerator(1, new StandardCategoryItemLabelGenerator("{2}", pctFormat));
        plot.getRenderer().setSeriesItemLabelsVisible(1, true);
        
        rangeAxis.setNumberFormatOverride(pctFormat);
        
        
        //XYPlot plot = (XYPlot) chart.getPlot();
        //DateAxis dateAxis = new DateAxis();
        //dateAxis.setDateFormatOverride(new SimpleDateFormat("dd-MM-yyyy")); 
        //plot.setDomainAxis(dateAxis);
        
        
        //grafico_barras.getXYPlot().getRangeAxis().setRange(0, 100);
        //((NumberAxis) grafico_barras.getXYPlot().getRangeAxis()).setNumberFormatOverride(new DecimalFormat("#'%'"));
        BufferedImage image = grafico_barras.createBufferedImage(700,360);
        lblGrafic1.setIcon(new ImageIcon(image));
        
        repaint();
        /*dataset.addValue(1.0, series1, category1);
        dataset.addValue(4.0, series1, category2);
        dataset.addValue(3.0, series1, category3);
        dataset.addValue(5.0, series1, category4);
        dataset.addValue(5.0, series1, category5);

        dataset.addValue(5.0, series2, category1);
        dataset.addValue(7.0, series2, category2);
        dataset.addValue(6.0, series2, category3);
        dataset.addValue(8.0, series2, category4);
        dataset.addValue(4.0, series2, category5);*/
	}
	
	private List<Double> change100(List<Double> value){
		List<Double> newValue = new ArrayList<Double>();
		Double total = 0.0;
		for(int i=0; i<value.size(); i++) {
			total += value.get(i);
		}
		for(int i=0; i<value.size(); i++) {
			newValue.add((value.get(i)*100)/total);
		}
		return newValue;
	}
	
}
