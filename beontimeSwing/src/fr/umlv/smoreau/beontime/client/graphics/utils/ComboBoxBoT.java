package fr.umlv.smoreau.beontime.client.graphics.utils;

import java.awt.Component;
import java.awt.event.ActionListener;

import javax.swing.ComboBoxEditor;
import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 * @author BeOnTime
 */
public class ComboBoxBoT extends JComboBox {
    public ComboBoxBoT(Object[] objects) {
        super(objects);
    }

    public ComboBoxBoT() {
        super();
    }

    public void setEditable(boolean aFlag, final int size) {
        setEditable(aFlag);
        
        setEditor(new ComboBoxEditor() {
		    JTextField textField = new TextFieldBoT(size);

            public Component getEditorComponent() {
                return textField;
            }

            public void setItem(Object obj) {
                textField.setText((String) obj);
            }

            public Object getItem() {
                return textField.getText().trim();
            }

            public void selectAll() {
                textField.selectAll();
            }

            public void addActionListener(ActionListener arg0) {
            }

            public void removeActionListener(ActionListener arg0) {
            }
		});
    }
}
