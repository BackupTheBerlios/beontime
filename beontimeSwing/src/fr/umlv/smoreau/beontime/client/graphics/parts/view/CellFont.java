/*
 * (swing1.1beta3)
 * 
 */



package fr.umlv.smoreau.beontime.client.graphics.parts.view;
import java.awt.*;


/**
 * @version 1.0 11/22/98
 */

public interface CellFont {
  
  public Font getFont(int row, int column);
  public void setFont(Font font, int row, int column);
  public void setFont(Font font, int[] rows, int[] columns);


}
