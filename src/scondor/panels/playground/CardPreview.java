package scondor.panels.playground;

import scondor.components.Card;
import scondor.components.Container;
import scondor.deck.card.CardData;
import scondor.inputs.Mouse;

public class CardPreview extends Container {

	private static final int PRIORITY = 3;
	private static Card preview;
	
	public CardPreview() {
		super(PRIORITY);
		
		/*
		 * create components
		 */
		
		preview = new Card(null, 380, 300, 3, false);
		
		/*
		 * add components to container
		 */
		
		super.add(preview);
		
		/*
		 * validate container
		 */
		
		super.validate();
		
	}
	
	public void showCard(CardData card) {
		preview.changeData(card);
		preview.fade(0, 1, 0);
	}
	
	@Override
	public void refresh() {
		if (!preview.isMouseOver()&&Mouse.isButtonTyped(0)) preview.fade(1, 0, 0);
	}
	
	@Override
	protected void discard() {
		preview.fade(1, 0, 0);
	}

	@Override
	public int getID() {
		return -1;
	}

}
