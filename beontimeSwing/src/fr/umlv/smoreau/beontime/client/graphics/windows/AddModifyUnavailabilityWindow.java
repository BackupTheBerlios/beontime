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
import javax.swing.JTextArea;

import com.toedter.calendar.JDateChooser;




/**
 * @author BeOnTime
 */
public class AddModifyUnavailabilityWindow {
	
	
	private static final String TITRE = "Ajouter une indisponibilité";
	
	private JLabel startUnavailabilityLabel;
	private JLabel endUnavailabilityLabel;
	private JLabel repeatUnavailabilityLabel;
	private JLabel typeUnavailabilityLabel;
	private JLabel subjectUnavailabilityLabel;
	private JLabel descriptionLabel;
	
	private JLabel theLabel;
	private JLabel hourLabel;
	private JLabel minuteLabel;
	private JLabel weeksLabel;
	
	private JDateChooser startUnavailabilitDate;
	private JDateChooser endUnavailabilitDate;
	
	private JComboBox startUnavailabilitHourJcb;
	private JComboBox startUnavailabilitMinuteJcb;
	private JComboBox endUnavailabilitHourJcb;
	private JComboBox endUnavailabilitMinuteJcb;
	private JComboBox repeatUnavailabilityJcb;
	private JComboBox typeUnavailabilityJcb;
	private JComboBox SubjectUnavailabilityJcb;
	
	private JTextArea descriptionTextArea;
	
	private JButton ok;
	private JButton annuler;
	
	private JFrame AMUWFrame;
	private GridBagLayout AMUWLayout = new GridBagLayout();
    private GridBagConstraints layoutConstraints = new GridBagConstraints();
	

    
    public AddModifyUnavailabilityWindow() {
    	
    	AMUWFrame = new JFrame();
    	AMUWFrame.getContentPane().setLayout(AMUWLayout);
        
    	initAddModifyUnavailabilityWindow();  
        
    }


    public void initAddModifyUnavailabilityWindow() {
    	
    	
    	startUnavailabilityLabel = new JLabel("Début d'indisponibilité :");
    	addComponent(AMUWLayout,layoutConstraints,startUnavailabilityLabel,GridBagConstraints.REMAINDER,1,1.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(10,10,0,10));
    	AMUWFrame.getContentPane().add(startUnavailabilityLabel);
    	
    	startUnavailabilitHourJcb = new JComboBox();
    	addComponent(AMUWLayout,layoutConstraints,startUnavailabilitHourJcb,GridBagConstraints.RELATIVE,1,0.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(0,10,0,10));
    	AMUWFrame.getContentPane().add(startUnavailabilitHourJcb);
    	
    	hourLabel = new JLabel("heure");
    	addComponent(AMUWLayout,layoutConstraints,hourLabel,GridBagConstraints.REMAINDER,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(0,10,0,10));
    	AMUWFrame.getContentPane().add(hourLabel);
    	
    	theLabel = new JLabel("le");
    	addComponent(AMUWLayout,layoutConstraints,theLabel,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(0,10,0,10));
    	AMUWFrame.getContentPane().add(theLabel);
    	
    	startUnavailabilitMinuteJcb = new JComboBox();
    	addComponent(AMUWLayout,layoutConstraints,startUnavailabilitMinuteJcb,GridBagConstraints.RELATIVE,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(0,10,10,10));
    	AMUWFrame.getContentPane().add(startUnavailabilitMinuteJcb);
    	
    	minuteLabel = new JLabel("min");
    	addComponent(AMUWLayout,layoutConstraints,minuteLabel,GridBagConstraints.REMAINDER,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(0,10,0,10));
    	AMUWFrame.getContentPane().add(minuteLabel);
    	
    	
    	
    	
    	endUnavailabilityLabel = new JLabel("Fin d'indisponibilité :");
    	addComponent(AMUWLayout,layoutConstraints,startUnavailabilityLabel,GridBagConstraints.REMAINDER,1,1.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(10,10,0,10));
    	AMUWFrame.getContentPane().add(startUnavailabilityLabel);
    	
    	endUnavailabilitHourJcb = new JComboBox();
    	addComponent(AMUWLayout,layoutConstraints,endUnavailabilitHourJcb,GridBagConstraints.RELATIVE,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(0,10,0,10));
    	AMUWFrame.getContentPane().add(endUnavailabilitHourJcb);
    	
    	addComponent(AMUWLayout,layoutConstraints,hourLabel,GridBagConstraints.REMAINDER,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(0,10,0,10));
    	AMUWFrame.getContentPane().add(hourLabel);
    	
    	addComponent(AMUWLayout,layoutConstraints,theLabel,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(0,10,0,10));
    	AMUWFrame.getContentPane().add(theLabel);
    	
    	endUnavailabilitMinuteJcb = new JComboBox();
    	addComponent(AMUWLayout,layoutConstraints,endUnavailabilitMinuteJcb,GridBagConstraints.RELATIVE,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(0,10,10,10));
    	AMUWFrame.getContentPane().add(endUnavailabilitMinuteJcb);
    	
    	addComponent(AMUWLayout,layoutConstraints,minuteLabel,GridBagConstraints.REMAINDER,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(0,10,0,10));
    	AMUWFrame.getContentPane().add(minuteLabel);
    	
    	
    	
    	
    	repeatUnavailabilityLabel = new JLabel("Répéter cette indisponibilité sur");
    	addComponent(AMUWLayout,layoutConstraints,repeatUnavailabilityLabel,2,1,1.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(10,10,10,10));
    	AMUWFrame.getContentPane().add(repeatUnavailabilityLabel);
    	
    	repeatUnavailabilityJcb = new JComboBox();
    	addComponent(AMUWLayout,layoutConstraints,repeatUnavailabilityJcb,1,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(10,10,10,10));
    	AMUWFrame.getContentPane().add(repeatUnavailabilityJcb);
    	
    	weeksLabel = new JLabel("semaines");
    	addComponent(AMUWLayout,layoutConstraints,weeksLabel,GridBagConstraints.REMAINDER,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(10,10,10,10));
    	AMUWFrame.getContentPane().add(weeksLabel);
    	
    	
    	
    	typeUnavailabilityLabel = new JLabel("Type d'indisponibilité :");
    	addComponent(AMUWLayout,layoutConstraints,typeUnavailabilityLabel,2,1,1.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(10,10,10,10));
    	AMUWFrame.getContentPane().add(typeUnavailabilityLabel);
    	
    	typeUnavailabilityJcb = new JComboBox();
    	addComponent(AMUWLayout,layoutConstraints,typeUnavailabilityJcb,GridBagConstraints.RELATIVE,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(10,10,10,10));
    	AMUWFrame.getContentPane().add(typeUnavailabilityJcb);
    	
    	
    	
    	subjectUnavailabilityLabel = new JLabel("<type> indisponible :");
    	addComponent(AMUWLayout,layoutConstraints,typeUnavailabilityLabel,2,1,1.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(10,10,10,10));
    	AMUWFrame.getContentPane().add(typeUnavailabilityLabel);
    	
    	typeUnavailabilityJcb = new JComboBox();
    	addComponent(AMUWLayout,layoutConstraints,typeUnavailabilityJcb,GridBagConstraints.RELATIVE,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(10,10,10,10));
    	AMUWFrame.getContentPane().add(typeUnavailabilityJcb);
    	
    	

    	descriptionLabel = new JLabel("Description :");
    	addComponent(AMUWLayout,layoutConstraints,descriptionLabel,GridBagConstraints.REMAINDER,1,1.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(10,10,0,10));
    	AMUWFrame.getContentPane().add(descriptionLabel);
    	
    	
    	descriptionTextArea = new JTextArea();
    	addComponent(AMUWLayout,layoutConstraints,descriptionTextArea,GridBagConstraints.REMAINDER,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,10,10,10));
    	AMUWFrame.getContentPane().add(descriptionTextArea);
    	
    	
    }


    
    /* (non-Javadoc)
     * @see fr.umlv.smoreau.beontimeSwing.graphics.windows.Window#show(java.lang.Object[])
     */
    public void show() {
    	       
    	    AMUWFrame.pack();
    	    AMUWFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	    AMUWFrame.setVisible(true);
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
    			
    		AddModifyUnavailabilityWindow form = new AddModifyUnavailabilityWindow();
    		form.show();
    			
    }
}