package fr.umlv.smoreau.beontime.client.graphics.windows;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.toedter.calendar.JDateChooser;

import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.client.graphics.parts.TimeTableViewPanelBar;
import fr.umlv.smoreau.beontime.dao.DaoManager;
import fr.umlv.smoreau.beontime.dao.UserDao;
import fr.umlv.smoreau.beontime.filter.GroupFilter;
import fr.umlv.smoreau.beontime.model.Formation;
import fr.umlv.smoreau.beontime.model.Group;
import fr.umlv.smoreau.beontime.model.element.Material;
import fr.umlv.smoreau.beontime.model.element.Room;
import fr.umlv.smoreau.beontime.model.user.User;


/**
 * @author BeOnTime
 */
public class ViewTimetableWindow {
	private static final String TITRE = "Visualiser un emploi du temps";
	
	private JLabel choiceEDTLabel;
	private JLabel choice2EDTLabel;
	private JLabel periodViewEDTLabel;
	
	private JComboBox visuEDTJcb;
	private JComboBox choiceEDTJcb;
	private JComboBox choice2EDTJcb;
	private JComboBox periodViewEDTJcb;
	
	private JRadioButton semestriel;
	private JRadioButton hebdomadaire;
	
	private JDateChooser periodViewEDTDate;

	private JPanel periodViewEDTPanel = new JPanel();

	private JDialog VTWFrame;
	private GridBagLayout VTWLayout = new GridBagLayout();
    private GridBagConstraints layoutConstraints = new GridBagConstraints();
    
	private boolean isOk;
    
	private String[] formationsName;
	private Long[] formationsId;
	private String[] teachersName;
	private Long[] teachersId;
	private String[] roomsName;
	private Long[] roomsId;
	private String[] materialsName;
	private Long[] materialsId;
	private String[] groupsName;
	private Long[] groupsId;
	

	public ViewTimetableWindow() {
		VTWFrame = new JDialog(MainFrame.getInstance().getMainFrame(), TITRE, true);
		VTWFrame.getContentPane().setLayout(VTWLayout);
		this.isOk = false;
	    
	    initViewTimetableWindow();
	    
	    MainFrame mainFrame = MainFrame.getInstance();
		try {
		    Collection formations = null;
		    if (UserDao.TYPE_SECRETARY.equals(mainFrame.getUserConnected().getUserType()))
		        formations = mainFrame.getUserConnected().getFormationsInCharge();
		    else
		        formations = DaoManager.getFormationDao().getFormations();
			formationsName = new String[formations.size()+1];
			formationsId = new Long[formations.size()+1];
			formationsName[0] = "";
			int j = 1;
			for (Iterator i = formations.iterator(); i.hasNext(); ++j) {
				Formation formation = (Formation) i.next();
				formationsId[j] = formation.getIdFormation();
				formationsName[j] = formation.getHeading();
			}
		} catch (Exception e) {
			formationsId = new Long[0];
			formationsName = new String[0];
		}
		
		try {
		    Collection teachers = DaoManager.getUserDao().getTeachers();
		    teachersName = new String[teachers.size()+1];
		    teachersId = new Long[teachers.size()+1];
		    teachersName[0] = "";
			int j = 1;
			for (Iterator i = teachers.iterator(); i.hasNext(); ++j) {
				User teacher = (User) i.next();
				teachersId[j] = teacher.getIdUser();
				teachersName[j] = teacher.getName() + " " + teacher.getFirstName();
			}
		} catch (Exception e) {
		    teachersId = new Long[0];
		    teachersName = new String[0];
		}
		
		try {
		    Collection rooms = DaoManager.getElementDao().getRooms();
		    roomsName = new String[rooms.size()+1];
		    roomsId = new Long[rooms.size()+1];
		    roomsName[0] = "";
			int j = 1;
			for (Iterator i = rooms.iterator(); i.hasNext(); ++j) {
				Room room = (Room) i.next();
				roomsId[j] = room.getIdRoom();
				roomsName[j] = room.getName();
			}
		} catch (Exception e) {
		    roomsId = new Long[0];
		    roomsName = new String[0];
		}
		
		try {
		    Collection materials = DaoManager.getElementDao().getMaterials();
		    materialsName = new String[materials.size()+1];
		    materialsId = new Long[materials.size()+1];
		    materialsName[0] = "";
			int j = 1;
			for (Iterator i = materials.iterator(); i.hasNext(); ++j) {
				Material material = (Material) i.next();
				materialsId[j] = material.getIdMaterial();
				materialsName[j] = material.getName();
			}
		} catch (Exception e) {
		    materialsId = new Long[0];
		    materialsName = new String[0];
		}
	}
	
	
	private void initViewTimetableWindow() {
	    JLabel visuEDTLabel = new JLabel("Visualisation d'un emploi du temps d'un(e)");
		addComponent(VTWLayout,layoutConstraints,visuEDTLabel,2,1,1.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		VTWFrame.getContentPane().add(visuEDTLabel);
		
		
		visuEDTJcb = new JComboBox(TimeTableViewPanelBar.ALL_TYPES);
		visuEDTJcb.addItemListener(new ItemListener() {
    		public void itemStateChanged(ItemEvent e) {
    		    if (ItemEvent.SELECTED == e.getStateChange()) {
	    		    String choice = ((JComboBox)e.getSource()).getSelectedItem().toString();

	    		    choiceEDTJcb.removeAllItems();
	    		    choice2EDTLabel.setVisible(false);
				    choice2EDTJcb.setVisible(false);

	    		    if (TimeTableViewPanelBar.TYPE_VIDE.equals(choice)) {
	    		        choiceEDTLabel.setText("Veuillez choisir ... :");
	    		        choiceEDTLabel.setEnabled(false);
	    		        choiceEDTJcb.setEnabled(false);
	    		    } else {
	    		        choiceEDTLabel.setEnabled(true);
				        choiceEDTJcb.setEnabled(true);
	    		    }

	    		    if (TimeTableViewPanelBar.TYPE_FORMATION.equals(choice)) {
						choiceEDTLabel.setText("Veuillez choisir la formation :");
						for (int i = 0; i < formationsName.length; ++i)
						    choiceEDTJcb.addItem(formationsName[i]);
					} else if (TimeTableViewPanelBar.TYPE_ENSEIGNANT.equals(choice)) {
						choiceEDTLabel.setText("Veuillez choisir l'enseignant :");
						for (int i = 0; i < teachersName.length; ++i)
						    choiceEDTJcb.addItem(teachersName[i]);
					} else if (TimeTableViewPanelBar.TYPE_LOCAL.equals(choice)) {
					    choiceEDTLabel.setText("Veuillez choisir le local :");
					    for (int i = 0; i < roomsName.length; ++i)
						    choiceEDTJcb.addItem(roomsName[i]);
					} else if (TimeTableViewPanelBar.TYPE_MATERIEL.equals(choice)) {
					    choiceEDTLabel.setText("Veuillez choisir le mat�riel :");
					    for (int i = 0; i < materialsName.length; ++i)
						    choiceEDTJcb.addItem(materialsName[i]);
					} else if (TimeTableViewPanelBar.TYPE_GROUPE.equals(choice)) {
					    choiceEDTLabel.setText("Veuillez choisir la formation :");
					    for (int i = 0; i < formationsName.length; ++i)
						    choiceEDTJcb.addItem(formationsName[i]);
					    choice2EDTLabel.setVisible(true);
					    choice2EDTJcb.setVisible(true);
					}

				    VTWFrame.pack();
    		    }
    		}
		});
		addComponent(VTWLayout,layoutConstraints,visuEDTJcb,GridBagConstraints.REMAINDER,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(20,10,10,10));
		VTWFrame.getContentPane().add(visuEDTJcb);
		
		
		choiceEDTLabel = new JLabel("Veuillez choisir ... :");
		choiceEDTLabel.setEnabled(false);
		addComponent(VTWLayout,layoutConstraints,choiceEDTLabel,2,1,1.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(10,10,20,10));
		VTWFrame.getContentPane().add(choiceEDTLabel);
		
		choiceEDTJcb = new JComboBox();
		choiceEDTJcb.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (ItemEvent.SELECTED == e.getStateChange() &&
                        TimeTableViewPanelBar.TYPE_GROUPE.equals(visuEDTJcb.getSelectedItem().toString())) {
                    choice2EDTJcb.removeAllItems();

                    int index = ((JComboBox)e.getSource()).getSelectedIndex();
                    if (index == 0) {
    	    		    choice2EDTLabel.setEnabled(false);
    				    choice2EDTJcb.setEnabled(false);
    				    return;
                    } else {
                        choice2EDTLabel.setEnabled(true);
    				    choice2EDTJcb.setEnabled(true);
                    }

                    Long idFormation = formationsId[index];
                    try {
                        GroupFilter filter = new GroupFilter();
                        filter.setIdFormation(idFormation);
            		    Collection groups = DaoManager.getGroupDao().getGroups(filter);
            		    groupsName = new String[groups.size()+1];
            		    groupsId = new Long[groups.size()+1];
            		    groupsName[0] = "";
            		    choice2EDTJcb.addItem("");
            			int j = 1;
            			for (Iterator i = groups.iterator(); i.hasNext(); ++j) {
            				Group group = (Group) i.next();
            				groupsId[j] = group.getIdGroup();
            				groupsName[j] = group.getHeading();
            				choice2EDTJcb.addItem(group.getHeading());
            			}
            		} catch (Exception ex) {
            		    groupsId = new Long[0];
            		    groupsName = new String[0];
            		}
                }
            }
		});
		choiceEDTJcb.setEnabled(false);
		addComponent(VTWLayout,layoutConstraints,choiceEDTJcb,GridBagConstraints.REMAINDER,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(10,10,20,10));
		VTWFrame.getContentPane().add(choiceEDTJcb);
		
		
		choice2EDTLabel = new JLabel("Veuillez choisir le groupe :");
		choice2EDTLabel.setEnabled(false);
		choice2EDTLabel.setVisible(false);
		addComponent(VTWLayout,layoutConstraints,choice2EDTLabel,2,1,1.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(10,10,20,10));
		VTWFrame.getContentPane().add(choice2EDTLabel);
		
		choice2EDTJcb = new JComboBox();
		choice2EDTJcb.setEnabled(false);
		choice2EDTJcb.setVisible(false);
		addComponent(VTWLayout,layoutConstraints,choice2EDTJcb,GridBagConstraints.REMAINDER,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(10,10,20,10));
		VTWFrame.getContentPane().add(choice2EDTJcb);
		
		
		JLabel modeViewEDTLabel = new JLabel("Affichage de l'emploi du temps :");
		addComponent(VTWLayout,layoutConstraints,modeViewEDTLabel,GridBagConstraints.REMAINDER,1,1.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,5,10));
		VTWFrame.getContentPane().add(modeViewEDTLabel);

		
		semestriel = new JRadioButton("semestriel");
		semestriel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				periodViewEDTPanel.remove(periodViewEDTDate);
				periodViewEDTPanel.add(periodViewEDTJcb);
				periodViewEDTLabel.setText("Veuillez choisir le semestre � visualiser");
				VTWFrame.pack();
			}
		});
		addComponent(VTWLayout,layoutConstraints,semestriel,1,1,1.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(5,10,10,10));
		VTWFrame.getContentPane().add(semestriel);		
		
		hebdomadaire = new JRadioButton("hebdomadaire", true);
		hebdomadaire.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				periodViewEDTPanel.remove(periodViewEDTJcb);
				periodViewEDTPanel.add(periodViewEDTDate);
				periodViewEDTLabel.setText("Veuillez choisir la semaine � visualiser");
				VTWFrame.pack();
			}
		});
		addComponent(VTWLayout,layoutConstraints,hebdomadaire,GridBagConstraints.REMAINDER,1,1.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(5,10,10,10));
		VTWFrame.getContentPane().add(hebdomadaire);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(semestriel);
		buttonGroup.add(hebdomadaire);
		
		periodViewEDTLabel = new JLabel("Veuillez choisir la semaine � visualiser");
		addComponent(VTWLayout,layoutConstraints,periodViewEDTLabel,2,1,1.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(10,10,20,10));
		VTWFrame.getContentPane().add(periodViewEDTLabel);
		
		periodViewEDTDate = new JDateChooser();
		periodViewEDTDate.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent event) {
                Calendar date = Calendar.getInstance();
                date.setTime(periodViewEDTDate.getDate());
                date.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                periodViewEDTDate.setDate(date.getTime());
                periodViewEDTDate.revalidate();
            }
		});
		periodViewEDTDate.setDateFormatString("dd MMMMM yyyy");
		periodViewEDTDate.setDate(Calendar.getInstance().getTime());
		
		periodViewEDTJcb = new JComboBox();
		periodViewEDTJcb.addItem("1er semestre");
		periodViewEDTJcb.addItem("2�me semestre");
		
		periodViewEDTPanel.add(periodViewEDTDate);
		addComponent(VTWLayout,layoutConstraints,periodViewEDTPanel,GridBagConstraints.REMAINDER,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(10,10,20,10));
		VTWFrame.getContentPane().add(periodViewEDTPanel);
		
		
		JButton ok = new JButton("OK");
		ok.addActionListener(new ActionOk());
		addComponent(VTWLayout,layoutConstraints,ok,GridBagConstraints.RELATIVE,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		VTWFrame.getContentPane().add(ok);
		
		JButton annuler = new JButton("Annuler");
		annuler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	VTWFrame.dispose();
            }
		});
		addComponent(VTWLayout,layoutConstraints,annuler,GridBagConstraints.REMAINDER,1,0.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(20,10,10,10));
		VTWFrame.getContentPane().add(annuler);
	}
	
	public String getVisuEDTJcb() {
		return visuEDTJcb.getSelectedItem().toString();
	}
	
	public Long getChoiceEDTJcb() {
	    String type = getVisuEDTJcb();
	    int index = choiceEDTJcb.getSelectedIndex();

	    if (TimeTableViewPanelBar.TYPE_FORMATION.equals(type))
	        return formationsId[index];
	    else if (TimeTableViewPanelBar.TYPE_ENSEIGNANT.equals(type))
	        return teachersId[index];
	    else if (TimeTableViewPanelBar.TYPE_LOCAL.equals(type))
	        return roomsId[index];
	    else if (TimeTableViewPanelBar.TYPE_MATERIEL.equals(type))
	        return materialsId[index];
	    else if (TimeTableViewPanelBar.TYPE_GROUPE.equals(type))
	        return formationsId[index];

		return null;
	}
	
	public Long getChoice2EDTJcb() {
	    String type = getVisuEDTJcb();
	    int index = choice2EDTJcb.getSelectedIndex();

	    if (TimeTableViewPanelBar.TYPE_GROUPE.equals(type))
	        return groupsId[index];

		return null;
	}
	
	public String getPeriodViewEDTJcb() {
		if(semestriel.isSelected())
			return periodViewEDTJcb.getSelectedItem().toString();
		return null; 
	}
	
	public String getModeViewEDT() {
		if (semestriel.isSelected())
			return "semestriel";
		return "hebdomadaire";
	}
	
	 public Date getPeriodViewEDTDate () {
	 	if(hebdomadaire.isSelected())
	 		return periodViewEDTDate.getDate();
	 	return null;
	 }
	 
	
    /* (non-Javadoc)
     * @see fr.umlv.smoreau.beontimeSwing.graphics.windows.Window#show(java.lang.Object[])
     */
    public void show() {
    	VTWFrame.pack();
    	VTWFrame.setResizable(false);
    	VTWFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	VTWFrame.setLocationRelativeTo(null);
    	VTWFrame.setVisible(true);
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
    
	public boolean isOk() {
		return isOk;
	}
	
	private int checking() {
	    if (visuEDTJcb.getSelectedIndex() == 0)
	        return 1;
	    String type = (String) visuEDTJcb.getSelectedItem();
	    if (choiceEDTJcb.getSelectedIndex() == 0)
	        return 1;
	    if (TimeTableViewPanelBar.TYPE_GROUPE.equals(type) &&
	            choice2EDTJcb.getSelectedIndex() == 0)
	        return 1;
	    if (semestriel.isSelected())
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
				VTWFrame.dispose();
				return;
			case 1:
			    errorMessage = "Vous n'avez pas termin� de s�lectionner l'emploi du temps � visualiser";
			    break;
			case 2:
			    errorMessage = "L'affichage semestriel n'est pas encore impl�ment� !";
			    break;
			default:
				errorMessage = "Erreur inconnue";
			}
			JOptionPane.showMessageDialog(null, errorMessage, "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}
}
