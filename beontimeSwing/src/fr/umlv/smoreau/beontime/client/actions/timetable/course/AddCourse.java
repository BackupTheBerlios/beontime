package fr.umlv.smoreau.beontime.client.actions.timetable.course;

import java.awt.event.ActionEvent;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import fr.umlv.smoreau.beontime.client.DaoManager;
import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.BoTModel;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.client.graphics.windows.AddModifyCourseWindow;
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
public class AddCourse extends Action {
    private static final String NAME = "Placer un cours";
	private static final String ICON = "ajouter_cours.png";
	private static final String SMALL_ICON = "ajouter_cours_small.png";


    public AddCourse(MainFrame mainFrame) {
        super(NAME, SMALL_ICON, ICON, mainFrame);
    }

    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        JTable table = mainFrame.getTable();
        int[] columns = table.getSelectedColumns();
        int[] rows    = table.getSelectedRows();
    	AddModifyCourseWindow window = new AddModifyCourseWindow(AddModifyCourseWindow.TYPE_ADD);
    	if (columns.length>1){
    		window.setStartHour(columns[0]);
    		window.setEndHour(columns[columns.length-1]);
    	}
    	window.setIdFormation(mainFrame.getSubjectSelected().getIdFormation());
    	window.setTypeCourse(mainFrame.getCourseTypeSelected());
    	window.setIdTeacher(mainFrame.getSubjectSelected().getIdTeacher());
    	if (mainFrame.getModel().getTimetable().getGroup() != null)
    	    window.setIdGroup(mainFrame.getModel().getTimetable().getGroup().getIdGroup());
    	window.show();
    	
    	if (window.isOk()) {
    	    Course course = new Course();
    	    course.setIdFormation(mainFrame.getFormationSelected().getIdFormation());
    	    
    	    course.setSubject(mainFrame.getSubjectSelected());
    	    
    	    TakePartGroupSubjectCourse takePart = new TakePartGroupSubjectCourse();
    	    takePart.setIdGroup(window.getCourseGroup().getIdGroup());
    	    takePart.setIdSubject(mainFrame.getSubjectSelected().getIdSubject());
    	    course.addGroupSubjectTakingPart(takePart);
    	    
    	    Collection teachers = window.getTeachers();
    	    course.setTeachersDirecting(new HashSet());
    	    course.setTeachers(new HashSet());
            for (Iterator j = teachers.iterator(); j.hasNext(); ) {
                User user = (User)j.next();
                course.addTeacher(user);
                course.addTeacherDirecting(new IsDirectedByCourseTeacher(user, course));
            }
    	    
    	    course.setRooms(new HashSet());
    	    Collection rooms = window.getPlaceCourse();
    	    for (Iterator i = rooms.iterator(); i.hasNext(); )
    	        course.addRoom((Room) i.next());
    	    
    	    course.setMaterials(new HashSet());
    	    Collection materials = window.getCourseEquipment();
    	    for (Iterator i = materials.iterator(); i.hasNext(); )
    	        course.addMaterial((Material) i.next());

    	    try {
                Collection types = DaoManager.getTimetableDao().getTypesCourse();
                String tmp = window.getTypeCourse();
                for (Iterator i = types.iterator(); i.hasNext(); ) {
                    CourseType type = (CourseType) i.next();
                    if (tmp.equals(type.getNameCourseType())) {
                        course.setIdCourseType(type);
                        break;
                    }
                }
                
                for (int i = 0; i < window.getNbWeeksCourse(); ++i) {
            	    Calendar beginDate = Calendar.getInstance();
            	    beginDate.setTime(window.getDateCourse());
            	    beginDate.set(Calendar.DAY_OF_YEAR, beginDate.get(Calendar.DAY_OF_YEAR) + 7*i);
            	    beginDate.set(Calendar.HOUR_OF_DAY, window.getStartHour() + (beginDate.get(Calendar.DST_OFFSET) == 0 ? 1 : 2));
            	    beginDate.set(Calendar.MINUTE, window.getStartMinute());
            	    beginDate.set(Calendar.SECOND, 0);
            	    beginDate.set(Calendar.MILLISECOND, 0);
            	    Calendar endDate = Calendar.getInstance();
            	    endDate.setTime(window.getDateCourse());
            	    endDate.set(Calendar.DAY_OF_YEAR, endDate.get(Calendar.DAY_OF_YEAR) + 7*i);
            	    endDate.set(Calendar.HOUR_OF_DAY, window.getEndHour() + (beginDate.get(Calendar.DST_OFFSET) == 0 ? 1 : 2));
            	    endDate.set(Calendar.MINUTE, window.getEndMinute());
            	    endDate.set(Calendar.SECOND, 0);
            	    endDate.set(Calendar.MILLISECOND, 0);

                    course.setBeginDate(beginDate);
                    course.setEndDate(endDate);
	                
                    course = DaoManager.getTimetableDao().addCourse(course);
                    
                    if (course.getBeginDate().getTimeInMillis() >= mainFrame.getBeginPeriod().getTimeInMillis() &&
                            course.getEndDate().getTimeInMillis() <= mainFrame.getEndPeriod().getTimeInMillis()) {
	                    course.getBeginDate().set(Calendar.HOUR_OF_DAY, window.getStartHour());
	                    course.getEndDate().set(Calendar.HOUR_OF_DAY, window.getEndHour());
		                mainFrame.getModel().getTimetable().addCourse(course);
		                mainFrame.getModel().fireRefreshCourse(course, BoTModel.TYPE_ADD);
                    }
                }
                
                JOptionPane.showMessageDialog(null, "Ajout effectu� avec succ�s", "Information", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Une erreur interne est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
    	}
    }
}
