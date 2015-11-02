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
						Acknowledgement ack = ClientBuilder.newClient()
								.target(URL)
								.request(MediaType.APPLICATION_JSON)
								.header(AUTH_HEADER, encodeAuth("robin", "secret54321"))
								.get(Acknowledgement.class);
						System.out.println("Got an event: " + ack);
					}
				});

	}
}