package fr.umlv.smoreau.beontime.client.graphics.windows;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JPanel;
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
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.model.Group;
import fr.umlv.smoreau.beontime.model.timetable.Timetable;

/**
 * @author BeOnTime
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
		
		super.addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent e) {
                TreePath treePath = e.getPath();
                Object selected = treePath.getLastPathComponent();
                if (selected instanceof Timetable) {
                    mainFrame.setGroupSelected(null);
                    ActionsList.getAction("ModifyGroup").setEnabled(false);
            		ActionsList.getAction("RemoveGroup").setEnabled(false);
            		ActionsList.getAction("ManageIdentitiesToGroups").setEnabled(false);
                } else if (selected instanceof Group) {
                    mainFrame.setGroupSelected((Group) selected);
                }
            }
		});
		
		JScrollPane scrollPane = new JScrollPane(this);
		
		super.setMinimumSize(new Dimension(100, 50));
		
		panel.add(scrollPane);
	}
	
	public JPanel getPanel() {
		return panel;
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
}
