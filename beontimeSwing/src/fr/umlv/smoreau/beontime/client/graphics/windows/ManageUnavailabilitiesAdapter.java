package fr.umlv.smoreau.beontime.client.graphics.windows;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.JTree;
import javax.swing.event.EventListenerList;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import fr.umlv.smoreau.beontime.client.graphics.BoTModel;
import fr.umlv.smoreau.beontime.client.graphics.event.DefaultBoTListener;
import fr.umlv.smoreau.beontime.dao.UnavailabilityDao;

import fr.umlv.smoreau.beontime.model.Unavailability;
import fr.umlv.smoreau.beontime.model.UnavailabilityType;
import fr.umlv.smoreau.beontime.model.element.Material;
import fr.umlv.smoreau.beontime.model.element.Room;
import fr.umlv.smoreau.beontime.model.timetable.Course;
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
	private ArrayList listUnavailabilities = new ArrayList();
	private ArrayList listRooms = new ArrayList();
	private ArrayList listMaterials = new ArrayList();
	private ArrayList listTeachers = new ArrayList();
	// fin TODO
	
	
	public ManageUnavailabilitiesAdapter(BoTModel model, JTree t) {
		this.model = model;
		this.list = new EventListenerList();
		this.tree = t;
		
		String[] tabTypesUnavailabilities = UnavailabilityDao.ALL_TYPES;
		
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
		u2.setNameUnavailabilityType(UnavailabilityDao.TYPE_COURSE);
		
		UnavailabilityType u3 = new UnavailabilityType(new Long(3));
		u3.setNameUnavailabilityType(UnavailabilityDao.TYPE_MATERIAL);
		
		UnavailabilityType u4 = new UnavailabilityType(new Long(4));
		u4.setNameUnavailabilityType(UnavailabilityDao.TYPE_ROOM);
		
		UnavailabilityType u5= new UnavailabilityType(new Long(5));
		u5.setNameUnavailabilityType(UnavailabilityDao.TYPE_GROUP);
		
		UnavailabilityType u6 = new UnavailabilityType(new Long(3));
		u6.setNameUnavailabilityType(UnavailabilityDao.TYPE_TEACHER);
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
		
		listUnavailabilities.add(i1);
		listUnavailabilities.add(i2);
		listUnavailabilities.add(i3);
		listUnavailabilities.add(i4);
		listUnavailabilities.add(i5);
		listUnavailabilities.add(i6);
		
		
		
		//ROOM
		Room room1 = new Room(new Long(1)); 
		room1.setName("2b104");
		room1.setDescription("Description1");
		
		Room room2 = new Room(new Long(2)); 
		room2.setName("3b114/116");
		room2.setDescription("Description2");
		
		listRooms.add(room1);
		listRooms.add(room2);
		
		
		//MATERIAL
		Material material1 = new Material(new Long(1)); 
		material1.setName("Materiel1");
		material1.setDescription("Vidéoprojecteur");
		
		Material material2 = new Material(new Long(2)); 
		material2.setName("Materiel2");
		material2.setDescription("Rétroprojecteur");
		
		listMaterials.add(material1);
		listMaterials.add(material2);
		
		
		//USERS
		User user1 = new User(new Long(1), "enseignant"); 
		user1.setName("Nom1");
		user1.setFirstName("prenom1");
		user1.setEMail("user1@yahoo.fr");
		
		User user2 = new User(new Long(2), "enseignant"); 
		user2.setName("Nom2");
		user2.setFirstName("prenom2");
		user2.setEMail("user2@yahoo.fr");
		
		listTeachers.add(user1);
		listTeachers.add(user2);
		
		//fin TODO
		
		model.addBoTListener(new TreeListener(this));
	}
	
	
	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeModel#getRoot()
	 */
	public Object getRoot() {
		
		return "";
		//return listTypeUnavailabilities;
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeModel#getChild(java.lang.Object, int)
	 */
	public Object getChild(Object parent, int index) {
		
		//Si c'est la liste des type
		/*if (parent instanceof ArrayList) {
			
			ArrayList list = (ArrayList) parent;		
			
			return UnavailabilityDao.ALL_TYPES[index];
			
		}*/	
		
		//Si c'est un type indisponibilité
		if (parent instanceof String) {
			
			String type = (String) parent;	
			
			//Si c'est la racine
			if (type.compareTo("") == 0) {
				return UnavailabilityDao.ALL_TYPES[index];
			}
			
			else if (type.compareTo(UnavailabilityDao.TYPE_TEACHER) == 0){
				
				//On parcours la base des indisponibilités
				//On réccupère l'identificateur du sujet des indisponibilités de type enseignant
				//ArrayList list = getIDSubjectUnavailabilityType(listUnavailabilities, UnavailabilityDao.TYPE_TEACHER);
				HashSet set = getIDSubjectUnavailabilityType(listUnavailabilities, UnavailabilityDao.TYPE_TEACHER);
				
				ArrayList list2 = new ArrayList();
				//On parcours la base des enseignant
				//Si l'identificateur d'un enseignant est égale a un des identificateurs de la
				//liste remplie précédemment on l'ajoute  la nouvelle liste
				for(Iterator it = listTeachers.iterator();it.hasNext();) {
					User user = (User) it.next();
					
					for(Iterator it2 = set.iterator();it2.hasNext();) {
						if( user.getIdUser().compareTo((Long) it2.next()) == 0)
							list2.add(user);	
					}
					
				}
				
				return list2.toArray() [index];
			}
			
			/*else if (type.compareTo(UnavailabilityDao.TYPE_STUDENT) == 0){
			 
			 //Meme traitement que pour les autres, mais j'ai pas mis d'étudiant pour le test en local
			  }
			  
			  else if (type.compareTo(UnavailabilityDao.TYPE_COURSE) == 0){
			  //Meme traitement que pour les autres, mais j'ai pas mis d'étudiant pour le test en local
			   }*/
			
			else if (type.compareTo(UnavailabilityDao.TYPE_MATERIAL) == 0){
				//On parcours la base des indisponibilités
				//On réccupère l'identificateur du sujet des indisponibilités de type materiel
				//ArrayList list = getIDSubjectUnavailabilityType(listUnavailabilities, UnavailabilityDao.TYPE_MATERIAL);
				HashSet set = getIDSubjectUnavailabilityType(listUnavailabilities, UnavailabilityDao.TYPE_MATERIAL);
				
				ArrayList list2 = new ArrayList();
				//On parcours la base contenant les materiels
				//Si l'identificateur d'un materiel est égale a un des identificateurs de la
				//liste remplie précédemment on l'ajoute  la nouvelle liste
				for(Iterator it = listMaterials.iterator();it.hasNext();) {
					Material material = (Material) it.next();
					
					for(Iterator it2 = set.iterator();it2.hasNext();) {
						if( material.getIdMaterial().compareTo((Long) it2.next()) == 0)
							list2.add(material);	
					}
					
				}
				
				return list2.toArray() [index];
			}
			
			else if (type.compareTo(UnavailabilityDao.TYPE_ROOM) == 0){
				//			On parcours la base des indisponibilités
				//On réccupère l'identificateur du sujet des indisponibilités de type materiel
				//ArrayList list = getIDSubjectUnavailabilityType(listUnavailabilities, UnavailabilityDao.TYPE_ROOM);
				HashSet set = getIDSubjectUnavailabilityType(listUnavailabilities, UnavailabilityDao.TYPE_ROOM);
				
				ArrayList list2 = new ArrayList();
				//On parcours la base des locaux
				//Si l'identificateur d'un materiel est égale a un des identificateurs de la
				//liste remplie précédemment on l'ajoute  la nouvelle liste
				for(Iterator it = listRooms.iterator();it.hasNext();) {
					Room room = (Room) it.next();
					
					for(Iterator it2 = set.iterator();it2.hasNext();) {
						if( room.getIdRoom().compareTo((Long) it2.next()) == 0)
							list2.add(room);	
					}
					
				}
				
				return list2.toArray() [index];
			}
			
			/*else if (type.compareTo(UnavailabilityDao.TYPE_CALENDAR) == 0){
			 //Meme traitement que pour les autres, mais j'ai pas mis d'étudiant pour le test en local
			  }*/
		}
		
		
		else if (parent instanceof User) {
			
			User user = (User) parent;
			
			//On parcours la base des indisponibilités
			//On réccupère l'identificateur des indisponibilités du type de l'utilisateur considéré
			//qui ont pour identificateur du sujet l'identifiant de l'utilisateur considéré
			ArrayList list = getIDSubjectUnavailabilityType(listUnavailabilities, user.getUserType(), user.getIdUser());
			return list.toArray() [index];
			
		}
		
		else if (parent instanceof Course) {
			
			Course course = (Course) parent;
			
			ArrayList list = getIDSubjectUnavailabilityType(listUnavailabilities, UnavailabilityDao.TYPE_COURSE, course.getIdCourse());
			return list.toArray() [index];
			
		}
		
		else if (parent instanceof Material) {
			
			 Material material = (Material) parent;
			
			ArrayList list = getIDSubjectUnavailabilityType(listUnavailabilities, UnavailabilityDao.TYPE_MATERIAL, material.getIdMaterial());
			return list.toArray() [index];
			
		}
		
		else if (parent instanceof Room) {
			
			Room room = (Room) parent;
			
			ArrayList list = getIDSubjectUnavailabilityType(listUnavailabilities, UnavailabilityDao.TYPE_ROOM, room.getIdRoom());
			return list.toArray() [index];
			
		}
		
		//TODO Il faut metre ici la condition concernant les indisponibilité sur calendrier
		
		return null;
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeModel#getChildCount(java.lang.Object)
	 */
	public int getChildCount(Object parent) {
		
		/*//	Si c'est la liste des type
		if (parent instanceof ArrayList) {
			
			ArrayList list = (ArrayList) parent;		
			return 6;
			
		}*/	
		
		//Si c'est un type indisponibilité
		if (parent instanceof String) {
			
			String type = (String) parent;	
			
			//Si c'est la racine
			if (type.compareTo("") == 0) {
				return 6;
			}
			
			else if (type.compareTo(UnavailabilityDao.TYPE_TEACHER) == 0){
				
				//On parcours la base des indisponibilités
				//On réccupère l'identificateur du sujet des indisponibilités de type enseignant
				//ArrayList list = getIDSubjectUnavailabilityType(listUnavailabilities, UnavailabilityDao.TYPE_TEACHER);
				HashSet set = getIDSubjectUnavailabilityType(listUnavailabilities, UnavailabilityDao.TYPE_TEACHER);
				
				ArrayList list2 = new ArrayList();
				//On parcours la base des enseignant
				//Si l'identificateur d'un enseignant est égale a un des identificateurs de la
				//liste remplie précédemment on l'ajoute  la nouvelle liste
				for(Iterator it = listTeachers.iterator();it.hasNext();) {
					User user = (User) it.next();
					
					for(Iterator it2 = set.iterator();it2.hasNext();) {
						if( user.getIdUser().compareTo((Long) it2.next()) == 0)
							list2.add(user);	
					}
					
				}
				
				return list2.size();
			}
			
			/*else if (type.compareTo(UnavailabilityDao.TYPE_STUDENT) == 0){
			 
			 
			 }
			 
			 else if (type.compareTo(UnavailabilityDao.TYPE_COURSE) == 0){
			 
			 }*/
			
			else if (type.compareTo(UnavailabilityDao.TYPE_MATERIAL) == 0){
				//On parcours la base des indisponibilités
				//On réccupère l'identificateur du sujet des indisponibilités de type materiel
				//ArrayList list = getIDSubjectUnavailabilityType(listUnavailabilities, UnavailabilityDao.TYPE_MATERIAL);
				HashSet set = getIDSubjectUnavailabilityType(listUnavailabilities, UnavailabilityDao.TYPE_MATERIAL);
				
				ArrayList list2 = new ArrayList();
				//On parcours la base contenant les materiels
				//Si l'identificateur d'un materiel est égale a un des identificateurs de la
				//liste remplie précédemment on l'ajoute  la nouvelle liste
				for(Iterator it = listMaterials.iterator();it.hasNext();) {
					Material material = (Material) it.next();
					
					for(Iterator it2 = set.iterator();it2.hasNext();) {
						if( material.getIdMaterial().compareTo((Long) it2.next()) == 0)
							list2.add(material);	
					}
					
				}
				
				return list2.size();
			}
			
			else if (type.compareTo(UnavailabilityDao.TYPE_ROOM) == 0){
				//			On parcours la base des indisponibilités
				//On réccupère l'identificateur du sujet des indisponibilités de type materiel
				//ArrayList list = getIDSubjectUnavailabilityType(listUnavailabilities, UnavailabilityDao.TYPE_ROOM);
				HashSet set = getIDSubjectUnavailabilityType(listUnavailabilities, UnavailabilityDao.TYPE_ROOM);
				
				ArrayList list2 = new ArrayList();
				//On parcours la base des locaux
				//Si l'identificateur d'un materiel est égale a un des identificateurs de la
				//liste remplie précédemment on l'ajoute  la nouvelle liste
				for(Iterator it = listRooms.iterator();it.hasNext();) {
					Room room = (Room) it.next();
					
					for(Iterator it2 = set.iterator();it2.hasNext();) {
						if( room.getIdRoom().compareTo((Long) it2.next()) == 0)
							list2.add(room);	
					}
					
				}
				
				return list2.size();
			}
			
			/*else if (type.compareTo(UnavailabilityDao.TYPE_CALENDAR) == 0){
			 
			 }*/
		}
		
		else if (parent instanceof User) {
			
			User user = (User) parent;
			
			//On parcours la base des indisponibilités
			//On réccupère l'identificateur des indisponibilités du type de l'utilisateur considéré
			//qui ont pour identificateur du sujet l'identifiant de l'utilisateur considéré
			ArrayList list = getIDSubjectUnavailabilityType(listUnavailabilities, user.getUserType(), user.getIdUser());
			return list.size();
			
		}
		
		else if (parent instanceof Course) {
			
			Course course = (Course) parent;
			
			ArrayList list = getIDSubjectUnavailabilityType(listUnavailabilities, UnavailabilityDao.TYPE_COURSE, course.getIdCourse());
			return list.size();
			
		}
		
		else if (parent instanceof Material) {
			
			 Material material = (Material) parent;
			
			ArrayList list = getIDSubjectUnavailabilityType(listUnavailabilities, UnavailabilityDao.TYPE_MATERIAL, material.getIdMaterial());
			return list.size();
			
		}
		
		else if (parent instanceof Room) {
			
			Room room = (Room) parent;
			
			ArrayList list = getIDSubjectUnavailabilityType(listUnavailabilities, UnavailabilityDao.TYPE_ROOM, room.getIdRoom());
			return list.size();
			
		}
		
		//TODO Il faut metre ici la condition concernant les indisponibilité sur calendrier
		
		return 0;
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeModel#isLeaf(java.lang.Object)
	 */
	public boolean isLeaf(Object node) {
		if (node instanceof String || node instanceof User || node instanceof Material || node instanceof Room /*|| TODO pour le  type calendrier*/)
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
	
	
	/*private ArrayList getIDSubjectUnavailabilityType(ArrayList listUnavailabilities, String type) {
	 
	 ArrayList list = new ArrayList();
	 
	 for(Iterator it = listUnavailabilities.iterator();it.hasNext();) {
	 Unavailability u = (Unavailability) it.next();
	 System.out.println(u.getIdUnavailabilityType().getNameUnavailabilityType());
	 if(u.getIdUnavailabilityType().getNameUnavailabilityType().compareTo(type) == 0)
	 list.add(u.getIdUnavailabilitySubject());
	 }
	 
	 return list;
	 }*/
	
	private HashSet getIDSubjectUnavailabilityType(ArrayList listUnavailabilities, String type) {
		
		//ArrayList list = new ArrayList();
		HashSet set = new HashSet();
		
		for(Iterator it = listUnavailabilities.iterator();it.hasNext();) {
			
			Unavailability u = (Unavailability) it.next();
			
			if(u.getIdUnavailabilityType().getNameUnavailabilityType().compareTo(type) == 0)
				set.add(u.getIdUnavailabilitySubject());
		}
		
		return set;
	}
	
	
	private ArrayList getIDSubjectUnavailabilityType(ArrayList listUnavailabilities, String type, Long idSubject) {
		
		ArrayList list = new ArrayList();
		
		for(Iterator it = listUnavailabilities.iterator();it.hasNext();) {
			
			Unavailability u = (Unavailability) it.next();
			
			if(u.getIdUnavailabilityType().getNameUnavailabilityType().compareTo(type) == 0)
				if(u.getIdUnavailabilitySubject().compareTo(idSubject) == 0)
					list.add(u);
				
		}
		
		return list;
	}
}

