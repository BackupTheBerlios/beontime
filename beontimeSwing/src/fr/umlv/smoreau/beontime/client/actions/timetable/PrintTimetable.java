package fr.umlv.smoreau.beontime.client.actions.timetable;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.JobAttributes;
import java.awt.PageAttributes;
import java.awt.PrintJob;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.event.ActionEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;


import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;

/**
 * @author BeOnTime
 */
public class PrintTimetable extends Action {
    private static final String NAME = "Imprimer l'emploi du temps";
    private static final String ICON = "imprimer.png";
    private static final String SMALL_ICON = "imprimer_small.png";
    private static final String KEY_STROKE = "ctrl P";


    public PrintTimetable(MainFrame mainFrame) {
        super(NAME, SMALL_ICON, ICON, KeyStroke.getKeyStroke(KEY_STROKE), mainFrame);
    }

    
    public static BufferedImage scale(BufferedImage bi, double scaleValue) {
        AffineTransform tx = new AffineTransform();
        tx.scale(scaleValue, scaleValue);
        AffineTransformOp op = new AffineTransformOp(tx,
                AffineTransformOp.TYPE_BILINEAR);
        BufferedImage biNew = new BufferedImage( (int) (bi.getWidth() * scaleValue),
                (int) (bi.getHeight() * scaleValue),
                bi.getType());
        return op.filter(bi, biNew);
                
}
    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
	
    	
    	
    	JobAttributes jobAttributes = new JobAttributes();
		jobAttributes.setDestination(JobAttributes.DestinationType.PRINTER);
		
		PageAttributes pageAttributes = new PageAttributes();
		pageAttributes.setOrientationRequested(PageAttributes.OrientationRequestedType.LANDSCAPE);
		pageAttributes.setColor(PageAttributes.ColorType.COLOR);
		pageAttributes.setOrigin(PageAttributes.OriginType.PRINTABLE);
		pageAttributes.setMedia(PageAttributes.MediaType.A4);
    	
    	
		
		
		
		
    	PrintJob pJob = mainFrame.getMainFrame().getToolkit().getPrintJob(mainFrame.getMainFrame(), "Impression",jobAttributes, pageAttributes);
		    
    	   
		    if (pJob != null)
		      {
		        Graphics pg = pJob.getGraphics();
		       
		        JFrame frame = ExportTimetable.organizeTask();
		        
		        frame.setVisible(true);
				
		        Image image;
		        
		        //number of day is 6
		        if (frame.getHeight() < 722)
		        	image  = ExportTimetable.getImage(frame.getContentPane()).getScaledInstance(728, -1, Image.SCALE_SMOOTH);
				else
					image  = ExportTimetable.getImage(frame.getContentPane()).getScaledInstance(670, -1, Image.SCALE_SMOOTH);
				
				
				
				frame.setVisible(false);
				
				BoTJPanelImageBg panel = new BoTJPanelImageBg(image);
				panel.setBackground(Color.WHITE);
				frame.setContentPane(panel);
				frame.setSize(image.getWidth(panel)+10, image.getHeight(panel)+34);
				
				frame.setVisible(true);
				
				frame.getContentPane().printAll(pg);
				
				frame.setVisible(false);
				
				MainFrame.getInstance().setView(MainFrame.getInstance().getView());
		        pg.dispose();
		        pJob.end();
		        
		      }

    	
    	
    }
    
    public class BoTJPanelImageBg extends JComponent
	{
		private TexturePaint texture; 
		private BufferedImage bufferedImage; 

		public static final int CENTRE = 0;
		public static final int TEXTURE = 1;

		BoTJPanelImageBg( Image image)
		{	
			this.bufferedImage = ExportTimetable.toBufferedImage(image);
			this.texture = new TexturePaint(bufferedImage,new Rectangle(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight())); 
		} 

		public void paintComponent(Graphics g)
		{	
					g.setColor(this.getBackground());
					g.fillRect(0,0,getWidth(), getHeight() );
					g.drawImage(bufferedImage,(getWidth()-bufferedImage.getWidth())/2,(getHeight()-bufferedImage.getHeight())/2,null);		
		}
	}
}