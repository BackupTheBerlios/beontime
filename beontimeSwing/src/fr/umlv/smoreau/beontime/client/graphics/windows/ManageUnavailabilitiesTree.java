package fr.umlv.smoreau.beontime.client.graphics.windows;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.ToolTipManager;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;


import fr.umlv.smoreau.beontime.client.graphics.BoTModel;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.model.Formation;
import fr.umlv.smoreau.beontime.model.Group;
import fr.umlv.smoreau.beontime.model.Unavailability;
import fr.umlv.smoreau.beontime.model.element.Material;
import fr.umlv.smoreau.beontime.model.element.Room;
import fr.umlv.smoreau.beontime.model.user.User;

/**
 * @author BeOnTime
 */
public class ManageUnavailabilitiesTree extends JTree {
    private SimpleDateFormat FORMAT_DATE  = new SimpleDateFormat("dd/MM/yyyy à HH:mm");

	private final JTree tree;
	private Unavailability unavailabilitySelected;
	
	private JPanel panel;
	private static MainFrame mainFrame;


	public ManageUnavailabilitiesTree(final BoTModel model) {
		super();
		super.setModel(new ManageUnavailabilitiesAdapter(model, this));
		ManageUnavailabilitiesTree.mainFrame = MainFrame.getInstance();
		
		panel = new JPanel(new GridLayout(1, 0));
		this.setCellRenderer(new TreeRenderer());
		this.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		
		ToolTipManager.sharedInstance().registerComponent(this);
		
		tree = this;
		
		super.addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent e) {
                TreePath treePath = e.getPath();
                Object selected = treePath.getLastPathComponent();
                if (selected instanceof Unavailability)
                    mainFrame.setUnavailabilitySelected((Unavailability) selected);
                else
                    mainFrame.setUnavailabilitySelected(null);
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
			} else if (value instanceof Formation) {
				font = new Font("Arial", Font.PLAIN, 14);
				Formation formation = (Formation) value;
				setText(formation.getHeading());
			} else if (value instanceof Group) {
				font = new Font("Arial", Font.PLAIN, 14);
				Group group = (Group) value;
				setText(group.getHeading());
			} else if (value instanceof Unavailability) {
				font = new Font("Arial", Font.PLAIN, 14);
				Unavailability u = (Unavailability) value;
				setText(u.getDescription()+" du "+FORMAT_DATE.format(u.getBeginDate().getTime())+" au "+FORMAT_DATE.format(u.getEndDate().getTime()));
			}

			setFont(font);
			setToolTipText(tooltip);
			
			setIcon(null);
			
			return this;
		}
	}
}
