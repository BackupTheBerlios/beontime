package fr.umlv.smoreau.beontime.client.graphics.windows;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.event.EventListenerList;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import fr.umlv.smoreau.beontime.client.graphics.BoTModel;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.client.graphics.event.DefaultBoTListener;
import fr.umlv.smoreau.beontime.dao.DaoManager;
import fr.umlv.smoreau.beontime.dao.GroupDao;
import fr.umlv.smoreau.beontime.filter.UserFilter;
import fr.umlv.smoreau.beontime.model.Group;
import fr.umlv.smoreau.beontime.model.association.BelongStudentGroup;
import fr.umlv.smoreau.beontime.model.user.User;

/**
 * @author BeOnTime
 */
public class ManageIdentityGroupsAdapter implements TableModel {
	private final EventListenerList list;
	private BoTModel model;
	private Group group;
	private ArrayList students;
	
	
	public ManageIdentityGroupsAdapter(BoTModel model) {
		this.model = model;
		this.list = new EventListenerList();
		this.group = MainFrame.getInstance().getGroupSelected();
		
		try {
			this.group = DaoManager.getGroupDao().getGroup(this.group, new String[] {GroupDao.JOIN_STUDENTS});
		} catch (Exception e) {
			this.group.setStudents(new HashSet());
		}
		
		try {
			UserFilter filter = new UserFilter();
			filter.setIdFormation(group.getIdFormation());
			this.students = (ArrayList) DaoManager.getUserDao().getStudents(filter);
		} catch (Exception e) {
			this.students = new ArrayList();
		}
	
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
		return 2;
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getColumnName(int)
	 */
	public String getColumnName(int columnIndex) {
		if (columnIndex == 0)
			return "";
		
		return group.getHeading();
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getColumnClass(int)
	 */
	public Class getColumnClass(int columnIndex) {
		return Object.class;
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#isCellEditable(int, int)
	 */
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	public Object getValueAt(int rowIndex, int columnIndex) {
		User student = (User) students.get(rowIndex);

		if (columnIndex == 0)
			return student;

		for (Iterator it = group.getStudents().iterator(); it.hasNext(); ) {
			BelongStudentGroup belong = (BelongStudentGroup) it.next();
			if (belong.getIdStudent().equals(student.getIdUser()))
				return belong;
		}

		return null;
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#setValueAt(java.lang.Object, int, int)
	 */
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
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
		list.remove(TableModelListener.class, l);
	}
	
	public EventListenerList getEventListenerList() {
		return list;
	}
	
	public Group getGroup() {
		return group;
	}
	
	private class  ManageIdentityGroupsListener extends DefaultBoTListener {
		private TableModel source;
		
		public  ManageIdentityGroupsListener(TableModel source) {
			this.source = source;
		}	
	}
}
