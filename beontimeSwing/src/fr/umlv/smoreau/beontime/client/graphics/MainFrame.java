package fr.umlv.smoreau.beontime.client.graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.Collection;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
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
import fr.umlv.smoreau.beontime.model.timetable.Timetable;
import fr.umlv.smoreau.beontime.model.user.User;

/**
 * @author BeOnTime
 */
public class MainFrame {
	
	private static final String TITRE = "BeOnTime";
	
	private BoTModel model;

	private MenuBar menuBar;
	private ButtonBar buttonBar;
	private TitleBar titleBar;
	private StateBar stateBar;	
	private Edit edit;
	private View view;
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
        model = new BoTModel();
        menuBar = new MenuBar(this);
        buttonBar = new ButtonBar(this);
        titleBar = new TitleBar();
        stateBar = new StateBar();
        timetableviewpanel = new TimeTableViewPanelBar();
        edit = new Edit(model, this);
        view = new View();
        
        mainFrame = new JFrame();
		mainFrame.setSize(WIDTH,HEIGHT);
        
        //splitPaneVertical = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,new JPanel(),new JPanel());
        JSplitPane splitPaneHorizontal = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        JSplitPane splitPaneHorizontal2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		JSplitPane splitPaneVertical = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		final JSplitPane splitPaneVertical2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		
        splitPaneVertical2.setContinuousLayout(true);
        splitPaneVertical.setContinuousLayout(true);
        splitPaneVertical.setOneTouchExpandable(true);
        splitPaneVertical2.setOneTouchExpandable(true);
        splitPaneHorizontal.setContinuousLayout(true);
        splitPaneHorizontal.setOneTouchExpandable(true);

        Container container=mainFrame.getContentPane();
        Border beveledBorder = BorderFactory.createBevelBorder(BevelBorder.RAISED, new Color(153, 204, 255), new Color(204, 204, 255));
		titleBar.setBorder(beveledBorder);
		
        splitPaneHorizontal.setLeftComponent(edit.getPane());
        splitPaneHorizontal.setRightComponent(view);
        splitPaneHorizontal.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        splitPaneHorizontal.setDividerLocation((int)(WIDTH*0.15));
        splitPaneHorizontal.setDividerSize(6);
        splitPaneHorizontal.setOneTouchExpandable(false);
        
        splitPaneVertical.setTopComponent(titleBar.getTitleBarPanel());
        splitPaneVertical.setBottomComponent(splitPaneHorizontal);
        splitPaneVertical.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        splitPaneVertical.setDividerLocation((int)(HEIGHT*0.05));
        splitPaneVertical.setDividerSize(4);
        splitPaneVertical.setOneTouchExpandable(false);
        splitPaneVertical.setEnabled(false);
		
        splitPaneVertical2.setTopComponent(splitPaneVertical);
        splitPaneVertical2.setBottomComponent(stateBar.getStateBarPanel());
        splitPaneVertical2.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        splitPaneVertical2.setDividerLocation((int)mainFrame.getHeight()-160);
        splitPaneVertical2.setDividerSize(1);
        splitPaneVertical2.setEnabled(false);
        
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
		mainFrame.setResizable(true);
		try {
			UIManager.setLookAndFeel(new MetalLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			System.err.println("Look And Feel non supporté ...");
		}
		
		mainFrame.addComponentListener(new ComponentListener() {
			public void componentResized(ComponentEvent e) {
			    splitPaneVertical2.setDividerLocation((int)mainFrame.getHeight()-160);
			    refresh();
			}
			public void componentHidden(ComponentEvent e) {
			}
			public void componentMoved(ComponentEvent e) {
			}
			public void componentShown(ComponentEvent e) {
			}
		});

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
		return edit.getSubjectSelected();
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
	    Timetable timetable = model.getTimetable();
	    if (timetable != null) {
	        return timetable.getFormation();
	    }
		return null;
	}
	
	public User getUserSelected() {
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


	public void refresh() {
	    mainFrame.setVisible(true);
	}
}
