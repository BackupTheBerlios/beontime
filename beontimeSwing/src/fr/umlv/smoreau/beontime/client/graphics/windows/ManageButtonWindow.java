/*
 * Created on 23 févr. 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package fr.umlv.smoreau.beontime.client.graphics.windows;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

import fr.umlv.smoreau.beontime.client.actions.ActionFactory;
import fr.umlv.smoreau.beontime.client.actions.ActionFormList;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;

/**
 * @author Mohamed
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ManageButtonWindow {
	private ArrayList buttons;
	private ArrayList ref;
	private final JList list_d;
	private static GridBagLayout lm;
	private static GridBagConstraints gbc;
	private final MainFrame mainFrame;
	private ActionListener listenerDone;
	private boolean operationOk;

	public void setListenerDone(ActionListener listenerDone) {
		this.listenerDone = listenerDone;
	}

	public ManageButtonWindow(MainFrame mainFrame,ArrayList buttons) {
		this.buttons = buttons;
		ActionFormList afl=new ActionFormList();
		this.ref = afl.getToolTips();
		ref.add(ref.size(), "----------");
		this.mainFrame = mainFrame;
		operationOk = false;
		lm = new GridBagLayout();
		gbc = new GridBagConstraints();
		final JFrame frame = new JFrame("ToolBar Manager");
		//frame.setIconImage(Action.getImage("logo.gif"));
		final RefListModel model_g = new RefListModel();
		final JList list_g = new JList(model_g);
		list_g.setToolTipText("Liste d'éléments ajoutable");
		JScrollPane sc_g = new JScrollPane();
		sc_g.getViewport().setView(list_g);
		final ButtonListModel model_d = new ButtonListModel(buttons);
		list_d = new JList(model_d);
		list_d.setToolTipText("Contenu de la barre");
		JScrollPane sc_d = new JScrollPane();
		sc_d.getViewport().setView(list_d);
		JButton add=new JButton(ActionFactory.getActionForName("AddButtonToToolBar",""));
		add.setToolTipText("Ajouter un icon à la ToolBar");
		JButton rem = new JButton(ActionFactory.getActionForName("RemoveButtonToToolBar",""));
		rem.setToolTipText("Supprimer un icon de la ToolBar");
		JButton up = new JButton(ActionFactory.getActionForName("MoveUpButtonToToolBar",""));
		up.setToolTipText("Remonter un icon dans la ToolBar");
		JButton dw = new JButton(ActionFactory.getActionForName("MoveDownButtonToToolBar",""));
		dw.setToolTipText("Descendre un icon dans la ToolBar");
		JButton ok = new JButton("OK");
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String tmp = (String) list_g.getSelectedValue();
				if (tmp != null)
					model_d.add(tmp);
				
			}
		});
		rem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = list_d.getSelectedIndex();
				if (index != -1)
					model_d.del(index);
			}
		});
		up.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = list_d.getSelectedIndex();
				if (index != 0 && index != -1) {
					model_d.up(index);
					list_d.setSelectedIndex(index - 1);
				}
			}
		});
		dw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = list_d.getSelectedIndex();
				if (index != model_d.getSize() && index != -1) {
					model_d.dw(index);
					list_d.setSelectedIndex(index + 1);
				}
			}
		});
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				operationOk = true;
				listenerDone.actionPerformed(new ActionEvent(this, 0, "Done"));
				frame.dispose();
				return;
			}
		});
		JPanel panel = new JPanel();
		panel.setLayout(lm);
		gbc.gridy = 0;
		gbc.gridx = 0;
		gbc.gridheight = 6;
		gbc.gridwidth = 3;
		addComponent(panel, sc_g, lm, gbc);
		gbc.gridx = 6;
		addComponent(panel, sc_d, lm, gbc);
		gbc.gridy = 1;
		gbc.gridx = 4;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		addComponent(panel, up, lm, gbc);
		gbc.gridy = 2;
		gbc.gridx = 3;
		addComponent(panel, rem, lm, gbc);
		gbc.gridy = 2;
		gbc.gridx = 5;
		addComponent(panel, add, lm, gbc);
		gbc.gridy = 3;
		gbc.gridx = 4;
		addComponent(panel, dw, lm, gbc);
		gbc.gridy = 5;
		gbc.gridx = 4;
		addComponent(panel, ok, lm, gbc);
		frame.setContentPane(panel);
		frame.pack();
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((d.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((d.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
		frame.setResizable(false);
		frame.show();
	}

	public static void addComponent(JPanel panel, Component co,
			GridBagLayout gridbag, GridBagConstraints gbc) {
		gridbag.setConstraints(co, gbc);
		panel.add(co);
	}

	public boolean isOk() {
		return operationOk;
	}


	private class RefListModel implements ListModel {
		public int getSize() {
			return ref.size();
		}

		public Object getElementAt(int index) {
			return ref.get(index);
		}

		public void addListDataListener(ListDataListener arg0) {
			// TODO Auto-generated method stub
			
		}

		public void removeListDataListener(ListDataListener arg0) {
			// TODO Auto-generated method stub
			
		}
	}

	private class ButtonListModel extends DefaultListModel {
		public ButtonListModel() {
			buttons = new ArrayList();
		}

		protected void up(int index) {
			Object swap = buttons.remove(index);
			buttons.add(index - 1, swap);
			fireContentsChanged(this, index - 1, index);
		}

		protected void dw(int index) {
			if (index + 1 != buttons.size()) {
				Object swap = buttons.remove(index);
				buttons.add(index + 1, swap);
				fireContentsChanged(this, index, index + 1);
			}
		}

		protected void del(int index) {
			buttons.remove(index);
			fireIntervalRemoved(this, index, index);
		}

		public ButtonListModel(ArrayList buttons) {
			super();
		}

		public void add(String value) {
			buttons.add(buttons.size(), value);
			fireIntervalAdded(this, buttons.size(), buttons.size());
		}

		public int getSize() {
			return buttons.size();
		}

		public Object getElementAt(int index) {
			return buttons.get(index);
		}
	}

	public ArrayList getButtons() {
		return buttons;
	}
}
