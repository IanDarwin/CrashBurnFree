package crashburnfree.client;

/**
 * A simple, contrived demo. As it stands, only works if you run the server locally.
 */
public class CrashBurnFreeDemo {
	public static void main(String[] args) {
		CrashBurnFree.register(CrashBurnFree.DEMO_URL, 12345, "some enchanted evening");
		throw new RuntimeException("mwuh hah hah!");
	}
}
