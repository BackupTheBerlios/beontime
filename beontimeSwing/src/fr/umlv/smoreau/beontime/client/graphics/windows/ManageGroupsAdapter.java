package fr.umlv.smoreau.beontime.client.graphics.windows;

import java.util.Collection;

import javax.swing.JTree;
import javax.swing.event.EventListenerList;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import fr.umlv.smoreau.beontime.client.graphics.BoTModel;
import fr.umlv.smoreau.beontime.client.graphics.event.BoTEvent;
import fr.umlv.smoreau.beontime.client.graphics.event.DefaultBoTListener;
import fr.umlv.smoreau.beontime.model.timetable.Timetable;

/**
 * @author BeOnTime
 */
public class ManageGroupsAdapter implements TreeModel {
	private BoTModel model;
	private final EventListenerList list;
	private JTree tree;

	public ManageGroupsAdapter(BoTModel model, JTree t) {
		this.model = model;
		this.list = new EventListenerList();
		this.tree = t;

		model.addBoTListener(new TreeListener());
	}
	
	
	
	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeModel#getRoot()
	 */
	public Object getRoot() {
		return model.getTimetable(); 
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeModel#getChild(java.lang.Object, int)
	 */
	public Object getChild(Object parent, int n) {
		if (parent instanceof Timetable) {
	        Timetable timetable = (Timetable) parent;
	        Collection c = timetable.getGroups();
	        return c.toArray()[n];
		}
		
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeModel#getChildCount(java.lang.Object)
	 */
	public int getChildCount(Object parent) {
		if (parent instanceof Timetable) {
	        Timetable timetable = (Timetable) parent;
	        Collection c = timetable.getGroups();
	        return (c == null ? 0 : c.size());
	    } 
		
		return 0;
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeModel#isLeaf(java.lang.Object)
	 */
	public boolean isLeaf(Object node) {
		 if (node instanceof Timetable)
	        return false;

		return true;
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeModel#valueForPathChanged(javax.swing.tree.TreePath, java.lang.Object)
	 */
	public void valueForPathChanged(TreePath path, Object newValue) {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeModel#getIndexOfChild(java.lang.Object, java.lang.Object)
	 */
	public int getIndexOfChild(Object parent, Object child) {
		return 0;
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeModel#addTreeModelListener(javax.swing.event.TreeModelListener)
	 */
	public void addTreeModelListener(TreeModelListener l) {
		list.add(TreeModelListener.class, l);
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeModel#removeTreeModelListener(javax.swing.event.TreeModelListener)
	 */
	public void removeTreeModelListener(TreeModelListener l) {
		list.remove(TreeModelListener.class, l);
	}


	private class TreeListener extends DefaultBoTListener {
	    public void addGroup(BoTEvent e) {
	        //TODO améliorer
		    tree.updateUI();
		    tree.setVisible(true);
		}
		
		public void modifyGroup(BoTEvent e) {
		    //TODO améliorer
		    tree.updateUI();
		    tree.setVisible(true);
		}
		
		public void removeGroup(BoTEvent e) {
		    //TODO améliorer
		    tree.updateUI();
		    tree.setVisible(true);
		}
	}
}
