/*
 * Created on 22 févr. 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package fr.umlv.smoreau.beontime.client.actions;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import javax.swing.Action;

import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
/**
 * @author Mohamed
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ActionFactory {
		private static MainFrame mainFrame = null;
		private static HashMap listeActions = new HashMap();
		
		public static void init(MainFrame mainFrame) {
			ActionFactory.mainFrame = mainFrame;
		}

		/**
		 * Returns the instance of the action desired, creating it if needed.
		 * @param strActionClass
		 * @return
		 */
		public static Action getActionForName(String strActionClass,String name) {
			if (mainFrame==null) throw new InternalError("You must initialize ActionFactory before calling this method.");

			if (listeActions.containsKey(strActionClass))
				return (Action)listeActions.get(strActionClass);

			Action a = null;

			try {
				Class classe=Class.forName("fr.umlv.smoreau.beontime.client.actions.forms." + strActionClass);
				Object obj=classe.newInstance();
				Class[] types={Class.forName("java.lang.String")};
				Method methode=classe.getMethod("getAction",types);
				String[] params={name};
				a= (Action) methode.invoke(obj,params);
				
				
			} catch (ClassNotFoundException e) {
				System.out.println("fr.umlv.smoreau.beontime.client.actions.forms." + strActionClass);
				throw new InternalError("Request for a wrong class.");
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			listeActions.put(strActionClass, a);
			return a;
		}
		
	}
