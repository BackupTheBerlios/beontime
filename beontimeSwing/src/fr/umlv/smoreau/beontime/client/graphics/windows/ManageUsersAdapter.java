/*
 * Created on 1 mars 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package fr.umlv.smoreau.beontime.client.graphics.windows;

import java.util.ArrayList;

import javax.swing.event.EventListenerList;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import fr.umlv.smoreau.beontime.client.graphics.BoTModel;
import fr.umlv.smoreau.beontime.client.graphics.event.DefaultBoTListener;
import fr.umlv.smoreau.beontime.model.user.User;

/**
 * @author BeOnTime
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ManageUsersAdapter implements TableModel {

	
	private final EventListenerList list;
	private BoTModel model;
	private ArrayList listUsers;
	private final static String[] columnNames = {"Nom","Prénom","Courriel"};
	
	
	public ManageUsersAdapter(BoTModel model) {
		this.model = model;
		this.list = new EventListenerList();
		
		//TODO pour tester en local
		User user1 = new User(new Long(1), "enseignant"); 
		user1.setName("Nom1");
		user1.setFirstName("prenom1");
		user1.setEMail("user1@yahoo.fr");
		
		User user2 = new User(new Long(2), "enseignant"); 
		user2.setName("Nom2");
		user2.setFirstName("prenom2");
		user2.setEMail("user2@yahoo.fr");
		
		User user3 = new User(new Long(3), "enseignant"); 
		user3.setName("Nom3");
		user3.setFirstName("prenom3");
		user3.setEMail("user3@yahoo.fr");
		
		User user4 = new User(new Long(4), "enseignant"); 
		user4.setName("Nom4");
		user4.setFirstName("prenom4");
		user4.setEMail("user4@yahoo.fr");
		
		User user5 = new User(new Long(5), "enseignant"); 
		user5.setName("Nom5");
		user5.setFirstName("prenom5");
		user5.setEMail("user5@yahoo.fr");
		//finTODO
		
		
		listUsers = new ArrayList();
		listUsers.add(user1);
		listUsers.add(user2);
		listUsers.add(user3);
		listUsers.add(user4);
		listUsers.add(user5);
		
		model.addBoTListener(new ManageUsersListener(this));
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	public int getRowCount() {
		
		return listUsers.size();
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	public int getColumnCount() {
		return 3;
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getColumnName(int)
	 */
	public String getColumnName(int columnIndex) {
		return columnNames[columnIndex];
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
		
		User user  = (User)listUsers.get(rowIndex);
		
		switch(columnIndex) {
		
		case 0: return user.getName();
		case 1: return user.getFirstName();
		case 2: return user.getEMail();
		}
		
		throw new IllegalArgumentException("colonne invalide ("+rowIndex+','+columnIndex+')');
		
	}
	

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#setValueAt(java.lang.Object, int, int)
	 */
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub

	}

	
	/* (non-Javadoc)
	 * 
	 */
	public Object getObjectAt(int rowIndex) {
		
		return listUsers.get(rowIndex);
				
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

	private class ManageUsersListener extends DefaultBoTListener {
		private TableModel source;

		public ManageUsersListener(TableModel source) {
			this.source = source;
		}	
	}
}
