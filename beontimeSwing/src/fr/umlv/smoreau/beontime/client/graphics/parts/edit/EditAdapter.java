package fr.umlv.smoreau.beontime.client.graphics.parts.edit;
/* DESS CRI - BeOnTime - timetable project */

import java.util.Collection;

import javax.swing.JTree;
import javax.swing.event.*;
import javax.swing.tree.*;

import fr.umlv.smoreau.beontime.client.graphics.BoTModel;
import fr.umlv.smoreau.beontime.client.graphics.event.BoTEvent;
import fr.umlv.smoreau.beontime.client.graphics.event.DefaultBoTListener;
import fr.umlv.smoreau.beontime.dao.TimetableDao;
import fr.umlv.smoreau.beontime.model.timetable.Subject;
import fr.umlv.smoreau.beontime.model.timetable.Timetable;

/**
 * An Adapter on the BotModel to manage the tree view (edit panel)
 * @author BeOnTime team
 */
public class EditAdapter /*implements TreeModel*/ extends DefaultTreeModel {
	/** This class has to be serializable */
	private static final long serialVersionUID = 1L;
	
	private BoTModel model;
	private final EventListenerList list;
	private JTree tree;
	
	public EditAdapter(BoTModel model, JTree t) {
		super (new DefaultMutableTreeNode());
		this.model = model;
		this.list = new EventListenerList();
		this.tree = t;
		
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
		    model.setTimetable(e.getTimetable());
		    
		    //TODO améliorer
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
		 //   tree.treeDidChange();
		    tree.updateUI();
		    tree.setVisible(true);
	//	    System.out.println("refeshall");
		}
		
		public void addSubject(BoTEvent e) {
	//	    Subject subject = e.getSubject();
		    
		    //TODO améliorer
	//	    System.out.println("addSubject");
		    
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
		/*    Timetable tt = e.getTimetable();
		    EditAdapter ea = (EditAdapter) tree.getModel();
		    ea.fireTreeNodesInserted(ea,
		    		new Object[]{tree.getModel().getRoot(), subject}, 
		    		new int[]{0, 1, 2}, 
		    		new Object[]{TimetableDao.TYPES_COURSES[0], TimetableDao.TYPES_COURSES[1], TimetableDao.TYPES_COURSES[2]});
		  */  
		    
	//	   tree.treeDidChange();
		//    refreshAll(e);

		    tree.updateUI();
		    tree.setVisible(true);
		}
		
		public void modifySubject(BoTEvent e) {
		//    Subject subject = e.getSubject();
		    
		    //TODO améliorer
		//    System.out.println("modSubject");
		    tree.updateUI();
		    tree.setVisible(true);
		}
		
		public void removeSubject(BoTEvent e) {
		//    Subject subject = e.getSubject();
		    
		    //TODO améliorer
		//    System.out.println("delSubject");
		    tree.updateUI();
		    tree.setVisible(true);
		}
	}
}
