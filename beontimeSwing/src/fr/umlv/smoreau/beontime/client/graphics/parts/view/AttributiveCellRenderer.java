package fr.umlv.smoreau.beontime.client.graphics.parts.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import fr.umlv.smoreau.beontime.model.timetable.Courses;
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
        ArrayList courses = new ArrayList();
        if (value instanceof Course) {
            courses.add(value);
        } else if (value instanceof Courses) {
            courses.addAll(((Courses) value).getCourses()); 
        } else {
            setText(null);
            setToolTipText(null);
            return;
        }

        StringBuffer text = new StringBuffer();
        text.append("<html>");
        StringBuffer tooltip = new StringBuffer();
        tooltip.append("<html>");

        for (Iterator j = courses.iterator(); j.hasNext(); ) {
            Course c = (Course) j.next();
            StringBuffer prtScreen = new StringBuffer();
            StringBuffer schedule = new StringBuffer();
            StringBuffer teachers = new StringBuffer();
            StringBuffer rooms = new StringBuffer();
            StringBuffer materials = new StringBuffer();
            Subject subj=c.getSubject();
            if (subj != null) {
                prtScreen.append("<b>").append(subj.getHeading()).append("</b>");
                if (TimetableDao.TYPE_TD.equals(c.getIdCourseType().getNameCourseType()))
                    prtScreen.append(" (TD)");
                else if (TimetableDao.TYPE_TP.equals(c.getIdCourseType().getNameCourseType()))
                    prtScreen.append(" (TP)");
                
                schedule.append(FORMAT_HOUR.format(c.getBeginDate().getTime()));
                schedule.append(" / ").append(FORMAT_HOUR.format(c.getEndDate().getTime()));

                for (Iterator i = c.getTeachers().iterator(); i.hasNext(); ) {
                    User user = (User) i.next();
                    if (user != null) {
	                    if (teachers.length() > 0)
	                        teachers.append(" - ");
	                    teachers.append(user.getFirstName()).append(" ").append(user.getName());
                    }
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

            text.append(prtScreen);
            text.append("<br>").append(schedule);
            if (teachers.length() > 0)
                text.append("<br>").append(teachers);
            if (rooms.length() > 0) {
                if (courses.size() > 1)
                    text.append(" | ");
                else
                    text.append("<br>");
                text.append(rooms);
            }

            tooltip.append("Matière: ").append(prtScreen);
            tooltip.append("<br>").append("Horaires: ");
            tooltip.append(schedule);
            if (teachers.length() > 0)
                tooltip.append("<br>").append("Enseignant(s): ").append(teachers);
            if (rooms.length() > 0)
                tooltip.append("<br>").append("Salle(s): ").append(rooms);
            if (materials.length() > 0)
                tooltip.append("<br>").append("Matériel(s): ").append(materials);
            
            if (j.hasNext()) {
                text.append("<br>");
                tooltip.append("<br><br>");
            }
        }

        tooltip.append("</html>");
        setToolTipText(tooltip.toString());
        text.append("</html>");
        setText(text.toString());

        this.setHorizontalAlignment(JLabel.CENTER);
        this.setHorizontalTextPosition(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.CENTER);
        this.setVerticalTextPosition(JLabel.CENTER);
    }
}


