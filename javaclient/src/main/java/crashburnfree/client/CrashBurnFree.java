package crashburnfree.client;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.Date;

/**
 * The main Client for CBF.
 * Usage: 
 *	-- call register() at the start of your application;
 *	-- call send() when you catch an Exception.
 * @author Ian Darwin
 */
public class CrashBurnFree {

	public static final String DEMO_URL =
			"http://localhost:8080/crashburnfree/rest/submit";
	private static final String AUTH_HEADER = "Authorization";
	
	private static String encodeAuth(String userName, String password) {
		String cred = userName + ":" + password;
		return new String(Base64.getEncoder().encode(cred.getBytes()));
	}

	/**
	 * Register the service.
	 * @param url The URL of YOUR CBF server
	 * @param devNumber Your Developer number
	 * @param devToken Your developer password or authtoken
	 */
	public static void register(String url, long devNumber, String devToken) {
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
						// All the fields must be present; nulls break the library, alas.
						r.opSys = System.getProperty("os.name");
						r.osVer = System.getProperty("os.version");
						r.when = new Date();
						r.where = "Unknown";
						r.description = "Happened on thread " + t.getName();
						r.device = "desktop";
						r.exception = ex;
						try {
							int status = send(url, r, encodeAuth(Long.toString(devNumber), devToken));
							System.out.println("Web service response was: " + status);
							if (status != 201) {
								System.out.println("... But I expected a 201 (Created)!!");
							}
						} catch (Exception e) {
							System.err.println("Failed to send exception to reporter: " + ex);
							// The end of the line - we give up
						}
					}
				});
		System.out.println("CrashBurnFree.register(): registered successfully.");
	}
	
	/**
	 * Send the report!
	 * Generates JSON directly; this is UGLY and brutal, but avoids extra dependencies and especially conflicts
	 * where somebody will have used a different and incompatible version of whichever of the 26 or so
	 * Java JSON parsers we choose...
	 * @param r The Throwable
	 * @param authToken Your CBF authentication token
	 * @return The Throwable in JSON format
	 * @throws java.lang.Exception In case of failure.
	 */
	public static int send(String URL, Report r, String authToken) throws Exception {
		URL url = new URL(URL);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setAllowUserInteraction(true);
		conn.setRequestProperty(AUTH_HEADER, authToken);
		conn.setRequestProperty("Content-type", "text/plain"); // Tell JAX-RS we're parsing it ourselves

		conn.connect();
		
		conn.getOutputStream().write(JsonConverters.reportToJSON(r).getBytes());
		conn.disconnect();
		
		return conn.getResponseCode();
	}
}
