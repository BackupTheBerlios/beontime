/*
 * Created on 1 mars 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package fr.umlv.smoreau.beontime.client.graphics.windows;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import fr.umlv.smoreau.beontime.client.graphics.BoTModel;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.model.Formation;
import fr.umlv.smoreau.beontime.model.Group;
import fr.umlv.smoreau.beontime.model.element.Room;

/**
 * @author sandrine
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ManageIdentityGroupsTable extends JTable {
	private JPanel panel;
    private final JTable table;
    private static MainFrame mainFrame;
    private Room roomSelected;
    
    public ManageIdentityGroupsTable(final BoTModel model, Formation formation, ArrayList groups) {
        super();
        super.setModel(new ManageIdentityGroupsAdapter(model, formation, groups));
        ManageIdentityGroupsTable.mainFrame = MainFrame.getInstance();
        
        panel = new JPanel(new GridLayout(1, 0));
        
        
        table = this;
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setCellSelectionEnabled(true);
        
        //table.setRowSelectionAllowed(true);
        //table.setColumnSelectionAllowed(true);
        
        
        //table.setDefaultRenderer(Object.class, new CrossRenderer());
        TableColumnModel tableColumnModel = new DefaultTableColumnModel();
        TableColumn tableColumn = new TableColumn(0);
        tableColumnModel.addColumn(tableColumn);
        for (int i = 0; i < groups.size(); ++i) {
            Group group = (Group) groups.get(i);
			tableColumn = new TableColumn(i+1);
			tableColumn.setCellRenderer(new CrossRenderer());
			tableColumn.setHeaderValue(group.getHeading());
			tableColumnModel.addColumn(tableColumn);
        }
        //tableColumnModel.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setColumnModel(tableColumnModel);
		
        
        JScrollPane scrollPane = new JScrollPane(this);

		super.setMinimumSize(new Dimension(100, 50));

		panel.add(scrollPane);
    }
    
    public JPanel getPanel() {
        return panel;
    }
  
    
    private class CrossRenderer extends DefaultTableCellRenderer {
    	public CrossRenderer() {
    		super();
    		this.setHorizontalAlignment(SwingConstants.CENTER);
    	}
    	
    	/**
    	 * Returns the component used for drawing the cell.
    	 * This method is used to configure the renderer appropriately before drawing.
    	 * @param table the <tt>JTable</tt> that is asking the renderer to draw; can be <tt>null</tt>.
    	 * @param value the value of the cell to be rendered. It is up to the specific renderer to interpret and draw the value. For example, if value is the string "true", it could be rendered as a string or it could be rendered as a check box that is checked. null is a valid value.
    	 * @param isSelected <tt>true</tt> if the cell is to be rendered with the selection highlighted; otherwise <tt>false</tt>
    	 * @param hasFocus if <tt>true</tt>, render cell appropriately. For example, put a special border on the cell, if the cell can be edited, render in the color used to indicate editing.
    	 * @param row the row index of the cell being drawn. When drawing the header, the value of <tt>row</tt> is -1.
    	 * @param column the column index of the cell being drawn.
    	 */
    	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
    	    
    		
    		String v = (String) value;
    	    
    	    if ("oui".equals(v))
    	        return new CrossDrawing();
    		
    		return this;
    	}
    }
}

