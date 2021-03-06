package fr.umlv.smoreau.beontime.client.actions.timetable.course;

import java.awt.event.ActionEvent;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.client.graphics.windows.AddModifyCourseWindow;
import fr.umlv.smoreau.beontime.dao.DaoManager;
import fr.umlv.smoreau.beontime.dao.UnavailabilityDao;
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
public class AddCourse extends Action {
    private static final String NAME = "Placer un cours";
	private static final String ICON = "ajouter_cours.png";
	private static final String SMALL_ICON = "ajouter_cours_small.png";
	private static final String KEY_STROKE = "ctrl K";


    public AddCourse(MainFrame mainFrame) {
        super(NAME, SMALL_ICON, ICON, KeyStroke.getKeyStroke(KEY_STROKE), mainFrame);
    }

    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        JTable table = mainFrame.getTable();
        int[] columns = table.getSelectedColumns();
        int[] rows    = table.getSelectedRows();
    	AddModifyCourseWindow window = new AddModifyCourseWindow(AddModifyCourseWindow.TYPE_ADD);
    	if (columns.length > 1) {
    		window.setStartHour(columns[0]);
    		window.setEndHour(columns[columns.length-1]);
    	}
    	if (rows.length > 0) {
    	    Calendar date = Calendar.getInstance();
    	    date.setTime(mainFrame.getDateSelected());
    	    date.set(Calendar.DAY_OF_WEEK, rows[0] + 2);
    	    window.setDateCourse(date);
    	}
    	window.setIdFormation(mainFrame.getSubjectSelected().getIdFormation());
    	window.setTypeCourse(mainFrame.getCourseTypeSelected());
    	window.setIdTeacher(mainFrame.getSubjectSelected().getIdTeacher());
    	if (mainFrame.getModel().getTimetable().getGroup() != null)
    	    window.setIdGroup(mainFrame.getModel().getTimetable().getGroup().getIdGroup());
    	window.show();
    	
    	if (window.isOk()) {
    	    try {
    	        for (int k = 0; k < window.getNbWeeksCourse(); ++k) {
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
	
	                course.setIdCourseType(DaoManager.getTimetableDao().getTypeCourse(window.getTypeCourse()));
                
            	    Calendar beginDate = window.getBeginDate();
            	    beginDate.set(Calendar.DAY_OF_YEAR, beginDate.get(Calendar.DAY_OF_YEAR) + 7*k);
            	    beginDate.set(Calendar.HOUR_OF_DAY, beginDate.get(Calendar.HOUR_OF_DAY) + (beginDate.get(Calendar.DST_OFFSET) == 0 ? 1 : 2));
            	    Calendar endDate = window.getEndDate();
            	    endDate.set(Calendar.DAY_OF_YEAR, endDate.get(Calendar.DAY_OF_YEAR) + 7*k);
            	    endDate.set(Calendar.HOUR_OF_DAY, endDate.get(Calendar.HOUR_OF_DAY) + (endDate.get(Calendar.DST_OFFSET) == 0 ? 1 : 2));

                    course.setBeginDate(beginDate);
                    course.setEndDate(endDate);
	                
                    course = DaoManager.getTimetableDao().addCourse(course);

                    // ajoute les indisponibilités qui en découlent
                    UnavailabilityDao unavailabilityDao = DaoManager.getAvailabilityDao();

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
                }
                
                JOptionPane.showMessageDialog(null, "Ajout effectué avec succès", "Information", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Une erreur interne est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
    	}
    }
}
