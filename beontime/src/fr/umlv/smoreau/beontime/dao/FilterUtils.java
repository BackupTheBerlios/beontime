package fr.umlv.smoreau.beontime.dao;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

/**
 * @author BeOnTime
 */
public class FilterUtils {
    protected static void fillFilterClass(Object in, Object out, Collection methods) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        if (in != null && out != null) {
            Class classIn = in.getClass();
            Class classOut = out.getClass();

            for (Iterator i = methods.iterator(); i.hasNext();) {
                String methodName = (String) i.next();
                
                Method method = classOut.getMethod("get"+methodName, null);
                classIn.getMethod("set"+methodName, new Class[] {method.getReturnType()}).invoke(in,new Object[] {method.invoke(out,null)});
            }
        }
    }
    
    protected static String createHQLQuery(Object object, HashMap corres) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
	    StringBuffer query = new StringBuffer();
	    Class clazz = object.getClass();
	    
	    for (Iterator i = corres.keySet().iterator(); i.hasNext();) {
	        String methodName = (String) i.next();
	        String attributeName = (String) corres.get(methodName);

	        Object obj = clazz.getMethod("get"+methodName, null).invoke(object, null);
	        if (obj != null) {
		        if (query.length() != 0)
			        query.append(" AND ");
		        query.append(attributeName).append("='").append(obj).append("'");
	        }
	    }
		    
		return query.toString();
    }
}
