package fr.umlv.smoreau.beontime.client;

import java.awt.datatransfer.*;
import java.io.IOException;

import fr.umlv.smoreau.beontime.model.timetable.Course;

/**
 * The class <tt>TransferableCourse</tt> is used to provide data for a transfer operation.<br/>
 * The datas to transfer are <tt>Course</tt> type.
 * @author BeOnTime
 */
public class TransferableCourse implements Transferable {
    private static final DataFlavor FLAVOR = new DataFlavor(Course.class, DataFlavor.javaSerializedObjectMimeType);
	private static final DataFlavor[] SUPPORTED_FLAVORS = {FLAVOR};

	private final Course course;

	public static final String MIME_TYPE = FLAVOR.getMimeType();
	public final static DataFlavor DATA_FLAVOR = new DataFlavor(TransferableCourse.class, MIME_TYPE);

	/**
	 * Builds a <tt>TransferableCourse</tt> using only one <tt>Course</tt>.<br/>
	 * @param course the <tt>Course</tt> to transfer with this <tt>TransferableCourse</tt>.
	 */
	public TransferableCourse(Course course) {
	    this.course = course;
	}

	/**
	 * Returns an object which represents the data to be transferred.<br/>
	 * The class of the object returned is defined by the representation class of the flavor.
	 * @param flavor the requested flavor for the data.
	 */
	public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
		if (!isDataFlavorSupported(flavor))
		    throw new UnsupportedFlavorException(flavor);
		return course;
	}

	/**
	 * Returns an array of DataFlavor objects indicating the flavors the data can be provided in.<br/>
	 * The array should be ordered according to preference for providing the data <i>(from most richly descriptive to least descriptive)</i>.
	 * @return an array of data flavors in which this data can be transferred.
	 */
	public DataFlavor[] getTransferDataFlavors() {
		return SUPPORTED_FLAVORS;
	}

	/**
	 * Returns whether or not the specified data flavor is supported for this object.
	 * @param flavor the requested flavor for the data.
	 * @return boolean indicating whether or not the data flavor is supported.
	 */
	public boolean isDataFlavorSupported(DataFlavor flavor) {
		return FLAVOR.equals(flavor);
	}
}