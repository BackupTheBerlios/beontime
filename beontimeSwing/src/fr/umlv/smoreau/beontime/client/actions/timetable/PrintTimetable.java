package fr.umlv.smoreau.beontime.client.actions.timetable;


import java.awt.event.ActionEvent;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.MessageFormat;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttribute;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Chromaticity;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.JTable;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.model.timetable.Timetable;

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
		/*JobAttributes jobAttributes = new JobAttributes();
		jobAttributes.setDestination(JobAttributes.DestinationType.PRINTER);
		
		PageAttributes pageAttributes = new PageAttributes();
		pageAttributes.setOrientationRequested(PageAttributes.OrientationRequestedType.LANDSCAPE);
		pageAttributes.setColor(PageAttributes.ColorType.COLOR);
		pageAttributes.setOrigin(PageAttributes.OriginType.PRINTABLE);
		pageAttributes.setMedia(PageAttributes.MediaType.A4);
		
		PrintJob demandeDImpression = mainFrame.getTable().getToolkit().getPrintJob(mainFrame.getMainFrame(), "Impression", jobAttributes, pageAttributes);
		if (demandeDImpression != null) {
			Graphics gImpr = demandeDImpression.getGraphics();
			
			//mainFrame.getTable().printAll(gImpr);
			
			MessageFormat headerFormat = new MessageFormat("aaaa");
			MessageFormat footerFormat = new MessageFormat("bbbb");
			 
			
			try {
		
				mainFrame.getTable().print(JTable.PrintMode.FIT_WIDTH, headerFormat, footerFormat);
			} catch (PrinterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			gImpr.dispose();
			demandeDImpression.end();
		}*/
    	
    	Timetable timetable = mainFrame.getModel().getTimetable();
    	StringBuffer header = new StringBuffer();
    	if (timetable.getFormation() != null) {
    	    header.append("Formation: ");
    	    header.append(timetable.getFormation().getHeading());
    	    header.append("\r\n");
    	    header.append("Responsable: ");
    	    header.append(timetable.getPersonInCharge().getName());
    	    header.append(" ");
    	    header.append(timetable.getPersonInCharge().getFirstName());
    	    header.append("\r\nEmploi du temps ");
    	    header.append(mainFrame.getTitleBar().getPeriod());
    	}
    	MessageFormat headerFormat = new MessageFormat(header.toString());
		MessageFormat footerFormat = new MessageFormat(mainFrame.getStateBar().getRemark()+"\r\nPage {0}");
    	
        try {
            
            Printable printable = mainFrame.getTable().getPrintable(JTable.PrintMode.FIT_WIDTH, headerFormat, footerFormat);

            PrinterJob job = PrinterJob.getPrinterJob();
            
            job.setPrintable(printable);

            PrintRequestAttributeSet attr = new HashPrintRequestAttributeSet(new PrintRequestAttribute[] { OrientationRequested.LANDSCAPE, Chromaticity.COLOR});
            
            boolean printAccepted = job.printDialog(attr);

            if (printAccepted)    
                job.print(attr);
            
        } catch (PrinterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        
    }
}
