package scondor.panels.deck;

import scondor.components.Container;
import scondor.components.Containers;

public class DeckStarter extends Container {

	private static final int PRIORITY = 1;

	public DeckStarter() {
		super(PRIORITY);
	}

	@Override
	public void refresh() {
		
	}

	@Override
	public int getID() {
		return Containers.DECK_STARTER;
	}
	
	
	
}
