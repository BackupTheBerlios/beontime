/*
 * 
 */
package fr.umlv.smoreau.beontime.client.graphics;

import java.awt.BorderLayout;
import java.util.Collection;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import fr.umlv.smoreau.beontime.client.graphics.parts.ButtonBar;
import fr.umlv.smoreau.beontime.client.graphics.parts.MenuBar;
import fr.umlv.smoreau.beontime.client.graphics.parts.StateBar;
import fr.umlv.smoreau.beontime.client.graphics.parts.TitleBar;
import fr.umlv.smoreau.beontime.model.Formation;
import fr.umlv.smoreau.beontime.model.Group;
import fr.umlv.smoreau.beontime.model.Unavailability;
import fr.umlv.smoreau.beontime.model.element.Material;
import fr.umlv.smoreau.beontime.model.element.Room;
import fr.umlv.smoreau.beontime.model.timetable.Course;
import fr.umlv.smoreau.beontime.model.timetable.Subject;
import fr.umlv.smoreau.beontime.model.user.Person;

/**
 * @author BeOnTime
 */
public class MainFrame {
	
	private static final String TITRE = "BeOnTime";

	private MenuBar menuBar;
	private ButtonBar buttonBar;
	private TitleBar titleBar;
	private StateBar stateBar;
	
	
    private JSplitPane splitPaneVertical;

	private JFrame mainFrame;

	private static MainFrame instance;
	
	
    /** Creates a new instance of FenetreConjugaison */
    public MainFrame() {
	
        initMainFrame();
       
    }
    
    
    public static MainFrame getInstance() {
    	if (null == instance) instance = new MainFrame();
		return instance;
	}
    /**
     * Initialise la fenetre principale de l'application.
     */
    public void initMainFrame() {
        
        menuBar = new MenuBar();
        buttonBar = new ButtonBar();
        titleBar = new TitleBar();
        stateBar = new StateBar();
        
        splitPaneVertical = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,new JPanel(),new JPanel());
        splitPaneVertical.setContinuousLayout(true);
        splitPaneVertical.setOneTouchExpandable(true);
        
       
        JPanel panelHaut = new JPanel();
        panelHaut.setLayout(new BoxLayout(panelHaut, BoxLayout.Y_AXIS));
        panelHaut.add(menuBar.getPanel());
        panelHaut.add(buttonBar.getButtonBarPanel());
        panelHaut.add(titleBar.getTitleBarPanel());
        
        mainFrame = new JFrame();
        mainFrame.getContentPane().add(panelHaut,BorderLayout.NORTH);
        mainFrame.getContentPane().add(splitPaneVertical,BorderLayout.CENTER);
        mainFrame.getContentPane().add(stateBar,BorderLayout.CENTER);
        
    }
    

    
	public void open() {
		
		mainFrame.pack();
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
	
	}
	
	public void close() {
		System.exit(0);
	}

	public Course getCourseSelected() {
		//TODO à implémenter
		return null;
	}
	
	public Subject getSubjectSelected() {
		//TODO à implémenter
		return null;
	}
	
	public Unavailability getUnavailabilitySelected() {
		//TODO à implémenter
		return null;
	}
	
	public Group getGroupSelected() {
		//TODO à implémenter
		return null;
	}
	
	public Collection getGroupsSelected() {
		//TODO à implémenter
		return null;
	}
	
	public Formation getFormationSelected() {
		//TODO à implémenter
		return null;
	}
	
	public Person getUserSelected() {
		//TODO à implémenter
		return null;
	}
	
	public Material getMaterialSelected() {
		//TODO à implémenter
		return null;
	}
		
	public Room getRoomSelected() {
		//TODO à implémenter
		return null;
	}
	public static void main(String[] args){
		MainFrame mf=new MainFrame();
		mf.open();
	}

	public JFrame getMainFrame() {
		return mainFrame;
	}
}
