/*
 * 
 */
package fr.umlv.smoreau.beontime.client.graphics.parts;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;

import fr.umlv.smoreau.beontime.client.actions.ActionFactory;
import fr.umlv.smoreau.beontime.client.actions.ActionFormList;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;

/**
 * @author BeOnTime
 */
public class ButtonBar{

	private static JToolBar toolBar;
	private JMenuItem[] view;
	private MainFrame mainFrame;
	private ArrayList defToolBar;
	private int deb_index;
	private int nb_max;
	private ActionFormList afl=new ActionFormList();
	
	public ButtonBar(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT,3,3));
		deb_index = 0;
		nb_max = 255;
		loadToolBar();
		changeAll(defToolBar);	
	}
	
	
	/**
	 * 
	 */


	public void addButton(String action, String text) {
		ActionFactory.init(mainFrame);
		JButton bouton = toolBar.add((Action) ActionFactory.getActionForName(action,""));
		bouton.setToolTipText(text);
		bouton.setText("");
		//bouton.setBackground(new Color(255,255,255,2));
		bouton.setBorderPainted(true);
		bouton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				mainFrame.refresh();
				
			}
		});
		defToolBar.add(text);
	}
	public void changeAll(ArrayList buttons) {
		toolBar.removeAll();
		toolBar.repaint();
		defToolBar = new ArrayList(buttons);
		int tmp = defToolBar.size();
		for (int i = deb_index; i < tmp; ++i) {
			String act = (String) defToolBar.get(i);
			if (act.compareTo("----------") == 0)
				toolBar.addSeparator();
			else {
				addButton((String) afl.getActionForName(act),act);
			}
		}
		defToolBar = new ArrayList(buttons);
	}

	public ArrayList getDefToolBar() {
		return defToolBar;
	}


	public void saveToolBar() {
		FileOutputStream fos = null;
		ObjectOutputStream oos=null;
		try {
			fos =new FileOutputStream(System.getProperty("user.home")+ "/.BoTtoolBar");
			oos = new ObjectOutputStream(fos);
			oos.writeObject(defToolBar);
		} catch (FileNotFoundException e1) {
			return;
		} catch (IOException e3) {
			return;
		}
	}

	public void loadToolBar() {
		FileInputStream fis = null;
		try {
			fis =new FileInputStream(System.getProperty("user.home") + "/.BoTtoolBar");
			ObjectInputStream ois = new ObjectInputStream(fis);
			defToolBar = (ArrayList) ois.readObject();
		} catch (FileNotFoundException e) {
			setDefaultToolBar();
			return;
		} catch (IOException e1) {
			setDefaultToolBar();
			return;
		} catch (ClassNotFoundException e) {
			setDefaultToolBar();
			return;
		}

	}

	public void setDefaultToolBar() {
		defToolBar = new ArrayList();
		addButton("ViewTimetable","Visualiser un EDT");
		addButton("PrintTimetable","Imprimer l'EDT");
		addButton("ExportTimetable","Exporter l'EDT");
		defToolBar.add("----------");
		toolBar.addSeparator();
		addButton("CutCourse","Couper un cours");
		addButton("CopyCourse","Copier un cours");
		addButton("PasteCourse","Coller le cours");
		defToolBar.add("----------");
		toolBar.addSeparator();
		addButton("AddUser","Créer un utilisateur");
		addButton("AddSubject","Ajouter une matière");
		addButton("AddRoom","Créer un local");
		addButton("AddMaterial","Créer un matériel");
		addButton("GenerateGroups","Générer des groupes");
		defToolBar.add("----------");
		toolBar.addSeparator();		
		addButton("AddCourse","Placer un cours");
		addButton("DeleteCourse","Supprimer le cours");
		defToolBar.add("----------");
		toolBar.addSeparator();
		addButton("ShowTimetable","Afficher l'EDT par semaines");
		addButton("ShowTimetable","Afficher l'EDT par semestre");
		defToolBar.add("----------");
		toolBar.addSeparator();
		addButton("ActionManageButtonWindow", "Configurer la toolbar");

	}

	public void repaintToolBar() {
		toolBar.removeAll();
		int tmp = defToolBar.size();
		ArrayList al = new ArrayList(defToolBar);
		for (int i = deb_index; i < tmp; ++i) {
			String act = (String) defToolBar.get(i);
			if (act.compareTo("----------") == 0)
				toolBar.addSeparator();
			else {
				addButton((String) afl.getActionForName(act),act);
			}
		}
		defToolBar = new ArrayList(al);
		mainFrame.refresh();
	}


	public JToolBar getToolBar() {
		return toolBar;
	}


	
}
