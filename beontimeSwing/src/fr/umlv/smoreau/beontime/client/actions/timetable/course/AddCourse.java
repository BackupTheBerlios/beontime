package fr.umlv.smoreau.beontime.client.actions.timetable.course;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JTable;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.client.graphics.parts.view.AttributiveCellTableModel;
import fr.umlv.smoreau.beontime.client.graphics.parts.view.DefaultCellAttribute;
import fr.umlv.smoreau.beontime.client.graphics.windows.AddModifyCourseWindow;
import fr.umlv.smoreau.beontime.model.timetable.Course;

/**
 * @author BeOnTime
 */
public class AddCourse extends Action {
    private static final String NAME = "Placer un cours";
	private static final String ICON = "ajouter_cours.png";
    private static JTable table;
    private static DefaultCellAttribute cellAtt;
    private static AttributiveCellTableModel ml;

    

    public AddCourse(MainFrame mainFrame) {
        super(NAME, ICON, mainFrame);
    }
    
    public AddCourse(boolean showIcon, MainFrame mainFrame) {
        super(NAME, showIcon ? ICON : null, mainFrame);
    }
    
    public AddCourse(String name, MainFrame mainFrame) {
        super(name, ICON, mainFrame);
    }
    
    public AddCourse(String name, boolean showIcon, MainFrame mainFrame) {
        super(name, showIcon ? ICON : null, mainFrame);
    }
    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        ml=mainFrame.getTableModel();
        cellAtt = (DefaultCellAttribute) ml.getCellAttribute();
        table = mainFrame.getTable();
        int[] columns = table.getSelectedColumns();
        int[] rows    = table.getSelectedRows();
    	AddModifyCourseWindow window = new AddModifyCourseWindow();
    	if (columns.length>1){
    		window.setStartHour(columns[0]);
    		window.setEndHour(columns[columns.length-1]);
    	}
        window.show();
        int startColumn=window.getStartHour();
        int endColumn=window.getEndHour();
        if (endColumn<startColumn) return;
        columns=new int[(endColumn-startColumn)+1];
        for(int i=0;i<=(endColumn-startColumn);i++){
        	columns[i]=startColumn+i;
        }
        Date date=window.getDateCourse();
        Calendar c=Calendar.getInstance();
        c.setTime(date);
        int day=c.get(Calendar.DAY_OF_WEEK)-2;
        int [] row=new int[]{day};
        changeColor(false,row,columns,Color.BLUE);
        cellAtt.setFont(new Font("Arial", Font.CENTER_BASELINE, 9),row,columns);
        ml.setValueAt(new Course(new Long(4)),row[0],columns[0]);
        ml.fireTableDataChanged();
        cellAtt.combine(row,columns);
        table.revalidate();
        table.repaint();
        
        

    }
    private final void changeColor(boolean isForeground,int[] rows,int[] columns,Color c) {
        if ((rows == null) || (columns == null)) return;
        if ((rows.length<1)||(columns.length<1)) return;
        Color target    = cellAtt.getForeground(rows[0], columns[0]);
        Color reference = cellAtt.getBackground(rows[0], columns[0]);
        for (int i=0;i<rows.length;i++) {
          int row = rows[i];
          for (int j=0;j<columns.length;j++) {
            int column = columns[j];
            target    = (target    != cellAtt.getForeground(row, column)) ?
              null : target;
            reference = (reference != cellAtt.getBackground(row, column)) ?
              null : reference;
          }
        }
        String title;
        if (isForeground) {
          target    = (target   !=null) ? target    : table.getForeground();
          reference = (reference!=null) ? reference : table.getBackground();
          title = "Foreground Color";
        } else {
          target    = (reference!=null) ? reference : table.getBackground();
          reference = (target   !=null) ? target    : table.getForeground();
          title = "Foreground Color";
        }   
        
        Color color = c;
        if (color != null) {        
          if (isForeground) {
            cellAtt.setForeground(color, rows, columns);
          } else {
            cellAtt.setBackground(color, rows, columns);
          }
          table.clearSelection();
          table.revalidate();
          table.repaint();    
        }
      }
}
