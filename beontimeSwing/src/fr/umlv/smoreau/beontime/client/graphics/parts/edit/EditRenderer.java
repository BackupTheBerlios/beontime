package fr.umlv.smoreau.beontime.client.graphics.parts.edit;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

import fr.umlv.smoreau.beontime.client.graphics.ColorBoT;
import fr.umlv.smoreau.beontime.model.timetable.Subject;
import fr.umlv.smoreau.beontime.model.timetable.Timetable;

/**
 * @author BeOnTime
 */
public class EditRenderer extends DefaultTreeCellRenderer {
    /* (non-Javadoc)
     * @see javax.swing.tree.TreeCellRenderer#getTreeCellRendererComponent(javax.swing.JTree, java.lang.Object, boolean, boolean, boolean, int, boolean)
     */
    public Component getTreeCellRendererComponent(
            JTree tree,
			Object value,
			boolean selected,
			boolean expanded,
			boolean leaf,
			int row,
			boolean hasFocus) {
        
        super.getTreeCellRendererComponent(tree, value, selected,
				expanded, leaf, row, hasFocus);

        Font font = null;
        String tooltip = null;
        if (value instanceof Timetable) {
            font = new Font("Arial", Font.BOLD, 15);
            Timetable timetable = (Timetable) value;
            if (timetable.getGroup() != null)
                setText(timetable.getFormation().getHeading() + " (" + timetable.getGroup().getHeading() + ")");
            else if (timetable.getFormation() != null)
                setText(timetable.getFormation().getHeading());
            else if (timetable.getTeacher() != null)
                setText(timetable.getTeacher().getName() + " " + timetable.getTeacher().getFirstName());
            else if (timetable.getRoom() != null)
                setText(timetable.getRoom().getName());
            else if (timetable.getMaterial() != null)
                setText(timetable.getMaterial().getName());
        } else if (value instanceof Subject) {
            font = new Font("Arial", Font.PLAIN, 14);
            Subject subject = (Subject) value;
            setText(subject.getHeading());
            setForeground(ColorBoT.getColorAt(subject.getIdSubject().intValue()));
        } else if (value instanceof String) {
            font = new Font("Arial", Font.PLAIN, 13);
            setText((String) value);
        }
        setFont(font);
        setToolTipText(tooltip);
        setBackgroundSelectionColor(Color.WHITE);
        
        setIcon(null);
        
        return this;
    }
}