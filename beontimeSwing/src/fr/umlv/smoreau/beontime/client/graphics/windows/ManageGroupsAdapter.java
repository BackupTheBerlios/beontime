/*
 * Created on 28 févr. 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package fr.umlv.smoreau.beontime.client.graphics.windows;

import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JTree;
import javax.swing.event.EventListenerList;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;


import fr.umlv.smoreau.beontime.client.graphics.BoTModel;
import fr.umlv.smoreau.beontime.client.graphics.event.DefaultBoTListener;
import fr.umlv.smoreau.beontime.model.Formation;
import fr.umlv.smoreau.beontime.model.Group;
import fr.umlv.smoreau.beontime.model.timetable.Subject;
import fr.umlv.smoreau.beontime.model.timetable.Timetable;

/**
 * @author sandrine
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ManageGroupsAdapter implements TreeModel {

	
	private BoTModel model;
	private final EventListenerList list;
	private JTree tree;

	public ManageGroupsAdapter(BoTModel model, JTree t) {
		this.model = model;
		this.list = new EventListenerList();
		this.tree = t;
		
		//TODO pour tester en local
		Timetable timetable = new Timetable();
		Formation formation = new Formation();
		formation.setHeading("DESS CRI");
		formation.setIdFormation(new Long(1));
		timetable.setFormation(formation);
		
		Group group1 = new Group();
		group1.setHeading("Groupe1");
		group1.setIdGroup(new Long(1));
		
		Group group2 = new Group();
		group2.setHeading("Groupe2");
		group2.setIdGroup(new Long(2));
		
		Group group3 = new Group();
		group3.setHeading("Groupe3");
		group3.setIdGroup(new Long(3));
		
		ArrayList listGroups = new ArrayList();
		listGroups.add(group1);
		listGroups.add(group2);
		listGroups.add(group3);
		timetable.setGroups(listGroups);
		
		
		Subject subject = new Subject();
		subject.setHeading("Matière1");
		subject.setIdSubject(new Long(1));
		Subject subject2 = new Subject();
		subject2.setHeading("Matière2");
		subject2.setIdSubject(new Long(2));
		Subject subject3 = new Subject();
		subject3.setHeading("Matière3");
		subject3.setIdSubject(new Long(3));
		ArrayList list = new ArrayList();
		list.add(subject);
		list.add(subject2);
		list.add(subject3);
		timetable.setSubjects(list);
		
		model.setTimetable(timetable);
		//finTODO

		model.addBoTListener(new TreeListener(this));
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
		// TODO Auto-generated method stub
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
		private TreeModel source;

		public TreeListener(TreeModel source) {
			this.source = source;
		}	
	}

}
