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
import fr.umlv.smoreau.beontime.dao.TimetableDao;
import fr.umlv.smoreau.beontime.dao.UnavailabilityDao;
import fr.umlv.smoreau.beontime.model.Formation;

import fr.umlv.smoreau.beontime.model.Unavailability;
import fr.umlv.smoreau.beontime.model.UnavailabilityType;
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
	
	//TODO pour tester en local
	private ArrayList listRooms = new ArrayList();
	private ArrayList listTMaterials = new ArrayList();
	private ArrayList listUsers = new ArrayList();
	// fin TODO
	
	
	public ManageUnavailabilitiesAdapter(BoTModel model, JTree t) {
		this.model = model;
		this.list = new EventListenerList();
		this.tree = t;
		
		String [] tabTypesUnavailabilities = UnavailabilityDao.ALL_TYPES;
		
		listTypeUnavailabilities = new ArrayList();
		
		for(int i=0; i<tabTypesUnavailabilities.length;i++) {
			listTypeUnavailabilities.add(tabTypesUnavailabilities[i]);
		}
		

		//TODO pour tester en local
		//Ce qui est supposé se trouver dans la table UnavailabilityType
		//de la base
		UnavailabilityType u1 = new UnavailabilityType(new Long(1));
		u1.setNameUnavailabilityType(UnavailabilityDao.TYPE_CALENDAR);
		
		UnavailabilityType u2 = new UnavailabilityType(new Long(2));
		u1.setNameUnavailabilityType(UnavailabilityDao.TYPE_COURSE);
		
		UnavailabilityType u3 = new UnavailabilityType(new Long(3));
		u1.setNameUnavailabilityType(UnavailabilityDao.TYPE_MATERIAL);
		
		UnavailabilityType u4 = new UnavailabilityType(new Long(4));
		u1.setNameUnavailabilityType(UnavailabilityDao.TYPE_ROOM);
		
		UnavailabilityType u5= new UnavailabilityType(new Long(5));
		u1.setNameUnavailabilityType(UnavailabilityDao.TYPE_STUDENT);
		
		UnavailabilityType u6 = new UnavailabilityType(new Long(3));
		u1.setNameUnavailabilityType(UnavailabilityDao.TYPE_TEACHER);
		// Fin de ce qui est sencé se trouver dans la base
		
		
		Unavailability i1 = new Unavailability();
		i1.setIdUnavailability(new Long(1));
		i1.setDescription("occupé");
		i1.setIdUnavailabilitySubject(new Long(1));
		i1.setIdUnavailabilityType(u4);
		
		
		Unavailability i2 = new Unavailability();
		i2.setIdUnavailability(new Long(2));
		i2.setDescription("occupé");
		i2.setIdUnavailabilitySubject(new Long(2));
		i2.setIdUnavailabilityType(u4);
		
		
		
		Unavailability i3 = new Unavailability();
		i3.setIdUnavailability(new Long(3));
		i3.setDescription("réservé");
		i3.setIdUnavailabilitySubject(new Long(1));
		i3.setIdUnavailabilityType(u3);
		
		
		Unavailability i4 = new Unavailability();
		i4.setIdUnavailability(new Long(4));
		i4.setDescription("volé");
		i4.setIdUnavailabilitySubject(new Long(2));
		i4.setIdUnavailabilityType(u3);
		
		
		Unavailability i5 = new Unavailability();
		i5.setIdUnavailability(new Long(5));
		i5.setDescription("autre cours");
		i5.setIdUnavailabilitySubject(new Long(1));
		i5.setIdUnavailabilityType(u6);
		
	
		Unavailability i6 = new Unavailability();
		i6.setIdUnavailability(new Long(6));
		i6.setDescription("en maladie");
		i6.setIdUnavailabilitySubject(new Long(2));
		i6.setIdUnavailabilityType(u6);
		
		
		
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
		
		
		//fin TODO

		model.addBoTListener(new TreeListener(this));
	}

	
	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeModel#getRoot()
	 */
	public Object getRoot() {
		
		return listTypeUnavailabilities;
	}

	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeModel#getChild(java.lang.Object, int)
	 */
	public Object getChild(Object parent, int index) {
		/* if (parent instanceof ArrayList) {
	         ArrayList list = (ArrayList) parent;
	        Collection c = timetable.getSubjects();
	        return c.toArray()[n];
	    } else if (parent instanceof Subject) {
	        return TimetableDao.TYPES_COURSES[n];
	    }
		return null;enerated method stub*/
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
