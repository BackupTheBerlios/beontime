/*
 * 
 */
package fr.umlv.smoreau.beontime.client.graphics.parts;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

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
		fichier.add(new JMenuItem("Visualiser un emploi du temps"));
		fichier.add(new JMenuItem("Fermer l'emploi du temps"));
		fichier.addSeparator();
		fichier.add(new JMenuItem("Imprimer"));
		fichier.add(new JMenuItem("Exporter"));
		fichier.addSeparator();
		fichier.add(new JMenuItem("Quitter"));
		
		
		
		JMenu edition = new JMenu("Edition");
		edition.add(new JMenuItem("Couper"));
		edition.add(new JMenuItem("Copier"));		
		edition.add(new JMenuItem("Coller"));
		
		
		
		JMenu affichage = new JMenu("Affichage");
		JMenu presentation = new JMenu("Presentation");		
		presentation.add(new JMenuItem("Vertical"));
		presentation.add(new JMenuItem("Horizontal"));
		affichage.add(presentation);
		JMenu vue = new JMenu("Vue");
		vue.add(new JMenuItem("Semaine"));		
		vue.add(new JMenuItem("Semestre"));
		affichage.add(vue);
		
		
		
		JMenu utilisateur = new JMenu("Utilisateur");
		utilisateur.add(new JMenuItem("Créer"));		
		utilisateur.add(new JMenuItem("Gérer les utilisateurs"));
		
		
		
		JMenu emploi_du_temps = new JMenu("emploi du temps");
		
		JMenu matiere = new JMenu("Matière");
		matiere.add(new JMenuItem("Ajouter"));
		matiere.add(new JMenuItem("Supprimer"));
		matiere.add(new JMenuItem("Modifier"));
		matiere.add(new JMenuItem("Gérer les matières"));
		
		emploi_du_temps.add(matiere);
		
		
		JMenu cours = new JMenu("Cours");
		cours.add(new JMenuItem("Placer"));
		cours.add(new JMenuItem("Modifier"));
		cours.add(new JMenuItem("Supprimer"));
		
		emploi_du_temps.add(cours);
		
		
		JMenu groupe = new JMenu("Groupe");
		groupe.add(new JMenuItem("Créer"));
		groupe.add(new JMenuItem("Modifier"));
		groupe.add(new JMenuItem("Supprimer"));
		groupe.add(new JMenuItem("Gérer les groupes"));
		groupe.addSeparator();
		groupe.add(new JMenuItem("Générer des groupes automatiquement"));
		
		emploi_du_temps.add(groupe);
		
		
		JMenu local = new JMenu("Local"); 
		local.add(new JMenuItem("Créer"));
		local.add(new JMenuItem("Gérer les locaux"));
		
		emploi_du_temps.add(local);
		
		
		JMenu materiel = new JMenu("Matériel"); 
		materiel.add(new JMenuItem("Créer"));
		materiel.add(new JMenuItem("Gérer les matériels"));
		
		emploi_du_temps.add(materiel);
		
		
		JMenu indisponibilite = new JMenu("indisponibilite");
		indisponibilite.add(new JMenuItem("Ajouter"));
		indisponibilite.add(new JMenuItem("Gérer les indisponibilités"));
		indisponibilite.add(new JMenuItem("Recherher une disponibilité"));
		
		emploi_du_temps.add(indisponibilite);
		
		
		JMenu a_propos_de = new JMenu("?");
		a_propos_de.add(new JMenuItem("A propos de BeOnTime"));
		

		add(fichier);
		add(edition);
		add(affichage);
		add(utilisateur);
		add(emploi_du_temps);
		add(cours);
		add(groupe);
		add(local);
		add(materiel);
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


