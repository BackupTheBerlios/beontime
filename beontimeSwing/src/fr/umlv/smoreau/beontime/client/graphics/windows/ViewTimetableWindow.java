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
import com.toedter.calendar.JYearChooser;

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
	private JYearChooser yearChooser;

	private JPanel periodViewEDTPanel = new JPanel();

	private JDialog VTWFrame;
	private GridBagLayout VTWLayout = new GridBagLayout();
    private GridBagConstraints layoutConstraints = new GridBagConstraints();
    
	private boolean isOk;
    
	private String[] formationsName;
	private Formation[] formations;
	private String[] teachersName;
	private User[] teachers;
	private String[] roomsName;
	private Room[] rooms;
	private String[] materialsName;
	private Material[] materials;
	private String[] groupsName;
	private Group[] groups;
	

	public ViewTimetableWindow() {
		VTWFrame = new JDialog(MainFrame.getInstance().getMainFrame(), TITRE, true);
		VTWFrame.getContentPane().setLayout(VTWLayout);
		this.isOk = false;
	    
	    initViewTimetableWindow();
	    
	    MainFrame mainFrame = MainFrame.getInstance();
		try {
		    Collection f = null;
		    if (UserDao.TYPE_SECRETARY.equals(mainFrame.getUserConnected().getUserType()))
		        f = mainFrame.getUserConnected().getFormationsInCharge();
		    else
		        f = DaoManager.getFormationDao().getFormations();
			formationsName = new String[f.size()+1];
			formations = new Formation[f.size()+1];
			formationsName[0] = "";
			int j = 1;
			for (Iterator i = f.iterator(); i.hasNext(); ++j) {
			    formations[j] = (Formation) i.next();
				formationsName[j] = formations[j].getHeading();
			}
		} catch (Exception e) {
			formations = new Formation[0];
			formationsName = new String[0];
		}
		
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
		} catch (Exception e) {
		    teachers = new User[0];
		    teachersName = new String[0];
		}
		
		try {
		    Collection r = DaoManager.getElementDao().getRooms();
		    roomsName = new String[r.size()+1];
		    rooms = new Room[r.size()+1];
		    roomsName[0] = "";
			int j = 1;
			for (Iterator i = r.iterator(); i.hasNext(); ++j) {
			    rooms[j] = (Room) i.next();
				roomsName[j] = rooms[j].getName();
			}
		} catch (Exception e) {
		    rooms = new Room[0];
		    roomsName = new String[0];
		}
		
		try {
		    Collection m = DaoManager.getElementDao().getMaterials();
		    materialsName = new String[m.size()+1];
		    materials = new Material[m.size()+1];
		    materialsName[0] = "";
			int j = 1;
			for (Iterator i = m.iterator(); i.hasNext(); ++j) {
			    materials[j] = (Material) i.next();
				materialsName[j] = materials[j].getName();
			}
		} catch (Exception e) {
		    materials = new Material[0];
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
					    choiceEDTLabel.setText("Veuillez choisir le matériel :");
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

                    Long idFormation = formations[index].getIdFormation();
                    try {
                        GroupFilter filter = new GroupFilter();
                        filter.setIdFormation(idFormation);
            		    Collection g = DaoManager.getGroupDao().getGroups(filter);
            		    groupsName = new String[g.size()+1];
            		    groups = new Group[g.size()+1];
            		    groupsName[0] = "";
            		    choice2EDTJcb.addItem("");
            			int j = 1;
            			for (Iterator i = g.iterator(); i.hasNext(); ++j) {
            			    groups[j] = (Group) i.next();
            				groupsName[j] = groups[j].getHeading();
            				choice2EDTJcb.addItem(groups[j].getHeading());
            			}
            		} catch (Exception ex) {
            		    groups = new Group[0];
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
				periodViewEDTLabel.setText("Veuillez choisir le semestre à visualiser");
				periodViewEDTPanel.add(yearChooser);
				VTWFrame.pack();
			}
		});
		addComponent(VTWLayout,layoutConstraints,semestriel,1,1,1.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(5,10,10,10));
		VTWFrame.getContentPane().add(semestriel);		
		
		hebdomadaire = new JRadioButton("hebdomadaire", true);
		hebdomadaire.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				periodViewEDTPanel.remove(periodViewEDTJcb);
				periodViewEDTPanel.remove(yearChooser);
				periodViewEDTPanel.add(periodViewEDTDate);
				periodViewEDTLabel.setText("Veuillez choisir la semaine à visualiser");
				VTWFrame.pack();
			}
		});
		addComponent(VTWLayout,layoutConstraints,hebdomadaire,GridBagConstraints.REMAINDER,1,1.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(5,10,10,10));
		VTWFrame.getContentPane().add(hebdomadaire);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(semestriel);
		buttonGroup.add(hebdomadaire);
		
		periodViewEDTLabel = new JLabel("Veuillez choisir la semaine à visualiser");
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
		periodViewEDTJcb.addItem("<html>1<sup>er</sup> semestre</html>");
		periodViewEDTJcb.addItem("<html>2<sup>ème</sup> semestre</html>");
		
		yearChooser = new JYearChooser();
		
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
	
	public Object getChoiceEDTJcb() {
	    String type = getVisuEDTJcb();
	    int index = choiceEDTJcb.getSelectedIndex();

	    if (TimeTableViewPanelBar.TYPE_FORMATION.equals(type))
	        return formations[index];
	    else if (TimeTableViewPanelBar.TYPE_ENSEIGNANT.equals(type))
	        return teachers[index];
	    else if (TimeTableViewPanelBar.TYPE_LOCAL.equals(type))
	        return rooms[index];
	    else if (TimeTableViewPanelBar.TYPE_MATERIEL.equals(type))
	        return materials[index];
	    else if (TimeTableViewPanelBar.TYPE_GROUPE.equals(type))
	        return formations[index];

		return null;
	}
	
	public Object getChoice2EDTJcb() {
	    String type = getVisuEDTJcb();
	    int index = choice2EDTJcb.getSelectedIndex();

	    if (TimeTableViewPanelBar.TYPE_GROUPE.equals(type))
	        return groups[index];

		return null;
	}
	
	public int getPeriodViewEDTJcb() {
		if (semestriel.isSelected())
			return periodViewEDTJcb.getSelectedIndex() + 1;
		return 0; 
	}
	
	public int getModeViewEDT() {
		if (semestriel.isSelected())
			return MainFrame.VIEW_HALF_YEAR;
		return MainFrame.VIEW_WEEK;
	}
	
	 public Date getPeriodViewEDTDate () {
	 	if (hebdomadaire.isSelected())
	 		return periodViewEDTDate.getDate();
	 	return null;
	 }
	 
	 public int getYear() {
	     return yearChooser.getValue();
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
			    errorMessage = "Vous n'avez pas terminé de sélectionner l'emploi du temps à visualiser";
			    break;
			default:
				errorMessage = "Erreur inconnue";
			}
			JOptionPane.showMessageDialog(null, errorMessage, "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}
}
