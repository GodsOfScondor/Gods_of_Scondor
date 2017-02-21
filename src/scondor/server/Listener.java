package scondor.server;

import scondor.gnet.client.ClientEventListener;
import scondor.gnet.client.ServerModel;
import scondor.gnet.packet.Packet;
import scondor.packets.Message;
import scondor.panels.Panels;
import scondor.util.Action;

public class Listener extends ClientEventListener {

	@Override
	protected void clientConnected(ServerModel server) {
		Client.setServerModel(server);
	}

	@Override
	protected void clientDisconnected(ServerModel server) {}

	@Override
	protected void debugMessage(String msg) {
		System.out.println(msg);
	}

	@Override
	protected void errorMessage(String msg) {
		System.out.println(msg);
	}
	
	/**
	 * 
	 * recieve packets from server
	 * 
	 */
	@Override
	protected void packetReceived(ServerModel server, final Packet packet) {
		
		/*
		 * msg from server
		 */
		if (packet instanceof Message) {
			Client.add(new Action() {
				public void perform() {
					Panels.popup("" + packet.getEntry("MESSAGE"));
				}
			});
		}
		
	}

}
