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
import javax.swing.ListSelectionModel;

import fr.umlv.smoreau.beontime.client.graphics.BoTModel;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.model.user.User;

/**
 * @author BeOnTime
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
       
        
        table = this;
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        
        
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