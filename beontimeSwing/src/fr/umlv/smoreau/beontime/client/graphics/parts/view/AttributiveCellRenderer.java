package fr.umlv.smoreau.beontime.client.graphics.parts.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Iterator;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import fr.umlv.smoreau.beontime.dao.TimetableDao;
import fr.umlv.smoreau.beontime.model.element.Material;
import fr.umlv.smoreau.beontime.model.element.Room;
import fr.umlv.smoreau.beontime.model.timetable.Course;
import fr.umlv.smoreau.beontime.model.timetable.Subject;
import fr.umlv.smoreau.beontime.model.user.User;

/**
 * @author BeOnTime
 */
public class AttributiveCellRenderer extends JLabel implements TableCellRenderer {
    private SimpleDateFormat FORMAT_HOUR = new SimpleDateFormat("HH'h'mm");
    
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
        Course c = (Course)value;
        if (value==null) {
            setText(null);
            setToolTipText(null);
        } else {
            StringBuffer prtScreen = new StringBuffer();
            StringBuffer teachers = new StringBuffer();
            StringBuffer rooms = new StringBuffer();
            StringBuffer materials = new StringBuffer();
            Subject subj=c.getSubject();
            if (subj != null) {
                prtScreen.append(subj.getHeading());
                if (TimetableDao.TYPE_TD.equals(c.getIdCourseType().getNameCourseType()))
                    prtScreen.append(" (TD)");
                else if (TimetableDao.TYPE_TP.equals(c.getIdCourseType().getNameCourseType()))
                    prtScreen.append(" (TP)");
                
                for (Iterator i = c.getTeachers().iterator(); i.hasNext(); ) {
                    User user = (User) i.next();
                    if (teachers.length() > 0)
                        teachers.append(" - ");
                    teachers.append(user.getFirstName()).append(" ").append(user.getName());
                }

                for (Iterator i = c.getRooms().iterator(); i.hasNext(); ) {
                    Room room = (Room) i.next();
                    if (rooms.length() > 0)
                        rooms.append(" - ");
                    rooms.append(room.getName());
                }

                for (Iterator i = c.getMaterials().iterator(); i.hasNext(); ) {
                    Material material = (Material) i.next();
                    if (materials.length() > 0)
                        materials.append(" - ");
                    materials.append(material.getName());
                }
            }
            else {
                prtScreen.append("Matière inconnue");
            }

            StringBuffer prtScreen1 = new StringBuffer();
            prtScreen1.append("<html>");
            prtScreen1.append(prtScreen);
            if (teachers.length() > 0)
                prtScreen1.append("<br>").append(teachers);
            if (rooms.length() > 0)
                prtScreen1.append("<br>").append(rooms);
            prtScreen1.append("</html>");
            setText(prtScreen1.toString());
            
            StringBuffer prtScreen2 = new StringBuffer();
            prtScreen2.append("<html>");
            prtScreen2.append("Matière: ").append(prtScreen);
            prtScreen2.append("<br>").append("Horaires: ");
            prtScreen2.append(FORMAT_HOUR.format(c.getBeginDate().getTime()));
            prtScreen2.append(" / ").append(FORMAT_HOUR.format(c.getEndDate().getTime()));
            if (teachers.length() > 0)
                prtScreen2.append("<br>").append("Enseignant(s): ").append(teachers);
            if (rooms.length() > 0)
                prtScreen2.append("<br>").append("Salle(s): ").append(rooms);
            if (materials.length() > 0)
                prtScreen2.append("<br>").append("Matériel(s): ").append(materials);
            prtScreen2.append("</html>");
            setToolTipText(prtScreen2.toString());
        }

        this.setHorizontalAlignment(JLabel.CENTER);
        this.setHorizontalTextPosition(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.CENTER);
        this.setVerticalTextPosition(JLabel.CENTER);
    }
}


