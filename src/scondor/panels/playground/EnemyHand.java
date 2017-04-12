package scondor.panels.playground;

import scondor.components.Container;
import scondor.components.Containers;
import scondor.components.Picture;
import scondor.image.Images;

public class EnemyHand extends Container {
	
	private static final int PRIORITY = 2;
	private Picture[] handcards;
	private int n;
	private int MAX_CARDS = 8;
	private int amount;
	
	public EnemyHand() {
		super(PRIORITY);
		
		/*
		 * create components
		 */
		
		handcards = new Picture[MAX_CARDS];
		
		for (n=0;n<MAX_CARDS;n++) {
			handcards[n] = new Picture(Images.CARD_BACK, -100, -60, 80, 110, false);
			handcards[n].setLayer(0.1f-0.001f*n);
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
	
	public void updateCards(int amount) {
		this.amount = amount;
		for (n=0;n<MAX_CARDS;n++) {
			if (n<amount) {
				handcards[n].setCompX(440 - 40*n+((amount*40)/2));
				handcards[n].fade(0, 1, 0);
			} else {
				handcards[n].fade(1, 0, 0);
			}
		}
	}

	@Override
	public void refresh() {
		if (Containers.getPlayground().isOnTurn()) {
			for (n = 0; n < MAX_CARDS; n++)
				if (n<amount) handcards[n].setCompY(-120);
		} else {
			for (n = 0; n < MAX_CARDS; n++)
				if (n<amount) handcards[n].setCompY(-60);
		}
	}

	@Override
	public int getID() {
		return -1;
	}
	
}