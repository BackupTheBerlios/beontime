package fr.umlv.smoreau.beontime.client.graphics.parts;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import fr.umlv.smoreau.beontime.client.DaoManager;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.filter.GroupFilter;
import fr.umlv.smoreau.beontime.filter.TimetableFilter;
import fr.umlv.smoreau.beontime.model.Formation;
import fr.umlv.smoreau.beontime.model.Group;
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
	private JComboBox jcbGroupEDT;
	
	private static final String TYPE_VIDE = "";
	private static final String TYPE_FORMATION = "Formation";
	private static final String TYPE_ENSEIGNANT = "Enseignant";
	private static final String TYPE_GROUPE = "Groupe";
	private static final String TYPE_LOCAL = "Local";
	private static final String TYPE_MATERIEL = "Matériel";
	private static final String[] ALL_TYPES = { TYPE_VIDE, TYPE_FORMATION, TYPE_ENSEIGNANT, TYPE_GROUPE, TYPE_LOCAL, TYPE_MATERIEL };
	
	
	public TimeTableViewPanelBar(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		
		final GridBagLayout visuEDTPanelLayout = new GridBagLayout();
		final GridBagConstraints layoutConstraints = new GridBagConstraints();
		setLayout(visuEDTPanelLayout);
		//visuEDTLabel = new JLabel("Visualiser un emploi du temps");
		//addComponent(visuEDTPanelLayout,layoutConstraints,visuEDTLabel,1,1,GridBagConstraints.REMAINDER,1,0.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(5,5,5,5));
		//add(visuEDTLabel);
		jcbTypeEDT = new JComboBox(ALL_TYPES);
		jcbTypeEDT.addItemListener(new ItemListenerType(this,visuEDTPanelLayout,layoutConstraints));
		addComponent(visuEDTPanelLayout,layoutConstraints,jcbTypeEDT,1,1,1,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(2,2,2,2));
		add(jcbTypeEDT);
		jcbSubjectEDT = new JComboBox();
		jcbSubjectEDT.addItemListener(new ItemListenerSubject());
		addComponent(visuEDTPanelLayout,layoutConstraints,jcbSubjectEDT,2,1,1,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(2,2,2,2));
		add(jcbSubjectEDT);
		jcbGroupEDT = new JComboBox();
		jcbGroupEDT.addItemListener(new ItemListenerGroup());
		addComponent(visuEDTPanelLayout,layoutConstraints,jcbGroupEDT,3,1,1,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(2,2,2,2));
		add(jcbGroupEDT);
		jcbSubjectEDT.setEnabled(false);
		jcbGroupEDT.setEnabled(false);
	}
	
	public void init() {
	    jcbTypeEDT.setSelectedIndex(0);
	    if (jcbSubjectEDT.isEnabled())
	        jcbSubjectEDT.setSelectedIndex(0);
	    if (jcbGroupEDT.isEnabled())
	        jcbGroupEDT.setSelectedIndex(0);
	    jcbSubjectEDT.setEnabled(false);
	    jcbGroupEDT.setEnabled(false);
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
	
	private Calendar getBeginPeriod() {
	    Calendar begin = Calendar.getInstance();
		begin.setTime(mainFrame.getDateSelected());
		begin.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		begin.set(Calendar.HOUR_OF_DAY, 0);
		begin.set(Calendar.MINUTE, 0);
		begin.set(Calendar.SECOND, 0);
		return begin;
	}
	
	private Calendar getEndPeriod() {
	    Calendar end = Calendar.getInstance();
		end.setTime(mainFrame.getDateSelected());
		end.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		end.set(Calendar.HOUR_OF_DAY, 23);
		end.set(Calendar.MINUTE, 59);
		end.set(Calendar.SECOND, 59);
		return end;
	}
	
	private class ItemListenerType implements ItemListener {
	    private JPanel panel;
	    GridBagLayout visuEDTPanelLayout;
	    GridBagConstraints layoutConstraints;

	    public ItemListenerType(JPanel panel,GridBagLayout visuEDTPanelLayout, GridBagConstraints layoutConstraints) {
	        this.panel = panel;
	        this.visuEDTPanelLayout = visuEDTPanelLayout;
	        this.layoutConstraints = layoutConstraints;

	    }

		public void itemStateChanged(ItemEvent event) {
			if (ItemEvent.SELECTED == event.getStateChange()) {
				jcbSubjectEDT.removeAllItems();
				try {
				    jcbSubjectEDT.setEnabled(false);
				    jcbGroupEDT.setEnabled(false);
				    if (mainFrame.getModel().getTimetable() != null) {
					    mainFrame.getModel().setTimetable(null);
					    mainFrame.getModel().fireCloseTimetable(false);
				    }
					if (TYPE_FORMATION.equals(event.getItem())) {
						Collection formations = DaoManager.getFormationDao().getFormations();
						jcbSubjectEDT.addItem(TYPE_VIDE);
						for (Iterator i = formations.iterator(); i.hasNext(); ) {
							Formation formation = (Formation) i.next();
							jcbSubjectEDT.addItem(new Item(formation.getHeading(), formation.getIdFormation()));
						}
						jcbSubjectEDT.setEnabled(true);
					} else if (TYPE_ENSEIGNANT.equals(event.getItem())) {
						Collection teachers = DaoManager.getUserDao().getTeachers();
						jcbSubjectEDT.addItem(TYPE_VIDE);
						for (Iterator i = teachers.iterator(); i.hasNext(); ) {
							User teacher = (User) i.next();
							jcbSubjectEDT.addItem(new Item(teacher.getName()+" "+teacher.getFirstName(), teacher.getIdUser()));
						}
						jcbSubjectEDT.setEnabled(true);
					} else if (TYPE_LOCAL.equals(event.getItem())) {
						Collection rooms = DaoManager.getElementDao().getRooms();
						jcbSubjectEDT.addItem(TYPE_VIDE);
						for (Iterator i = rooms.iterator(); i.hasNext(); ) {
							Room room = (Room) i.next();
							jcbSubjectEDT.addItem(new Item(room.getName(),room.getIdRoom()));
						}
						jcbSubjectEDT.setEnabled(true);
					} else if (TYPE_MATERIEL.equals(event.getItem())) {
						Collection materials = DaoManager.getElementDao().getMaterials();
						jcbSubjectEDT.addItem(TYPE_VIDE);
						for (Iterator i = materials.iterator(); i.hasNext(); ) {
							Material material = (Material) i.next();
							jcbSubjectEDT.addItem(new Item(material.getName(), material.getIdMaterial()));
						}
						jcbSubjectEDT.setEnabled(true);
					} else if (TYPE_GROUPE.equals(event.getItem())) {
						Collection formations = DaoManager.getFormationDao().getFormations();
						jcbSubjectEDT.addItem(TYPE_VIDE);
						for (Iterator i = formations.iterator(); i.hasNext(); ) {
							Formation formation = (Formation) i.next();
							jcbSubjectEDT.addItem(new Item(formation.getHeading(), formation.getIdFormation()));
						}
						jcbSubjectEDT.setEnabled(true);
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
				    if (mainFrame.getModel().getTimetable() != null) {
					    mainFrame.getModel().setTimetable(null);
					    mainFrame.getModel().fireCloseTimetable(false);
				    }
					if (!TYPE_VIDE.equals(event.getItem())) {
					    if (TYPE_FORMATION.equals(jcbTypeEDT.getSelectedItem())) {
							Long id = ((Item)event.getItem()).getId();
							TimetableFilter filter = new TimetableFilter();
							filter.setFormation(new Formation(id));
							filter.setBeginPeriod(getBeginPeriod());
							filter.setEndPeriod(getEndPeriod());
							Timetable timetable = DaoManager.getTimetableDao().getTimetable(filter);
							mainFrame.getModel().fireShowTimetable(timetable);
					    } else if (TYPE_GROUPE.equals(jcbTypeEDT.getSelectedItem())) {
					        Long id = ((Item)event.getItem()).getId();
							GroupFilter filter = new GroupFilter();
							filter.setIdFormation(id);
							Collection groups = DaoManager.getGroupDao().getGroups(filter);
							jcbGroupEDT.removeAll();
							jcbGroupEDT.addItem("");
							for (Iterator i = groups.iterator(); i.hasNext(); )
							    jcbGroupEDT.addItem(((Group) i.next()).getHeading());
							jcbGroupEDT.setEnabled(true);
					    } else if (TYPE_ENSEIGNANT.equals(jcbTypeEDT.getSelectedItem())) {
					        Long id = ((Item)event.getItem()).getId();
							TimetableFilter filter = new TimetableFilter();
							filter.setTeacher(new User(id));
							filter.setBeginPeriod(getBeginPeriod());
							filter.setEndPeriod(getEndPeriod());
							Timetable timetable = DaoManager.getTimetableDao().getTimetable(filter);
							mainFrame.getModel().fireShowTimetable(timetable);
					    }
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Une erreur interne est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	
	private class ItemListenerGroup implements ItemListener {
		public void itemStateChanged(ItemEvent event) {
			if (ItemEvent.SELECTED == event.getStateChange()) {
				try {
					if (!TYPE_VIDE.equals(event.getItem())) {
					    //TODO récupérer l'emploi du temps du groupe (sans doute modifier le noyau)
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
