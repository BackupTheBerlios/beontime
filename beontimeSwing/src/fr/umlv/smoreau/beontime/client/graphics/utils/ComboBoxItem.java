package fr.umlv.smoreau.beontime.client.graphics.utils;


/**
 * @author BeOnTime
 */
public class ComboBoxItem {
    private String text;
	private Long id;
	
	public ComboBoxItem(String text, Long id) {
		this.text = text;
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
	public String toString() {
		return text;
	}
	
	public boolean equals(Object obj) {
	    if (obj == null || !(obj instanceof ComboBoxItem))
	        return false;

	    ComboBoxItem item = (ComboBoxItem) obj;

	    return id.equals(item.id);
	}
}
