/*
 *
 */

package au.org.aurin.test.async;

import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import au.org.aurin.test.async.PingPongService;

public class Ping {

  protected Ping() throws Exception {
    JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
    sf.setResourceClasses(PingPongService.class);
    sf.setAddress("http://localhost:8081/");    
    sf.create();
  }

  /**
   *
   */
  public static void main(String args[]) throws Exception {
    Ping ping = new Ping();
  }

}
