package fr.umlv.smoreau.beontime.client.graphics.windows;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import fr.umlv.smoreau.beontime.client.graphics.BoTModel;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.model.Group;
import fr.umlv.smoreau.beontime.model.association.BelongStudentGroup;
import fr.umlv.smoreau.beontime.model.element.Room;
import fr.umlv.smoreau.beontime.model.user.User;

/**
 * @author BeOnTime
 */
public class ManageIdentityGroupsTable extends JTable {
	private JPanel panel;
    private final JTable table;
    private static MainFrame mainFrame;
    private Room roomSelected;
    
    public ManageIdentityGroupsTable(final BoTModel model) {
        super();
        super.setModel(new ManageIdentityGroupsAdapter(model));
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
        tableColumn.setCellRenderer(new CrossRenderer());
        tableColumnModel.addColumn(tableColumn);
        
        Group group = mainFrame.getGroupSelected();
		tableColumn = new TableColumn(1);
		tableColumn.setCellRenderer(new CrossRenderer());
		tableColumn.setHeaderValue(group.getHeading());
		tableColumnModel.addColumn(tableColumn);

        //tableColumnModel.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setColumnModel(tableColumnModel);
		
		this.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				Point p = new Point(e.getX(), e.getY());
				final int row = table.rowAtPoint(p);
				final int column = table.columnAtPoint(p);
				
				if (column == 1) {
					ManageIdentityGroupsAdapter adapter = (ManageIdentityGroupsAdapter) table.getModel();

					Object obj = table.getValueAt(row, column);
					if (obj instanceof BelongStudentGroup) {
						adapter.getGroup().getStudents().remove(obj);
					} else {
						User student = (User) table.getValueAt(row, 0);
						adapter.getGroup().getStudents().add(new BelongStudentGroup(student, adapter.getGroup()));
					}
					
					final Object[] listeners = adapter.getEventListenerList().getListenerList();
					for (int i = listeners.length-2; i >= 0; i -= 2) {
						if (listeners[i] == TableModelListener.class) {
							final int index = i;
							EventQueue.invokeLater(new Runnable() {
								public void run() {
									((TableModelListener)listeners[index+1]).tableChanged(new TableModelEvent(table.getModel()));
								}
							});
						}
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
    		setSelectionBackground(Color.WHITE);

    		if (value == null)
    			return this;

    		if (value instanceof BelongStudentGroup) {
    			return new CrossDrawing();
    		}
    		
    		if (value instanceof User) {
    			User student = (User) value;
    			setText(student.getName() + " " + student.getFirstName());
    		}

    		return this;
    	}
    }
}

