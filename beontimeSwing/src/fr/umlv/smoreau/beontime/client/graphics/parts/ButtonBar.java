/*
 * 
 */
package fr.umlv.smoreau.beontime.client.graphics.parts;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import fr.umlv.smoreau.beontime.client.actions.forms.AddCourse;
import fr.umlv.smoreau.beontime.client.actions.forms.AddMaterial;
import fr.umlv.smoreau.beontime.client.actions.forms.AddRoom;
import fr.umlv.smoreau.beontime.client.actions.forms.AddSubject;
import fr.umlv.smoreau.beontime.client.actions.forms.AddUser;
import fr.umlv.smoreau.beontime.client.actions.forms.CopyCourse;
import fr.umlv.smoreau.beontime.client.actions.forms.CutCourse;
import fr.umlv.smoreau.beontime.client.actions.forms.DeleteCourse;
import fr.umlv.smoreau.beontime.client.actions.forms.ExportTimetable;
import fr.umlv.smoreau.beontime.client.actions.forms.GenerateGroups;
import fr.umlv.smoreau.beontime.client.actions.forms.PasteCourse;
import fr.umlv.smoreau.beontime.client.actions.forms.PrintTimetable;
import fr.umlv.smoreau.beontime.client.actions.forms.ShowTimetable;
import fr.umlv.smoreau.beontime.client.actions.forms.ViewTimetable;

/**
 * @author BeOnTime
 */
public class ButtonBar extends JToolBar{
	
	private JLabel visuEDTLabel;
	private JComboBox jcbTypeEDT;
	private JComboBox jcbSubjectEDT;
	private static int nbGroups=5;
	private String[] defaultButtonName[]=new String[nbGroups][5];
	private Action[] defaultAction[]=new Action[nbGroups][5];
	
	private ArrayList listButtonDefault = new ArrayList();
	private ArrayList listAllButton=new ArrayList();
	private ArrayList listButtonAdd = new ArrayList();
	private JPanel [] defaultgroupsButton=new JPanel[nbGroups];
	private JPanel buttonPanel = new JPanel();
	private JPanel visuEDTPanel = new JPanel();
	private JButton[] defaultButtons []=new JButton[nbGroups][5];
	
	
	public ButtonBar() {
		
		
		initButton();
		initVisuEDTPanel();
		initButtonPanel();
		
		
		BorderLayout buttonBarPanelLayout = new BorderLayout();
		setLayout(buttonBarPanelLayout);
		
		add(buttonPanel, BorderLayout.WEST);
		add(visuEDTPanel, BorderLayout.EAST);
		
	}
	
	
	/**
	 * 
	 */
	private void initButton() {
		defaultButtonName[0] = new String[]{"Visualiser un EDT","Imprimer l'EDT","Exporter l'EDT"};
		defaultButtonName[1] = new String[]{"Couper un cours","Copier un cours","Coller le cours"};
		defaultButtonName[2] = new String[]{"Créer un utilisateur","Ajouter une matière","Créer un local","Créer un matériel","Générer des groupes"};
		defaultButtonName[3] = new String[]{"Placer un cours","Supprimer le cours"};
		defaultButtonName[4] = new String[]{"Afficher l'EDT par semaines","Afficher l'EDT par semestre"};

		defaultAction[0] = new Action[]{ViewTimetable.getAction(),PrintTimetable.getAction(),ExportTimetable.getAction()};
		defaultAction[1] = new Action[]{CutCourse.getAction(),CopyCourse.getAction(),PasteCourse.getAction()};
		defaultAction[2] = new Action[]{AddUser.getAction(),AddSubject.getAction(),AddRoom.getAction(),AddMaterial.getAction(),GenerateGroups.getAction()};
		defaultAction[3] = new Action[]{AddCourse.getAction(),DeleteCourse.getAction()};
		defaultAction[4] = new Action[]{ShowTimetable.getAction(),ShowTimetable.getAction()};
		initButtonPanel();
		for(int i=0;i<nbGroups;i++){
			for(int j=0;j<defaultButtonName[i].length;j++){
			 	defaultButtons[i][j] = new JButton(defaultAction[i][j]);
				final JButton button = defaultButtons[i][j];
				defaultButtons[i][j].setToolTipText(defaultButtonName[i][j]);
				defaultButtons[i][j].setPreferredSize(new Dimension(28,28));
				defaultButtons[i][j].setVisible(true);
				defaultgroupsButton[i].add(defaultButtons[i][j]);
			}
			buttonPanel.add(defaultgroupsButton[i]);
		}
		
	}


	private void initVisuEDTPanel() {
		
		GridBagLayout visuEDTPanelLayout = new GridBagLayout();
	    GridBagConstraints layoutConstraints = new GridBagConstraints();

	    visuEDTPanel.setLayout(visuEDTPanelLayout);
		
		visuEDTLabel = new JLabel("Visualiser un emploi du temps");
		addComponent(visuEDTPanelLayout,layoutConstraints,visuEDTLabel,1,GridBagConstraints.REMAINDER,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(10,10,10,10));
		visuEDTPanel.add(visuEDTLabel);
		
		
		jcbTypeEDT = new JComboBox();
		jcbTypeEDT.addItem("Formation");
		jcbTypeEDT.addItem("Enseignant");
		jcbTypeEDT.addItem("Groupe");
		jcbTypeEDT.addItem("Local");
		jcbTypeEDT.addItem("Materiel");
		addComponent(visuEDTPanelLayout,layoutConstraints,jcbTypeEDT,1,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(10,10,5,10));
		visuEDTPanel.add(jcbTypeEDT);
		
		jcbSubjectEDT = new JComboBox();
		addComponent(visuEDTPanelLayout,layoutConstraints,jcbSubjectEDT,1,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,10,10,10));
		visuEDTPanel.add(jcbSubjectEDT);

	}
	
	private void initButtonPanel() {
		for (int i = 0; i < nbGroups;i++) 
			defaultgroupsButton[i] = new JPanel(new FlowLayout(FlowLayout.LEFT, 3, 1));		
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

	/*
	public static void main(String[] args){
		
		ButtonBar bar = new ButtonBar();
		
		JFrame mafenetre = new JFrame();
		mafenetre.getContentPane().add(bar.getButtonBarPanel());
		mafenetre.setTitle("Essai td");
		mafenetre.pack();
		mafenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mafenetre.setVisible(true);
	}*/
	
}
