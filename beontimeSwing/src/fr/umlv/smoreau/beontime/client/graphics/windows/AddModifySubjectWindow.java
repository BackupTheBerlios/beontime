package fr.umlv.smoreau.beontime.client.graphics.windows;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.umlv.smoreau.beontime.client.DaoManager;
import fr.umlv.smoreau.beontime.client.actions.ActionsList;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.model.Formation;
import fr.umlv.smoreau.beontime.model.Group;
import fr.umlv.smoreau.beontime.model.user.User;

/**
 * @author NBeOnTime
 */
public class AddModifySubjectWindow {
	private static final String TITRE_AJOUTER = "Ajouter une matière";
	private static final String TITRE_MODIFIER = "Ajouter une matière";
	
	public static final int TYPE_ADD = 0;
	public static final int TYPE_MODIFY = 1;
	
	//private JComboBox formationFieldJcb;
	private JTextField intitleFieldJtf;
	private JComboBox teacherFieldJcb;
	private JComboBox magistrauxJcb;
	private JComboBox tdJcb;
	private JComboBox tpJcb;
	private JComboBox nbGroupsMag;
	private JComboBox nbGroupsTd;
	private JComboBox nbGroupsTp;
	
	private JButton createGroupButton;
	
	private boolean isOk;
	private static String[] teachersName;
	private User[] teachers;
	private static String[] groupsName;
	private Group[] groups;
	private Formation formation;
	
	
	private JPanel courseMPanel = new JPanel();
	private JPanel tdPanel = new JPanel();
	private JPanel tpPanel = new JPanel();
	
	private JDialog AMFWFrame;
	private GridBagLayout AMFWLayout = new GridBagLayout();
    private GridBagConstraints layoutConstraints = new GridBagConstraints();
    
    private static class NbHourJcbListener implements ItemListener {

    	private int type;
    	
    	private JPanel panel;
    	
    	private GridBagLayout layout;
    	private GridBagConstraints layoutConstraints;
    	private JDialog formFrame;
    	
    	
    	
    	public NbHourJcbListener(JDialog formFrame, GridBagLayout layout, GridBagConstraints layoutConstraints, JPanel panel, int type) {
    		
    		this.type = type;
    		
    		this.panel = panel;
    		
    		this.layout = layout;
    		this.layoutConstraints = layoutConstraints;
    		this.formFrame = formFrame;
    	}
    	

		/* (non-Javadoc)
		 * @see java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent)
		 */
		public void itemStateChanged(ItemEvent e) {
			
			int nbGroupsChoose = ((JComboBox)e.getSource()).getSelectedIndex()+1;
			int nbGroupsVisible = ((JPanel)(panel.getComponent(4))).getComponentCount()/2;
			
			
			if (nbGroupsChoose > nbGroupsVisible) {
				
					int nbSup = nbGroupsChoose-nbGroupsVisible;
				
					for(int i=nbSup-1;i>=0;i--) {
					
						((JPanel)(panel.getComponent(3))).add(new JLabel("Groupe "+(nbGroupsChoose-i)+" :"));
						((JPanel)(panel.getComponent(3))).add(Box.createVerticalStrut(13));
					
						((JPanel)(panel.getComponent(4))).add(new JComboBox(groupsName));
						((JPanel)(panel.getComponent(4))).add(Box.createVerticalStrut(5));
					
						((JPanel)(panel.getComponent(5))).add(new JLabel("Enseignant :"));
						((JPanel)(panel.getComponent(5))).add(Box.createVerticalStrut(13));
					
						((JPanel)(panel.getComponent(6))).add(new JComboBox(teachersName));
						((JPanel)(panel.getComponent(6))).add(Box.createVerticalStrut(5));
				
					}
				
				formFrame.pack();
			}
			
			if (nbGroupsChoose < nbGroupsVisible) {
			
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
				
				formFrame.pack();
			}
		}
		
    }
    
    
    public AddModifySubjectWindow(int type) {
        if (type == TYPE_ADD)
            AMFWFrame = new JDialog(MainFrame.getInstance().getMainFrame(), TITRE_AJOUTER, true);
        else if (type == TYPE_MODIFY)
            AMFWFrame = new JDialog(MainFrame.getInstance().getMainFrame(), TITRE_MODIFIER, true);
    	AMFWFrame.getContentPane().setLayout(AMFWLayout);
    	
    	this.isOk = false;
    	this.formation = MainFrame.getInstance().getFormationSelected();
        
    	initAddModifyFieldWindow();  
    }
    
    
    private void initAddModifyFieldWindow() {
        JLabel formationFieldLabel = new JLabel("Formation Correspondante :");
    	addComponent(AMFWLayout,layoutConstraints,formationFieldLabel,1,1,4,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,0,10));
    	AMFWFrame.getContentPane().add(formationFieldLabel);
    	
    	/*formationFieldJcb = new JComboBox();
    	addComponent(AMFWLayout,layoutConstraints,formationFieldJcb,5,1,4,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(0,10,0,10));
    	AMFWFrame.getContentPane().add(formationFieldJcb);*/
    	
    	JLabel formationValue = new JLabel(formation.getHeading());
    	addComponent(AMFWLayout,layoutConstraints,formationValue,5,1,4,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(20,10,0,10));
    	AMFWFrame.getContentPane().add(formationValue);
    	
    	JLabel teacherFieldLabel = new JLabel("Enseignant responsable :");
    	addComponent(AMFWLayout,layoutConstraints,teacherFieldLabel,1,3,4,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(30,10,15,10));
    	AMFWFrame.getContentPane().add(teacherFieldLabel);
    	
    	try {
            Collection t = DaoManager.getUserDao().getTeachers();
            teachersName = new String[t.size()+1];
            teachers = new User[t.size()+1];
            teachersName[0] = "";
            int j = 1;
            for (Iterator i = t.iterator(); i.hasNext(); ++j) {
                teachers[j] = (User) i.next();
                teachersName[j] = teachers[j].getName() + " " + teachers[j].getFirstName();
            }
            Arrays.sort(teachersName);
            teacherFieldJcb = new JComboBox(teachersName);
        } catch (Exception e) {
            JLabel label = new JLabel("Erreur lors de la récupération des enseignants");
            label.setForeground(Color.RED);
            addComponent(AMFWLayout,layoutConstraints,label,5,2,4,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(20,10,15,10));
            AMFWFrame.getContentPane().add(label);
            teacherFieldLabel.setEnabled(false);
            teacherFieldJcb = new JComboBox();
            teacherFieldJcb.setEnabled(false);
        }
 	
    	addComponent(AMFWLayout,layoutConstraints,teacherFieldJcb,5,3,4,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(20,10,15,10));
    	AMFWFrame.getContentPane().add(teacherFieldJcb);
    	
    	
    	
    	JLabel intitleFieldLabel = new JLabel("Intitulé de la matière :");
    	addComponent(AMFWLayout,layoutConstraints,intitleFieldLabel,1,4,3,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(15,10,15,10));
    	AMFWFrame.getContentPane().add(intitleFieldLabel);
    	
    	intitleFieldJtf = new JTextField();   	
    	addComponent(AMFWLayout,layoutConstraints,intitleFieldJtf,4,4,3,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(20,10,15,10));
    	AMFWFrame.getContentPane().add(intitleFieldJtf);
    	
    	
    	
    	JLabel hourCourseFieldLabel = new JLabel("Nombre d'heures de cours :");
    	addComponent(AMFWLayout,layoutConstraints,hourCourseFieldLabel,1,5,4,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(15,10,15,10));
    	AMFWFrame.getContentPane().add(hourCourseFieldLabel);

    	
    	
    	JLabel magistrauxLabel = new JLabel("Magistraux");
    	addComponent(AMFWLayout,layoutConstraints,magistrauxLabel,2,6,1,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(15,10,15,10));
    	AMFWFrame.getContentPane().add(magistrauxLabel);
    	
    	magistrauxJcb = new JComboBox();
    	initNumberJcb(magistrauxJcb, 0, 300);
    	magistrauxJcb.addItemListener(new ItemListener(){

			public void itemStateChanged(ItemEvent e) {
				
				if ( (((JComboBox)e.getSource()).getSelectedIndex() == 0) && (courseMPanel.getComponentCount() != 0)) {
					
					courseMPanel.removeAll();
					AMFWFrame.getContentPane().remove(courseMPanel);
					AMFWFrame.pack();
				}
				
				if((((JComboBox)e.getSource()).getSelectedIndex() != 0) && (courseMPanel.getComponentCount() == 0)) {
					initTypeCoursePanel(courseMPanel, 1);
			    	addComponent(AMFWLayout,layoutConstraints,courseMPanel,1,7,9,1,0.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,10,15,10));
			    	AMFWFrame.getContentPane().add(courseMPanel);
			    	AMFWFrame.pack();
				}
			}
    		
    	});
    	addComponent(AMFWLayout,layoutConstraints,magistrauxJcb,3,6,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(5,10,15,10));
    	AMFWFrame.getContentPane().add(magistrauxJcb);
    	
    	JLabel tdLabel = new JLabel("TD");
    	addComponent(AMFWLayout,layoutConstraints,tdLabel,5,6,1,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(15,10,15,10));
    	AMFWFrame.getContentPane().add(tdLabel);
    	
    	tdJcb = new JComboBox();
    	initNumberJcb(tdJcb, 0, 300);
    	tdJcb.addItemListener(new ItemListener(){

			public void itemStateChanged(ItemEvent e) {
				
				if ( (((JComboBox)e.getSource()).getSelectedIndex() == 0) && (tdPanel.getComponentCount() != 0)) {
					
					tdPanel.removeAll();
					AMFWFrame.getContentPane().remove(tdPanel);
					AMFWFrame.pack();
				}
				
				if((((JComboBox)e.getSource()).getSelectedIndex() != 0) && (tdPanel.getComponentCount() == 0)) {
					initTypeCoursePanel(tdPanel, 2);
			    	addComponent(AMFWLayout,layoutConstraints,tdPanel,1,8,9,1,0.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,10,15,10));
			    	AMFWFrame.getContentPane().add(tdPanel);
			    	AMFWFrame.pack();
				}
			}
    		
    	});
    	addComponent(AMFWLayout,layoutConstraints,tdJcb,6,6,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(5,10,15,10));
    	AMFWFrame.getContentPane().add(tdJcb);
    	
    	JLabel tpLabel = new JLabel("TP");
    	addComponent(AMFWLayout,layoutConstraints,tpLabel,8,6,1,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(15,10,15,10));
    	AMFWFrame.getContentPane().add(tpLabel);
    	
    	tpJcb = new JComboBox();
    	initNumberJcb(tpJcb, 0, 300);
    	tpJcb.addItemListener(new ItemListener(){

			public void itemStateChanged(ItemEvent e) {
				
				if ( (((JComboBox)e.getSource()).getSelectedIndex() == 0) && (tpPanel.getComponentCount() != 0)) {
					
					tpPanel.removeAll();
					AMFWFrame.getContentPane().remove(tpPanel);
					AMFWFrame.pack();
				}
				
				if((((JComboBox)e.getSource()).getSelectedIndex() != 0) && (tpPanel.getComponentCount() == 0)) {
					initTypeCoursePanel(tpPanel, 3);
			    	addComponent(AMFWLayout,layoutConstraints,tpPanel,1,9,9,1,0.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,10,15,10));
			    	AMFWFrame.getContentPane().add(tpPanel);
			    	AMFWFrame.pack();
				}
			}
    		
    	});
    	addComponent(AMFWLayout,layoutConstraints,tpJcb,9,6,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(5,10,15,10));
    	AMFWFrame.getContentPane().add(tpJcb);

    	
    	createGroupButton = new JButton("Créer");
    	createGroupButton.setAction(ActionsList.getAction("AddGroup"));
    	addComponent(AMFWLayout,layoutConstraints,createGroupButton,1,10,6,1,0.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,10,15,10));
    	AMFWFrame.getContentPane().add(createGroupButton);

    	
    	JButton ok = new JButton("OK");
    	ok.addActionListener(new ActionOk());
		addComponent(AMFWLayout,layoutConstraints,ok,8,11,GridBagConstraints.RELATIVE,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		AMFWFrame.getContentPane().add(ok);

		JButton annuler = new JButton("Annuler");
		annuler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                AMFWFrame.dispose();
            }
		});

		addComponent(AMFWLayout,layoutConstraints,annuler,9,11,GridBagConstraints.REMAINDER,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		AMFWFrame.getContentPane().add(annuler);
    }
    
    
    private void initTypeCoursePanel(JPanel panel, int type) {
    	GridBagLayout layout = new GridBagLayout();
        GridBagConstraints layoutConstraints = new GridBagConstraints();
    	panel.setLayout(layout);
    	
    	JLabel typeCourseLabel = new JLabel();
    	
    	JLabel nbGroupsLabel;
    	nbGroupsLabel = new JLabel("Nombre de groupes :");
    	
    	JLabel nameGroupLabel = new JLabel("Groupe1 :");
    	
    	JLabel teacherGroupsLabel;
    	teacherGroupsLabel = new JLabel("Enseignant :");
    	
    	if (groupsName == null) {
    	    groupsName = new String[formation.getGroups().size()+1];
    	    groups = new Group[formation.getGroups().size()+1];
    	    
    	    groupsName[0] = "";
    	    int j = 1;
    	    for (Iterator i = formation.getGroups().iterator(); i.hasNext(); ++j) {
    	        groups[j] = (Group) i.next();
    	        groupsName[j] = groups[j].getHeading();
    	    }
    	}
    	JComboBox nameGroupJcb = new JComboBox(groupsName);
    	JComboBox teacherGroupsJcB = new JComboBox(teachersName);
    	
    	/*
    	JPanel nameGroupsPanel = new JPanel();
    	JPanel chooseNameGroupsPanel = new JPanel();
    	JPanel teacherGroupsPanel = new JPanel();
    	JPanel chooseTeacherGroupsPanel = new JPanel();
    	*/
    	
    	switch(type) {
	    	case 1:
	    	    typeCourseLabel.setText("Cours magistraux :");
	    	    nbGroupsMag = new JComboBox();
	    	    initNumberJcb(nbGroupsMag, 1, 10);
	    	    //nbGroupsMag.addItemListener(new NbHourJcbListener(AMFWFrame, AMFWLayout, layoutConstraints, panel, type));
	        	addComponent(layout,layoutConstraints,nbGroupsMag,9,1,1,1,0.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(7,10,7,10));
	        	panel.add(nbGroupsMag);
	    	    break;
	        case 2:
	            typeCourseLabel.setText("Travaux dirigés :");
	            nbGroupsTd = new JComboBox();
	    	    initNumberJcb(nbGroupsTd, 1, 10);
	    	    //nbGroupsTd.addItemListener(new NbHourJcbListener(AMFWFrame, AMFWLayout, layoutConstraints, panel, type));
	        	addComponent(layout,layoutConstraints,nbGroupsTd,9,1,1,1,0.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(7,10,7,10));
	        	panel.add(nbGroupsTd);
	            break;
	        case 3:
	            typeCourseLabel.setText("Travaux pratiques :");
	            nbGroupsTp = new JComboBox();
	    	    initNumberJcb(nbGroupsTp, 1, 10);
	    	    //nbGroupsTp.addItemListener(new NbHourJcbListener(AMFWFrame, AMFWLayout, layoutConstraints, panel, type));
	        	addComponent(layout,layoutConstraints,nbGroupsTp,9,1,1,1,0.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(7,10,7,10));
	        	panel.add(nbGroupsTp);
	            break;
    	}
    	
    	addComponent(layout,layoutConstraints,typeCourseLabel,1,1,2,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(15,10,7,10));
    	panel.add(typeCourseLabel);
    	
    
    	addComponent(layout,layoutConstraints,nbGroupsLabel,6,1,3,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(15,10,7,10));
    	panel.add(nbGroupsLabel);
    	
    	
    	/*nbGroupsJcb.addItemListener(new NbHourJcbListener(AMFWFrame, AMFWLayout, layoutConstraints, panel, type));
    	addComponent(layout,layoutConstraints,nbGroupsJcb,4,2,1,1,0.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(7,10,7,10));
    	panel.add(nbGroupsJcb);*/
    	
    
    	/*nameGroupsPanel.setLayout(new BoxLayout(nameGroupsPanel, BoxLayout.Y_AXIS));
    	nameGroupsPanel.add(nameGroupLabel);
    	nameGroupsPanel.add(Box.createVerticalStrut(13));
    	addComponent(layout,layoutConstraints,nameGroupsPanel,1,3,1,1,0.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(7,10,15,10));
    	panel.add(nameGroupsPanel);
    	
    	chooseNameGroupsPanel.setLayout(new BoxLayout(chooseNameGroupsPanel, BoxLayout.Y_AXIS));
    	chooseNameGroupsPanel.add(nameGroupJcb);
    	chooseNameGroupsPanel.add(Box.createVerticalStrut(5));
    	addComponent(layout,layoutConstraints,chooseNameGroupsPanel,2,3,2,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(7,10,15,10));
    	panel.add(chooseNameGroupsPanel);
    	
    	teacherGroupsPanel.setLayout(new BoxLayout(teacherGroupsPanel, BoxLayout.Y_AXIS));
    	teacherGroupsPanel.add(teacherGroupsLabel);
    	teacherGroupsPanel.add(Box.createVerticalStrut(13));
    	addComponent(layout,layoutConstraints,teacherGroupsPanel,6,3,2,1,0.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(7,10,15,10));
    	panel.add(teacherGroupsPanel);
    	
   
    	chooseTeacherGroupsPanel.setLayout(new BoxLayout(chooseTeacherGroupsPanel, BoxLayout.Y_AXIS));
    	chooseTeacherGroupsPanel.add(teacherGroupsJcB);
    	chooseTeacherGroupsPanel.add(Box.createVerticalStrut(5));
    	addComponent(layout,layoutConstraints,chooseTeacherGroupsPanel,8,3,2,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(7,10,15,10));
    	panel.add(chooseTeacherGroupsPanel);*/
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
    	AMFWFrame.setLocationRelativeTo(null);
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
    
    
    public String getIntitule() {
        return intitleFieldJtf.getText();
    }
    
    public void setIntitule(String intitule) {
        intitleFieldJtf.setText(intitule);
    }
    
    public User getTeacher() {
        int index = teacherFieldJcb.getSelectedIndex();
        if (index == 0)
            return null;
        else
            return teachers[index];
    }
    
    public void setIdTeacher(Long idTeacher) {
        for (int i = 1; i < teachers.length; ++i) {
            if (teachers[i].getIdUser().equals(idTeacher)) {
                teacherFieldJcb.setSelectedIndex(i);
                break;
            }
        }
    }
    
    public Integer getNbMagHours() {
        return new Integer((String) magistrauxJcb.getSelectedItem());
    }
    
    public void setNbMagHours(Integer value) {
        magistrauxJcb.setSelectedItem(value.toString());
    }
    
    public Integer getNbTdHours() {
        return new Integer((String) tdJcb.getSelectedItem());
    }
    
    public void setNbTdHours(Integer value) {
        tdJcb.setSelectedItem(value.toString());
    }
    
    public Integer getNbTpHours() {
        return new Integer((String) tpJcb.getSelectedItem());
    }
    
    public void setNbTpHours(Integer value) {
        tpJcb.setSelectedItem(value.toString());
    }
    
    public Integer getNbMagGroups() {
        if (nbGroupsMag != null)
            return new Integer((String) nbGroupsMag.getSelectedItem());
        return new Integer(0);
    }
    
    public void setNbMagGroups(Integer value) {
        if (value.intValue() > 0)
            nbGroupsMag.setSelectedItem(value.toString());
    }
    
    public Integer getNbTdGroups() {
        if (nbGroupsTd != null)
            return new Integer((String) nbGroupsTd.getSelectedItem());
        return new Integer(0);
    }
    
    public void setNbTdGroups(Integer value) {
        if (value.intValue() > 0)
            nbGroupsTd.setSelectedItem(value.toString());
    }
    
    public Integer getNbTpGroups() {
        if (nbGroupsTp != null)
            return new Integer((String) nbGroupsTp.getSelectedItem());
        return new Integer(0);
    }
    
    public void setNbTpGroups(Integer value) {
        if (value.intValue() > 0)
            nbGroupsTp.setSelectedItem(value.toString());
    }
    
    public boolean isOk() {
        return isOk;
    }
    
    private int checking() {
        String intitule = getIntitule();
        if (intitule == null || "".equals(intitule))
            return 1;
        if (getTeacher() == null)
            return 2;
        return 0;
    }


    private class ActionOk implements ActionListener {
        /* (non-Javadoc)
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent arg0) {
            String errorMessage = null;
            switch (checking()) {
            case 0:
                isOk = true;
                AMFWFrame.dispose();
                return;
            case 1:
                errorMessage = "L'intitulé est obligatoire";
                break;
            case 2:
                errorMessage = "L'enseignant responsable est obligatoire";
                break;
            default:
                errorMessage = "Erreur inconnue";
            }
            JOptionPane.showMessageDialog(null, errorMessage, "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
