package fr.umlv.smoreau.beontime.client.graphics.windows;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;


/**
 * @author BeOnTime
 */
public class CrossDrawing extends Canvas {
    public void paint(Graphics g) {
        int width = getWidth()-50;
        int height = getHeight()-50;
        g.setColor(Color.BLACK);
        
        g.drawLine(50, 50, width, height);
        g.drawLine(width, 50, 50, height);
    }
}