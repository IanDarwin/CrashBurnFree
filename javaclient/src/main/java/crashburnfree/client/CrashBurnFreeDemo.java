package crashburnfree.client;

public class CrashBurnFreeDemo {
	public static void main(String[] args) {
		CrashBurnFree.register(12345, "some enchanted evening");
		throw new RuntimeException("mwuh hah hah!");
	}
}