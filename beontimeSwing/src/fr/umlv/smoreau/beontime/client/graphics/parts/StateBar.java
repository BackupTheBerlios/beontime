/*
 * 
 */
package fr.umlv.smoreau.beontime.client.graphics.parts;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author BeOnTime
 */
public class StateBar extends JPanel {
	private JLabel remarkLabel;
	private JPanel remarkPanel = new JPanel();
    public StateBar() {
    	remarkPanel.setLayout(new BorderLayout());
		
		initremarkPanel();
		remarkPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		remarkPanel.add(remarkLabel, BorderLayout.CENTER);

    }
	/**
	 * 
	 */
	private void initremarkPanel() {

		remarkLabel = new JLabel("Remarque");
		remarkLabel.setHorizontalAlignment(JLabel.CENTER);
	}
	public JPanel getStateBarPanel() {
		return remarkPanel;
	}
	public void setStateBarPanel(JPanel jp) {
		remarkPanel=jp;
	}
	public String getRemark() {
		return remarkLabel.getText();
	}
	public void setRemark(String rem) {
		remarkLabel.setText(rem);
	}
	public static void main(String[] args){
		
		StateBar maBar = new StateBar();
		
		JFrame mafenetre = new JFrame();
		mafenetre.getContentPane().add(maBar.getStateBarPanel());
		mafenetre.setTitle("Essai td");
		mafenetre.pack();
		mafenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mafenetre.setVisible(true);
		}
	
}
