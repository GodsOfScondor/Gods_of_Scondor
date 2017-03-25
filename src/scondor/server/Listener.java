package scondor.server;

import scondor.Engine;
import scondor.gnet.client.ClientEventListener;
import scondor.gnet.client.ServerModel;
import scondor.gnet.packet.Packet;
import scondor.packets.CardList;
import scondor.packets.DeckList;
import scondor.packets.Message;
import scondor.panels.deck.DeckStarter;
import scondor.panels.shop.ShopHandler;
import scondor.util.Action;
import scondor.util.Messanger;

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
					
					if (code < 5) msg = Engine.getConnection().msgFromServer(code);
					else if (code < 7) msg = ShopHandler.msgFromServer(code);
					else if (code < 8) msg = DeckStarter.msgFromServer(code);
					
					Messanger.popup(msg);
				}
			});
		}
		
		/*
		 * incoming content from server
		 */
		else if (packet instanceof CardList || packet instanceof DeckList) {
			Engine.getConnection().incoming(packet);
		}
		
	}

}
