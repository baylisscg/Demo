/**
 *
 */

package au.org.aurin.test.async;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Context;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import javax.servlet.http.HttpServletResponse;

@Path("/")
public class PingPongService {

  private int count = 0;

  // public PingPongService();

  /**
   *
   */
  @GET
  @Path("/ping")
  @Produces("text/plain")
  public Response ping(@Context HttpServletResponse servletResponse){
    return Response.ok("PONG").build();  
  }

  @POST
  @Path("/pong")
  public Response addCustomer() {
    return Response.ok().build();
  }
  
}

