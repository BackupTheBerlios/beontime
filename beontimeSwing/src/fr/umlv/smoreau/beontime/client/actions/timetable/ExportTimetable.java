/*
 * 
 */
package fr.umlv.smoreau.beontime.client.actions.timetable;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;

/**
 * @author BeOnTime
 */
public class ExportTimetable extends Action {
	private static final String NAME = "Exporter l'emploi du temps";
	private static final String ICON = "exporter.png";
	private static final String SMALL_ICON = "exporter_small.png";
	
	
	public ExportTimetable(MainFrame mainFrame) {
		super(NAME, SMALL_ICON, ICON, mainFrame);
	}
	
	
	public static Image getImage(Component component){
		if(component==null){return null;}
		int width = component.getWidth();
		int height = component.getHeight();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = image.createGraphics();
		component.paintAll(g);
		g.dispose();
		return image;
	}
	
	public static BufferedImage toBufferedImage(Image image) {
		/** On test si l'image n'est pas déja une instance de BufferedImage */
		if( image instanceof BufferedImage ) {
			/** cool, rien à faire */
			return( (BufferedImage)image );
		} else {
			/** On s'assure que l'image est complètement chargée */
			image = new ImageIcon(image).getImage();
			
			/** On crée la nouvelle image */
			BufferedImage bufferedImage = new BufferedImage(
					image.getWidth(null),
					image.getHeight(null),
					BufferedImage.TYPE_INT_RGB );
			Graphics g = bufferedImage.createGraphics();
			g.drawImage(image,0,0,null);
			g.dispose();
			
			return( bufferedImage );
		} 
	}
	
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		JFileChooser EEDTFrame = new JFileChooser();
		
		BOTFileFilter filterJPEG = new BOTFileFilter(new String[] {"jpeg", "jpg"}, "JPEG Images");
		BOTFileFilter filterPNG = new BOTFileFilter("png", "PNG Images");
		BOTFileFilter filterTXT= new BOTFileFilter("txt", "TEXT FILE ");
		BOTFileFilter filterXML = new BOTFileFilter("xml", "XML FILE");
		BOTFileFilter filter = new BOTFileFilter(new String[] {"png", "jpeg", "txt","xml"}, "Images JPEG & PNG Images, Fichiers TXT & XML ");
		
		EEDTFrame.addChoosableFileFilter(filterJPEG);
		EEDTFrame.addChoosableFileFilter(filterPNG);
		EEDTFrame.addChoosableFileFilter(filterTXT);
		EEDTFrame.addChoosableFileFilter(filterXML);
		EEDTFrame.addChoosableFileFilter(filter);
		
		int returnVal = EEDTFrame.showSaveDialog(MainFrame.getInstance().getMainFrame());
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			
			System.out.println("You chose to save this file: " + EEDTFrame.getSelectedFile().getName());
			
			String formatName = null;
			
			if(EEDTFrame.getSelectedFile().getName().endsWith("txt")) {
				formatName = "txt";
				return;
			}
				
			else if(EEDTFrame.getSelectedFile().getName().endsWith("xml")) {
				formatName = "xml";
				return;
			}
				
			else if(EEDTFrame.getSelectedFile().getName().endsWith("jpeg"))
				formatName = "image/jpeg";
			
				
			else if(EEDTFrame.getSelectedFile().getName().endsWith("jpg"))
				formatName = "jpg";
			
			else if(EEDTFrame.getSelectedFile().getName().endsWith("png"))
				formatName = "png";
			
			
			
			Image image = ExportTimetable.getImage(MainFrame.getInstance().getView().getJScrollPane());
			try {
				
				
				/* Possibilité d'exporter en "gif", "jpg", "bmp", "image/jpeg" */
				ImageIO.write(ExportTimetable.toBufferedImage(image), formatName, EEDTFrame.getSelectedFile());
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	}
	
	
	
	
	
	
	private static class BOTFileFilter extends FileFilter {
		
		private static String TYPE_UNKNOWN = "Type Unknown";
		private static String HIDDEN_FILE = "Hidden File";
		
		private Hashtable filters = null;
		private String description = null;
		private String fullDescription = null;
		private boolean useExtensionsInDescription = true;
		
		/**
		 * Creates a file filter. If no filters are added, then all
		 * files are accepted.
		 *
		 * @see #addExtension
		 */
		public BOTFileFilter() {
			this.filters = new Hashtable();
		}
		
		/**
		 * Creates a file filter that accepts files with the given extension.
		 * Example: new BOTFileFilter("jpg");
		 *
		 * @see #addExtension
		 */
		public BOTFileFilter(String extension) {
			this(extension,null);
		}
		
		/**
		 * Creates a file filter that accepts the given file type.
		 * Example: new BOTFileFilter("jpg", "JPEG Image Images");
		 *
		 * Note that the "." before the extension is not needed. If
		 * provided, it will be ignored.
		 *
		 * @see #addExtension
		 */
		public BOTFileFilter(String extension, String description) {
			this();
			if(extension!=null) addExtension(extension);
			if(description!=null) setDescription(description);
		}
		
		/**
		 * Creates a file filter from the given string array.
		 * Example: new BOTFileFilter(String {"gif", "jpg"});
		 *
		 * Note that the "." before the extension is not needed adn
		 * will be ignored.
		 *
		 * @see #addExtension
		 */
		public BOTFileFilter(String[] filters) {
			this(filters, null);
		}
		
		/**
		 * Creates a file filter from the given string array and description.
		 * Example: new BOTFileFilter(String {"gif", "jpg"}, "Gif and JPG Images");
		 *
		 * Note that the "." before the extension is not needed and will be ignored.
		 *
		 * @see #addExtension
		 */
		public BOTFileFilter(String[] filters, String description) {
			this();
			for (int i = 0; i < filters.length; i++) {
				// add filters one by one
				addExtension(filters[i]);
			}
			if(description!=null) setDescription(description);
		}
		
		/**
		 * Return true if this file should be shown in the directory pane,
		 * false if it shouldn't.
		 *
		 * Files that begin with "." are ignored.
		 *
		 * @see #getExtension
		 * @see FileFilter#accepts
		 */
		public boolean accept(File f) {
			if(f != null) {
				if(f.isDirectory()) {
					return true;
				}
				String extension = getExtension(f);
				if(extension != null && filters.get(getExtension(f)) != null) {
					return true;
				};
			}
			return false;
		}
		
		/**
		 * Return the extension portion of the file's name .
		 *
		 * @see #getExtension
		 * @see FileFilter#accept
		 */
		public String getExtension(File f) {
			if(f != null) {
				String filename = f.getName();
				int i = filename.lastIndexOf('.');
				if(i>0 && i<filename.length()-1) {
					return filename.substring(i+1).toLowerCase();
				};
			}
			return null;
		}
		
		/**
		 * Adds a filetype "dot" extension to filter against.
		 *
		 * For example: the following code will create a filter that filters
		 * out all files except those that end in ".jpg" and ".tif":
		 *
		 *   BOTFileFilter filter = new BOTFileFilter();
		 *   filter.addExtension("jpg");
		 *   filter.addExtension("tif");
		 *
		 * Note that the "." before the extension is not needed and will be ignored.
		 */
		public void addExtension(String extension) {
			if(filters == null) {
				filters = new Hashtable(5);
			}
			filters.put(extension.toLowerCase(), this);
			fullDescription = null;
		}
		
		
		/**
		 * Returns the human readable description of this filter. For
		 * example: "JPEG and GIF Image Files (*.jpg, *.gif)"
		 *
		 * @see setDescription
		 * @see setExtensionListInDescription
		 * @see isExtensionListInDescription
		 * @see FileFilter#getDescription
		 */
		public String getDescription() {
			if(fullDescription == null) {
				if(description == null || isExtensionListInDescription()) {
					fullDescription = description==null ? "(" : description + " (";
					// build the description from the extension list
					Enumeration extensions = filters.keys();
					if(extensions != null) {
						fullDescription += "." + (String) extensions.nextElement();
						while (extensions.hasMoreElements()) {
							fullDescription += ", ." + (String) extensions.nextElement();
						}
					}
					fullDescription += ")";
				} else {
					fullDescription = description;
				}
			}
			return fullDescription;
		}
		
		/**
		 * Sets the human readable description of this filter. For
		 * example: filter.setDescription("Gif and JPG Images");
		 *
		 * @see setDescription
		 * @see setExtensionListInDescription
		 * @see isExtensionListInDescription
		 */
		public void setDescription(String description) {
			this.description = description;
			fullDescription = null;
		}
		
		/**
		 * Determines whether the extension list (.jpg, .gif, etc) should
		 * show up in the human readable description.
		 *
		 * Only relevent if a description was provided in the constructor
		 * or using setDescription();
		 *
		 * @see getDescription
		 * @see setDescription
		 * @see isExtensionListInDescription
		 */
		public void setExtensionListInDescription(boolean b) {
			useExtensionsInDescription = b;
			fullDescription = null;
		}
		
		/**
		 * Returns whether the extension list (.jpg, .gif, etc) should
		 * show up in the human readable description.
		 *
		 * Only relevent if a description was provided in the constructor
		 * or using setDescription();
		 *
		 * @see getDescription
		 * @see setDescription
		 * @see setExtensionListInDescription
		 */
		public boolean isExtensionListInDescription() {
			return useExtensionsInDescription;
		}
	}
}
