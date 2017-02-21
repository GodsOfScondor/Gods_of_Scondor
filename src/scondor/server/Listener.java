package scondor.server;

import scondor.gnet.client.ClientEventListener;
import scondor.gnet.client.ServerModel;
import scondor.gnet.packet.Packet;
import scondor.packets.Message;
import scondor.panels.Panels;
import scondor.panels.start.Connector;
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
					int code = Integer.parseInt((String) packet.getEntry("MESSAGE"));
					
					String msg = "1,0,0:Invalid code!";
					if (code < 5) msg = Connector.msgFromServer(code);
					
					String[] buffer = msg.split(":");
					
					if (buffer.length==2) {
						Panels.popup(buffer[1], Float.parseFloat(buffer[0].split(",")[0]), Float.parseFloat(buffer[0].split(",")[1]), Float.parseFloat(buffer[0].split(",")[2]));
					} else {
						Panels.popup(buffer[2], Float.parseFloat(buffer[0].split(",")[0]), Float.parseFloat(buffer[0].split(",")[1]), Float.parseFloat(buffer[0].split(",")[2]), Integer.parseInt(buffer[1]));
					}
				}
			});
		}
		
	}

}
