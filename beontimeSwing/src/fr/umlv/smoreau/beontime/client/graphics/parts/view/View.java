package fr.umlv.smoreau.beontime.client.graphics.parts.view;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.UIManager;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import fr.umlv.smoreau.beontime.client.actions.ActionsList;
import fr.umlv.smoreau.beontime.client.graphics.BoTModel;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.model.timetable.Course;


public class View {
	private JScrollPane jScrollPane;
	private MultiSpanCellTable table;
	private DefaultCellAttribute cellAtt;
	private static MainFrame mainFrame;
	private BoTModel model;
	private AttributiveCellTableModel ml;
	private JList rowHeader;
	private GroupableTableHeader header;
	private int hour_begin;
	private int hour_end;
	private JList columnHeader;

	
    public View(MainFrame mainframe, final BoTModel model) {
    	mainFrame=mainframe;
    	this.model = model;
    	init();
    	final PopupMenu popupMenu = new PopupMenu(null);
		table.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				Point p = new Point(e.getX(), e.getY());
				int row = table.rowAtPoint(p);
				int column = table.columnAtPoint(p);
				Object object = (Object) table.getValueAt(row, column);
				if (object instanceof Course)
				    mainFrame.setCourseSelected((Course) object);
				else
				    mainFrame.setCourseSelected(null);

				// Popup Menus
				if (e.getButton() == MouseEvent.BUTTON3 && model.getTimetable() != null) {
					table.changeSelection(row, column, false, false);
					if (object != null)
						popupMenu.show(e.getComponent(), e.getX(), e.getY());
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
    }
    public void init(){
    	UIManager.put(GroupableTableHeader.uiClassID, "fr.umlv.smoreau.beontime.client.graphics.parts.view.GroupableTableHeaderUI");
    	Course[][] data=new Course[6][52];
    	ml = new AttributiveCellTableModel(model,data,13*4,this);
        cellAtt =(DefaultCellAttribute)ml.getCellAttribute();
        table = new MultiSpanCellTable(ml);
        table.setDefaultRenderer(Object.class ,new AttributiveCellRenderer());
		header = new GroupableTableHeader(table.getColumnModel());
		initHeader(8,20);
		table.setTableHeader(header);
        ListModel listModel = new AbstractListModel() {
            String headers[] = {"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi"};
            public int getSize() { return headers.length; }
            public Object getElementAt(int index) { return headers[index]; }
        };
        rowHeader = new JList(listModel);
        rowHeader.setFixedCellWidth(50);
        rowHeader.setFixedCellHeight(table.getRowHeight()+1);
        rowHeader.setCellRenderer(new RowHeaderRenderer(table));
		jScrollPane=new JScrollPane(table);
		jScrollPane.setRowHeaderView(rowHeader);
		jScrollPane.getVerticalScrollBar().setEnabled(false);
		jScrollPane.getHorizontalScrollBar().setEnabled(false);
		//header.setSize(table.getWidth(),50);
		table.setVisible(false); 
    }
	/**
	 * @param i
	 * @param j
	 */
	private void initHeader(int deb, int fin) {
		hour_begin=deb;
		hour_end=fin;
		TableColumnModel columns = table.getColumnModel();
		String [] hours=new String[fin-deb+1];
		int j=0;
		for (int i=deb;i<=fin;i++){
			Integer ind=new Integer(i);
			hours[j]=ind.toString()+"H";
			j++;
		}
		ColumnGroup hoursGroup;
		j=0;
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
	}
	public GroupableTableHeader createHeader(int deb, int fin) {

		hour_begin=deb;
		hour_end=fin;

		TableColumnModel columns = table.getColumnModel();
		String [] hours=new String[fin-deb+1];
		int j=0;
		for (int i=deb;i<=fin;i++){
			Integer ind=new Integer(i);
			hours[j]=ind.toString()+"H";
			j++;
		}
		ColumnGroup hoursGroup;
		j=0;
		GroupableTableHeader header1 = new GroupableTableHeader(table.getColumnModel());
		for(int i=0;i<hours.length;i++){
			
			hoursGroup= new ColumnGroup(hours[i]);
			hoursGroup.add(columns.getColumn(j));
			hoursGroup.add(columns.getColumn(j+1));
			hoursGroup.add(columns.getColumn(j+2));
			hoursGroup.add(columns.getColumn(j+3));
			j=j+4;
			header1.addGroup(hoursGroup);
			
		}
		header1.setResizingAllowed(false);
		return header1;
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
            JMenuItem menuItem = new JMenuItem(ActionsList.getAction("ModifyCourse"));
			add(menuItem);
			menuItem = new JMenuItem(ActionsList.getAction("RemoveCourse"));
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
	public JList getRowHeader() {
		return rowHeader;
	}
	public int getHour_begin() {
		return hour_begin;
	}
	public int getHour_end() {
		return hour_end;
	}
}

 class RowHeaderRenderer extends JLabel implements ListCellRenderer {
    
    /**
     * Constructor creates all cells the same
     * To change look for individual cells put code in
     * getListCellRendererComponent method
     **/
    RowHeaderRenderer(JTable table) {
        JTableHeader header = table.getTableHeader();
        setOpaque(true);
        setBorder(UIManager.getBorder("TableHeader.cellBorder"));
        setHorizontalAlignment(CENTER);
        setForeground(header.getForeground());
        setBackground(header.getBackground());
        setFont(header.getFont());
    }
    
    /**
     * Returns the JLabel after setting the text of the cell
     **/
    public Component getListCellRendererComponent( JList list,
    Object value, int index, boolean isSelected, boolean cellHasFocus) {
        
        setText((value == null) ? "" : value.toString());
        return this;
    }
}