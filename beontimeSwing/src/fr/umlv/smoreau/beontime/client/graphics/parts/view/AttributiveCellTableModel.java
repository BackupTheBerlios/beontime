/*
 * (swing1.1beta3)
 * 
 */



package fr.umlv.smoreau.beontime.client.graphics.parts.view;
import java.awt.Dimension;

import javax.swing.table.AbstractTableModel;


/**
 * @version 1.0 11/22/98
 */

public class AttributiveCellTableModel extends AbstractTableModel {
	int colNb=0;
	private DefaultCellAttribute cellAtt;
	private String[][] data;
	private String[] plage=new String[]{"00","15","30","45"};
	/**
	 * 
	 */
	public AttributiveCellTableModel(int numRows, int numColumns) {
		colNb = numColumns;
		data =new String[numRows][numColumns];
	    cellAtt = new DefaultCellAttribute(numRows,numColumns);
	}
	
	  public AttributiveCellTableModel(String[][] data, int nbcolumn) {
	  	colNb=nbcolumn;
	  	this.data=data;
	  	cellAtt = new DefaultCellAttribute(data.length,nbcolumn);
	  }

	public int getRowCount() {
		return data.length;
	}

	public int getColumnCount() {
		return colNb;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		return (String)(data[rowIndex][columnIndex]);
	}
	public void setValueAt(Object o,int rowIndex, int columnIndex){
		data[rowIndex][columnIndex]=(String)o;
	}
	
	public DefaultCellAttribute getCellAttribute() {
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
	public String getColumnName(int columnIndex) {
		return plage[columnIndex%4];
	}
   
}

