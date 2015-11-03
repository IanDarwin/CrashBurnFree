package crashburnfree.client;

import org.junit.Test;
import static org.junit.Assert.*;

public class JsonConvertersTest {
	
	@Test
	public void testThrowableToJson() {
		IllegalArgumentException data = new IllegalArgumentException("The best test in jest");
		String actual = JsonConverters.throwableToJson(data);
		// "{\"class\":\"java.lang.IllegalArgumentException\",\"message\":\"The best test in jest\",
		// \"localizedMessage\":\"The best test in jest\",\"suppressed\":[]},
		// \"stacktrace\":[{\"methodName\":\"testThrowableToJson\",\"fileName\":\"JsonConvertersTest.java\",\"lineNumber\":10,\"className\":\"crashburnfree.client.JsonConvertersTest\",\"nativeMethod\":false},
		assertTrue("Throwable To JSON", actual.startsWith("{\"class\":\"java.lang.IllegalArgumentException\",\"message\":\"The best test in jest\""));
		System.out.println("Actual JSON for validation:");
		System.out.println(actual);
	}
	
	@Test
	public void testAppendField() {
		StringBuilder sb = new StringBuilder();
		JsonConverters.appendField(sb, "test5678", Integer.valueOf(42), false);
		assertEquals("\"test5678\":\"42\"", sb.toString());
	}
}
