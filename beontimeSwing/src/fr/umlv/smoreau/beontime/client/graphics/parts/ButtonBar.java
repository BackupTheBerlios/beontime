package fr.umlv.smoreau.beontime.client.graphics.parts;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStreamWriter;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;

import fr.umlv.smoreau.beontime.client.actions.ActionsList;
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
import fr.umlv.smoreau.beontime.dao.UserDao;

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
	private ActionsList afl=new ActionsList();
	private boolean isSecretary;
	private boolean all;
	
	public ButtonBar(MainFrame mainFrame) {
	    this.all = false;
	    if (UserDao.TYPE_SECRETARY.equals(mainFrame.getUser().getUserType()))
	        this.isSecretary = true;
	    else if (UserDao.TYPE_ADMIN.equals(mainFrame.getUser().getUserType()))
	        this.isSecretary = false;
	    else
	        throw new InvalidParameterException();
		this.mainFrame = mainFrame;
		toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT,3,3));
		deb_index = 0;
		nb_max = 255;
		loadToolBar();
		changeAll(defToolBar);	
	}
	
	public void setViewAll(boolean all) {
	    this.all = all;
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
		try {
		    FileOutputStream fos = new FileOutputStream(System.getProperty("user.home")+ "/.BoTtoolBar"+mainFrame.getUser().getIdUser());
		    OutputStreamWriter osw = new OutputStreamWriter(fos);
			for (Iterator i = defToolBar.iterator(); i.hasNext(); ) {
			    Action action = (Action) i.next();
			    if (action == null)
			        osw.write("\n");
			    else {
			        String name = action.getClass().getName();
			        Pattern p = Pattern.compile(".*\\.([^.]*)$");
			        Matcher m = p.matcher(name);
			        if (m.find()) {
			            name = name.substring(m.start(1), m.end(1));
			            osw.write(name + "\n");
			        }
			    }
			}
			osw.flush();
			osw.close();
		} catch (FileNotFoundException e1) {
			return;
		} catch (IOException e3) {
			return;
		}
	}

	public void loadToolBar() {
		FileInputStream fis = null;
		try {
		    defToolBar = new ArrayList();
			fis =new FileInputStream(System.getProperty("user.home") + "/.BoTtoolBar"+mainFrame.getUser().getIdUser());
			LineNumberReader lnr = new LineNumberReader(new InputStreamReader(fis));
			String line = lnr.readLine();
			while (line != null) {
			    Action action = ActionsList.getAction(line, mainFrame);
			    defToolBar.add(action);
			    line = lnr.readLine();
			}
		} catch (Exception e) {
			setDefaultToolBar();
		}
	}

	public void setDefaultToolBar() {
	    if (isSecretary || all) {
			defToolBar = new ArrayList();
			addButton(new ViewTimetable(mainFrame));
			addButton(new PrintTimetable(mainFrame));
			addButton(new ExportTimetable(mainFrame));
			addButton(null);
			addButton(new CutCourse(mainFrame));
			addButton(new CopyCourse(mainFrame));
			addButton(new PasteCourse(mainFrame));
			addButton(null);
			addButton(new AddUser("Cr�er un enseignant", mainFrame, UserDao.TYPE_TEACHER));
	    }
		if (!isSecretary)
		    addButton(new AddUser("Cr�er une secr�taire", mainFrame, UserDao.TYPE_SECRETARY));
		if (isSecretary || all) {
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
		}
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
