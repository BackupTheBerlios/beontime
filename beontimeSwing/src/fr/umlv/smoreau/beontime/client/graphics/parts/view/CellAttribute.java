package fr.umlv.smoreau.beontime.client.graphics.parts.view;
import java.awt.*;

public interface CellAttribute {

  public void addColumn();

  public void addRow();

  public void insertRow(int row);

  public Dimension getSize();

  public void setSize(Dimension size);


}
