package fr.umlv.smoreau.beontime.dao;
/* DESS CRI - BeOnTime - timetable project */

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

import fr.umlv.smoreau.beontime.Hibernate;
import fr.umlv.smoreau.beontime.TransactionManager;
import fr.umlv.smoreau.beontime.filter._BelongStudentGroupFilter;
import fr.umlv.smoreau.beontime.filter.GroupFilter;
import fr.umlv.smoreau.beontime.filter.UserFilter;
import fr.umlv.smoreau.beontime.model.Group;
import fr.umlv.smoreau.beontime.model.association.BelongStudentGroup;
import fr.umlv.smoreau.beontime.model.user.User;

/**
 * RMI implementation of the Group DAO
 * @author BeOnTime team
 */
public class GroupDaoImpl extends Dao implements GroupDao {
	//TODO en cas de modif refaire le rmic et rebalancer coté client
	/** This class has to be serialisable */
	private static final long serialVersionUID = 1L;

    private static GroupDao INSTANCE;
    static {
    	try {
			INSTANCE = new GroupDaoImpl();
		} catch (RemoteException e) {
			System.err.println("problème RMI à l'instanciation du Group DAO");
			//TODO gerer
		}
    }
    
    private static final String TABLE        = "Group";
    private static final String TABLE_BELONG = "BelongStudentGroup";
    
    private GroupDaoImpl() throws RemoteException {
    }

    public static GroupDao getInstance() {
        return INSTANCE;
    }


	public Collection getGroups(GroupFilter filter) throws RemoteException, HibernateException {
	    Session session = null;
        try {
            session = Hibernate.getCurrentSession();
            Collection c = get(TABLE, filter, session);
            UserDao userDao = UserDaoImpl.getInstance();
            for (Iterator i = c.iterator(); i.hasNext(); ) {
                Group group = (Group) i.next();
                Set belongs = group.getStudents();
                if (belongs != null) {
	                group.setStudents(new HashSet());
	                for (Iterator j = belongs.iterator(); j.hasNext(); ) {
	                    BelongStudentGroup belong = (BelongStudentGroup) j.next();
	                    Collection tmp = userDao.getStudents(new UserFilter(new User(belong.getIdStudent())));
	                    group.addStudent(tmp.toArray()[0]);
	                }
                }
            }
            return c;
        } finally {
            Hibernate.closeSession();
        }
	}
	
	public Collection getGroups() throws RemoteException, HibernateException {
		return getGroups(null);
	}
	
	public void addGroup(Group group) throws RemoteException, HibernateException {
	    Session session = null;
        try {
            TransactionManager.beginTransaction();
            session = Hibernate.getCurrentSession();
            add(group, session);
            Collection c = group.getStudents();
            if (c != null) {
	            for (Iterator i = c.iterator(); i.hasNext(); ) {
	                BelongStudentGroup belong = new BelongStudentGroup((User) i.next(), group);
	                add(belong, session);
	            }
            }
            TransactionManager.commit();
        } catch (HibernateException e) {
            TransactionManager.rollback();
            throw e;
        } finally {
            session = null;
            Hibernate.closeSession();
        }
	}
	
	public void modifyGroup(Group group) throws RemoteException, HibernateException {
	    Session session = null;
        try {
            TransactionManager.beginTransaction();
            session = Hibernate.getCurrentSession();
            modify(group, session);
            Collection c = group.getStudents();
            if (c != null) {
	            for (Iterator i = c.iterator(); i.hasNext(); ) {
	                BelongStudentGroup belong = new BelongStudentGroup((User) i.next(), group);
                	addOrModify(belong, session);
	            }
            }
            TransactionManager.commit();
        } catch (HibernateException e) {
            TransactionManager.rollback();
            throw e;
        } finally {
            Hibernate.closeSession();
        }
	}
	
	public void removeGroup(Group group) throws RemoteException, HibernateException {
	    Session session = null;
        try {
            TransactionManager.beginTransaction();
            session = Hibernate.getCurrentSession();
            Set p = group.getStudents();
            if (p != null) {
	            for (Iterator i = p.iterator(); i.hasNext(); ) {
	                BelongStudentGroup belong = new BelongStudentGroup(((User)i.next()).getIdUser(), group);
	                remove(TABLE_BELONG, new _BelongStudentGroupFilter(belong), session);
	            }
            }
            remove(TABLE, new GroupFilter(group), session);
            TransactionManager.commit();
        } catch (HibernateException e) {
            TransactionManager.rollback();
            throw e;
        } finally {
            Hibernate.closeSession();
        }
	}
}
