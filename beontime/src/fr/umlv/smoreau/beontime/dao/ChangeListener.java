package fr.umlv.smoreau.beontime.dao;

import java.rmi.Remote;
import java.rmi.RemoteException;

import fr.umlv.smoreau.beontime.model.Formation;
import fr.umlv.smoreau.beontime.model.Group;
import fr.umlv.smoreau.beontime.model.Unavailability;
import fr.umlv.smoreau.beontime.model.element.Material;
import fr.umlv.smoreau.beontime.model.element.Room;
import fr.umlv.smoreau.beontime.model.timetable.Course;
import fr.umlv.smoreau.beontime.model.timetable.Subject;
import fr.umlv.smoreau.beontime.model.user.User;

/**
 * @author BeOnTime
 */
public interface ChangeListener extends Remote {
	public static final int TYPE_ADD    = 0;
	public static final int TYPE_MODIFY = 1;
	public static final int TYPE_REMOVE = 2;

    public void courseChanged(Course course, int type) throws RemoteException;
    
    public void subjectChanged(Subject subject, int type) throws RemoteException;
    
    public void userChanged(User user, int type) throws RemoteException;
    
    public void roomChanged(Room room, int type) throws RemoteException;
    
    public void materialChanged(Material material, int type) throws RemoteException;
    
    public void formationChanged(Formation formation, int type) throws RemoteException;
    
    public void groupChanged(Group group, int type) throws RemoteException;
    
    public void unavailabilityChanged(Unavailability unavailability, int type) throws RemoteException;
}
