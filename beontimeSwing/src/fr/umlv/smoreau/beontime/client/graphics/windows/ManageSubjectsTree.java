package fr.umlv.smoreau.beontime.client.graphics.windows;
/* DESS CRI - BeOnTime - timetable project */

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.ToolTipManager;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreeSelectionModel;

import fr.umlv.smoreau.beontime.client.graphics.BoTModel;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.client.graphics.parts.edit.EditRenderer;

/**
 * Manages the graphical JTree of subject
 * @author BeOnTime
 */
public class ManageSubjectsTree extends JTree {
	
	private final JTree tree;
	
	private JPanel panel;
	private static MainFrame mainFrame;

	
	public ManageSubjectsTree(final BoTModel model) {
		super();
		super.setModel(new ManageSubjectsAdapter(model, this));
		ManageSubjectsTree.mainFrame = MainFrame.getInstance();
		
		panel = new JPanel(new GridLayout(1, 0));
		this.setCellRenderer(new EditRenderer());
		this.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		
		ToolTipManager.sharedInstance().registerComponent(this);
		
		tree = this;
		
		super.addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent e) {
                mainFrame.getEdit().addSelectionPath(e.getPath());
            }
		});
		
		JScrollPane scrollPane = new JScrollPane(this);
		
		super.setMinimumSize(new Dimension(100, 50));
		
		panel.add(scrollPane);
	}
	
	public JPanel getPanel() {
		return panel;
	}
}