package scondor.containers.battlefield;

import java.util.List;

import scondor.components.Card;
import scondor.components.Container;
import scondor.components.Containers;
import scondor.deck.card.CardData;
import scondor.inputs.Mouse;

public class PlayerHand extends Container{

	private static final int PRIORITY = 2;

	private Card[] handcards;
	private CardPreview preview;
	
	private int n;
	private int MAX_CARDS = 8;

	private int amount;
	
	public PlayerHand() {
		super(PRIORITY);
		
		/*
		 * create other containers
		 */
		
		preview = new CardPreview();
		preview.fade(0, 1, 0);
		
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
				this.handcards[n].setCompX(460 - 40 * n + (((handcards.size() - n) * 40) / 2));
				this.handcards[n].fade(0, 1, 0);
			} else {
				this.handcards[n].fade(1, 0, 0);
			}
		}
	}
	
	public void discardCardPreview() {
		preview.discard();
	}
	
	@Override
	public void refresh() {
		boolean onturn = Containers.getBattlefield().isOnTurn();
		for (n = 0; n < MAX_CARDS; n++) if (n<amount) handcards[n].setCompY(onturn?860:920);
		for (n = 0; n < MAX_CARDS; n++) if (n<amount) if (handcards[n].isMouseOver() && (n==amount-1 || !handcards[n+1].isMouseOver())) {
			
			handcards[n].setCompY(onturn?800:860);
			if (Mouse.isButtonTyped(0)) preview.showCard(handcards[n].getData());
		}
		preview.update();
		preview.refresh();
	}

	@Override
	public int getID() {
		return -1;
	}
	
}
