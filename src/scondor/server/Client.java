package scondor.server;

import java.util.ArrayList;
import java.util.List;

import scondor.gnet.client.GNetClient;
import scondor.gnet.client.ServerModel;
import scondor.gnet.packet.Packet;
import scondor.util.Action;

public class Client extends GNetClient {

	private static final String IP = "84.200.106.98";
	private static final int PORT = 2882;
	private static ServerModel server;
	private static List<Action> actions = new ArrayList<>();
	
	public Client() {
		super(IP, PORT);
		bind();
		start();
		addEventListener(new Listener());
		while (server==null)
			try { Thread.sleep(100); } 
			catch (InterruptedException e) { e.printStackTrace(); }
	}
	
	public static void sendToServer(Packet packet) {
		server.sendPacket(packet);
//		if (packet instanceof Message) System.out.println((String) packet.getEntry("MESSAGE"));
	}
	
	protected static void setServerModel(ServerModel server) {
		Client.server = server;
	}
	
	public static void update() {
		synchronized (actions) {
			for (Action action : actions) {
				action.perform();
			}
			actions.clear();
		}
	}
	
	public static void add(Action action) {
		synchronized (actions) {
			actions.add(action);
		}
	}

}
