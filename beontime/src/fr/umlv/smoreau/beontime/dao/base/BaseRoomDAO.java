package fr.umlv.smoreau.beontime.dao.base;

import net.sf.hibernate.Hibernate;
import net.sf.hibernate.Session;
import fr.umlv.smoreau.beontime.dao._RootDAO;

/**
 * This class has been automatically generated by Hibernate Synchronizer.
 * For more information or documentation, visit The Hibernate Synchronizer page
 * at http://www.binamics.com/hibernatesync or contact Joe Hudson at joe@binamics.com.
 *
 * This is an automatically generated DAO class which should not be edited.
 */
public class BaseRoomDAO extends _RootDAO {

	public static BaseRoomDAO instance;

	/**
	 * Return a singleton of the DAO
	 */
	public static BaseRoomDAO getInstance () {
		if (null == instance) instance = new BaseRoomDAO();
		return instance;
	}

	/**
	 * fr.umlv.smoreau.beontime.model.element.dao._RootDAO _RootDAO.getReferenceClass()
	 */
	public Class getReferenceClass () {
		return fr.umlv.smoreau.beontime.model.element.Room.class;
	}
	
	public fr.umlv.smoreau.beontime.model.element.Room load(java.lang.Long key)
		throws net.sf.hibernate.HibernateException {
		return (fr.umlv.smoreau.beontime.model.element.Room) load(getReferenceClass(), key);
	}

	public fr.umlv.smoreau.beontime.model.element.Room load(java.lang.Long key, Session s)
		throws net.sf.hibernate.HibernateException {
		return (fr.umlv.smoreau.beontime.model.element.Room) load(getReferenceClass(), key, s);
	}

	public fr.umlv.smoreau.beontime.model.element.Room loadInitialize(java.lang.Long key, Session s) 
			throws net.sf.hibernate.HibernateException { 
		fr.umlv.smoreau.beontime.model.element.Room obj = load(key, s); 
		if (!Hibernate.isInitialized(obj)) {
			Hibernate.initialize(obj);
		} 
		return obj; 
	}

	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
	 * of the identifier property if the assigned generator is used.) 
	 * @param room a transient instance of a persistent class 
	 * @return the class identifier
	 */
	public java.lang.Long save(fr.umlv.smoreau.beontime.model.element.Room room)
		throws net.sf.hibernate.HibernateException {
		return (java.lang.Long) super.save(room);
	}

	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
	 * of the identifier property if the assigned generator is used.) 
	 * Use the Session given.
	 * @param room a transient instance of a persistent class
	 * @param s the Session
	 * @return the class identifier
	 */
	public java.lang.Long save(fr.umlv.smoreau.beontime.model.element.Room room, Session s)
		throws net.sf.hibernate.HibernateException {
		return (java.lang.Long) super.save(room, s);
	}

	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default
	 * the instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the
	 * identifier property mapping. 
	 * @param room a transient instance containing new or updated state 
	 */
	public void saveOrUpdate(fr.umlv.smoreau.beontime.model.element.Room room)
		throws net.sf.hibernate.HibernateException {
		super.saveOrUpdate(room);
	}

	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default the
	 * instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the identifier
	 * property mapping. 
	 * Use the Session given.
	 * @param room a transient instance containing new or updated state.
	 * @param s the Session.
	 */
	public void saveOrUpdate(fr.umlv.smoreau.beontime.model.element.Room room, Session s)
		throws net.sf.hibernate.HibernateException {
		super.saveOrUpdate(room, s);
	}

	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * @param room a transient instance containing updated state
	 */
	public void update(fr.umlv.smoreau.beontime.model.element.Room room) 
		throws net.sf.hibernate.HibernateException {
		super.update(room);
	}

	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * Use the Session given.
	 * @param room a transient instance containing updated state
	 * @param the Session
	 */
	public void update(fr.umlv.smoreau.beontime.model.element.Room room, Session s)
		throws net.sf.hibernate.HibernateException {
		super.update(room, s);
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * @param id the instance ID to be removed
	 */
	public void delete(java.lang.Long id)
		throws net.sf.hibernate.HibernateException {
		super.delete(load(id));
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * Use the Session given.
	 * @param id the instance ID to be removed
	 * @param s the Session
	 */
	public void delete(java.lang.Long id, Session s)
		throws net.sf.hibernate.HibernateException {
		super.delete(load(id, s), s);
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * @param room the instance to be removed
	 */
	public void delete(fr.umlv.smoreau.beontime.model.element.Room room)
		throws net.sf.hibernate.HibernateException {
		super.delete(room);
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * Use the Session given.
	 * @param room the instance to be removed
	 * @param s the Session
	 */
	public void delete(fr.umlv.smoreau.beontime.model.element.Room room, Session s)
		throws net.sf.hibernate.HibernateException {
		super.delete(room, s);
	}
	
	/**
	 * Re-read the state of the given instance from the underlying database. It is inadvisable to use this to implement
	 * long-running sessions that span many business tasks. This method is, however, useful in certain special circumstances.
	 * For example 
	 * <ul> 
	 * <li>where a database trigger alters the object state upon insert or update</li>
	 * <li>after executing direct SQL (eg. a mass update) in the same session</li>
	 * <li>after inserting a Blob or Clob</li>
	 * </ul>
	 */
	public void refresh (fr.umlv.smoreau.beontime.model.element.Room room, Session s)
		throws net.sf.hibernate.HibernateException {
		super.refresh(room, s);
	}

    public String getDefaultOrderProperty () {
		return null;
    }

}