package fr.umlv.smoreau.beontime.client.graphics.parts.edit;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.ToolTipManager;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import fr.umlv.smoreau.beontime.client.actions.ActionsList;
import fr.umlv.smoreau.beontime.client.graphics.BoTModel;
import fr.umlv.smoreau.beontime.client.graphics.ColorBoT;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.model.timetable.Subject;
import fr.umlv.smoreau.beontime.model.timetable.Timetable;


/**
 * @author BeOnTime
 */
public class Edit extends JTree {
    private JPanel pane;
    private final JTree tree;
    private Subject subjectSelected;
    private String courseTypeSelected;
    private static MainFrame mainFrame;

    public Edit(final BoTModel model, final MainFrame mainFrame) {
        super();
        super.setModel(new EditAdapter(model, this));
        Edit.mainFrame = mainFrame;
        
        pane = new JPanel(new GridLayout(1, 0));
        this.setCellRenderer(new TreeRenderer());
        this.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        
        ToolTipManager.sharedInstance().registerComponent(this);
        
        tree = this;
        
        // Popup Menus
		final PopupMenu popupSubject = new PopupMenu(new Subject());
		final PopupMenu popupTypes = new PopupMenu(new String());
		final PopupMenu popupNothing = new PopupMenu(null);

		super.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				// Popup Menus
				if (e.getButton() == MouseEvent.BUTTON3 && model.getTimetable() != null) {
					int row = tree.getRowForLocation(e.getX(), e.getY());
					tree.setSelectionRow(row);
					if (row != -1) {
						Object object = tree.getPathForRow(row).getLastPathComponent();
						if (object instanceof Subject) {
						    popupSubject.show(e.getComponent(), e.getX(), e.getY());
						} else if (object instanceof String) {
						    popupTypes.show(e.getComponent(), e.getX(), e.getY());
						}
					} else {
					    popupNothing.show(e.getComponent(), e.getX(), e.getY());
					}
				}
			}
			public void mousePressed(MouseEvent e) {
			}
			public void mouseReleased(MouseEvent e) {
			}
			public void mouseEntered(MouseEvent e) {
			}
			public void mouseExited(MouseEvent e) {
			}
		});
        
		super.addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent e) {
                TreePath treePath = e.getPath();
                Object selected = treePath.getLastPathComponent();
                if (selected instanceof Timetable) {
                    ActionsList.getAction("ModifySubject").setEnabled(false);
                    ActionsList.getAction("RemoveSubject").setEnabled(false);
                    ActionsList.getAction("AddCourse").setEnabled(false);
                    subjectSelected = null;
                    courseTypeSelected = null;
                } else if (selected instanceof Subject) {
                    ActionsList.getAction("ModifySubject").setEnabled(true);
                    ActionsList.getAction("RemoveSubject").setEnabled(true);
                    ActionsList.getAction("AddCourse").setEnabled(true);
                    subjectSelected = (Subject) selected;
                    courseTypeSelected = null;
                } else if (selected instanceof String) {
                    ActionsList.getAction("ModifySubject").setEnabled(true);
                    ActionsList.getAction("RemoveSubject").setEnabled(true);
                    ActionsList.getAction("AddCourse").setEnabled(true);
                    subjectSelected = (Subject) treePath.getPathComponent(treePath.getPathCount()-2);
                    courseTypeSelected = (String) selected;
                }
                
                mainFrame.getTable().clearSelection();
                ActionsList.getAction("ModifyCourse").setEnabled(false);
                ActionsList.getAction("RemoveCourse").setEnabled(false);
				ActionsList.getAction("CutCourse").setEnabled(false);
				ActionsList.getAction("CopyCourse").setEnabled(false);
            }
		});

        JScrollPane scrollPane = new JScrollPane(this);

		super.setMinimumSize(new Dimension(100, 50));

		pane.add(scrollPane);
    }
    
    public JPanel getPane() {
        return pane;
    }
    
    public Subject getSubjectSelected() {
        return subjectSelected;
    }
    
    public String getCourseTypeSelected() {
        return courseTypeSelected;
    }
    
    
    private class TreeRenderer extends DefaultTreeCellRenderer {
        /* (non-Javadoc)
         * @see javax.swing.tree.TreeCellRenderer#getTreeCellRendererComponent(javax.swing.JTree, java.lang.Object, boolean, boolean, boolean, int, boolean)
         */
        public Component getTreeCellRendererComponent(
                JTree tree,
    			Object value,
    			boolean selected,
    			boolean expanded,
    			boolean leaf,
    			int row,
    			boolean hasFocus) {
            
            super.getTreeCellRendererComponent(tree, value, selected,
    				expanded, leaf, row, hasFocus);

            Font font = null;
            String tooltip = null;
            if (value instanceof Timetable) {
                font = new Font("Arial", Font.BOLD, 15);
                setText(((Timetable) value).getFormation().getHeading());
            } else if (value instanceof Subject) {
                font = new Font("Arial", Font.PLAIN, 14);
                Subject subject = (Subject) value;
                setText(subject.getHeading());
                setForeground(ColorBoT.getColorAt(subject.getIdSubject().intValue()));
            } else if (value instanceof String) {
                font = new Font("Arial", Font.PLAIN, 13);
                setText((String) value);
            }
            setFont(font);
            setToolTipText(tooltip);
            setBackgroundSelectionColor(Color.WHITE);
            
            setIcon(null);
            
            return this;
        }
    }
    
	private static class PopupMenu extends JPopupMenu {
	    private Object selected;
	    
	    public PopupMenu(Object object) {
	        super();

	        if (object == null) {
	            JMenuItem menuItem = new JMenuItem(ActionsList.getAction("AddSubject"));
				add(menuItem);
	        } else if (object instanceof Subject) {
	            JMenuItem menuItem = new JMenuItem(ActionsList.getAction("AddCourse"));
				add(menuItem);
				menuItem = new JMenuItem(ActionsList.getAction("ModifySubject"));
				add(menuItem);
				menuItem = new JMenuItem(ActionsList.getAction("RemoveSubject"));
				add(menuItem);
	        } else if (object instanceof String) {
	            JMenuItem menuItem = new JMenuItem(ActionsList.getAction("AddCourse"));
				add(menuItem);
	        }
	    }
	}
}
