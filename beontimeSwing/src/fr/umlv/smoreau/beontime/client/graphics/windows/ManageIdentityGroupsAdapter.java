package fr.umlv.smoreau.beontime.client.graphics.windows;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.event.EventListenerList;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import fr.umlv.smoreau.beontime.client.graphics.BoTModel;
import fr.umlv.smoreau.beontime.client.graphics.event.DefaultBoTListener;
import fr.umlv.smoreau.beontime.model.Formation;
import fr.umlv.smoreau.beontime.model.Group;

/**
 * @author BeOnTime
 */
public class ManageIdentityGroupsAdapter implements TableModel {

	private final EventListenerList list;
	private BoTModel model;
	private ArrayList groups;
	private ArrayList students;
	
	
	
	
	public ManageIdentityGroupsAdapter(BoTModel model, Formation formation, ArrayList groups) {
		this.model = model;
		this.list = new EventListenerList();
		this.groups = groups;
	
		HashSet setStudents = new HashSet();
		
		for(Iterator it = formation.getGroups().iterator();it.hasNext();) {
			for(Iterator it2 = ((Group)it.next()).getStudents().iterator();it2.hasNext();) {
				// TODO Normalement Je pense que c'est le nom de l'étudiant, pour l'instant j'ai mis sont identificateur
				setStudents.add((Long)it2.next());
			}	
		}	
		
		this.students = new ArrayList(setStudents);
	
		model.addBoTListener(new ManageIdentityGroupsListener(this));
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	public int getRowCount() {
		
		return students.size();
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	public int getColumnCount() {
		return groups.size()+1;
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getColumnName(int)
	 */
	public String getColumnName(int columnIndex) {
		
		if (columnIndex == 0)
			return "";
		
		return ((Group)groups.get(columnIndex-1)).getHeading();
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getColumnClass(int)
	 */
	public Class getColumnClass(int columnIndex) {
		// TODO Auto-generated method stub
		return Object.class;
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#isCellEditable(int, int)
	 */
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		
		Long ID_student = (Long)students.get(rowIndex);
		
		if (columnIndex == 0)
			return ID_student;
		
		
		for(Iterator it = ((Group)(groups.get(columnIndex-1))).getStudents().iterator();it.hasNext();) {
			
			if ( ((Long)it.next()).compareTo(ID_student) == 0)
				return "oui";
		}	
		
		return "non";
		
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#setValueAt(java.lang.Object, int, int)
	 */
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#addTableModelListener(javax.swing.event.TableModelListener)
	 */
	public void addTableModelListener(TableModelListener l) {
		list.add(TableModelListener.class, l);
		
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#removeTableModelListener(javax.swing.event.TableModelListener)
	 */
	public void removeTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub
		list.remove(TableModelListener.class, l);
	}
	
	private class  ManageIdentityGroupsListener extends DefaultBoTListener {
		private TableModel source;
		
		public  ManageIdentityGroupsListener(TableModel source) {
			this.source = source;
		}	
	}
}
