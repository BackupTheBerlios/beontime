package fr.umlv.smoreau.beontime.client.actions.timetable.course;

import java.awt.event.ActionEvent;
import java.util.Calendar;
import java.util.Collection;
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
    	AddModifyCourseWindow window = new AddModifyCourseWindow();
    	if (columns.length>1){
    		window.setStartHour(columns[0]);
    		window.setEndHour(columns[columns.length-1]);
    	}
    	window.setCourseFormation(mainFrame.getFormationSelected());
    	window.setTypeCourse(mainFrame.getCourseTypeSelected());
    	//TODO ajouter l'enseignant responsable de la matière pour le cours
    	window.show();
    	
    	if (window.isOk()) {
    	    Course course = new Course();
    	    course.setIdFormation(mainFrame.getFormationSelected().getIdFormation());
    	    
    	    course.setSubject(mainFrame.getSubjectSelected());

    	    Calendar beginDate = Calendar.getInstance();
    	    beginDate.setTime(window.getDateCourse());
    	    beginDate.set(Calendar.HOUR_OF_DAY, window.getStartHour()+1);
    	    beginDate.set(Calendar.MINUTE, window.getStartMinute());
    	    beginDate.set(Calendar.SECOND, 0);
    	    beginDate.set(Calendar.MILLISECOND, 0);
    	    course.setBeginDate(beginDate);
    	    
    	    Calendar endDate = Calendar.getInstance();
    	    endDate.setTime(window.getDateCourse());
    	    endDate.set(Calendar.HOUR_OF_DAY, window.getEndHour()+1);
    	    endDate.set(Calendar.MINUTE, window.getEndMinute());
    	    endDate.set(Calendar.SECOND, 0);
    	    endDate.set(Calendar.MILLISECOND, 0);
    	    course.setEndDate(endDate);
    	    
    	    course.setNbWeeks(new Integer(window.getNbWeeksCourse()));
    	    
    	    TakePartGroupSubjectCourse takePart = new TakePartGroupSubjectCourse();
    	    takePart.setIdGroup(window.getCourseGroup().getIdGroup());
    	    takePart.setIdSubject(mainFrame.getSubjectSelected().getIdSubject());
    	    course.addGroupSubjectTakingPart(takePart);
    	    
    	    Collection teachers = window.getTeachers();
    	    for (Iterator i = teachers.iterator(); i.hasNext(); )
    	        course.addTeacherDirecting(new IsDirectedByCourseTeacher((User) i.next(),course));
    	    
    	    Collection rooms = window.getPlaceCourse();
    	    for (Iterator i = rooms.iterator(); i.hasNext(); )
    	        course.addRoom((Room) i.next());
    	    
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
                
                course = DaoManager.getTimetableDao().addCourse(course);
                course.getBeginDate().set(Calendar.HOUR_OF_DAY, course.getBeginDate().get(Calendar.HOUR_OF_DAY)-1);
                course.getEndDate().set(Calendar.HOUR_OF_DAY, course.getEndDate().get(Calendar.HOUR_OF_DAY)-1);
                mainFrame.getModel().getTimetable().addCourse(course);
                mainFrame.getModel().fireRefreshCourse(course, BoTModel.TYPE_ADD);
                
                JOptionPane.showMessageDialog(null, "Ajout effectué avec succès", "Information", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Une erreur interne est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
    	}
    }
}
