package scondor.server;

import java.util.List;

import scondor.Engine;
import scondor.deck.card.CardData;
import scondor.gnet.client.ClientEventListener;
import scondor.gnet.client.ServerModel;
import scondor.gnet.packet.Packet;
import scondor.packets.CardList;
import scondor.packets.DeckList;
import scondor.packets.Message;
import scondor.packets.State;
import scondor.panels.shop.PackType;
import scondor.panels.shop.ShopHandler;
import scondor.session.GameType;
import scondor.session.PlayerSideData;
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
					
					String message = (String) packet.getEntry("MESSAGE");
					final String[] parts = message.split(";");
					
					System.out.println(message);
					
					/*
					 * fight command from server
					 */
					if (message.startsWith("fight;")) {
						
						if (parts[1].startsWith("exit")) {
							
						} else if (parts[1].startsWith("start")) {
							
							GameType type = GameType.valueOf(parts[2].toUpperCase());
							String enemy = parts[3];
//							Panels.getPlayground().initData(type, enemy);
//							Panels.show(Panels.PLAYGROUND);
							
						} else if (parts[1].equalsIgnoreCase("action")) {
							
							if (parts[2].equalsIgnoreCase("turn")) {
//								Panels.getPlayground().setOnTurn(true);
							} else if (parts[2].equalsIgnoreCase("wait")) {
//								Panels.getPlayground().setOnTurn(false);
							}
							
						}
						
					} 
					
					/*
					 * answer to money request
					 */
					else if (message.startsWith("money;")) {
						ShopHandler.updateMoney(Integer.parseInt(parts[1]));
					}
					
					/*
					 * MSG code from Server
					 */
					else {
						int code = Integer.parseInt(message);
						
						String msg = "1,0,0:Invalid code!";
						
						if (code < 5) msg = Engine.getConnection().msgFromServer(code);
						else if (code < 7) msg = ShopHandler.msgFromServer(code);
//						else if (code < 8) msg = DeckStarter.msgFromServer(code);
						
						Messanger.popup(msg);
					}
				}
			});
		}
		
		/*
		 * incoming game state
		 */
		if (packet instanceof State) {
			
			Client.add(new Action() {
				public void perform() {
					
					PlayerSideData player = (PlayerSideData) packet.getEntry("PLAYER1");
					PlayerSideData enemy = (PlayerSideData) packet.getEntry("PLAYER2");
					
					String params = (String) packet.getEntry("PARAMS");
					
//					Playground playground = Panels.getPlayground();
//					playground.updateData(player, enemy, params);
					
				}
			});
			
		}
		
		/*
		 * incoming content from server
		 */
		else if (packet instanceof CardList) {
			
			final String params = (String) packet.getEntry("PARAMS");
			
			@SuppressWarnings("unchecked")
			final List<CardData> cards = (List<CardData>) packet.getEntry("LIST");
			
			if (params.startsWith("shop;")) {
				
				Client.add(new Action() {
					public void perform() {
						ShopHandler.incomingData(cards, PackType.valueOf(params.split(";")[1]));
					}
				});
				
			} else {
				Engine.getConnection().incoming(packet);
			}
			
		}
		
		/*
		 * incoming content from server
		 */
		else if (packet instanceof DeckList) {
			Engine.getConnection().incoming(packet);
		}
		
	}


}