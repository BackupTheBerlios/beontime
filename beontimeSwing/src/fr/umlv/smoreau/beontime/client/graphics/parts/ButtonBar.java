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

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;

import fr.umlv.smoreau.beontime.client.actions.ActionFormList;
import fr.umlv.smoreau.beontime.client.actions.element.AddMaterial;
import fr.umlv.smoreau.beontime.client.actions.element.AddRoom;
import fr.umlv.smoreau.beontime.client.actions.group.GenerateGroups;
import fr.umlv.smoreau.beontime.client.actions.timetable.ExportTimetable;
import fr.umlv.smoreau.beontime.client.actions.timetable.PrintTimetable;
import fr.umlv.smoreau.beontime.client.actions.timetable.ShowTimetableBySixMonthPeriod;
import fr.umlv.smoreau.beontime.client.actions.timetable.ShowTimetableByWeek;
import fr.umlv.smoreau.beontime.client.actions.timetable.ViewTimetable;
import fr.umlv.smoreau.beontime.client.actions.timetable.course.AddCourse;
import fr.umlv.smoreau.beontime.client.actions.timetable.course.CopyCourse;
import fr.umlv.smoreau.beontime.client.actions.timetable.course.CutCourse;
import fr.umlv.smoreau.beontime.client.actions.timetable.course.PasteCourse;
import fr.umlv.smoreau.beontime.client.actions.timetable.course.RemoveCourse;
import fr.umlv.smoreau.beontime.client.actions.timetable.subject.AddSubject;
import fr.umlv.smoreau.beontime.client.actions.toolbar.ManageButtons;
import fr.umlv.smoreau.beontime.client.actions.user.AddUser;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;

/**
 * @author BeOnTime
 */
public class ButtonBar {
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

	public void addButton(Action action) {
	    if (action == null) {
	        defToolBar.add(null);
	    } else {
			JButton bouton = toolBar.add(action);
			bouton.setToolTipText((String) action.getValue(AbstractAction.NAME));
			bouton.setText("");
			bouton.setBorderPainted(true);
			bouton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					mainFrame.refresh();
				}
			});
			defToolBar.add(action);
	    }
	}

	public void changeAll(ArrayList buttons) {
		toolBar.removeAll();
		toolBar.repaint();
		defToolBar = new ArrayList(buttons);
		int tmp = defToolBar.size();
		for (int i = deb_index; i < tmp; ++i) {
			Action act = (Action) defToolBar.get(i);
			if (act == null)
				toolBar.addSeparator();
			else {
			    addButton(act);
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
		} catch (Exception e) {
			setDefaultToolBar();
		}
	}

	public void setDefaultToolBar() {
		defToolBar = new ArrayList();
		addButton(new ViewTimetable(mainFrame));
		addButton(new PrintTimetable(mainFrame));
		addButton(new ExportTimetable(mainFrame));
		addButton(null);
		toolBar.addSeparator();
		addButton(new CutCourse(mainFrame));
		addButton(new CopyCourse(mainFrame));
		addButton(new PasteCourse(mainFrame));
		addButton(null);
		toolBar.addSeparator();
		addButton(new AddUser(mainFrame));
		addButton(new AddSubject(mainFrame));
		addButton(new AddRoom(mainFrame));
		addButton(new AddMaterial(mainFrame));
		addButton(new GenerateGroups(mainFrame));
		addButton(null);
		addButton(new AddCourse(mainFrame));
		addButton(new RemoveCourse(mainFrame));
		addButton(null);
		addButton(new ShowTimetableByWeek(mainFrame));
		addButton(new ShowTimetableBySixMonthPeriod(mainFrame));
		addButton(null);
		addButton(new ManageButtons(mainFrame));

	}

	public void repaintToolBar() {
		toolBar.removeAll();
		int tmp = defToolBar.size();
		ArrayList al = new ArrayList(defToolBar);
		for (int i = deb_index; i < tmp; ++i) {
			Action act = (Action) defToolBar.get(i);
			if (act == null)
				toolBar.addSeparator();
			else {
			    addButton(act);
			}
		}
		defToolBar = new ArrayList(al);
		mainFrame.refresh();
	}


	public JToolBar getToolBar() {
		return toolBar;
	}


	
}
