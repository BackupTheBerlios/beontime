package fr.umlv.smoreau.beontime.client.graphics.parts.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.AbstractListModel;
import javax.swing.ListModel;
import javax.swing.table.AbstractTableModel;

import fr.umlv.smoreau.beontime.client.graphics.BoTModel;
import fr.umlv.smoreau.beontime.client.graphics.event.BoTEvent;
import fr.umlv.smoreau.beontime.client.graphics.event.DefaultBoTListener;
import fr.umlv.smoreau.beontime.model.timetable.Course;
import fr.umlv.smoreau.beontime.model.timetable.Courses;
import fr.umlv.smoreau.beontime.model.timetable.Timetable;

public class AttributiveCellTableModel extends AbstractTableModel {
	private int colNb=0;
	private DefaultCellAttribute cellAtt;
	private Object[][] data;
	private String[] plage=new String[]{"00","15","30","45"};
	private int rowNb=0;
	private View view;

	public AttributiveCellTableModel(BoTModel model, int numRows, int numColumns,View v) {
		colNb = numColumns;
		rowNb=numRows;
		view=v;
		data =new Course[numRows][numColumns];
	    cellAtt = new DefaultCellAttribute(numRows,numColumns);
	    
	    model.addBoTListener(new ViewListener());
	}
	
	public AttributiveCellTableModel(BoTModel model, Course[][] data, int nbcolumn,View v) {
	  	colNb=nbcolumn;
	  	rowNb=data.length;
	  	this.data=data;
	  	view=v;
	  	cellAtt = new DefaultCellAttribute(data.length,nbcolumn);
	  	init();
	  	model.addBoTListener(new ViewListener());
	  }
	
	private void init(){
		for(int i=0;i<rowNb;i++){
			for(int j=0;j<colNb;j++){
				data[i][j]=null;
			}
		}
	}
	private void initDataTab() {
		data =new Object[rowNb][colNb];
	    cellAtt = new DefaultCellAttribute(rowNb,colNb);
	    init();
	}
	public int getRowCount() {
		return data.length;
	}

	public int getColumnCount() {
		return colNb;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		return data[rowIndex][columnIndex];
	}
	public void setValueAt(Object o,int rowIndex, int columnIndex){
		data[rowIndex][columnIndex]=o;
	}
	
	public DefaultCellAttribute getCellAttribute() {
	    return cellAtt;
	}
	public void setCellAttribute(DefaultCellAttribute cellatt) {
	    cellAtt=cellatt;
	}
	public void addDataRow(){
		Object[][] data2=new Object[rowNb+1][colNb];
		for(int i=0;i<rowNb;i++){
			for(int j=0;j<colNb;j++){
				data2[i][j]=data[i][j];
			}
		}
	    for (int i=0;i<colNb;i++) {
	        data2[rowNb-1][i]=null;
	      }
		data=data2;
		rowNb=rowNb+1;
	}
	public void addDataColumn(){
		Object[][] data2=new Object[rowNb][colNb+4];
		for(int i=0;i<rowNb;i++){
			for(int j=0;j<colNb;j++){
				data2[i][j]=data[i][j];
			}
		}
	    for (int i=0;i<rowNb;i++) {
	    	for(int j=0;j<4;j++){
	    		data2[i][colNb+j]=null;
	    	}
	      }
		data=data2;
		colNb=colNb+4;
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
		    view.getTable().setVisible(true);
		    view.getJScrollPane().getVerticalScrollBar().setEnabled(true);
		    view.getJScrollPane().getHorizontalScrollBar().setEnabled(true);
			Timetable timetable = e.getTimetable();
		    Collection courses = timetable.getCoursesArranged();
		    initDataTab();
		    for (Iterator i = courses.iterator(); i.hasNext(); ) {
		        Object object = (Object) i.next();

		        Calendar beginDate;
		        Calendar endDate;
		        Color color;
		        
		        if (object instanceof Course) {
		            Course tmp = (Course) object;
		            beginDate = tmp.getBeginDate();
		            endDate = tmp.getEndDate();
		            color = tmp.getSubject().getColor();
		        } else if (object instanceof Courses) {
		            Courses tmp = (Courses) object;
		            beginDate = tmp.getBeginDate();
		            endDate = tmp.getEndDate();
		            color = tmp.getColor();
		        } else {
		            continue;
		        }
		        
		        int day=beginDate.get(Calendar.DAY_OF_WEEK)-2;
	        	if ((cellAtt.rowSize<7) && (day<0)){
	        		day=cellAtt.rowSize;
	        		cellAtt.addRow();
	        		addDataRow();
	                ListModel listModel = new AbstractListModel() {
	                    String headers[] = {"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche"};
	                    public int getSize() { return headers.length; }
	                    public Object getElementAt(int index) { return headers[index]; }
	                };	                
	        		view.getRowHeader().setModel(listModel);
	        		view.getJScrollPane().repaint();
	        	}
	        	if(day<0){
	        		day=6;

	        	}
	        	/*
	        	if((beginDate.get(Calendar.HOUR_OF_DAY)>view.getHour_end())||((endDate.get(Calendar.HOUR_OF_DAY)-1)>view.getHour_end())){
	        		System.out.println("add column");
	        		cellAtt.addColumn();
	        		addDataColumn();
	        		//GroupableTableHeader header = view.createHeader(view.getHour_begin(),(view.getHour_end()+1));
	        		//view.getTable().setTableHeader(header);
	        	}*/
	        	
		        int startColumn=getStartColumnHour(beginDate.get(Calendar.HOUR_OF_DAY),beginDate.get(Calendar.MINUTE));
		        int endColumn=getEndColumnHour(endDate.get(Calendar.HOUR_OF_DAY),endDate.get(Calendar.MINUTE));

		        if (endColumn>startColumn){
			        int[] columns=new int[(endColumn-startColumn)+1];
			        for(int j=0;j<=(endColumn-startColumn);j++){
			        	columns[j]=startColumn+j;
			        	}
			        int [] row=new int[]{day};
			        if(cellAtt.combine(row,columns)){
			        	setValueAt(object,row[0],columns[0]);
			        	changeColor(false,row,columns,color);
				        cellAtt.setFont(new Font("Arial", Font.CENTER_BASELINE, 9),row,columns);
			        }
		        }

		    }
		    fireTableDataChanged();
		}
		
		public void closeTimetable(BoTEvent e) {
		    initDataTab();
		    view.getTable().setVisible(false);
		    view.getJScrollPane().getVerticalScrollBar().setEnabled(false);
		    view.getJScrollPane().getHorizontalScrollBar().setEnabled(false);
		    fireTableDataChanged();
		}

		public void addCourse(BoTEvent e) {
		    Course course = e.getCourse();
	        Calendar beginDate=course.getBeginDate();
	        Calendar endDate=course.getEndDate();
	        int day=beginDate.get(Calendar.DAY_OF_WEEK)-2;
        	if ((cellAtt.rowSize<7) && (day<0)){
        		day=cellAtt.rowSize;
        		cellAtt.addRow();
        		addDataRow();
                ListModel listModel = new AbstractListModel() {
                    String headers[] = {"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche"};
                    public int getSize() { return headers.length; }
                    public Object getElementAt(int index) { return headers[index]; }
                };
                view.getRowHeader().setModel(listModel);
        		view.getJScrollPane().repaint();
        	}
        	if(day<0){
        		day=cellAtt.rowSize-1;

        	}
        	/*
        	if((beginDate.get(Calendar.HOUR_OF_DAY)>view.getHour_end())||(endDate.get(Calendar.HOUR_OF_DAY)>view.getHour_end())){
        		System.out.println("ok");
        		cellAtt.addColumn();
        		addDataColumn();
        		//GroupableTableHeader header = view.createHeader(view.getHour_begin(),view.getHour_end()+1,data);
        		//view.getTable().setTableHeader(header);
        	}*/
	        int startColumn=getStartColumnHour(beginDate.get(Calendar.HOUR_OF_DAY),beginDate.get(Calendar.MINUTE));
	        int endColumn=getEndColumnHour(endDate.get(Calendar.HOUR_OF_DAY),endDate.get(Calendar.MINUTE));
	        if (endColumn>startColumn){
		        int[] columns=new int[(endColumn-startColumn)+1];
		        for(int j=0;j<=(endColumn-startColumn);j++){
		        	columns[j]=startColumn+j;
		        	}
		        int [] row=new int[]{day};
		        if(cellAtt.combine(row,columns)){
		        	setValueAt(course,row[0],columns[0]);
		        	changeColor(false,row,columns,course.getSubject().getColor());
			        cellAtt.setFont(new Font("Arial", Font.CENTER_BASELINE, 9),row,columns);
		        }
		        fireTableDataChanged();

	        }
		}
		
		public void modifyCourse(BoTEvent e) {
		    Course course = e.getCourse();
		    for(int i=0;i<rowNb;i++){
		    	for(int j=0;j<colNb;j++){
		    		if(((Course)getValueAt(i,j))!=null){
			    		Course courseRead=(Course)getValueAt(i,j);
			    		if (courseRead.equals(course)){
			    			cellAtt.split(i,j);
			    	        setValueAt(null,i,j);
			    	        j=colNb;
				    		i=rowNb;
			    		}
		    		}
		    	}
		    }
	        Calendar beginDate=course.getBeginDate();
	        Calendar endDate=course.getEndDate();
	        int day=beginDate.get(Calendar.DAY_OF_WEEK)-2;
	        int startColumn=getStartColumnHour(beginDate.get(Calendar.HOUR_OF_DAY),beginDate.get(Calendar.MINUTE));
	        int endColumn=getEndColumnHour(endDate.get(Calendar.HOUR_OF_DAY),endDate.get(Calendar.MINUTE));
	        if (endColumn<startColumn) return;
	        int[] columns=new int[(endColumn-startColumn)+1];
	        for(int j=0;j<=(endColumn-startColumn);j++){
	        	columns[j]=startColumn+j;
	        }
	        int [] row=new int[]{day};
	        changeColor(false,row,columns,course.getSubject().getColor());
	        cellAtt.setFont(new Font("Arial", Font.CENTER_BASELINE, 9),row,columns);
	        setValueAt(course,row[0],columns[0]);
	        cellAtt.combine(row,columns);
	        fireTableDataChanged();
		    
		}
		
		public void removeCourse(BoTEvent e) {
		    Course course = e.getCourse();
		    for(int i=0;i<rowNb;i++){
		    	for(int j=0;j<colNb;j++){
		    		Object obj = getValueAt(i,j);
		    		if (obj instanceof Course) {
			    		if(((Course)obj)!=null){
			    			Course courseRead=(Course)getValueAt(i,j);
			    			if (courseRead.equals(course)){
			    				cellAtt.split(i,j);
			    				setValueAt(null,i,j);
			    				fireTableDataChanged();
			    				return;
			    			}
			    		}
		    		}
		    	}
		    }
		}

		private int getStartColumnHour(int hour,int minute){
			int ret;
			if(hour<8){
				ret=0;
				return ret;
			}
			else{
				ret=(hour-8)*4;
			}
			ret=ret+(minute/15);
	    	return ret;
		}
		private int getEndColumnHour(int hour,int minute){
	    	int ret;
	    	if (hour<8){
	    		ret=0;
	    		return ret;
	    	}
	    	else{
	    		ret=(hour-8)*4;
	    	}
	    	ret=ret+(minute/15)-1;
	    	return ret;
		}
		private final void changeColor(boolean isForeground,int[] rows,int[] columns,Color c) {
			MultiSpanCellTable table;
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
	          target    = (target   !=null) ? target    : Color.BLACK;
	          reference = (reference!=null) ? reference : Color.WHITE;
	          title = "Foreground Color";
	        } else {
	          target    = (reference!=null) ? reference : Color.WHITE;
	          reference = (target   !=null) ? target    : Color.BLACK;
	          title = "Foreground Color";
	        }   
	        
	        Color color = c;
	        if (color != null) {        
	          if (isForeground) {
	            cellAtt.setForeground(color, rows, columns);
	          } else {
	            cellAtt.setBackground(color, rows, columns);
	          }
	        }
	      }
	}
}

