package fr.umlv.smoreau.beontime.client.actions.timetable.course;

import java.awt.event.ActionEvent;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.JOptionPane;

import fr.umlv.smoreau.beontime.client.DaoManager;
import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.BoTModel;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.client.graphics.windows.AddModifyCourseWindow;
import fr.umlv.smoreau.beontime.dao.TimetableDao;
import fr.umlv.smoreau.beontime.model.Formation;
import fr.umlv.smoreau.beontime.model.association.IsDirectedByCourseTeacher;
import fr.umlv.smoreau.beontime.model.association.TakePartGroupSubjectCourse;
import fr.umlv.smoreau.beontime.model.element.Material;
import fr.umlv.smoreau.beontime.model.element.Room;
import fr.umlv.smoreau.beontime.model.timetable.Course;
import fr.umlv.smoreau.beontime.model.timetable.CourseType;
import fr.umlv.smoreau.beontime.model.user.User;

/**
 * @author BeOnTime
 */
public class ModifyCourse extends Action {
    private static final String NAME = "Modifier le cours";
    private static final String ICON = "modifier_cours.png";
    private static final String SMALL_ICON = "modifier_cours_small.png";


    public ModifyCourse(MainFrame mainFrame) {
        super(NAME, SMALL_ICON, ICON, mainFrame);
    }

    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        Course course = mainFrame.getCourseSelected();
        if (course == null)
            return;

        AddModifyCourseWindow window = new AddModifyCourseWindow(AddModifyCourseWindow.TYPE_MODIFY);
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
        	    Calendar beginDate = Calendar.getInstance();
        	    beginDate.setTime(window.getDateCourse());
        	    beginDate.set(Calendar.HOUR_OF_DAY, window.getStartHour() + (beginDate.get(Calendar.DST_OFFSET) == 0 ? 1 : 2));
        	    beginDate.set(Calendar.MINUTE, window.getStartMinute());
        	    beginDate.set(Calendar.SECOND, 0);
        	    beginDate.set(Calendar.MILLISECOND, 0);
        	    course.setBeginDate(beginDate);
        	    
        	    Calendar endDate = Calendar.getInstance();
        	    endDate.setTime(window.getDateCourse());
        	    endDate.set(Calendar.HOUR_OF_DAY, window.getEndHour() + (beginDate.get(Calendar.DST_OFFSET) == 0 ? 1 : 2));
        	    endDate.set(Calendar.MINUTE, window.getEndMinute());
        	    endDate.set(Calendar.SECOND, 0);
        	    endDate.set(Calendar.MILLISECOND, 0);
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
        	    
        	    Collection types = DaoManager.getTimetableDao().getTypesCourse();
                String tmp = window.getTypeCourse();
                for (Iterator i = types.iterator(); i.hasNext(); ) {
                    CourseType type = (CourseType) i.next();
                    if (tmp.equals(type.getNameCourseType())) {
                        course.setIdCourseType(type);
                        break;
                    }
                }
        	    
	            DaoManager.getTimetableDao().modifyCourse(course);
	            course.getBeginDate().set(Calendar.HOUR_OF_DAY, window.getStartHour());
                course.getEndDate().set(Calendar.HOUR_OF_DAY, window.getEndHour());
	            mainFrame.getModel().fireRefreshCourse(course, BoTModel.TYPE_MODIFY);

	            JOptionPane.showMessageDialog(null, "Modification effectuée avec succès", "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Une erreur interne est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
