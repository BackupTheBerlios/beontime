/*
 * 
 */
package fr.umlv.smoreau.beontime.client.graphics.parts.view;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableColumnModel;


/**
 * @author BeOnTime
 */
public class View {
	private JScrollPane jScrollPane;
    public View() {
    	init();
    }
    public void init(){
    	UIManager.put(GroupableTableHeader.uiClassID, "fr.umlv.smoreau.beontime.client.graphics.parts.view.GroupableTableHeaderUI");
    	JTable table = new JTable(6,52);
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
		jScrollPane=new JScrollPane(table);
    }
	/**
	 * @return Returns the jPanel.
	 */
	public JScrollPane getJScrollPane() {
		return jScrollPane;
	}
}
