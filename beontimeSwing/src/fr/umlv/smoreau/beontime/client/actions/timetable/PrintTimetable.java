package fr.umlv.smoreau.beontime.client.actions.timetable;

import java.awt.Graphics;
import java.awt.JobAttributes;
import java.awt.PageAttributes;
import java.awt.PrintJob;
import java.awt.event.ActionEvent;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;

/**
 * @author BeOnTime
 */
public class PrintTimetable extends Action {
    private static final String NAME = "Imprimer l'emploi du temps";
    private static final String ICON = "imprimer.png";
    private static final String SMALL_ICON = "imprimer_small.png";


    public PrintTimetable(MainFrame mainFrame) {
        super(NAME, SMALL_ICON, ICON, mainFrame);
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
		
		PrintJob demandeDImpression = mainFrame.getTable().getToolkit().getPrintJob(mainFrame.getMainFrame(), "Impression", jobAttributes, pageAttributes);
		if (demandeDImpression != null) {
			Graphics gImpr = demandeDImpression.getGraphics();

			mainFrame.getTable().printAll(gImpr);

			gImpr.dispose();
			demandeDImpression.end();
		}
    }
}
