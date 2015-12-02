package kz.greetgo.utilgg;

import sun.jvmstat.monitor.*;

import java.net.URISyntaxException;

public class JvmProbes {
  public static void main(String[] args) throws MonitorException, URISyntaxException {
    MonitoredHost host = MonitoredHost.getMonitoredHost((String) null);
    for (Integer id : host.activeVms()) {

      MonitoredVm monitoredVm = host.getMonitoredVm(new VmIdentifier(id.toString()));

      Object value = monitoredVm.findByName("sun.rt.javaCommand").getValue();
      System.out.println(value);
    }
  }
}
