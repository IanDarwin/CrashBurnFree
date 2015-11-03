package crashburnfree.javaserver;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("/submit")
public class JavaServer {

	@GET
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public void upload(Throwable t, @HeaderParam("Authorization") String authorization) {
		System.out.println("JavaServer.upload: " + t);
		// Do something with it...
	}
}
