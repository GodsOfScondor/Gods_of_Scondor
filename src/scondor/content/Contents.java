package scondor.content;

import java.util.List;

import scondor.deck.card.CardData;
import scondor.deck.card.troops.TroopCardData;
import scondor.packets.CardList;
import scondor.packets.Message;
import scondor.server.Client;

public class Contents {
	
//	private static final File cards = new File("res/data/cards.dat");
	
	private List<CardData> cards;
	
	public void load() {
		try {
			System.out.println("Starting content loading...");
			Client.send(new Message("request;cardlist"));
			synchronized (this) { wait(); }
			System.out.println("... finished content loading!");
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void incoming(CardList list) {
		cards = (List<CardData>) list.getEntry("LIST");
		System.out.println(cards.size());
		synchronized (this) { notify(); }
	}
	
	public void close() {
		for (CardData data : cards) {
			if (data instanceof TroopCardData) {
				System.out.println("here" + data.getID());
			}
		}
	}
	
	public List<CardData> getAvaibleCards() {
		return cards;
	}
	
}
