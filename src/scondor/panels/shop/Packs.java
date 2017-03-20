package scondor.panels.shop;

import scondor.components.Card;
import scondor.components.Picture;
import scondor.deck.card.CardData;
import scondor.image.Texture;

public class Packs {

	private Card card;
	private Picture pic;

	Texture card_border = new Texture("card_gn_common");
	Texture deck_border = new Texture("arrow");
	
	public Packs(PackType type, CardData data, int x, int y, float size) {
		card = new Card(data, x, y, size);
		
		switch (type) {
		case I_CLOSED:
//			pic = new Picture()
			break;
		case I_OPEN:

			break;
		case X_CLOSED:

			break;
		}
	}

	public void setCard(Card card) {
		this.card = card;
	}
}
