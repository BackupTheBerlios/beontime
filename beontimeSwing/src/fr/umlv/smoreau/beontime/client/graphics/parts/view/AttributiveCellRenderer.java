
package fr.umlv.smoreau.beontime.client.graphics.parts.view;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.border.*;


/**
 * @version 1.0 11/22/98
 */
public class AttributiveCellRenderer extends JLabel
    implements TableCellRenderer {
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
    }
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
    setText((value == null) ? "" : value.toString());
  }
}


