/*
 * Created on 25 f�vr. 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package fr.umlv.smoreau.beontime.client.graphics.parts.view;

import javax.swing.JLabel;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 * @author BeOnTime
 */
public class MyTableModel implements TableModel {

	String[] nomsColonnes = { "8H", "9H", "10H", "11H", "12H", "13H", "14H", "15H", "16H", "17H", "18H", "19H", "20H" };
	JLabel jLabelList[][];

	public MyTableModel() {
		this.jLabelList = new JLabel[5][nomsColonnes.length];
	}

	// autant de ligne que d'objet Url dans notre ArrayList 
	public int getRowCount() {
		return 5;
	}

	// autant de lignes que de propri�t�s dans un objet Url
	// ici r�cup�r� � travers le nombre de titres de colonnes
	public int getColumnCount() {
		return nomsColonnes.length;
	}

	// retourne le titre de chaque colonne
	public String getColumnName(int columnIndex) {
		return nomsColonnes[columnIndex];
	}

	// on sp�cifie la classe des objets contenus dans chaque colonne
	public Class getColumnClass(int columnIndex) {
			return JLabel.class;
	}

	//	seul les champs de type Date sont �ditables
	// donc on retourne vrai seulement pour les 3 derni�res colones
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	// d�termine l'objet correspondant � la ligne rowIndex
	// retourne l'�l�ment de cet objet correspondant � la colonne columnIndex
	public Object getValueAt(int rowIndex, int columnIndex) {
		JLabel jl = (JLabel) jLabelList[rowIndex][columnIndex];
		return jl;

	}

	// n'est utile que pour les cellules �ditables
	// dans notre cas, les 3 derni�res colones, 
	// comme d�fini dans la fonction isCellEditable()
	public void setValueAt(Object aValue, int rowIndex, int bcolumnIndex) {
		jLabelList[rowIndex][bcolumnIndex]=(JLabel)aValue;
	}

	public void addTableModelListener(TableModelListener l) {

	}

	public void removeTableModelListener(TableModelListener l) {

	}

	/******************************/

	/*****************************/


}
