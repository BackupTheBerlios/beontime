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
import fr.umlv.smoreau.beontime.model.element.Room;

/**
 * @author BeOnTime
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ManageRoomsAdapter implements TableModel {

	private final EventListenerList list;
	private BoTModel model;
	private ArrayList listRooms;
	private final static String[] columnNames = {"Nom","Description"};
	
	
	public ManageRoomsAdapter(BoTModel model) {
		this.model = model;
		this.list = new EventListenerList();
		
		//TODO pour tester en local
		Room room1 = new Room(new Long(1)); 
		room1.setName("Nom1");
		
		Room room2 = new Room(new Long(2)); 
		room2.setName("Nom2");
		
		Room room3 = new Room(new Long(3)); 
		room3.setName("Nom3");
		
		Room room4 = new Room(new Long(4)); 
		room4.setName("Nom4");
		
		Room room5 = new Room(new Long(5)); 
		room5.setName("Nom5");
		//finTODO
		
		
		listRooms = new ArrayList();
		listRooms.add(room1);
		listRooms.add(room2);
		listRooms.add(room3);
		listRooms.add(room4);
		listRooms.add(room5);
		
		model.addBoTListener(new ManageRoomsListener(this));
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	public int getRowCount() {
		
		return listRooms.size();
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
		
		Room room  = (Room)listRooms.get(rowIndex);
		
		switch(columnIndex) {
		
		case 0: return room.getName();
		case 1: return room.getDescription();
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
	 * @see javax.swing.table.TableModel#addTableModelListener(javax.swing.event.TableModelListener)
	 */
	public void addTableModelListener(TableModelListener l) {
		list.add(l.getClass(), l);

	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#removeTableModelListener(javax.swing.event.TableModelListener)
	 */
	public void removeTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub
		 list.remove(l.getClass(), l);
	}

	private class ManageRoomsListener extends DefaultBoTListener {
		private TableModel source;

		public ManageRoomsListener(TableModel source) {
			this.source = source;
		}	
	}
}
