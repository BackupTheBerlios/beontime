/*
 * 
 */
package fr.umlv.smoreau.beontime.client.graphics.windows;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

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
	private JLabel CourseEquipmentLabel;
	private JLabel CourseFormationLabel;
	private JLabel CourseGroupLabel;
	private JLabel typeCourseLabel;
	
	private JLabel dateCourseTheLabel;
	private JLabel startCourseHourLabel;
	private JLabel startCourseMinuteLabel;
	private JLabel endCourseHourLabel;
	private JLabel endCourseMinuteLabel;
	private JLabel dureeCourseDeLabel;
	private JLabel dureeCourseALabel;
	
	private JComboBox teacherCourseJcb;
	private JComboBox startUnavailabilitHourJcb;
	private JComboBox startUnavailabilitMinuteJcb;
	private JComboBox endUnavailabilitHourJcb;
	private JComboBox endUnavailabilitMinuteJcb;
	private JDateChooser placeCourseJcb;
	private JComboBox repeatCourseJcb;
	private JComboBox courseEquipmentJcb;
	private JComboBox courseFormationJcb;
	private JComboBox CourseGroupJcb;
	
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
    	addComponent(AMCWLayout,layoutConstraints,teacherLabel,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,0,10));
    	AMCWFrame.getContentPane().add(teacherLabel);

    	teacherCourseJcb = new JComboBox();
    	addComponent(AMCWLayout,layoutConstraints,teacherCourseJcb,GridBagConstraints.RELATIVE,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(0,10,0,10));
    	AMCWFrame.getContentPane().add(teacherCourseJcb);
    	
    	teacherPlus = new JButton("+");
    	addComponent(AMCWLayout,layoutConstraints,teacherPlus,GridBagConstraints.REMAINDER,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,0,10));
    	AMCWFrame.getContentPane().add(teacherPlus);
    	
    	dateCourseTheLabel = new JLabel("le");
    	addComponent(AMCWLayout,layoutConstraints,dateCourseTheLabel,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,0,10));
    	AMCWFrame.getContentPane().add(dateCourseTheLabel);
    	
    	dateCourse = new JDateChooser();
    	addComponent(AMCWLayout,layoutConstraints,dateCourse,GridBagConstraints.REMAINDER,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(0,10,0,10));
    	AMCWFrame.getContentPane().add(dateCourse);
    	
    	dureeCourseDeLabel = new JLabel("de");
    	addComponent(AMCWLayout,layoutConstraints,dureeCourseDeLabel,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,0,10));
    	AMCWFrame.getContentPane().add(dureeCourseDeLabel);
    	
    
    	
    	
    	
    	
    	
    	
    	
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
