package fr.umlv.smoreau.beontime.client.graphics.windows;

import java.awt.*;
import java.net.URL;

import javax.swing.*;


/**
 * @author BeOnTime
 */
public class AboutWindow {
    private static final String[] DEVELOPPERS = { "Stéphane Moreau",
                                                  "Adrien Bruneteau",
                                                  "Sandrine Malouines",
                                                  "Mohamed Saadouni",
                                                  "Youssef Chfiri" };

	public void show(final Component parent) {
		JPanel panel = new JPanel(null);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

		Box box = new Box(BoxLayout.X_AXIS);
		box.add(Box.createHorizontalGlue());
		URL url = AboutWindow.class.getResource("logoBoT.gif");
		ImageIcon image = new ImageIcon(url);
		box.add(new JLabel(image));
		box.add(Box.createHorizontalGlue());
		panel.add(box);
		
		Box right = new Box(BoxLayout.Y_AXIS);

		box = new Box(BoxLayout.X_AXIS);
		box.add(Box.createHorizontalGlue());
		box.add(new JLabel("Projet de Génie Logiciel 2004-2005"));
		box.add(Box.createHorizontalGlue());
		right.add(box);
		
		right.add(Box.createVerticalStrut(10));

		box = new Box(BoxLayout.X_AXIS);
		box.add(Box.createHorizontalGlue());
		box.add(new JLabel("Réalisé au sein de "));
		box.add(Box.createHorizontalGlue());
		right.add(box);

		box = new Box(BoxLayout.X_AXIS);
		box.add(Box.createHorizontalGlue());
		box.add(new JLabel("l'Université de Marne-La-Vallée"));
		box.add(Box.createHorizontalGlue());
		right.add(box);
		
		right.add(Box.createVerticalStrut(20));

		box = new Box(BoxLayout.X_AXIS);
		box.add(Box.createHorizontalGlue());
		box.add(new JLabel("Par :"));
		box.add(Box.createHorizontalGlue());
		right.add(box);
		
		right.add(Box.createVerticalStrut(10));

		JLabel label;
		for (int i = 0; i < DEVELOPPERS.length; ++i) {
			box = new Box(BoxLayout.X_AXIS);
			box.add(Box.createHorizontalGlue());
			box.add(new JLabel(DEVELOPPERS[i]));
			box.add(Box.createHorizontalGlue());
			right.add(box);
		}
		
		panel.add(right);

		JOptionPane jop = new JOptionPane(panel);

		JDialog dialog = jop.createDialog(parent, "A propos de BeOnTime");
		dialog.setVisible(true);
	}
}

