package fr.umlv.smoreau.beontime.client.actions.timetable.course;

import java.awt.event.ActionEvent;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import fr.umlv.smoreau.beontime.client.ClipboardManager;
import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.client.graphics.windows.AddModifyCourseWindow;
import fr.umlv.smoreau.beontime.dao.DaoManager;
import fr.umlv.smoreau.beontime.model.Formation;
import fr.umlv.smoreau.beontime.model.association.IsDirectedByCourseTeacher;
import fr.umlv.smoreau.beontime.model.association.TakePartGroupSubjectCourse;
import fr.umlv.smoreau.beontime.model.element.Material;
import fr.umlv.smoreau.beontime.model.element.Room;
import fr.umlv.smoreau.beontime.model.timetable.Course;
import fr.umlv.smoreau.beontime.model.user.User;

/**
 * @author BeOnTime
 */
public class PasteCourse extends Action {
    private static final String NAME = "Coller le cours";
    private static final String ICON = "coller.png";
    private static final String SMALL_ICON = "coller_small.png";


    public PasteCourse(MainFrame mainFrame) {
        super(NAME, SMALL_ICON, ICON, mainFrame);
    }

    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        Course course = ClipboardManager.getCourse();
        
        if (course == null)
            return;
        
        course.setIdCourse(null);
        
        JTable table = mainFrame.getTable();
        int[] columns = table.getSelectedColumns();
        int[] rows    = table.getSelectedRows();

        AddModifyCourseWindow window = new AddModifyCourseWindow(AddModifyCourseWindow.TYPE_MODIFY);
        if (columns.length > 1) {
    		window.setStartHour(columns[0]);
    		window.setEndHour(columns[columns.length-1]);
    	} else
    	    window.setBeginDate(course.getBeginDate());
    	if (rows.length > 0) {
    	    Calendar date = Calendar.getInstance();
    	    date.setTime(mainFrame.getDateSelected());
    	    date.set(Calendar.DAY_OF_WEEK, rows[0] + 2);
    	    window.setDateCourse(date);
    	} else
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

	            course = DaoManager.getTimetableDao().addCourse(course);

	            JOptionPane.showMessageDialog(null, "Ajout effectué avec succès", "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Une erreur interne est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
