/*
 * Created on 1 mars 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package fr.umlv.smoreau.beontime.client.graphics.windows;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import fr.umlv.smoreau.beontime.client.graphics.BoTModel;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.model.element.Room;

/**
 * @author BeOnTime
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ManageRoomsTable extends JTable {
	private JPanel panel;
    private final JTable table;
    private static MainFrame mainFrame;
    private Room roomSelected;
    
    public ManageRoomsTable(final BoTModel model, final JButton modifyButton, final JButton deleteButton) {
        super();
        super.setModel(new ManageRoomsAdapter(model));
        ManageRoomsTable.mainFrame = MainFrame.getInstance();
        roomSelected = null;
        
        panel = new JPanel(new GridLayout(1, 0));
        
        
        table = this;
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent e) {
				modifyButton.setEnabled(true);
				deleteButton.setEnabled(true);
				
				roomSelected = (Room)((ManageRoomsAdapter)table.getModel()).getObjectAt(table.getSelectedRow());
			}
			
		});
        
        
        JScrollPane scrollPane = new JScrollPane(this);

		super.setMinimumSize(new Dimension(100, 50));

		panel.add(scrollPane);
    }
    
    public JPanel getPanel() {
        return panel;
    }
    
    public Room getRoomSelected() {
        return roomSelected;
    }
}
