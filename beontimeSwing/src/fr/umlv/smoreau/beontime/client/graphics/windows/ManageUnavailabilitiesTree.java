/*
 * Created on 3 mars 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package fr.umlv.smoreau.beontime.client.graphics.windows;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.ToolTipManager;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeSelectionModel;


import fr.umlv.smoreau.beontime.client.graphics.BoTModel;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.model.Unavailability;
import fr.umlv.smoreau.beontime.model.element.Material;
import fr.umlv.smoreau.beontime.model.element.Room;
import fr.umlv.smoreau.beontime.model.timetable.Course;
import fr.umlv.smoreau.beontime.model.user.User;

/**
 * @author smalouin
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ManageUnavailabilitiesTree extends JTree {
	
	private final JTree tree;
	private Unavailability unavailabilitySelected;
	
	private JPanel panel;
	private static MainFrame mainFrame;
	
	
	
	public ManageUnavailabilitiesTree(final BoTModel model, final JButton modifyButton, final JButton removeButton, final JButton searchUnavailabilitiesButton) {
		super();
		super.setModel(new ManageUnavailabilitiesAdapter(model, this));
		ManageUnavailabilitiesTree.mainFrame = MainFrame.getInstance();
		
		panel = new JPanel(new GridLayout(1, 0));
		this.setCellRenderer(new TreeRenderer());
		this.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		
		ToolTipManager.sharedInstance().registerComponent(this);
		
		tree = this;
		
		// Popup Menus
		//final PopupMenu popupSubject = new PopupMenu(new Unavailability());
		//final PopupMenu popupNothing = new PopupMenu(null);
		
		super.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				
				modifyButton.setEnabled(true);
				removeButton.setEnabled(true);
				searchUnavailabilitiesButton.setVisible(true);
				
				// Popup Menus
				if (e.getButton() == MouseEvent.BUTTON3) {
					int row = tree.getRowForLocation(e.getX(), e.getY());
					tree.setSelectionRow(row);
					unavailabilitySelected = null;
					if (row != -1) {
						Object object = tree.getPathForRow(row).getLastPathComponent();
						/*if (object instanceof Unavailability) {
							popupSubject.show(e.getComponent(), e.getX(), e.getY());
							unavailabilitySelected = (Unavailability) object;
						} */
					} else {
						//popupNothing.show(e.getComponent(), e.getX(), e.getY());
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
		
		panel.add(scrollPane);
	}
	
	public JPanel getPanel() {
		return panel;
	}
	
	public Unavailability getUnavailabilitySelected() {
		return unavailabilitySelected;
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
			if (value instanceof String) {
				font = new Font("Arial", Font.BOLD, 15);
				setText(((String) value).toString());
			} else if (value instanceof User) {
				font = new Font("Arial", Font.PLAIN, 14);
				User user = (User) value;
				setText(user.getName()+" "+user.getFirstName());
			} else if (value instanceof Material) {
				font = new Font("Arial", Font.PLAIN, 14);
				Material material = (Material) value;
				setText(material.getName());
			} else if (value instanceof Room) {
				font = new Font("Arial", Font.PLAIN, 14);
				Room room = (Room) value;
				setText(room.getName());
			} else if (value instanceof Course) {
				font = new Font("Arial", Font.PLAIN, 14);
				Course course = (Course) value;
				setText(course.getIdCourseType().getNameCourseType()+" du "+course.getBeginDate()+" au "+course.getEndDate());
			} else if (value instanceof Unavailability) {
				font = new Font("Arial", Font.PLAIN, 14);
				Unavailability u = (Unavailability) value;
				setText(u.getDescription()+" du "+u.getBeginDate()+" au "+u.getEndDate());
			}
			
			
			
			setFont(font);
			setToolTipText(tooltip);
			setBackgroundSelectionColor(Color.WHITE);
			
			setIcon(null);
			
			return this;
		}
	}
	
/*	private static class PopupMenu extends JPopupMenu {
		private Object selected;
		
		public PopupMenu(Object object) {
			super();
			
			if (object == null) {
				JMenuItem menuItem = new JMenuItem(new AddSubject(false, mainFrame));
				add(menuItem);
			} else if (object instanceof Subject) {
				JMenuItem menuItem = new JMenuItem(new ModifySubject(false, mainFrame));
				add(menuItem);
				menuItem = new JMenuItem(new RemoveSubject(false, mainFrame));
				add(menuItem);
			}
		}
	}
*/
}
