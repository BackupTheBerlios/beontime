/*
 * 
 */
package fr.umlv.smoreau.beontime.client.graphics.windows;


import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.toedter.calendar.JDateChooser;

import fr.umlv.smoreau.beontime.client.graphics.MainFrame;




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
	
	private JLabel startUnavailabilityTheLabel;
	private JLabel startUnavailabilityHourLabel;
	private JLabel startUnavailabilityMinuteLabel;
	private JLabel endUnavailabilityTheLabel;
	private JLabel endUnavailabilityHourLabel;
	private JLabel endUnavailabilityMinuteLabel;
	
	
	private JLabel weeksLabel;
	
	private JDateChooser startUnavailabilityDate;
	private JDateChooser endUnavailabilityDate;
	
	private JComboBox startUnavailabilityHourJcb;
	private JComboBox startUnavailabilityMinuteJcb;
	private JComboBox endUnavailabilityHourJcb;
	private JComboBox endUnavailabilityMinuteJcb;
	private JComboBox repeatUnavailabilityJcb;
	private JComboBox typeUnavailabilityJcb;
	private JComboBox SubjectUnavailabilityJcb;
	
	private JTextArea descriptionTextArea;
	
	private JButton ok;
	private JButton annuler;
	
	private JDialog AMUWFrame;
	private GridBagLayout AMUWLayout = new GridBagLayout();
    private GridBagConstraints layoutConstraints = new GridBagConstraints();
	

  
    
    public AddModifyUnavailabilityWindow() {
    	
    	AMUWFrame = new JDialog(MainFrame.getInstance().getMainFrame(), TITRE, true);
    	AMUWFrame.getContentPane().setLayout(AMUWLayout);
        
    	initAddModifyUnavailabilityWindow();  
        
    }


    private void initAddModifyUnavailabilityWindow() {
    	
    	
    	startUnavailabilityLabel = new JLabel("Début d'indisponibilité :");
    	addComponent(AMUWLayout,layoutConstraints,startUnavailabilityLabel,GridBagConstraints.REMAINDER,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,0,10));
    	AMUWFrame.getContentPane().add(startUnavailabilityLabel);
    	
    	startUnavailabilityHourJcb = new JComboBox();
    	initNumberJcb(startUnavailabilityHourJcb, 0, 23);
    	addComponent(AMUWLayout,layoutConstraints,startUnavailabilityHourJcb,GridBagConstraints.RELATIVE,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(0,10,0,10));
    	AMUWFrame.getContentPane().add(startUnavailabilityHourJcb);
    	
    	startUnavailabilityHourLabel = new JLabel("heure");
    	addComponent(AMUWLayout,layoutConstraints,startUnavailabilityHourLabel,GridBagConstraints.REMAINDER,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(10,10,0,10));
    	AMUWFrame.getContentPane().add(startUnavailabilityHourLabel);
    	
    	startUnavailabilityTheLabel = new JLabel("le");
    	addComponent(AMUWLayout,layoutConstraints,startUnavailabilityTheLabel,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(0,10,0,10));
    	AMUWFrame.getContentPane().add(startUnavailabilityTheLabel);
    	
    	startUnavailabilityDate = new JDateChooser();
    	addComponent(AMUWLayout,layoutConstraints,startUnavailabilityDate,GridBagConstraints.REMAINDER,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(0,10,0,10));
    	AMUWFrame.getContentPane().add(startUnavailabilityDate);
    	
    	startUnavailabilityMinuteJcb = new JComboBox();
    	initMinuteJcb(startUnavailabilityMinuteJcb);
    	addComponent(AMUWLayout,layoutConstraints,startUnavailabilityMinuteJcb,GridBagConstraints.RELATIVE,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(0,10,10,10));
    	AMUWFrame.getContentPane().add(startUnavailabilityMinuteJcb);
    	
    	startUnavailabilityMinuteLabel = new JLabel("min");
    	addComponent(AMUWLayout,layoutConstraints,startUnavailabilityMinuteLabel,GridBagConstraints.REMAINDER,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(10,10,10,10));
    	AMUWFrame.getContentPane().add(startUnavailabilityMinuteLabel);
    	
    	
    	
    	
    	endUnavailabilityLabel = new JLabel("Fin d'indisponibilité :");
    	addComponent(AMUWLayout,layoutConstraints,endUnavailabilityLabel,GridBagConstraints.REMAINDER,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(0,10,0,10));
    	AMUWFrame.getContentPane().add(endUnavailabilityLabel);
    	
    	endUnavailabilityHourJcb = new JComboBox();
    	initNumberJcb(endUnavailabilityHourJcb, 0, 23);
    	endUnavailabilityHourJcb.setSelectedIndex(23); 
    	addComponent(AMUWLayout,layoutConstraints,endUnavailabilityHourJcb,GridBagConstraints.RELATIVE,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(0,10,0,10));
    	AMUWFrame.getContentPane().add(endUnavailabilityHourJcb);
    	
    	endUnavailabilityHourLabel = new JLabel("heure");
    	addComponent(AMUWLayout,layoutConstraints,endUnavailabilityHourLabel,GridBagConstraints.REMAINDER,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(10,10,0,10));
    	AMUWFrame.getContentPane().add(endUnavailabilityHourLabel);
    	
    	endUnavailabilityTheLabel = new JLabel("le");
    	addComponent(AMUWLayout,layoutConstraints,endUnavailabilityTheLabel,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(0,10,0,10));
    	AMUWFrame.getContentPane().add(endUnavailabilityTheLabel);
    	
    	endUnavailabilityDate = new JDateChooser();
    	addComponent(AMUWLayout,layoutConstraints,endUnavailabilityDate,GridBagConstraints.REMAINDER,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(0,10,0,10));
    	AMUWFrame.getContentPane().add(endUnavailabilityDate);
    	
    	endUnavailabilityMinuteJcb = new JComboBox();
    	initMinuteJcb(endUnavailabilityMinuteJcb);
    	endUnavailabilityMinuteJcb.setSelectedIndex(45); 
    	addComponent(AMUWLayout,layoutConstraints,endUnavailabilityMinuteJcb,GridBagConstraints.RELATIVE,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(0,10,20,10));
    	AMUWFrame.getContentPane().add(endUnavailabilityMinuteJcb);
    	
    	endUnavailabilityMinuteLabel = new JLabel("min");
    	addComponent(AMUWLayout,layoutConstraints,endUnavailabilityMinuteLabel,GridBagConstraints.REMAINDER,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(10,10,20,10));
    	AMUWFrame.getContentPane().add(endUnavailabilityMinuteLabel);
    	
    	
    	
    	
    	repeatUnavailabilityLabel = new JLabel("Répéter cette indisponibilité sur");
    	addComponent(AMUWLayout,layoutConstraints,repeatUnavailabilityLabel,3,1,1.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,20,10));
    	AMUWFrame.getContentPane().add(repeatUnavailabilityLabel);
    	
    	repeatUnavailabilityJcb = new JComboBox();
    	initNumberJcb(repeatUnavailabilityJcb,1,48);
    	addComponent(AMUWLayout,layoutConstraints,repeatUnavailabilityJcb,1,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(10,10,20,10));
    	AMUWFrame.getContentPane().add(repeatUnavailabilityJcb);
    	
    	weeksLabel = new JLabel("semaines");
    	addComponent(AMUWLayout,layoutConstraints,weeksLabel,GridBagConstraints.REMAINDER,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,20,10));
    	AMUWFrame.getContentPane().add(weeksLabel);
    	
    	
    	
    	typeUnavailabilityLabel = new JLabel("Type d'indisponibilité :");
    	addComponent(AMUWLayout,layoutConstraints,typeUnavailabilityLabel,2,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,10,10));
    	AMUWFrame.getContentPane().add(typeUnavailabilityLabel);
    	
    	typeUnavailabilityJcb = new JComboBox();
    	typeUnavailabilityJcb.addItem("Calendrier");
    	typeUnavailabilityJcb.addItem("Cours");
    	typeUnavailabilityJcb.addItem("Enseignant");
    	typeUnavailabilityJcb.addItem("Etudiant");
    	typeUnavailabilityJcb.addItem("Local");
    	typeUnavailabilityJcb.addItem("Matériel");
    	
    	
    	typeUnavailabilityJcb.addItemListener(new ItemListener() {
    		public void itemStateChanged(ItemEvent e) {
    			subjectUnavailabilityLabel.setText(((JComboBox)e.getSource()).getSelectedItem()+" indisponible :");
    		}

    	});
    	addComponent(AMUWLayout,layoutConstraints,typeUnavailabilityJcb,GridBagConstraints.REMAINDER,1,1.0,0.0,GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,new Insets(20,10,10,10));
    	AMUWFrame.getContentPane().add(typeUnavailabilityJcb);
    	
    	
    	
    	subjectUnavailabilityLabel = new JLabel("Calendrier indisponible :");
    	addComponent(AMUWLayout,layoutConstraints,subjectUnavailabilityLabel,2,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(10,10,10,10));
    	AMUWFrame.getContentPane().add(subjectUnavailabilityLabel);
    	
    	SubjectUnavailabilityJcb = new JComboBox();
    	addComponent(AMUWLayout,layoutConstraints,SubjectUnavailabilityJcb,GridBagConstraints.REMAINDER,1,1.0,0.0,GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,new Insets(10,10,10,10));
    	AMUWFrame.getContentPane().add(SubjectUnavailabilityJcb);
    	
    	

    	descriptionLabel = new JLabel("Description :");
    	addComponent(AMUWLayout,layoutConstraints,descriptionLabel,GridBagConstraints.REMAINDER,1,1.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(10,10,0,10));
    	AMUWFrame.getContentPane().add(descriptionLabel);
    	
    	
    	descriptionTextArea = new JTextArea();
    	descriptionTextArea.setLineWrap(true);
    	descriptionTextArea.setRows(4);
    	JScrollPane pane= new JScrollPane(descriptionTextArea);
    	addComponent(AMUWLayout,layoutConstraints,pane,GridBagConstraints.REMAINDER,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,10,20,10));
    	AMUWFrame.getContentPane().add(pane);
    	
    	
    	ok = new JButton("OK");
		addComponent(AMUWLayout,layoutConstraints,ok,GridBagConstraints.RELATIVE,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		AMUWFrame.getContentPane().add(ok);
		
		annuler = new JButton("Annuler");
		annuler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	AMUWFrame.dispose();
            }
		});
		addComponent(AMUWLayout,layoutConstraints,annuler,GridBagConstraints.REMAINDER,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		AMUWFrame.getContentPane().add(annuler);
    	
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
    
    /* (non-Javadoc)
     * @see fr.umlv.smoreau.beontimeSwing.graphics.windows.Window#show(java.lang.Object[])
     */
    public void show() {
    	       
    	    AMUWFrame.pack();
    	    AMUWFrame.setResizable(false);
    	    AMUWFrame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    	    AMUWFrame.setVisible(true);
    }
    	    
    	    
    	    
   private void addComponent(GridBagLayout gbLayout,GridBagConstraints constraints,Component comp,int gridwidth, int gridheight, double weightx, double weighty, int anchor, int fill, Insets insets) {
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
    		
     	AddModifyUnavailabilityWindow form = new AddModifyUnavailabilityWindow();
    	form.show();
    			
    }
}