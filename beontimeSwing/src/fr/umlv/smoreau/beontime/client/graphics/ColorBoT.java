package fr.umlv.smoreau.beontime.client.graphics;

import java.awt.Color;
import java.util.HashSet;

/**
 * @author BeOnTime
 */
public class ColorBoT {
	private static HashSet colors;
	
	static {
		colors = new HashSet();
		
		colors.add(new Color(255, 255, 153));
		colors.add(new Color(255, 255, 102));
		colors.add(new Color(255, 204, 102));
		colors.add(new Color(255, 153, 102));
		colors.add(new Color(255, 204, 153));
		colors.add(new Color(255, 204, 255));
		colors.add(new Color(255, 153, 255));
		colors.add(new Color(255, 153, 153));
		colors.add(new Color(204, 255, 255));
		colors.add(new Color(153, 204, 255));
		colors.add(new Color(204, 204, 255));
		colors.add(new Color(204, 153, 255));
		colors.add(new Color(204, 102, 255));
		colors.add(new Color(204, 255, 153));
		colors.add(new Color(153, 255, 102));
		colors.add(new Color(102, 255, 102));
		colors.add(new Color(204, 204, 102));
		colors.add(new Color(204, 153, 102));
		colors.add(new Color(204, 204, 153));
		colors.add(new Color(204, 204, 204));
	}

	public static Color getColorAt(int index){
		if (index > colors.size())
		    return getColorAt(index - colors.size());
		
		return (Color) colors.toArray()[index];
	}
}
