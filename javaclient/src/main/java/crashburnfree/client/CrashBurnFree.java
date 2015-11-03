package crashburnfree.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;
import java.util.Date;
import java.util.regex.Pattern;

public class CrashBurnFree {

	private static final String URL =
			"http://localhost:8080/crashburnfree/submit";
	private static final String AUTH_HEADER = "Authorization";

	private static String encodeAuth(String userName, String password) {
		String cred = userName + ":" + password;
		return new String(Base64.getEncoder().encode(cred.getBytes()));
	}

	class Acknowledgement {
		String message;
	}

	public void establish() {
		Thread.setDefaultUncaughtExceptionHandler(
				new Thread.UncaughtExceptionHandler(){
					public void uncaughtException(Thread t, Throwable ex) {
						System.out.println(
								"CrashBurnFree.establish(): Crashed in thread " + t.getName());
						System.out.println(
								"CrashBurnFree.establish(): Handler running in thread " + t.getName());
						System.out.println(
								"CrashBurnFree.establish():Exception was: " + ex.toString());
						Report r = new Report();
						r.opSys = System.getProperty("system.os.name");
						r.osVer = System.getProperty("system.os.version");
						r.when = new Date();
						r.description = "Happened on thread " + t.getName();
						r.exception = ex;
						try {
							send(r);
						} catch (Exception e) {
							System.err.println("Failed to send exception to reporter: " + ex);
							// The end of the line - we give up
						}
					}
				});

	}
	
	/** This is ugly and brutal, but avoids extra dependencies and especially conflicts
	 * where somebody will have used a different version of whichever of the 26 or so
	 * Java JSON parsers we choose...
	 * @param t The Throwable
	 * @return The Throwable in JSON format
	 */
	public int send(Report r) throws Exception {
		URL url = new URL(URL);
		URLConnection conn = url.openConnection();
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setAllowUserInteraction(true);

		conn.connect();

		PrintWriter out = new PrintWriter(conn.getOutputStream());
		out.println(JsonConverters.ReportToJSON(r));
		out.close();			// Important!

		BufferedReader in = new BufferedReader(
				new InputStreamReader(conn.getInputStream()));
		String line = in.readLine(); // Must be of form HTTP/1.1 200 OK
		in.close();
		Pattern regex = Pattern.compile("HTTP/\\d\\.\\d\\s+(\\d{3})\\s+.*");
		if (!regex.matcher(line).matches()) {
			throw new IllegalStateException("Unknown response from web service");
		}
		return Integer.parseInt(regex.matcher(line).group(1));
	}
}
