package fr.umlv.smoreau.beontime.client;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;

import fr.umlv.smoreau.beontime.client.actions.ActionsList;
import fr.umlv.smoreau.beontime.model.timetable.Course;

/**
 * The class <tt>ClipboardManager</tt> represents the clipboard manager.<br/>
 * It's possible to copy, cut and paste.  
 * @author BeOnTime
 */
public class ClipboardManager implements ClipboardOwner {
    public static final String SEPARATOR = ",";

	private static Toolkit toolkit = Toolkit.getDefaultToolkit();
	private static Thread thread = new Thread(new ThreadClipboard());


	/**
	 * Puts <tt>Course</tt> in the system clipboard.<br/>
	 * In fact, it put <tt>TransferableCourse</tt> in the clipboard.
	 * @param course the <tt>course</tt> to put in the system clipboard.
	 * @see fr.umlv.smoreau.beontime.TransferableCourse
	 */
	public static void putCourse(Course course) {
		toolkit.getSystemClipboard().setContents(new TransferableCourse(course), new ClipboardManager());
	}

	/**
	 * Gets the <tt>Course</tt> containing in the system clipboard.<br/>
	 * @return the <tt>course</tt> containing in the clipboard.
	 */
	public static Course getCourse() {
		Transferable tr = toolkit.getSystemClipboard().getContents(null);
		DataFlavor[] dfs = tr.getTransferDataFlavors();

		Course course = null;

		for (int i = 0; i < dfs.length; ++i) {
			if (!dfs[i].isFlavorSerializedObjectType()) {
				continue;
			}
			try {
				course = (Course) tr.getTransferData(dfs[i]);
			} catch (Exception e) {
			    // ne rien faire, le cours est déjà à null
			}
		}

		return course;
	}

	/**
	 * Empty implementation of the ClipboardOwner interface.
	 */
    public void lostOwnership(Clipboard arg0, Transferable arg1) {
        // do nothing
    }
    
    public static void runClipboardThread() {
        if (!thread.isAlive())
            thread.start();
    }
    
    
    private static class ThreadClipboard implements Runnable {
        public void run() {
            while(true) {
                boolean enable = ActionsList.getAction("PasteCourse").isEnabled();
                boolean isCourse = (getCourse() != null);

                if (enable != isCourse)
                    ActionsList.getAction("PasteCourse").setEnabled(isCourse);

                try {
    				Thread.sleep(5000);
    			} catch (InterruptedException e) {
    				e.printStackTrace();
    			}
            }
        }
    }
}