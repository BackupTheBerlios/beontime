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
import fr.umlv.smoreau.beontime.model.element.Material;

/**
 * @author sandrine
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ManageMaterialsAdapter implements TableModel {
	
	private final EventListenerList list;
	private BoTModel model;
	private ArrayList listMaterials;
	private final static String[] columnNames = {"Nom","Description"};
	
	
	public ManageMaterialsAdapter(BoTModel model) {
		this.model = model;
		this.list = new EventListenerList();
		
		//TODO pour tester en local
		Material material1 = new Material(new Long(1)); 
		material1.setName("Nom1");
		material1.setDescription("Vidéo projecteur");
		
		Material material2 = new Material(new Long(2)); 
		material2.setName("Nom2");
		material2.setDescription("Description2");
		
		Material material3 = new Material(new Long(3)); 
		material3.setName("Nom3");
		material3.setDescription("Description3");
		
		Material material4 = new Material(new Long(4)); 
		material4.setName("Nom4");
		material4.setDescription("Description4");
		
		Material material5 = new Material(new Long(5)); 
		material5.setName("Nom5");
		material5.setDescription("Description5");
		//finTODO
		
		
		listMaterials = new ArrayList();
		listMaterials.add(material1);
		listMaterials.add(material2);
		listMaterials.add(material3);
		listMaterials.add(material4);
		listMaterials.add(material5);
		
		model.addBoTListener(new ManageMaterialsListener(this));
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	public int getRowCount() {
		
		return listMaterials.size();
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
		
		Material material  = (Material)listMaterials.get(rowIndex);
		
		switch(columnIndex) {
		
		case 0: return material.getName();
		case 1: return material.getDescription();
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
		
		return listMaterials.get(rowIndex);
				
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
	
	private class  ManageMaterialsListener extends DefaultBoTListener {
		private TableModel source;
		
		public  ManageMaterialsListener(TableModel source) {
			this.source = source;
		}	
	}
}
