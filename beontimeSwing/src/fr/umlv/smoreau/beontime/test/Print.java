package fr.umlv.smoreau.beontime.test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Properties;

class MonDessin extends JPanel {
    MonDessin() {
	setPreferredSize(new Dimension(180, 120));
    }
    public void paintComponent(Graphics g) {
	super.paintComponent(g);
	g.drawOval(50, 20, 80, 80);
    }
}

class Print extends JPanel implements ActionListener {
    MonDessin dessin = new MonDessin();
    JButton imprimer = new JButton("imprimer");
    JRadioButton choixTout = new JRadioButton("tout", true);
    JRadioButton choixDessin = new JRadioButton("dessin", false);
    JFrame fenetre;

    Print(JFrame _fenetre) {
	fenetre = _fenetre;
	setLayout(new BorderLayout());
	JPanel p = new JPanel();
	p.add(choixTout);
	p.add(choixDessin);
	add(p, BorderLayout.NORTH);

	ButtonGroup choix = new ButtonGroup();
	choix.add(choixTout);
	choix.add(choixDessin);
	
	add(dessin, BorderLayout.CENTER);
	
	imprimer.addActionListener(this);
	add(imprimer, BorderLayout.SOUTH);
    }
    
    public void actionPerformed(ActionEvent e) {
	Properties props = new Properties();
	
	props.setProperty("awt.print.paperSize", "A4");
	props.setProperty("awt.print.destination", "printer");
	
	PrintJob demandeDImpression = getToolkit().getPrintJob(fenetre, "Impression", props);
		if (demandeDImpression != null) {
		    Graphics gImpr = demandeDImpression.getGraphics();
		    
		    if (choixTout.isSelected())
		    	fenetre.printAll(gImpr);
		    else
		    	dessin.printAll(gImpr);
		    gImpr.dispose();
		    demandeDImpression.end();
		}
    }
    
    public static void main(String[] arg) {
	JFrame cadre = new JFrame();
	cadre.setContentPane( new Print(cadre));
	cadre.pack();
	cadre.setLocation(100, 100);
	cadre.setVisible(true);
	
	cadre.addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent evt) {
		    System.exit(0);
		}
		});
    }
}
