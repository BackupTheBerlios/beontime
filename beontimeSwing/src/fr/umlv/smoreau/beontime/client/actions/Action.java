package fr.umlv.smoreau.beontime.client.actions;

import java.net.URL;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import fr.umlv.smoreau.beontime.client.graphics.MainFrame;


/**
 * @author BeOnTime
 */
public abstract class Action extends AbstractAction {
    private static final String DIR_IMAGES = "images";
    
    protected MainFrame mainFrame;


    public Action(String name, String icon, MainFrame mainFrame) {
        super(name, getImage(icon));
        this.mainFrame = mainFrame;
    }
    
    public void setMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

	public static Icon getImage(String urli) {
	    if (urli != null) {
	        URL url = Action.class.getResource(DIR_IMAGES + "/" + urli);
	        if (url != null)
	            return new ImageIcon(url);
	    }
		return null;
	}
}
