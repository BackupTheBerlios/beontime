
package fr.umlv.smoreau.beontime.client.graphics.parts.view;
import java.awt.Dimension;

import javax.swing.table.AbstractTableModel;


/**
 * @version 1.0 11/22/98
 */

public class AttributiveCellTableModel extends AbstractTableModel {
	String[] colName;
	private fr.umlv.smoreau.beontime.client.graphics.parts.view.DefaultCellAttribute cellAtt;
	private Object[][] data;
	/**
	 * 
	 */
	public AttributiveCellTableModel(int numRows, int numColumns) {
		colName = new String[numColumns];
		data =new Object[numRows][numColumns];
	    cellAtt = new DefaultCellAttribute(numRows,numColumns);
	}
	
	  public AttributiveCellTableModel(Object[][] data, String[] columnNames) {
	  	colName=columnNames;
	  	this.data=data;
	  	cellAtt = new DefaultCellAttribute(data.length,columnNames.length);
	  }

	public int getRowCount() {
		return data.length;
	}

	public int getColumnCount() {
		return colName.length;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		return data[rowIndex][columnIndex];
	}
	
	public CellAttribute getCellAttribute() {
	    return cellAtt;
	}

	public void setCellAttribute(CellAttribute newCellAtt) {
	    int numColumns = getColumnCount();
	    int numRows    = getRowCount();
	    if ((newCellAtt.getSize().width  != numColumns) ||
	        (newCellAtt.getSize().height != numRows)) {
	      newCellAtt.setSize(new Dimension(numRows, numColumns));
	    }
	    cellAtt = (DefaultCellAttribute) newCellAtt;
	    fireTableDataChanged();
	}
   
}

