package fr.umlv.smoreau.beontime.client.graphics.parts;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import fr.umlv.smoreau.beontime.client.actions.forms.ActionAboutForm;
import fr.umlv.smoreau.beontime.client.actions.forms.AddCourse;
import fr.umlv.smoreau.beontime.client.actions.forms.AddMaterial;
import fr.umlv.smoreau.beontime.client.actions.forms.AddRoom;
import fr.umlv.smoreau.beontime.client.actions.forms.AddSubject;
import fr.umlv.smoreau.beontime.client.actions.forms.AddUser;
import fr.umlv.smoreau.beontime.client.actions.forms.CopyCourse;
import fr.umlv.smoreau.beontime.client.actions.forms.CutCourse;
import fr.umlv.smoreau.beontime.client.actions.forms.ExportTimetable;
import fr.umlv.smoreau.beontime.client.actions.forms.GenerateGroups;
import fr.umlv.smoreau.beontime.client.actions.forms.PasteCourse;
import fr.umlv.smoreau.beontime.client.actions.forms.PrintTimetable;
import fr.umlv.smoreau.beontime.client.actions.forms.ViewTimetable;

/**
 * @author BeOnTime
 */
public class MenuBar extends JMenuBar {
	
	private JMenu [] tabMenu;
	

	
	
	public MenuBar(){
		
		initJMenuBar();
		
	}
	

	private void initJMenuBar(){
		
		
		JMenu fichier = new JMenu("Fichier");
		fichier.add(new JMenuItem(ViewTimetable.getAction("Visualiser un emploi du temps",null)));
		fichier.add(new JMenuItem("Fermer l'emploi du temps"));
		fichier.addSeparator();
		fichier.add(new JMenuItem(PrintTimetable.getAction("Imprimer",null)));
		fichier.add(new JMenuItem(ExportTimetable.getAction("Exporter",null)));
		fichier.addSeparator();
		fichier.add(new JMenuItem("Quitter"));
		
		
		
		JMenu edition = new JMenu("Edition");
		edition.add(new JMenuItem(CutCourse.getAction("Couper",null)));
		edition.add(new JMenuItem(CopyCourse.getAction("Copier",null)));		
		edition.add(new JMenuItem(PasteCourse.getAction("Coller",null)));
		
		
		
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
		utilisateur.add(new JMenuItem(AddUser.getAction("Créer",null)));		
		utilisateur.add(new JMenuItem("Gérer les utilisateurs"));
		
		
		
		JMenu emploi_du_temps = new JMenu("Emploi du temps");
		
		JMenu matiere = new JMenu("Matière");
		matiere.add(new JMenuItem(AddSubject.getAction("Ajouter",null)));
		matiere.add(new JMenuItem("Supprimer"));
		matiere.add(new JMenuItem("Modifier"));
		matiere.add(new JMenuItem("Gérer les matières"));
		
		emploi_du_temps.add(matiere);
		
		
		JMenu cours = new JMenu("Cours");
		cours.add(new JMenuItem(AddCourse.getAction("Placer",null)));
		cours.add(new JMenuItem("Modifier"));
		cours.add(new JMenuItem("Supprimer"));
		
		emploi_du_temps.add(cours);
		
		
		JMenu groupe = new JMenu("Groupe");
		groupe.add(new JMenuItem("Créer"));
		groupe.add(new JMenuItem("Modifier"));
		groupe.add(new JMenuItem("Supprimer"));
		groupe.add(new JMenuItem("Gérer les groupes"));
		groupe.addSeparator();
		groupe.add(new JMenuItem(GenerateGroups.getAction("Générer des groupes automatiquement",null)));
		
		emploi_du_temps.add(groupe);
		
		
		JMenu local = new JMenu("Local"); 
		local.add(new JMenuItem(AddRoom.getAction("Créer")));
		local.add(new JMenuItem("Gérer les locaux"));
		
		emploi_du_temps.add(local);
		
		
		JMenu materiel = new JMenu("Matériel"); 
		materiel.add(new JMenuItem(AddMaterial.getAction("Créer")));
		materiel.add(new JMenuItem("Gérer les matériels"));
		
		emploi_du_temps.add(materiel);
		
		
		
		JMenu indisponibilite = new JMenu("Indisponibilite");
		indisponibilite.add(new JMenuItem("Ajouter"));
		indisponibilite.add(new JMenuItem("Gérer les indisponibilités"));
		indisponibilite.add(new JMenuItem("Recherher une disponibilité"));
		
		
		JMenu a_propos_de = new JMenu("?");
		a_propos_de.add(new JMenuItem(ActionAboutForm.getAction("A propos de BeOnTime",null)));
		

		add(fichier);
		add(edition);
		add(affichage);
		add(utilisateur);
		add(emploi_du_temps);
		add(indisponibilite);
		add(a_propos_de);
	}
	
	
	/*public static void main(String[] args){
		
		MenuBar maBar = new MenuBar();
		
		JFrame mafenetre = new JFrame();
		mafenetre.getContentPane().add(maBar.getPanel());
		mafenetre.setTitle("Essai td");
		mafenetre.pack();
		mafenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mafenetre.setVisible(true);
		}*/

}


