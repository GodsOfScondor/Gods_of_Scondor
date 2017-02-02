package scondor.deck.card.spell;

import scondor.deck.card.Card;
import scondor.mana.ManaType;

public abstract class Spell extends Card {

	public Spell(int mana_cost, ManaType mana_type) {
		super(mana_cost, mana_type);
	}

}