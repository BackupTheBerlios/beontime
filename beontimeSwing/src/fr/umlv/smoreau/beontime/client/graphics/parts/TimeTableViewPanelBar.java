package fr.umlv.smoreau.beontime.client.graphics.parts;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import fr.umlv.smoreau.beontime.client.DaoManager;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.filter.TimetableFilter;
import fr.umlv.smoreau.beontime.model.Formation;
import fr.umlv.smoreau.beontime.model.element.Material;
import fr.umlv.smoreau.beontime.model.element.Room;
import fr.umlv.smoreau.beontime.model.timetable.Timetable;
import fr.umlv.smoreau.beontime.model.user.User;

/**
 * @author BeOnTime
 */
public class TimeTableViewPanelBar extends JPanel {
    private MainFrame mainFrame;

	private JLabel visuEDTLabel;
	private JComboBox jcbTypeEDT;
	private JComboBox jcbSubjectEDT;
	
	private static final String TYPE_VIDE = "";
	private static final String TYPE_FORMATION = "Formation";
	private static final String TYPE_ENSEIGNANT = "Enseignant";
	private static final String TYPE_GROUPE = "Groupe";
	private static final String TYPE_LOCAL = "Local";
	private static final String TYPE_MATERIEL = "Matériel";
	private static final String[] ALL_TYPES = { TYPE_VIDE, TYPE_FORMATION, TYPE_ENSEIGNANT, TYPE_GROUPE, TYPE_LOCAL, TYPE_MATERIEL };


	public TimeTableViewPanelBar(MainFrame mainFrame) {
	    this.mainFrame = mainFrame;

		GridBagLayout visuEDTPanelLayout = new GridBagLayout();
	    GridBagConstraints layoutConstraints = new GridBagConstraints();
	    setLayout(visuEDTPanelLayout);
		visuEDTLabel = new JLabel("Visualiser un emploi du temps");
		addComponent(visuEDTPanelLayout,layoutConstraints,visuEDTLabel,1,GridBagConstraints.REMAINDER,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(10,10,10,10));
		add(visuEDTLabel);
		jcbTypeEDT = new JComboBox(ALL_TYPES);
		jcbTypeEDT.addItemListener(new ItemListenerType());
		addComponent(visuEDTPanelLayout,layoutConstraints,jcbTypeEDT,1,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(10,10,5,10));
		add(jcbTypeEDT);
		jcbSubjectEDT = new JComboBox();
		jcbSubjectEDT.addItemListener(new ItemListenerSubject());
		addComponent(visuEDTPanelLayout,layoutConstraints,jcbSubjectEDT,1,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,10,10,10));
		add(jcbSubjectEDT);
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
	
	private class ItemListenerType implements ItemListener {
	    public void itemStateChanged(ItemEvent event) {
	        if (ItemEvent.SELECTED == event.getStateChange()) {
	            jcbSubjectEDT.removeAllItems();
	            try {
	                if (TYPE_FORMATION.equals(event.getItem())) {
	                    Collection formations = DaoManager.getFormationDao().getFormations();
	                    jcbSubjectEDT.addItem(TYPE_VIDE);
	                    for (Iterator i = formations.iterator(); i.hasNext(); ) {
	                        Formation formation = (Formation) i.next();
	                        jcbSubjectEDT.addItem(new Item(formation.getHeading(), formation.getIdFormation()));
	                    }
	                } else if (TYPE_ENSEIGNANT.equals(event.getItem())) {
	                    Collection teachers = DaoManager.getUserDao().getTeachers();
	                    jcbSubjectEDT.addItem(TYPE_VIDE);
	                    for (Iterator i = teachers.iterator(); i.hasNext(); ) {
	                        User teacher = (User) i.next();
	                        jcbSubjectEDT.addItem(new Item(teacher.getName()+" "+teacher.getFirstName(), teacher.getIdUser()));
	                    }
	                } else if (TYPE_LOCAL.equals(event.getItem())) {
	                    Collection rooms = DaoManager.getElementDao().getRooms();
	                    jcbSubjectEDT.addItem(TYPE_VIDE);
	                    for (Iterator i = rooms.iterator(); i.hasNext(); ) {
	                        Room room = (Room) i.next();
	                        jcbSubjectEDT.addItem(new Item(room.getName(),room.getIdRoom()));
	                    }
	                } else if (TYPE_MATERIEL.equals(event.getItem())) {
	                    Collection materials = DaoManager.getElementDao().getMaterials();
	                    jcbSubjectEDT.addItem(TYPE_VIDE);
	                    for (Iterator i = materials.iterator(); i.hasNext(); ) {
	                        Material material = (Material) i.next();
	                        jcbSubjectEDT.addItem(new Item(material.getName(), material.getIdMaterial()));
	                    }
	                } else if (TYPE_GROUPE.equals(event.getItem())) {
	                    Collection formations = DaoManager.getFormationDao().getFormations();
	                    jcbSubjectEDT.addItem(TYPE_VIDE);
	                    for (Iterator i = formations.iterator(); i.hasNext(); ) {
	                        Formation formation = (Formation) i.next();
	                        jcbSubjectEDT.addItem(new Item(formation.getHeading(), formation.getIdFormation()));
	                    }
	                }
	            } catch (Exception e) {
	                JOptionPane.showMessageDialog(null, "Une erreur interne est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
	            }
	        }
        }
	}

	private class ItemListenerSubject implements ItemListener {
	    public void itemStateChanged(ItemEvent event) {
            if (ItemEvent.SELECTED == event.getStateChange()) {
                try {
	                if (!TYPE_VIDE.equals(event.getItem()) && TYPE_FORMATION.equals(jcbTypeEDT.getSelectedItem())) {
	                    Long id = ((Item)event.getItem()).getId();
	                    TimetableFilter filter = new TimetableFilter();
	                    filter.setFormation(new Formation(id));
	                    Timetable timetable = DaoManager.getTimetableDao().getTimetable(filter);
	                    mainFrame.getModel().fireShowTimetable(timetable);
	                }
                } catch (Exception e) {
	                JOptionPane.showMessageDialog(null, "Une erreur interne est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
	            }
            }
        }
	}
	
	private class Item {
	    private String text;
	    private Long id;

	    public Item(String text, Long id) {
	        this.text = text;
	        this.id = id;
	    }
	    
	    public Long getId() {
	        return id;
	    }
	    
	    public String toString() {
	        return text;
	    }
	}
}
