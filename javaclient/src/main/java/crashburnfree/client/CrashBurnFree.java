package crashburnfree.client;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.Date;

public class CrashBurnFree {

	private static final String URL =
			"http://localhost:8080/crashburnfree/submit";
	private static final String AUTH_HEADER = "Authorization";
	
	private static String encodeAuth(String userName, String password) {
		String cred = userName + ":" + password;
		return new String(Base64.getEncoder().encode(cred.getBytes()));
	}

	public static void register(long devNumber, String devToken) {
		Thread.UncaughtExceptionHandler old = Thread.getDefaultUncaughtExceptionHandler();
		if (old != null) {
			System.err.println("Replacing old exception catcher " + old);
		}
		Thread.setDefaultUncaughtExceptionHandler(
				new Thread.UncaughtExceptionHandler(){
					public void uncaughtException(Thread t, Throwable ex) {
						System.out.println(
								"CrashBurnFree.handler: Crashed in thread " + t.getName());
						System.out.println(
								"CrashBurnFree.handler: Handler running in thread " + t.getName());
						System.out.println(
								"CrashBurnFree.handler: Exception was: " + ex.toString());
						Report r = new Report();
						r.opSys = System.getProperty("system.os.name");
						r.osVer = System.getProperty("system.os.version");
						r.when = new Date();
						r.description = "Happened on thread " + t.getName();
						r.exception = ex;
						try {
							int n = send(r, encodeAuth(Long.toString(devNumber), devToken));
							System.out.println("Web service response was: " + n);
						} catch (Exception e) {
							System.err.println("Failed to send exception to reporter: " + ex);
							// The end of the line - we give up
						}
					}
				});
		System.out.println("CrashBurnFree.register(): registered successfully.");
	}
	
	/** This is ugly and brutal, but avoids extra dependencies and especially conflicts
	 * where somebody will have used a different and incompatible version of whichever of the 26 or so
	 * Java JSON parsers we choose...
	 * @param t The Throwable
	 * @return The Throwable in JSON format
	 */
	public static int send(Report r, String authToken) throws Exception {
		URL url = new URL(URL);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setAllowUserInteraction(true);
		conn.setRequestProperty(AUTH_HEADER, authToken);

		conn.connect();
		
		conn.getOutputStream().write(JsonConverters.reportToJSON(r).getBytes());
		conn.disconnect();
		
		return conn.getResponseCode();
	}
}
