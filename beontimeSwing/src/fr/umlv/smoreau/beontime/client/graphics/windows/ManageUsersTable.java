/*
 * Created on 1 mars 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package fr.umlv.smoreau.beontime.client.graphics.windows;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import fr.umlv.smoreau.beontime.client.graphics.BoTModel;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.model.user.User;

/**
 * @author sandrine
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ManageUsersTable extends JTable {
	
	private JPanel panel;
    private final JTable table;
    private static MainFrame mainFrame;
    private User userSelected;
    
    public ManageUsersTable(final BoTModel model) {
        super();
        super.setModel(new ManageUsersAdapter(model));
        ManageUsersTable.mainFrame = MainFrame.getInstance();
        
        panel = new JPanel(new GridLayout(1, 0));
        /*this.setCellRenderer(new TreeRenderer());
        this.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        
        ToolTipManager.sharedInstance().registerComponent(this);*/
        
        table = this;
        
        /*// Popup Menus
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
		});*/
        
        JScrollPane scrollPane = new JScrollPane(this);

		super.setMinimumSize(new Dimension(100, 50));

		panel.add(scrollPane);
    }
    
    public JPanel getPanel() {
        return panel;
    }
    
    /*public Subject getSubjectSelected() {
        return subjectSelected;
    }*/
    
    
}
