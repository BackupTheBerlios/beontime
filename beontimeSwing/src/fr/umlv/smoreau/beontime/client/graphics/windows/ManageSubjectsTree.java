package fr.umlv.smoreau.beontime.client.graphics.windows;
/* DESS CRI - BeOnTime - timetable project */

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.ToolTipManager;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeSelectionModel;

import fr.umlv.smoreau.beontime.client.actions.timetable.subject.AddSubject;
import fr.umlv.smoreau.beontime.client.actions.timetable.subject.ModifySubject;
import fr.umlv.smoreau.beontime.client.actions.timetable.subject.RemoveSubject;
import fr.umlv.smoreau.beontime.client.graphics.BoTModel;
import fr.umlv.smoreau.beontime.client.graphics.ColorBoT;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.model.timetable.Subject;
import fr.umlv.smoreau.beontime.model.timetable.Timetable;

/**
 * Manages the graphical JTree of subject
 * @author sandrine (BeOnTime team)
 */
public class ManageSubjectsTree extends JTree {
	/** This class has to be serializable */
	private static final long serialVersionUID = 1L;
	
	private final JTree tree;
	private Subject subjectSelected;
	
	private JPanel panel;
	private static MainFrame mainFrame;
	
	
	
	public ManageSubjectsTree(final BoTModel model, final JButton modifyButton, final JButton removeButton) {
		super();
		System.out.println("Subject tree");
		super.setModel(new ManageSubjectsAdapter(model, this));
		ManageSubjectsTree.mainFrame = MainFrame.getInstance();
		
		panel = new JPanel(new GridLayout(1, 0));
		this.setCellRenderer(new TreeRenderer());
		this.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		
		ToolTipManager.sharedInstance().registerComponent(this);
		
		tree = this;
		
		// Popup Menus
		final PopupMenu popupSubject = new PopupMenu(new Subject());
		final PopupMenu popupNothing = new PopupMenu(null);
		
		super.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				
				modifyButton.setEnabled(true);
				removeButton.setEnabled(true);
				
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
	
	public Subject getSubjectSelected() {
		return subjectSelected;
	}
	
	
	private class TreeRenderer extends DefaultTreeCellRenderer {
		/** This class has to be serializable */
		private static final long serialVersionUID = 1L;

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
			} 
			
			setFont(font);
			setToolTipText(tooltip);
			setBackgroundSelectionColor(Color.WHITE);
			
			setIcon(null);
			
			return this;
		}
	}
	
	private static class PopupMenu extends JPopupMenu {
		/** This calss has to be serializable */
		private static final long serialVersionUID = 1L;
		
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
}
