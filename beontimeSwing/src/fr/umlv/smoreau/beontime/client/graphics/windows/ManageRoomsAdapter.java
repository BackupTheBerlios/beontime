package fr.umlv.smoreau.beontime.client.graphics.windows;

import java.util.Collection;

import javax.swing.event.EventListenerList;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import fr.umlv.smoreau.beontime.client.graphics.BoTModel;
import fr.umlv.smoreau.beontime.client.graphics.event.BoTEvent;
import fr.umlv.smoreau.beontime.client.graphics.event.DefaultBoTListener;
import fr.umlv.smoreau.beontime.model.element.Room;

/**
 * @author BeOnTime
 */
public class ManageRoomsAdapter implements TableModel {
	private final EventListenerList list;
	private Room[] rooms;
	private final static String[] columnNames = {"Nom","Description"};
	
	
	public ManageRoomsAdapter(BoTModel model, Collection rooms) {
		this.list = new EventListenerList();
		this.rooms = (Room[]) rooms.toArray(new Room[rooms.size()]);
		
		model.addBoTListener(new ManageRoomsListener());
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	public int getRowCount() {
		return rooms.length;
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
	    if (rowIndex < rooms.length) {
		    switch(columnIndex) {
		    	case 0: return rooms[rowIndex].getName();
		    	case 1: return rooms[rowIndex].getDescription();
		    }
	    }
	    
	    throw new IllegalArgumentException("case ("+rowIndex+','+columnIndex+") invalide");
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#setValueAt(java.lang.Object, int, int)
	 */
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
	}

	public Object getObjectAt(int rowIndex) {
		return rooms[rowIndex];	
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
	
	private class ManageRoomsListener extends DefaultBoTListener {
	    public void addRoom(BoTEvent e) throws InterruptedException {
	        Room room = (Room) e.getRoom();
	        //TODO Ajoute la ligne du nouveau local
        }

        public void modifyRoom(BoTEvent e) throws InterruptedException {
            Room room = (Room) e.getRoom();
	        //TODO Modifie la ligne du local modifié
        }

        public void removeRoom(BoTEvent e) throws InterruptedException {
            Room room = (Room) e.getRoom();
	        //TODO Supprime la ligne du local supprimé
        }
	}
}
