package fr.umlv.smoreau.beontime.client.graphics.utils;

import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

/**
 * @author BeOnTime
 */
public class TextFieldBoT extends JFormattedTextField {
    public TextFieldBoT(int size) {
        super();
        if (size > 0) {
            try {
                StringBuffer mask = new StringBuffer();
                for (int i = 0; i < size; ++i)
                    mask.append('*');
                MaskFormatter formatter = new MaskFormatter(mask.toString());
                DefaultFormatterFactory formatterFactory = new DefaultFormatterFactory(formatter);
                super.setFormatterFactory(formatterFactory);
            } catch (ParseException e) {
    		    // ne rien faire
    		}
        }
    }
}
