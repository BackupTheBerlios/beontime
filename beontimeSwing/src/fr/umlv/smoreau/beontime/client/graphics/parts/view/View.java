package fr.umlv.smoreau.beontime.client.graphics.parts.view;

import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.table.TableColumnModel;

import fr.umlv.smoreau.beontime.client.actions.ActionsList;
import fr.umlv.smoreau.beontime.client.actions.timetable.course.AddCourse;
import fr.umlv.smoreau.beontime.client.graphics.BoTModel;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.model.timetable.Course;
import fr.umlv.smoreau.beontime.model.timetable.Subject;


/**
 * @author BeOnTime
 */
public class View {
	private JScrollPane jScrollPane;
	private MultiSpanCellTable table;
	private DefaultCellAttribute cellAtt;
	private static MainFrame mainFrame;
	private BoTModel model;
	private AttributiveCellTableModel ml;
	/**
	 * @return Returns the table.
	 */

	
    public View(MainFrame mainframe, BoTModel model) {
    	mainFrame=mainframe;
    	this.model = model;
    	init();
    }
    public void init(){
    	UIManager.put(GroupableTableHeader.uiClassID, "fr.umlv.smoreau.beontime.client.graphics.parts.view.GroupableTableHeaderUI");
    	Course[][] data=new Course[6][52];
    	ml = new AttributiveCellTableModel(model,data,13*4);
        cellAtt =(DefaultCellAttribute)ml.getCellAttribute();
        table = new MultiSpanCellTable(ml);
        table.setDefaultRenderer(Object.class ,new AttributiveCellRenderer());
        table.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				int x=e.getX();
				int y=e.getY();
				Point p=new Point(x,y);
				int row=table.rowAtPoint(p);
				int column=table.columnAtPoint(p);
				if (((Course) table.getValueAt(row,column))!=null){
					ActionsList.getAction("ModifyCourse").setEnabled(true);
					ActionsList.getAction("RemoveCourse").setEnabled(true);
					ActionsList.getAction("CutCourse").setEnabled(true);
					ActionsList.getAction("CopyCourse").setEnabled(true);
				}
				else{
					ActionsList.getAction("ModifyCourse").setEnabled(false);
					ActionsList.getAction("RemoveCourse").setEnabled(false);
					ActionsList.getAction("CutCourse").setEnabled(false);
					ActionsList.getAction("CopyCourse").setEnabled(false);
				}

			}

			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
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
		header.setResizingAllowed(false);
		table.setTableHeader(header);
		jScrollPane=new JScrollPane(table);
		//header.setSize(table.getWidth(),50);
		
		
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
	/**
	 * @return
	 */
	public Course getCourseSelected() {
		int row=getTable().getSelectedRow();
		int column=getTable().getSelectedColumn();
		if ((row==-1)&&(column==-1)){
			return null;
		}
		Course course=(Course)ml.getValueAt(row,column);
		return course;
	}
}
