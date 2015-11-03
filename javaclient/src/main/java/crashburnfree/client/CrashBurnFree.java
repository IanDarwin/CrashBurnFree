package crashburnfree.client;

import java.awt.PageAttributes.MediaType;
import java.util.Base64;

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
					public void uncaughtException(Thread t, Throwable ex){
						System.out.println(
								"CrashBurnFree.establish(): Crashed in thread " + t.getName());
						System.out.println(
								"CrashBurnFree.establish(): Handler running in thread " + t.getName());
						System.out.println(
								"CrashBurnFree.establish():Exception was: " + ex.toString());
//						Acknowledgement ack = ClientBuilder.newClient()
//								.target(URL)
//								.request(MediaType.APPLICATION_JSON)
//								.header(AUTH_HEADER, encodeAuth("robin", "secret54321"))
//								.get(Acknowledgement.class);
//						System.out.println("Got an event: " + ack);
						
					}
				});

	}
	
	/** This is ugly and brutal, but avoids extra dependencies and especially conflicts
	 * where somebody will have used a different version of whichever of the 26 or so
	 * Java JSON parsers we choose...
	 * @param t The Throwable
	 * @return The Throwable in JSON format
	 */
	public String exceptionToJSON(Throwable t) {
		//return "{\"cause\":{\"cause\":null,\"stackTrace\":[{\"methodName\":\"main\",\"fileName\":\"PlayHouse.java\",\"lineNumber\":10,\"className\":\"play.PlayHouse\",\"nativeMethod\":false}],\"message\":\"Testing 1 2 3\",\"localizedMessage\":\"Testing 1 2 3\",\"suppressed\":[]},\"stackTrace\":[{\"methodName\":\"main\",\"fileName\":\"PlayHouse.java\",\"lineNumber\":11,\"className\":\"play.PlayHouse\",\"nativeMethod\":false}],\"message\":\"How's this?\",\"localizedMessage\":\"How's this?\",\"suppressed\":[]}";
		return "{\"class\":\"" + t.getClass().getName() + "\",\"message\":\"" + t.getMessage() + "\",\"localizedMessage\":\"How's this?\",\"suppressed\":[]}";

	}
}
