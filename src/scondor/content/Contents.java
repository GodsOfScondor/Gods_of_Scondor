package scondor.content;

import java.util.List;

import scondor.deck.DeckData;
import scondor.deck.card.CardData;
import scondor.packets.CardList;
import scondor.packets.DeckList;
import scondor.packets.Message;
import scondor.server.Client;

public class Contents {
	
//	private static final File cards = new File("res/data/cards.dat");
	
	private List<CardData> cards;
	private List<DeckData> decks;
	
	public void load() {
		
		System.out.println("Starting content loading...");
		
		request("request;cardlist");
		request("request;decklist");
		
		System.out.println("... finished content loading!");
		
	}
	
	public void request(String request) {
		try {
			Client.send(new Message(request));
			synchronized (this) { wait(); }
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void incoming(CardList list) {
		cards = (List<CardData>) list.getEntry("LIST");
		System.out.println("Cards: "+cards.size());
		synchronized (this) { notify(); }
	}
	
	@SuppressWarnings("unchecked")
	public void incoming(DeckList list) {
		decks = (List<DeckData>) list.getEntry("LIST");
		System.out.println("Decks: "+decks.size());
		synchronized (this) { notify(); }
	}
	
	public void close() {
		
	}
	
	public List<CardData> getAvaibleCards() {
		return cards;
	}
	
	public List<DeckData> getAvaibleDecks() {
		return decks;
	}
	
}
