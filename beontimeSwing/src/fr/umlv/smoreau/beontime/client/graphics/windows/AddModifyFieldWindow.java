/*
 * 
 */
package fr.umlv.smoreau.beontime.client.graphics.windows;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
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
 

    
    private static class NbHourJcbListener implements ItemListener {

    	private int type;
    	
    	private JPanel panel;
    	
    	private GridBagConstraints layoutConstraints;
    	private JDialog formFrame;
    	
    	
    	
    	public NbHourJcbListener(JDialog formFrame, GridBagConstraints layoutConstraints, JPanel panel, int type) {
    		
    		this.type = type;
    		
    		this.panel = panel;
    		
    		this.layoutConstraints = layoutConstraints;
    		this.formFrame = formFrame;
    	}
    	

		/* (non-Javadoc)
		 * @see java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent)
		 */
		public void itemStateChanged(ItemEvent e) {
			int nbGroupsChoose = ((JComboBox)e.getSource()).getSelectedIndex()+1;
			int nbGroupsVisible = ((JPanel)(panel.getComponent(3))).getComponentCount()/2;
			//int nbGroupsVisible = nameGroupLabelPanel.getComponentCount()/2;
			
		
			formFrame.getContentPane().remove(panel);
			
			if (nbGroupsChoose > nbGroupsVisible) {
				
				int nbSup = nbGroupsChoose-nbGroupsVisible;
				
				for(int i=nbSup-1;i>=0;i--) {
					
					((JPanel)(panel.getComponent(3))).add(new JLabel("Groupe "+(nbGroupsChoose-i)+" :"));
					((JPanel)(panel.getComponent(3))).add(Box.createVerticalStrut(9));
					
					((JPanel)(panel.getComponent(4))).add(new JComboBox());
					((JPanel)(panel.getComponent(4))).add(Box.createVerticalStrut(5));
					
					((JPanel)(panel.getComponent(5))).add(new JLabel("Enseignant :"));
					((JPanel)(panel.getComponent(5))).add(Box.createVerticalStrut(9));
					
					((JPanel)(panel.getComponent(6))).add(new JComboBox());
					((JPanel)(panel.getComponent(6))).add(Box.createVerticalStrut(5));
				
				}
				
				
				switch (type) {
				case 1 :
					addComponent((GridBagLayout)formFrame.getLayout(),layoutConstraints,panel,1,6,9,1,0.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,10,15,10));
					break;
				
				case 2 :
					addComponent((GridBagLayout)formFrame.getLayout(),layoutConstraints,panel,1,7,9,1,0.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,10,15,10));
					break;
				
				case 3 :
					addComponent((GridBagLayout)formFrame.getLayout(),layoutConstraints,panel,1,8,9,1,0.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,10,15,10));
					break;		
				}
				
				formFrame.getContentPane().add(panel);
				formFrame.pack();
				
			}
			
			if (nbGroupsChoose < nbGroupsVisible) {
			
				if (nbGroupsChoose == 0) {
					//formFrame.getContentPane().remove(panel);
					initTypeCoursePanel(panel, type);
				}
 				
				else {
					int nbInf = nbGroupsVisible - nbGroupsChoose;
					int position = nbGroupsVisible*2;
    			
					for(int i=1;i<=nbInf;i++) {
				
						((JPanel)(panel.getComponent(3))).remove(position-i);
						((JPanel)(panel.getComponent(3))).remove(position-(i+1));
						
						((JPanel)(panel.getComponent(4))).remove(position-i);
						((JPanel)(panel.getComponent(4))).remove(position-(i+1));
						
						((JPanel)(panel.getComponent(5))).remove(position-i);
						((JPanel)(panel.getComponent(5))).remove(position-(i+1));
						
						((JPanel)(panel.getComponent(6))).remove(position-i);
						((JPanel)(panel.getComponent(6))).remove(position-(i+1));
					
						position-=1;
					}
				}
			
				switch (type) {
				case 1 :
					addComponent((GridBagLayout)formFrame.getLayout(),layoutConstraints,panel,1,6,9,1,0.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,10,15,10));
					break;
				
				case 2 :
					addComponent((GridBagLayout)formFrame.getLayout(),layoutConstraints,panel,1,7,9,1,0.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,10,15,10));
					break;
				
				case 3 :
					addComponent((GridBagLayout)formFrame.getLayout(),layoutConstraints,panel,1,8,9,1,0.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,10,15,10));
					break;		
				}
				
				formFrame.getContentPane().add(panel);
				formFrame.pack();
			}
		}
		
		
		/*private void initTypeCoursePanel(JPanel panel, int type) {
		    
		    	GridBagLayout layout = new GridBagLayout();
		        GridBagConstraints layoutConstraints = new GridBagConstraints();
		    	panel.setLayout(layout);
		    	
		    	JLabel typeCourseLabel = new JLabel();
		    	
		    	JLabel nbGroupsLabel;
		    	nbGroupsLabel = new JLabel("Nombre de groupes :");
		    	
		    	JLabel nameGroupLabel = new JLabel("Groupe1 :");
		    	
		    	JLabel teacherGroupsLabel;
		    	teacherGroupsLabel = new JLabel("Enseignant :");
		    	
		    	JComboBox nbGroupsJcB = new JComboBox();
		    	initNumberJcb(nbGroupsJcB, 1, 10);
		    	
		    	JComboBox nameGroupJcb = new JComboBox();
		    	JComboBox teacherGroupsJcB = new JComboBox();
		    	
		    	JPanel nameGroupsPanel = new JPanel();
		    	JPanel chooseNameGroupsPanel = new JPanel();
		    	JPanel teacherGroupsPanel = new JPanel();
		    	JPanel chooseTeacherGroupsPanel = new JPanel();
		    	
		    	switch(type) {
		    	
		    	case 1:  typeCourseLabel.setText("Cours magistraux :"); break;
		    		
		        case 2:  typeCourseLabel.setText("Travaux dirigés :"); break;
		        	
		        case 3:  typeCourseLabel.setText("Travaux pratiques :"); break;
		    	}
		    	
		    	addComponent(layout,layoutConstraints,typeCourseLabel,1,1,2,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(15,10,15,10));
		    	panel.add(typeCourseLabel);
		    	
		    
		    	addComponent(layout,layoutConstraints,nbGroupsLabel,1,2,3,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(15,10,15,10));
		    	panel.add(nbGroupsLabel);
		    	
		    	addComponent(layout,layoutConstraints,nbGroupsJcB,4,2,1,1,0.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(15,10,15,10));
		    	panel.add(nbGroupsJcB);
		    	
		    
		    	nameGroupsPanel.setLayout(new BoxLayout(nameGroupsPanel, BoxLayout.Y_AXIS));
		    	nameGroupsPanel.add(nameGroupLabel);
		    	nameGroupsPanel.add(Box.createVerticalStrut(5));
		    	addComponent(layout,layoutConstraints,nameGroupsPanel,1,3,1,1,0.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(15,10,15,10));
		    	panel.add(nameGroupsPanel);
		    	
		    	chooseNameGroupsPanel.setLayout(new BoxLayout(chooseNameGroupsPanel, BoxLayout.Y_AXIS));
		    	chooseNameGroupsPanel.add(nameGroupJcb);
		    	chooseNameGroupsPanel.add(Box.createVerticalStrut(5));
		    	addComponent(layout,layoutConstraints,chooseNameGroupsPanel,2,3,2,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(15,10,15,10));
		    	panel.add(chooseNameGroupsPanel);
		    	
		    	teacherGroupsPanel.setLayout(new BoxLayout(teacherGroupsPanel, BoxLayout.Y_AXIS));
		    	teacherGroupsPanel.add(teacherGroupsLabel);
		    	teacherGroupsPanel.add(Box.createVerticalStrut(5));
		    	addComponent(layout,layoutConstraints,teacherGroupsPanel,6,3,2,1,0.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(15,10,15,10));
		    	panel.add(teacherGroupsPanel);
		    	
		   
		    	chooseTeacherGroupsPanel.setLayout(new BoxLayout(chooseTeacherGroupsPanel, BoxLayout.Y_AXIS));
		    	chooseTeacherGroupsPanel.add(teacherGroupsJcB);
		    	chooseTeacherGroupsPanel.add(Box.createVerticalStrut(5));
		    	addComponent(layout,layoutConstraints,chooseTeacherGroupsPanel,8,3,2,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(15,10,15,10));
		    	panel.add(chooseTeacherGroupsPanel);
		    }*/

    }
    
    
    public AddModifyFieldWindow() {
    	
    	AMFWFrame = new JDialog(MainFrame.getInstance().getMainFrame(), TITRE, true);
    	AMFWFrame.getContentPane().setLayout(AMFWLayout);
        
    	initAddModifyFieldWindow();  
        
    }
    
    
    private void initAddModifyFieldWindow() {
    	
    	
    	
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
    	
    	
    	
    	initTypeCoursePanel(courseMPanel, 1);
    	//addComponent(AMFWLayout,layoutConstraints,courseMPanel,1,6,9,1,0.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,10,15,10));
    	//AMFWFrame.getContentPane().add(courseMPanel);
    	
    	initTypeCoursePanel(tdPanel, 2);
    	//addComponent(AMFWLayout,layoutConstraints,tdPanel,1,7,9,1,0.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,10,15,10));
    	//AMFWFrame.getContentPane().add(tdPanel);
    	
    	initTypeCoursePanel(tpPanel, 3);
    	//addComponent(AMFWLayout,layoutConstraints,tpPanel,1,8,9,1,0.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,10,15,10));
    	//AMFWFrame.getContentPane().add(tpPanel);
    	
    	
    	
    	
    	
    	magistrauxLabel = new JLabel("Magistraux");
    	addComponent(AMFWLayout,layoutConstraints,magistrauxLabel,2,5,1,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(15,10,15,10));
    	AMFWFrame.getContentPane().add(magistrauxLabel);
    	
    	magistrauxJcb = new JComboBox();
    	initNumberJcb(magistrauxJcb, 0, 300);
    	magistrauxJcb.addItemListener(new NbHourJcbListener(AMFWFrame, layoutConstraints, courseMPanel, 1));
    	addComponent(AMFWLayout,layoutConstraints,magistrauxJcb,3,5,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(5,10,15,10));
    	AMFWFrame.getContentPane().add(magistrauxJcb);
    	
    	tdLabel = new JLabel("TD");
    	addComponent(AMFWLayout,layoutConstraints,tdLabel,5,5,1,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(15,10,15,10));
    	AMFWFrame.getContentPane().add(tdLabel);
    	
    	tdJcb = new JComboBox();
    	initNumberJcb(tdJcb, 0, 300);
    	tdJcb.addItemListener(new NbHourJcbListener(AMFWFrame, layoutConstraints, tdPanel, 2));
    	addComponent(AMFWLayout,layoutConstraints,tdJcb,6,5,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(5,10,15,10));
    	AMFWFrame.getContentPane().add(tdJcb);
    	
    	tpLabel = new JLabel("TP");
    	addComponent(AMFWLayout,layoutConstraints,tpLabel,8,5,1,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(15,10,15,10));
    	AMFWFrame.getContentPane().add(tpLabel);
    	
    	tpJcb = new JComboBox();
    	initNumberJcb(tpJcb, 0, 300);
    	tpJcb.addItemListener(new NbHourJcbListener(AMFWFrame, layoutConstraints, tpPanel, 3));
    	addComponent(AMFWLayout,layoutConstraints,tpJcb,9,5,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(5,10,15,10));
    	AMFWFrame.getContentPane().add(tpJcb);
    	
    	
    	
    	
    	
    	
    	
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
    
    
    private static void initTypeCoursePanel(JPanel panel, int type) {
    
    	GridBagLayout layout = new GridBagLayout();
        GridBagConstraints layoutConstraints = new GridBagConstraints();
    	panel.setLayout(layout);
    	
    	JLabel typeCourseLabel = new JLabel();
    	
    	JLabel nbGroupsLabel;
    	nbGroupsLabel = new JLabel("Nombre de groupes :");
    	
    	JLabel nameGroupLabel = new JLabel("Groupe1 :");
    	
    	JLabel teacherGroupsLabel;
    	teacherGroupsLabel = new JLabel("Enseignant :");
    	
    	JComboBox nbGroupsJcB = new JComboBox();
    	initNumberJcb(nbGroupsJcB, 1, 10);
    	
    	JComboBox nameGroupJcb = new JComboBox();
    	JComboBox teacherGroupsJcB = new JComboBox();
    	
    	JPanel nameGroupsPanel = new JPanel();
    	JPanel chooseNameGroupsPanel = new JPanel();
    	JPanel teacherGroupsPanel = new JPanel();
    	JPanel chooseTeacherGroupsPanel = new JPanel();
    	
    	switch(type) {
    	
    	case 1:  typeCourseLabel.setText("Cours magistraux :"); break;
    		
        case 2:  typeCourseLabel.setText("Travaux dirigés :"); break;
        	
        case 3:  typeCourseLabel.setText("Travaux pratiques :"); break;
    	}
    	
    	addComponent(layout,layoutConstraints,typeCourseLabel,1,1,2,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(15,10,15,10));
    	panel.add(typeCourseLabel);
    	
    
    	addComponent(layout,layoutConstraints,nbGroupsLabel,1,2,3,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(15,10,15,10));
    	panel.add(nbGroupsLabel);
    	
    	addComponent(layout,layoutConstraints,nbGroupsJcB,4,2,1,1,0.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(15,10,15,10));
    	panel.add(nbGroupsJcB);
    	
    
    	nameGroupsPanel.setLayout(new BoxLayout(nameGroupsPanel, BoxLayout.Y_AXIS));
    	nameGroupsPanel.add(nameGroupLabel);
    	nameGroupsPanel.add(Box.createVerticalStrut(5));
    	addComponent(layout,layoutConstraints,nameGroupsPanel,1,3,1,1,0.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(15,10,15,10));
    	panel.add(nameGroupsPanel);
    	
    	chooseNameGroupsPanel.setLayout(new BoxLayout(chooseNameGroupsPanel, BoxLayout.Y_AXIS));
    	chooseNameGroupsPanel.add(nameGroupJcb);
    	chooseNameGroupsPanel.add(Box.createVerticalStrut(5));
    	addComponent(layout,layoutConstraints,chooseNameGroupsPanel,2,3,2,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(15,10,15,10));
    	panel.add(chooseNameGroupsPanel);
    	
    	teacherGroupsPanel.setLayout(new BoxLayout(teacherGroupsPanel, BoxLayout.Y_AXIS));
    	teacherGroupsPanel.add(teacherGroupsLabel);
    	teacherGroupsPanel.add(Box.createVerticalStrut(5));
    	addComponent(layout,layoutConstraints,teacherGroupsPanel,6,3,2,1,0.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(15,10,15,10));
    	panel.add(teacherGroupsPanel);
    	
   
    	chooseTeacherGroupsPanel.setLayout(new BoxLayout(chooseTeacherGroupsPanel, BoxLayout.Y_AXIS));
    	chooseTeacherGroupsPanel.add(teacherGroupsJcB);
    	chooseTeacherGroupsPanel.add(Box.createVerticalStrut(5));
    	addComponent(layout,layoutConstraints,chooseTeacherGroupsPanel,8,3,2,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(15,10,15,10));
    	panel.add(chooseTeacherGroupsPanel);
    }
    
    
    private static void initNumberJcb(JComboBox jcb, int start, int end) {

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

    
    private static void addComponent(GridBagLayout gbLayout,GridBagConstraints constraints,Component comp,int gridx, int gridy, int gridwidth, int gridheight, double weightx, double weighty, int anchor, int fill, Insets insets) {
	     
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
