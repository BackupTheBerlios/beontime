package fr.umlv.smoreau.beontime.client.graphics.windows;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Collection;

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
 */
public class ManageRoomsTable extends JTable {
	private JPanel panel;
    private final JTable table;
    private static MainFrame mainFrame;
    
    public ManageRoomsTable(final BoTModel model, Collection rooms) {
        super();
        super.setModel(new ManageRoomsAdapter(model, rooms));
        ManageRoomsTable.mainFrame = MainFrame.getInstance();
        
        panel = new JPanel(new GridLayout(1, 0));

        table = this;
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				mainFrame.setRoomSelected((Room)((ManageRoomsAdapter)table.getModel()).getObjectAt(table.getSelectedRow()));
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
