/*
 * 
 */
package fr.umlv.smoreau.beontime.client.graphics.event;

import java.util.*;

/**
 * @author BeOnTime
 */
public interface BoTListener extends EventListener {
	public void refresh(BoTEvent e) throws InterruptedException;
}
