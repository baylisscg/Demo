/*
 *
 */

package au.org.aurin.test.async;

import org.jboss.resteasy.plugins.server.tjws.TJWSEmbeddedJaxrsServer;
import au.org.aurin.test.async.PingPongService;

public class Ping {

  protected Ping() throws Exception {
    TJWSEmbeddedJaxrsServer tjws = new TJWSEmbeddedJaxrsServer();
    tjws.setPort(8080);
    tjws.getDeployment().getActualResourceClasses().add(PingPongService.class);
    tjws.start();
  }

  /**
   *
   */
  public static void main(String args[]) throws Exception {
    Ping ping = new Ping();
  }

}
