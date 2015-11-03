package crashburnfree.javaserver;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/** This class is needed to enable REST and to specify the base path for REST services */
@ApplicationPath("/rest")
public class RestApplication extends Application {
	// Empty
}
