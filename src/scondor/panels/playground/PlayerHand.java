package scondor.panels.playground;

import java.util.List;

import scondor.components.Card;
import scondor.components.Component;
import scondor.components.Panel;
import scondor.deck.card.CardData;
import scondor.panels.EffectAble;
import scondor.panels.Panels;

public class PlayerHand extends Panel {
	
	private Card[] handcards;
	private int n;
	private int MAX_CARDS = 8;
	
	public PlayerHand() {
		super(1);
		handcards = new Card[MAX_CARDS];
		for (n=0;n<MAX_CARDS;n++) {
			handcards[n] = new Card(null, MAX_CARDS, n, 2);
			handcards[n].setLayer(0.1f-0.01f*n);
			add(handcards[n]);
		}
		
	}
	
	public void updateCards(List<CardData> handcards) {
		for (n=0;n<MAX_CARDS;n++) {
			if (n<handcards.size()) {
				this.handcards[n].setData(handcards.get(n));
				this.handcards[n].setX(440 - 40*n+(((handcards.size()-n)*40)/2));
				this.handcards[n].setVisible(true);
			} else {
				this.handcards[n].setVisible(false);
			}
		}
	}

	@Override
	public void swipeIn() {
		fade(0, 1, Panels.FADEIN);
		for (Component comp : comps) if (comp instanceof EffectAble<?>) ((EffectAble<?>) comp).fade(0, 1, Panels.FADEIN);
	}

	@Override
	public void swipeOut() {
		fade(1, 0, Panels.FADEOUT);
		for (Component comp : comps) if (comp instanceof EffectAble<?>) ((EffectAble<?>) comp).fade(1, 0, Panels.FADEOUT);
	}
	
}
