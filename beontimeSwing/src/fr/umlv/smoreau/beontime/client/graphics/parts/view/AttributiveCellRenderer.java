package fr.umlv.smoreau.beontime.client.graphics.parts.view;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.Iterator;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import fr.umlv.smoreau.beontime.model.element.Room;
import fr.umlv.smoreau.beontime.model.timetable.Course;
import fr.umlv.smoreau.beontime.model.timetable.Subject;
import fr.umlv.smoreau.beontime.model.user.User;

public class AttributiveCellRenderer extends JLabel implements TableCellRenderer {
  protected static Border noFocusBorder; 
 
  public AttributiveCellRenderer() {
    noFocusBorder = new EmptyBorder(1, 2, 1, 2);
    setOpaque(true);
    setBorder(noFocusBorder);  
  }

  public Component getTableCellRendererComponent(JTable table, Object value,
                 boolean isSelected, boolean hasFocus, int row, int column) {
    Color foreground = null;
    Color background = null;
    Font font = null;
    TableModel model = table.getModel();
    if (model instanceof AttributiveCellTableModel) {
      CellAttribute cellAtt = ((AttributiveCellTableModel)model).getCellAttribute();
      if (cellAtt instanceof ColoredCell) {
	foreground = ((ColoredCell)cellAtt).getForeground(row,column);
	background = ((ColoredCell)cellAtt).getBackground(row,column);
      }
      if (cellAtt instanceof CellFont) {
	font = ((CellFont)cellAtt).getFont(row,column);
      }
    }
    if (isSelected) {
      setForeground((foreground != null) ? foreground
                          : table.getSelectionForeground());
      setBackground(table.getSelectionBackground());
    } else {
      setForeground((foreground != null) ? foreground 
			  : table.getForeground());
      setBackground((background != null) ? background 
			  : table.getBackground());
    }
    setFont((font != null) ? font : table.getFont());
    
    if (hasFocus) {
      setBorder( UIManager.getBorder("Table.focusCellHighlightBorder") );
      if (table.isCellEditable(row, column)) {
	setForeground((foreground != null) ? foreground
	              : UIManager.getColor("Table.focusCellForeground") );
	setBackground( UIManager.getColor("Table.focusCellBackground") );
      }
    } else {
      setBorder(noFocusBorder);
    }
    setValue(value);        
    return this;
  }  
  protected void setValue(Object value) {
  		Course c=(Course)value;
  		if (value==null){
  			setText("");
  			setToolTipText("");
  		}
  		else{
  			String prtScreen="";
  			String teachers="";
  			String rooms="";
  			Subject subj=c.getSubject();
  			if (subj!=null) {
  				prtScreen=subj.getHeading();
  				if((c.getIdCourseType().getIdCourseType().intValue())==2){
  					prtScreen=prtScreen+" ( TD )";
  				}
  				else if((c.getIdCourseType().getIdCourseType().intValue())==3){
  					prtScreen=prtScreen+" ( TP )";
  				}
  				
  				for (Iterator i = c.getTeachers().iterator(); i.hasNext(); ) {
  					User user=(User) i.next();
  					teachers=teachers+" - "+user.getFirstName()+" "+user.getName();
  				}
  				if(teachers.length()>3){
  					teachers=teachers.substring(2);
  				}
  				teachers=teachers.trim();
  				//prtScreen=prtScreen+"\r"+teachers;
  				for (Iterator i = c.getRooms().iterator(); i.hasNext(); ) {
  					Room room=(Room) i.next();
  					rooms=rooms+" - "+room.getName();
  					
  				}
  				if(rooms.length()>3){
  					rooms=rooms.substring(2);
  				}
  				rooms=rooms.trim();
  				if (rooms!=""){
  					rooms="Salles : "+rooms;
  				}
  				//prtScreen=prtScreen+"\n"+rooms;
  			}
  			else{
  				prtScreen="matiere inconnue";
  			}
  			String prtScreen1="<html>"+prtScreen+"<br>"+teachers+"<br>"+rooms+"</html>";
  			setText(prtScreen1);
  			setToolTipText(prtScreen1);
  		}
  		this.setHorizontalAlignment(JLabel.CENTER);
  		this.setHorizontalTextPosition(JLabel.CENTER);
  		this.setVerticalAlignment(JLabel.CENTER);
  		this.setVerticalTextPosition(JLabel.CENTER);
  }
}


