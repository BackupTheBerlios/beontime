package fr.umlv.smoreau.beontime.client.graphics.windows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JTree;
import javax.swing.event.EventListenerList;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import fr.umlv.smoreau.beontime.client.graphics.BoTModel;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.client.graphics.event.BoTEvent;
import fr.umlv.smoreau.beontime.client.graphics.event.DefaultBoTListener;
import fr.umlv.smoreau.beontime.dao.DaoManager;
import fr.umlv.smoreau.beontime.dao.UnavailabilityDao;
import fr.umlv.smoreau.beontime.filter.GroupFilter;
import fr.umlv.smoreau.beontime.filter.UnavailabilityFilter;

import fr.umlv.smoreau.beontime.model.Formation;
import fr.umlv.smoreau.beontime.model.Group;
import fr.umlv.smoreau.beontime.model.Unavailability;
import fr.umlv.smoreau.beontime.model.element.Material;
import fr.umlv.smoreau.beontime.model.element.Room;
import fr.umlv.smoreau.beontime.model.user.User;


/**
 * @author BeOnTime
 */
public class ManageUnavailabilitiesAdapter implements TreeModel {
	private BoTModel model;
	private final EventListenerList list;
	private JTree tree;

	private ArrayList listTypeUnavailabilities;

	private ArrayList listFormations = new ArrayList();
	private HashMap listGroups = new HashMap();
	private ArrayList listRooms = new ArrayList();
	private ArrayList listMaterials = new ArrayList();
	private ArrayList listTeachers = new ArrayList();
	private ArrayList listCalendar = new ArrayList();
	
	private HashMap groupsUnavailabilities = new HashMap();
	private HashMap roomsUnavailabilities = new HashMap();
	private HashMap materialsUnavailabilities = new HashMap();
	private HashMap teachersUnavailabilities = new HashMap();


	public ManageUnavailabilitiesAdapter(BoTModel model, JTree t) {
		this.model = model;
		this.list = new EventListenerList();
		this.tree = t;
		
		listTypeUnavailabilities = new ArrayList();
		for(int i = 0; i < UnavailabilityDao.ALL_TYPES.length; ++i) {
		    if (UnavailabilityDao.ALL_TYPES[i].equals(UnavailabilityDao.TYPE_GROUP))
		        listTypeUnavailabilities.add("formation");
		    else
		        listTypeUnavailabilities.add(UnavailabilityDao.ALL_TYPES[i]);
		}
		
		UnavailabilityDao unavailabilityDao = DaoManager.getAvailabilityDao();
		UnavailabilityFilter filter = new UnavailabilityFilter();
		filter.setNotDescription(UnavailabilityDao.AUTO_DESCRIPTION);

		try {
		    Set set = (Set) MainFrame.getInstance().getUserConnected().getFormationsInCharge();
		    for (Iterator i = set.iterator(); i.hasNext(); ) {
		        Formation formation = (Formation) i.next();
		        listFormations.add(formation);
		        
		        GroupFilter f = new GroupFilter();
		        f.setIdFormation(formation.getIdFormation());
		        ArrayList groups = (ArrayList) DaoManager.getGroupDao().getGroups(f);
		        listGroups.put(formation, groups);
		    }

		    filter.setIdUnavailabilityType(unavailabilityDao.getTypeUnavailability(UnavailabilityDao.TYPE_GROUP));
		    ArrayList list = (ArrayList) unavailabilityDao.getUnavailabilities(filter);
		    for (Iterator i = list.iterator(); i.hasNext(); ) {
		        Unavailability tmp = (Unavailability) i.next();
		        ArrayList u = (ArrayList) groupsUnavailabilities.get(tmp.getIdUnavailabilitySubject());
		        if (u == null) {
		            u = new ArrayList();
		            u.add(tmp);
		            groupsUnavailabilities.put(tmp.getIdUnavailabilitySubject(), u);
		        } else {
		            u.add(tmp);
		        }
		    }

		    
		    //listGroups = (ArrayList) unavailabilityDao.getUnavailabilities(filter);
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		try {
		    listTeachers = (ArrayList) DaoManager.getUserDao().getTeachers();
		    filter.setIdUnavailabilityType(unavailabilityDao.getTypeUnavailability(UnavailabilityDao.TYPE_TEACHER));
		    ArrayList list = (ArrayList) unavailabilityDao.getUnavailabilities(filter);
		    for (Iterator i = list.iterator(); i.hasNext(); ) {
		        Unavailability tmp = (Unavailability) i.next();
		        ArrayList u = (ArrayList) teachersUnavailabilities.get(tmp.getIdUnavailabilitySubject());
		        if (u == null) {
		            u = new ArrayList();
		            u.add(tmp);
		            teachersUnavailabilities.put(tmp.getIdUnavailabilitySubject(), u);
		        } else {
		            u.add(tmp);
		        }
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		try {
		    listRooms = (ArrayList) DaoManager.getElementDao().getRooms();
		    filter.setIdUnavailabilityType(unavailabilityDao.getTypeUnavailability(UnavailabilityDao.TYPE_ROOM));
		    ArrayList list = (ArrayList) unavailabilityDao.getUnavailabilities(filter);
		    for (Iterator i = list.iterator(); i.hasNext(); ) {
		        Unavailability tmp = (Unavailability) i.next();
		        ArrayList u = (ArrayList) roomsUnavailabilities.get(tmp.getIdUnavailabilitySubject());
		        if (u == null) {
		            u = new ArrayList();
		            u.add(tmp);
		            roomsUnavailabilities.put(tmp.getIdUnavailabilitySubject(), u);
		        } else {
		            u.add(tmp);
		        }
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		try {
		    listMaterials = (ArrayList) DaoManager.getElementDao().getMaterials();
		    filter.setIdUnavailabilityType(unavailabilityDao.getTypeUnavailability(UnavailabilityDao.TYPE_MATERIAL));
		    ArrayList list = (ArrayList) unavailabilityDao.getUnavailabilities(filter);
		    for (Iterator i = list.iterator(); i.hasNext(); ) {
		        Unavailability tmp = (Unavailability) i.next();
		        ArrayList u = (ArrayList) materialsUnavailabilities.get(tmp.getIdUnavailabilitySubject());
		        if (u == null) {
		            u = new ArrayList();
		            u.add(tmp);
		            materialsUnavailabilities.put(tmp.getIdUnavailabilitySubject(), u);
		        } else {
		            u.add(tmp);
		        }
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		try {
		    filter.setIdUnavailabilityType(unavailabilityDao.getTypeUnavailability(UnavailabilityDao.TYPE_CALENDAR));
		    listCalendar = (ArrayList) unavailabilityDao.getUnavailabilities(filter);
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		model.addBoTListener(new ManageUnavailabilitiesListener());
	}
	
	
	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeModel#getRoot()
	 */
	public Object getRoot() {
		return "";
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeModel#getChild(java.lang.Object, int)
	 */
	public Object getChild(Object parent, int index) {
		// si c'est un type indisponibilité
		if (parent instanceof String) {
			String type = (String) parent;	
			
			// si c'est la racine
			if ("".equals(type))
				return listTypeUnavailabilities.get(index);
			
			// sinon
			else if (UnavailabilityDao.TYPE_TEACHER.equals(type))
			    return listTeachers.get(index);
			else if (UnavailabilityDao.TYPE_ROOM.equals(type))
			    return listRooms.get(index);
			else if (UnavailabilityDao.TYPE_MATERIAL.equals(type))
			    return listMaterials.get(index);
			else if ("formation".equals(type))
			    return listFormations.get(index);
			else if (UnavailabilityDao.TYPE_CALENDAR.equals(type))
			    return listCalendar.get(index);
		}

		else if (parent instanceof User) {
			User user = (User) parent;
			
			ArrayList list = (ArrayList) teachersUnavailabilities.get(user.getIdUser());
			return list.get(index);
		}
		
		else if (parent instanceof Room) {
			Room room = (Room) parent;
			
			ArrayList list = (ArrayList) roomsUnavailabilities.get(room.getIdRoom());
			return list.get(index);
		}
		
		else if (parent instanceof Material) {
		    Material material = (Material) parent;
			
			ArrayList list = (ArrayList) materialsUnavailabilities.get(material.getIdMaterial());
			return list.get(index);
		}
		
		else if (parent instanceof Formation) {
		    Formation formation = (Formation) parent;

		    ArrayList list = (ArrayList) listGroups.get(formation);
			return list.get(index);
		}
		
		else if (parent instanceof Group) {
		    Group group = (Group) parent;
			
			ArrayList list = (ArrayList) groupsUnavailabilities.get(group.getIdGroup());
			return list.get(index);
		}

		return null;
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeModel#getChildCount(java.lang.Object)
	 */
	public int getChildCount(Object parent) {
		// si c'est un type indisponibilité
		if (parent instanceof String) {
			String type = (String) parent;	
			
			// si c'est la racine
			if ("".equals(type))
				return listTypeUnavailabilities.size();
			
			// sinon
			else if (UnavailabilityDao.TYPE_TEACHER.equals(type))
			    return listTeachers.size();
			else if (UnavailabilityDao.TYPE_ROOM.equals(type))
			    return listRooms.size();
			else if (UnavailabilityDao.TYPE_MATERIAL.equals(type))
			    return listMaterials.size();
			else if ("formation".equals(type))
			    return listFormations.size();
			else if (UnavailabilityDao.TYPE_CALENDAR.equals(type))
			    return listCalendar.size();
		}

		else if (parent instanceof User) {
			User user = (User) parent;
			
			ArrayList list = (ArrayList) teachersUnavailabilities.get(user.getIdUser());
			if (list == null)
			    return 0;
			return list.size();
		}
		
		else if (parent instanceof Room) {
			Room room = (Room) parent;
			
			ArrayList list = (ArrayList) roomsUnavailabilities.get(room.getIdRoom());
			if (list == null)
			    return 0;
			return list.size();
		}
		
		else if (parent instanceof Material) {
		    Material material = (Material) parent;
			
			ArrayList list = (ArrayList) materialsUnavailabilities.get(material.getIdMaterial());
			if (list == null)
			    return 0;
			return list.size();
		}
		
		else if (parent instanceof Formation) {
		    Formation formation = (Formation) parent;

		    ArrayList list = (ArrayList) listGroups.get(formation);
			if (list == null)
			    return 0;
			return list.size();
		}
		
		else if (parent instanceof Group) {
		    Group group = (Group) parent;
			
			ArrayList list = (ArrayList) groupsUnavailabilities.get(group.getIdGroup());
			if (list == null)
			    return 0;
			return list.size();
		}
		
		return 0;
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.tree.TreeModel#isLeaf(java.lang.Object)
	 */
	public boolean isLeaf(Object node) {
		if (node instanceof Unavailability)
			return true;
		return false;
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
	
	
	private class ManageUnavailabilitiesListener extends DefaultBoTListener {
		public void addUnavailability(BoTEvent e) {
		    Unavailability unavailability = e.getUnavailability();
		    if (UnavailabilityDao.TYPE_CALENDAR.equals(unavailability.getIdUnavailabilityType().getNameUnavailabilityType()))
		        listCalendar.add(unavailability);
		    else if (UnavailabilityDao.TYPE_ROOM.equals(unavailability.getIdUnavailabilityType().getNameUnavailabilityType())) {
		        ArrayList u = (ArrayList) roomsUnavailabilities.get(unavailability.getIdUnavailabilitySubject());
		        if (u == null) {
		            u = new ArrayList();
		            u.add(unavailability);
		            roomsUnavailabilities.put(unavailability.getIdUnavailabilitySubject(), u);
		        } else {
		            u.add(unavailability);
		        }
		    } else if (UnavailabilityDao.TYPE_MATERIAL.equals(unavailability.getIdUnavailabilityType().getNameUnavailabilityType())) {
		        ArrayList u = (ArrayList) materialsUnavailabilities.get(unavailability.getIdUnavailabilitySubject());
		        if (u == null) {
		            u = new ArrayList();
		            u.add(unavailability);
		            materialsUnavailabilities.put(unavailability.getIdUnavailabilitySubject(), u);
		        } else {
		            u.add(unavailability);
		        }
		    } else if (UnavailabilityDao.TYPE_TEACHER.equals(unavailability.getIdUnavailabilityType().getNameUnavailabilityType())) {
		        ArrayList u = (ArrayList) teachersUnavailabilities.get(unavailability.getIdUnavailabilitySubject());
		        if (u == null) {
		            u = new ArrayList();
		            u.add(unavailability);
		            teachersUnavailabilities.put(unavailability.getIdUnavailabilitySubject(), u);
		        } else {
		            u.add(unavailability);
		        }
		    } else if (UnavailabilityDao.TYPE_GROUP.equals(unavailability.getIdUnavailabilityType().getNameUnavailabilityType())) {
		        ArrayList u = (ArrayList) groupsUnavailabilities.get(unavailability.getIdUnavailabilitySubject());
		        if (u == null) {
		            u = new ArrayList();
		            u.add(unavailability);
		            groupsUnavailabilities.put(unavailability.getIdUnavailabilitySubject(), u);
		        } else {
		            u.add(unavailability);
		        }
		    }

		    //TODO améliorer
		    tree.updateUI();
		    tree.setVisible(true);
		}
		
		public void modifyUnavailability(BoTEvent e) {
		    //TODO améliorer
		    tree.updateUI();
		    tree.setVisible(true);
		}
		
		public void removeUnavailability(BoTEvent e) {
		    Unavailability unavailability = e.getUnavailability();
		    if (UnavailabilityDao.TYPE_CALENDAR.equals(unavailability.getIdUnavailabilityType().getNameUnavailabilityType()))
		        listCalendar.remove(unavailability);
		    else if (UnavailabilityDao.TYPE_ROOM.equals(unavailability.getIdUnavailabilityType().getNameUnavailabilityType())) {
		        ArrayList u = (ArrayList) roomsUnavailabilities.get(unavailability.getIdUnavailabilitySubject());
		        if (u != null)
		            u.remove(unavailability);
		    } else if (UnavailabilityDao.TYPE_MATERIAL.equals(unavailability.getIdUnavailabilityType().getNameUnavailabilityType())) {
		        ArrayList u = (ArrayList) materialsUnavailabilities.get(unavailability.getIdUnavailabilitySubject());
		        if (u != null)
		            u.remove(unavailability);
		    } else if (UnavailabilityDao.TYPE_TEACHER.equals(unavailability.getIdUnavailabilityType().getNameUnavailabilityType())) {
		        ArrayList u = (ArrayList) teachersUnavailabilities.get(unavailability.getIdUnavailabilitySubject());
		        if (u != null)
		            u.remove(unavailability);
		    } else if (UnavailabilityDao.TYPE_GROUP.equals(unavailability.getIdUnavailabilityType().getNameUnavailabilityType())) {
		        ArrayList u = (ArrayList) groupsUnavailabilities.get(unavailability.getIdUnavailabilitySubject());
		        if (u != null)
		            u.remove(unavailability);
		    }

		    //TODO améliorer
		    tree.updateUI();
		    tree.setVisible(true);
		}
	}
}

