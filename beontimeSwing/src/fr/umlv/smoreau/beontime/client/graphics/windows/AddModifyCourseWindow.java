/*
 * 
 */
package fr.umlv.smoreau.beontime.client.graphics.windows;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.toedter.calendar.JDateChooser;

import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.model.Formation;
import fr.umlv.smoreau.beontime.model.Group;

/**
 * @author BeOnTime
 */
public class AddModifyCourseWindow {
	
	private static final String TITRE = "Placer un cours";
	
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
	
	
	
	private JPanel teacherPanel = new JPanel();
	private JPanel teacherPlusPanel = new JPanel();
	private JPanel placeCoursePanel = new JPanel();
	private JPanel placeCoursePlusPanel = new JPanel();
	private JPanel courseEquipmentPanel = new JPanel();
	private JPanel courseEquipmentPlusPanel = new JPanel();
	
	private JDialog AMCWFrame;
	private GridBagLayout AMCWLayout = new GridBagLayout();
	private GridBagConstraints layoutConstraints = new GridBagConstraints();
	
	
	public AddModifyCourseWindow () {
		
		AMCWFrame = new JDialog(MainFrame.getInstance().getMainFrame(), TITRE, true);
		AMCWFrame.getContentPane().setLayout(AMCWLayout);
		
		initAddModifyCourseWindow();  
		
	}
	
	
	public Collection getTeachers() {
		
		int nbteachers = teacherPanel.getComponentCount();
		
		ArrayList list = new ArrayList();
		Component [] components = teacherPanel.getComponents();
		
		for(int i=0; i<nbteachers;i++) {
			list.add(((JComboBox)components[i]).getSelectedItem().toString());
		}
		
		return list;
	}
	
	
	public Date getDateCourse() {
		return dateCourse.getDate();
	}
	
	
	public Collection getPlaceCourse() {
		
		int nbteachers = placeCoursePanel.getComponentCount();
		
		ArrayList list = new ArrayList();
		Component [] components = placeCoursePanel.getComponents();
		
		for(int i=0; i<nbteachers;i++) {
			list.add(((JComboBox)components[i]).getSelectedItem().toString());
		}
		
		return list;
	}
	
	public int getNbWeeksCourse(){
		
		return Integer.parseInt(repeatCourseJcb.getSelectedItem().toString());
		
	}
	
	public Collection getCourseEquipment() {
		
		int nbteachers = courseEquipmentPanel.getComponentCount();
		
		ArrayList list = new ArrayList();
		Component [] components = courseEquipmentPanel.getComponents();
		
		for(int i=0; i<nbteachers;i++) {
			list.add(((JComboBox)components[i]).getSelectedItem().toString());
		}
		
		return list;
	}
	
	public String getCourseFormation() {
		return courseFormationJcb.getSelectedItem().toString();
	}
	
	public String getCourseGroup() {
		return courseGroupJcb.getSelectedItem().toString();
	}
	
	public String getTypeCourse() {
		
		if (magistral.isSelected())
			return "cours magistraux";
		
		else if(td.isSelected())
			return "travaux dirigés";
		
		return "travaux pratiques";
	}
	
	

	
	public void setNbWeeksCourse(int nb){	
		repeatCourseJcb.addItem(""+nb);
	}
	
	
	
	public void setCourseFormation(Formation formation) {
		
		AMCWFrame.getContentPane().remove(courseFormationLabel);
		AMCWFrame.getContentPane().remove(courseFormationJcb);
		
		courseFormationLabel = new JLabel("Le cours est attribué à la formation "+formation.getHeading());
		addComponent(AMCWLayout,layoutConstraints,courseFormationLabel,1,8,6,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(15,10,15,10));
		AMCWFrame.getContentPane().add(courseFormationLabel);
	
	}
	
	public void setCourseGroup(Group group) {
		courseGroupJcb.addItem(group.getHeading());
	}
	
	public void setTypeCourse(String typeCourse) {
		
		if (typeCourse.compareTo("cours magistraux") == 0)
			magistral.setSelected(true);
		
		else if(typeCourse.compareTo("travaux dirigés") == 0)
			td.setSelected(true);
		
		tp.setSelected(true);
	}
	
	
	
	
	private void initAddModifyCourseWindow() {
		
		
		teacherLabel = new JLabel("Le cours sera donné par :");
		addComponent(AMCWLayout,layoutConstraints,teacherLabel,1,1,3,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(30,10,15,10));
		AMCWFrame.getContentPane().add(teacherLabel);
		
		teacherCourseJcb = new JComboBox();
		
		teacherPanel.setLayout(new BoxLayout(teacherPanel, BoxLayout.Y_AXIS));
		teacherPanel.add(teacherCourseJcb);
		teacherPanel.add(Box.createVerticalStrut(5));
		
		addComponent(AMCWLayout,layoutConstraints,teacherPanel,4,1,2,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(20,10,15,10));
		AMCWFrame.getContentPane().add(teacherPanel);
		
		teacherPlus = new JButton("+");
		teacherPlus.addActionListener(new ButtonPlusListener(teacherPlusPanel, teacherPanel, AMCWFrame));
		
		teacherPlusPanel.setLayout(new BoxLayout(teacherPlusPanel, BoxLayout.Y_AXIS));
		teacherPlusPanel.add(teacherPlus);
		teacherPlusPanel.add(Box.createVerticalStrut(5));
		
		addComponent(AMCWLayout,layoutConstraints,teacherPlusPanel,6,1,GridBagConstraints.REMAINDER,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,15,10));
		AMCWFrame.getContentPane().add(teacherPlusPanel);
		
		
		
		dateCourseTheLabel = new JLabel("le");
		addComponent(AMCWLayout,layoutConstraints,dateCourseTheLabel,1,2,1,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(15,10,15,10));
		AMCWFrame.getContentPane().add(dateCourseTheLabel);
		
		dateCourse = new JDateChooser();
		addComponent(AMCWLayout,layoutConstraints,dateCourse,2,2,2,1,0.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(5,10,15,10));
		AMCWFrame.getContentPane().add(dateCourse);
		
		
		
		
		dureeCourseDeLabel = new JLabel("de");
		addComponent(AMCWLayout,layoutConstraints,dureeCourseDeLabel,1,3,1,1,0.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(15,10,15,10));
		AMCWFrame.getContentPane().add(dureeCourseDeLabel);
		
		startCourseHourJcb = new JComboBox();
		initNumberJcb(startCourseHourJcb, 0, 23);
		addComponent(AMCWLayout,layoutConstraints,startCourseHourJcb,2,3,1,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(5,10,15,10));
		AMCWFrame.getContentPane().add(startCourseHourJcb);
		
		startCourseHourLabel = new JLabel("heure");
		addComponent(AMCWLayout,layoutConstraints,startCourseHourLabel,3,3,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(15,10,15,10));
		AMCWFrame.getContentPane().add(startCourseHourLabel);
		
		dureeCourseALabel = new JLabel("à");
		addComponent(AMCWLayout,layoutConstraints,dureeCourseALabel,4,3,1,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(15,10,15,10));
		AMCWFrame.getContentPane().add(dureeCourseALabel);
		
		endCourseHourJcb = new JComboBox();
		initNumberJcb(endCourseHourJcb, 0, 23);
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
		
		placeCourseJcb = new JComboBox();
		
		placeCoursePanel.setLayout(new BoxLayout(placeCoursePanel, BoxLayout.Y_AXIS));
		placeCoursePanel.add(placeCourseJcb);
		placeCoursePanel.add(Box.createVerticalStrut(5));
		addComponent(AMCWLayout,layoutConstraints,placeCoursePanel,4,5,2,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,10,15,10));
		AMCWFrame.getContentPane().add(placeCoursePanel);
		
		
		
		placeCoursePlus = new JButton("+");
		placeCoursePlus.addActionListener(new ButtonPlusListener(placeCoursePlusPanel, placeCoursePanel, AMCWFrame));
		
		placeCoursePlusPanel.setLayout(new BoxLayout(placeCoursePlusPanel, BoxLayout.Y_AXIS));
		placeCoursePlusPanel.add(placeCoursePlus);
		placeCoursePlusPanel.add(Box.createVerticalStrut(5));
		
		addComponent(AMCWLayout,layoutConstraints,placeCoursePlusPanel,6,5,GridBagConstraints.REMAINDER,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(5,10,15,10));
		AMCWFrame.getContentPane().add(placeCoursePlusPanel);
		
		
		
		repeatCourseLabel = new JLabel("Sur combien de semaines se déroulera le cours ?");
		addComponent(AMCWLayout,layoutConstraints,repeatCourseLabel,1,6,5,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(15,10,15,10));
		AMCWFrame.getContentPane().add(repeatCourseLabel);
		
		repeatCourseJcb = new JComboBox();
		initNumberJcb(repeatCourseJcb, 1, 30);
		addComponent(AMCWLayout,layoutConstraints,repeatCourseJcb,6,6,GridBagConstraints.REMAINDER,1,0.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(5,10,15,10));
		AMCWFrame.getContentPane().add(repeatCourseJcb);
		
		
		
		courseEquipmentLabel = new JLabel("Le(s) matériel(s) utilisé(s) :");
		addComponent(AMCWLayout,layoutConstraints,courseEquipmentLabel,1,7,3,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(15,10,15,10));
		AMCWFrame.getContentPane().add(courseEquipmentLabel);
		
		courseEquipmentJcb = new JComboBox();
		
		courseEquipmentPanel.setLayout(new BoxLayout(courseEquipmentPanel, BoxLayout.Y_AXIS));
		courseEquipmentPanel.add(courseEquipmentJcb);
		courseEquipmentPanel.add(Box.createVerticalStrut(5));
		
		addComponent(AMCWLayout,layoutConstraints,courseEquipmentPanel,4,7,2,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,10,15,10));
		AMCWFrame.getContentPane().add(courseEquipmentPanel);
		
		courseEquipmentPlus = new JButton("+");
		courseEquipmentPlus.addActionListener(new ButtonPlusListener(courseEquipmentPlusPanel, courseEquipmentPanel, AMCWFrame));
		
		courseEquipmentPlusPanel.setLayout(new BoxLayout(courseEquipmentPlusPanel, BoxLayout.Y_AXIS));
		courseEquipmentPlusPanel.add(courseEquipmentPlus);
		courseEquipmentPlusPanel.add(Box.createVerticalStrut(5));
		
		addComponent(AMCWLayout,layoutConstraints,courseEquipmentPlusPanel,6,7,GridBagConstraints.REMAINDER,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(5,10,15,10));
		AMCWFrame.getContentPane().add(courseEquipmentPlusPanel);
		
		
		
		courseFormationLabel = new JLabel("Le cours est attribué à la formation :");
		addComponent(AMCWLayout,layoutConstraints,courseFormationLabel,1,8,4,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(15,10,15,10));
		AMCWFrame.getContentPane().add(courseFormationLabel);
		
		courseFormationJcb = new JComboBox();
		addComponent(AMCWLayout,layoutConstraints,courseFormationJcb,5,8,GridBagConstraints.REMAINDER,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,10,15,10));
		AMCWFrame.getContentPane().add(courseFormationJcb);
		
		
		courseGroupLabel = new JLabel("et plus précisément, au groupe :");
		addComponent(AMCWLayout,layoutConstraints,courseGroupLabel,1,9,4,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(15,10,15,10));
		AMCWFrame.getContentPane().add(courseGroupLabel);
		
		courseGroupJcb = new JComboBox();
		courseGroupJcb.addItem("Tous");
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
		addComponent(AMCWLayout,layoutConstraints,ok,5,12,GridBagConstraints.RELATIVE,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		AMCWFrame.getContentPane().add(ok);
		
		annuler = new JButton("Annuler");
		addComponent(AMCWLayout,layoutConstraints,annuler,6,12,GridBagConstraints.REMAINDER,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		AMCWFrame.getContentPane().add(annuler);
	}
	
	
	
	
	private void initNumberJcb(JComboBox jcb, int start, int end) {
		
		for(int i=start;i<=end;i++) {
			jcb.addItem(""+i);
		}
	}
	
	private void initMinuteJcb(JComboBox jcb) {
		
		for(int i=0;i<10;i++) {
			jcb.addItem("0"+i);
		}
		for(int i=10;i<=60;i++) {
			jcb.addItem(""+i);
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
	
	
	public static void main(String[] args){
		
		MainFrame frame = MainFrame.getInstance();
		frame.open();
		
		AddModifyCourseWindow form = new AddModifyCourseWindow();
		form.show();
		
	}

    public void show() {
    	AMCWFrame.pack();
    	AMCWFrame.setResizable(false);
	    AMCWFrame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	    AMCWFrame.setVisible(true);
    }
    public void setStartHour(int start){
    	startCourseHourJcb.setSelectedIndex((start/4)+8);
    	Double d_start=new Double(start);
    	Double d=new Double(Math.IEEEremainder(d_start.doubleValue(),4.0));
    	startCourseMinuteJcb.setSelectedIndex(d.intValue()*15);
    }
    public void setEndHour(int end){
    	endCourseHourJcb.setSelectedIndex((end/4)+8);
    	Double e_start=new Double(end);
    	Double e=new Double(Math.IEEEremainder(e_start.doubleValue(),4.0));
    	endCourseMinuteJcb.setSelectedIndex((e.intValue()+1)*15);
    }
    public int getStartHour(){
    	
    	int ind_hour=startCourseHourJcb.getSelectedIndex();
    	int ind_min=startCourseMinuteJcb.getSelectedIndex();
    	int ret;
    	if (ind_hour>7){
    		ret=(ind_hour-8)*4;
    	}
    	else{
    		ret=0;
    	}
    	ret=ret+(ind_min/15);
    	return ret;
    	
    }
    public int getEndHour(){
    	int ind_hour=endCourseHourJcb.getSelectedIndex();
    	int ind_min=endCourseMinuteJcb.getSelectedIndex();
    	int ret;
    	if (ind_hour>7){
    		ret=(ind_hour-8)*4;
    	}
    	else{
    		ret=0;
    	}
    	ret=ret+(ind_min/15)-1;
    	return ret;
    }

}
