package fr.umlv.smoreau.beontime.client.graphics.windows;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.toedter.calendar.JDateChooser;

import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.dao.DaoManager;
import fr.umlv.smoreau.beontime.dao.UnavailabilityDao;
import fr.umlv.smoreau.beontime.filter.UnavailabilityFilter;
import fr.umlv.smoreau.beontime.model.Unavailability;
import fr.umlv.smoreau.beontime.model.element.Material;
import fr.umlv.smoreau.beontime.model.element.Room;


/**
 * @author BeOnTime
 */
public class SearchAvailabilityWindow {
	private static final String TITRE = "Rechercher une disponibilité";
	
	private JDateChooser dateAvailability;
	
	private JComboBox startAvailabilityHourJcb;
	private JComboBox startAvailabilityMinuteJcb;
	private JComboBox endAvailabilityHourJcb;
	private JComboBox endAvailabilityMinuteJcb;
	
	private JRadioButton localJrb;
	private JRadioButton equipmentJrb;
	
	private JTextArea resultTextArea;
	
	private JDialog SAWFrame;
	private GridBagLayout SAWLayout = new GridBagLayout();
	private GridBagConstraints layoutConstraints = new GridBagConstraints();
	
	
	public SearchAvailabilityWindow () {
		SAWFrame = new JDialog(MainFrame.getInstance().getMainFrame(), TITRE, true);
		SAWFrame.getContentPane().setLayout(SAWLayout);
		
		initSearchAvailabilityWindow();  
	}
	
	private Calendar getBeginDate() {
	    Calendar date = Calendar.getInstance();
	    date.setTime(dateAvailability.getDate());
	    date.set(Calendar.HOUR_OF_DAY, (new Integer((String) startAvailabilityHourJcb.getSelectedItem())).intValue());
	    date.set(Calendar.MINUTE, (new Integer((String) startAvailabilityMinuteJcb.getSelectedItem())).intValue());
	    date.set(Calendar.SECOND, 0);
	    date.set(Calendar.MILLISECOND, 0);
	    return date;
	}
	
	private Calendar getEndDate() {
	    Calendar date = Calendar.getInstance();
	    date.setTime(dateAvailability.getDate());
	    date.set(Calendar.HOUR_OF_DAY, (new Integer((String) endAvailabilityHourJcb.getSelectedItem())).intValue());
	    date.set(Calendar.MINUTE, (new Integer((String) endAvailabilityMinuteJcb.getSelectedItem())).intValue());
	    date.set(Calendar.SECOND, 0);
	    date.set(Calendar.MILLISECOND, 0);
	    return date;
	}
	
	private void initSearchAvailabilityWindow() {
	    JLabel chearchAvailabilityLabel = new JLabel("Rechercher la disponibilité d'un ");
		addComponent(SAWLayout,layoutConstraints,chearchAvailabilityLabel,1,1,4,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(30,10,15,10));
		SAWFrame.getContentPane().add(chearchAvailabilityLabel);
		
		localJrb = new JRadioButton("local",true);
		addComponent(SAWLayout,layoutConstraints,localJrb,5,1,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(30,10,20,10));
		SAWFrame.getContentPane().add(localJrb);
		
		equipmentJrb = new JRadioButton("matériel");
		addComponent(SAWLayout,layoutConstraints,equipmentJrb,6,1,2,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(30,10,20,10));
		SAWFrame.getContentPane().add(equipmentJrb);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(localJrb);
		buttonGroup.add(equipmentJrb);
		
		
		
		JLabel dateAvailabilityTheLabel = new JLabel("le");
		addComponent(SAWLayout,layoutConstraints,dateAvailabilityTheLabel,1,2,1,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(15,10,15,10));
		SAWFrame.getContentPane().add(dateAvailabilityTheLabel);
		
		dateAvailability = new JDateChooser();
		addComponent(SAWLayout,layoutConstraints,dateAvailability,2,2,2,1,0.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(5,10,15,10));
		SAWFrame.getContentPane().add(dateAvailability);
		
		
		
		
		JLabel dureeAvailabilityDeLabel = new JLabel("de");
		addComponent(SAWLayout,layoutConstraints,dureeAvailabilityDeLabel,1,3,1,1,0.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(15,10,15,10));
		SAWFrame.getContentPane().add(dureeAvailabilityDeLabel);
		
		startAvailabilityHourJcb = new JComboBox();
		initNumberJcb(startAvailabilityHourJcb, 0, 23);
		addComponent(SAWLayout,layoutConstraints,startAvailabilityHourJcb,2,3,1,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(5,10,15,10));
		SAWFrame.getContentPane().add(startAvailabilityHourJcb);
		
		JLabel startAvailabilityHourLabel = new JLabel("heure");
		addComponent(SAWLayout,layoutConstraints,startAvailabilityHourLabel,3,3,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(15,10,15,10));
		SAWFrame.getContentPane().add(startAvailabilityHourLabel);
		
		JLabel dureeAvailabilityALabel = new JLabel("à");
		addComponent(SAWLayout,layoutConstraints,dureeAvailabilityALabel,4,3,1,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(15,10,15,10));
		SAWFrame.getContentPane().add(dureeAvailabilityALabel);
		
		endAvailabilityHourJcb = new JComboBox();
		initNumberJcb(endAvailabilityHourJcb, 0, 23);
		endAvailabilityHourJcb.setSelectedIndex(23);
		addComponent(SAWLayout,layoutConstraints,endAvailabilityHourJcb,5,3,GridBagConstraints.RELATIVE,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(5,10,15,10));
		SAWFrame.getContentPane().add(endAvailabilityHourJcb);
		
		JLabel endAvailabilityHourLabel = new JLabel("heure");
		addComponent(SAWLayout,layoutConstraints,endAvailabilityHourLabel,6,3,GridBagConstraints.REMAINDER,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(15,10,15,10));
		SAWFrame.getContentPane().add(endAvailabilityHourLabel);
		
		
		
		startAvailabilityMinuteJcb = new JComboBox();
		initMinuteJcb(startAvailabilityMinuteJcb);
		addComponent(SAWLayout,layoutConstraints,startAvailabilityMinuteJcb,2,4,1,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(5,10,15,10));
		SAWFrame.getContentPane().add(startAvailabilityMinuteJcb);
		
		JLabel startAvailabilityMinuteLabel = new JLabel("min");
		addComponent(SAWLayout,layoutConstraints,startAvailabilityMinuteLabel,3,4,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(15,10,15,10));
		SAWFrame.getContentPane().add(startAvailabilityMinuteLabel);
		
		endAvailabilityMinuteJcb = new JComboBox();
		initMinuteJcb(endAvailabilityMinuteJcb);
		endAvailabilityMinuteJcb.setSelectedIndex(3); 
		addComponent(SAWLayout,layoutConstraints,endAvailabilityMinuteJcb,5,4,GridBagConstraints.RELATIVE,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(5,10,15,10));
		SAWFrame.getContentPane().add(endAvailabilityMinuteJcb);
		
		JLabel endAvailabilityMinuteLabel = new JLabel("min");
		addComponent(SAWLayout,layoutConstraints,endAvailabilityMinuteLabel,6,4,GridBagConstraints.REMAINDER,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(15,10,15,10));
		SAWFrame.getContentPane().add(endAvailabilityMinuteLabel);
		
		
		
		JButton searchButton = new JButton("Rechercher");
		searchButton.addActionListener(new ActionSearch());
		addComponent(SAWLayout,layoutConstraints,searchButton,6,5,2,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(15,10,15,10));
		SAWFrame.getContentPane().add(searchButton);
		
		
		
		JLabel result = new JLabel("Résultat :");
		addComponent(SAWLayout,layoutConstraints,result,1,6,2,1,1.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(10,10,0,10));
		SAWFrame.getContentPane().add(result);
		
		
		resultTextArea = new JTextArea();
		resultTextArea.setLineWrap(true);
		resultTextArea.setRows(4);
		resultTextArea.setEditable(false);
		JScrollPane pane= new JScrollPane(resultTextArea);
		addComponent(SAWLayout,layoutConstraints,pane,1,7,7,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,10,20,10));
		SAWFrame.getContentPane().add(pane);
		
		
		
		JButton annuler = new JButton("Annuler");
		annuler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	SAWFrame.dispose();
            }
		});
		addComponent(SAWLayout,layoutConstraints,annuler,6,8,2,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		SAWFrame.getContentPane().add(annuler);
	}
	
	
	private void initNumberJcb(JComboBox jcb, int start, int end) {
		for(int i=start;i<=end;i++) {
			jcb.addItem(""+i);
		}
	}
	
	private void initMinuteJcb(JComboBox jcb) {
		String [] tabMin = new String[] {"00","15","30","45"};
		
		for (int i=0;i<tabMin.length;i++) {
			jcb.addItem(tabMin[i]);
		}
	}
	
	
	/* (non-Javadoc)
	 * @see fr.umlv.smoreau.beontimeSwing.graphics.windows.Window#show(java.lang.Object[])
	 */
	public void show() {
		SAWFrame.pack();
		SAWFrame.setResizable(false);
		SAWFrame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		SAWFrame.setLocationRelativeTo(null);
		SAWFrame.setVisible(true);
	}
	
	private void addComponent(GridBagLayout gbLayout,GridBagConstraints constraints,Component comp,int gridx, int gridy, int gridwidth, int gridheight, double weightx, double weighty, int anchor, int fill, Insets insets) {
		constraints. gridx= gridx;
		constraints. gridy = gridy;
		constraints. gridwidth= gridwidth;
		constraints. gridheight = gridheight;
		constraints.weightx = weightx;
		constraints.weighty = weighty;
		constraints.anchor = anchor;
		constraints.fill = fill;
		constraints.insets = insets;
		
		gbLayout.setConstraints(comp,constraints);
	}
	
	
    private class ActionSearch implements ActionListener {
        /* (non-Javadoc)
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent arg0) {
            UnavailabilityDao unavailabilityDao = DaoManager.getAvailabilityDao();
            
            try {
                boolean isRoom = localJrb.isSelected();
                ArrayList elements = null;

	            UnavailabilityFilter filter = new UnavailabilityFilter();
	            if (isRoom) {
	    			filter.setIdUnavailabilityType(unavailabilityDao.getTypeUnavailability(UnavailabilityDao.TYPE_ROOM));
	    			elements = (ArrayList) DaoManager.getElementDao().getRooms();
	            } else {
	                filter.setIdUnavailabilityType(unavailabilityDao.getTypeUnavailability(UnavailabilityDao.TYPE_MATERIAL));
	                elements = (ArrayList) DaoManager.getElementDao().getMaterials();
	            }
	            
	            Collection unavailabilities = unavailabilityDao.getUnavailabilities(filter);
	            Calendar beginDate = getBeginDate();
	            Calendar endDate = getEndDate();
	            
	            for (Iterator i = unavailabilities.iterator(); i.hasNext(); ) {
	                Unavailability unavailability = (Unavailability) i.next();
	                
	                if ((beginDate.getTimeInMillis() < unavailability.getBeginDate().getTimeInMillis() && endDate.getTimeInMillis() > unavailability.getBeginDate().getTimeInMillis()) ||
	                        (beginDate.getTimeInMillis() < unavailability.getEndDate().getTimeInMillis() && endDate.getTimeInMillis() > unavailability.getEndDate().getTimeInMillis()) ||
	                        (beginDate.getTimeInMillis() >= unavailability.getBeginDate().getTimeInMillis() && endDate.getTimeInMillis() <= unavailability.getEndDate().getTimeInMillis()) ||
	                        (beginDate.getTimeInMillis() <= unavailability.getBeginDate().getTimeInMillis() && endDate.getTimeInMillis() >= unavailability.getEndDate().getTimeInMillis())) {
	                    Object del = null;
	                    if (isRoom)
	                        del = new Room(unavailability.getIdUnavailabilitySubject());
	                    else
	                        del = new Material(unavailability.getIdUnavailabilitySubject());
	                    elements.remove(del);
	                }
	            }
	            
	            StringBuffer result = new StringBuffer();
	            for (Iterator i = elements.iterator(); i.hasNext(); ) {
	                if (result.length() != 0)
                        result.append('\n');
	                if (isRoom) {
	                    Room room = (Room) i.next();
	                    result.append(room.getName());
	                } else {
	                    Material material = (Material) i.next();
	                    result.append(material.getName());
	                }
	            }
	            
	            resultTextArea.setText(result.toString());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Une erreur interne est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
                SAWFrame.dispose();
            }
        }
    }
}
