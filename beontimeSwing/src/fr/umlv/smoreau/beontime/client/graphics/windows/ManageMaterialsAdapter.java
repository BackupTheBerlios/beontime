package fr.umlv.smoreau.beontime.client.graphics.windows;

import java.util.Collection;

import javax.swing.event.EventListenerList;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import fr.umlv.smoreau.beontime.client.graphics.BoTModel;
import fr.umlv.smoreau.beontime.client.graphics.event.BoTEvent;
import fr.umlv.smoreau.beontime.client.graphics.event.DefaultBoTListener;
import fr.umlv.smoreau.beontime.model.element.Material;

/**
 * @author BeOnTime
 */
public class ManageMaterialsAdapter implements TableModel {
	private final EventListenerList list;
	private Material[] materials;
	private final static String[] columnNames = {"Nom","Description"};
	
	
	public ManageMaterialsAdapter(BoTModel model, Collection materials) {
		this.list = new EventListenerList();
		this.materials = (Material[]) materials.toArray(new Material[materials.size()]);
		
		//TODO pour tester en local
		/*Material material1 = new Material(new Long(1)); 
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
		listMaterials.add(material5);*/
		
		model.addBoTListener(new ManageMaterialsListener());
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	public int getRowCount() {
		return materials.length;
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
	    if (rowIndex < materials.length) {
		    switch(columnIndex) {
		    	case 0: return materials[rowIndex].getName();
		    	case 1: return materials[rowIndex].getDescription();
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
		return materials[rowIndex];		
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
	    public void addMaterial(BoTEvent e) throws InterruptedException {
	        Material material = (Material) e.getMaterial();
	        //TODO Ajoute la ligne du nouveau matériel
        }

        public void modifyMaterial(BoTEvent e) throws InterruptedException {
            Material material = (Material) e.getMaterial();
	        //TODO Modifie la ligne du matériel modifié
        }

        public void removeMaterial(BoTEvent e) throws InterruptedException {
            Material material = (Material) e.getMaterial();
	        //TODO Supprime la ligne du matériel supprimé
        }
	}
}
