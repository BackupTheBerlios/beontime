package fr.umlv.smoreau.beontime.client.graphics;
/* DESS CRI - BeOnTime - timetable project */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.net.URL;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

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
	private Group groupSelected;
	private Course courseSelected;
	private Unavailability unavailabilitySelected;

	private MenuBar menuBar;
	private ButtonBar buttonBar;
	private TitleBar titleBar;
	private StateBar stateBar;	
	private Edit edit;
	private View view;
    private JSplitPane splitPaneVertical;
    private JFrame mainFrame;

    private JSplitPane splitPaneHorizontal = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
    
	private final static int X_POSITION = 0;
	private final static int Y_POSITION = 0;
	private final static int WIDTH = 1024;
	private final static int HEIGHT = 700;

	private TimeTableViewPanelBar timetableviewpanel;
	private Container container;
	
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
        titleBar = new TitleBar(model, this);
        stateBar = new StateBar();
        edit = new Edit(model, this);
        view = new View(this, model);
        
        mainFrame = new JFrame();
		mainFrame.setSize(WIDTH,HEIGHT);

        //JSplitPane splitPaneHorizontal = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		JSplitPane splitPaneVertical = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		final JSplitPane splitPaneVertical2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		
        splitPaneVertical2.setContinuousLayout(true);
        splitPaneVertical.setContinuousLayout(true);
        splitPaneVertical.setOneTouchExpandable(true);
        splitPaneVertical2.setOneTouchExpandable(true);
        splitPaneHorizontal.setContinuousLayout(true);
        splitPaneHorizontal.setOneTouchExpandable(true);

        container=mainFrame.getContentPane();
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
        splitPaneVertical2.setDividerLocation((int)mainFrame.getHeight()-125);
        splitPaneVertical2.setDividerSize(1);
        splitPaneVertical2.setEnabled(false);
        
		container.setLayout(new BorderLayout()); 

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
			    splitPaneVertical2.setDividerLocation((int)mainFrame.getHeight()-125);
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

    public void setView(View view) {
    	splitPaneHorizontal.setRightComponent(view.getJScrollPane());
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
        container.add(buttonBar.getToolBar(), BorderLayout.NORTH);
		mainFrame.setVisible(true);
	}
	
	public void close() {
		ActionsList.getAction("CloseTimetable").actionPerformed(null);
		mainFrame.setVisible(false);
	}

	public Course getCourseSelected() {
		return courseSelected;
	}
	
	public void setCourseSelected(Course course) {
	    boolean enable = (course != null);

	    ActionsList.getAction("ModifyCourse").setEnabled(enable);
		ActionsList.getAction("RemoveCourse").setEnabled(enable);
		ActionsList.getAction("CutCourse").setEnabled(enable);
		ActionsList.getAction("CopyCourse").setEnabled(enable);

	    this.courseSelected = course;
	}
	
	public Subject getSubjectSelected() {
		return edit.getSubjectSelected();
	}
	
	public String getCourseTypeSelected() {
		return edit.getCourseTypeSelected();
	}
	
	public Unavailability getUnavailabilitySelected() {
		return unavailabilitySelected;
	}
	
	public void setUnavailabilitySelected(Unavailability unavailability) {
		ActionsList.getAction("ModifyUnavailability").setEnabled(unavailability != null);
		ActionsList.getAction("RemoveUnavailability").setEnabled(unavailability != null);

	    this.unavailabilitySelected = unavailability;
	}
	
	public Group getGroupSelected() {
		return groupSelected;
	}
	
	public void setGroupSelected(Group group) {
	    boolean enable;
	    if (group == null && (model.getTimetable() == null || model.getTimetable().getGroup() == null))
	        enable = false;
	    else
	        enable = true;
		ActionsList.getAction("ModifyGroup").setEnabled(enable);
		ActionsList.getAction("RemoveGroup").setEnabled(enable);
		ActionsList.getAction("ManageIdentitiesToGroups").setEnabled(enable);

	    this.groupSelected = group;
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
	    boolean enable;
	    if (material == null && (model.getTimetable() == null || model.getTimetable().getMaterial() == null))
	        enable = false;
	    else
	        enable = true;
		ActionsList.getAction("ModifyMaterial").setEnabled(enable);
		ActionsList.getAction("RemoveMaterial").setEnabled(enable);

	    this.materialSelected = material;
	}
		
	public Room getRoomSelected() {
		return roomSelected;
	}
	
	public void setRoomSelected(Room room) {
	    boolean enable;
	    if (room == null && (model.getTimetable() == null || model.getTimetable().getRoom() == null))
	        enable = false;
	    else
	        enable = true;
		ActionsList.getAction("ModifyRoom").setEnabled(enable);
		ActionsList.getAction("RemoveRoom").setEnabled(enable);

	    this.roomSelected = room;
	}

	public ButtonBar getToolBar() {
		return buttonBar;
	}
	
	public MenuBar getMenuBar() {
	    return menuBar;
	}
	
	public TitleBar getTitleBar() {
		return titleBar;
	}
	
	public StateBar getStateBar() {
		return stateBar;
	}

	public JFrame getMainFrame() {
		return mainFrame;
	}
	
	public BoTModel getModel() {
	    return model;
	}
	
	public Edit getEdit() {
	    return edit;
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
	
	public Date getDateSelected() {
	    return titleBar.getPeriod();
	}
	
	public Calendar getBeginPeriod() {
	    Calendar begin = Calendar.getInstance();
		begin.setTime(getDateSelected());
		begin.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		begin.set(Calendar.HOUR_OF_DAY, 0);
		begin.set(Calendar.MINUTE, 0);
		begin.set(Calendar.SECOND, 0);
		return begin;
	}
	
	public Calendar getEndPeriod() {
	    Calendar end = Calendar.getInstance();
		end.setTime(getDateSelected());
		end.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		end.set(Calendar.HOUR_OF_DAY, 23);
		end.set(Calendar.MINUTE, 59);
		end.set(Calendar.SECOND, 59);
		return end;
	}
}
