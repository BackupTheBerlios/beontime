package fr.umlv.smoreau.beontime.client.graphics.parts;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import fr.umlv.smoreau.beontime.client.actions.About;
import fr.umlv.smoreau.beontime.client.actions.authentication.Quit;
import fr.umlv.smoreau.beontime.client.actions.availability.AddUnavailability;
import fr.umlv.smoreau.beontime.client.actions.availability.ManageUnavailabilities;
import fr.umlv.smoreau.beontime.client.actions.availability.SearchAvailability;
import fr.umlv.smoreau.beontime.client.actions.element.AddMaterial;
import fr.umlv.smoreau.beontime.client.actions.element.AddRoom;
import fr.umlv.smoreau.beontime.client.actions.element.ManageMaterials;
import fr.umlv.smoreau.beontime.client.actions.element.ManageRooms;
import fr.umlv.smoreau.beontime.client.actions.group.AddGroup;
import fr.umlv.smoreau.beontime.client.actions.group.GenerateGroups;
import fr.umlv.smoreau.beontime.client.actions.group.ManageGroups;
import fr.umlv.smoreau.beontime.client.actions.group.ModifyGroup;
import fr.umlv.smoreau.beontime.client.actions.group.RemoveGroup;
import fr.umlv.smoreau.beontime.client.actions.timetable.CloseTimetable;
import fr.umlv.smoreau.beontime.client.actions.timetable.ExportTimetable;
import fr.umlv.smoreau.beontime.client.actions.timetable.PrintTimetable;
import fr.umlv.smoreau.beontime.client.actions.timetable.ViewTimetable;
import fr.umlv.smoreau.beontime.client.actions.timetable.course.AddCourse;
import fr.umlv.smoreau.beontime.client.actions.timetable.course.CopyCourse;
import fr.umlv.smoreau.beontime.client.actions.timetable.course.CutCourse;
import fr.umlv.smoreau.beontime.client.actions.timetable.course.ModifyCourse;
import fr.umlv.smoreau.beontime.client.actions.timetable.course.PasteCourse;
import fr.umlv.smoreau.beontime.client.actions.timetable.course.RemoveCourse;
import fr.umlv.smoreau.beontime.client.actions.timetable.subject.AddSubject;
import fr.umlv.smoreau.beontime.client.actions.timetable.subject.ManageSubjects;
import fr.umlv.smoreau.beontime.client.actions.timetable.subject.ModifySubject;
import fr.umlv.smoreau.beontime.client.actions.timetable.subject.RemoveSubject;
import fr.umlv.smoreau.beontime.client.actions.user.AddUser;
import fr.umlv.smoreau.beontime.client.actions.user.ManageUsers;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;

/**
 * @author BeOnTime
 */
public class MenuBar extends JMenuBar {
	private JMenu [] tabMenu;
	private MainFrame mainFrame;

	public MenuBar(MainFrame mainFrame) {
		initJMenuBar();
		this.mainFrame = mainFrame;
	}
	

	private void initJMenuBar() {
		JMenu fichier = new JMenu("Fichier");
		fichier.add(new JMenuItem(new ViewTimetable(mainFrame)));
		fichier.add(new JMenuItem(new CloseTimetable(mainFrame)));
		fichier.addSeparator();
		fichier.add(new JMenuItem(new PrintTimetable("Imprimer",null)));
		fichier.add(new JMenuItem(new ExportTimetable("Exporter", mainFrame)));
		fichier.addSeparator();
		fichier.add(new JMenuItem(new Quit(mainFrame)));
		
		
		
		JMenu edition = new JMenu("Edition");
		edition.add(new JMenuItem(new CutCourse("Couper", mainFrame)));
		edition.add(new JMenuItem(new CopyCourse("Copier", mainFrame)));		
		edition.add(new JMenuItem(new PasteCourse("Coller", mainFrame)));
		
		
		
		JMenu affichage = new JMenu("Affichage");
		JMenu presentation = new JMenu("Présentation");		
		presentation.add(new JMenuItem("Verticale"));
		presentation.add(new JMenuItem("Horizontale"));
		affichage.add(presentation);
		JMenu vue = new JMenu("Vue");
		vue.add(new JMenuItem("Semaine"));		
		vue.add(new JMenuItem("Semestre"));
		affichage.add(vue);
		
		
		
		JMenu utilisateur = new JMenu("Utilisateur");
		utilisateur.add(new JMenuItem(new AddUser("Créer", mainFrame)));		
		utilisateur.add(new JMenuItem(new ManageUsers(mainFrame)));
		
		
		
		JMenu emploi_du_temps = new JMenu("Emploi du temps");
		
		JMenu matiere = new JMenu("Matière");
		matiere.add(new JMenuItem(new AddSubject("Ajouter", mainFrame)));
		matiere.add(new JMenuItem(new ModifySubject("Modifier", mainFrame)));
		matiere.add(new JMenuItem(new RemoveSubject("Supprimer", mainFrame)));
		matiere.add(new JMenuItem(new ManageSubjects(mainFrame)));
		
		emploi_du_temps.add(matiere);
		
		
		JMenu cours = new JMenu("Cours");
		cours.add(new JMenuItem(new AddCourse("Placer", mainFrame)));
		cours.add(new JMenuItem(new ModifyCourse("Modifier", mainFrame)));
		cours.add(new JMenuItem(new RemoveCourse("Supprimer", mainFrame)));
		
		emploi_du_temps.add(cours);
		
		
		JMenu groupe = new JMenu("Groupe");
		groupe.add(new JMenuItem(new AddGroup("Créer", mainFrame)));
		groupe.add(new JMenuItem(new ModifyGroup("Modifier", mainFrame)));
		groupe.add(new JMenuItem(new RemoveGroup("Supprimer", mainFrame)));
		groupe.add(new JMenuItem(new ManageGroups(mainFrame)));
		groupe.addSeparator();
		groupe.add(new JMenuItem(new GenerateGroups(mainFrame)));
		
		emploi_du_temps.add(groupe);

		
		JMenu local = new JMenu("Local"); 
		local.add(new JMenuItem(new AddRoom("Créer", mainFrame)));
		local.add(new JMenuItem(new ManageRooms(mainFrame)));
		
		emploi_du_temps.add(local);
		
		
		JMenu materiel = new JMenu("Matériel"); 
		materiel.add(new JMenuItem(new AddMaterial("Créer", mainFrame)));
		materiel.add(new JMenuItem(new ManageMaterials(mainFrame)));
		
		emploi_du_temps.add(materiel);
		
		
		
		JMenu indisponibilite = new JMenu("Indisponibilite");
		indisponibilite.add(new JMenuItem(new AddUnavailability("Ajouter", mainFrame)));
		indisponibilite.add(new JMenuItem(new ManageUnavailabilities(mainFrame)));
		indisponibilite.add(new JMenuItem(new SearchAvailability(mainFrame)));
		
		
		JMenu a_propos_de = new JMenu("?");
		a_propos_de.add(new JMenuItem(new About(mainFrame)));
		

		add(fichier);
		add(edition);
		add(affichage);
		add(utilisateur);
		add(emploi_du_temps);
		add(indisponibilite);
		add(a_propos_de);
	}
}


