/*
 * 
 */
package fr.umlv.smoreau.beontime.client.actions.timetable;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileFilter;


import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.model.element.Room;
import fr.umlv.smoreau.beontime.model.timetable.Course;
import fr.umlv.smoreau.beontime.model.timetable.Timetable;
import fr.umlv.smoreau.beontime.model.user.User;

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
	
	
	/**
	 * Write a line into a file
	 * @param line line to write in the file
	 */
	public static void writeLineFich(File file,String line) {
		
		try {
			FileWriter fichWriter  = new FileWriter(file,true);
			
			fichWriter.write(line);
			fichWriter.close();
		}
		catch(IOException ioe) {
			JOptionPane.showMessageDialog(null, "Une erreur interne est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	public static String createSubjectHeader(Timetable timetable) {
		
		StringBuffer header = new StringBuffer();
		if (timetable.getGroup() != null) {
			header.append("GROUPE : ");
			header.append(timetable.getGroup().getHeading());
		} else if (timetable.getFormation() != null) {
			header.append("FORMATION : ");
			header.append(timetable.getFormation().getHeading());
		} else if (timetable.getTeacher() != null) {
			header.append("ENSEIGNANT : ");
			header.append(timetable.getTeacher().getName());
			header.append(" ");
			header.append(timetable.getTeacher().getFirstName());
		} else if (timetable.getRoom() != null) {
			header.append("LOCAL: ");
			header.append(timetable.getRoom().getName());
		} else if (timetable.getMaterial() != null) {
			header.append("MATERIEL : ");
			header.append(timetable.getMaterial().getName());
		}
		
		return header.toString();
	}
	
	public static String createResponsableHeader(Timetable timetable) {
		
		StringBuffer header = new StringBuffer();
		
		header.append("RESPONSABLE : ");
		header.append(timetable.getPersonInCharge().getName());
		header.append(" ");
		header.append(timetable.getPersonInCharge().getFirstName());
		
		return header.toString();
	}
	
	public static String createDateHeader(Timetable timetable) {
		
		StringBuffer header = new StringBuffer();
		
		header.append("EMPLOI DU TEMPS DU ");
		Calendar beginPeriod = MainFrame.getInstance().getBeginPeriod();
		
		header.append(beginPeriod.get(Calendar.DAY_OF_MONTH)+" "+getMonth(beginPeriod)+" "+beginPeriod.get(Calendar.YEAR));
		//header.append(MainFrame.getInstance().getBeginPeriod().getTime());
		header.append(" AU ");
		
		Calendar endPeriod = MainFrame.getInstance().getEndPeriod();
		header.append(endPeriod.get(Calendar.DAY_OF_MONTH)+" "+getMonth(endPeriod)+" "+endPeriod.get(Calendar.YEAR));
		//header.append(MainFrame.getInstance().getEndPeriod().getTime());	
		
		
		
		return header.toString();
	}
	
	public static String createHeader(Timetable timetable) {
		
		StringBuffer header = new StringBuffer();
		if (timetable.getGroup() != null) {
			header.append("GROUPE : ");
			header.append(timetable.getGroup().getHeading());
		} else if (timetable.getFormation() != null) {
			header.append("FORMATION : ");
			header.append(timetable.getFormation().getHeading());
			header.append("\r\n");
			header.append("RESPONSABLE : ");
			header.append(timetable.getPersonInCharge().getName());
			header.append(" ");
			header.append(timetable.getPersonInCharge().getFirstName());
		} else if (timetable.getTeacher() != null) {
			header.append("ENSEIGNANT : ");
			header.append(timetable.getTeacher().getName());
			header.append(" ");
			header.append(timetable.getTeacher().getFirstName());
		} else if (timetable.getRoom() != null) {
			header.append("LOCAL: ");
			header.append(timetable.getRoom().getName());
		} else if (timetable.getMaterial() != null) {
			header.append("MATERIEL : ");
			header.append(timetable.getMaterial().getName());
		}
		
		header.append("\r\n\r\n\r\n\r\nEMPLOI DU TEMPS DU ");
		Calendar beginPeriod = MainFrame.getInstance().getBeginPeriod();
		
		header.append(beginPeriod.get(Calendar.DAY_OF_MONTH)+" "+getMonth(beginPeriod)+" "+beginPeriod.get(Calendar.YEAR));
		//header.append(MainFrame.getInstance().getBeginPeriod().getTime());
		header.append(" AU ");
		
		Calendar endPeriod = MainFrame.getInstance().getEndPeriod();
		header.append(endPeriod.get(Calendar.DAY_OF_MONTH)+" "+getMonth(endPeriod)+" "+endPeriod.get(Calendar.YEAR));
		//header.append(MainFrame.getInstance().getEndPeriod().getTime());	
		header.append("\r\n\r\n\r\n");
		
		
		return header.toString();
	}
	
	public static String getDay(Calendar calendar) {
		
		if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY)
			return "Lundi";
		
		else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY)
			return "Mardi";
		
		else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY)
			return "Marcredi";
		
		else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY)
			return "Jeudi";
		
		else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY)
			return "Vendredi";
		
		else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)
			return "Samedi";
		
		else 
			return "Dimanche";
		
		
		
		
	}
	
	public static String getMonth(Calendar calendar) {
		
		if (calendar.get(Calendar.MONTH) == Calendar.JANUARY)
			return "Janvier";
		
		else if (calendar.get(Calendar.MONTH) == Calendar.FEBRUARY)
			return "Fevrier";
		
		else if (calendar.get(Calendar.MONTH) == Calendar.MARCH)
			return "Mars";
		
		else if (calendar.get(Calendar.MONTH) == Calendar.APRIL)
			return "Avril";
		
		else if (calendar.get(Calendar.MONTH) == Calendar.MAY)
			return "Mai";
		
		else if (calendar.get(Calendar.MONTH) == Calendar.JUNE)
			return "Juin";
		
		else if (calendar.get(Calendar.MONTH) == Calendar.JULY)
			return "Juillet";
		
		else if (calendar.get(Calendar.MONTH) == Calendar.AUGUST)
			return "Aout";
		
		else if (calendar.get(Calendar.MONTH) == Calendar.SEPTEMBER)
			return "Septembre";
		
		else if (calendar.get(Calendar.MONTH) == Calendar.OCTOBER)
			return "Octobre";
		
		else if (calendar.get(Calendar.MONTH) == Calendar.NOVEMBER)
			return "Novembre";
		
		else
			return "Decembre";
		
		
		
	}
	
	
	
	public static String getHour(Calendar calendar) {
		
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		
		if (minute != 0)
			return hour+"H"+minute;
		else
			return hour+"H";
	}
	
	public static void exportTXT(File file) {
		
		Timetable timetable = MainFrame.getInstance().getModel().getTimetable();
		writeLineFich(file,ExportTimetable.createHeader(timetable));
		
		
		
		
		Collection courses = timetable.getCourses();
		
		
		
		Calendar beginPeriod = MainFrame.getInstance().getBeginPeriod();
		Calendar endPeriod = MainFrame.getInstance().getEndPeriod();	
		
		
		
		
		int diffDay = (beginPeriod.get(Calendar.DAY_OF_WEEK)) - (Calendar.MONDAY);
		for(int i=0; i<diffDay; i++) {
			beginPeriod.roll(Calendar.DATE, false);
		}
		
		
		
		/* write the timetable week by week */
		while (beginPeriod.compareTo(endPeriod) < 0) {
			
			Calendar beginPeriodCopy = Calendar.getInstance();
			beginPeriodCopy.setTime(beginPeriod.getTime());
			
			
			for(int i=1; i<7; i++) {
				beginPeriodCopy.roll(Calendar.DATE, true);
			}
			
			writeLineFich(file,"Semaine du "+beginPeriod.get(Calendar.DAY_OF_MONTH)+" "+getMonth(beginPeriod)+" "+beginPeriod.get(Calendar.YEAR)+" au "+beginPeriodCopy.get(Calendar.DAY_OF_MONTH)+" "+getMonth(beginPeriodCopy)+" "+beginPeriodCopy.get(Calendar.YEAR)+" : \r\n\r\n");
			
			
			for(int i=0; i<7; i++) {
				
				writeLineFich(file, getDay(beginPeriod).toUpperCase()+" "+beginPeriod.get(Calendar.DAY_OF_MONTH)+" : \r\n");
				boolean existCourse = false;
				
				for (Iterator it = courses.iterator(); it.hasNext(); ) {
					Course course = (Course) it.next();					
					
					
					if((course.getBeginDate().get(Calendar.DAY_OF_MONTH) == beginPeriod.get(Calendar.DAY_OF_MONTH)) && (course.getBeginDate().get(Calendar.MONTH) == beginPeriod.get(Calendar.MONTH)) && (course.getBeginDate().get(Calendar.YEAR) == beginPeriod.get(Calendar.YEAR))) {
						writeLineFich(file,course.getSubject().getHeading()+" ("+getHour(course.getBeginDate())+'-'+getHour(course.getEndDate())+")");
						
						Set teachers = course.getTeachers();
						if (!teachers.isEmpty()) {
							for (Iterator it2 = teachers.iterator(); it2.hasNext(); ) {
								User user = (User) it2.next();
								writeLineFich(file," - "+user.getName()+" ");
							}
						}
						
						
						Set rooms = course.getRooms();
						if (!rooms.isEmpty()) {
							for (Iterator it2 = rooms.iterator(); it2.hasNext(); ) {
								Room room = (Room) it2.next();
								writeLineFich(file," - "+room.getName()+" ");
							}
						}
						writeLineFich(file,"\r\n");
						existCourse = true;
					}  
				}
				
				if (!existCourse)
					writeLineFich(file,"Pas de cours\r\n");
				
				writeLineFich(file,"\r\n");
				beginPeriod.roll(Calendar.DATE, true);
			}
			
			
		}
		
		
	}
	
	
	public static JFrame organizeTask() {
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JLabel subjectHeader = new JLabel(createSubjectHeader(MainFrame.getInstance().getModel().getTimetable()), JLabel.CENTER);
		subjectHeader.setFont(new Font("Arial", Font.BOLD, 16));
		subjectHeader.setHorizontalTextPosition(JLabel.CENTER);
		
		panel.add(subjectHeader);
		panel.add(Box.createVerticalStrut(5));
		
		JLabel responsibleHeader = null;
		
		if (MainFrame.getInstance().getModel().getTimetable().getFormation() != null) {
			responsibleHeader = new JLabel( createResponsableHeader(MainFrame.getInstance().getModel().getTimetable()), JLabel.CENTER);
			responsibleHeader.setFont(new Font("Arial", Font.BOLD, 16));
			responsibleHeader.setHorizontalTextPosition(JLabel.CENTER);
			
			panel.add(responsibleHeader);
			panel.add(Box.createVerticalStrut(10));
		}
		
		JLabel dateheader = new JLabel( createDateHeader(MainFrame.getInstance().getModel().getTimetable()), JLabel.CENTER);
		dateheader.setFont(new Font("Arial", Font.BOLD, 16));
		dateheader.setHorizontalTextPosition(JLabel.CENTER);
		
		panel.add(dateheader,BorderLayout.CENTER);
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createEmptyBorder(10,0,20,0));
	
		
		JFrame frame = new JFrame();
		JScrollPane pane = MainFrame.getInstance().getView().getJScrollPane();
		
		
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		frame.getContentPane().add(pane, BorderLayout.CENTER);
		
		
		int nbDay = MainFrame.getInstance().getView() .getTable().getModel().getRowCount();
		
		if((nbDay ==6) && (responsibleHeader == null))
			frame.setSize(new Double(pane.getSize().getWidth()).intValue(), 537+107);
		else if((nbDay ==6) && (responsibleHeader != null))
			frame.setSize(new Double(pane.getSize().getWidth()).intValue(), 537+136);
		else if ((nbDay == 7) && (responsibleHeader == null))
			frame.setSize(new Double(pane.getSize().getWidth()).intValue(), 615+107);
		else if ((nbDay == 7) && (responsibleHeader != null))
			frame.setSize(new Double(pane.getSize().getWidth()).intValue(), 615+136);
		
		return frame;
	}
	
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		JFileChooser EEDTFrame = new JFileChooser();
		
		BOTFileFilter filterJPEG = new BOTFileFilter(new String[] {"jpeg", "jpg"}, "Images JPEG");
		BOTFileFilter filterPNG = new BOTFileFilter("png", "Images PNG");
		BOTFileFilter filterTXT= new BOTFileFilter("txt", "Fichiers TEXT");
		BOTFileFilter filter = new BOTFileFilter(new String[] {"png", "jpeg", "jpg", "txt"}, "Images JPEG & Images PNG, Fichiers TXT");
		
		EEDTFrame.addChoosableFileFilter(filterJPEG);
		EEDTFrame.addChoosableFileFilter(filterPNG);
		EEDTFrame.addChoosableFileFilter(filterTXT);
		EEDTFrame.addChoosableFileFilter(filter);
		
		int returnVal = EEDTFrame.showSaveDialog(MainFrame.getInstance().getMainFrame());
		
		
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			
			FileFilter selectedFilter = EEDTFrame.getFileFilter();
			
			
			String formatName = null;
			File file = EEDTFrame.getSelectedFile();
			
			try {
				
				if (selectedFilter.equals(filter)) {
					if( !(EEDTFrame.getSelectedFile().getName().endsWith(".txt")) && !(EEDTFrame.getSelectedFile().getName().endsWith(".png")) && !(EEDTFrame.getSelectedFile().getName().endsWith(".jpeg")) && !(EEDTFrame.getSelectedFile().getName().endsWith(".jpg")) ) {
						
						Object[] possibleValues = { "png", "jpeg", "jpg", "txt"};
						Object selectedValue = JOptionPane.showInputDialog(null,
								
								"Veuillez sélectionner un format d'exportation dans la liste suivante", "Format d'exportation",
								
								JOptionPane.INFORMATION_MESSAGE, null,
								
								possibleValues, possibleValues[0]);
						
						if(selectedValue == null)
							return;
						
						for(int i = 0, maxCounter = possibleValues.length;
						i < maxCounter; i++) {
							if(possibleValues[i].equals(selectedValue))
								formatName = (String)possibleValues[i];
						}
						
						file = new File(EEDTFrame.getCurrentDirectory(), EEDTFrame.getSelectedFile().getName().concat("."+formatName));			
					}
					
					else {
						
						int posPoint = EEDTFrame.getSelectedFile().getName().lastIndexOf('.');
						formatName = EEDTFrame.getSelectedFile().getName().substring(posPoint+1);
					}
					
					
				}
				
				else if (selectedFilter.equals(filterTXT)) {
					
					if(!EEDTFrame.getSelectedFile().getName().endsWith(".txt"))
						file = new File(EEDTFrame.getCurrentDirectory(), EEDTFrame.getSelectedFile().getName().concat(".txt"));
					
					formatName = "txt";
					
				}
				
				else if(selectedFilter.equals(filterJPEG)) {
					
					if(!(EEDTFrame.getSelectedFile().getName().endsWith(".jpeg")) && !(EEDTFrame.getSelectedFile().getName().endsWith(".jpg"))) {
						file = new File(EEDTFrame.getCurrentDirectory(), EEDTFrame.getSelectedFile().getName().concat(".jpeg"));
						formatName = "jpeg";
					}
					
					else if(EEDTFrame.getSelectedFile().getName().endsWith(".jpeg"))
						formatName = "jpeg";
					
					else
						formatName = "jpg";
					
				}
				
				else if(selectedFilter.equals(filterPNG)) {
					
					if(!EEDTFrame.getSelectedFile().getName().endsWith(".png")) {
						file = new File(EEDTFrame.getCurrentDirectory(), EEDTFrame.getSelectedFile().getName().concat(".png"));
					}
					formatName = "png";
				}
				
				
				
				if (formatName.compareTo("txt") == 0) {
					ExportTimetable.exportTXT(file);		
					return;
				}
				
				/*
				
				JPanel panel = new JPanel();
				panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
				
				JLabel subjectHeader = new JLabel(createSubjectHeader(MainFrame.getInstance().getModel().getTimetable()), JLabel.CENTER);
				subjectHeader.setFont(new Font("Arial", Font.BOLD, 16));
				subjectHeader.setHorizontalTextPosition(JLabel.CENTER);
				
				panel.add(subjectHeader);
				panel.add(Box.createVerticalStrut(5));
				
				JLabel responsibleHeader = null;
				
				if (MainFrame.getInstance().getModel().getTimetable().getFormation() != null) {
					responsibleHeader = new JLabel( createResponsableHeader(MainFrame.getInstance().getModel().getTimetable()), JLabel.CENTER);
					responsibleHeader.setFont(new Font("Arial", Font.BOLD, 16));
					responsibleHeader.setHorizontalTextPosition(JLabel.CENTER);
					
					panel.add(responsibleHeader);
					panel.add(Box.createVerticalStrut(10));
				}
				
				JLabel dateheader = new JLabel( createDateHeader(MainFrame.getInstance().getModel().getTimetable()), JLabel.CENTER);
				dateheader.setFont(new Font("Arial", Font.BOLD, 16));
				dateheader.setHorizontalTextPosition(JLabel.CENTER);
				
				panel.add(dateheader,BorderLayout.CENTER);
				panel.setBackground(Color.WHITE);
				panel.setBorder(BorderFactory.createEmptyBorder(10,0,20,0));
				
				
				
				JFrame frame = new JFrame();
				JScrollPane pane = MainFrame.getInstance().getView().getJScrollPane();
				
				
				frame.getContentPane().add(panel, BorderLayout.NORTH);
				frame.getContentPane().add(pane, BorderLayout.CENTER);
				
				
				int nbDay = MainFrame.getInstance().getView() .getTable().getModel().getRowCount();
				
				if((nbDay ==6) && (responsibleHeader == null))
					frame.setSize(new Double(pane.getSize().getWidth()).intValue(), 537+107);
				else if((nbDay ==6) && (responsibleHeader != null))
					frame.setSize(new Double(pane.getSize().getWidth()).intValue(), 537+136);
				else if ((nbDay == 7) && (responsibleHeader == null))
					frame.setSize(new Double(pane.getSize().getWidth()).intValue(), 615+107);
				else if ((nbDay == 7) && (responsibleHeader != null))
					frame.setSize(new Double(pane.getSize().getWidth()).intValue(), 615+136);*/
				
				JFrame frame = organizeTask();
				frame.setVisible(true);
				
				Image image = ExportTimetable.getImage(frame.getContentPane());
				frame.setVisible(false);
				ImageIO.write(ExportTimetable.toBufferedImage(image), formatName, file);
				MainFrame.getInstance().setView(MainFrame.getInstance().getView());
				
			} catch (IOException e) {		
				JOptionPane.showMessageDialog(null, "Une erreur interne est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
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
