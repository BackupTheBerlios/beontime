/*
 * 
 */
package fr.umlv.smoreau.beontime.client.graphics.windows;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import com.toedter.calendar.JDateChooser;

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
	private JButton CourseEquipmentPlus;
	private JButton ok;
	private JButton annuler;
	
	private JFrame AMCWFrame;
	private GridBagLayout AMCWLayout = new GridBagLayout();
    private GridBagConstraints layoutConstraints = new GridBagConstraints();
    
    
    public AddModifyCourseWindow () {
    	
    	AMCWFrame = new JFrame(TITRE);
    	AMCWFrame.getContentPane().setLayout(AMCWLayout);
        
    	initAddModifyCourseWindow();  
        
    }


    public void initAddModifyCourseWindow() {
    	
    	
    	teacherLabel = new JLabel("Le cours sera donné par :");
    	addComponent(AMCWLayout,layoutConstraints,teacherLabel,3,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,0,10));
    	AMCWFrame.getContentPane().add(teacherLabel);

    	teacherCourseJcb = new JComboBox();
    	addComponent(AMCWLayout,layoutConstraints,teacherCourseJcb,GridBagConstraints.RELATIVE,1,1.0,0.0,GridBagConstraints.EAST,GridBagConstraints.HORIZONTAL,new Insets(0,10,0,10));
    	AMCWFrame.getContentPane().add(teacherCourseJcb);
    	
    	teacherPlus = new JButton("+");
    	addComponent(AMCWLayout,layoutConstraints,teacherPlus,GridBagConstraints.REMAINDER,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,0,10));
    	AMCWFrame.getContentPane().add(teacherPlus);
    	
    	
    	
    	dateCourseTheLabel = new JLabel("le");
    	addComponent(AMCWLayout,layoutConstraints,dateCourseTheLabel,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,0,10));
    	AMCWFrame.getContentPane().add(dateCourseTheLabel);
    	
    	dateCourse = new JDateChooser();
    	addComponent(AMCWLayout,layoutConstraints,dateCourse,GridBagConstraints.REMAINDER,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(0,10,0,10));
    	AMCWFrame.getContentPane().add(dateCourse);
    	
    	
    	
    	
    	dureeCourseDeLabel = new JLabel("de");
    	addComponent(AMCWLayout,layoutConstraints,dureeCourseDeLabel,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,0,10));
    	AMCWFrame.getContentPane().add(dureeCourseDeLabel);
    	
    	startCourseHourJcb = new JComboBox();
    	initCourseHourJcb(startCourseHourJcb);
    	addComponent(AMCWLayout,layoutConstraints,startCourseHourJcb,1,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(0,10,0,10));
    	AMCWFrame.getContentPane().add(startCourseHourJcb);
    	
    	startCourseHourLabel = new JLabel("heure");
    	addComponent(AMCWLayout,layoutConstraints,startCourseHourLabel,1,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(20,10,0,10));
    	AMCWFrame.getContentPane().add(startCourseHourLabel);
    	
    	dureeCourseALabel = new JLabel("à");
    	addComponent(AMCWLayout,layoutConstraints,dureeCourseALabel,1,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(20,10,0,10));
    	AMCWFrame.getContentPane().add(dureeCourseALabel);
    	
    	endCourseHourJcb = new JComboBox();
    	initCourseHourJcb(endCourseHourJcb);
    	addComponent(AMCWLayout,layoutConstraints,endCourseHourJcb,GridBagConstraints.RELATIVE,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(0,10,0,10));
    	AMCWFrame.getContentPane().add(endCourseHourJcb);
    	
    	endCourseHourLabel = new JLabel("heure");
    	addComponent(AMCWLayout,layoutConstraints,endCourseHourLabel,GridBagConstraints.REMAINDER,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(10,10,0,10));
    	AMCWFrame.getContentPane().add(endCourseHourLabel);
    	
    	
    	
    	startCourseMinuteJcb = new JComboBox();
    	initCourseMinuteJcb(startCourseMinuteJcb);
    	addComponent(AMCWLayout,layoutConstraints,startCourseMinuteJcb,1,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(0,10,0,10));
    	AMCWFrame.getContentPane().add(startCourseMinuteJcb);
    	
    	startCourseMinuteLabel = new JLabel("min");
    	addComponent(AMCWLayout,layoutConstraints,startCourseMinuteLabel,1,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(20,10,0,10));
    	AMCWFrame.getContentPane().add(startCourseMinuteLabel);
    	
    	endCourseMinuteJcb = new JComboBox();
    	initCourseMinuteJcb(endCourseMinuteJcb);
    	addComponent(AMCWLayout,layoutConstraints,endCourseMinuteJcb,GridBagConstraints.RELATIVE,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(0,10,0,10));
    	AMCWFrame.getContentPane().add(endCourseMinuteJcb);
    	
    	endCourseMinuteLabel = new JLabel("min");
    	addComponent(AMCWLayout,layoutConstraints,endCourseMinuteLabel,GridBagConstraints.REMAINDER,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(20,10,0,10));
    	AMCWFrame.getContentPane().add(endCourseMinuteLabel);

    	
    	
    	
    	placeCourseLabel = new JLabel("dans le local ou les locaux");
    	addComponent(AMCWLayout,layoutConstraints,placeCourseLabel,3,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,0,10));
    	AMCWFrame.getContentPane().add(placeCourseLabel);
    	
    	placeCourseJcb = new JComboBox();
    	addComponent(AMCWLayout,layoutConstraints,placeCourseJcb,GridBagConstraints.RELATIVE,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(0,10,0,10));
    	AMCWFrame.getContentPane().add(placeCourseJcb);

    	placeCoursePlus = new JButton("+");
    	addComponent(AMCWLayout,layoutConstraints,placeCoursePlus,GridBagConstraints.REMAINDER,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,0,10));
    	AMCWFrame.getContentPane().add(placeCoursePlus);
    	

    	
    	repeatCourseLabel = new JLabel("Sur combien de semaines se déroulera le cours ?");
    	addComponent(AMCWLayout,layoutConstraints,repeatCourseLabel,5,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,0,10));
    	AMCWFrame.getContentPane().add(repeatCourseLabel);
    	
    	repeatCourseJcb = new JComboBox();
    	addComponent(AMCWLayout,layoutConstraints,repeatCourseJcb,GridBagConstraints.REMAINDER,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(0,10,0,10));
    	AMCWFrame.getContentPane().add(repeatCourseJcb);
   
    	
    	
    	courseEquipmentLabel = new JLabel("Le(s)matériel(s) utilisé(s) :");
    	addComponent(AMCWLayout,layoutConstraints,courseEquipmentLabel,3,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,0,10));
    	AMCWFrame.getContentPane().add(courseEquipmentLabel);
    	
    	courseEquipmentJcb = new JComboBox();
    	addComponent(AMCWLayout,layoutConstraints,courseEquipmentJcb,2,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(0,10,0,10));
    	AMCWFrame.getContentPane().add(courseEquipmentJcb);
    	
    	CourseEquipmentPlus = new JButton("+");
    	addComponent(AMCWLayout,layoutConstraints,CourseEquipmentPlus,GridBagConstraints.REMAINDER,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,0,10));
    	AMCWFrame.getContentPane().add(CourseEquipmentPlus);
    	
    	
    	
    	courseFormationLabel = new JLabel("Le cours est attribué à la formation :");
    	addComponent(AMCWLayout,layoutConstraints,courseFormationLabel,4,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,0,10));
    	AMCWFrame.getContentPane().add(courseFormationLabel);
    	
    	courseFormationJcb = new JComboBox();
    	addComponent(AMCWLayout,layoutConstraints,courseFormationJcb,GridBagConstraints.REMAINDER,1,1.0,0.0,GridBagConstraints.EAST,GridBagConstraints.HORIZONTAL,new Insets(0,10,0,10));
    	AMCWFrame.getContentPane().add(courseFormationJcb);
    	
    	
    	courseGroupLabel = new JLabel("et plus précisément, au groupe :");
    	addComponent(AMCWLayout,layoutConstraints,courseGroupLabel,4,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,0,10));
    	AMCWFrame.getContentPane().add(courseGroupLabel);

    	courseGroupJcb = new JComboBox();
    	addComponent(AMCWLayout,layoutConstraints,courseGroupJcb,GridBagConstraints.REMAINDER,1,1.0,0.0,GridBagConstraints.EAST,GridBagConstraints.HORIZONTAL,new Insets(0,10,0,10));
    	AMCWFrame.getContentPane().add(courseGroupJcb);
    	
    	
    	
    	typeCourseLabel = new JLabel("Choisissez le type de cours :");
    	addComponent(AMCWLayout,layoutConstraints,typeCourseLabel,GridBagConstraints.REMAINDER,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,0,10));
    	AMCWFrame.getContentPane().add(typeCourseLabel);
    	
    	magistral = new JRadioButton("Cours magistral",true);
		addComponent(AMCWLayout,layoutConstraints,magistral,1,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(5,10,10,10));
		AMCWFrame.getContentPane().add(magistral);
		
    	td = new JRadioButton("TD");
		addComponent(AMCWLayout,layoutConstraints,td,1,1,1.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(5,10,10,10));
		AMCWFrame.getContentPane().add(td);
		
    	tp = new JRadioButton("TP");
		addComponent(AMCWLayout,layoutConstraints,tp,GridBagConstraints.REMAINDER,1,1.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(5,10,10,10));
		AMCWFrame.getContentPane().add(tp);
    
    	ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(magistral);
		buttonGroup.add(td);
		buttonGroup.add(tp);
    
    
		ok = new JButton("OK");
		addComponent(AMCWLayout,layoutConstraints,ok,GridBagConstraints.RELATIVE,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		AMCWFrame.getContentPane().add(ok);
		
		annuler = new JButton("Annuler");
		addComponent(AMCWLayout,layoutConstraints,annuler,GridBagConstraints.REMAINDER,1,0.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(20,10,10,10));
		AMCWFrame.getContentPane().add(annuler);
    }


    private void initCourseHourJcb(JComboBox jcb) {

    	for(int i=0;i<24;i++) {
    		jcb.addItem(""+i);
    	}
    }
    
    private void initCourseMinuteJcb(JComboBox jcb) {

    	for(int i=0;i<10;i++) {
    		jcb.addItem("0"+i);
    	}
    	for(int i=10;i<=60;i++) {
    		jcb.addItem(""+i);
    	}

    }
    
	
	
    /* (non-Javadoc)
     * @see fr.umlv.smoreau.beontimeSwing.graphics.windows.Window#show(java.lang.Object[])
     */
    public void show() {
    	AMCWFrame.pack();
	    AMCWFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    AMCWFrame.setVisible(true);
    }

	    
    public void addComponent(GridBagLayout gbLayout,GridBagConstraints constraints,Component comp,int gridwidth, int gridheight, double weightx, double weighty, int anchor, int fill, Insets insets) {
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
     			
     	AddModifyCourseWindow form = new AddModifyCourseWindow();
     	form.show();
     			
     }
    
}
