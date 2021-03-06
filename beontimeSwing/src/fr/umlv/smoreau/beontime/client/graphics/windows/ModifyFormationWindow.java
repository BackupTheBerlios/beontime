package fr.umlv.smoreau.beontime.client.graphics.windows;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.toedter.calendar.JDateChooser;

import fr.umlv.smoreau.beontime.client.BoTConfig;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.client.graphics.utils.TextFieldBoT;
import fr.umlv.smoreau.beontime.dao.DaoManager;
import fr.umlv.smoreau.beontime.model.user.User;

/**
 * @author BeOnTime
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
	
	private TextFieldBoT intituleJtf;
	
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
		
		try {
            Collection t = DaoManager.getUserDao().getTeachers();
            if (t.size() == 0)
                throw new Exception();
            teachersName = new String[t.size()+1];
            teachers = new User[t.size()+1];
            teachersName[0] = "";
            int j = 1;
            for (Iterator i = t.iterator(); i.hasNext(); ++j) {
                teachers[j] = (User) i.next();
                teachersName[j] = teachers[j].getName() + " " + teachers[j].getFirstName();
            }
        } catch (Exception e) {
            teachersName = null;
            teachers = null;
        }
		
		initModifyFormationWindow();
	}
	
	
	public String getIntituleFormation() {
		return intituleJtf.getText();
	}

	public void setIntituleFormation(String intitule) {
	    intituleJtf.setText(intitule);
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
	
	public Calendar getStartHalfYear1() {
	    Calendar date = Calendar.getInstance();
	    date.setTime(startHalfYear1Jc.getDate());
	    return date;
	}
	
	public void setStartHalfYear1(Calendar date) {
	    if (date != null)
	        startHalfYear1Jc.setDate(date.getTime());
	}
	
	public Calendar getEndHalfYear1() {
	    Calendar date = Calendar.getInstance();
	    date.setTime(endHalfYear1Jc.getDate());
	    return date;
	}
	
	public void setEndHalfYear1(Calendar date) {
	    if (date != null)
	        endHalfYear1Jc.setDate(date.getTime());
	}
	
	public Calendar getStartHalfYear2() {
	    Calendar date = Calendar.getInstance();
	    date.setTime(startHalfYear2Jc.getDate());
	    return date;
	}
	
	public void setStartHalfYear2(Calendar date) {
	    if (date != null)
	        startHalfYear2Jc.setDate(date.getTime());
	}
	
	public Calendar getEndHalfYear2() {
	    Calendar date = Calendar.getInstance();
	    date.setTime(endHalfYear2Jc.getDate());
	    return date;
	}
	
	public void setEndHalfYear2(Calendar date) {
	    if (date != null)
	        endHalfYear2Jc.setDate(date.getTime());
	}
	
	private void initModifyFormationWindow() {
		entitleLabel = new JLabel("Intitul� de la formation :");
		addComponent(MFWLayout,layoutConstraints,entitleLabel,1,1,2,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(30,10,15,10));
		MFWFrame.getContentPane().add(entitleLabel);
		
		
		intituleJtf = new TextFieldBoT(20);
		addComponent(MFWLayout,layoutConstraints,intituleJtf,3,1,3,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(30,10,15,10));
		MFWFrame.getContentPane().add(intituleJtf);
		
		
		secretaryLabel = new JLabel("Secr�taire en charge de la formation :");
		addComponent(MFWLayout,layoutConstraints,secretaryLabel,1,2,3,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(30,10,15,10));
		MFWFrame.getContentPane().add(secretaryLabel);
		
		nameSecretaryLabel = new JLabel("inconnue");
		addComponent(MFWLayout,layoutConstraints,nameSecretaryLabel,4,2,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(30,10,15,10));
		MFWFrame.getContentPane().add(nameSecretaryLabel);
		
		teacherLabel = new JLabel("Responsable de la formation :");
		addComponent(MFWLayout,layoutConstraints,teacherLabel,1,3,3,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(30,10,15,10));
		MFWFrame.getContentPane().add(teacherLabel);
		
		
		teacherJcb = new JComboBox(teachersName);
		addComponent(MFWLayout,layoutConstraints,teacherJcb,4,3,2,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,10,15,10));
		MFWFrame.getContentPane().add(teacherJcb);
		
	
		startEndHalfYear1Label = new JLabel("Date de d�but et de fin du 1er semestre :");
		addComponent(MFWLayout,layoutConstraints,startEndHalfYear1Label,1,4,4,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(30,10,15,10));
		MFWFrame.getContentPane().add(startEndHalfYear1Label);
		
		startHalfYear1Label = new JLabel("D�but :");
		addComponent(MFWLayout,layoutConstraints,startHalfYear1Label,1,5,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(30,10,15,10));
		MFWFrame.getContentPane().add(startHalfYear1Label);
		
		
		startHalfYear1Jc = new JDateChooser();
        startHalfYear1Jc.setDate(BoTConfig.getBeginFirstHalfYear());
		startHalfYear1Jc.setDateFormatString("dd MMMMM");
		startHalfYear1Jc.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent arg0) {
                startHalfYear1Jc.revalidate();
            }
		});
		addComponent(MFWLayout,layoutConstraints,startHalfYear1Jc,2,5,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(30,10,15,10));
		MFWFrame.getContentPane().add(startHalfYear1Jc);
			
		endHalfYear1Label = new JLabel("Fin :");
		addComponent(MFWLayout,layoutConstraints,endHalfYear1Label,3,5,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(30,10,15,10));
		MFWFrame.getContentPane().add(endHalfYear1Label);
		
		endHalfYear1Jc = new JDateChooser();
		endHalfYear1Jc.setDate(BoTConfig.getEndFirstHalfYear());
        endHalfYear1Jc.setDateFormatString("dd MMMMM");
        endHalfYear1Jc.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent arg0) {
                endHalfYear1Jc.revalidate();
            }
		});
		addComponent(MFWLayout,layoutConstraints,endHalfYear1Jc,4,5,2,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(30,10,15,10));
		MFWFrame.getContentPane().add(endHalfYear1Jc);
		
		
		startEndHalfYear2Label = new JLabel("Date de d�but et de fin du 2�me semestre :");
		addComponent(MFWLayout,layoutConstraints,startEndHalfYear2Label,1,6,3,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(30,10,15,10));
		MFWFrame.getContentPane().add(startEndHalfYear2Label);
		
		startHalfYear2Label = new JLabel("D�but :");
		addComponent(MFWLayout,layoutConstraints,startHalfYear2Label,1,7,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(30,10,15,10));
		MFWFrame.getContentPane().add(startHalfYear2Label);
		
		startHalfYear2Jc = new JDateChooser();
		startHalfYear2Jc.setDate(BoTConfig.getBeginSecondHalfYear());
        startHalfYear2Jc.setDateFormatString("dd MMMMM");
        startHalfYear2Jc.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent arg0) {
                startHalfYear2Jc.revalidate();
            }
		});
		addComponent(MFWLayout,layoutConstraints,startHalfYear2Jc,2,7,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(30,10,15,10));
		MFWFrame.getContentPane().add(startHalfYear2Jc);
		
		endHalfYear2Label = new JLabel("Fin :");
		addComponent(MFWLayout,layoutConstraints,endHalfYear2Label,3,7,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(30,10,15,10));
		MFWFrame.getContentPane().add(endHalfYear2Label);
		
		endHalfYear2Jc = new JDateChooser();
		endHalfYear2Jc.setDate(BoTConfig.getEndSecondHalfYear());
        endHalfYear2Jc.setDateFormatString("dd MMMMM");
        endHalfYear2Jc.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent arg0) {
                endHalfYear2Jc.revalidate();
            }
		});
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

    private int checking() {
    	if (getTeacherJcb() == null)
            return 1;
        return 0;
    }

    private class ActionOk implements ActionListener {
        /* (non-Javadoc)
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        public void actionPerformed(ActionEvent arg0) {
            String errorMessage = null;
            switch (checking()) {
            case 0:
                isOk = true;
                MFWFrame.dispose();
                return;
            case 1:
                errorMessage = "Le responsable doit �tre s�lectionn�";
                break;
            default:
                errorMessage = "Erreur inconnue";
            }
            JOptionPane.showMessageDialog(null, errorMessage, "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
 }
