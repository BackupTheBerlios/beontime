package fr.umlv.smoreau.beontime.client.graphics.windows;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import fr.umlv.smoreau.beontime.client.graphics.BoTModel;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.dao.DaoManager;


/**
 * @author BeOnTime
 */
public class ManageGroupsWindow {
	private static final String TITRE = "Gérer les entités des groupes";
	
	private JPanel panelTable = new JPanel();
	private JPanel validateButtonPanel = new JPanel();
	
	private BoTModel model = new BoTModel();
	
	private JDialog MGWFrame;
	
	private ManageIdentityGroupsTable table;
	
	
	
	public ManageGroupsWindow() {
		MGWFrame = new JDialog(MainFrame.getInstance().getMainFrame(), TITRE, true);   
		initManageGroupsWindow();  
	}
	
	private void initManageGroupsWindow() {
		model = new BoTModel();
		
		initPanel();
		MGWFrame.getContentPane().add(panelTable, BorderLayout.CENTER);
		
		initValidateButtonPanel();
		MGWFrame.getContentPane().add(validateButtonPanel, BorderLayout.SOUTH);
	}
	
	
	private void initPanel() {
		this.table = new ManageIdentityGroupsTable(model);
		panelTable = table.getPanel(); 
	
	}
	
	
	private void initValidateButtonPanel() {
		validateButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		JButton ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	ManageIdentityGroupsAdapter adapter = (ManageIdentityGroupsAdapter) table.getModel();
            	try {
            		DaoManager.getGroupDao().modifyGroup(adapter.getGroup());
            		
            		JOptionPane.showMessageDialog(null, "Modification effectuée avec succès", "Information", JOptionPane.INFORMATION_MESSAGE);
            	} catch (Exception e) {
            		e.printStackTrace();
            		JOptionPane.showMessageDialog(null, "Une erreur interne est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
            	}
            	MGWFrame.dispose();
            }
		});
		validateButtonPanel.add(ok);
		
		JButton annuler = new JButton("Annuler");
		annuler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	MGWFrame.dispose();
            }
		});
		validateButtonPanel.add(annuler);
	}	
	
	/* (non-Javadoc)
	 * @see fr.umlv.smoreau.beontimeSwing.graphics.windows.Window#show(java.lang.Object[])
	 */
	public void show() {
		MGWFrame.pack();
		MGWFrame.setResizable(false);
		MGWFrame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		MGWFrame.setLocationRelativeTo(null);
		MGWFrame.setVisible(true);
	}
}
