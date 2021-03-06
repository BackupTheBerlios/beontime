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

import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.client.graphics.utils.TextFieldBoT;
import fr.umlv.smoreau.beontime.dao.DaoManager;
import fr.umlv.smoreau.beontime.model.Formation;
import fr.umlv.smoreau.beontime.model.Group;
import fr.umlv.smoreau.beontime.model.user.User;

/**
 * @author NBeOnTime
 */
public class AddModifySubjectWindow {
	private static final String TITRE_AJOUTER = "Ajouter une mati�re";
	private static final String TITRE_MODIFIER = "Modifier la mati�re";
	
	public static final int TYPE_ADD = 0;
	public static final int TYPE_MODIFY = 1;
	
	//private JComboBox formationFieldJcb;
	private JTextField intituleFieldJtf;
	private JComboBox teacherFieldJcb;
	private JComboBox magistrauxJcb;
	private JComboBox tdJcb;
	private JComboBox tpJcb;
	private JComboBox nbGroupsMag;
	private JComboBox nbGroupsTd;
	private JComboBox nbGroupsTp;
	private JComboBox formationFieldJcb;
	
	private JButton createGroupButton;
	
	private boolean isOk;
	private User teacher;
	private static String[] teachersName;
	private User[] teachers;
	private static String[] groupsName;
	private Group[] groups;
	private Formation formation;
	private Formation[] formations;
	private static String[] formationsName;
	
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
    	MainFrame mainFrame = MainFrame.getInstance();
    	this.formation = mainFrame.getFormationSelected();
    	this.teacher = mainFrame.getModel().getTimetable().getTeacher();
        
    	initAddModifyFieldWindow();  
    }
    
    
    private void initAddModifyFieldWindow() {
        JLabel formationFieldLabel = new JLabel("Formation Correspondante :");
    	addComponent(AMFWLayout,layoutConstraints,formationFieldLabel,1,1,4,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,0,10));
    	AMFWFrame.getContentPane().add(formationFieldLabel);
    	
    	if (formation != null) {
    	    JLabel formationValue = new JLabel(formation.getHeading());
	    	addComponent(AMFWLayout,layoutConstraints,formationValue,5,1,GridBagConstraints.REMAINDER,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(20,10,0,10));
	    	AMFWFrame.getContentPane().add(formationValue);
    	} else {
            Collection tmp = MainFrame.getInstance().getUserConnected().getFormationsInCharge();
            formationsName = new String[tmp.size()+1];
            formations = new Formation[tmp.size()+1];
            formationsName[0] = "";
            int j = 1;
            for (Iterator i = tmp.iterator(); i.hasNext(); ++j) {
                formations[j] = (Formation) i.next();
                formationsName[j] = formations[j].getHeading();
            }
            formationFieldJcb = new JComboBox(formationsName);
	    	addComponent(AMFWLayout,layoutConstraints,formationFieldJcb,5,1,4,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(0,10,0,10));
	    	AMFWFrame.getContentPane().add(formationFieldJcb);
    	}
    	
    	JLabel teacherFieldLabel = new JLabel("Enseignant responsable :");
    	addComponent(AMFWLayout,layoutConstraints,teacherFieldLabel,1,3,4,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(30,10,15,10));
    	AMFWFrame.getContentPane().add(teacherFieldLabel);
    	
    	if (teacher != null) {
    	    JLabel teacherValue = new JLabel(teacher.getName()+" "+teacher.getFirstName());
	    	addComponent(AMFWLayout,layoutConstraints,teacherValue,5,3,GridBagConstraints.REMAINDER,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(20,10,0,10));
	    	AMFWFrame.getContentPane().add(teacherValue);
    	} else {
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
	            teacherFieldJcb = new JComboBox(teachersName);
	        } catch (Exception e) {
	            JLabel label = new JLabel("Erreur lors de la r�cup�ration des enseignants");
	            label.setForeground(Color.RED);
	            addComponent(AMFWLayout,layoutConstraints,label,5,2,4,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(20,10,15,10));
	            AMFWFrame.getContentPane().add(label);
	            teacherFieldLabel.setEnabled(false);
	            teacherFieldJcb = new JComboBox();
	            teacherFieldJcb.setEnabled(false);
	        }
	    	addComponent(AMFWLayout,layoutConstraints,teacherFieldJcb,5,3,4,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(20,10,15,10));
	    	AMFWFrame.getContentPane().add(teacherFieldJcb);
    	}
    	
    	
    	JLabel intitleFieldLabel = new JLabel("Intitul� de la mati�re :");
    	addComponent(AMFWLayout,layoutConstraints,intitleFieldLabel,1,4,3,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(15,10,15,10));
    	AMFWFrame.getContentPane().add(intitleFieldLabel);
    	
    	intituleFieldJtf = new TextFieldBoT(40);   	
    	addComponent(AMFWLayout,layoutConstraints,intituleFieldJtf,4,4,3,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(20,10,15,10));
    	AMFWFrame.getContentPane().add(intituleFieldJtf);
    	
    	
    	
    	/*JLabel hourCourseFieldLabel = new JLabel("Nombre d'heures de cours :");
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
			    	addComponent(AMFWLayout,layoutConstraints,courseMPanel,1,7,8,1,0.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,10,15,10));
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
    	addComponent(AMFWLayout,layoutConstraints,tpLabel,7,6,1,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(15,10,15,10));
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
    	addComponent(AMFWLayout,layoutConstraints,tpJcb,8,6,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(5,10,15,10));
    	AMFWFrame.getContentPane().add(tpJcb);*/

    	
    	/*createGroupButton = new JButton("Cr�er");
    	createGroupButton.setAction(ActionsList.getAction("AddGroup"));
    	addComponent(AMFWLayout,layoutConstraints,createGroupButton,1,10,6,1,0.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,10,15,10));
    	AMFWFrame.getContentPane().add(createGroupButton);*/

    	
    	JButton ok = new JButton("OK");
    	ok.addActionListener(new ActionOk());
		addComponent(AMFWLayout,layoutConstraints,ok,7,11,1,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		AMFWFrame.getContentPane().add(ok);

		JButton annuler = new JButton("Annuler");
		annuler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                AMFWFrame.dispose();
            }
		});

		addComponent(AMFWLayout,layoutConstraints,annuler,8,11,1,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(20,10,10,10));
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
    	
    	/*if (groupsName == null) {
    	    try {
                formation = DaoManager.getFormationDao().getFormation(formation, new String[] {FormationDao.JOIN_GROUPS});
                
                groupsName = new String[formation.getGroups().size()+1];
        	    groups = new Group[formation.getGroups().size()+1];
        	    
        	    groupsName[0] = "";
        	    int j = 1;
        	    for (Iterator i = formation.getGroups().iterator(); i.hasNext(); ++j) {
        	        groups[j] = (Group) i.next();
        	        groupsName[j] = groups[j].getHeading();
        	    }
        	    throw new Exception();
            } catch (Exception e) {
                JLabel label = new JLabel("Erreur lors de la r�cup�ration des groupes");
                label.setForeground(Color.RED);
                addComponent(AMFWLayout,layoutConstraints,label,7,1,1,1,0.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(20,10,15,10));
                panel.add(label);
            }
    	}
    	JComboBox nameGroupJcb = new JComboBox(groupsName);
    	JComboBox teacherGroupsJcB = new JComboBox(teachersName);*/
    	
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
	        	addComponent(layout,layoutConstraints,nbGroupsMag,8,1,1,1,0.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(7,10,7,10));
	        	panel.add(nbGroupsMag);
	    	    break;
	        case 2:
	            typeCourseLabel.setText("Travaux dirig�s :");
	            nbGroupsTd = new JComboBox();
	    	    initNumberJcb(nbGroupsTd, 1, 10);
	    	    //nbGroupsTd.addItemListener(new NbHourJcbListener(AMFWFrame, AMFWLayout, layoutConstraints, panel, type));
	        	addComponent(layout,layoutConstraints,nbGroupsTd,8,1,1,1,0.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(7,10,7,10));
	        	panel.add(nbGroupsTd);
	            break;
	        case 3:
	            typeCourseLabel.setText("Travaux pratiques :");
	            nbGroupsTp = new JComboBox();
	    	    initNumberJcb(nbGroupsTp, 1, 10);
	    	    //nbGroupsTp.addItemListener(new NbHourJcbListener(AMFWFrame, AMFWLayout, layoutConstraints, panel, type));
	        	addComponent(layout,layoutConstraints,nbGroupsTp,8,1,1,1,0.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(7,10,7,10));
	        	panel.add(nbGroupsTp);
	            break;
    	}
    	
    	addComponent(layout,layoutConstraints,typeCourseLabel,1,1,3,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(15,10,7,10));
    	panel.add(typeCourseLabel);
    	
    
    	addComponent(layout,layoutConstraints,nbGroupsLabel,4,1,4,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(15,10,7,10));
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
        return intituleFieldJtf.getText().trim();
    }
    
    public void setIntitule(String intitule) {
        intituleFieldJtf.setText(intitule);
        intituleFieldJtf.setEnabled(false);
    }
    
    public User getTeacher() {
        if (teacher != null)
            return teacher;
        int index = teacherFieldJcb.getSelectedIndex();
        if (index == 0)
            return null;
        else
            return teachers[index];
    }
    
    public Formation getFormation() {
        if (formation != null)
            return formation;
        int index = formationFieldJcb.getSelectedIndex();
        if (index == 0)
            return null;
        else
            return formations[index];
    }
    
    public void setIdTeacher(Long idTeacher) {
        if (teachers != null) {
	        for (int i = 1; i < teachers.length; ++i) {
	            if (teachers[i].getIdUser().equals(idTeacher)) {
	                teacherFieldJcb.setSelectedIndex(i);
	                break;
	            }
	        }
        }
    }
    
    public void setIdFormation(Long idFormation) {
	    try {
	        formation = DaoManager.getFormationDao().getFormation(new Formation(idFormation), null);
	        AMFWFrame.getContentPane().remove(formationFieldJcb);
	        JLabel formationValue = new JLabel(formation.getHeading());
	    	addComponent(AMFWLayout,layoutConstraints,formationValue,5,1,GridBagConstraints.REMAINDER,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(20,10,0,10));
	    	AMFWFrame.getContentPane().add(formationValue);
        } catch (Exception e) {
        }
	}
    
    public Integer getNbMagHours() {
        if (magistrauxJcb != null)
            return new Integer((String) magistrauxJcb.getSelectedItem());
        return null;
    }
    
    public void setNbMagHours(Integer value) {
        if (magistrauxJcb != null)
            magistrauxJcb.setSelectedItem(value.toString());
    }
    
    public Integer getNbTdHours() {
        if (tdJcb != null)
            return new Integer((String) tdJcb.getSelectedItem());
        return null;
    }
    
    public void setNbTdHours(Integer value) {
        if (tdJcb != null)
            tdJcb.setSelectedItem(value.toString());
    }
    
    public Integer getNbTpHours() {
        if (tpJcb != null)
            return new Integer((String) tpJcb.getSelectedItem());
        return null;
    }
    
    public void setNbTpHours(Integer value) {
        if (tpJcb != null)
            tpJcb.setSelectedItem(value.toString());
    }
    
    public Integer getNbMagGroups() {
        if (nbGroupsMag != null)
            return new Integer((String) nbGroupsMag.getSelectedItem());
        return new Integer(0);
    }
    
    public void setNbMagGroups(Integer value) {
        if (value.intValue() > 0 && nbGroupsMag != null)
            nbGroupsMag.setSelectedItem(value.toString());
    }
    
    public Integer getNbTdGroups() {
        if (nbGroupsTd != null)
            return new Integer((String) nbGroupsTd.getSelectedItem());
        return new Integer(0);
    }
    
    public void setNbTdGroups(Integer value) {
        if (value.intValue() > 0 && nbGroupsTd != null)
            nbGroupsTd.setSelectedItem(value.toString());
    }
    
    public Integer getNbTpGroups() {
        if (nbGroupsTp != null)
            return new Integer((String) nbGroupsTp.getSelectedItem());
        return new Integer(0);
    }
    
    public void setNbTpGroups(Integer value) {
        if (value.intValue() > 0 && nbGroupsTp != null)
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
        if (getFormation() == null)
            return 3;
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
                errorMessage = "L'intitul� est obligatoire";
                break;
            case 2:
                errorMessage = "L'enseignant responsable est obligatoire";
                break;
            case 3:
                errorMessage = "La formation doit �tre renseign�e";
                break;
            default:
                errorMessage = "Erreur inconnue";
            }
            JOptionPane.showMessageDialog(null, errorMessage, "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
