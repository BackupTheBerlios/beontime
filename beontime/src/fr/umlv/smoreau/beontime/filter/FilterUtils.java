package fr.umlv.smoreau.beontime.filter;

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
                
                if (methodName.indexOf(".") != -1)
                    methodName = (methodName.split("\\."))[0];
                Method method = classOut.getMethod("get"+methodName, (java.lang.Class[]) null);
                classIn.getMethod("set"+methodName, new Class[] {method.getReturnType()}).invoke(in,new Object[] {method.invoke(out,(java.lang.Object[]) null)});
            }
        }
    }
    
    protected static String createHQLQuery(Object object, HashMap corres) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
	    StringBuffer query = new StringBuffer();
	    
	    for (Iterator i = corres.keySet().iterator(); i.hasNext();) {
	        String methodName = (String) i.next();
	        String attributeName = (String) corres.get(methodName);

	        String[] split = null;
	        if (methodName.indexOf(".") != -1)
	            split = methodName.split("\\.");
	        else
	            split = new String[] { methodName };
	        Object obj = object;
	        for (int j = 0; j < split.length && obj != null; ++j) {
	            Class clazz = obj.getClass();
	            obj = clazz.getMethod("get"+split[j], (java.lang.Class[]) null).invoke(obj, (java.lang.Object[]) null);
	        }

	        if (obj != null) {
		        if (query.length() != 0)
			        query.append(" AND ");
		        if (obj instanceof String)
		            obj = ((String) obj).replaceAll("'","''");
		        query.append(attributeName).append("='").append(obj).append("'");
	        }
	    }
		    
		return query.toString();
    }
}
