/*
 * Created on 23 févr. 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package fr.umlv.smoreau.beontime.client.actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * @author Mohamed
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ActionFormList {
	private HashMap action=new HashMap();
	
	/**
	 * 
	 */
	public ActionFormList() {
		init();
	}

	/**
	 * 
	 */
	private void init() {
		action.put("Visualiser un EDT","ViewTimetable");
		action.put("Imprimer l'EDT","PrintTimetable");
		action.put("Exporter l'EDT","ExportTimetable");
		action.put("Couper un cours","CutCourse");
		action.put("Copier un cours","CopyCourse");
		action.put("Coller le cours","PasteCourse");
		action.put("Créer un utilisateur","AddUser");
		action.put("Ajouter une matière","AddSubject");
		action.put("Créer un local","AddRoom");
		action.put("Créer un matériel","AddMaterial");
		action.put("Générer des groupes","GenerateGroups");
		action.put("Placer un cours","AddCourse");
		action.put("Supprimer le cours","DeleteCourse");
		action.put("Afficher l'EDT par semaines","ShowTimetable");
		action.put("Afficher l'EDT par semestre","ShowTimetable");
		action.put("Configurer la toolbar","ActionManageButtonWindow");
	}
	
	public String getActionForName(String name){
		String s=(String) action.get(name);
		return s;
		
	}
	
	public ArrayList getActions(){
		Collection col=action.values();
		ArrayList al=new ArrayList(col);
		return al;
	}
	
	public ArrayList getToolTips(){
		Object[] obj=action.keySet().toArray();
		ArrayList al=new ArrayList();
		for(int i=0;i<obj.length;i++){
			al.add(obj[i].toString());
		}
		return al;
		
	}
}
