package fr.umlv.smoreau.beontime.client.graphics.parts;

import java.security.InvalidParameterException;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import fr.umlv.smoreau.beontime.client.actions.ActionsList;
import fr.umlv.smoreau.beontime.client.graphics.BoTModel;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.client.graphics.event.BoTEvent;
import fr.umlv.smoreau.beontime.client.graphics.event.DefaultBoTListener;
import fr.umlv.smoreau.beontime.dao.UserDao;

/**
 * @author BeOnTime
 */
public class MenuBar extends JMenuBar {
	private JMenu [] tabMenu;
	private MainFrame mainFrame;
	private boolean isSecretary;
	private boolean all;

	public MenuBar(MainFrame mainFrame, BoTModel model, String userType) {
	    this.all = false;
	    if (UserDao.TYPE_SECRETARY.equals(userType))
	        this.isSecretary = true;
	    else if (UserDao.TYPE_ADMIN.equals(userType))
	        this.isSecretary = false;
	    else
	        throw new InvalidParameterException();
		this.mainFrame = mainFrame;
		
		model.addBoTListener(new MenuBarListener());

		initJMenuBar();
	}
	
	public void setViewAll(boolean all) {
	    this.all = all;
	    initJMenuBar();
	}

	private void initJMenuBar() {
	    removeAll();

		JMenu fichier = new JMenu("Fichier");
		if (isSecretary || all) {
			fichier.add(new JMenuItem(ActionsList.getAction("ViewTimetable")));
			fichier.add(new JMenuItem(ActionsList.getAction("CloseTimetable")));
			fichier.addSeparator();
			fichier.add(new JMenuItem(ActionsList.getAction("PrintTimetable")));
			fichier.add(new JMenuItem(ActionsList.getAction("ExportTimetable")));
			fichier.addSeparator();
		}
		fichier.add(new JMenuItem(ActionsList.getAction("Disconnect")));
		fichier.add(new JMenuItem(ActionsList.getAction("Quit")));

		JMenu edition = null;
		JMenu affichage = null;
		if (isSecretary || all) {
			edition = new JMenu("Edition");
			edition.add(new JMenuItem(ActionsList.getAction("CutCourse")));
			edition.add(new JMenuItem(ActionsList.getAction("CopyCourse")));		
			edition.add(new JMenuItem(ActionsList.getAction("PasteCourse")));
			
			affichage = new JMenu("Affichage");
			JMenu presentation = new JMenu("Présentation");		
			presentation.add(new JMenuItem(ActionsList.getAction("ShowTimetableVertical")));
			presentation.add(new JMenuItem(ActionsList.getAction("ShowTimetableHorizontal")));
			affichage.add(presentation);
			JMenu vue = new JMenu("Vue");
			vue.add(new JMenuItem(ActionsList.getAction("ShowTimetableByWeek")));		
			vue.add(new JMenuItem(ActionsList.getAction("ShowTimetableBySixMonthPeriod")));
			affichage.add(vue);
		}
		
		JMenu utilisateur = new JMenu("Utilisateur");
		if (isSecretary || all)
		    utilisateur.add(new JMenuItem(ActionsList.getAction("AddTeacher")));
		if (!isSecretary) {
		    utilisateur.add(new JMenuItem(ActionsList.getAction("AddAdministrator")));
		    utilisateur.add(new JMenuItem(ActionsList.getAction("AddSecretary")));
		}
		utilisateur.add(new JMenuItem(ActionsList.getAction("ManageUsers")));
		
		JMenu emploi_du_temps = null;
		JMenu indisponibilite = null;
		if (isSecretary || all) {
			emploi_du_temps = new JMenu("Emploi du temps");
			
			JMenu matiere = new JMenu("Matière");
			matiere.add(new JMenuItem(ActionsList.getAction("AddSubject")));
			matiere.add(new JMenuItem(ActionsList.getAction("ModifySubject")));
			matiere.add(new JMenuItem(ActionsList.getAction("RemoveSubject")));
			matiere.add(new JMenuItem(ActionsList.getAction("ManageSubjects")));
			
			emploi_du_temps.add(matiere);
			
			JMenu cours = new JMenu("Cours");
			cours.add(new JMenuItem(ActionsList.getAction("AddCourse")));
			cours.add(new JMenuItem(ActionsList.getAction("ModifyCourse")));
			cours.add(new JMenuItem(ActionsList.getAction("RemoveCourse")));
			
			emploi_du_temps.add(cours);
			
			JMenu groupe = new JMenu("Groupe");
			groupe.add(new JMenuItem(ActionsList.getAction("AddGroup")));
			groupe.add(new JMenuItem(ActionsList.getAction("ModifyGroup")));
			groupe.add(new JMenuItem(ActionsList.getAction("RemoveGroup")));
			groupe.add(new JMenuItem(ActionsList.getAction("ManageGroups")));
			groupe.addSeparator();
			groupe.add(new JMenuItem(ActionsList.getAction("GenerateGroups")));
			
			emploi_du_temps.add(groupe);
			
			JMenu local = new JMenu("Local"); 
			local.add(new JMenuItem(ActionsList.getAction("AddRoom")));
			local.add(new JMenuItem(ActionsList.getAction("ManageRooms")));
			
			emploi_du_temps.add(local);
			
			JMenu materiel = new JMenu("Matériel"); 
			materiel.add(new JMenuItem(ActionsList.getAction("AddMaterial")));
			materiel.add(new JMenuItem(ActionsList.getAction("ManageMaterials")));
			
			emploi_du_temps.add(materiel);
			
			indisponibilite = new JMenu("Indisponibilité");
			indisponibilite.add(new JMenuItem(ActionsList.getAction("AddUnavailability")));
			indisponibilite.add(new JMenuItem(ActionsList.getAction("ManageUnavailabilities")));
			indisponibilite.add(new JMenuItem(ActionsList.getAction("SearchAvailability")));
		}
		
		JMenu outils = null;
		if (!isSecretary) {
			outils = new JMenu("Outils");
			outils.add(new JMenuItem(ActionsList.getAction("ModifyDBParameters")));
			if (!all)
			    outils.add(new JMenuItem(ActionsList.getAction("ViewAllFunctionalities")));
		}
		
		JMenu a_propos_de = new JMenu("?");
		a_propos_de.add(new JMenuItem(ActionsList.getAction("About")));
		

		add(fichier);
		if (edition != null)
		    add(edition);
		if (affichage != null)
		    add(affichage);
		add(utilisateur);
		if (emploi_du_temps != null)
		    add(emploi_du_temps);
		if (indisponibilite != null)
		    add(indisponibilite);
		if (outils != null)
		    add(outils);
		add(a_propos_de);
	}
	
	
	private class MenuBarListener extends DefaultBoTListener {
		public void refreshAll(BoTEvent e) {
		    ActionsList.getAction("CloseTimetable").setEnabled(true);
		    ActionsList.getAction("PrintTimetable").setEnabled(true);
		    ActionsList.getAction("ExportTimetable").setEnabled(true);
		    ActionsList.getAction("ShowTimetableVertical").setEnabled(true);
		    ActionsList.getAction("ShowTimetableHorizontal").setEnabled(true);
		    //ActionsList.getAction("ShowTimetableByWeek").setEnabled(true);
		    //ActionsList.getAction("ShowTimetableBySixMonthPeriod").setEnabled(true);
		    ActionsList.getAction("AddSubject").setEnabled(true);
		    ActionsList.getAction("ManageSubjects").setEnabled(true);
		    ActionsList.getAction("AddGroup").setEnabled(true);
		    ActionsList.getAction("ManageGroups").setEnabled(true);
		    ActionsList.getAction("GenerateGroups").setEnabled(true);
		}
		
		public void closeTimetable(BoTEvent e) {
		    ActionsList.getAction("CloseTimetable").setEnabled(false);
		    ActionsList.getAction("PrintTimetable").setEnabled(false);
		    ActionsList.getAction("ExportTimetable").setEnabled(false);
		    ActionsList.getAction("ShowTimetableVertical").setEnabled(false);
		    ActionsList.getAction("ShowTimetableHorizontal").setEnabled(false);
		    ActionsList.getAction("ShowTimetableByWeek").setEnabled(false);
		    ActionsList.getAction("ShowTimetableBySixMonthPeriod").setEnabled(false);
		    ActionsList.getAction("AddSubject").setEnabled(false);
		    ActionsList.getAction("ManageSubjects").setEnabled(false);
		    ActionsList.getAction("AddGroup").setEnabled(false);
		    ActionsList.getAction("ManageGroups").setEnabled(false);
		    ActionsList.getAction("GenerateGroups").setEnabled(false);
		}
	}
}


