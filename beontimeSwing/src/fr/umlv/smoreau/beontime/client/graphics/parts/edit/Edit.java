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
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeSelectionModel;

import fr.umlv.smoreau.beontime.client.actions.timetable.course.AddCourse;
import fr.umlv.smoreau.beontime.client.actions.timetable.subject.AddSubject;
import fr.umlv.smoreau.beontime.client.actions.timetable.subject.ModifySubject;
import fr.umlv.smoreau.beontime.client.actions.timetable.subject.RemoveSubject;
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
    private static MainFrame mainFrame;

    public Edit(final BoTModel model, MainFrame mainFrame) {
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
				if (e.getButton() == MouseEvent.BUTTON3) {
					int row = tree.getRowForLocation(e.getX(), e.getY());
					tree.setSelectionRow(row);
					subjectSelected = null;
					if (row != -1) {
						Object object = tree.getPathForRow(row).getLastPathComponent();
						if (object instanceof Subject) {
						    popupSubject.show(e.getComponent(), e.getX(), e.getY());
						    subjectSelected = (Subject) object;
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
	            JMenuItem menuItem = new JMenuItem(new AddSubject(false, mainFrame));
				add(menuItem);
	        } else if (object instanceof Subject) {
	            JMenuItem menuItem = new JMenuItem(new AddCourse(false, mainFrame));
				add(menuItem);
				menuItem = new JMenuItem(new ModifySubject(false, mainFrame));
				add(menuItem);
				menuItem = new JMenuItem(new RemoveSubject(false, mainFrame));
				add(menuItem);
	        } else if (object instanceof String) {
	            JMenuItem menuItem = new JMenuItem(new AddCourse(false, mainFrame));
				add(menuItem);
	        }
	    }
	}
}
