package fr.umlv.smoreau.beontime.test;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import javax.swing.*;


class DragAndDrop extends JFrame {
    private JPanel topPanel;
    private RectangleTest oldRect,rect;
    private boolean clicRect;

    public DragAndDrop() {
        setTitle("Drag and Drop Example");
        setSize(400, 300);
        setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        topPanel = new JPanel();
        topPanel.setLayout( null );
        getContentPane().add(topPanel);
        
        rect = new RectangleTest(150,125,100,50);
        oldRect = null;
        clicRect = false;

        // Action and mouse listener support
        enableEvents(AWTEvent.MOUSE_MOTION_EVENT_MASK | AWTEvent.MOUSE_EVENT_MASK);
    }
    
    public void paint(Graphics g) {
        if (oldRect != null) {
            oldRect.effacer(g);
            oldRect = null;
        }
        rect.dessiner(g);
    }
    
    public void processMouseEvent(MouseEvent event) {
        if (event.getID() == MouseEvent.MOUSE_PRESSED &&
                event.getButton() == MouseEvent.BUTTON1) {
            if (rect.contains(event.getX(),event.getY())) 
                clicRect = true;
        }
        
        if (event.getID() == MouseEvent.MOUSE_RELEASED &&
                event.getButton() == MouseEvent.BUTTON1) {
            if (clicRect)
                clicRect = false;
        }

        super.processMouseEvent(event);
    }
    
    public void processMouseMotionEvent(MouseEvent event) {
        if (clicRect) {
            oldRect = (RectangleTest) rect.clone();
            rect.setPosition((int) (event.getX()-rect.getWidth()/2),
                    (int) (event.getY()-rect.getHeight()/2));
            repaint();
        }
    }
    
    public static void main(String args[]) {
        DragAndDrop mainFrame	= new DragAndDrop();
        mainFrame.setVisible(true);
    }
    
    
    public class RectangleTest extends Rectangle2D.Double {
        RectangleTest(double x, double y, double width, double height) {
            this.x = x; 
            this.y = y; 
            this.width = width;
            this.height = height;   
        }

        public void setPosition(int x, int y) {
            this.x = x;
            this.y = y;
        }

        void dessiner(Graphics g) {
            g.setColor(Color.BLUE);
            g.fillRect((int)x, (int)y, (int)width, (int)height);
            g.setColor(Color.RED);
            g.drawString("Génie Logiciel", (int)(x+15), (int)(y+15));
        }
        
        void effacer(Graphics g) {
            g.setColor(Color.WHITE);
            g.fillRect((int)x, (int)y, (int)width, (int)height);
        }
    }
}