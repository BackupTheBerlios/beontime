package fr.umlv.smoreau.beontime.client.graphics.windows;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.event.EventListenerList;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import fr.umlv.smoreau.beontime.client.graphics.BoTModel;
import fr.umlv.smoreau.beontime.client.graphics.event.BoTEvent;
import fr.umlv.smoreau.beontime.client.graphics.event.DefaultBoTListener;
import fr.umlv.smoreau.beontime.model.element.Room;

/**
 * @author BeOnTime
 */
public class ManageRoomsAdapter extends AbstractTableModel {
	private final EventListenerList list;
	private ArrayList rooms;
	private final static String[] columnNames = {"Nom","Description"};
	
	
	public ManageRoomsAdapter(BoTModel model, Collection rooms) {
		this.list = new EventListenerList();
		this.rooms = new ArrayList(rooms);
		
		model.addBoTListener(new ManageRoomsListener(this));
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	public int getRowCount() {
		return rooms.size();
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
	    if (rowIndex < rooms.size()) {
		    switch(columnIndex) {
		    	case 0: return ((Room) rooms.get(rowIndex)).getName();
		    	case 1: return ((Room) rooms.get(rowIndex)).getDescription();
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
	    if (rowIndex < 0 || rowIndex >= getRowCount())
	        return null;
		return rooms.get(rowIndex);
	}
	
	public int indexOf(Object obj) {
	    Room room = (Room) obj;
	    for (int i = 0; i < rooms.size(); ++i) {
	        if (room.equals(rooms.get(i)))
	            return i;
	    }
	    return -1;
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
	    private TableModel source;

		public ManageRoomsListener(TableModel source) {
			this.source = source;
		}

	    public void addRoom(BoTEvent e) throws InterruptedException {
	        Room room = (Room) e.getRoom();
	        rooms.add(room);
	        final int row = indexOf(room);
	        
	        final Object[] listeners = list.getListenerList();

	        for (int i = listeners.length-2; i >= 0; i -= 2) {
				if (listeners[i] == TableModelListener.class) {
					final int index = i;
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							((TableModelListener)listeners[index+1]).tableChanged(new TableModelEvent(source,row,row,TableModelEvent.ALL_COLUMNS,TableModelEvent.INSERT));
						}
					});
				}
			}
        }

        public void modifyRoom(BoTEvent e) throws InterruptedException {
            Room room = (Room) e.getRoom();
            final int row = indexOf(room);

            final Object[] listeners = list.getListenerList();

			for (int i = listeners.length-2; i >= 0; i -= 2) {
				if (listeners[i] == TableModelListener.class) {
					final int index = i;
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							((TableModelListener)listeners[index+1]).tableChanged(new TableModelEvent(source,row,row,1,TableModelEvent.UPDATE));
						}
					});
				}
			}
        }

        public void removeRoom(BoTEvent e) throws InterruptedException {
            Room room = (Room) e.getRoom();
            final int row = indexOf(room);
            rooms.remove(room);

            final Object[] listeners = list.getListenerList();

			for (int i = listeners.length-2; i >= 0; i -= 2) {
				if (listeners[i] == TableModelListener.class) {
					final int index = i;
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							((TableModelListener)listeners[index+1]).tableChanged(new TableModelEvent(source,row,row,TableModelEvent.ALL_COLUMNS,TableModelEvent.DELETE));
						}
					});
				}
			}
        }
	}
}
