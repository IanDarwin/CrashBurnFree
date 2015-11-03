package crashburnfree.client;

import org.junit.Test;
import static org.junit.Assert.*;

public class JsonConvertersTest {
	
	@Test
	public void testThrowableToJson() {
		IllegalArgumentException data = new IllegalArgumentException("The best test in jest");
		String actual = JsonConverters.throwableToJson(data);
		String expected = "{\"class\":\"java.lang.IllegalArgumentException\",\"message\":\"The best test in jest\",\"localizedMessage\":\"The best test in jest\",\"suppressed\":[]}\"stacktrace\":[{\"methodName\":\"testThrowableToJson\",\"fileName\":\"JsonConvertersTest.java\",\"lineNumber\":10,\"className\":\"crashburnfree.client.JsonConvertersTest\",\"nativeMethod\":false}{\"methodName\":\"invoke0\",\"fileName\":\"NativeMethodAccessorImpl.java\",\"lineNumber\":-2,\"className\":\"sun.reflect.NativeMethodAccessorImpl\",\"nativeMethod\":true}{\"methodName\":\"invoke\",\"fileName\":\"NativeMethodAccessorImpl.java\",\"lineNumber\":62,\"className\":\"sun.reflect.NativeMethodAccessorImpl\",\"nativeMethod\":false}{\"methodName\":\"invoke\",\"fileName\":\"DelegatingMethodAccessorImpl.java\",\"lineNumber\":43,\"className\":\"sun.reflect.DelegatingMethodAccessorImpl\",\"nativeMethod\":false}{\"methodName\":\"invoke\",\"fileName\":\"Method.java\",\"lineNumber\":483,\"className\":\"java.lang.reflect.Method\",\"nativeMethod\":false}{\"methodName\":\"runReflectiveCall\",\"fileName\":\"FrameworkMethod.java\",\"lineNumber\":50,\"className\":\"org.junit.runners.model.FrameworkMethod$1\",\"nativeMethod\":false}{\"methodName\":\"run\",\"fileName\":\"ReflectiveCallable.java\",\"lineNumber\":12,\"className\":\"org.junit.internal.runners.model.ReflectiveCallable\",\"nativeMethod\":false}{\"methodName\":\"invokeExplosively\",\"fileName\":\"FrameworkMethod.java\",\"lineNumber\":47,\"className\":\"org.junit.runners.model.FrameworkMethod\",\"nativeMethod\":false}{\"methodName\":\"evaluate\",\"fileName\":\"InvokeMethod.java\",\"lineNumber\":17,\"className\":\"org.junit.internal.runners.statements.InvokeMethod\",\"nativeMethod\":false}{\"methodName\":\"runLeaf\",\"fileName\":\"ParentRunner.java\",\"lineNumber\":325,\"className\":\"org.junit.runners.ParentRunner\",\"nativeMethod\":false}{\"methodName\":\"runChild\",\"fileName\":\"BlockJUnit4ClassRunner.java\",\"lineNumber\":78,\"className\":\"org.junit.runners.BlockJUnit4ClassRunner\",\"nativeMethod\":false}{\"methodName\":\"runChild\",\"fileName\":\"BlockJUnit4ClassRunner.java\",\"lineNumber\":57,\"className\":\"org.junit.runners.BlockJUnit4ClassRunner\",\"nativeMethod\":false}{\"methodName\":\"run\",\"fileName\":\"ParentRunner.java\",\"lineNumber\":290,\"className\":\"org.junit.runners.ParentRunner$3\",\"nativeMethod\":false}{\"methodName\":\"schedule\",\"fileName\":\"ParentRunner.java\",\"lineNumber\":71,\"className\":\"org.junit.runners.ParentRunner$1\",\"nativeMethod\":false}{\"methodName\":\"runChildren\",\"fileName\":\"ParentRunner.java\",\"lineNumber\":288,\"className\":\"org.junit.runners.ParentRunner\",\"nativeMethod\":false}{\"methodName\":\"access$000\",\"fileName\":\"ParentRunner.java\",\"lineNumber\":58,\"className\":\"org.junit.runners.ParentRunner\",\"nativeMethod\":false}{\"methodName\":\"evaluate\",\"fileName\":\"ParentRunner.java\",\"lineNumber\":268,\"className\":\"org.junit.runners.ParentRunner$2\",\"nativeMethod\":false}{\"methodName\":\"run\",\"fileName\":\"ParentRunner.java\",\"lineNumber\":363,\"className\":\"org.junit.runners.ParentRunner\",\"nativeMethod\":false}{\"methodName\":\"run\",\"fileName\":\"JUnit4TestReference.java\",\"lineNumber\":86,\"className\":\"org.eclipse.jdt.internal.junit4.runner.JUnit4TestReference\",\"nativeMethod\":false}{\"methodName\":\"run\",\"fileName\":\"TestExecution.java\",\"lineNumber\":38,\"className\":\"org.eclipse.jdt.internal.junit.runner.TestExecution\",\"nativeMethod\":false}{\"methodName\":\"runTests\",\"fileName\":\"RemoteTestRunner.java\",\"lineNumber\":459,\"className\":\"org.eclipse.jdt.internal.junit.runner.RemoteTestRunner\",\"nativeMethod\":false}{\"methodName\":\"runTests\",\"fileName\":\"RemoteTestRunner.java\",\"lineNumber\":675,\"className\":\"org.eclipse.jdt.internal.junit.runner.RemoteTestRunner\",\"nativeMethod\":false}{\"methodName\":\"run\",\"fileName\":\"RemoteTestRunner.java\",\"lineNumber\":382,\"className\":\"org.eclipse.jdt.internal.junit.runner.RemoteTestRunner\",\"nativeMethod\":false}{\"methodName\":\"main\",\"fileName\":\"RemoteTestRunner.java\",\"lineNumber\":192,\"className\":\"org.eclipse.jdt.internal.junit.runner.RemoteTestRunner\",\"nativeMethod\":false}]}";
		assertEquals("Throwable To JSON", expected, actual);
	}
	
	@Test
	public void testAppendField() {
		StringBuilder sb = new StringBuilder();
		JsonConverters.appendField(sb, "test5678", Integer.valueOf(42), false);
		assertEquals("\"test5678\":\"42\"", sb.toString());
	}
}