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
import fr.umlv.smoreau.beontime.model.element.Material;

/**
 * @author BeOnTime
 */
public class ManageMaterialsAdapter extends AbstractTableModel {
	private final EventListenerList list;
	private ArrayList materials;
	private final static String[] columnNames = {"Nom","Description"};
	
	
	
	public ManageMaterialsAdapter(BoTModel model, Collection materials) {
		this.list = new EventListenerList();
		this.materials = new ArrayList(materials);
		
		model.addBoTListener(new ManageMaterialsListener(this));
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	public int getRowCount() {
		return materials.size();
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
		
		if (rowIndex < materials.size()) {
			switch(columnIndex) {
			case 0: return ((Material)materials.get(rowIndex)).getName();
			case 1: return ((Material)materials.get(rowIndex)).getDescription();
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
		return (Material)materials.get(rowIndex);		
	}
	
	public int indexOf(Object obj) {
	    Material material = (Material) obj;
	    for (int i = 0; i < materials.size(); ++i) {
	        if (material.equals(materials.get(i)))
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


	private class  ManageMaterialsListener extends DefaultBoTListener {
	    private TableModel source;

		public ManageMaterialsListener(TableModel source) {
			this.source = source;
		}

		public void addMaterial(BoTEvent e) throws InterruptedException {
		    Material material = (Material) e.getMaterial();
	        materials.add(material);
	        final int row = indexOf(material);
	        
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
		
		public void modifyMaterial(BoTEvent e) throws InterruptedException {
		    Material material = (Material) e.getMaterial();
            final int row = indexOf(material);

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
		
		public void removeMaterial(BoTEvent e) throws InterruptedException {
		    Material material = (Material) e.getMaterial();
            final int row = indexOf(material);
            materials.remove(material);

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
