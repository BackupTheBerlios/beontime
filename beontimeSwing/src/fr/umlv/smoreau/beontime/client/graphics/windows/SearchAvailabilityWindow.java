/*
 * 
 */
package fr.umlv.smoreau.beontime.client.graphics.windows;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.toedter.calendar.JDateChooser;

import fr.umlv.smoreau.beontime.client.graphics.MainFrame;

/**
 * @author BeOnTime
 */
public class SearchAvailabilityWindow {
	
	private static final String TITRE = "Rechercher une disponibilité";
	
	private JLabel chearchAvailabilityLabel;	
	private JLabel dateAvailabilityTheLabel;
	private JLabel startAvailabilityHourLabel;
	private JLabel startAvailabilityMinuteLabel;
	private JLabel endAvailabilityHourLabel;
	private JLabel endAvailabilityMinuteLabel;
	private JLabel dureeAvailabilityDeLabel;
	private JLabel dureeAvailabilityALabel;
	private JLabel result;
	
	private JDateChooser dateAvailability;
	
	private JComboBox startAvailabilityHourJcb;
	private JComboBox startAvailabilityMinuteJcb;
	private JComboBox endAvailabilityHourJcb;
	private JComboBox endAvailabilityMinuteJcb;
	
	private JRadioButton localJrb;
	private JRadioButton equipmentJrb;
	
	private JTextArea resultTextArea;
	
	private JButton chearchButton;
	private JButton ok;
	private JButton annuler;
	
	private JDialog SAWFrame;
	private GridBagLayout SAWLayout = new GridBagLayout();
	private GridBagConstraints layoutConstraints = new GridBagConstraints();
	
	
	public SearchAvailabilityWindow () {
		
		SAWFrame = new JDialog(MainFrame.getInstance().getMainFrame(), TITRE, true);
		SAWFrame.getContentPane().setLayout(SAWLayout);
		
		initSearchAvailabilityWindow();  
		
	}
	
	public String getSelectedElement() {
		
		if (localJrb.isSelected()) 
			return "local";
		else 
			return "materiel";
	}
	
	public Date getDateAvailability() {
		return dateAvailability.getDate();
	}
	
	public String getDateAvailabilityString() {
		return dateAvailability.getDateFormatString();
	}
	
	public String getStartAvailabilityHour() {
		//je ne sais pas encore sous quelle forme on aura besoin de le reccupérer
		return null;
	}
	
	public String getEndAvailabilityHour() {
        //je ne sais pas encore sous quelle forme on aura besoin de le reccupérer
		return null;
	}
	
	
	
	private void initSearchAvailabilityWindow() {
		
		
		chearchAvailabilityLabel = new JLabel("Rechercher la disponibilité d'un ");
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
		
		
		
		dateAvailabilityTheLabel = new JLabel("le");
		addComponent(SAWLayout,layoutConstraints,dateAvailabilityTheLabel,1,2,1,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(15,10,15,10));
		SAWFrame.getContentPane().add(dateAvailabilityTheLabel);
		
		dateAvailability = new JDateChooser();
		addComponent(SAWLayout,layoutConstraints,dateAvailability,2,2,2,1,0.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(5,10,15,10));
		SAWFrame.getContentPane().add(dateAvailability);
		
		
		
		
		dureeAvailabilityDeLabel = new JLabel("de");
		addComponent(SAWLayout,layoutConstraints,dureeAvailabilityDeLabel,1,3,1,1,0.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(15,10,15,10));
		SAWFrame.getContentPane().add(dureeAvailabilityDeLabel);
		
		startAvailabilityHourJcb = new JComboBox();
		initNumberJcb(startAvailabilityHourJcb, 0, 23);
		addComponent(SAWLayout,layoutConstraints,startAvailabilityHourJcb,2,3,1,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(5,10,15,10));
		SAWFrame.getContentPane().add(startAvailabilityHourJcb);
		
		startAvailabilityHourLabel = new JLabel("heure");
		addComponent(SAWLayout,layoutConstraints,startAvailabilityHourLabel,3,3,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(15,10,15,10));
		SAWFrame.getContentPane().add(startAvailabilityHourLabel);
		
		dureeAvailabilityALabel = new JLabel("à");
		addComponent(SAWLayout,layoutConstraints,dureeAvailabilityALabel,4,3,1,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(15,10,15,10));
		SAWFrame.getContentPane().add(dureeAvailabilityALabel);
		
		endAvailabilityHourJcb = new JComboBox();
		initNumberJcb(endAvailabilityHourJcb, 0, 23);
		endAvailabilityHourJcb.setSelectedIndex(23);
		addComponent(SAWLayout,layoutConstraints,endAvailabilityHourJcb,5,3,GridBagConstraints.RELATIVE,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(5,10,15,10));
		SAWFrame.getContentPane().add(endAvailabilityHourJcb);
		
		endAvailabilityHourLabel = new JLabel("heure");
		addComponent(SAWLayout,layoutConstraints,endAvailabilityHourLabel,6,3,GridBagConstraints.REMAINDER,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(15,10,15,10));
		SAWFrame.getContentPane().add(endAvailabilityHourLabel);
		
		
		
		startAvailabilityMinuteJcb = new JComboBox();
		initMinuteJcb(startAvailabilityMinuteJcb);
		addComponent(SAWLayout,layoutConstraints,startAvailabilityMinuteJcb,2,4,1,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(5,10,15,10));
		SAWFrame.getContentPane().add(startAvailabilityMinuteJcb);
		
		startAvailabilityMinuteLabel = new JLabel("min");
		addComponent(SAWLayout,layoutConstraints,startAvailabilityMinuteLabel,3,4,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(15,10,15,10));
		SAWFrame.getContentPane().add(startAvailabilityMinuteLabel);
		
		endAvailabilityMinuteJcb = new JComboBox();
		initMinuteJcb(endAvailabilityMinuteJcb);
		endAvailabilityMinuteJcb.setSelectedIndex(3); 
		addComponent(SAWLayout,layoutConstraints,endAvailabilityMinuteJcb,5,4,GridBagConstraints.RELATIVE,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(5,10,15,10));
		SAWFrame.getContentPane().add(endAvailabilityMinuteJcb);
		
		endAvailabilityMinuteLabel = new JLabel("min");
		addComponent(SAWLayout,layoutConstraints,endAvailabilityMinuteLabel,6,4,GridBagConstraints.REMAINDER,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(15,10,15,10));
		SAWFrame.getContentPane().add(endAvailabilityMinuteLabel);
		
		
		
		chearchButton = new JButton("Rechercher");
		addComponent(SAWLayout,layoutConstraints,chearchButton,6,5,2,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(15,10,15,10));
		SAWFrame.getContentPane().add(chearchButton);
		
		
		
		result = new JLabel("Résultat :");
		addComponent(SAWLayout,layoutConstraints,result,1,6,2,1,1.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(10,10,0,10));
		SAWFrame.getContentPane().add(result);
		
		
		resultTextArea = new JTextArea();
		resultTextArea.setLineWrap(true);
		resultTextArea.setRows(4);
		JScrollPane pane= new JScrollPane(resultTextArea);
		addComponent(SAWLayout,layoutConstraints,pane,1,7,7,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,10,20,10));
		SAWFrame.getContentPane().add(pane);
		
		
		
		annuler = new JButton("Annuler");
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
	
	
	public static void main(String[] args){
		
		MainFrame frame = MainFrame.getInstance();
		frame.open();
		
		SearchAvailabilityWindow form = new SearchAvailabilityWindow();
		form.show();
		
	}
}
