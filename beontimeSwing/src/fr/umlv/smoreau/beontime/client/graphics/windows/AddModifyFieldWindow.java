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
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.umlv.smoreau.beontime.client.graphics.MainFrame;

/**
 * @author BeOnTime
 */
public class AddModifyFieldWindow {

	private static final String TITRE = "Ajouter une matière";
	
	private JLabel formationFieldLabel;
	private JLabel teacherFieldLabel;
	private JLabel intitleFieldLabel;
	private JLabel hourCourseFieldLabel;
	private JLabel magistrauxLabel;
	private JLabel tdLabel;
	private JLabel tpLabel;
	private JLabel createGroupStudentLabel;
	
	private JComboBox formationFieldJcb;
	private JComboBox intitleFieldJcb;
	private JComboBox teacherFieldJcb;
	private JComboBox magistrauxJcb;
	private JComboBox tdJcb;
	private JComboBox tpJcb;
	
	private JButton createGroupButton;
	private JButton ok;
	private JButton annuler;
	
	
	private JPanel courseMPanel = new JPanel();
	private JPanel tdPanel = new JPanel();
	private JPanel tpPanel = new JPanel();
	
	private JDialog AMFWFrame;
	private GridBagLayout AMFWLayout = new GridBagLayout();
    private GridBagConstraints layoutConstraints = new GridBagConstraints();
 

    public AddModifyFieldWindow() {
    	
    	AMFWFrame = new JDialog(MainFrame.getInstance().getMainFrame(), TITRE, true);
    	AMFWFrame.getContentPane().setLayout(AMFWLayout);
        
    	initAddModifyFieldWindow();  
        
    }
    
    
    public void initAddModifyFieldWindow() {
    	
    	
    	
    	formationFieldLabel = new JLabel("Formation Correspondante :");
    	addComponent(AMFWLayout,layoutConstraints,formationFieldLabel,1,1,4,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,0,10));
    	AMFWFrame.getContentPane().add(formationFieldLabel);
    	
    	formationFieldJcb = new JComboBox();
    	addComponent(AMFWLayout,layoutConstraints,formationFieldJcb,5,1,4,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(0,10,0,10));
    	AMFWFrame.getContentPane().add(formationFieldJcb);
    	
    	teacherFieldLabel = new JLabel("Enseignant responsable :");
    	addComponent(AMFWLayout,layoutConstraints,teacherFieldLabel,1,2,4,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(30,10,15,10));
    	AMFWFrame.getContentPane().add(teacherFieldLabel);

    	teacherFieldJcb = new JComboBox();   	
    	addComponent(AMFWLayout,layoutConstraints,teacherFieldJcb,5,2,4,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(20,10,15,10));
    	AMFWFrame.getContentPane().add(teacherFieldJcb);
    	
    	
    	
    	intitleFieldLabel = new JLabel("Intitulé de la matière :");
    	addComponent(AMFWLayout,layoutConstraints,intitleFieldLabel,1,3,3,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(15,10,15,10));
    	AMFWFrame.getContentPane().add(intitleFieldLabel);
    	
    	intitleFieldJcb = new JComboBox();   	
    	addComponent(AMFWLayout,layoutConstraints,intitleFieldJcb,4,3,3,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(20,10,15,10));
    	AMFWFrame.getContentPane().add(intitleFieldJcb);
    	
    	
    	
    	hourCourseFieldLabel = new JLabel("Nombre d'heures de cours :");
    	addComponent(AMFWLayout,layoutConstraints,hourCourseFieldLabel,1,4,4,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(15,10,15,10));
    	AMFWFrame.getContentPane().add(hourCourseFieldLabel);
    	
    	magistrauxLabel = new JLabel("Magistraux");
    	addComponent(AMFWLayout,layoutConstraints,magistrauxLabel,2,5,1,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(15,10,15,10));
    	AMFWFrame.getContentPane().add(magistrauxLabel);
    	
    	magistrauxJcb = new JComboBox();
    	initNumberJcb(magistrauxJcb, 0, 300);
    	addComponent(AMFWLayout,layoutConstraints,magistrauxJcb,3,5,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(5,10,15,10));
    	AMFWFrame.getContentPane().add(magistrauxJcb);
    	
    	tdLabel = new JLabel("TD");
    	addComponent(AMFWLayout,layoutConstraints,tdLabel,5,5,1,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(15,10,15,10));
    	AMFWFrame.getContentPane().add(tdLabel);
    	
    	tdJcb = new JComboBox();
    	initNumberJcb(tdJcb, 0, 300);
    	addComponent(AMFWLayout,layoutConstraints,tdJcb,6,5,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(5,10,15,10));
    	AMFWFrame.getContentPane().add(tdJcb);
    	
    	tpLabel = new JLabel("TP");
    	addComponent(AMFWLayout,layoutConstraints,tpLabel,8,5,1,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(15,10,15,10));
    	AMFWFrame.getContentPane().add(tpLabel);
    	
    	tpJcb = new JComboBox();
    	initNumberJcb(tpJcb, 0, 300);
    	addComponent(AMFWLayout,layoutConstraints,tpJcb,9,5,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(5,10,15,10));
    	AMFWFrame.getContentPane().add(tpJcb);
    	
    	
    	
    	
    	/*addComponent(AMFWLayout,layoutConstraints,courseMPanel,1,6,9,1,0.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,10,15,10));
    	AMFWFrame.getContentPane().add(courseMPanel);
    	
    	addComponent(AMFWLayout,layoutConstraints,tdPanel,1,7,9,1,0.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,10,15,10));
    	AMFWFrame.getContentPane().add(tdPanel);
    	
    	addComponent(AMFWLayout,layoutConstraints,tpPanel,1,8,9,1,0.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,10,15,10));
    	AMFWFrame.getContentPane().add(tpPanel);*/
    	
    	
    	
    	
    	createGroupStudentLabel = new JLabel("Créer un groupe d'étudiants :");
    	addComponent(AMFWLayout,layoutConstraints,createGroupStudentLabel,1,9,4,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(15,10,15,10));
    	AMFWFrame.getContentPane().add(createGroupStudentLabel);
    	
    	createGroupButton = new JButton("Créer");
    	addComponent(AMFWLayout,layoutConstraints,createGroupButton,5,9,2,1,0.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,10,15,10));
    	AMFWFrame.getContentPane().add(createGroupButton);
    	

    	
    	
		ok = new JButton("OK");
		addComponent(AMFWLayout,layoutConstraints,ok,8,10,GridBagConstraints.RELATIVE,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		AMFWFrame.getContentPane().add(ok);
		
		annuler = new JButton("Annuler");
		addComponent(AMFWLayout,layoutConstraints,annuler,9,10,GridBagConstraints.REMAINDER,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		AMFWFrame.getContentPane().add(annuler);
    	
    }
    
    
    private void initNumberJcb(JComboBox jcb, int start, int end) {

    	for(int i=start;i<=end;i++) {
    		jcb.addItem(""+i);
    	}
    }
    
    
    /* (non-Javadoc)
     * @see fr.umlv.smoreau.beontimeSwing.graphics.windows.Window#show(java.lang.Object[])
     */
    public void show() {
    	AMFWFrame.pack();
    	AMFWFrame.setResizable(false);
    	AMFWFrame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    	AMFWFrame.setVisible(true);
    }

    
    public void addComponent(GridBagLayout gbLayout,GridBagConstraints constraints,Component comp,int gridx, int gridy, int gridwidth, int gridheight, double weightx, double weighty, int anchor, int fill, Insets insets) {
	     
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
     	
     	AddModifyFieldWindow form = new AddModifyFieldWindow();
     	form.show();
     			
     }
}
