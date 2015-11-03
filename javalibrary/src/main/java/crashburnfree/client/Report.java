package crashburnfree.client;

import java.util.Date;

/** This is one error report */
public class Report {
	/** When. Because Android will never support java.time, despite that it's way better */
	public Date when;
	/** Where the crash happened, if known (and allowed by your privacy policy) */
	public String where;
	/** Kind of device */
	public String device;
	public String opSys;
	public String osVer;
	/** User comment on what they did to cause, if available */
	public String description;
	/** The reported error */
	public Throwable exception;
	
	public void setWhen(Date when) {
		this.when = when;
	}
	public Date getWhen() {
		return when;
	}
}
