package fr.umlv.smoreau.beontime.client.graphics.parts.view;
import java.awt.*;

public class DefaultCellAttribute implements CellAttribute ,CellSpan ,ColoredCell ,CellFont {


  protected int rowSize;
  protected int columnSize;
  protected int[][][] span;                   // CellSpan
  protected Color[][] foreground;             // ColoredCell
  protected Color[][] background;             //
  protected Font[][]  font;                   // CellFont
  
  public DefaultCellAttribute() {
    this(1,1);
  }
  
  public DefaultCellAttribute(int numRows, int numColumns) {
    setSize(new Dimension(numColumns, numRows));
  }

  protected void initValue() {
    for(int i=0; i<span.length;i++) {
      for(int j=0; j<span[i].length; j++) {
      		span[i][j][CellSpan.COLUMN] = 1;
      		span[i][j][CellSpan.ROW]    = 1;
      }
    }
  }

  public int[] getSpan(int row, int column) {
    if (isOutOfBounds(row, column)) {
      int[] ret_code = {1,1};
      return ret_code;
    }
    return span[row][column];
  }

  public void setSpan(int[] span, int row, int column) {
    if (isOutOfBounds(row, column)) return;
    this.span[row][column] = span;
  }
      
  public boolean isVisible(int row, int column) {
    if (isOutOfBounds(row, column)) return false;
    if ((span[row][column][CellSpan.COLUMN] < 1)
      ||(span[row][column][CellSpan.ROW]    < 1)) return false;
    return true;
  }

  public boolean combine(int[] rows, int[] columns) {
    if (isOutOfBounds(rows, columns)){
    	return false;
    }
    int    rowSpan  = rows.length;
    int columnSpan  = columns.length;
    int startRow    = rows[0];
    int startColumn = columns[0];
    for (int i=0;i<rowSpan;i++) {
      for (int j=0;j<columnSpan;j++) {
	if ((span[startRow +i][startColumn +j][CellSpan.COLUMN] != 1)
	  ||(span[startRow +i][startColumn +j][CellSpan.ROW]    != 1)) {
	  //System.out.println("can't combine");
	  return false;
	}
      }
    }
    for (int i=0,ii=0;i<rowSpan;i++,ii--) {
      for (int j=0,jj=0;j<columnSpan;j++,jj--) {
	span[startRow +i][startColumn +j][CellSpan.COLUMN] = jj;
	span[startRow +i][startColumn +j][CellSpan.ROW]    = ii;
      }
    }
    span[startRow][startColumn][CellSpan.COLUMN] = columnSpan;
    span[startRow][startColumn][CellSpan.ROW]    =    rowSpan;
    return true;
  }

  public void split(int row, int column) {
    if (isOutOfBounds(row, column)) return;
    int columnSpan = span[row][column][CellSpan.COLUMN];
    int    rowSpan = span[row][column][CellSpan.ROW];
    for (int i=0;i<rowSpan;i++) {
      for (int j=0;j<columnSpan;j++) {
      	span[row +i][column +j][CellSpan.COLUMN] = 1;
      	span[row +i][column +j][CellSpan.ROW]    = 1;
      	setBackground(Color.WHITE,row+i,column+j);
      }
    }
  }

  public Color getForeground(int row, int column) {
    if (isOutOfBounds(row, column)) return null;
    return foreground[row][column];
  }
  public void setForeground(Color color, int row, int column) {
    if (isOutOfBounds(row, column)) return;
    foreground[row][column] = color;
  }
  public void setForeground(Color color, int[] rows, int[] columns) {
    if (isOutOfBounds(rows, columns)) return;
    setValues(foreground, color, rows, columns);
  }
  public Color getBackground(int row, int column) {
    if (isOutOfBounds(row, column)) return null;
    return background[row][column];
  }
  public void setBackground(Color color, int row, int column) {
    if (isOutOfBounds(row, column)) return;
    background[row][column] = color;
  }
  public void setBackground(Color color, int[] rows, int[] columns) {
    if (isOutOfBounds(rows, columns)) return;
    setValues(background, color, rows, columns);
  }

  public Font getFont(int row, int column) {
    if (isOutOfBounds(row, column)) return null;
    return font[row][column];
  }
  public void setFont(Font font, int row, int column) {
    if (isOutOfBounds(row, column)) return;
    this.font[row][column] = font;
  }
  public void setFont(Font font, int[] rows, int[] columns) {
    if (isOutOfBounds(rows, columns)) return;
    setValues(this.font, font, rows, columns);
  }

  public void addColumn() {
    int[][][] oldSpan = span;
    Color[][] oldForeground=foreground;
    Color[][] oldBackground=background;
    Font[][]  oldFont=font;
    int numRows    = oldSpan.length;
    int numColumns = oldSpan[0].length+4;
    System.out.println("numColumns :"+numColumns);
    setSize(new Dimension(numColumns, numRows));
    //System.arraycopy(oldSpan,0,span,0,numRows);
    for(int i=0;i<numRows;i++){
    	for(int j=0;j<numColumns-4;j++){
    		span[i][j][CellSpan.COLUMN]=oldSpan[i][j][CellSpan.COLUMN];
    		span[i][j][CellSpan.ROW]=oldSpan[i][j][CellSpan.ROW];
    		foreground[i][j]=oldForeground[i][j];
    		background[i][j]=oldBackground[i][j];
    		font[i][j]=oldFont[i][j];
    	}
    }
  }


  public void addRow() {
    int[][][] oldSpan = span;
    Color[][] oldForeground=foreground;
    Color[][] oldBackground=background;
    Font[][]  oldFont=font;
    int numRows    = oldSpan.length+1;
    int numColumns = oldSpan[0].length;
    setSize(new Dimension(numColumns, numRows));
    //System.arraycopy(oldSpan,0,span,0,numRows);
    for(int i=0;i<numRows-1;i++){
    	for(int j=0;j<numColumns;j++){
    		span[i][j][CellSpan.COLUMN]=oldSpan[i][j][CellSpan.COLUMN];
    		span[i][j][CellSpan.ROW]=oldSpan[i][j][CellSpan.ROW];
    		foreground[i][j]=oldForeground[i][j];
    		background[i][j]=oldBackground[i][j];
    		font[i][j]=oldFont[i][j];
    	}
    }
  }

  public void insertRow(int row) {
    int[][][] oldSpan = span;
    int numRows    = oldSpan.length;
    int numColumns = oldSpan[0].length;
    span = new int[numRows + 1][numColumns][2];
    if (0 < row) {
      System.arraycopy(oldSpan,0,span,0,row-1);
    }
    System.arraycopy(oldSpan,0,span,row,numRows - row);
    for (int i=0;i<numColumns;i++) {
      span[row][i][CellSpan.COLUMN] = 1;
      span[row][i][CellSpan.ROW]    = 1;
    }
  }

  public Dimension getSize() {
    return new Dimension(rowSize, columnSize);
  }

  public void setSize(Dimension size) {
    columnSize = size.width;
    rowSize    = size.height;
    span = new int[rowSize][columnSize][2];   // 2: COLUMN,ROW
    foreground = new Color[rowSize][columnSize];
    background = new Color[rowSize][columnSize];
    font = new Font[rowSize][columnSize];
    initValue();
  }
  protected boolean isOutOfBounds(int row, int column) {
    if ((row    < 0)||(rowSize    <= row)
      ||(column < 0)||(columnSize <= column)) {
      return true;
    }
    return false;
  }

  protected boolean isOutOfBounds(int[] rows, int[] columns) {
    for (int i=0;i<rows.length;i++) {
      if ((rows[i] < 0)||(rowSize <= rows[i])) return true;
    }
    for (int i=0;i<columns.length;i++) {
      if ((columns[i] < 0)||(columnSize <= columns[i])) return true;
    }
    return false;
  }

  protected void setValues(Object[][] target, Object value,
                           int[] rows, int[] columns) {
    for (int i=0;i<rows.length;i++) {
      int row = rows[i];
      for (int j=0;j<columns.length;j++) {
	int column = columns[j];
	target[row][column] = value;
      }
    }
  }
}
