/*
 * 
 */
package fr.umlv.smoreau.beontime.client.graphics.parts.view;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.table.TableColumnModel;

import fr.umlv.smoreau.beontime.client.actions.timetable.course.AddCourse;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;


/**
 * @author BeOnTime
 */
public class View {
	private JScrollPane jScrollPane;
	private MultiSpanCellTable table;
	private DefaultCellAttribute cellAtt;
	private static MainFrame mainFrame;
	private AttributiveCellTableModel ml;
	/**
	 * @return Returns the table.
	 */

	
    public View(MainFrame mainframe) {
    	mainFrame=mainframe;
    	init();
    }
    public void init(){
    	UIManager.put(GroupableTableHeader.uiClassID, "fr.umlv.smoreau.beontime.client.graphics.parts.view.GroupableTableHeaderUI");
    	String[][] data=new String[6][52];
    	ml = new AttributiveCellTableModel(data,13*4);
        cellAtt =(DefaultCellAttribute)ml.getCellAttribute();
        table = new MultiSpanCellTable(ml);
        table.setDefaultRenderer(Object.class ,new AttributiveCellRenderer());

		GroupableTableHeader header = new GroupableTableHeader(table.getColumnModel());
		TableColumnModel columns = table.getColumnModel();
		String [] hours=new String[]{"8H","9H","10H","11H","12H","13H","14H","15H","16H","17H","18H","19H","20H"};
		ColumnGroup hoursGroup;
		int j=0;
		for(int i=0;i<hours.length;i++){
			
			hoursGroup= new ColumnGroup(hours[i]);
			hoursGroup.add(columns.getColumn(j));
			hoursGroup.add(columns.getColumn(j+1));
			hoursGroup.add(columns.getColumn(j+2));
			hoursGroup.add(columns.getColumn(j+3));
			j=j+4;
			header.addGroup(hoursGroup);
		}
		table.setTableHeader(header);
		table.getTableHeader().setReorderingAllowed(false);
		jScrollPane=new JScrollPane(table);

		
		
    }
	/**
	 * @return Returns the jPanel.
	 */

    public JScrollPane getJScrollPane() {
		return jScrollPane;
	}
	private static class PopupMenu extends JPopupMenu {
	    private Object selected;
	    
	    public PopupMenu(Object object) {
	        super();
            JMenuItem menuItem = new JMenuItem(new AddCourse(false, mainFrame));
			add(menuItem);
	    }
	}


	/**
	 * @return Returns the cellAtt.
	 */
	public AttributiveCellTableModel getTableModel() {
		return ml;
	}
	/**
	 * @return Returns the table.
	 */
	public MultiSpanCellTable getTable() {
		return table;
	}
}
