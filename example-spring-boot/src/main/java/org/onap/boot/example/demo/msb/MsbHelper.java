package org.onap.boot.example.demo.msb;

import java.net.InetAddress;
import java.util.HashSet;
import java.util.Set;

import org.onap.msb.sdk.discovery.entity.MicroServiceInfo;
import org.onap.msb.sdk.discovery.entity.Node;
import org.onap.msb.sdk.httpclient.msb.MSBServiceClient;

public class MsbHelper {

  private MSBServiceClient msbClient;

  public MsbHelper(MSBServiceClient msbClient) {
    super();
    this.msbClient = msbClient;
  }

  public void registerMsb() throws Exception {


    MicroServiceInfo msinfo = new MicroServiceInfo();

    msinfo.setServiceName("employee");
    msinfo.setVersion("v1");
    msinfo.setUrl("/api/v1");
    msinfo.setProtocol("REST");
    msinfo.setVisualRange("0|1");
    
    Set<Node> nodes = new HashSet<>();
    Node node1 = new Node();
    node1.setIp(InetAddress.getLocalHost().getHostAddress());
    node1.setPort("8080");
    nodes.add(node1);
    msinfo.setNodes(nodes);
    msbClient.registerMicroServiceInfo(msinfo, false);
  }
}
