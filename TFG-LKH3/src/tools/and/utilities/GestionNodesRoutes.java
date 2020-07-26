package tools.and.utilities;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import java.awt.GridLayout;
import javax.swing.ListSelectionModel;

public class GestionNodesRoutes extends JPanel {

	/**
	 * Create the panel.
	 */
	private JList<Nodo> list;
	DefaultListModel<Nodo> nodoModel;
	
	public GestionNodesRoutes() {
		setLayout(null);
		nodoModel = new DefaultListModel<>();
		list = new JList<>();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBounds(65, 96, 256, 258);
		JScrollPane listScroller = new JScrollPane(list);
	    //listScroller.setViewportView(list);
		listScroller.setBounds(65, 96, 256, 258);
		//add(new JScrollPane(list));
		add(listScroller);
		
		


	}
	
	public void listUpdate(NodosList ejercicio) {
		
		nodoModel.addAll(ejercicio.getNodes());
		list.setModel(nodoModel);
	}
	
	public void clearList() {
		nodoModel.removeAllElements();
		list.setModel(nodoModel);
	}
}


/*final JList<Student> studentList = new JList<>();
            final DefaultListModel<Student> studentModel = new DefaultListModel<>();
            studentList.setModel(studentModel);*/