/*
 * Created on 28 mars 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package fr.umlv.smoreau.beontime.client.graphics.windows;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.model.user.User;

/**
 * @author sandrine
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ModifyFormationWindow {

	private static final String TITRE = "Modifier une formation";
	
	private JLabel entitleLabel;
	private JLabel secretaryLabel;
	private JLabel nameSecretaryLabel;
	private JLabel teacherLabel;
	private JLabel startEndHalfYear1Label;
	private JLabel startHalfYear1Label;
	private JLabel endHalfYear1Label;
	private JLabel startEndHalfYear2Label;
	private JLabel startHalfYear2Label;
	private JLabel endHalfYear2Label;
	
	private JTextField entitleJtf;
	
	private JComboBox teacherJcb;
	
	private JDateChooser startHalfYear1Jc;
	private JDateChooser endHalfYear1Jc;
	private JDateChooser startHalfYear2Jc;
	private JDateChooser endHalfYear2Jc;
	
	private JButton ok;
	private JButton annuler;
	
	private boolean isOk;
	
	private JDialog MFWFrame;
	private GridBagLayout MFWLayout = new GridBagLayout();
	private GridBagConstraints layoutConstraints = new GridBagConstraints();
	
	private String[] teachersName;
	private User[] teachers;
	private MainFrame mainFrame;
	
	
	public ModifyFormationWindow() {
	    this.isOk = false;
	    
	    this.mainFrame = MainFrame.getInstance();
	    MFWFrame = new JDialog(MainFrame.getInstance().getMainFrame(), TITRE, true);
		MFWFrame.getContentPane().setLayout(MFWLayout);
		
		initModifyFormationWindow();
	}
	
	
	public String getEntitleFormation() {
		
		return entitleJtf.getText();
	}
	public void setEntitleFormation(String entitle) {
		
		entitleJtf.setText("entitle");
	}
	
	public String getNameSecretaryLabel() {
		return nameSecretaryLabel.getText();
	}
	
	public void setNameSecretaryLabel(String name) {
		nameSecretaryLabel.setText(name);
	}
	
	public User getTeacherJcb() {
		return teachers[teacherJcb.getSelectedIndex()];
	}
	
	public void setIdTeacher(Long idTeacher) {
	    for (int i = 1; i < teachers.length; ++i)
	        if (teachers[i].getIdUser().equals(idTeacher)) {
	            teacherJcb.setSelectedIndex(i);
	            break;
	        }
	}
	
	private void initModifyFormationWindow() {
		
		
		entitleLabel = new JLabel("Intitulé de la formation :");
		addComponent(MFWLayout,layoutConstraints,entitleLabel,1,1,2,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(30,10,15,10));
		MFWFrame.getContentPane().add(entitleLabel);
		
		
		entitleJtf = new JTextField();
		addComponent(MFWLayout,layoutConstraints,entitleJtf,3,1,3,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(30,10,15,10));
		MFWFrame.getContentPane().add(entitleJtf);
		
		
		secretaryLabel = new JLabel("Secrétaire en charge de la formation :");
		addComponent(MFWLayout,layoutConstraints,secretaryLabel,1,2,3,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(30,10,15,10));
		MFWFrame.getContentPane().add(secretaryLabel);
		
		nameSecretaryLabel = new JLabel("inconnue");
		addComponent(MFWLayout,layoutConstraints,nameSecretaryLabel,4,2,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(30,10,15,10));
		MFWFrame.getContentPane().add(nameSecretaryLabel);
		
		teacherLabel = new JLabel("Responsable de la formation :");
		addComponent(MFWLayout,layoutConstraints,teacherLabel,1,3,3,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(30,10,15,10));
		MFWFrame.getContentPane().add(teacherLabel);
		
		
		teacherJcb = new JComboBox();
		addComponent(MFWLayout,layoutConstraints,teacherJcb,4,3,2,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,10,15,10));
		MFWFrame.getContentPane().add(teacherJcb);
		
	
		startEndHalfYear1Label = new JLabel("Date de début et de fin du 1er semestre :");
		addComponent(MFWLayout,layoutConstraints,startEndHalfYear1Label,1,4,4,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(30,10,15,10));
		MFWFrame.getContentPane().add(startEndHalfYear1Label);
		
		startHalfYear1Label = new JLabel("Début :");
		addComponent(MFWLayout,layoutConstraints,startHalfYear1Label,1,5,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(30,10,15,10));
		MFWFrame.getContentPane().add(startHalfYear1Label);
		
		
		startHalfYear1Jc = new JDateChooser();
		addComponent(MFWLayout,layoutConstraints,startHalfYear1Jc,2,5,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(30,10,15,10));
		MFWFrame.getContentPane().add(startHalfYear1Jc);
			
		endHalfYear1Label = new JLabel("Fin :");
		addComponent(MFWLayout,layoutConstraints,endHalfYear1Label,3,5,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(30,10,15,10));
		MFWFrame.getContentPane().add(endHalfYear1Label);
		
		endHalfYear1Jc = new JDateChooser();
		addComponent(MFWLayout,layoutConstraints,endHalfYear1Jc,4,5,2,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(30,10,15,10));
		MFWFrame.getContentPane().add(endHalfYear1Jc);
		
		
		startEndHalfYear2Label = new JLabel("Date de début et de fin du 2ème semestre :");
		addComponent(MFWLayout,layoutConstraints,startEndHalfYear2Label,1,6,3,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(30,10,15,10));
		MFWFrame.getContentPane().add(startEndHalfYear2Label);
		
		startHalfYear2Label = new JLabel("Début :");
		addComponent(MFWLayout,layoutConstraints,startHalfYear2Label,1,7,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(30,10,15,10));
		MFWFrame.getContentPane().add(startHalfYear2Label);
		
		startHalfYear2Jc = new JDateChooser();
		addComponent(MFWLayout,layoutConstraints,startHalfYear2Jc,2,7,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(30,10,15,10));
		MFWFrame.getContentPane().add(startHalfYear2Jc);
		
		endHalfYear2Label = new JLabel("Fin :");
		addComponent(MFWLayout,layoutConstraints,endHalfYear2Label,3,7,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(30,10,15,10));
		MFWFrame.getContentPane().add(endHalfYear2Label);
		
		endHalfYear2Jc = new JDateChooser();
		addComponent(MFWLayout,layoutConstraints,endHalfYear2Jc,4,7,2,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(30,10,15,10));
		MFWFrame.getContentPane().add(endHalfYear2Jc);
		
		
		ok = new JButton("OK");
		ok.addActionListener(new ActionOk());
		addComponent(MFWLayout,layoutConstraints,ok,4,8,GridBagConstraints.RELATIVE,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		MFWFrame.getContentPane().add(ok);
		
		annuler = new JButton("Annuler");
		annuler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	MFWFrame.dispose();
            }
		});
		addComponent(MFWLayout,layoutConstraints,annuler,5,8,GridBagConstraints.REMAINDER,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(20,10,10,10));
		MFWFrame.getContentPane().add(annuler);
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

    public void show() {
    	MFWFrame.pack();
    	MFWFrame.setResizable(false);
	    MFWFrame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	    MFWFrame.setLocationRelativeTo(null);
	    MFWFrame.setVisible(true);
    }
	
    
    public boolean isOk() {
     
    	return isOk;
    }	
	
    private class ActionOk implements ActionListener {
        /* (non-Javadoc)
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent arg0) {
           
        }
    }
    
   
 }
