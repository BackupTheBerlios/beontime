package fr.umlv.smoreau.beontime.client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Calendar;
import java.util.Iterator;

import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.dao.ChangeListener;
import fr.umlv.smoreau.beontime.model.Formation;
import fr.umlv.smoreau.beontime.model.Group;
import fr.umlv.smoreau.beontime.model.Unavailability;
import fr.umlv.smoreau.beontime.model.association.TakePartGroupSubjectCourse;
import fr.umlv.smoreau.beontime.model.element.Material;
import fr.umlv.smoreau.beontime.model.element.Room;
import fr.umlv.smoreau.beontime.model.timetable.Course;
import fr.umlv.smoreau.beontime.model.timetable.Subject;
import fr.umlv.smoreau.beontime.model.timetable.Timetable;
import fr.umlv.smoreau.beontime.model.user.User;

/**
 * @author BeOnTime
 */
public class ChangeMonitor extends UnicastRemoteObject implements ChangeListener {
    private MainFrame mainFrame;

    public ChangeMonitor(MainFrame mainFrame) throws RemoteException {
        this.mainFrame = mainFrame;
    }

    public void courseChanged(Course course, int type) throws RemoteException {
        Timetable timetable = mainFrame.getModel().getTimetable();
        if (timetable == null)
            return;

        try {
            if (ChangeListener.TYPE_ADD == type) {
                if (course.getBeginDate().getTimeInMillis() >= mainFrame.getBeginPeriod().getTimeInMillis() &&
                        course.getEndDate().getTimeInMillis() <= mainFrame.getEndPeriod().getTimeInMillis()) {
                    course.getBeginDate().set(Calendar.HOUR_OF_DAY, course.getBeginDate().get(Calendar.HOUR_OF_DAY) - (course.getBeginDate().get(Calendar.DST_OFFSET) == 0 ? 1 : 2));
                    course.getEndDate().set(Calendar.HOUR_OF_DAY, course.getEndDate().get(Calendar.HOUR_OF_DAY) - (course.getEndDate().get(Calendar.DST_OFFSET) == 0 ? 1 : 2));
                    
                    // si le cours n'appartient pas l'emploi du temps de la formation courante
                    if (timetable.getFormation() != null &&
                            !timetable.getFormation().getIdFormation().equals(course.getIdFormation()))
                        return;
                    
                    // si le cours n'appartient pas l'emploi du temps du groupe courant
                    if (timetable.getGroup() != null) {
                        TakePartGroupSubjectCourse takePart = (TakePartGroupSubjectCourse) course.getGroupsSubjectsTakingPart().toArray()[0];
                        if (!timetable.getGroup().getIdGroup().equals(takePart.getIdGroup()))
                            return;
                    }
                    
                    // si le cours n'appartient pas à l'emploi du temps de l'enseignant courant
                    if (timetable.getTeacher() != null) {
                        boolean show = false;
                        for (Iterator i = course.getTeachers().iterator(); i.hasNext(); ) {
                            User teacher = (User) i.next();
                            if (teacher.getIdUser().equals(timetable.getTeacher().getIdUser())) {
                                show = true;
                                break;
                            }
                        }
                        System.out.println(show);
                        if (!show)
                            return;
                    }
                    
                    // si le cours n'appartient pas à l'emploi du temps du local
                    if (timetable.getRoom() != null) {
                        boolean show = false;
                        for (Iterator i = course.getRooms().iterator(); i.hasNext(); ) {
                            Room room = (Room) i.next();
                            if (room.getIdRoom().equals(timetable.getRoom().getIdRoom())) {
                                show = true;
                                break;
                            }
                        }
                        if (!show)
                            return;
                    }
                    
                    // si le cours n'appartient pas à l'emploi du temps du matériel
                    if (timetable.getMaterial() != null) {
                        boolean show = false;
                        for (Iterator i = course.getMaterials().iterator(); i.hasNext(); ) {
                            Material material = (Material) i.next();
                            if (material.getIdMaterial().equals(timetable.getMaterial().getIdMaterial())) {
                                show = true;
                                break;
                            }
                        }
                        if (!show)
                            return;
                    }

                    timetable.addCourse(course);
                } else {
                    return;
                }
            } else if (ChangeListener.TYPE_REMOVE == type) {
                if (!timetable.removeCourse(course))
                    return;
                mainFrame.setCourseSelected(null);
            } else if (ChangeListener.TYPE_MODIFY == type) {
                courseChanged(course, ChangeListener.TYPE_ADD);
                courseChanged(course, ChangeListener.TYPE_REMOVE);
                return;
            }

            mainFrame.getModel().fireRefreshCourse(course, type);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public void subjectChanged(Subject subject, int type) throws RemoteException {
        Timetable timetable = mainFrame.getModel().getTimetable();
        if (timetable == null)
            return;

        try {
            if (ChangeListener.TYPE_ADD == type) {
                // si la matière n'appartient pas l'emploi du temps du groupe ou de la formation courante
                if ((timetable.getFormation() != null || timetable.getGroup() != null) &&
                        !timetable.getFormation().getIdFormation().equals(subject.getIdFormation()))
                    return;
                
                // si la matière n'appartient pas à l'emploi du temps de l'enseignant courant
                if (timetable.getTeacher() != null &&
                        !timetable.getTeacher().getIdUser().equals(subject.getIdTeacher()))
                    return;
                
                // si l'affichage courant est l'emploi du temps d'un local
                if (timetable.getRoom() != null)
                    return;
                
                // si l'affichage courant est l'emploi du temps d'un matériel
                if (timetable.getMaterial() != null)
                    return;

                timetable.addSubject(subject);
            } else if (ChangeListener.TYPE_REMOVE == type) {
                if (!timetable.removeSubject(subject))
                    return;
            } else if (ChangeListener.TYPE_MODIFY == type) {
                subjectChanged(subject, ChangeListener.TYPE_REMOVE);
                subjectChanged(subject, ChangeListener.TYPE_ADD);
                return;
            }
            mainFrame.getModel().fireRefreshSubject(subject, type);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public void userChanged(User user, int type) throws RemoteException {
        try {
            mainFrame.getModel().fireRefreshUser(user, type);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void roomChanged(Room room, int type) throws RemoteException {
        try {
            mainFrame.getModel().fireRefreshRoom(room, type);
            if (ChangeListener.TYPE_REMOVE == type &&
                    mainFrame.getRoomSelected() == null)
                mainFrame.getModel().fireCloseTimetable();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void materialChanged(Material material, int type) throws RemoteException {
        try {
            mainFrame.getModel().fireRefreshMaterial(material, type);
            if (ChangeListener.TYPE_REMOVE == type &&
                    mainFrame.getMaterialSelected() == null)
                mainFrame.getModel().fireCloseTimetable();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void formationChanged(Formation formation, int type) throws RemoteException {
        try {
            mainFrame.getModel().fireRefreshFormation(formation, type);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void groupChanged(Group group, int type) throws RemoteException {
        Timetable timetable = mainFrame.getModel().getTimetable();
        try {
            if (ChangeListener.TYPE_ADD == type)
                timetable.addGroup(group);
            else if (ChangeListener.TYPE_REMOVE == type)
                if (!timetable.removeGroup(group))
                    return;

            mainFrame.getModel().fireRefreshGroup(group, type);
            if (ChangeListener.TYPE_REMOVE == type &&
                    mainFrame.getGroupSelected() == null)
                mainFrame.getModel().fireCloseTimetable();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void unavailabilityChanged(Unavailability unavailability, int type) throws RemoteException {
        try {
            mainFrame.getModel().fireRefreshUnavailability(unavailability, type);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
