package fr.umlv.smoreau.beontime.client.graphics.windows;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.event.EventListenerList;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import fr.umlv.smoreau.beontime.client.graphics.BoTModel;
import fr.umlv.smoreau.beontime.client.graphics.event.BoTEvent;
import fr.umlv.smoreau.beontime.client.graphics.event.DefaultBoTListener;
import fr.umlv.smoreau.beontime.model.user.User;

/**
 * @author BeOnTime
 */
public class ManageUsersAdapter implements TableModel {
	private final EventListenerList list;
	private ArrayList users;
	private final static String[] columnNames = {"Nom","Prénom","Courriel"};
	
	
	
	public ManageUsersAdapter(BoTModel model, Collection users) {
		this.list = new EventListenerList();
		this.users = new ArrayList(users);
		
		model.addBoTListener(new ManageUsersListener(this));
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	public int getRowCount() {
		return users.size();
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
	    if (rowIndex < users.size()) {
	        switch(columnIndex) {
		        case 0: return ((User)users.get(rowIndex)).getName();
		        case 1: return ((User)users.get(rowIndex)).getFirstName();
		        case 2: return ((User)users.get(rowIndex)).getEMail();
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
		return (User)users.get(rowIndex);
	}
	
	public int indexOf(Object obj) {
	    User user = (User) obj;
	    for (int i = 0; i < users.size(); ++i) {
	        if (user.equals(users.get(i)))
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


	private class ManageUsersListener extends DefaultBoTListener {
	    private TableModel source;

		public ManageUsersListener(TableModel source) {
			this.source = source;
		}

	    public void addUser(BoTEvent e) throws InterruptedException {
	        User user = (User) e.getUser();
	        users.add(user);
	        final int row = indexOf(user);
	        
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

	    public void modifyUser(BoTEvent e) throws InterruptedException {
	        User user = (User) e.getUser();
	        final int row = indexOf(user);

            final Object[] listeners = list.getListenerList();

			for (int i = listeners.length-2; i >= 0; i -= 2) {
				if (listeners[i] == TableModelListener.class) {
					final int index = i;
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							((TableModelListener)listeners[index+1]).tableChanged(new TableModelEvent(source,row,row,2,TableModelEvent.UPDATE));
						}
					});
				}
			}
	    }
	    
	    public void removeUser(BoTEvent e) throws InterruptedException {
	        User user = (User) e.getUser();
	        final int row = indexOf(user);
            users.remove(user);

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
