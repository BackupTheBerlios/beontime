package fr.umlv.smoreau.beontime.client.graphics;
/* DESS CRI - BeOnTime - timetable project */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.net.URL;
import java.util.Collection;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.plaf.metal.MetalLookAndFeel;

import fr.umlv.smoreau.beontime.client.actions.ActionsList;
import fr.umlv.smoreau.beontime.client.graphics.parts.ButtonBar;
import fr.umlv.smoreau.beontime.client.graphics.parts.MenuBar;
import fr.umlv.smoreau.beontime.client.graphics.parts.StateBar;
import fr.umlv.smoreau.beontime.client.graphics.parts.TimeTableViewPanelBar;
import fr.umlv.smoreau.beontime.client.graphics.parts.TitleBar;
import fr.umlv.smoreau.beontime.client.graphics.parts.edit.Edit;
import fr.umlv.smoreau.beontime.client.graphics.parts.view.MultiSpanCellTable;
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
 * Manages the Main UI Frame
 * @author BeOnTime team
 */
public class MainFrame {
	private static final String TITRE = "BeOnTime";
	private static final MainFrame INSTANCE = new MainFrame();
	
	private BoTModel model;
	private User userConnected;
	private User userSelected;
	private Room roomSelected;
	private Material materialSelected;

	private MenuBar menuBar;
	private ButtonBar buttonBar;
	private TitleBar titleBar;
	private StateBar stateBar;	
	private Edit edit;
	private View view;
    private JSplitPane splitPaneVertical;
    private JFrame mainFrame;
//	private static MainFrame instance;	//TODO virer ?
	private final static int X_POSITION = 0;
	private final static int Y_POSITION = 0;
	private final static int WIDTH = 1024;
	private final static int HEIGHT = 700;

	private TimeTableViewPanelBar timetableviewpanel;
	private JSplitPane splitPaneHorizontal2;
	
	/* type d'affichage semaine / semestre */
	private int view_type = 0;
	public static final int VIEW_WEEK     = 0;
	public static final int VIEW_HALF_YEAR = 1;
	
	
    /** Creates a new instance of FenetreConjugaison */
    private MainFrame() {
        initMainFrame();
        ActionsList.initActions(this);
    }
    
    
    public static MainFrame getInstance() {
		return INSTANCE;
	}

    /**
     * Initialise la fenetre principale de l'application.
     */
    public void initMainFrame() {
        model = new BoTModel();
        titleBar = new TitleBar(model);
        stateBar = new StateBar();
        timetableviewpanel = new TimeTableViewPanelBar(this);
        edit = new Edit(model, this);
        view = new View(this, model);
        
        mainFrame = new JFrame();
		mainFrame.setSize(WIDTH,HEIGHT);
        
        //splitPaneVertical = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,new JPanel(),new JPanel());
        JSplitPane splitPaneHorizontal = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPaneHorizontal2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
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
        splitPaneHorizontal.setRightComponent(view.getJScrollPane());

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
		//splitPaneHorizontal2.setLeftComponent(buttonBar.getToolBar());
		splitPaneHorizontal2.setRightComponent(timetableviewpanel);
		splitPaneHorizontal2.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		//splitPaneHorizontal2.setDividerLocation((int)(WIDTH*0.68));
		splitPaneHorizontal2.setDividerSize(0);
		
		container.add(splitPaneHorizontal2, BorderLayout.NORTH);
		container.add(splitPaneVertical2); 
		mainFrame.setTitle("BeOnTime");
		mainFrame.setResizable(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLocationRelativeTo(null);
		URL url = MainFrame.class.getResource("miniLogoBoT.png");
		if (url != null) {
			ImageIcon image = new ImageIcon(url);
			mainFrame.setIconImage(image.getImage());
		}
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

    public View getView(){
		return view;
    }

    public fr.umlv.smoreau.beontime.client.graphics.parts.view.AttributiveCellTableModel getTableModel(){
		return view.getTableModel();
    }

    public MultiSpanCellTable getTable(){
		return view.getTable();
    	
    }

	public void open() {
        menuBar = new MenuBar(this, model, userConnected.getUserType());
		mainFrame.setJMenuBar(menuBar);
        buttonBar = new ButtonBar(this);
        splitPaneHorizontal2.setLeftComponent(buttonBar.getToolBar());
        splitPaneHorizontal2.setDividerLocation((int)(WIDTH*0.68));
		mainFrame.setVisible(true);
	}
	
	public void close() {
		mainFrame.setVisible(false);
	}

	public Course getCourseSelected() {
		return view.getCourseSelected();
	}
	
	public Subject getSubjectSelected() {
		return edit.getSubjectSelected();
	}
	
	public String getCourseTypeSelected() {
		return edit.getCourseTypeSelected();
	}
	
	public Unavailability getUnavailabilitySelected() {
		//TODO à implémenter
		return null;
	}
	
	public Group getGroupSelected() {
	    Timetable timetable = model.getTimetable();
	    if (timetable != null) {
	        return timetable.getGroup();
	    }
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
	
	public User getUserConnected() {
		return userConnected;
	}
	
	public void setUserConnected(User user) {
	    this.userConnected = user;
	}
	
	public User getUserSelected() {
		return userSelected;
	}
	
	public void setUserSelected(User user) {
		ActionsList.getAction("ModifyUser").setEnabled(user != null);
		ActionsList.getAction("RemoveUser").setEnabled(user != null);

	    this.userSelected = user;
	}
	
	public Material getMaterialSelected() {
		return materialSelected;
	}
	
	public void setMaterialSelected(Material material) {
		ActionsList.getAction("ModifyMaterial").setEnabled(material != null);
		ActionsList.getAction("RemoveMaterial").setEnabled(material != null);

	    this.materialSelected = material;
	}
		
	public Room getRoomSelected() {
		return roomSelected;
	}
	
	public void setRoomSelected(Room room) {
		ActionsList.getAction("ModifyRoom").setEnabled(room != null);
		ActionsList.getAction("RemoveRoom").setEnabled(room != null);

	    this.roomSelected = room;
	}

	public ButtonBar getToolBar() {
		return buttonBar;
	}
	
	public MenuBar getMenuBar() {
	    return menuBar;
	}

	public JFrame getMainFrame() {
		return mainFrame;
	}
	
	public BoTModel getModel() {
	    return model;
	}

	public void refresh() {
	    mainFrame.setVisible(true);
	}
	
	/**
	 * @return Returns the view_type.
	 */
	public int getViewType() {
		return view_type;
	}
	/**
	 * @param view_type The view_type to set.
	 */
	public void setViewType(int view_type) {
		this.view_type = view_type;
	}
}
