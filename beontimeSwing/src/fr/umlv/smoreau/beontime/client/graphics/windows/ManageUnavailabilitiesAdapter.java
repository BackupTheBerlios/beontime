/*
 * Created on 28 févr. 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package fr.umlv.smoreau.beontime.client.graphics.windows;

import java.util.ArrayList;

import javax.swing.JTree;
import javax.swing.event.EventListenerList;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import fr.umlv.smoreau.beontime.client.graphics.BoTModel;
import fr.umlv.smoreau.beontime.client.graphics.event.DefaultBoTListener;
import fr.umlv.smoreau.beontime.model.Formation;
import fr.umlv.smoreau.beontime.model.Unavailability;
import fr.umlv.smoreau.beontime.model.element.Material;
import fr.umlv.smoreau.beontime.model.element.Room;
import fr.umlv.smoreau.beontime.model.timetable.Subject;
import fr.umlv.smoreau.beontime.model.timetable.Timetable;
import fr.umlv.smoreau.beontime.model.user.User;

/**
 * @author sandrine
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ManageUnavailabilitiesAdapter implements TreeModel {

	
	private BoTModel model;
	private final EventListenerList list;
	private JTree tree;

	private ArrayList listTypeUnavailabilities = new ArrayList();
	
	
	public ManageUnavailabilitiesAdapter(BoTModel model, JTree t) {
		this.model = model;
		this.list = new EventListenerList();
		this.tree = t;
		
		listTypeUnavailabilities = new ArrayList();
		listTypeUnavailabilities.add("Calendrier");
		listTypeUnavailabilities.add("Cours");
		listTypeUnavailabilities.add("Enseignant");
		listTypeUnavailabilities.add("Etudiant");
		listTypeUnavailabilities.add("Local");
		listTypeUnavailabilities.add("Materiel");
		
		
		//TODO pour tester en local
		Unavailability i1 = new Unavailability();
		i1.setIdUnavailability(new Long(1));
		i1.setDescription("occupé");
		
		Unavailability i2 = new Unavailability();
		i2.setIdUnavailability(new Long(2));
		i2.setDescription("occupé");
		
		Unavailability i3 = new Unavailability();
		i3.setIdUnavailability(new Long(3));
		i3.setDescription("réservé");
		
		Unavailability i4 = new Unavailability();
		i4.setIdUnavailability(new Long(4));
		i4.setDescription("volé");
		
		Unavailability i5 = new Unavailability();
		i5.setIdUnavailability(new Long(5));
		i5.setDescription("autre cours");
		
		Unavailability i6 = new Unavailability();
		i6.setIdUnavailability(new Long(6));
		i6.setDescription("en maladie");
		
		
		
		//ROOM
		Room room1 = new Room(new Long(1)); 
		room1.setName("Nom1");
		room1.setDescription("Description1");
		
		
		Room room2 = new Room(new Long(2)); 
		room2.setName("Nom2");
		room2.setDescription("Description2");
		
		//MATERIAL
		Material material1 = new Material(new Long(1)); 
		material1.setName("Nom1");
		material1.setDescription("Vidéoprojecteur");
		
		Material material2 = new Material(new Long(2)); 
		material2.setName("Nom2");
		material2.setDescription("Rétroprojecteur");
		
		//USERS
		User user1 = new User(new Long(1), "enseignant"); 
		user1.setName("Nom1");
		user1.setFirstName("prenom1");
		user1.setEMail("user1@yahoo.fr");
		
		User user2 = new User(new Long(2), "enseignant"); 
		user2.setName("Nom2");
		user2.setFirstName("prenom2");
		user2.setEMail("user2@yahoo.fr");
		
		
		//finTODO

		model.addBoTListener(new TreeListener(this));
	}

	
	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeModel#getRoot()
	 */
	public Object getRoot() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeModel#getChild(java.lang.Object, int)
	 */
	public Object getChild(Object parent, int index) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeModel#getChildCount(java.lang.Object)
	 */
	public int getChildCount(Object parent) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeModel#isLeaf(java.lang.Object)
	 */
	public boolean isLeaf(Object node) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeModel#valueForPathChanged(javax.swing.tree.TreePath, java.lang.Object)
	 */
	public void valueForPathChanged(TreePath path, Object newValue) {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeModel#removeTreeModelListener(javax.swing.event.TreeModelListener)
	 */
	public void removeTreeModelListener(TreeModelListener l) {
		// TODO Auto-generated method stub

	}


	private class TreeListener extends DefaultBoTListener {
		private TreeModel source;

		public TreeListener(TreeModel source) {
			this.source = source;
		}	
	}
}
