package fr.umlv.smoreau.beontime.client.graphics.parts.view;
import java.awt.*;

public interface CellFont {
  
  public Font getFont(int row, int column);
  public void setFont(Font font, int row, int column);
  public void setFont(Font font, int[] rows, int[] columns);


}
