package fr.umlv.smoreau.beontime.client.graphics.parts;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
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
		remarkPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		remarkPanel.add(remarkLabel, BorderLayout.CENTER);
    }

	private void initremarkPanel() {
		remarkLabel = new JLabel("Remarque");
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
	
}
