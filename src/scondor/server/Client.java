package scondor.server;

import scondor.gnet.client.GNetClient;
import scondor.gnet.client.ServerModel;
import scondor.gnet.packet.Packet;

public class Client extends GNetClient {

	private static final String IP = "84.200.106.98";
	private static final int PORT = 2882;
	private static ServerModel server;
	
	public Client() {
		super(IP, PORT);
		bind();
		start();
		addEventListener(new Listener());
	}
	
	public static void send(Packet packet) {
		server.sendPacket(packet);
	}
	
	protected static void setServerModel(ServerModel server) {
		Client.server = server;
	}

}
