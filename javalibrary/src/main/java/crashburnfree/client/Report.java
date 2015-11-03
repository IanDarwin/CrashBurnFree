package crashburnfree.client;

import java.util.Date;

/** This is one error report */
public class Report {
	/** When. Because Android will never support java.time, despite that it's way better */
	Date when;
	/** Where the crash happened, if known (and allowed by your privacy policy) */
	String location;
	/** Kind of device */
	String device;
	String opSys;
	String osVer;
	/** User comment on what they did to cause, if available */
	String description;
	/** The reported error */
	Throwable exception;
}
