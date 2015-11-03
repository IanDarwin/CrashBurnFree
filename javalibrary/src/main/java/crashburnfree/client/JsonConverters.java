package crashburnfree.client;

import java.util.Date;

/** This is ugly and brutal, but avoids extra dependencies and especially conflicts
 * where somebody will have used a different version of whichever of the 26 or so
 * Java JSON parsers we choose...
 */
public class JsonConverters {
	/*
	 * @param t The Throwable
	 * @return The Throwable in JSON format
	 */
	public static String ReportToJSON(Report r) {
		Throwable t = r.exception;
		StringBuilder sb = new StringBuilder('{');
		appendField(sb, "when", r.when);
		appendField(sb, "location", r.location);
		/** Kind of device */
		appendField(sb, "device", r.device);
		appendField(sb, "opSys", r.opSys);
		appendField(sb, "osVer", r.osVer);
		/** User comment on what they did to cause, if available */
		appendField(sb, "description", r.description);
		/** The reported error */
		sb.append("exception':").append(throwableToJson(t)).append("}");
		return sb.toString();
	}
	
	private static void appendField(StringBuilder sb, String name, Object object) {
		appendField(sb, name, object, true);
	}
	
	protected static void appendField(StringBuilder sb, String name, Object object, boolean postComma) {
		if (object == null)
			return;
		sb.append('"').append(name).append('"')
			.append(':')
			.append('"').append(object).append('"');
		if (postComma) {
			sb.append(',');
		}
	}

	protected static String throwableToJson(Throwable t) {
		StringBuilder body = new StringBuilder();
		body.append("{\"class\":\"" + t.getClass().getName() + "\",\"message\":\"" + t.getMessage() + "\",\"localizedMessage\":\"" + t.getLocalizedMessage() + "\",\"suppressed\":[]},");
		StackTraceElement[] traceback = t.getStackTrace();
		if (traceback != null && traceback.length != 0) {
			body.append("\"stacktrace\":[");
			for (StackTraceElement st : traceback) {
				body.append("{\"methodName\":\"" + st.getMethodName() + "\",\"fileName\":\"" + st.getFileName() + "\",\"lineNumber\":" + st.getLineNumber() + ",\"className\":\"" + st.getClassName() + "\",\"nativeMethod\":" + st.isNativeMethod() + "},");
			}
			body.append("]");
		}
		return body.append("}").toString();
	}
}
