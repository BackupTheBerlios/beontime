/*
 * Created on 22 févr. 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package fr.umlv.smoreau.beontime.client.graphics.parts;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Mohamed
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TimeTableViewPanelBar extends JPanel {

	private JLabel visuEDTLabel;
	private JComboBox jcbTypeEDT;
	private JComboBox jcbSubjectEDT;

	/**
	 * 
	 */
	public TimeTableViewPanelBar() {
		GridBagLayout visuEDTPanelLayout = new GridBagLayout();
	    GridBagConstraints layoutConstraints = new GridBagConstraints();
	    setLayout(visuEDTPanelLayout);
		visuEDTLabel = new JLabel("Visualiser un emploi du temps");
		addComponent(visuEDTPanelLayout,layoutConstraints,visuEDTLabel,1,GridBagConstraints.REMAINDER,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(10,10,10,10));
		add(visuEDTLabel);
		jcbTypeEDT = new JComboBox();
		jcbTypeEDT.addItem("Formation");
		jcbTypeEDT.addItem("Enseignant");
		jcbTypeEDT.addItem("Groupe");
		jcbTypeEDT.addItem("Local");
		jcbTypeEDT.addItem("Materiel");
		addComponent(visuEDTPanelLayout,layoutConstraints,jcbTypeEDT,1,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(10,10,5,10));
		add(jcbTypeEDT);
		jcbSubjectEDT = new JComboBox();
		addComponent(visuEDTPanelLayout,layoutConstraints,jcbSubjectEDT,1,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,10,10,10));
		add(jcbSubjectEDT);
	}
	public void addComponent(GridBagLayout gbLayout,GridBagConstraints constraints,Component comp,int gridwidth, int gridheight, double weightx, double weighty, int anchor, int fill, Insets insets) {
        constraints. gridwidth= gridwidth;
        constraints. gridheight = gridheight;
        constraints.weightx = weightx;
        constraints.weighty = weighty;
        constraints.anchor = anchor;
        constraints.fill = fill;
        constraints.insets = insets;
 
        gbLayout.setConstraints(comp,constraints);
    }
}
