package fr.umlv.smoreau.beontime.client.graphics.parts.view;
import java.awt.Dimension;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.table.AbstractTableModel;

import fr.umlv.smoreau.beontime.client.graphics.BoTModel;
import fr.umlv.smoreau.beontime.client.graphics.event.BoTEvent;
import fr.umlv.smoreau.beontime.client.graphics.event.DefaultBoTListener;
import fr.umlv.smoreau.beontime.model.timetable.Course;
import fr.umlv.smoreau.beontime.model.timetable.Timetable;

public class AttributiveCellTableModel extends AbstractTableModel {
	int colNb=0;
	private DefaultCellAttribute cellAtt;
	private String[][] data;
	private String[] plage=new String[]{"00","15","30","45"};

	public AttributiveCellTableModel(BoTModel model, int numRows, int numColumns) {
		colNb = numColumns;
		data =new String[numRows][numColumns];
	    cellAtt = new DefaultCellAttribute(numRows,numColumns);
	    
	    model.addBoTListener(new ViewListener());
	}
	
	  public AttributiveCellTableModel(BoTModel model, String[][] data, int nbcolumn) {
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
   
	private class ViewListener extends DefaultBoTListener {
		public void refreshAll(BoTEvent e) {
		    Timetable timetable = e.getTimetable();
		    Collection courses = timetable.getCourses();
		    
		    for (Iterator i = courses.iterator(); i.hasNext(); ) {
		        Course course = (Course) i.next();
		        
		        //TODO Mohamed: afficher le cours
		    }
		}
		
		public void addCourse(BoTEvent e) {
		    Course course = e.getCourse();

		    //TODO Mohamed: ajouter le cours
		}
		
		public void modifyCourse(BoTEvent e) {
		    Course course = e.getCourse();

		    //TODO Mohamed: modifier le cours
		}
		
		public void removeCourse(BoTEvent e) {
		    Course course = e.getCourse();

		    //TODO Mohamed: supprimer le cours
		}
	}
}

