package fr.umlv.smoreau.beontime.client.graphics.windows;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.toedter.calendar.JDateChooser;

import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.client.graphics.utils.ComboBoxItem;
import fr.umlv.smoreau.beontime.dao.DaoManager;
import fr.umlv.smoreau.beontime.dao.UnavailabilityDao;
import fr.umlv.smoreau.beontime.filter.GroupFilter;
import fr.umlv.smoreau.beontime.model.Formation;
import fr.umlv.smoreau.beontime.model.Group;
import fr.umlv.smoreau.beontime.model.element.Material;
import fr.umlv.smoreau.beontime.model.element.Room;
import fr.umlv.smoreau.beontime.model.user.User;


/**
 * @author BeOnTime
 */
public class AddModifyUnavailabilityWindow {
    private static final String TITRE_ADD = "Ajouter une indisponibilité";
    private static final String TITRE_MODIFY = "Modifier l'indisponibilité";
    
    public static final int TYPE_ADD = 0;
    public static final int TYPE_MODIFY = 1;
    
    private JLabel subjectUnavailabilityLabel;
    private JLabel subject2UnavailabilityLabel;
    
    private JDateChooser startUnavailabilityDate;
    private JDateChooser endUnavailabilityDate;
    
    private JComboBox startUnavailabilityHourJcb;
    private JComboBox startUnavailabilityMinuteJcb;
    private JComboBox endUnavailabilityHourJcb;
    private JComboBox endUnavailabilityMinuteJcb;
    private JComboBox repeatUnavailabilityJcb;
    private JComboBox typeUnavailabilityJcb;
    private JComboBox subjectUnavailabilityJcb;
    private JComboBox subject2UnavailabilityJcb;
    
    private JTextArea descriptionTextArea;
    
    private JDialog AMUWFrame;
    private GridBagLayout AMUWLayout = new GridBagLayout();
    private GridBagConstraints layoutConstraints = new GridBagConstraints();
    
    private Collection teachers;
    private Collection formations;
    private Collection rooms;
    private Collection materials;
    private HashMap groups;
    
	private boolean isOk = false;
	private int type;


    public AddModifyUnavailabilityWindow(int type) {
        this.type = type;
        String titre = "";
        switch (type) {
	        case TYPE_ADD: titre = TITRE_ADD; break;
	        case TYPE_MODIFY: titre = TITRE_MODIFY; break;
        }
        AMUWFrame = new JDialog(MainFrame.getInstance().getMainFrame(), titre, true);
        AMUWFrame.getContentPane().setLayout(AMUWLayout);
        
        try {
            teachers = DaoManager.getUserDao().getTeachers();
        } catch (Exception e) {
            teachers = new ArrayList();
        }
        try {
            rooms = DaoManager.getElementDao().getRooms();
        } catch (Exception e) {
            rooms = new ArrayList();
        }
        try {
            materials = DaoManager.getElementDao().getMaterials();
        } catch (Exception e) {
            materials = new ArrayList();
        }
        formations = MainFrame.getInstance().getUserConnected().getFormationsInCharge();
        try {
            groups = new HashMap();
            for (Iterator i = formations.iterator(); i.hasNext(); ) {
                Formation formation = (Formation) i.next();
                GroupFilter filter = new GroupFilter();
                filter.setIdFormation(formation.getIdFormation());
                groups.put(formation.getIdFormation(), DaoManager.getGroupDao().getGroups(filter));
            }
        } catch (Exception e) {
        }
        
        initAddModifyUnavailabilityWindow();  
    }


    private void initAddModifyUnavailabilityWindow() {
        JLabel startUnavailabilityLabel = new JLabel("Début d'indisponibilité :");
        addComponent(AMUWLayout,layoutConstraints,startUnavailabilityLabel,1,1,2,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,0,10));
        AMUWFrame.getContentPane().add(startUnavailabilityLabel);
        
        startUnavailabilityHourJcb = new JComboBox();
        initNumberJcb(startUnavailabilityHourJcb, 0, 23);
        addComponent(AMUWLayout,layoutConstraints,startUnavailabilityHourJcb,4,2,1,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(0,10,0,10));
        AMUWFrame.getContentPane().add(startUnavailabilityHourJcb);
        
        JLabel startUnavailabilityHourLabel = new JLabel("heure");
        addComponent(AMUWLayout,layoutConstraints,startUnavailabilityHourLabel,5,2,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(10,10,0,10));
        AMUWFrame.getContentPane().add(startUnavailabilityHourLabel);
        
        JLabel startUnavailabilityTheLabel = new JLabel("le");
        addComponent(AMUWLayout,layoutConstraints,startUnavailabilityTheLabel,1,3,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(0,10,0,10));
        AMUWFrame.getContentPane().add(startUnavailabilityTheLabel);
        
        startUnavailabilityDate = new JDateChooser();
        startUnavailabilityDate.setDateFormatString("dd MMMMM yyyy");
        startUnavailabilityDate.setDate(Calendar.getInstance().getTime());
        addComponent(AMUWLayout,layoutConstraints,startUnavailabilityDate,2,3,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(0,10,0,10));
        AMUWFrame.getContentPane().add(startUnavailabilityDate);
        
        startUnavailabilityMinuteJcb = new JComboBox();
        initMinuteJcb(startUnavailabilityMinuteJcb);
        addComponent(AMUWLayout,layoutConstraints,startUnavailabilityMinuteJcb,4,4,1,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(0,10,10,10));
        AMUWFrame.getContentPane().add(startUnavailabilityMinuteJcb);
        
        JLabel startUnavailabilityMinuteLabel = new JLabel("min");
        addComponent(AMUWLayout,layoutConstraints,startUnavailabilityMinuteLabel,5,4,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(10,10,10,10));
        AMUWFrame.getContentPane().add(startUnavailabilityMinuteLabel);

        
        JLabel endUnavailabilityLabel = new JLabel("Fin d'indisponibilité :");
        addComponent(AMUWLayout,layoutConstraints,endUnavailabilityLabel,1,5,2,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(0,10,0,10));
        AMUWFrame.getContentPane().add(endUnavailabilityLabel);
        
        endUnavailabilityHourJcb = new JComboBox();
        initNumberJcb(endUnavailabilityHourJcb, 0, 23);
        endUnavailabilityHourJcb.setSelectedIndex(23); 
        addComponent(AMUWLayout,layoutConstraints,endUnavailabilityHourJcb,4,6,1,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(0,10,0,10));
        AMUWFrame.getContentPane().add(endUnavailabilityHourJcb);
        
        JLabel endUnavailabilityHourLabel = new JLabel("heure");
        addComponent(AMUWLayout,layoutConstraints,endUnavailabilityHourLabel,5,6,GridBagConstraints.REMAINDER,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(10,10,0,10));
        AMUWFrame.getContentPane().add(endUnavailabilityHourLabel);
        
        JLabel endUnavailabilityTheLabel = new JLabel("le");
        addComponent(AMUWLayout,layoutConstraints,endUnavailabilityTheLabel,1,7,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(0,10,0,10));
        AMUWFrame.getContentPane().add(endUnavailabilityTheLabel);
        
        endUnavailabilityDate = new JDateChooser();
        endUnavailabilityDate.setDateFormatString("dd MMMMM yyyy");
        endUnavailabilityDate.setDate(Calendar.getInstance().getTime());
        addComponent(AMUWLayout,layoutConstraints,endUnavailabilityDate,2,7,GridBagConstraints.REMAINDER,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(0,10,0,10));
        AMUWFrame.getContentPane().add(endUnavailabilityDate);
        
        endUnavailabilityMinuteJcb = new JComboBox();
        initMinuteJcb(endUnavailabilityMinuteJcb);
        endUnavailabilityMinuteJcb.setSelectedIndex(3); 
        addComponent(AMUWLayout,layoutConstraints,endUnavailabilityMinuteJcb,4,8,1,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(0,10,20,10));
        AMUWFrame.getContentPane().add(endUnavailabilityMinuteJcb);
        
        JLabel endUnavailabilityMinuteLabel = new JLabel("min");
        addComponent(AMUWLayout,layoutConstraints,endUnavailabilityMinuteLabel,5,8,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(10,10,20,10));
        AMUWFrame.getContentPane().add(endUnavailabilityMinuteLabel);

        
        JLabel repeatUnavailabilityLabel = new JLabel("Répéter cette indisponibilité sur");
        addComponent(AMUWLayout,layoutConstraints,repeatUnavailabilityLabel,1,9,3,1,1.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,20,10));
        AMUWFrame.getContentPane().add(repeatUnavailabilityLabel);
        
        repeatUnavailabilityJcb = new JComboBox();
        initNumberJcb(repeatUnavailabilityJcb,1,48);
        addComponent(AMUWLayout,layoutConstraints,repeatUnavailabilityJcb,4,9,1,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(10,10,20,10));
        AMUWFrame.getContentPane().add(repeatUnavailabilityJcb);
        
        JLabel weeksLabel = new JLabel("semaines");
        addComponent(AMUWLayout,layoutConstraints,weeksLabel,5,9,1,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,20,10));
        AMUWFrame.getContentPane().add(weeksLabel);
        
        if (type == TYPE_MODIFY) {
            repeatUnavailabilityLabel.setVisible(false);
            repeatUnavailabilityJcb.setVisible(false);
            weeksLabel.setVisible(false);
        }
        
        
        
        JLabel typeUnavailabilityLabel = new JLabel("Type d'indisponibilité :");
        addComponent(AMUWLayout,layoutConstraints,typeUnavailabilityLabel,1,10,2,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(20,10,10,10));
        AMUWFrame.getContentPane().add(typeUnavailabilityLabel);
        
        typeUnavailabilityJcb = new JComboBox(UnavailabilityDao.ALL_TYPES);
        addComponent(AMUWLayout,layoutConstraints,typeUnavailabilityJcb,3,10,GridBagConstraints.REMAINDER,1,1.0,0.0,GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,new Insets(20,10,10,10));
        AMUWFrame.getContentPane().add(typeUnavailabilityJcb);
        
        
        subject2UnavailabilityLabel = new JLabel("Formation indisponible :");
        addComponent(AMUWLayout,layoutConstraints,subject2UnavailabilityLabel,1,11,2,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(10,10,10,10));
        AMUWFrame.getContentPane().add(subject2UnavailabilityLabel);
        
        subject2UnavailabilityJcb = new JComboBox();
        for (Iterator i = formations.iterator(); i.hasNext(); ) {
            Formation formation = (Formation) i.next();
            subject2UnavailabilityJcb.addItem(new ComboBoxItem(formation.getHeading(), formation.getIdFormation()));
        }
        subject2UnavailabilityJcb.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                initSubjectUnavailabilityJcb();
            }
        });
        addComponent(AMUWLayout,layoutConstraints,subject2UnavailabilityJcb,3,11,GridBagConstraints.REMAINDER,1,1.0,0.0,GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,new Insets(10,10,10,10));
        AMUWFrame.getContentPane().add(subject2UnavailabilityJcb);
        
        subject2UnavailabilityLabel.setVisible(false);
        subject2UnavailabilityJcb.setVisible(false);
        
        
        String tmp = UnavailabilityDao.ALL_TYPES[0].substring(0,1).toUpperCase() + UnavailabilityDao.ALL_TYPES[0].substring(1);
        subjectUnavailabilityLabel = new JLabel(tmp + " indisponible :");
        typeUnavailabilityJcb.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                initSubjectUnavailabilityJcb();
            }
        });
        addComponent(AMUWLayout,layoutConstraints,subjectUnavailabilityLabel,1,11,2,1,0.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(10,10,10,10));
        AMUWFrame.getContentPane().add(subjectUnavailabilityLabel);
        
        subjectUnavailabilityJcb = new JComboBox();
        addComponent(AMUWLayout,layoutConstraints,subjectUnavailabilityJcb,3,11,GridBagConstraints.REMAINDER,1,1.0,0.0,GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL,new Insets(10,10,10,10));
        AMUWFrame.getContentPane().add(subjectUnavailabilityJcb);
        initSubjectUnavailabilityJcb();
        
        
        JLabel descriptionLabel = new JLabel("Description :");
        addComponent(AMUWLayout,layoutConstraints,descriptionLabel,1,12,2,1,1.0,0.0,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(10,10,0,10));
        AMUWFrame.getContentPane().add(descriptionLabel);
        
        
        descriptionTextArea = new JTextArea();
        descriptionTextArea.setLineWrap(true);
        descriptionTextArea.setRows(4);
        JScrollPane pane= new JScrollPane(descriptionTextArea);
        addComponent(AMUWLayout,layoutConstraints,pane,1,13,GridBagConstraints.REMAINDER,1,1.0,0.0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(5,10,20,10));
        AMUWFrame.getContentPane().add(pane);
        
        
        JButton ok = new JButton("OK");
        ok.addActionListener(new ActionOk());
        addComponent(AMUWLayout,layoutConstraints,ok,4,14,1,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(20,10,10,10));
        AMUWFrame.getContentPane().add(ok);
        
        JButton annuler = new JButton("Annuler");
        annuler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                AMUWFrame.dispose();
            }
        });
        addComponent(AMUWLayout,layoutConstraints,annuler,5,14,GridBagConstraints.REMAINDER,1,0.0,0.0,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(20,10,10,10));
        AMUWFrame.getContentPane().add(annuler);
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
    
    private void initSubjectUnavailabilityJcb() {
        String selected = (String) typeUnavailabilityJcb.getSelectedItem();
        if (UnavailabilityDao.TYPE_CALENDAR.equals(selected)) {
            subjectUnavailabilityLabel.setVisible(false);
            subjectUnavailabilityJcb.setVisible(false);
            subject2UnavailabilityLabel.setVisible(false);
	        subject2UnavailabilityJcb.setVisible(false);
        } else if (UnavailabilityDao.TYPE_GROUP.equals(selected)) {
	        subject2UnavailabilityLabel.setVisible(true);
	        subject2UnavailabilityJcb.setVisible(true);
	        subjectUnavailabilityLabel.setVisible(true);
            subjectUnavailabilityJcb.setVisible(true);
	    } else {
            subjectUnavailabilityLabel.setVisible(true);
            subjectUnavailabilityJcb.setVisible(true);
            subject2UnavailabilityLabel.setVisible(false);
	        subject2UnavailabilityJcb.setVisible(false);
        }
        subjectUnavailabilityLabel.setText(selected.substring(0,1).toUpperCase() + selected.substring(1) + " indisponible :");
        
        subjectUnavailabilityJcb.removeAllItems();
        if (UnavailabilityDao.TYPE_TEACHER.equals(selected)) {
            for (Iterator i = teachers.iterator(); i.hasNext(); ) {
                User teacher = (User) i.next();
                subjectUnavailabilityJcb.addItem(new ComboBoxItem(teacher.getName() + " " + teacher.getFirstName(), teacher.getIdUser()));
            }
        } else if (UnavailabilityDao.TYPE_ROOM.equals(selected)) {
            for (Iterator i = rooms.iterator(); i.hasNext(); ) {
                Room room = (Room) i.next();
                subjectUnavailabilityJcb.addItem(new ComboBoxItem(room.getName(), room.getIdRoom()));
            }
        } else if (UnavailabilityDao.TYPE_MATERIAL.equals(selected)) {
            for (Iterator i = materials.iterator(); i.hasNext(); ) {
                Material material = (Material) i.next();
                subjectUnavailabilityJcb.addItem(new ComboBoxItem(material.getName(), material.getIdMaterial()));
            }
        } else if (UnavailabilityDao.TYPE_GROUP.equals(selected)) {
            ComboBoxItem item = (ComboBoxItem) subject2UnavailabilityJcb.getSelectedItem();
            Collection g = (Collection) groups.get(item.getId());
            for (Iterator i = g.iterator(); i.hasNext(); ) {
                Group group = (Group) i.next();
                subjectUnavailabilityJcb.addItem(new ComboBoxItem(group.getHeading(), group.getIdGroup()));
            }
        }
    }
    
    /* (non-Javadoc)
     * @see fr.umlv.smoreau.beontimeSwing.graphics.windows.Window#show(java.lang.Object[])
     */
    public void show() {
        AMUWFrame.pack();
        AMUWFrame.setResizable(false);
        AMUWFrame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        AMUWFrame.setLocationRelativeTo(null);
        AMUWFrame.setVisible(true);
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
    public boolean isOk() {
        return isOk;
    }

    private int getStartHour(){
    	return Integer.parseInt((String) startUnavailabilityHourJcb.getSelectedItem());
    }
    
    private int getStartMinute(){
    	return Integer.parseInt((String) startUnavailabilityMinuteJcb.getSelectedItem());
    }

    private int getEndHour(){
        return Integer.parseInt((String) endUnavailabilityHourJcb.getSelectedItem());
    }
    
    private int getEndMinute(){
        return Integer.parseInt((String) endUnavailabilityMinuteJcb.getSelectedItem());
    }
    
    public Calendar getBeginDate() {
        Calendar beginDate = Calendar.getInstance();
	    beginDate.setTime(startUnavailabilityDate.getDate());
	    beginDate.set(Calendar.HOUR_OF_DAY, getStartHour());
	    beginDate.set(Calendar.MINUTE, getStartMinute());
	    beginDate.set(Calendar.SECOND, 0);
	    beginDate.set(Calendar.MILLISECOND, 0);
	    return beginDate;
    }
    
    public void setBeginDate(Calendar date) {
        startUnavailabilityDate.setDate(date.getTime());
        startUnavailabilityHourJcb.setSelectedIndex(date.get(Calendar.HOUR_OF_DAY));
        startUnavailabilityMinuteJcb.setSelectedIndex(date.get(Calendar.MINUTE)/15);
    }
    
    public Calendar getEndDate() {
        Calendar beginDate = Calendar.getInstance();
	    beginDate.setTime(endUnavailabilityDate.getDate());
	    beginDate.set(Calendar.HOUR_OF_DAY, getEndHour());
	    beginDate.set(Calendar.MINUTE, getEndMinute());
	    beginDate.set(Calendar.SECOND, 0);
	    beginDate.set(Calendar.MILLISECOND, 0);
	    return beginDate;
    }
    
    public void setEndDate(Calendar date) {
        endUnavailabilityDate.setDate(date.getTime());
        endUnavailabilityHourJcb.setSelectedIndex(date.get(Calendar.HOUR_OF_DAY));
        endUnavailabilityMinuteJcb.setSelectedIndex(date.get(Calendar.MINUTE)/15);
    }
    
    public String getTypeUnavailability() {
        return (String) typeUnavailabilityJcb.getSelectedItem();
    }
    
    public void setTypeUnavailability(String type) {
        typeUnavailabilityJcb.setSelectedItem(type);
        typeUnavailabilityJcb.setEnabled(false);
    }
    
    public Long getSubjectUnavailability() {
        if (UnavailabilityDao.TYPE_CALENDAR.equals(getTypeUnavailability()))
            return null;
        return ((ComboBoxItem) subjectUnavailabilityJcb.getSelectedItem()).getId();
    }
    
    public void setSubjectUnavailability(Long id) {
        for (int i = 0; i < subjectUnavailabilityJcb.getItemCount(); ++i) {
            ComboBoxItem item = (ComboBoxItem) subjectUnavailabilityJcb.getItemAt(i);
            if (item.getId().equals(id)) {
                subjectUnavailabilityJcb.setSelectedItem(item);
                break;
            }
        }
        subjectUnavailabilityJcb.setEnabled(false);
    }
    
    public String getDescription() {
        return descriptionTextArea.getText();
    }
    
    public void setDescription(String description) {
        descriptionTextArea.setText(description);
    }
    
    public int getRepeatUnavailability() {
        return (new Integer((String) repeatUnavailabilityJcb.getSelectedItem())).intValue();
    }
    
    private int checking() {
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
                AMUWFrame.dispose();
                return;
            default:
                errorMessage = "Erreur inconnue";
            }
            JOptionPane.showMessageDialog(null, errorMessage, "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}