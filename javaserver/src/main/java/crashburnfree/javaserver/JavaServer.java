package crashburnfree.javaserver;

import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import crashburnfree.client.Report;

@Path("/submit")
public class JavaServer {

	// @PersistenceUnit(name="crashburnfree") EntityManagerFactory emf;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	// @Transactional(value=TxType.REQUIRED)
	public void upload(Report r, @HeaderParam("Authorization") String authorization) {
		System.out.println("JavaServer.upload: " + r);
		
		// Here you'd have to actually do something with the Throwable.
		
	}
}
