package scondor.content;

import java.util.List;

import scondor.components.Containers;
import scondor.deck.DeckData;
import scondor.deck.card.CardData;
import scondor.packets.CardList;
import scondor.packets.DeckList;
import scondor.packets.Message;
import scondor.server.Client;
import scondor.util.Action;
import scondor.util.Messanger;

public class Contents {

	// private static final File cards = new File("res/data/cards.dat");

	private List<CardData> cards;
	private List<DeckData> decks;

	public void load() {

		System.out.println("Starting content loading...");

		request("request;cardlist");
		request("request;decklist");
		Client.sendToServer(new Message("money"));

		System.out.println("... finished content loading!");

	}

	public void request(String request) {
		try {
			Client.sendToServer(new Message(request));
			synchronized (this) {
				wait();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void incoming(CardList list) {
		cards = (List<CardData>) list.getEntry("LIST");
		if (((String) list.getEntry("PARAMS")).equalsIgnoreCase("starter cards")) {
			Client.add(new Action() {
				public void perform() {
					Messanger.popup(Messanger.build("Recieved starter deck!", Containers.MAIN, 0, 1, 0));
				}
			});
		}
		synchronized (this) {
			notify();
		}
	}

	@SuppressWarnings("unchecked")
	public void incoming(DeckList list) {
		decks = (List<DeckData>) list.getEntry("LIST");
		System.out.println("Decks: " + decks.size());
		synchronized (this) {
			notify();
		}
		Client.add(new Action() {
			public void perform() {
				Containers.getDeckChooser().setData(decks);
			}
		});
	}

	public void close() {}

	public List<CardData> getAvaibleCards() {
		return cards;
	}

	public List<DeckData> getAvaibleDecks() {
		return decks;
	}

}
