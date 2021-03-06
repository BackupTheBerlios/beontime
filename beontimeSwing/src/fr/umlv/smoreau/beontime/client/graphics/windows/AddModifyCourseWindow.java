package fr.umlv.smoreau.beontime.client.graphics.windows;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.toedter.calendar.JDateChooser;

import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.dao.DaoManager;
import fr.umlv.smoreau.beontime.dao.TimetableDao;
import fr.umlv.smoreau.beontime.dao.UnavailabilityDao;
import fr.umlv.smoreau.beontime.filter.GroupFilter;
import fr.umlv.smoreau.beontime.filter.UnavailabilityFilter;
import fr.umlv.smoreau.beontime.model.Formation;
import fr.umlv.smoreau.beontime.model.Group;
import fr.umlv.smoreau.beontime.model.Unavailability;
import fr.umlv.smoreau.beontime.model.association.IsDirectedByCourseTeacher;
import fr.umlv.smoreau.beontime.model.element.Material;
import fr.umlv.smoreau.beontime.model.element.Room;
import fr.umlv.smoreau.beontime.model.user.User;

/**
 * @author BeOnTime
 */
public class AddModifyCourseWindow {
	private static final String TITRE = "Placer un cours";
	
	private static final int FIRST_HOUR = 8;
	private static final int LAST_HOUR = 19;
	
	public static final int TYPE_ADD = 0;
	public static final int TYPE_MODIFY = 1;
	
	private JLabel teacherLabel;
	private JLabel placeCourseLabel;
	private JLabel repeatCourseLabel;
	private JLabel courseEquipmentLabel;
	private JLabel courseFormationLabel;
	private JLabel courseGroupLabel;
	private JLabel typeCourseLabel;
	
	private JLabel dateCourseTheLabel;
	private JLabel startCourseHourLabel;
	private JLabel startCourseMinuteLabel;
	private JLabel endCourseHourLabel;
	private JLabel endCourseMinuteLabel;
	private JLabel dureeCourseDeLabel;
	private JLabel dureeCourseALabel;
	
	private JComboBox teacherCourseJcb;
	private JComboBox startCourseHourJcb;
	private JComboBox startCourseMinuteJcb;
	private JComboBox endCourseHourJcb;
	private JComboBox endCourseMinuteJcb;
	private JComboBox placeCourseJcb;
	private JComboBox repeatCourseJcb;
	private JComboBox courseEquipmentJcb;
	private JComboBox courseFormationJcb;
	private JComboBox courseGroupJcb;
	
	private JDateChooser dateCourse;
	
	private JRadioButton magistral;
	private JRadioButton td;
	private JRadioButton tp;
	
	private JButton teacherPlus;
	private JButton placeCoursePlus;
	private JButton courseEquipmentPlus;
	private JButton ok;
	private JButton annuler;
	
	private boolean isOk;
	
	private JPanel teacherPanel = new JPanel();
	private JPanel teacherPlusPanel = new JPanel();
	private JPanel placeCoursePanel = new JPanel();
	private JPanel placeCoursePlusPanel = new JPanel();
	private JPanel courseEquipmentPanel = new JPanel();
	private JPanel courseEquipmentPlusPanel = new JPanel();
	
	private JDialog AMCWFrame;
	private GridBagLayout AMCWLayout = new GridBagLayout();
	private GridBagConstraints layoutConstraints = new GridBagConstraints();
	
	private String[] teachersName;
	private User[] teachers;
	private String[] roomsName;
	private Room[] rooms;
	private String[] materialsName;
	private Material[] materials;
	private String[] groupsName;
	private Group[] groups;
	private int type;
	private MainFrame mainFrame;
	
	
	public AddModifyCourseWindow(int type) {
	    this.isOk = false;
	    this.type = type;
	    this.mainFrame = MainFrame.getInstance();
		AMCWFrame = new JDialog(MainFrame.getInstance().getMainFrame(), TITRE, true);
		AMCWFrame.getContentPane().setLayout(AMCWLayout);
		
		try {
            Collection t = DaoManager.getUserDao().getTeachers();
            if (t.size() == 0)
                throw new Exception();
            teachersName = new String[t.size()+1];
            teachers = new User[t.size()+1];
            teachersName[0] = "";
            int j = 1;
            for (Iterator i = t.iterator(); i.hasNext(); ++j) {
                teachers[j] = (User) i.next();
                teachersName[j] = teachers[j].getName() + " " + teachers[j].getFirstName();
            }
        } catch (Exception e) {
            teachersName = null;
            teachers = null;
        }
        
        try {
            Collection r = DaoManager.getElementDao().getRooms();
            if (r.size() == 0)
                throw new Exception();
            roomsName = new String[r.size()+1];
            rooms = new Room[r.size()+1];
            roomsName[0] = "";
            int j = 1;
            for (Iterator i = r.iterator(); i.hasNext(); ++j) {
                rooms[j] = (Room) i.next();
                roomsName[j] = rooms[j].getName();
            }
        } catch (Exception e) {
            roomsName = null;
            rooms = null;
        }
        
        try {
            Collection m = DaoManager.getElementDao().getMaterials();
            if (m.size() == 0)
                throw new Exception();
            materialsName = new String[m.size()+1];
            materials = new Material[m.size()+1];
            materialsName[0] = "";
            int j = 1;
            for (Iterator i = m.iterator(); i.hasNext(); ++j) {
                materials[j] = (Material) i.next();
                materialsName[j] = materials[j].getName();
            }
        } catch (Exception e) {
            materialsName = null;
            materials = null;
        }
        
        GroupFilter groupFilter = new GroupFilter();
        if (mainFrame.getSubjectSelected() != null)
            groupFilter.setIdFormation(mainFrame.getSubjectSelected().getIdFormation());
        else if (mainFrame.getCourseSelected() != null)
            groupFilter.setIdFormation(mainFrame.getCourseSelected().getIdFormation());
        Collection g = null;
        try {
            g = DaoManager.getGroupDao().getGroups(groupFilter);
        } catch (Exception e) {
            g = new ArrayList();
        }
        groups = new Group[g.size()];
        groupsName = new String[g.size()];
        int j = 0;
        for (Iterator i = g.iterator(); i.hasNext(); ++j) {
            groups[j] = (Group) i.next();
            groupsName[j] = groups[j].getHeading();
        }
		
		initAddModifyCourseWindow();
	}
	
	
	public Collection getTeachers() {
		ArrayList list = new ArrayList();
		
		if (teachersName != null) {
			Component[] components = teacherPanel.getComponents();
			
			for(int i = 0; i < components.length; ++i) {
			    if (components[i] instanceof JComboBox) {
			        int index = ((JComboBox)components[i]).getSelectedIndex();
			        if (index > 0)
			            list.add(teachers[index]);
			    }
			}
		}
		
		return list;
	}
	
	public void setTeachers(Collection teachers) {
	    
	    teacherPanel.removeAll();
	    teacherCourseJcb = new JComboBox(teachersName);
	    teacherPanel.add(teacherCourseJcb);
	    teacherPanel.add(Box.createVerticalStrut(5));
	    
	    int cpt = 0;
		int sizeTeachers = teachers.size();
		
		
		for(Iterator it = teachers.iterator(); it.hasNext(); ) {
			
			Long idTeacher = ((IsDirectedByCourseTeacher) it.next()).getIdTeacher();
			
			int pos = -1;
			
			for(int i = 1; i < this.teachers.length; ++i) {
				if (this.teachers[i].getIdUser().compareTo(idTeacher) == 0)
					pos = i;
			}
			
			if (cpt == 0) {
				teacherCourseJcb.setSelectedIndex(pos);    			
				
				if (cpt != (sizeTeachers-1))
					teacherPlus.setText("x");  	
			}
			else {
				JComboBox jcb = new JComboBox(teachersName);
				
				jcb.setSelectedItem(teachersName[pos]);
				
				teacherPanel.add(jcb);
				teacherPanel.add(Box.createVerticalStrut(5));
				
				JButton plus;
				if(cpt == (sizeTeachers-1))
					plus = new JButton("+");
				else
					plus = new JButton("x");
				
				plus.addActionListener(new ButtonPlusListener(teacherPlusPanel, teacherPanel, AMCWFrame, teachersName));
				teacherPlusPanel.add(plus);
				teacherPlusPanel.add(Box.createVerticalStrut(5));
			}
			cpt++;
		
		}
	}
	
	public void setRooms(Collection rooms) {
	    if (rooms == null || rooms.size() == 0)
	        return;
	    
	    placeCoursePanel.removeAll();
	    placeCourseJcb = new JComboBox(roomsName);
	    placeCoursePanel.add(placeCourseJcb);
	    placeCoursePanel.add(Box.createVerticalStrut(5));
	    
	    int cpt = 0;
		int sizeRooms = rooms.size();
		
		for(Iterator it = rooms.iterator(); it.hasNext(); ) {
			Room room = (Room) it.next();
			
			int pos = -1;
			
			for(int i = 1; i < this.rooms.length; ++i) {
				if (this.rooms[i].equals(room))
					pos = i;
			}
			
			if (cpt == 0) {
				placeCourseJcb.setSelectedIndex(pos);    			
				
				if (cpt != (sizeRooms-1))
					placeCoursePlus.setText("x");  	
			}
			else {
				JComboBox jcb = new JComboBox(roomsName);
				
				jcb.setSelectedItem(roomsName[pos]);
				
				placeCoursePanel.add(jcb);
				placeCoursePanel.add(Box.createVerticalStrut(5));
				
				JButton plus;
				if(cpt == (sizeRooms-1))
					plus = new JButton("+");
				else
					plus = new JButton("x");
				
				plus.addActionListener(new ButtonPlusListener(placeCoursePlusPanel, placeCoursePanel, AMCWFrame, roomsName));
				placeCoursePlusPanel.add(plus);
				placeCoursePlusPanel.add(Box.createVerticalStrut(5));
			}
			cpt++;
		}
	}
	
	public void setMaterials(Collection materials) {
	    if (materials == null || materials.size() == 0)
	        return;

	    courseEquipmentPanel.removeAll();
	    courseEquipmentJcb = new JComboBox(materialsName);
	    courseEquipmentPanel.add(courseEquipmentJcb);
	    courseEquipmentPanel.add(Box.createVerticalStrut(5));
	    
	    int cpt = 0;
		int sizeMaterials = materials.size();
		
		for(Iterator it = materials.iterator(); it.hasNext(); ) {
			Material material = (Material) it.next();
			
			int pos = -1;
			
			for(int i = 1; i < this.materials.length; ++i) {
				if (this.materials[i].equals(material))
					pos = i;
			}
			
			if (cpt == 0) {
				courseEquipmentJcb.setSelectedIndex(pos);    			
				
				if (cpt != (sizeMaterials-1))
					courseEquipmentPlus.setText("x");  	
			}
			else {
				JComboBox jcb = new JComboBox(materialsName);
				
				jcb.setSelectedItem(materialsName[pos]);
				
				courseEquipmentPanel.add(jcb);
				courseEquipmentPanel.add(Box.createVerticalStrut(5));
				
				JButton plus;
				if(cpt == (sizeMaterials-1))
					plus = new JButton("+");
				else
					plus = new JButton("x");
				
				plus.addActionListener(new ButtonPlusListener(courseEquipmentPlusPanel, courseEquipmentPanel, AMCWFrame, materialsName));
				courseEquipmentPlusPanel.add(plus);
				courseEquipmentPlusPanel.add(Box.createVerticalStrut(5));
			}
			cpt++;
		}
	}
	
	public void setIdTeacher(Long idTeacher) {
	    for (int i = 1; i < teachers.length; ++i)
	        if (teachers[i].getIdUser().equals(idTeacher)) {
	            teacherCourseJcb.setSelectedIndex(i);
	            break;
	        }
	}
	
	
	private Date getDateCourse() {
		return dateCourse.getDate();
	}
	
	
	public Collection getPlaceCourse() {
		ArrayList list = new ArrayList();
		
		if (roomsName != null) {
			Component[] components = placeCoursePanel.getComponents();
			
			for(int i = 0; i < components.length; ++i)
			    if (components[i] instanceof JComboBox) {
			        int index = ((JComboBox)components[i]).getSelectedIndex();
			        if (index > 0)
			            list.add(rooms[index]);
			    }
		}
		
		return list;
	}
	
	public int getNbWeeksCourse(){
		return Integer.parseInt(repeatCourseJcb.getSelectedItem().toString());
	}
	
	public Collection getCourseEquipment() {
		ArrayList list = new ArrayList();
		
		if (materialsName != null) {
			Component[] components = courseEquipmentPanel.getComponents();
			
			for (int i = 0; i < components.length; ++i)
			    if (components[i] instanceof JComboBox) {
			        int index = ((JComboBox)components[i]).getSelectedIndex();
			        if (index > 0)
			            list.add(materials[index]);
			    }
		}
		
		return list;
	}
	
	public String getCourseFormation() {
		return courseFormationJcb.getSelectedItem().toString();
	}
	
	public Group getCourseGroup() {
		return groups[courseGroupJcb.getSelectedIndex()];
	}
	
	public String getTypeCourse() {
		if (magistral.isSelected())
			return TimetableDao.TYPE_COURSE;
		
		else if(td.isSelected())
			return TimetableDao.TYPE_TD;
		
		return TimetableDao.TYPE_TP;
	}
	
	public void setCourseFormation(Formation formation) {
		AMCWFrame.getContentPane().remove(courseFormationLabel);
		AMCWFrame.getContentPane().remove(courseFormationJcb);
		
		courseFormationLabel = new JLabel("Le cours est attribu� � la formation "+(formation == null ? "?" : formation.getHeading()));
		addComponent(AMCWLayout,layoutConstraints,courseFormationLabel,1,8,6,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(15,10,15,10));
		AMCWFrame.getContentPane().add(courseFormationLabel);
	}
	
	public void setIdFormation(Long idFormation) {
	    try {
	        setCourseFormation(DaoManager.getFormationDao().getFormation(new Formation(idFormation), null));
        } catch (Exception e) {
            setCourseFormation(null);
        }
	}
	
	public void setIdGroup(Long idGroup) {
	    for (int i = 1; i < groups.length; ++i)
	        if (groups[i].getIdGroup().equals(idGroup)) {
	            courseGroupJcb.setSelectedIndex(i);
	            break;
	        }
	    courseGroupJcb.setEnabled(false);
	}
	
	public void setTypeCourse(String typeCourse) {
		if (TimetableDao.TYPE_TD.equals(typeCourse))
			td.setSelected(true);
		else if (TimetableDao.TYPE_TP.equals(typeCourse))
			tp.setSelected(true);
		else
		    magistral.setSelected(true);
	}
	

	private void initAddModifyCourseWindow() {
		teacherLabel = new JLabel("Le cours sera donn� par :");
		addComponent(AMCWLayout,layoutConstraints,teacherLabel,1,1,3,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(30,10,15,10));
		AMCWFrame.getContentPane().add(teacherLabel);
		
		if (teachersName == null)
		    teacherCourseJcb = new JComboBox();
		else
		    teacherCourseJcb = new JComboBox(teachersName);
		
		teacherPanel.setLayout(new BoxLayout(teacherPanel, BoxLayout.Y_AXIS));
		teacherPanel.add(teacherCourseJcb);
		teacherPanel.add(Box.createVerticalStrut(5));
		
		addComponent(AMCWLayout,layoutConstraints,teacherPanel,4,1,2,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(20,10,15,10));
		AMCWFrame.getContentPane().add(teacherPanel);
		
		teacherPlus = new JButton("+");
		teacherPlus.addActionListener(new ButtonPlusListener(teacherPlusPanel, teacherPanel, AMCWFrame, teachersName));
		
		teacherPlusPanel.setLayout(new BoxLayout(teacherPlusPanel, BoxLayout.Y_AXIS));
		teacherPlusPanel.add(teacherPlus);
		teacherPlusPanel.add(Box.createVerticalStrut(5));
		
		addComponent(AMCWLayout,layoutConstraints,teacherPlusPanel,6,1,GridBagConstraints.REMAINDER,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,15,10));
		AMCWFrame.getContentPane().add(teacherPlusPanel);
		
		if (teachersName == null) {
		    teacherLabel.setEnabled(false);
		    teacherCourseJcb.setEnabled(false);
		    teacherPlus.setEnabled(false);
		}
		
		
		
		dateCourseTheLabel = new JLabel("le");
		addComponent(AMCWLayout,layoutConstraints,dateCourseTheLabel,1,2,1,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(15,10,15,10));
		AMCWFrame.getContentPane().add(dateCourseTheLabel);
		
		dateCourse = new JDateChooser();
		dateCourse.setDateFormatString("dd MMMMM yyyy");
		addComponent(AMCWLayout,layoutConstraints,dateCourse,2,2,2,1,0.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(5,10,15,10));
		AMCWFrame.getContentPane().add(dateCourse);
		
		
		
		
		dureeCourseDeLabel = new JLabel("de");
		addComponent(AMCWLayout,layoutConstraints,dureeCourseDeLabel,1,3,1,1,0.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(15,10,15,10));
		AMCWFrame.getContentPane().add(dureeCourseDeLabel);
		
		startCourseHourJcb = new JComboBox();
		initNumberJcb(startCourseHourJcb, FIRST_HOUR, LAST_HOUR);
		addComponent(AMCWLayout,layoutConstraints,startCourseHourJcb,2,3,1,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(5,10,15,10));
		AMCWFrame.getContentPane().add(startCourseHourJcb);
		
		startCourseHourLabel = new JLabel("heure");
		addComponent(AMCWLayout,layoutConstraints,startCourseHourLabel,3,3,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(15,10,15,10));
		AMCWFrame.getContentPane().add(startCourseHourLabel);
		
		dureeCourseALabel = new JLabel("�");
		addComponent(AMCWLayout,layoutConstraints,dureeCourseALabel,4,3,1,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(15,10,15,10));
		AMCWFrame.getContentPane().add(dureeCourseALabel);
		
		endCourseHourJcb = new JComboBox();
		initNumberJcb(endCourseHourJcb, FIRST_HOUR, LAST_HOUR);
		addComponent(AMCWLayout,layoutConstraints,endCourseHourJcb,5,3,GridBagConstraints.RELATIVE,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(5,10,15,10));
		AMCWFrame.getContentPane().add(endCourseHourJcb);
		
		endCourseHourLabel = new JLabel("heure");
		addComponent(AMCWLayout,layoutConstraints,endCourseHourLabel,6,3,GridBagConstraints.REMAINDER,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(15,10,15,10));
		AMCWFrame.getContentPane().add(endCourseHourLabel);
		
		
		
		startCourseMinuteJcb = new JComboBox();
		initMinuteJcb(startCourseMinuteJcb);
		addComponent(AMCWLayout,layoutConstraints,startCourseMinuteJcb,2,4,1,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(5,10,15,10));
		AMCWFrame.getContentPane().add(startCourseMinuteJcb);
		
		startCourseMinuteLabel = new JLabel("min");
		addComponent(AMCWLayout,layoutConstraints,startCourseMinuteLabel,3,4,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(15,10,15,10));
		AMCWFrame.getContentPane().add(startCourseMinuteLabel);
		
		endCourseMinuteJcb = new JComboBox();
		initMinuteJcb(endCourseMinuteJcb);
		addComponent(AMCWLayout,layoutConstraints,endCourseMinuteJcb,5,4,GridBagConstraints.RELATIVE,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(5,10,15,10));
		AMCWFrame.getContentPane().add(endCourseMinuteJcb);
		
		endCourseMinuteLabel = new JLabel("min");
		addComponent(AMCWLayout,layoutConstraints,endCourseMinuteLabel,6,4,GridBagConstraints.REMAINDER,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(15,10,15,10));
		AMCWFrame.getContentPane().add(endCourseMinuteLabel);
		
		
		
		placeCourseLabel = new JLabel("dans le local ou les locaux :");
		addComponent(AMCWLayout,layoutConstraints,placeCourseLabel,1,5,3,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(15,10,15,10));
		AMCWFrame.getContentPane().add(placeCourseLabel);
		
		if (roomsName == null)
		    placeCourseJcb = new JComboBox();
		else
		    placeCourseJcb = new JComboBox(roomsName);
		
		placeCoursePanel.setLayout(new BoxLayout(placeCoursePanel, BoxLayout.Y_AXIS));
		placeCoursePanel.add(placeCourseJcb);
		placeCoursePanel.add(Box.createVerticalStrut(5));
		addComponent(AMCWLayout,layoutConstraints,placeCoursePanel,4,5,2,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,10,15,10));
		AMCWFrame.getContentPane().add(placeCoursePanel);
		
		placeCoursePlus = new JButton("+");
		placeCoursePlus.addActionListener(new ButtonPlusListener(placeCoursePlusPanel, placeCoursePanel, AMCWFrame, roomsName));
		
		placeCoursePlusPanel.setLayout(new BoxLayout(placeCoursePlusPanel, BoxLayout.Y_AXIS));
		placeCoursePlusPanel.add(placeCoursePlus);
		placeCoursePlusPanel.add(Box.createVerticalStrut(5));
		
		addComponent(AMCWLayout,layoutConstraints,placeCoursePlusPanel,6,5,GridBagConstraints.REMAINDER,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(5,10,15,10));
		AMCWFrame.getContentPane().add(placeCoursePlusPanel);
		
		if (roomsName == null) {
		    placeCourseLabel.setEnabled(false);
		    placeCourseJcb.setEnabled(false);
		    placeCoursePlus.setEnabled(false);
		}
		
		
		if (type == TYPE_ADD) {
			repeatCourseLabel = new JLabel("Sur combien de semaines se d�roulera le cours ?");
			addComponent(AMCWLayout,layoutConstraints,repeatCourseLabel,1,6,5,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(15,10,15,10));
			AMCWFrame.getContentPane().add(repeatCourseLabel);
			
			repeatCourseJcb = new JComboBox();
			initNumberJcb(repeatCourseJcb, 1, 30);
			addComponent(AMCWLayout,layoutConstraints,repeatCourseJcb,6,6,GridBagConstraints.REMAINDER,1,0.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(5,10,15,10));
			AMCWFrame.getContentPane().add(repeatCourseJcb);
		}
		
		
		
		courseEquipmentLabel = new JLabel("Le(s) mat�riel(s) utilis�(s) :");
		addComponent(AMCWLayout,layoutConstraints,courseEquipmentLabel,1,7,3,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(15,10,15,10));
		AMCWFrame.getContentPane().add(courseEquipmentLabel);
		
		if (materialsName == null)
		    courseEquipmentJcb = new JComboBox();
		else
		    courseEquipmentJcb = new JComboBox(materialsName);
		
		courseEquipmentPanel.setLayout(new BoxLayout(courseEquipmentPanel, BoxLayout.Y_AXIS));
		courseEquipmentPanel.add(courseEquipmentJcb);
		courseEquipmentPanel.add(Box.createVerticalStrut(5));
		
		addComponent(AMCWLayout,layoutConstraints,courseEquipmentPanel,4,7,2,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,10,15,10));
		AMCWFrame.getContentPane().add(courseEquipmentPanel);
		
		courseEquipmentPlus = new JButton("+");
		courseEquipmentPlus.addActionListener(new ButtonPlusListener(courseEquipmentPlusPanel, courseEquipmentPanel, AMCWFrame, materialsName));
		
		courseEquipmentPlusPanel.setLayout(new BoxLayout(courseEquipmentPlusPanel, BoxLayout.Y_AXIS));
		courseEquipmentPlusPanel.add(courseEquipmentPlus);
		courseEquipmentPlusPanel.add(Box.createVerticalStrut(5));
		
		addComponent(AMCWLayout,layoutConstraints,courseEquipmentPlusPanel,6,7,GridBagConstraints.REMAINDER,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(5,10,15,10));
		AMCWFrame.getContentPane().add(courseEquipmentPlusPanel);
		
		if (materialsName == null) {
		    courseEquipmentJcb.setEnabled(false);
		    courseEquipmentLabel.setEnabled(false);
		    courseEquipmentPlus.setEnabled(false);
		}
		
		
		
		courseFormationLabel = new JLabel("Le cours est attribu� � la formation :");
		addComponent(AMCWLayout,layoutConstraints,courseFormationLabel,1,8,4,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(15,10,15,10));
		AMCWFrame.getContentPane().add(courseFormationLabel);
		
		courseFormationJcb = new JComboBox();
		addComponent(AMCWLayout,layoutConstraints,courseFormationJcb,5,8,GridBagConstraints.REMAINDER,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,10,15,10));
		AMCWFrame.getContentPane().add(courseFormationJcb);
		
		
		courseGroupLabel = new JLabel("et plus pr�cis�ment, au groupe :");
		addComponent(AMCWLayout,layoutConstraints,courseGroupLabel,1,9,4,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(15,10,15,10));
		AMCWFrame.getContentPane().add(courseGroupLabel);
		
		courseGroupJcb = new JComboBox(groupsName);
		addComponent(AMCWLayout,layoutConstraints,courseGroupJcb,5,9,GridBagConstraints.REMAINDER,1,1.0,0.0,GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,new Insets(5,10,15,10));
		AMCWFrame.getContentPane().add(courseGroupJcb);
		
		
		
		typeCourseLabel = new JLabel("Choisissez le type de cours :");
		addComponent(AMCWLayout,layoutConstraints,typeCourseLabel,1,10,3,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(15,10,0,10));
		AMCWFrame.getContentPane().add(typeCourseLabel);
		
		magistral = new JRadioButton("Cours magistral",true);
		addComponent(AMCWLayout,layoutConstraints,magistral,1,11,2,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(5,10,20,10));
		AMCWFrame.getContentPane().add(magistral);
		
		td = new JRadioButton("TD");
		addComponent(AMCWLayout,layoutConstraints,td,3,11,1,1,1.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(5,10,20,10));
		AMCWFrame.getContentPane().add(td);
		
		tp = new JRadioButton("TP");
		addComponent(AMCWLayout,layoutConstraints,tp,4,11,GridBagConstraints.REMAINDER,1,1.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(5,10,20,10));
		AMCWFrame.getContentPane().add(tp);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(magistral);
		buttonGroup.add(td);
		buttonGroup.add(tp);
		
		
		ok = new JButton("OK");
		ok.addActionListener(new ActionOk());
		addComponent(AMCWLayout,layoutConstraints,ok,5,12,GridBagConstraints.RELATIVE,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		AMCWFrame.getContentPane().add(ok);
		
		annuler = new JButton("Annuler");
		annuler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	AMCWFrame.dispose();
            }
		});
		addComponent(AMCWLayout,layoutConstraints,annuler,6,12,GridBagConstraints.REMAINDER,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		AMCWFrame.getContentPane().add(annuler);
	}

	
	private void initNumberJcb(JComboBox jcb, int start, int end) {
		for(int i=start;i<=end;i++) {
			jcb.addItem(""+i);
		}
	}
	
	private void initMinuteJcb(JComboBox jcb) {
		String [] tabMin = new String[] {"00","15","30","45"};
		
		for (int i=0;i<tabMin.length;i++) {
			jcb.addItem(tabMin[i]);
			
		}
	}

	private void addComponent(GridBagLayout gbLayout,GridBagConstraints constraints,Component comp,int gridx, int gridy, int gridwidth, int gridheight, double weightx, double weighty, int anchor, int fill, Insets insets) {
		constraints. gridx= gridx;
		constraints. gridy = gridy;
		constraints. gridwidth= gridwidth;
		constraints. gridheight = gridheight;
		constraints.weightx = weightx;
		constraints.weighty = weighty;
		constraints.anchor = anchor;
		constraints.fill = fill;
		constraints.insets = insets;
		
		gbLayout.setConstraints(comp,constraints);
	}

    public void show() {
    	AMCWFrame.pack();
    	AMCWFrame.setResizable(false);
	    AMCWFrame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	    AMCWFrame.setLocationRelativeTo(null);
	    AMCWFrame.setVisible(true);
    }

    public void setStartHour(int start){
    	startCourseHourJcb.setSelectedIndex((start/4)+8 - FIRST_HOUR);
    	startCourseMinuteJcb.setSelectedIndex((start%4));
    }
    
    public void setDateCourse(Calendar date) {
        dateCourse.setDate(date.getTime());
    }
    
    public void setBeginDate(Calendar date){
    	startCourseHourJcb.setSelectedIndex(date.get(Calendar.HOUR_OF_DAY) - FIRST_HOUR);
    	startCourseMinuteJcb.setSelectedIndex(date.get(Calendar.MINUTE)/15);
    	dateCourse.setDate(date.getTime());
    }
    
    public Calendar getBeginDate() {
        Calendar beginDate = Calendar.getInstance();
	    beginDate.setTime(getDateCourse());
	    beginDate.set(Calendar.HOUR_OF_DAY, getStartHour());
	    beginDate.set(Calendar.MINUTE, getStartMinute());
	    beginDate.set(Calendar.SECOND, 0);
	    beginDate.set(Calendar.MILLISECOND, 0);
	    return beginDate;
    }

    public void setEndHour(int end){
    	endCourseHourJcb.setSelectedIndex(((end+1)/4)+8 - FIRST_HOUR);
    	endCourseMinuteJcb.setSelectedIndex(((end+1)%4));
    }
    
    public void setEndDate(Calendar date){
    	endCourseHourJcb.setSelectedIndex(date.get(Calendar.HOUR_OF_DAY) - FIRST_HOUR);
    	endCourseMinuteJcb.setSelectedIndex(date.get(Calendar.MINUTE)/15);
    	dateCourse.setDate(date.getTime());
    }
    
    public Calendar getEndDate() {
        Calendar endDate = Calendar.getInstance();
        endDate.setTime(getDateCourse());
        endDate.set(Calendar.HOUR_OF_DAY, getEndHour());
	    endDate.set(Calendar.MINUTE, getEndMinute());
	    endDate.set(Calendar.SECOND, 0);
	    endDate.set(Calendar.MILLISECOND, 0);
	    return endDate;
    }

    private int getStartHour(){
    	return Integer.parseInt((String) startCourseHourJcb.getSelectedItem());
    }
    
    private int getStartMinute(){
    	return Integer.parseInt((String) startCourseMinuteJcb.getSelectedItem());
    }

    private int getEndHour(){
        return Integer.parseInt((String) endCourseHourJcb.getSelectedItem());
    }
    
    private int getEndMinute(){
        return Integer.parseInt((String) endCourseMinuteJcb.getSelectedItem());
    }
    
    public boolean isOk() {
        return isOk;
    }
    
    private int checking() {
        if (getTeachers().size() == 0)
            return 1;
        if (getStartHour() == getEndHour() && getStartMinute() == getEndMinute())
            return 2;
        if (getStartHour() > getEndHour() || (getStartHour() == getEndHour() && getStartMinute() > getEndMinute()))
            return 8;

        try {
            Calendar beginCourse = getBeginDate();
            Calendar endCourse = getEndDate();
            UnavailabilityDao unavailabilityDao = DaoManager.getAvailabilityDao();
            UnavailabilityFilter filter = new UnavailabilityFilter();
            
            int nbWeeks = 1;
            if (repeatCourseJcb != null)
                nbWeeks = getNbWeeksCourse();

            for (int k = 0; k < nbWeeks; ++k) {
                beginCourse.set(Calendar.DAY_OF_YEAR, beginCourse.get(Calendar.DAY_OF_YEAR) + 7*k);
                endCourse.set(Calendar.DAY_OF_YEAR, endCourse.get(Calendar.DAY_OF_YEAR) + 7*k);
	            Collection unavailabilities = new ArrayList();
	
	            // v�rification que le groupe est libre
	            filter.setIdUnavailabilityType(unavailabilityDao.getTypeUnavailability(UnavailabilityDao.TYPE_GROUP));
	            filter.setIdUnavailabilitySubject(getCourseGroup().getIdGroup());
	            unavailabilities.addAll(unavailabilityDao.getUnavailabilities(filter));
	            
	            // v�rification que les enseignants sont libres
	            filter.setIdUnavailabilityType(unavailabilityDao.getTypeUnavailability(UnavailabilityDao.TYPE_TEACHER));
	            for (Iterator i = getTeachers().iterator(); i.hasNext(); ) {
	                User teacher = (User) i.next();
	                filter.setIdUnavailabilitySubject(teacher.getIdUser());
	                unavailabilities.addAll(unavailabilityDao.getUnavailabilities(filter));
	            }
	            
	            // v�rification que les locaux sont libres
	            filter.setIdUnavailabilityType(unavailabilityDao.getTypeUnavailability(UnavailabilityDao.TYPE_ROOM));
	            for (Iterator i = getPlaceCourse().iterator(); i.hasNext(); ) {
	                Room room = (Room) i.next();
	                filter.setIdUnavailabilitySubject(room.getIdRoom());
	                unavailabilities.addAll(unavailabilityDao.getUnavailabilities(filter));
	            }
	            
	            // v�rification que les mat�riels sont libres
	            filter.setIdUnavailabilityType(unavailabilityDao.getTypeUnavailability(UnavailabilityDao.TYPE_MATERIAL));
	            for (Iterator i = getCourseEquipment().iterator(); i.hasNext(); ) {
	                Material material = (Material) i.next();
	                filter.setIdUnavailabilitySubject(material.getIdMaterial());
	                unavailabilities.addAll(unavailabilityDao.getUnavailabilities(filter));
	            }
	
	            for (Iterator i = unavailabilities.iterator(); i.hasNext(); ) {
	                Unavailability unavailability = (Unavailability) i.next();
	                if (mainFrame.getCourseSelected() != null && unavailability.getIdCourse() != null && unavailability.getIdCourse().equals(mainFrame.getCourseSelected().getIdCourse()))
	                    continue;
	                if ((beginCourse.getTimeInMillis() < unavailability.getBeginDate().getTimeInMillis() && endCourse.getTimeInMillis() > unavailability.getBeginDate().getTimeInMillis()) ||
	                        (beginCourse.getTimeInMillis() < unavailability.getEndDate().getTimeInMillis() && endCourse.getTimeInMillis() > unavailability.getEndDate().getTimeInMillis()) ||
	                        (beginCourse.getTimeInMillis() >= unavailability.getBeginDate().getTimeInMillis() && endCourse.getTimeInMillis() <= unavailability.getEndDate().getTimeInMillis()) ||
	                        (beginCourse.getTimeInMillis() <= unavailability.getBeginDate().getTimeInMillis() && endCourse.getTimeInMillis() >= unavailability.getEndDate().getTimeInMillis())) {
	                    if (UnavailabilityDao.TYPE_GROUP.equals(unavailability.getIdUnavailabilityType().getNameUnavailabilityType()))
	                        return 3;
	                    if (UnavailabilityDao.TYPE_TEACHER.equals(unavailability.getIdUnavailabilityType().getNameUnavailabilityType())) {
	                        for (int j = 1; j < teachers.length; ++j)
	                            if (teachers[j].getIdUser().equals(unavailability.getIdUnavailabilitySubject()))
	                                errorSubject = teachersName[j];
	                        return 4;
	                    }
	                    if (UnavailabilityDao.TYPE_ROOM.equals(unavailability.getIdUnavailabilityType().getNameUnavailabilityType())) {
	                        for (int j = 1; j < rooms.length; ++j)
	                            if (rooms[j].getIdRoom().equals(unavailability.getIdUnavailabilitySubject()))
	                                errorSubject = roomsName[j];
	                        return 5;
	                    }
	                    if (UnavailabilityDao.TYPE_MATERIAL.equals(unavailability.getIdUnavailabilityType().getNameUnavailabilityType())) {
	                        for (int j = 1; j < materials.length; ++j)
	                            if (materials[j].getIdMaterial().equals(unavailability.getIdUnavailabilitySubject()))
	                                errorSubject = materialsName[j];
	                        return 6;
	                    }
	                }
	            }
            }
        } catch (Exception e) {
            return 7;
        }

        return 0;
    }

    private String errorSubject;

    private class ActionOk implements ActionListener {
        /* (non-Javadoc)
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent arg0) {
            String errorMessage = null;
            switch (checking()) {
            case 0:
                isOk = true;
                AMCWFrame.dispose();
                return;
            case 1:
                errorMessage = "Au moins un enseignant doit �tre s�lectionn�";
                break;
            case 2:
                errorMessage = "Le cours ne peut pas avoir une dur�e nulle";
                break;
            case 3:
                errorMessage = "Le groupe est indisponible pour cette p�riode";
                break;
            case 4:
                errorMessage = "L'enseignant '" + errorSubject + "' est indisponible pour cette p�riode";
                break;
            case 5:
                errorMessage = "Le local '" + errorSubject + "' est indisponible pour cette p�riode";
                break;
            case 6:
                errorMessage = "Le mat�riel '" + errorSubject + "' est indisponible pour cette p�riode";
                break;
            case 7:
                errorMessage = "Erreur lors de la v�rification des indisponibilit�s";
                break;
            case 8:
                errorMessage = "La date de d�but ne peut �tre inf�rieure � la date de fin";
                break;
            default:
                errorMessage = "Erreur inconnue";
            }
            JOptionPane.showMessageDialog(null, errorMessage, "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
