package fr.umlv.smoreau.beontime.client;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import fr.umlv.smoreau.beontime.model.timetable.Course;

/**
 * The class <tt>ClipboardManager</tt> represents the clipboard manager.<br/>
 * It's possible to copy, cut and paste.  
 * @author BeOnTime
 */
public class ClipboardManager implements ClipboardOwner {
    public static final String SEPARATOR = ",";
    public static final int UNKNOW = 0;
	public static final int COPY   = 1;
	public static final int CUT    = 2;

	private Toolkit toolkit;
	private Transferable tr;
	private int type;

	/**
	 * Constructs a <tt>ClipboardManager</tt>.
	 */
	public ClipboardManager() {
	    toolkit = Toolkit.getDefaultToolkit();
	    type = UNKNOW;
	}

	/**
	 * Puts <tt>Course</tt> in the system clipboard.<br/>
	 * The action type is UNKNOW by default.
	 * @param course the <tt>course</tt> to put in the system clipboard.
	 */
	public void putCourse(Course course) {
	    putCourse(course, UNKNOW);
	}

	/**
	 * Puts <tt>Course</tt> in the system clipboard.<br/>
	 * In fact, it put <tt>TransferableCourse</tt> in the clipboard.
	 * @param course the <tt>course</tt> to put in the system clipboard.
	 * @param type the action type, it could be <tt>COPY</tt> or <tt>CUT</tt>.
	 * @see fr.umlv.smoreau.beontime.TransferableCourse
	 */
	public void putCourse(Course course, int type) {
		toolkit.getSystemClipboard().setContents(new TransferableCourse(course), this);
		this.type = type;
	}

	/**
	 * Gets the <tt>Course</tt> containing in the system clipboard.<br/>
	 * @return the <tt>course</tt> containing in the clipboard.
	 */
	public Course getCourse() {
		Transferable tr = toolkit.getSystemClipboard().getContents(null);
		DataFlavor[] dfs = tr.getTransferDataFlavors();

		Course course = null;

		for (int i = 0; i < dfs.length; ++i) {
			if (!dfs[i].isFlavorSerializedObjectType()) {
				continue;
			}
			try {
				course = (Course) tr.getTransferData(dfs[i]);
			} catch (UnsupportedFlavorException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return course;
	}

	/**
	 * Gets the action type.<br/>
	 * It could be <tt>COPY</tt>, <tt>CUT</tt> or <tt>UNKNOW</tt>.
	 * @return the action type.
	 */
	public int getType() {
		if (this.tr == toolkit.getSystemClipboard().getContents(null)) {
			return type;
		}
		return UNKNOW;
	}

	/**
	 * Empty implementation of the ClipboardOwner interface.
	 */
    public void lostOwnership(Clipboard arg0, Transferable arg1) {
        // do nothing
    }
}