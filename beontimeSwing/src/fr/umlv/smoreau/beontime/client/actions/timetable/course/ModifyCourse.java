package fr.umlv.smoreau.beontime.client.actions.timetable.course;

import java.awt.event.ActionEvent;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.client.graphics.windows.AddModifyCourseWindow;
import fr.umlv.smoreau.beontime.dao.DaoManager;
import fr.umlv.smoreau.beontime.dao.TimetableDao;
import fr.umlv.smoreau.beontime.dao.UnavailabilityDao;
import fr.umlv.smoreau.beontime.filter.UnavailabilityFilter;
import fr.umlv.smoreau.beontime.model.Formation;
import fr.umlv.smoreau.beontime.model.Unavailability;
import fr.umlv.smoreau.beontime.model.association.IsDirectedByCourseTeacher;
import fr.umlv.smoreau.beontime.model.association.TakePartGroupSubjectCourse;
import fr.umlv.smoreau.beontime.model.element.Material;
import fr.umlv.smoreau.beontime.model.element.Room;
import fr.umlv.smoreau.beontime.model.timetable.Course;
import fr.umlv.smoreau.beontime.model.user.User;

/**
 * @author BeOnTime
 */
public class ModifyCourse extends Action {
    private static final String NAME = "Modifier le cours";
    private static final String ICON = "modifier_cours.png";
    private static final String SMALL_ICON = "modifier_cours_small.png";
    private static final String KEY_STROKE = "ctrl W";


    public ModifyCourse(MainFrame mainFrame) {
        super(NAME, SMALL_ICON, ICON, KeyStroke.getKeyStroke(KEY_STROKE), mainFrame);
    }

    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        Course course = mainFrame.getCourseSelected();
        if (course == null)
            return;

        AddModifyCourseWindow window = new AddModifyCourseWindow(AddModifyCourseWindow.TYPE_MODIFY);
        Calendar saveBeginDate = (Calendar) course.getBeginDate().clone();
        Calendar saveEndDate = (Calendar) course.getEndDate().clone();
        window.setBeginDate(course.getBeginDate());
        window.setEndDate(course.getEndDate());
        window.setTypeCourse(course.getIdCourseType().getNameCourseType());
        
        try {
            if (mainFrame.getFormationSelected() != null)
                window.setCourseFormation(mainFrame.getFormationSelected());
            else {
                Formation formation = new Formation();
                if (mainFrame.getSubjectSelected() != null) {
                    formation.setIdFormation(mainFrame.getSubjectSelected().getIdFormation());
                } else if (mainFrame.getCourseSelected() != null) {
                    formation.setIdFormation(mainFrame.getCourseSelected().getIdFormation());
                }
                window.setCourseFormation(DaoManager.getFormationDao().getFormation(formation, null));
            }
            Course courseTmp = DaoManager.getTimetableDao().getCourse(course, new String[] {TimetableDao.JOIN_GROUPS_SUBJECTS, TimetableDao.JOIN_TEACHERS_DIRECTING, TimetableDao.JOIN_ROOMS, TimetableDao.JOIN_MATERIALS});
            courseTmp.setSubject(course.getSubject());
            course = courseTmp;
            TakePartGroupSubjectCourse takePart = (TakePartGroupSubjectCourse) course.getGroupsSubjectsTakingPart().toArray()[0];
            window.setIdGroup(takePart.getIdGroup());
            window.setTeachers(course.getTeachersDirecting());
            window.setRooms(course.getRooms());
            window.setMaterials(course.getMaterials());

            window.show();

            if (window.isOk()) {
                Calendar beginDate = window.getBeginDate();
        	    beginDate.set(Calendar.HOUR_OF_DAY, beginDate.get(Calendar.HOUR_OF_DAY) + (beginDate.get(Calendar.DST_OFFSET) == 0 ? 1 : 2));
        	    course.setBeginDate(beginDate);
        	    
        	    Calendar endDate = window.getEndDate();
        	    endDate.set(Calendar.HOUR_OF_DAY, endDate.get(Calendar.HOUR_OF_DAY) + (endDate.get(Calendar.DST_OFFSET) == 0 ? 1 : 2));
        	    course.setEndDate(endDate);
        	    
        	    course.setTeachersDirecting(new HashSet());
        	    course.setTeachers(new HashSet());
        	    Collection teachers = window.getTeachers();
        	    for (Iterator i = teachers.iterator(); i.hasNext(); ) {
        	        User user = (User) i.next();
        	        course.addTeacherDirecting(new IsDirectedByCourseTeacher(user,course));
        	        course.addTeacher(user);
        	    }
        	    
        	    course.setRooms(new HashSet());
        	    Collection rooms = window.getPlaceCourse();
        	    for (Iterator i = rooms.iterator(); i.hasNext(); )
        	        course.addRoom((Room) i.next());
        	    
        	    course.setMaterials(new HashSet());
        	    Collection materials = window.getCourseEquipment();
        	    for (Iterator i = materials.iterator(); i.hasNext(); )
        	        course.addMaterial((Material) i.next());

                course.setIdCourseType(DaoManager.getTimetableDao().getTypeCourse(window.getTypeCourse()));

	            DaoManager.getTimetableDao().modifyCourse(course);


	            // modification des indisponibilités qui en découlent
	            UnavailabilityDao unavailabilityDao = DaoManager.getAvailabilityDao();

	            UnavailabilityFilter filter = new UnavailabilityFilter();
                filter.setIdCourse(course.getIdCourse());
                unavailabilityDao.removeUnavailability(filter);

                Unavailability unavailability = new Unavailability();
                unavailability.setBeginDate(course.getBeginDate());
                unavailability.setEndDate(course.getEndDate());
                unavailability.setIdCourse(course.getIdCourse());
                unavailability.setDescription(UnavailabilityDao.AUTO_DESCRIPTION);

                for (Iterator j = course.getGroupsSubjectsTakingPart().iterator(); j.hasNext(); ) {
                    TakePartGroupSubjectCourse tmp = (TakePartGroupSubjectCourse) j.next();
                    unavailability.setIdUnavailabilityType(unavailabilityDao.getTypeUnavailability(UnavailabilityDao.TYPE_GROUP));
                    unavailability.setIdUnavailabilitySubject(tmp.getIdGroup());
                    unavailabilityDao.addUnavailability(unavailability);
                }

                for (Iterator j = course.getTeachersDirecting().iterator(); j.hasNext(); ) {
                    IsDirectedByCourseTeacher tmp = (IsDirectedByCourseTeacher) j.next();
                    unavailability.setIdUnavailabilityType(unavailabilityDao.getTypeUnavailability(UnavailabilityDao.TYPE_TEACHER));
                    unavailability.setIdUnavailabilitySubject(tmp.getIdTeacher());
                    unavailabilityDao.addUnavailability(unavailability);
                }

                for (Iterator j = course.getMaterials().iterator(); j.hasNext(); ) {
                    Material tmp = (Material) j.next();
                    unavailability.setIdUnavailabilityType(unavailabilityDao.getTypeUnavailability(UnavailabilityDao.TYPE_MATERIAL));
                    unavailability.setIdUnavailabilitySubject(tmp.getIdMaterial());
                    unavailabilityDao.addUnavailability(unavailability);
                }

                for (Iterator j = course.getRooms().iterator(); j.hasNext(); ) {
                    Room tmp = (Room) j.next();
                    unavailability.setIdUnavailabilityType(unavailabilityDao.getTypeUnavailability(UnavailabilityDao.TYPE_ROOM));
                    unavailability.setIdUnavailabilitySubject(tmp.getIdRoom());
                    unavailabilityDao.addUnavailability(unavailability);
                }

	            JOptionPane.showMessageDialog(null, "Modification effectuée avec succès", "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Une erreur interne est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
