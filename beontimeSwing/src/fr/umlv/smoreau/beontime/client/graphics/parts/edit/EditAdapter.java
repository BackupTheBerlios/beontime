package fr.umlv.smoreau.beontime.client.graphics.parts.edit;

import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JTree;
import javax.swing.event.*;
import javax.swing.tree.*;

import fr.umlv.smoreau.beontime.client.graphics.BoTModel;
import fr.umlv.smoreau.beontime.client.graphics.event.BoTEvent;
import fr.umlv.smoreau.beontime.client.graphics.event.DefaultBoTListener;
import fr.umlv.smoreau.beontime.dao.TimetableDao;
import fr.umlv.smoreau.beontime.model.Formation;
import fr.umlv.smoreau.beontime.model.timetable.Subject;
import fr.umlv.smoreau.beontime.model.timetable.Timetable;

/**
 * @author BeOnTime
 */
public class EditAdapter /*implements TreeModel*/ extends DefaultTreeModel {
	private BoTModel model;
	private final EventListenerList list;
	private JTree tree;
	
	public EditAdapter(BoTModel model, JTree t) {
		super (new DefaultMutableTreeNode());
		this.model = model;
		this.list = new EventListenerList();
		this.tree = t;
		
		// pour tester en local
	/*	Timetable timetable = new Timetable();
		Formation formation = new Formation();
		formation.setHeading("DESS CRI");
		formation.setIdFormation(new Long(1));
		timetable.setFormation(formation);
		
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
		
		model.setTimetable(timetable); */

		model.addBoTListener(new EditListener());
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
	        Collection c = timetable.getSubjects();
	        return c.toArray()[n];
	    } else if (parent instanceof Subject) {
	        return TimetableDao.TYPES_COURSES[n];
	    }
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeModel#getChildCount(java.lang.Object)
	 */
	public int getChildCount(Object parent) {
	    if (parent instanceof Timetable) {
	        Timetable timetable = (Timetable) parent;
	        Collection c = timetable.getSubjects();
	        return (c == null ? 0 : c.size());
	    } else if (parent instanceof Subject)
	        return 3;

		return 0;
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeModel#isLeaf(java.lang.Object)
	 */
	public boolean isLeaf(Object node) {
	    if (node instanceof Timetable ||
	            node instanceof Subject)
	        return false;

		return true;
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeModel#valueForPathChanged(javax.swing.tree.TreePath, java.lang.Object)
	 */
	public void valueForPathChanged(TreePath arg0, Object arg1) {
	    throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeModel#getIndexOfChild(java.lang.Object, java.lang.Object)
	 */
	public int getIndexOfChild(Object arg0, Object arg1) {
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

	
	private class EditListener extends DefaultBoTListener {
		public void refreshAll(BoTEvent e) {
		    Timetable timetable = e.getTimetable();
		    model.setTimetable(timetable);
		    
		    //TODO rafraîchir l'arbre
		/*    if (timetable.getFormation() == null)
		    	System.err.println("pas de formation");
		    else
		    	Formation formation = timetable.getFormation();
		    	*/
		    
		  //  tree.fireTreeExpanded(new TreePath(timetable));
		   // tree.getModel().addTreeModelListener(this);
//		   tree.getModel().
		  //  tree.;
		//    ((DefaultTreeModel) tree.getModel()).nodeStructureChanged(new DefaultMutableTreeNode(tree.getModel().getRoot()));
		//    ((DefaultTreeModel) tree.getModel()).reload();
		//    tree.treeDidChange();
		    tree.updateUI();
		    System.out.println("refeshall");
		}
		
		public void addSubject(BoTEvent e) {
		    Subject subject = e.getSubject();
		    
		    //TODO ajouter la matière ++
		    System.out.println("addSubject");
		    
		  /*  try {
		    ((EditAdapter) tree.getModel()).nodesWereInserted(new DefaultMutableTreeNode(subject), new int[]{0});
		    } catch (Exception ex) {
		    	System.err.println("pas cool");
		    	ex.getMessage();
		    	ex.printStackTrace();
		    }
		 */
	//	   ((EditAdapter) tree.getModel()).reload();
		/*    ((EditAdapter) tree.getModel()).fireTreeNodesInserted(
	    			tree.getModel().getRoot(),
	    			e.getSource(),
	    			new Object[]{tree.getModel().getRoot(), subject},
	    			null,
	    			null);
*/
	/*	    TreeModelEvent tme = new TreeModelEvent (e.getSource(), new Object[]{tree.getModel().getRoot(), subject});
	//	    treeNodesInserted(new TreeModelEvent(null, new Object[]{tree.getModel().getRoot(), subject}));
		    ((EditAdapter) tree.getModel()).fireTreeNodesInserted(
		    		EditAdapter.this,
		    		tme.getPath(),
		    		tme.getChildIndices(),
		    		tme.getChildren());
		*/
		    Timetable tt = e.getTimetable();
		    EditAdapter ea = (EditAdapter) tree.getModel();
		    ea.fireTreeNodesInserted(ea,
		    		new Object[]{tree.getModel().getRoot(), subject}, 
		    		new int[]{0, 1, 2}, 
		    		new Object[]{TimetableDao.TYPES_COURSES[0], TimetableDao.TYPES_COURSES[1], TimetableDao.TYPES_COURSES[2]});
		    
		    
	//	   tree.treeDidChange();
		//    tree.updateUI();
		//    refreshAll(e);
		}
		
		public void modifySubject(BoTEvent e) {
		    Subject subject = e.getSubject();
		    
		    //TODO modifier la matière
		    System.out.println("modSubject");
		    refreshAll(e);
		}
		
		public void removeSubject(BoTEvent e) {
		    Subject subject = e.getSubject();
		    
		    //TODO supprimer la matière
		    System.out.println("delSubject");
		    refreshAll(e);
		}
	}
}
