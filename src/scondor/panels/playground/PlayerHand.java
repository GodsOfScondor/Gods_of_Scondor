package scondor.panels.playground;

import java.util.List;

import scondor.components.Card;
import scondor.components.Container;
import scondor.components.Containers;
import scondor.deck.card.CardData;

public class PlayerHand extends Container{

	private static final int PRIORITY = 1;

	private Card[] handcards;
	private int n;
	private int MAX_CARDS = 8;

	private int amount;
	
	public PlayerHand() {
		super(PRIORITY);
		
		/*
		 * create components
		 */
		
		handcards = new Card[MAX_CARDS];
		for (n=0;n<MAX_CARDS;n++) {
			handcards[n] = new Card(null, -200, 1000, 2, false);
			handcards[n].setLayer(0.1f-0.01f*n);
		}
		
		/*
		 * add components to container
		 */
		
		for (n=0;n<MAX_CARDS;n++) super.add(handcards[n]);
		
		/*
		 * validate container
		 */
		
		super.validate();
		
	}
	
	public void updateCards(List<CardData> handcards) {
		this.amount = handcards.size();
		for (n = 0; n < MAX_CARDS; n++) {
			if (n < handcards.size()) {
				this.handcards[n].changeData(handcards.get(n));
				this.handcards[n].setCompX(440 - 40 * n + (((handcards.size() - n) * 40) / 2));
				this.handcards[n].fade(0, 1, 0);
			} else {
				this.handcards[n].fade(1, 0, 0);
			}
		}
	}

	@Override
	public void refresh() {
		if (Containers.getPlayground().isOnTurn()) {
			for (n = 0; n < MAX_CARDS; n++)
				if (n<amount) handcards[n].setCompY(860);
		} else {
			for (n = 0; n < MAX_CARDS; n++)
				if (n<amount) handcards[n].setCompY(920);
		}
	}

	@Override
	public int getID() {
		return -1;
	}
	
}
