package fr.umlv.smoreau.beontime.client.actions;

import java.net.URL;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

import fr.umlv.smoreau.beontime.client.graphics.MainFrame;


/**
 * @author BeOnTime
 */
public abstract class Action extends AbstractAction {
    private static final String DIR_IMAGES = "images";
    
    public static final String ICON = "icon";
    
    protected MainFrame mainFrame;

    
    public Action(String name, String icon, MainFrame mainFrame) {
        super(name, getImage(icon));
        putValue(ICON, getImage(icon));
        this.mainFrame = mainFrame;
    }
    
    public Action(String name, String smallIcon, String icon, MainFrame mainFrame) {
        this(name, smallIcon, icon, null, mainFrame);
    }

    public Action(String name, String smallIcon, String icon, KeyStroke keyStroke, MainFrame mainFrame) {
        super(name, getImage(smallIcon));
        putValue(ICON, getImage(icon));
        putValue(Action.ACCELERATOR_KEY, keyStroke);
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
	
	public void setSmallIcon(boolean small) {
	    putValue(Action.SMALL_ICON, getImage("ajouter_matiere_small.png"));
	}
}
