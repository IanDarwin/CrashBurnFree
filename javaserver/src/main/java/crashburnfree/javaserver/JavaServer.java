package crashburnfree.javaserver;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import crashburnfree.client.Report;

@Path("/submit")
public class JavaServer {

	// @PersistenceUnit(name="crashburnfree") EntityManagerFactory emf;
	
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	// @Transactional(value=TxType.REQUIRED)
	public Response upload(String s, @HeaderParam("Authorization") String authorization) throws Exception {
		System.out.println("JavaServer.upload: " + s);
try {
		// Re-create the Report (including the Throwable) from JSON
		Report rept = new Report();
		JSONObject root = new JSONObject(new JSONTokener(s));
		rept.when = new Date(root.getLong("when"));
		rept.where = root.getString("where");
		rept.opSys = root.getString("opSys");
		rept.osVer = root.getString("osVer");
		rept.device = root.getString("device");
		rept.description = root.getString("description");

		// Re-create the Throwable
		JSONObject throwable = root.getJSONObject("exception");
		String clazzName = throwable.getString("class");
		Throwable t = (Throwable)Class.forName(clazzName).newInstance();
		JSONArray trace = throwable.getJSONArray("stacktrace");
		StackTraceElement[] stes = new StackTraceElement[trace.length()];
		for (int i = 0; i < trace.length(); i++) {
			JSONObject ste = trace.getJSONObject(i);
			stes[i] = new StackTraceElement(
				ste.getString("className"), ste.getString("methodName"),
				ste.getString("fileName"), ste.getInt("lineNumber"));
		}
		t.setStackTrace(stes);
		rept.exception = t;

		// Now you'd have to actually do something with the Report
		// For now just print it to show it got here
		System.out.println(rept);
		rept.exception.printStackTrace();
		
		// Indicate success
		return Response.created(null).build();
} catch (Exception ex) {
	ex.printStackTrace();
	return Response.serverError().build();
}
	}
}
