package scondor.server;

import org.gnet.client.GNetClient;

public class Client extends GNetClient {

	 private static final String IP = "84.200.106.98";
	 private static final int PORT = 2882;
	
	public Client() {
		super(IP, PORT);
		bind();
		start();
	}

	
}
