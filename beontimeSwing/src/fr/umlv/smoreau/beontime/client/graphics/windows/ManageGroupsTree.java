/*
 * Created on 28 f�vr. 2005
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

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.ToolTipManager;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeSelectionModel;

import fr.umlv.smoreau.beontime.client.actions.group.AddGroup;
import fr.umlv.smoreau.beontime.client.actions.group.ModifyGroup;
import fr.umlv.smoreau.beontime.client.actions.group.RemoveGroup;
import fr.umlv.smoreau.beontime.client.graphics.BoTModel;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.model.Group;
import fr.umlv.smoreau.beontime.model.timetable.Timetable;

/**
 * @author sandrine
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ManageGroupsTree extends JTree {
	
	private final JTree tree;
	private Group groupSelected;
	
	private JPanel panel;
	private static MainFrame mainFrame;
	
	
	
	public ManageGroupsTree(final BoTModel model) {
		super();
		super.setModel(new ManageGroupsAdapter(model, this));
		ManageGroupsTree.mainFrame = MainFrame.getInstance();
		
		panel = new JPanel(new GridLayout(1, 0));
		this.setCellRenderer(new TreeRenderer());
		this.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		
		ToolTipManager.sharedInstance().registerComponent(this);
		
		tree = this;
		
		// Popup Menus
		final PopupMenu popupGroup = new PopupMenu(new Group());
		final PopupMenu popupNothing = new PopupMenu(null);
		
		super.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				// Popup Menus
				if (e.getButton() == MouseEvent.BUTTON3) {
					int row = tree.getRowForLocation(e.getX(), e.getY());
					tree.setSelectionRow(row);
					groupSelected = null;
					if (row != -1) {
						Object object = tree.getPathForRow(row).getLastPathComponent();
						if (object instanceof Group) {
							popupGroup.show(e.getComponent(), e.getX(), e.getY());
							groupSelected = (Group) object;
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
		
		panel.add(scrollPane);
	}
	
	public JPanel getPanel() {
		return panel;
	}
	
	public Group getGroupSelected() {
		return groupSelected;
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
			} else if (value instanceof Group) {
				font = new Font("Arial", Font.PLAIN, 14);
				Group group = (Group) value;
				setText(group.getHeading());
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
				JMenuItem menuItem = new JMenuItem(new AddGroup(false, mainFrame));
				add(menuItem);
			} else if (object instanceof Group) {
				JMenuItem menuItem = new JMenuItem(new ModifyGroup(false, mainFrame));
				add(menuItem);
				menuItem = new JMenuItem(new RemoveGroup(false, mainFrame));
				add(menuItem);
			}
		}
	}
}
