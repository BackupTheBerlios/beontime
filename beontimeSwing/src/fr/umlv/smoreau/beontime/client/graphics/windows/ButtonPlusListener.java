package fr.umlv.smoreau.beontime.client.graphics.windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;

/**
 * @author BeOnTime
 */
public class ButtonPlusListener implements ActionListener {
	private JPanel ownPanel;
	private JPanel jcbPanel;
	private JDialog formFrame;
	private String[] contents;
	
	protected ButtonPlusListener(JPanel ownPanel, JPanel jcbPanel, JDialog formFrame, String[] contents) {
		this.ownPanel = ownPanel;
		this.jcbPanel = jcbPanel;
		
		this.formFrame = formFrame;
		this.contents = contents;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		String text = ((JButton)e.getSource()).getText();
		
		if("+".equals(text)) {
			jcbPanel.add(new JComboBox(contents));
			jcbPanel.add(Box.createVerticalStrut(5));
		
			JButton plus = new JButton("+");
			plus.addActionListener(new ButtonPlusListener(ownPanel, jcbPanel, formFrame, contents));
			ownPanel.add(plus);
			ownPanel.add(Box.createVerticalStrut(5));
		
			((JButton)e.getSource()).setText("x");
			
			formFrame.pack();
		} else {
			int position = ownPanel.getComponentZOrder(((JButton)e.getSource()));
			
			jcbPanel.remove(position);
			jcbPanel.remove(position);
			
			ownPanel.remove(position);
			ownPanel.remove(position);
			
			formFrame.pack();
		}
	}
}
