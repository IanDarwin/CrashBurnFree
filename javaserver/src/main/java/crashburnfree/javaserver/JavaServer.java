package crashburnfree.javaserver;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;
import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("/submit")
public class JavaServer {

	// @PersistenceUnit(name="crashburnfree") EntityManagerFactory emf;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	// @Transactional(value=TxType.REQUIRED)
	public void upload(Throwable t, @HeaderParam("Authorization") String authorization) {
		System.out.println("JavaServer.upload: " + t);
		
		// Here you'd have to actually do something with the Throwable.
		
	}
}
