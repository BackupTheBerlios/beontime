/*
 * 
 */
package fr.umlv.smoreau.beontime.client.graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.util.Collection;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.plaf.metal.MetalLookAndFeel;

import fr.umlv.smoreau.beontime.client.graphics.parts.ButtonBar;
import fr.umlv.smoreau.beontime.client.graphics.parts.MenuBar;
import fr.umlv.smoreau.beontime.client.graphics.parts.StateBar;
import fr.umlv.smoreau.beontime.client.graphics.parts.TimeTableViewPanelBar;
import fr.umlv.smoreau.beontime.client.graphics.parts.TitleBar;
import fr.umlv.smoreau.beontime.client.graphics.parts.edit.Edit;
import fr.umlv.smoreau.beontime.client.graphics.parts.view.View;
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
	private final static int X_POSITION = 0;
	private final static int Y_POSITION = 0;
	private final static int WIDTH = 1024;
	private final static int HEIGHT = 700;

	private TimeTableViewPanelBar timetableviewpanel;	
	
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
        buttonBar = new ButtonBar(this);
        titleBar = new TitleBar();
        stateBar = new StateBar();
        timetableviewpanel=new TimeTableViewPanelBar();
        Edit edit=new Edit();
        View view=new View();
        
        //splitPaneVertical = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,new JPanel(),new JPanel());
        JSplitPane splitPaneHorizontal = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        JSplitPane splitPaneHorizontal2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		JSplitPane splitPaneVertical = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		JSplitPane splitPaneVertical2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		
        splitPaneVertical2.setContinuousLayout(true);
        splitPaneVertical.setContinuousLayout(true);
        splitPaneVertical.setOneTouchExpandable(true);
        splitPaneVertical2.setOneTouchExpandable(true);
        splitPaneHorizontal.setContinuousLayout(true);
        splitPaneHorizontal.setOneTouchExpandable(true);
        mainFrame = new JFrame();
        Container container=mainFrame.getContentPane();
        Border beveledBorder = BorderFactory.createBevelBorder(BevelBorder.RAISED, new Color(153, 204, 255), new Color(204, 204, 255));
		titleBar.setBorder(beveledBorder);
		
        splitPaneHorizontal.setLeftComponent(edit);
        splitPaneHorizontal.setRightComponent(view);
        splitPaneHorizontal.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        splitPaneHorizontal.setDividerLocation((int)(WIDTH*0.15));
        splitPaneHorizontal.setDividerSize(8);
        
        splitPaneVertical.setTopComponent(titleBar.getTitleBarPanel());
        splitPaneVertical.setBottomComponent(splitPaneHorizontal);
        splitPaneVertical.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        splitPaneVertical.setDividerLocation((int)(HEIGHT*0.05));
        splitPaneVertical.setDividerSize(8);
		
        splitPaneVertical2.setTopComponent(splitPaneVertical);
        splitPaneVertical2.setBottomComponent(stateBar.getStateBarPanel());
        splitPaneVertical2.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        splitPaneVertical2.setDividerLocation((int)(HEIGHT*0.7));
        splitPaneVertical2.setDividerSize(8);
        
		container.setLayout(new BorderLayout()); 
		splitPaneHorizontal2.setLeftComponent(buttonBar.getToolBar());
		splitPaneHorizontal2.setRightComponent(timetableviewpanel);
		splitPaneHorizontal2.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		splitPaneHorizontal2.setDividerLocation((int)(WIDTH*0.68));
		splitPaneHorizontal2.setDividerSize(0);
		
		container.add(splitPaneHorizontal2, BorderLayout.NORTH);
		container.add(splitPaneVertical2); 
		mainFrame.setTitle("BeOnTime");
		mainFrame.setJMenuBar(menuBar);
		mainFrame.setSize(WIDTH,HEIGHT);
		mainFrame.setResizable(true);
		try {
			UIManager.setLookAndFeel(new MetalLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		
		/*
        JPanel panelHaut = new JPanel();
        panelHaut.setLayout(new BoxLayout(panelHaut, BoxLayout.Y_AXIS));
        panelHaut.add(menuBar.getPanel());
        panelHaut.add(buttonBar.getButtonBarPanel());
        panelHaut.add(titleBar.getTitleBarPanel());
        
        mainFrame = new JFrame();
        mainFrame.getContentPane().add(panelHaut,BorderLayout.NORTH);
        mainFrame.getContentPane().add(splitPaneVertical,BorderLayout.CENTER);
        mainFrame.getContentPane().add(stateBar,BorderLayout.CENTER);
        */
    }
    

    
	public void open() {
		
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
	public ButtonBar getToolBar() {
		return buttonBar;
	}
	public static void main(String[] args){
		MainFrame mf=new MainFrame();
		mf.open();
	}

	public JFrame getMainFrame() {
		return mainFrame;
	}


	/**
	 * 
	 */
	public void refresh() {
		// TODO Auto-generated method stub
		
	}
}
