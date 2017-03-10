package scondor.deck.card.spell;

import scondor.deck.card.CardData;
import scondor.mana.ManaType;

public class SpellCardData extends CardData {

	private static final long serialVersionUID = -8000955067669392632L;

	/**
	 *
	 * repesents raw data of a spell card
	 * 
	 */
	public SpellCardData(int id, String name, String description, int mana_cost, ManaType mana_type) {
		super(id, name, description, mana_cost, mana_type);
	}

}
